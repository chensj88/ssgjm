package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDataInfoDao;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysDataInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysDataInfo> implements SysDataInfoDao {

    @Override
    public Integer selectSysDataInfoCountByselective(SysDataInfo t) {
        String statement = "query" + t.getClass().getSimpleName() + "CountForSelectiveKey";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoPaginatedListByselective(SysDataInfo t) {
        String statement = "query" + t.getClass().getSimpleName() + "PaginatedListForSelectiveKey";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListForSelectiveKey(SysDataInfo t) {
        String statement = "query" + t.getClass().getSimpleName() + "ListForSelectiveKey";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListByIds(SysDataInfo t) {
        String statement = "query"+ t.getClass().getSimpleName() +"ListByIds";
        return super.getSqlSession().selectList(statement,t);
    }
}
