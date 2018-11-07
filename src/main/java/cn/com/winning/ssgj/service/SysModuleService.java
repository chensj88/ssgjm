package cn.com.winning.ssgj.service;

import java.util.List;
import java.util.Set;

import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;

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

    List<SysModule> getSysModulePaginatedListFuzzy(SysModule module);

    int getSysModuleCountFuzzy(SysModule module);

    List<NodeTree> getSysModuleNodeTree(SysModule module);

    List<NodeTree> getUserMenu(SysUserInfo sysUserInfo);

    List<NodeTree> getRoleMenu(SysRoleInfo roleInfo);

    Set<String> getBtnModuleListByModuleURL(SysModule module);
}