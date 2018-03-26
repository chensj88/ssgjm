package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProjectBasicInfoDao;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class PmisProjectBasicInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<PmisProjectBasicInfo> implements PmisProjectBasicInfoDao {

    @Override
    public List<PmisProjectBasicInfo> selectUserProcjectBasicInfo(PmisProjectBasicInfo basicInfo) {
        return super.getSqlSession().selectList("selectUserProcjectBasicInfo",basicInfo);
    }

    @Override
    public List<PmisProjectBasicInfo> selectPmisProjectBasicByKHXXAndIds(PmisProjectBasicInfo project) {
        return super.getSqlSession().selectList("selectPmisProjectBasicByKHXXAndIds",project);
    }

    @Override
    public PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId) {
        return super.getSqlSession().selectOne("queryPmisProjectBasicInfoByProjectId",pmId);
    }
}
