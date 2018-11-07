package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.dao.EntityDao;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.ZTreeNode;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysModuleDao extends EntityDao<SysModule> {

    List<SysModule> selectSysModulePaginatedListFuzzy(SysModule module);

    int selectSysModuleCountFuzzy(SysModule module);

    int selectSysModuleMaxOrderValue();

    List<SysModule> selectSysModuleDaoListForName(SysModule module);

    List<SysModule> selectUserParentMenuList(SysUserInfo sysUserInfo);

    List<SysModule> selectUserChildMenuList(Map<String,Object> param);

    List<SysModule> selectRoleParentMenuList(SysRoleInfo sysRoleInfo);

    List<SysModule> selectRoleChildMenuList(Map<String,Object> param);

    List<ZTreeNode> selectSysModuleParentTree();

    List<ZTreeNode> selectSysModuleChildTree(SysModule module);

    List<ZTreeNode> selectSysModuleTree();

    List<String> selectBtnModuleListByModuleURL(SysModule module);
}
