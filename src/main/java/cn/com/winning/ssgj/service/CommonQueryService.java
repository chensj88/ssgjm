package cn.com.winning.ssgj.service;

import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.service
 * @date 2018-03-20 10:29
 */
public interface CommonQueryService {

    public List<NodeTree> queryUserCustomerProjectTreeInfo(Long userId);

    /**
     * 根据项目Id和项目类型查询产品信息
     * @param pmId 项目ID
     * @param type 合同产品类型 @see cn.com.winning.ssgj.base.Constants.PMIS.CPLB_* 1 标准产品 9 接口
     * @return products
     */
    public List<PmisProductInfo> queryProductOfProjectByProjectIdAndType(long pmId,int type);


    public List<EtContractTask> queryEtContractTaskByProjectId(long pmId);

    PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId);
}
