package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFunDao;
import cn.com.winning.ssgj.domain.SysFun;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysFunDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysFun> implements SysFunDao {

    public Integer selectSysFunCountFuzzy(SysFun t) throws DataAccessException {
        String statement = "select" + t.getClass().getSimpleName() + "CountFuzzy";
        return super.getSqlSession().selectOne(statement, t);
    }

    public List<SysFun> selectSysFunPaginatedListFuzzy(SysFun t) throws DataAccessException {
        String statement = "select" + t.getClass().getSimpleName() + "PaginatedListFuzzy";
        return super.getSqlSession().selectList(statement, t);
    }

    @Override
    public int selectSysFunMaxOrderValue() throws DataAccessException {
        String statement = "selectSysFunMaxOrderValue";
        return super.getSqlSession().selectOne(statement);
    }

    @Override
    public List<SysFun> selectSysFunListForName(SysFun fun) throws DataAccessException {
        String statement = "selectSysFunListForName";
        return super.getSqlSession().selectList(statement, fun);
    }
}
