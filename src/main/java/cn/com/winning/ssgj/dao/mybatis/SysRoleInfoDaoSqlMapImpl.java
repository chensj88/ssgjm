package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysRoleInfoDao;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysRoleInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysRoleInfo> implements SysRoleInfoDao {

    @Override
    public Integer selectRoleInfoMaxOrderValue() {
        String statement = "selectRoleInfoMaxOrderValue";
        return super.getSqlSession().selectOne(statement);
    }

    @Override
    public Integer selectSysRoleInfoCountForName(SysRoleInfo t) {
        String statement = "selectSysRoleInfoCountForName";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysRoleInfo> selectSysRoleInfoPaginatedListForName(SysRoleInfo t) {
        String statement = "selectSysRoleInfoPaginatedListForName";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysRoleInfo> selectSysRoleInfoListForName(SysRoleInfo roleInfo) {
        String statement = "selectSysRoleInfoListForName";
        return super.getSqlSession().selectList(statement,roleInfo);
    }
}
