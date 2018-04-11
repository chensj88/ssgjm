package cn.com.winning.ssgj.service.impl;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.dao.PmisContractProductInfoDao;
import cn.com.winning.ssgj.dao.PmisProductInfoDao;
import cn.com.winning.ssgj.dao.SysUserInfoDao;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.service.impl
 * @date 2018-03-20 10:29
 */
@Service
public class CommonQueryServiceImpl implements CommonQueryService {


    @Autowired
    private PmisProjctUserService pmisProjctUserService;
    @Autowired
    private PmisProjectBasicInfoService pmisProjectBasicInfoService;
    @Autowired
    private PmisCustomerInformationService pmisCustomerInformationService;
    @Autowired
    private PmisContractProductInfoDao pmisContractProductInfoDao;
    @Autowired
    private PmisProductInfoDao pmisProductInfoDao;
    @Autowired
    private SysUserInfoService sysUserInfoService;
    @Autowired
    private EtDataCheckService etDataCheckService;
    @Autowired
    private EtEasyDataCheckService etEasyDataCheckService;
    @Autowired
    private EtFlowSurveyService etFlowSurveyService;
    @Override
    public List<NodeTree> queryUserCustomerProjectTreeInfo(Long userId) {
        //获取用户可以查看的项目信息
        PmisProjctUser projctUser = new PmisProjctUser();
        projctUser.setRy(userId);
        List<PmisProjctUser> userList = pmisProjctUserService.getPmisProjctUserList(projctUser);
        List<PmisProjectBasicInfo> basicInfoList = pmisProjectBasicInfoService.getUserProcjectBasicInfo(userList);
        //项目ID的集合
        List<String> pidList = new ArrayList<String>();
        for (PmisProjectBasicInfo basicInfo : basicInfoList) {
            pidList.add(basicInfo.getId()+"");
        }

        PmisCustomerInformation custinfo = new PmisCustomerInformation();
        custinfo.getMap().put("idList",pidList);
        List<PmisCustomerInformation> custInfoList = pmisCustomerInformationService.getCustomerInfoListByProjectList(custinfo);
        List<NodeTree> treeList = new ArrayList<NodeTree>();
        for (PmisCustomerInformation info : custInfoList) {
             NodeTree node = info.getNodeTree();
             node.setNodes(queryCustomerProjectNode(pidList,info.getId()));
             treeList.add(node);
        }
        return treeList;
    }

    @Override
    public List<PmisProductInfo> queryProductOfProjectByProjectIdAndType(long pmId, int type) {
        PmisProductInfo productInfo = new PmisProductInfo();
        productInfo.getMap().put("pks", queryProductIdByProjectIdAndType(pmId,type));
        return pmisProductInfoDao.selectPmisProductInfoListByIdList(productInfo);
    }

    /**
     * 根据项目ID和合同产品类型来获得List<Long> pdIds
     * @param pmId
     * @param type
     * @return pdIds
     */
    private List<Long> queryProductIdByProjectIdAndType(long pmId, int type){
        PmisContractProductInfo cpInfo = new PmisContractProductInfo();
        cpInfo.setHtcplb(type);
        cpInfo.setXmlcb(pmId);
        List<PmisContractProductInfo> cpInfoList = pmisContractProductInfoDao.selectEntityList(cpInfo);
        List<Long> pIds = new ArrayList<Long>();
        for (PmisContractProductInfo info : cpInfoList) {
            pIds.add(info.getCpxx());
        }
        return pIds;
    }

    /**
     * 根据项目id获取项目基本信息
     * @param pmId
     * @return
     */

    @Override
    public PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId) {
        return pmisProjectBasicInfoService.queryPmisProjectBasicInfoByProjectId(pmId);
    }

    @Override
    public List<SysUserInfo> queryProjectUserByCustomerId(Long customerId) {
        List<Long> pmidList = pmisProjectBasicInfoService.getPmisProjectBasicInfoIdListByCustomerID(customerId);
        List<Long> userIdList = pmisProjctUserService.getPmisProjctUserIdListByProjectIdList(pmidList);
        return sysUserInfoService.getSysUserInfoListByUserIdList(userIdList);
    }


    @Override
    public List<PmisProductInfo> queryProductOfProjectByProjectIdAndTypeAndDataType(long pmId, int type, int dataType) {
        PmisContractProductInfo cpInfo = new PmisContractProductInfo();
        cpInfo.setHtcplb(type);
        cpInfo.setXmlcb(pmId);
        List<PmisContractProductInfo> cpInfoList = pmisContractProductInfoDao.selectEntityList(cpInfo);
        if(cpInfoList==null||cpInfoList.size()<1){
            return new ArrayList<>();
        }
        List<Long> pIds = new ArrayList<Long>();
        for (PmisContractProductInfo info : cpInfoList) {
            pIds.add(info.getCpxx());
        }
        PmisProductInfo productInfo = new PmisProductInfo();
        productInfo.getMap().put("pids",pIds);
        if(dataType==3){
            return pmisProductInfoDao.selectEasyDataPmisProductInfoList(productInfo);
        }
        return pmisProductInfoDao.selectBasicDataPmisProductInfoList(productInfo);
    }

    @Override
    public Map<String,List> queryCompletionOfProject(long pmId) {
        List<Integer> projectCompele = new ArrayList<>();
        List<Integer> projectHandle = new ArrayList<>();
        List<String> projectItem = new ArrayList<>();
        EtDataCheck dataCheck = new EtDataCheck();
        dataCheck.setPmId(pmId);
        List<EtDataCheck> dataCheckList = this.etDataCheckService.getEtDataCheckList(dataCheck);
        int dataFialNum = 0; //校验失败总数
        int dataSuccNum = 0; //校验成功总数
        if((dataCheckList != null) &&(dataCheckList.size() > 0)){
            for (EtDataCheck check : dataCheckList) {
                if(!StringUtil.isEmptyOrNull(check.getContent())){
                     JSONArray array = (JSONArray) JSONArray.parse(check.getContent());
                    for (int i = 0; i < array.size(); i++) {
                        Object json = array.get(i);
                        System.out.println(json);
                        if(json.toString().contains("\"F\"")){
                            dataFialNum++;
                        }else{
                            dataSuccNum++;
                        }

                    }
                }else{
                    dataFialNum++;
                }
            }
        }
        projectCompele.add(dataSuccNum);
        projectHandle.add(dataFialNum);
        projectItem.add("基础数据("+(dataFialNum+dataSuccNum)+")");
        //=============校验易用数据=================//
        EtEasyDataCheck easyCheck = new EtEasyDataCheck();
        easyCheck.setPmId(pmId);
        easyCheck.setIsScope(Constants.STATUS_USE);
        List<EtEasyDataCheck> easyDataCheckList = this.etEasyDataCheckService.getEtEasyDataCheckList(easyCheck);
        int easyFailNum = 0; //校验失败总数
        int easySuccNum = 0; //校验成功总数
        if((easyDataCheckList != null) &&(easyDataCheckList.size() > 0)){
            for (EtEasyDataCheck check : easyDataCheckList) {
                if(!StringUtil.isEmptyOrNull(check.getContent())){
                    if ("校验正常".equals(check.getContent())){
                        easySuccNum++;
                    }else {
                        easyFailNum++;
                    }
                }else{
                    easyFailNum++;
                }
            }
        }
        projectCompele.add(easySuccNum);
        projectHandle.add(easyFailNum);
        projectItem.add("易用数据("+(easySuccNum+easyFailNum)+")");
        Map<String,List> result = new HashMap<String,List>();
        result.put("success",projectCompele);
        result.put("handle",projectHandle);
        result.put("item",projectItem);
        return result;
    }

    @Override
    public List<EtFlowSurvey> queryFlowInfoByProject(EtFlowSurvey flowSurvey) {
        List<Long> pdIds = this.queryProductIdByProjectIdAndType(flowSurvey.getPmId(),Constants.PMIS.CPLB_1);
        flowSurvey.getMap().put("pdIds",pdIds);
        etFlowSurveyService.generateEtFlowSurveyData(flowSurvey);
        return null;
    }

    /**
     * 查询项目信息
     * @param pidList 项目IDList
     * @param custId 客户ID
     * @return
     */
    private List<NodeTree> queryCustomerProjectNode(List<String> pidList, Long custId) {
        PmisProjectBasicInfo project  = new PmisProjectBasicInfo();
        project.getMap().put("idList",pidList);
        project.setKhxx(custId);
        List<PmisProjectBasicInfo> basicInfoList = pmisProjectBasicInfoService
                .getPmisProjectBasicByKHXXAndIds(project);
        List<NodeTree> treeList = new ArrayList<NodeTree>();
        for (PmisProjectBasicInfo info : basicInfoList) {
            treeList.add(info.getNodeTree());
        }
        return  treeList;
    }
}
