package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisProjctUser;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisProjectBasicInfoService {

    Integer createPmisProjectBasicInfo(PmisProjectBasicInfo t);

    int modifyPmisProjectBasicInfo(PmisProjectBasicInfo t);

    int removePmisProjectBasicInfo(PmisProjectBasicInfo t);

    PmisProjectBasicInfo getPmisProjectBasicInfo(PmisProjectBasicInfo t);

    List<PmisProjectBasicInfo> getPmisProjectBasicInfoList(PmisProjectBasicInfo t);

    Integer getPmisProjectBasicInfoCount(PmisProjectBasicInfo t);

    List<PmisProjectBasicInfo> getPmisProjectBasicInfoPaginatedList(PmisProjectBasicInfo t);

    List<PmisProjectBasicInfo> getUserProcjectBasicInfo(List<PmisProjctUser> userList);

    List<PmisProjectBasicInfo> getPmisProjectBasicByKHXXAndIds(PmisProjectBasicInfo project);

    PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId);

    /**
     * 根据客户ID获取所有的项目ID
     * @param customerId 客户信息
     * @return list
     */
    List<Long> getPmisProjectBasicInfoIdListByCustomerID(long customerId);
}