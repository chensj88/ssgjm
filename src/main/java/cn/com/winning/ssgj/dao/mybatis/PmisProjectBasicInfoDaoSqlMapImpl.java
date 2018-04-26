package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProjectBasicInfoDao;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class PmisProjectBasicInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<PmisProjectBasicInfo> implements PmisProjectBasicInfoDao {

    @Override
    public List<PmisProjectBasicInfo> selectUserCanViewProject(Long userId) {
        return super.getSqlSession().selectList("selectUserCanViewProject",userId);
    }

    @Override
    public List<PmisProjectBasicInfo> selectProjectInfoByCustomerIdAndProjectId(PmisProjectBasicInfo project) {
        return super.getSqlSession().selectList("selectProjectInfoByCustomerIdAndProjectId",project);
    }

    @Override
    public PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId) {
        return super.getSqlSession().selectOne("queryPmisProjectBasicInfoByProjectId",pmId);
    }

    @Override
    public List<Long> selectPmisProjectBasicInfoIdListByCustomerID(PmisProjectBasicInfo basicInfo) {
        return super.getSqlSession().selectList("selectPmisProjectBasicInfoIdListByCustomerID",basicInfo);
    }

    @Override
    public List<Long> selectUserCanViewProjectIdList(Long userId) {
        return super.getSqlSession().selectList("selectUserCanViewProjectIdList",userId);
    }
}
