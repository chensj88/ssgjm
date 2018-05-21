package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.client.BizProcessResult;
import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
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

    @Autowired
    private PmisWebServiceClient pmisWebServiceClient;
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
     * 处理方式修改/优先级修改/分配人修改
     * @param info
     * @return
     */
    @RequestMapping(value = "/update.do")
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


    @RequestMapping(value = "/exportPmisData.do",method = {RequestMethod.POST})
    @ResponseBody
    @ILog
    @Transactional
    public Map<String,Object> exportPmisData(EtSiteQuestionInfo info){
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        EtSiteQuestionInfo newInfo = new EtSiteQuestionInfo();
        newInfo.setId(info.getId());
        newInfo = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(newInfo);
        BizProcessResult bizResult = null;
        if(newInfo.getPmisStatus() == 2){
            SysUserInfo user = new SysUserInfo();
            user.setUserid(info.getCreateNo());
            user = super.getFacade().getSysUserInfoService().getSysUserInfo(user);
            info.getMap().put("createUser",user.getName());
            //使用WS_TEST_URL 为测试环境  WS_URL为生产环境
             bizResult =  pmisWebServiceClient.importWorkDataToPmis(Constants.PmisWSConstants.WS_TEST_URL,info);
            if(bizResult.getResult() != 1){
                throw new SSGJException(bizResult.getMessage());
            }
        }
        info.setPmisStatus(Constants.STATUS_USE);
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("msg", bizResult.getMessage());
        return result;
    }

    @RequestMapping(value = "/exportBatchPmisData.do")
    @ResponseBody
    public Map<String,Object> exportBatchPmisData(EtSiteQuestionInfo info,Long[] idList){
        EtSiteQuestionInfo oldInfo = null;
        for (int i=0 ;i<idList.length;i++) {
            oldInfo = new EtSiteQuestionInfo();
            oldInfo.setId(idList[i]);
            oldInfo = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(oldInfo);
            if(oldInfo.getPmisStatus() == 2 ){
                oldInfo.setBatchNo(info.getBatchNo());
                oldInfo.setQuestionType(info.getQuestionType());
                oldInfo.setReasonType(info.getReasonType());
                oldInfo.setManuscriptStatus(info.getManuscriptStatus());
                oldInfo.setDiffcultLevel(info.getDiffcultLevel());
                oldInfo.setDevUser(info.getDevUser());
                oldInfo.setDevUserName(info.getDevUserName());
                oldInfo.setLinkman(info.getLinkman());
                oldInfo.setMobile(info.getMobile());
                oldInfo.setSolutionResult(info.getSolutionResult());
                oldInfo.setHopeFinishDate(info.getHopeFinishDate());
                oldInfo.setResolveDate(info.getResolveDate());
                oldInfo.setWorkLoad(info.getWorkLoad());
                oldInfo.setUserMessage(info.getUserMessage());
                oldInfo.setOperator(info.getOperator());
                oldInfo.setOperatorTime(new Timestamp(new Date().getTime()));
                SysUserInfo user = new SysUserInfo();
                user.setId(oldInfo.getCreator());
                user = super.getFacade().getSysUserInfoService().getSysUserInfo(user);
                oldInfo.setCreateNo(user.getUserid());
                super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(oldInfo);
                oldInfo.getMap().put("createUser",user.getName());
                //TODO 上线使用
                BizProcessResult bizResult =  pmisWebServiceClient.importWorkDataToPmis(Constants.PmisWSConstants.WS_TEST_URL,info);
                if(bizResult.getResult() != 1){
                    throw new SSGJException(bizResult.getMessage());
                }
                oldInfo.setPmisStatus(Constants.STATUS_USE);
                super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(oldInfo);
            }
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
//        result.put("data", );
        return result;

    }
}
