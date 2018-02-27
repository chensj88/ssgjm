package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModuleDao;
import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.service.SysModuleService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

    @Resource
    private SysModuleDao sysModuleDao;


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
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("userId",sysUserInfo.getId());
            param.put("parId",pModule.getModId());
            List<SysModule> childModule = this.sysModuleDao.selectUserChildMenuList(param);
            NodeTree nodeTree = pModule.getNodeTree();
            nodeTree.setNodes(getChildNodeList(childModule));
            userMenu.add(nodeTree);
        }
        return userMenu;
    }

    private List<NodeTree> getChildNodeList(List<SysModule> moduleList) {
        List<NodeTree> nodes = new ArrayList<NodeTree>();
        for (SysModule sysModule : moduleList) {
            nodes.add(sysModule.getNodeTree());
        }
        return nodes;
    }

}
