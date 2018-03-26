package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.domain.SysProductFlowInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-11 8:36
 */
@Controller
@RequestMapping(value = "/admin")
public class ProductFlowController extends BaseController {
    /**
     * 获取产品流程页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/mapping/pflowinfo.do")
    public String getProductFlowInfoPage(HttpServletRequest request, Model model) {
        return "auth/mapping/productFlowMapping";
    }


    /**
     * 查询已经配置的产品流程信息
     *
     * @param productFlowInfo 产品流程信息
     * @return
     */
    @RequestMapping(value = "/pFlow/queryById.do")
    @ResponseBody
    public Map<String, Object> queryProductFlowById(SysProductFlowInfo productFlowInfo) {
        List<SysProductFlowInfo> flowInfoList = super.getFacade().getSysProductFlowInfoService().getSysProductFlowInfoList(productFlowInfo);
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        if (flowInfoList != null && flowInfoList.size() > 0) {
            List<String> idString = super.getFacade().getSysFlowInfoService().getFlowInfoId(flowInfoList);
            param.put("pks", idString);
            SysFlowInfo flowInfo = new SysFlowInfo();
            flowInfo.setMap(param);
            List<SysFlowInfo> SysFlowInfoList = super.getFacade().getSysFlowInfoService().getSysFlowInfoListById(flowInfo);
            result.put("status", Constants.SUCCESS);
            result.put("data", SysFlowInfoList);
            return result;
        }
        result.put("status", Constants.SUCCESS);
        result.put("data", "");
        return result;
    }

    /**
     * 查询删除的产品和流程信息
     *
     * @param pdId    产品ID
     * @param flowIds 流程IDS
     * @return
     */
    @RequestMapping(value = "/pFlow/queryProductFlowInfoById.do")
    @ResponseBody
    public Map<String, Object> queryProductFlowInfoById(Integer pdId, String flowIds) {
        List<SysProductFlowInfo> flowInfos = super.getFacade().getSysProductFlowInfoService().getSysProductFlowInfoByPdIdAndFlowId(pdId, flowIds);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", flowInfos);
        return result;
    }

    @RequestMapping(value = "/pFlow/addProductFlowInfo.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addProductFlowInfoMapping(String idList) throws ParseException {

        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        super.getFacade().getSysProductFlowInfoService().addSysProductFlowInfoMapping(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/pFlow/removeMapping.do")
    @ResponseBody
    @ILog
    public Map<String, Object> removeProductFlowInfoMapping(String idList) throws ParseException {
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        super.getFacade().getSysProductFlowInfoService().removeSysProductFlowInfoMappingByIds(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", "");
        return result;
    }


}
