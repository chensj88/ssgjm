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
    public List<SysDataInfo> selectSysDataInfoPaginatedListByPmIdAndDataType(SysDataInfo t) {
        String statement = "selectSysDataInfoPaginatedListByPmIdAndDataType";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public Integer countSysDataInfoListByPmIdAndDataType(SysDataInfo t) {
        String statement = "countSysDataInfoListByPmIdAndDataType";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListByPmIdAndDataType(SysDataInfo t) {
        String statement = "selectSysDataInfoListByPmIdAndDataType";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListForORKey(SysDataInfo t) {
        return super.getSqlSession().selectList("selectSysDataInfoListForORKey",t);
    }
}
