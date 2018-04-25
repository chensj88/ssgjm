package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductInterfaceInfoDao;
import cn.com.winning.ssgj.domain.SysProductInterfaceInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:03
 */
@Service
public class SysProductInterfaceInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysProductInterfaceInfo> implements SysProductInterfaceInfoDao {

    @Override
    public List<SysProductInterfaceInfo> selectSysProductInterfaceInfoByIds(Map<String, Object> param) {
        String statement = "selectSysProductInterfaceInfoByIds";
        return super.getSqlSession().selectList(statement,param);
    }

    @Override
    public int removeSysProductInterInfoByIds(Map<String, Object> param) {
        String statement = "removeSysProductInterInfoByIds";
        return super.getSqlSession().delete(statement,param);
    }

    @Override
    public List<SysProductInterfaceInfo> selectSysProductInterfaceInfoForIds(Map<String, Object> param) {
        String statement = "selectSysProductInterfaceInfoForIds";
        return super.getSqlSession().selectList(statement,param);
    }
}
