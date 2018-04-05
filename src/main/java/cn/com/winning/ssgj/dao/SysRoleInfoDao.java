package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysRoleInfoDao extends EntityDao<SysRoleInfo> {

    Integer selectRoleInfoMaxOrderValue();

    Integer selectSysRoleInfoCountForName(SysRoleInfo t);

    List<SysRoleInfo> selectSysRoleInfoPaginatedListForName(SysRoleInfo t);

    List<SysRoleInfo> selectSysRoleInfoListForName(SysRoleInfo roleInfo);
}
