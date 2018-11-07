package cn.com.winning.ssgj.service.impl;

import java.util.*;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;

import cn.com.winning.ssgj.dao.SysFunDao;
import cn.com.winning.ssgj.domain.SysFun;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModuleDao;
import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.service.SysModuleService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

    @Resource
    private SysModuleDao sysModuleDao;
    @Autowired
    private SysFunDao sysFunDao;



    public Integer createSysModule(SysModule t) {
        int maxOrderValue = this.sysModuleDao.selectSysModuleMaxOrderValue();
        t.setOrderValue(maxOrderValue + 1);
        return this.sysModuleDao.insertEntity(t);
    }


    public SysModule getSysModule(SysModule t) {
        return this.sysModuleDao.selectEntity(t);
    }


    public Integer getSysModuleCount(SysModule t) {
        return (Integer) this.sysModuleDao.selectEntityCount(t);
    }


    public List<SysModule> getSysModuleList(SysModule t) {
        return this.sysModuleDao.selectEntityList(t);
    }


    public int modifySysModule(SysModule t) {
        return this.sysModuleDao.updateEntity(t);
    }


    public int removeSysModule(SysModule t) {
        return this.sysModuleDao.deleteEntity(t);
    }


    public List<SysModule> getSysModulePaginatedList(SysModule t) {
        return this.sysModuleDao.selectEntityPaginatedList(t);
    }

    @Override

    public List<SysModule> getSysModulePaginatedListFuzzy(SysModule module) {
        return this.sysModuleDao.selectSysModulePaginatedListFuzzy(module);
    }

    @Override

    public int getSysModuleCountFuzzy(SysModule module) {
        return this.sysModuleDao.selectSysModuleCountFuzzy(module);
    }

    @Override

    public List<NodeTree> getSysModuleNodeTree(SysModule module) {
        module.setIsDel(Constants.STATUS_UNUSE);
        module.setModLevel(1);
        List<SysModule> moduleList = this.sysModuleDao.selectSysModuleDaoListForName(module);
        List<NodeTree> moduleTree = new ArrayList<NodeTree>();
        for (SysModule sysModule : moduleList) {
            NodeTree node = new NodeTree();
            node.setId(sysModule.getModId());
            node.setNodeId(sysModule.getModId());
            node.setText(sysModule.getModName());
            node.setNodes(getChildNode(sysModule.getModId()));
            moduleTree.add(node);
        }
        return moduleTree;
    }

    private List<NodeTree> getChildNode(Long modId) {
        SysModule module = new SysModule();
        module.setParId(modId);
        module.setIsDel(0);
        List<NodeTree> moduleTree = new ArrayList<NodeTree>();
        List<SysModule> moduleList = this.sysModuleDao.selectEntityList(module);
        for (SysModule sysModule : moduleList) {
            moduleTree.add(sysModule.getNodeTree());
        }
        return moduleTree;
    }

    @Override

    public List<NodeTree> getUserMenu(SysUserInfo sysUserInfo) {
        List<SysModule> moduleList = this.sysModuleDao.selectUserParentMenuList(sysUserInfo);
        List<NodeTree> userMenu = new ArrayList<NodeTree>();
        for (SysModule pModule : moduleList) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("userId", sysUserInfo.getId());
            param.put("parId", pModule.getModId());
            List<SysModule> childModule = this.sysModuleDao.selectUserChildMenuList(param);
            NodeTree nodeTree = pModule.getNodeTree();
            nodeTree.setNodes(getChildNodeListWithoutFun(childModule));
            userMenu.add(nodeTree);
        }
        return userMenu;
    }

    @Override

    public List<NodeTree> getRoleMenu(SysRoleInfo roleInfo) {
        List<NodeTree> roleMenu = new ArrayList<NodeTree>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleId", roleInfo.getId());
        List<SysModule> childModule = this.sysModuleDao.selectRoleChildMenuList(param);
        roleMenu = getChildNodeList(childModule);
        return roleMenu;
    }

    /**
     * 根据菜单URL获取下级按钮信息，主要获取className信息
     * @param module
     * @return
     */
    @Override
    public Set<String> getBtnModuleListByModuleURL(SysModule module) {
        List<String> allBtnList = sysModuleDao.selectBtnModuleListByModuleURL(module);
        return null;
    }

    private List<NodeTree> getChildNodeListWithoutFun(List<SysModule> moduleList) {
        List<NodeTree> nodes = new ArrayList<NodeTree>();
        for (SysModule sysModule : moduleList) {
            nodes.add(sysModule.getNodeTree());
        }
        return nodes;
    }

    private List<NodeTree> getChildNodeList(List<SysModule> moduleList) {
        List<NodeTree> nodes = new ArrayList<NodeTree>();
        for (SysModule sysModule : moduleList) {
            String funcInfo = getNodeTreeFunInfoString(sysModule);
            NodeTree node = sysModule.getNodeTree();
            node.setText(sysModule.getModPath());
            node.setFunInfo(funcInfo);
            node.setId(sysModule.getPopedomId());
            nodes.add(node);
        }
        return nodes;
    }


    private String getNodeTreeFunInfoString(SysModule module) {
        StringBuilder funcInfoSB = new StringBuilder();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("modId", module.getModId());
        List<SysFun> funList = this.sysFunDao.selectSysFunByModuleInfo(param);
        for (SysFun sysFun : funList) {
            funcInfoSB.append(sysFun.getFunCode() + ":" + sysFun.getFunName() + ";");
        }
        System.out.println(funcInfoSB);
        return funcInfoSB.toString();
    }
}
