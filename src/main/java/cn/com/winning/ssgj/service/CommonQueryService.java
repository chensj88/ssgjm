package cn.com.winning.ssgj.service;

import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.service
 * @date 2018-03-20 10:29
 */
public interface CommonQueryService {
    /**
     * 查询用户客户项目树信息
     * @param userId
     * @return
     */
    public List<NodeTree> queryUserCustomerProjectTreeInfo(Long userId);

    /**
     * 根据项目Id和项目类型查询产品信息
     * @param pmId 项目ID
     * @param type 合同产品类型 @see cn.com.winning.ssgj.base.Constants.PMIS.CPLB_* 1 标准产品 9 接口
     * @return products
     */
    public List<PmisProductInfo> queryProductOfProjectByProjectIdAndType(long pmId,int type);

    /**
     * 根据项目ID查询项目信息
     * @param pmId
     * @return
     */
    PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId);

    /**
     * 根据客户ID查询出人员信息
     * @param customerId
     * @return
     */
    public List<SysUserInfo> queryProjectUserByCustomerId(Long customerId);
    /**
     *
     * @param pmId 项目id
     * @param type 合同产品类型 @see cn.com.winning.ssgj.base.Constants.PMIS.CPLB_* 1 标准产品 9 接口
     * @param dataType 数据类别 0 国标数据;1 行标数据；2 共享数据；3 易用数据；
     * @return
     */
    public List<PmisProductInfo> queryProductOfProjectByProjectIdAndTypeAndDataType(long pmId, int type, int dataType);

    /**
     * 查询项目各项工作完成情况
     * @param pmId
     * @return resultMap
     */
    public Map<String,List> queryCompletionOfProject(long pmId);

    /**
     * 根据项目ID获取流程信息
     * @param process
     * @return
     */
    public void generateEtBusinessProcessByProject(EtBusinessProcess process);

}
