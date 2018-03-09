package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysDataCheckScript;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-09 16:50:10
 */
public interface SysDataCheckScriptService {

	Integer createSysDataCheckScript(SysDataCheckScript t);

	int modifySysDataCheckScript(SysDataCheckScript t);

	int removeSysDataCheckScript(SysDataCheckScript t);

	SysDataCheckScript getSysDataCheckScript(SysDataCheckScript t);

	List<SysDataCheckScript> getSysDataCheckScriptList(SysDataCheckScript t);

	Integer getSysDataCheckScriptCount(SysDataCheckScript t);

	List<SysDataCheckScript> getSysDataCheckScriptPaginatedList(SysDataCheckScript t);

}