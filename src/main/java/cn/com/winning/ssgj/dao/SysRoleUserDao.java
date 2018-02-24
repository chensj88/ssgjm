package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysRoleUser;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysRoleUserDao extends EntityDao<SysRoleUser> {

    List<SysRoleUser> selectSysRoleUserForIds(Map<String, Object> param);

    int deleteSysRoleUserForIds(Map<String, Object> param);
}
