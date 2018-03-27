package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface PmisProjectBasicInfoDao extends EntityDao<PmisProjectBasicInfo> {

    List<PmisProjectBasicInfo> selectUserProcjectBasicInfo(PmisProjectBasicInfo basicInfo);

    List<PmisProjectBasicInfo> selectPmisProjectBasicByKHXXAndIds(PmisProjectBasicInfo project);

    PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId);
}
