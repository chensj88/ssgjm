package cn.com.winning.ssgj.service.impl;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.dao.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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
    private EtBusinessProcessService etBusinessProcessService;
    @Autowired
    private SysFlowInfoService sysFlowInfoService;
    @Autowired
    private SysProductFlowInfoService sysProductFlowInfoService;
    @Autowired
    private PmisProductLineInfoDao pmisProductLineInfoDao;
    @Autowired
    private SSGJHelper ssgjHelper;
    @Autowired
    private EtThirdIntterfaceService etThirdIntterfaceService;
    @Autowired
    private SysDataInfoDao sysDataInfoDao;


    @Override
    public List<NodeTree> queryUserCustomerProjectTreeInfo(Long userId) {
        List<NodeTree> treeList = new ArrayList<NodeTree>();
        List<Long> basicInfoList = pmisProjectBasicInfoService.getUserCanViewProjectIdList(userId);
        List<PmisCustomerInformation> custInfoList = pmisCustomerInformationService.getUserCanViewCustomerList(userId);
        for (PmisCustomerInformation info : custInfoList) {
            NodeTree node = info.getNodeTree();
            node.setNodes(queryCustomerProjectNode(basicInfoList, info.getId()));
            treeList.add(node);
        }
        return treeList;
    }

    @Override
    public List<PmisProductInfo> queryProductOfProjectByProjectIdAndType(long pmId, int type) {
        Map<String,Object> param = new HashMap<>();
        param.put("pmId",pmId);
        param.put("type",type);
        return pmisProductInfoDao.selectProductInfoListByPmIdAndType(param);
    }

    /**
     * 根据项目ID和合同产品类型来获得List<Long> pdIds
     *
     * @param pmId
     * @param type
     * @return pdIds
     */
    private List<Long> queryProductIdByProjectIdAndType(long pmId, int type) {
        Map<String,Object> param = new HashMap<>();
        param.put("pmId",pmId);
        param.put("type",type);
        List<Long> pIds = pmisContractProductInfoDao.selectProcuctIdListByPmIdAndHtcplb(param);
        return pIds;
    }

    /**
     * 根据项目id获取项目基本信息
     *
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
        if (cpInfoList == null || cpInfoList.size() < 1) {
            return new ArrayList<>();
        }
        List<Long> pIds = new ArrayList<Long>();
        for (PmisContractProductInfo info : cpInfoList) {
            pIds.add(info.getCpxx());
        }
        PmisProductInfo productInfo = new PmisProductInfo();
        productInfo.getMap().put("pids", pIds);
        if (dataType == 3) {
            return pmisProductInfoDao.selectEasyDataPmisProductInfoList(productInfo);
        }
        return pmisProductInfoDao.selectBasicDataPmisProductInfoList(productInfo);
    }

    @Override
    public Map<String, List> queryCompletionOfProject(long pmId) {
        List<Integer> projectCompele = new ArrayList<>();
        List<Integer> projectHandle = new ArrayList<>();
        List<String> projectItem = new ArrayList<>();
        //基础数据准备
        dataCheckByProjectId(pmId,projectCompele,projectHandle,projectItem);
        //易用数据准备
        easyDataCheckByProjectId(pmId,projectCompele,projectHandle,projectItem);
        //接口准备
        thirdInterfaceCheckByProjectId(pmId,projectCompele,projectHandle,projectItem);
        //业务流程调研
        businessProcessCheckByProjectId(pmId,projectCompele,projectHandle,projectItem);
        //报表单据
        etReportCheckByProjectId(pmId,projectCompele,projectHandle,projectItem);
        Map<String, List> result = new HashMap<String, List>();
        result.put("success", projectCompele);
        result.put("handle", projectHandle);
        result.put("item", projectItem);
        return result;
    }

    /**
     * 报表单据准备
     * @param pmId
     * @param projectCompele
     * @param projectHandle
     * @param projectItem
     */
    private void etReportCheckByProjectId(long pmId, List<Integer> projectCompele, List<Integer> projectHandle, List<String> projectItem) {
        //TODO
        projectCompele.add(10);
        projectHandle.add(5);
        projectItem.add("单据准备(15)");
        projectCompele.add(12);
        projectHandle.add(2);
        projectItem.add("报表准备(14)");
    }

    /**
     * 业务流程调研
     * @param pmId
     * @param projectCompele
     * @param projectHandle
     * @param projectItem
     */
    private void businessProcessCheckByProjectId(long pmId, List<Integer> projectCompele, List<Integer> projectHandle, List<String> projectItem) {
        EtBusinessProcess process = new EtBusinessProcess();
        process.setPmId(pmId);
        process.setIsScope(Constants.STATUS_USE);
        int total = etBusinessProcessService.getEtBusinessProcessCount(process);
        process.setStatus(9);
        int succ = etBusinessProcessService.getEtBusinessProcessCount(process);
        int fail = total - succ;
        projectCompele.add(succ);
        projectHandle.add(fail);
        projectItem.add("流程调研(" + (total) + ")");
    }

    /**
     * 接口完成情况统计
     * @param pmId
     * @param projectCompele
     * @param projectHandle
     * @param projectItem
     */
    private void thirdInterfaceCheckByProjectId(long pmId, List<Integer> projectCompele, List<Integer> projectHandle, List<String> projectItem) {
        EtThirdIntterface thirdIntterface = new EtThirdIntterface();
        thirdIntterface.setPmId(pmId);
        thirdIntterface.setIsScope(1);
        int total = etThirdIntterfaceService.getEtThirdIntterfaceCount(thirdIntterface);
        int succNum = etThirdIntterfaceService.getEtThirdIntterfaceSuccessCount(thirdIntterface);
        int failNum = total - succNum;
        projectCompele.add(succNum);
        projectHandle.add(failNum);
        projectItem.add("接口准备(" + total + ")");
    }

    /**
     * 易用数据校验
     * @param pmId
     * @param projectCompele
     * @param projectHandle
     * @param projectItem
     */
    private void easyDataCheckByProjectId(long pmId, List<Integer> projectCompele, List<Integer> projectHandle, List<String> projectItem) {
        //=============校验易用数据=================//
        EtEasyDataCheck easyCheck = new EtEasyDataCheck();
        easyCheck.setPmId(pmId);
        easyCheck.setIsScope(Constants.STATUS_USE);
        List<EtEasyDataCheck> easyDataCheckList = this.etEasyDataCheckService.getEtEasyDataCheckList(easyCheck);
        int easyFailNum = 0; //校验失败总数
        int easySuccNum = 0; //校验成功总数
        if ((easyDataCheckList != null) && (easyDataCheckList.size() > 0)) {
            for (EtEasyDataCheck check : easyDataCheckList) {
                if (!StringUtil.isEmptyOrNull(check.getContent())) {
                    if ("校验正常".equals(check.getContent())) {
                        easySuccNum++;
                    } else {
                        easyFailNum++;
                    }
                } else {
                    easyFailNum++;
                }
            }
        }
        projectCompele.add(easySuccNum);
        projectHandle.add(easyFailNum);
        projectItem.add("易用数据(" + (easySuccNum + easyFailNum) + ")");
    }


    /**
     * 基础数据校验
     * @param pmId
     * @param projectCompele
     * @param projectHandle
     * @param projectItem
     */
    private void dataCheckByProjectId(long pmId, List<Integer> projectCompele, List<Integer> projectHandle, List<String> projectItem) {
        EtDataCheck dataCheck = new EtDataCheck();
        dataCheck.setPmId(pmId);
        List<EtDataCheck> dataCheckList = this.etDataCheckService.getEtDataCheckList(dataCheck);
        int dataFialNum = 0; //校验失败总数
        int dataSuccNum = 0; //校验成功总数
        if ((dataCheckList != null) && (dataCheckList.size() > 0)) {
            for (EtDataCheck check : dataCheckList) {
                if (!StringUtil.isEmptyOrNull(check.getContent())) {
                    JSONArray array = (JSONArray) JSONArray.parse(check.getContent());
                    for (int i = 0; i < array.size(); i++) {
                        Object json = array.get(i);
                        System.out.println(json);
                        if (json.toString().contains("\"F\"")) {
                            dataFialNum++;
                        } else {
                            dataSuccNum++;
                        }

                    }
                } else {
                    dataFialNum++;
                }
            }
        }
        projectCompele.add(dataSuccNum);
        projectHandle.add(dataFialNum);
        projectItem.add("基础数据(" + (dataFialNum + dataSuccNum) + ")");
    }

    @Override
    public void generateEtBusinessProcessByProject(EtBusinessProcess process) {
        List<Long> pdIds = this.queryProductIdByProjectIdAndType(process.getPmId(), Constants.PMIS.CPLB_1);
        List<Long> flowIds = null;
        List<SysFlowInfo> flowInfoList = null;
        long pmId = process.getPmId();
        long cId = process.getcId();
        String serialNo = process.getSerialNo();
        String pdIdStr = StringUtil.generateStringSql(pdIds);
        if (pdIds != null && pdIds.size() > 0) {
            flowIds = sysProductFlowInfoService.getSysProductFlowInfoByPdId(pdIdStr);
        }
        if (flowIds != null && flowIds.size() > 0) {
            SysFlowInfo flowInfo = new SysFlowInfo();
            flowInfo.getMap().put("pks", flowIds);
            flowInfoList = this.sysFlowInfoService.getSysFlowInfoListById(flowInfo);
        }
        if (flowInfoList != null && flowInfoList.size() > 0) {
            for (SysFlowInfo info : flowInfoList) {
                EtBusinessProcess queryProcess = new EtBusinessProcess();
                queryProcess.setFlowId(info.getId());
                queryProcess.setcId(cId);
                queryProcess.setPmId(pmId);
                queryProcess.setSerialNo(serialNo);
                queryProcess = this.etBusinessProcessService.getEtBusinessProcess(queryProcess);
                if (queryProcess == null) {
                    queryProcess = new EtBusinessProcess();
                    queryProcess.setId(ssgjHelper.createEtBusinessProcessIdService());
                    queryProcess.setPmId(pmId);
                    queryProcess.setcId(cId);
                    queryProcess.setSerialNo(serialNo);
                    queryProcess.setFlowId(info.getId());
                    queryProcess.setFlowCode(info.getFlowCode());
                    queryProcess.setFlowName(info.getFlowName());
                    queryProcess.setIsScope(Constants.STATUS_USE);
                    queryProcess.setStatus(Constants.STATUS_UNUSE);
                    queryProcess.setCreator(10001L);
                    queryProcess.setCreateTime(new Timestamp(new Date().getTime()));
                    etBusinessProcessService.createEtBusinessProcess(queryProcess);
                }
            }
        }
    }

    /**
     * 查询项目信息,根据客户ID和项目ID
     * @param pidList 项目IDList
     * @param custId  客户ID
     * @return
     */
    private List<NodeTree> queryCustomerProjectNode(List<Long> pidList, Long custId) {
        PmisProjectBasicInfo project = new PmisProjectBasicInfo();
        project.getMap().put("idList", pidList);
        project.setKhxx(custId);
        List<PmisProjectBasicInfo> basicInfoList = pmisProjectBasicInfoService.getProjectInfoByCustomerIdAndProjectId(project);
        List<NodeTree> treeList = new ArrayList<NodeTree>();
        for (PmisProjectBasicInfo info : basicInfoList) {
            treeList.add(info.getNodeTree());
        }
        return treeList;
    }

    /**
     * 根据产品获取产品条线
     *
     * @param pmisProductInfos
     * @return
     */
    public List<PmisProductLineInfo> selectPmisProductLineInfoByProductInfo(List<PmisProductInfo> pmisProductInfos) {
        return pmisProductLineInfoDao.selectPmisProductLineInfoByProductInfo(pmisProductInfos);
    }

    /**
     * 根据表名查询表是否存在数据库中
     * @param tableName
     * @return
     */
    @Override
    public Integer countTable(String tableName) {
        return sysDataInfoDao.countTable(tableName);
    }

}
