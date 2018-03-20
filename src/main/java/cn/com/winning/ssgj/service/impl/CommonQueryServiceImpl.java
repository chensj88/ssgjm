package cn.com.winning.ssgj.service.impl;

import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.domain.PmisProjctUser;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.service.CommonQueryService;
import cn.com.winning.ssgj.service.PmisCustomerInformationService;
import cn.com.winning.ssgj.service.PmisProjctUserService;
import cn.com.winning.ssgj.service.PmisProjectBasicInfoService;
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
    @Override
    public List<NodeTree> queryUserCustomerProjectTreeInfo(Long userId) {
        PmisProjctUser projctUser = new PmisProjctUser();
        projctUser.setRy(userId);
        List<PmisProjctUser> userList = pmisProjctUserService.getPmisProjctUserList(projctUser);
        List<PmisProjectBasicInfo> basicInfoList = pmisProjectBasicInfoService.getUserProcjectBasicInfo(userList);
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
