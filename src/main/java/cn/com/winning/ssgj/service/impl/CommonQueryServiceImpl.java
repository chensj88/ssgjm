package cn.com.winning.ssgj.service.impl;

import cn.com.winning.ssgj.dao.PmisContractProductInfoDao;
import cn.com.winning.ssgj.dao.PmisProductInfoDao;
import cn.com.winning.ssgj.dao.SysUserInfoDao;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        PmisContractProductInfo cpInfo = new PmisContractProductInfo();
        cpInfo.setHtcplb(type);
        cpInfo.setXmlcb(pmId);
        List<PmisContractProductInfo> cpInfoList = pmisContractProductInfoDao.selectEntityList(cpInfo);
        List<Long> pIds = new ArrayList<Long>();
        for (PmisContractProductInfo info : cpInfoList) {
            pIds.add(info.getCpxx());
        }
        PmisProductInfo productInfo = new PmisProductInfo();
        productInfo.getMap().put("pks",pIds);
        return pmisProductInfoDao.selectPmisProductInfoListByIdList(productInfo);
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
