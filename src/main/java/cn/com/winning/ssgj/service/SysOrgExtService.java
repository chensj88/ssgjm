package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysOrgExt;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-15 10:21:04
 */
public interface SysOrgExtService {

	Integer createSysOrgExt(SysOrgExt t);

	int modifySysOrgExt(SysOrgExt t);

	int removeSysOrgExt(SysOrgExt t);

	SysOrgExt getSysOrgExt(SysOrgExt t);

	List<SysOrgExt> getSysOrgExtList(SysOrgExt t);

	Integer getSysOrgExtCount(SysOrgExt t);

	List<SysOrgExt> getSysOrgExtPaginatedList(SysOrgExt t);

	public void callProcedure();

}