package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysHospitalDeptDao;
import cn.com.winning.ssgj.domain.SysHospitalDept;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
@Service
public class SysHospitalDeptDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysHospitalDept> implements SysHospitalDeptDao {

    @Override
    public List<SysHospitalDept> selectSysHospitalDeptPageListByFuzzy(SysHospitalDept dept) {
        return super.getSqlSession().selectList("selectSysHospitalDeptPageListByFuzzy",dept);
    }

    @Override
    public int selectSysHospitalDeptCountByFuzzy(SysHospitalDept dept) {
        return super.getSqlSession().selectOne("selectSysHospitalDeptCountByFuzzy",dept);
    }

    @Override
    public int selectSysHospitalDeptName(SysHospitalDept dept) {
        return super.getSqlSession().selectOne("selectSysHospitalDeptName",dept);
    }
}
