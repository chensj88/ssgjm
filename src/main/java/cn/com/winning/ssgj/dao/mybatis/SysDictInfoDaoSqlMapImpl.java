package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDictInfoDao;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysDictInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysDictInfo> implements SysDictInfoDao {

    @Override
    public List<SysDictInfo> selectEntityListBySelectiveKeyForAnd(SysDictInfo dict) {
        String statement = "selectEntityListBySelectiveKeyForAnd";
        return super.getSqlSession().selectList(statement,dict);
    }

    @Override
    public Integer selectEntityCountBySelectiveKeyForAnd(SysDictInfo dict) {
        String statement = "selectEntityCountBySelectiveKeyForAnd";
        return  super.getSqlSession().selectOne(statement,dict);
    }

    @Override
    public List<SysDictInfo> selectEntityListBySelectiveKeyForOr(SysDictInfo dict) {
        String statement = "selectEntityListBySelectiveKeyForOr";
        return super.getSqlSession().selectList(statement,dict);
    }

    @Override
    public Integer selectEntityCountBySelectiveKeyForOr(SysDictInfo dict) {
        String statement = "selectEntityCountBySelectiveKeyForOr";
        return  super.getSqlSession().selectOne(statement,dict);
    }
}
