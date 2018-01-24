package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysModule;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysModuleService {

    Integer createSysModule(SysModule t);

    int modifySysModule(SysModule t);

    int removeSysModule(SysModule t);

    SysModule getSysModule(SysModule t);

    List<SysModule> getSysModuleList(SysModule t);

    Integer getSysModuleCount(SysModule t);

    List<SysModule> getSysModulePaginatedList(SysModule t);

}