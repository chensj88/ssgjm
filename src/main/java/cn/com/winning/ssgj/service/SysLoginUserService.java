package cn.com.winning.ssgj.service;

import cn.com.winning.ssgj.domain.SysLoginUser;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-26 16:19:08
 */
public interface SysLoginUserService {

	Integer createSysLoginUser(SysLoginUser t);

	int modifySysLoginUser(SysLoginUser t);

	int removeSysLoginUser(SysLoginUser t);

	SysLoginUser getSysLoginUser(SysLoginUser t);

	List<SysLoginUser> getSysLoginUserList(SysLoginUser t);

	Integer getSysLoginUserCount(SysLoginUser t);

	List<SysLoginUser> getSysLoginUserPaginatedList(SysLoginUser t);

	Integer createSysLoginUserBySelectiveKey(String token,Long userId);

	SysLoginUser getSysLoginUserBySelectiveKey(String token);
}