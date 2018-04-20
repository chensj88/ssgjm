package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysUserVideoAuth;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-20 15:01:08
 */
public interface SysUserVideoAuthService {

	Integer createSysUserVideoAuth(SysUserVideoAuth t);

	int modifySysUserVideoAuth(SysUserVideoAuth t);

	int removeSysUserVideoAuth(SysUserVideoAuth t);

	SysUserVideoAuth getSysUserVideoAuth(SysUserVideoAuth t);

	List<SysUserVideoAuth> getSysUserVideoAuthList(SysUserVideoAuth t);

	Integer getSysUserVideoAuthCount(SysUserVideoAuth t);

	List<SysUserVideoAuth> getSysUserVideoAuthPaginatedList(SysUserVideoAuth t);

	/**
	 * 根据上传信息生成数据
	 * @param userAuth 上传数据
	 * @param auth 前端传输数据
	 */
    void generateUserVideoAuthInfo(List<List<Object>> userAuth, SysUserVideoAuth auth);
}