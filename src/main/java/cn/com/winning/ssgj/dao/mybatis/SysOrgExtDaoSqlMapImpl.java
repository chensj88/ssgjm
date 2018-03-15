package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysOrgExtDao;
import cn.com.winning.ssgj.domain.SysOrgExt;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-15 10:21:04
 */
@Service
public class SysOrgExtDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysOrgExt> implements SysOrgExtDao {

    @Override
    public void callOrgExtInfoProcedure() {
        super.getSqlSession().selectOne("callOrgExtInfoProcedure");
    }
}
