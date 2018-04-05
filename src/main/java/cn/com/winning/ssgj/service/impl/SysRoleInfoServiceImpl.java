package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;

import cn.com.winning.ssgj.domain.expand.NodeTree;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysRoleInfoDao;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.service.SysRoleInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysRoleInfoServiceImpl implements SysRoleInfoService {

    @Resource
    private SysRoleInfoDao sysRoleInfoDao;



    public Integer createSysRoleInfo(SysRoleInfo t) {
        int maxOrderValue = sysRoleInfoDao.selectRoleInfoMaxOrderValue();
        t.setOrderValue(maxOrderValue + 1);
        return this.sysRoleInfoDao.insertEntity(t);
    }


    public SysRoleInfo getSysRoleInfo(SysRoleInfo t) {
        return this.sysRoleInfoDao.selectEntity(t);
    }


    public Integer getSysRoleInfoCount(SysRoleInfo t) {
        return (Integer) this.sysRoleInfoDao.selectEntityCount(t);
    }


    public List<SysRoleInfo> getSysRoleInfoList(SysRoleInfo t) {
        return this.sysRoleInfoDao.selectEntityList(t);
    }

    @Override

    public List<NodeTree> getRoleInfoTree(String roleName) {
        SysRoleInfo roleInfo = new SysRoleInfo();
        roleInfo.setRoleName(roleName);
        roleInfo.setIsDel(Constants.STATUS_UNUSE);
        List<SysRoleInfo> roleInfos = this.sysRoleInfoDao.selectSysRoleInfoListForName(roleInfo);
        List<NodeTree> roleTree = new ArrayList<NodeTree>();
        for (SysRoleInfo info : roleInfos) {
            NodeTree tree = new NodeTree();
            tree.setNodeId(info.getId());
            tree.setId(info.getId());
            tree.setText(info.getRoleName());
            roleTree.add(tree);
        }
        return roleTree;
    }


    public int modifySysRoleInfo(SysRoleInfo t) {
        return this.sysRoleInfoDao.updateEntity(t);
    }


    public int removeSysRoleInfo(SysRoleInfo t) {
        return this.sysRoleInfoDao.deleteEntity(t);
    }


    public List<SysRoleInfo> getSysRoleInfoPaginatedList(SysRoleInfo t) {
        return this.sysRoleInfoDao.selectEntityPaginatedList(t);
    }

    @Override

    public Integer getSysRoleInfoCountForName(SysRoleInfo t) {
        return this.sysRoleInfoDao.selectSysRoleInfoCountForName(t);
    }

    @Override

    public List<SysRoleInfo> getSysRoleInfoPaginatedListForName(SysRoleInfo t) {
        return this.sysRoleInfoDao.selectSysRoleInfoPaginatedListForName(t);
    }

}
