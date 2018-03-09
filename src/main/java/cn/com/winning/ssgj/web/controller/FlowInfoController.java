package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 流程信息
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-01-25 17:59
 */
@Controller
@RequestMapping(value = "/admin/flow")
public class FlowInfoController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping("/flowInfo.do")
    public String gotoPage(HttpServletRequest request, Model model) {
        return "auth/module/flowinfo";
    }

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> getFlowList(Row row,SysFlowInfo flowInfo){
        flowInfo.setRow(row);
        List<SysFlowInfo> flowInfos = super.getFacade().getSysFlowInfoService().getSysFlowInfoPaginatedListForSelective(flowInfo);
        int total = super.getFacade().getSysFlowInfoService().getSysFlowInfoCountForSelective(flowInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows",flowInfos);
        return result;
    }

    @RequestMapping(value = "/queryFlowCode.do")
    @ResponseBody
    public Map<String,Object> queryFlowCode(String flowCode,int matchCount){
        Row row = new Row(0,matchCount);
        SysFlowInfo flowInfo = new SysFlowInfo();
        flowInfo.setRow(row);
        flowInfo.setFlowCode(flowCode);
        flowInfo.setFlowType(Constants.Flow.FLOW_TYPE_BIG);
        List<SysFlowInfo> flowInfos = super.getFacade().getSysFlowInfoService().querySysFlowInfoList(flowInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", matchCount);
        result.put("status", Constants.SUCCESS);
        result.put("data", flowInfos);
        return result;

    }
    @RequestMapping(value = "/add.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> addFlowInfo(SysFlowInfo flow){
        flow.setId(ssgjHelper.createFlowId());

        if(Constants.Flow.FLOW_TYPE_SMALL.equals(flow.getFlowType())){
            System.out.println(flow.getFlowCode());
        }else{
            flow.setFlowCode(ssgjHelper.createFlowCode());
        }

        flow.setLastUpdateTime(new Timestamp(new Date().getTime()));
        flow.setStatus(Constants.STATUS_USE);
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        flow.setLastUpdator(userInfo.getId());
        super.getFacade().getSysFlowInfoService().createSysFlowInfo(flow);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/createFlowCode.do")
    @ResponseBody
    public Map<String,Object> createFlowCode(String flowType,String flowCode){
        String reFlowCode = "";
        if(Constants.Flow.FLOW_TYPE_SMALL.equals(flowType) &&!StringUtils.isBlank(flowCode)){
            flowCode += "-";
            reFlowCode = flowCode + super.getFacade().getSysFlowInfoService().createFlowCode(flowCode,flowType);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", reFlowCode);
        result.put("status", Constants.SUCCESS);
        return  result;
    }

    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getFlowById(SysFlowInfo flow){
     flow = super.getFacade().getSysFlowInfoService().getSysFlowInfo(flow);
     Map<String,Object> result = new HashMap<String,Object>();
     result.put("status", Constants.SUCCESS);
     result.put("data", flow);
     return result;
    }

    @RequestMapping(value = "/update.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> updateFlowById(SysFlowInfo flow){
        flow.setLastUpdateTime(new Timestamp(new Date().getTime()));
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        flow.setLastUpdator(userInfo.getId());
        super.getFacade().getSysFlowInfoService().modifySysFlowInfo(flow);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/deleteById.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> deleteFlowById(SysFlowInfo flow){
        flow.setStatus(Constants.STATUS_UNUSE);
        super.getFacade().getSysFlowInfoService().removeSysFlowInfo(flow);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/listNoPage.do")
    @ResponseBody
    public Map<String,Object> queryFlowListNoPage(SysFlowInfo flowInfo){

        List<SysFlowInfo> sysDataInfos = getFacade().getSysFlowInfoService().getSysFlowInfoListForSelectiveKey(flowInfo);
        int total =  getFacade().getSysFlowInfoService().getSysFlowInfoCountForSelectiveKey(flowInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", sysDataInfos);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        return map;

    }

}