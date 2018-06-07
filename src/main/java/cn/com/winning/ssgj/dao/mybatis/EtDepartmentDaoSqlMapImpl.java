package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtDepartmentDao;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-23 14:32:40
 */
@Service
public class EtDepartmentDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtDepartment> implements EtDepartmentDao {

    @Override
    public List<EtDepartment> selectDepartmentTypeList(EtDepartment t) {
        return this.getSqlSession().selectList("selectDepartmentTypeList",t);
    }

    @Override
    public int selectDepartmentForSiteQuestionCount(EtDepartment department) {
        return this.getSqlSession().selectOne("selectDepartmentForSiteQuestionCount",department);
    }

    @Override
    public int selectDepartmentForSiteInstallCount(EtDepartment department) {
        return this.getSqlSession().selectOne("selectDepartmentForSiteInstallCount",department);
    }
}
