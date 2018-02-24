package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.domain.SysProductDataInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysRoleUserDao;
import cn.com.winning.ssgj.domain.SysRoleUser;
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
public class SysRoleUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysRoleUser> implements SysRoleUserDao {

    @Override
    public List<SysRoleUser> selectSysRoleUserForIds(Map<String, Object> param) {
        String statement = "selectSysRoleUserForIds";
        return super.getSqlSession().selectList(statement,param);
    }

    @Override
    public int deleteSysRoleUserForIds(Map<String, Object> param) {
        String statement = "deleteSysRoleUserForIds";
        return super.getSqlSession().update(statement,param);
    }
}
