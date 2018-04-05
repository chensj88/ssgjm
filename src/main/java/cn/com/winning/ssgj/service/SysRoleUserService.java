package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysRoleUser;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysRoleUserService {

    Integer createSysRoleUser(SysRoleUser t);

    int modifySysRoleUser(SysRoleUser t);

    int removeSysRoleUser(SysRoleUser t);

    SysRoleUser getSysRoleUser(SysRoleUser t);

    List<SysRoleUser> getSysRoleUserList(SysRoleUser t);

    Integer getSysRoleUserCount(SysRoleUser t);

    List<SysRoleUser> getSysRoleUserPaginatedList(SysRoleUser t);

    List<Long> getRoleIdList(SysRoleUser roleUser);

    void createSysRoleUserByIdString(String idStr);
}