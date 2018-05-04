package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author chenshijie
 * @title 指挥中心安排 PC端
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-29 16:08
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/siteCenter")
public class EtSiteQuestionInfoController extends BaseController {
    /**
     * 站点问题初始化显示
     * @param info
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> listSiteQuestionInfo(EtSiteQuestionInfo info, Row row){
        long pmid = info.getPmId();
        info.setPmId(null);
        info.setRow(row);
        //站点问题分页显示
        List<EtSiteQuestionInfo> questionInfoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoPaginatedList(info);
        int total = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoCount(info);
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoUserTotal(info);
        //人员信息
        List<String> nameList= new ArrayList<String>();
        List<Integer> numList= new ArrayList<Integer>();
        if(infoList != null && infoList.size() > 0){
            for (EtSiteQuestionInfo en:infoList) {
                nameList.add((String) en.getMap().get("c_name"));
                numList.add((Integer) en.getMap().get("num"));
            }
        }
        //可分配人员信息
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows",questionInfoList);
        result.put("data",this.getEtUserInfo(pmid));
        result.put("nameList",nameList);
        result.put("numList",numList);
        return result;
    }

    /**
     * 柱状图更新
     * @param info
     * @return
     */
    @RequestMapping(value = "/updateChart.do")
    @ResponseBody
    public Map<String,Object> updateChart(EtSiteQuestionInfo info){
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoUserTotal(info);
        //人员信息
        List<String> nameList= new ArrayList<String>();
        List<Integer> numList= new ArrayList<Integer>();
        if(infoList != null && infoList.size() > 0){
            for (EtSiteQuestionInfo en:infoList) {
                nameList.add((String) en.getMap().get("c_name"));
                numList.add((Integer) en.getMap().get("num"));
            }
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("nameList",nameList);
        result.put("numList",numList);
        return result;
    }

    /**
     * 处理方式修改
     * @param info
     * @return
     */
    @RequestMapping(value = "/updateOperate.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String,Object> updateOperate(EtSiteQuestionInfo info){
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 优先级修改
     * @param info
     * @return
     */
    @RequestMapping(value = "/updatePriority.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String,Object> updatePriority(EtSiteQuestionInfo info){
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 分配人修改
     * @param info
     * @return
     */
    @RequestMapping(value = "/updateAllocateUser.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String,Object> updateAllocateUser(EtSiteQuestionInfo info){
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/exportPmisData.do",method = {RequestMethod.POST})
    @ResponseBody
    @ILog
    @Transactional
    public Map<String,Object> exportPmisData(EtSiteQuestionInfo info){
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
}
