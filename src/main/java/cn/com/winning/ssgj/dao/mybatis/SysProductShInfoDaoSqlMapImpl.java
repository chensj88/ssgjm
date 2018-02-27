package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductShInfoDao;
import cn.com.winning.ssgj.domain.SysProductShInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:42:37
 */
@Service
public class SysProductShInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysProductShInfo> implements SysProductShInfoDao {

    @Override
    public List<SysProductShInfo> selectSysProductShInfoByIds(Map<String, Object> param) {
        String statement = "selectSysProductShInfoByIds";
        return super.getSqlSession().selectList(statement,param);
    }

    @Override
    public int removeSysProductShInfoMapping(Map<String, Object> param) {
        String statement = "removeSysProductShInfoMapping";
        return super.getSqlSession().update(statement,param);
    }

    @Override
    public List<SysProductShInfo> selectSysProductInterfaceInfoByIdMap(Map<String, Object> param) {
        String statement = "selectSysProductInterfaceInfoByIdMap";
        return super.getSqlSession().selectList(statement,param);
    }
}
