package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModFunDao;
import cn.com.winning.ssgj.domain.SysModFun;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysModFunDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysModFun> implements SysModFunDao {

    @Override
    public List<Long> selectFunIdsList(SysModFun fun) {
        String statement = "selectFunIdsList";
        return super.getSqlSession().selectList(statement,fun);
    }

    @Override
    public void deleteSysModFuncForIds(Map<String, Object> param) {
        String statement = "deleteSysModFuncForIds";
        super.getSqlSession().update(statement,param);
    }
}
