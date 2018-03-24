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
    public Integer selectSysDataInfoCountForSelectiveKey(SysDataInfo t) {
        String statement = "selectSysDataInfoCountForSelectiveKey";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoPaginatedListForSelectiveKey(SysDataInfo t) {
        String statement = "selectSysDataInfoPaginatedListForSelectiveKey";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListForSelectiveKey(SysDataInfo t) {
        String statement = "select" + t.getClass().getSimpleName() + "ListForSelectiveKey";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListByIds(SysDataInfo t) {
        String statement = "select"+ t.getClass().getSimpleName() +"ListByIds";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoPaginatedListByIds(SysDataInfo t) {
        String statement = "select"+ t.getClass().getSimpleName() +"PaginatedListByIds";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public Integer selectSysDataInfoCountByIds(SysDataInfo t) {
        String statement = "select"+ t.getClass().getSimpleName() +"CountByIds";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoPaginatedListByPidAndDataType(SysDataInfo t) {
        String statement = "select"+ t.getClass().getSimpleName() +"PaginatedListByPidAndDataType";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public Integer countSysDataInfoPaginatedListByPidAndDataType(SysDataInfo t) {
        String statement = "count"+ t.getClass().getSimpleName() +"PaginatedListByPidAndDataType";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListByPidAndDataType(SysDataInfo t) {
        String statement = "select"+ t.getClass().getSimpleName() +"ListByPidAndDataType";
        return super.getSqlSession().selectList(statement,t);
    }
}
