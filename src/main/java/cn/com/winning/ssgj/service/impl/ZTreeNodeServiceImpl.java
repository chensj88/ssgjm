package cn.com.winning.ssgj.service.impl;

import cn.com.winning.ssgj.dao.SysModuleDao;
import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.domain.expand.ZTreeNode;
import cn.com.winning.ssgj.service.ZTreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chensj
 * @title
 * @email chensj@winning.com.cn
 * @package cn.com.winning.ssgj.service.impl
 * @date: 2018-11-06 14:07
 */
@Service
public class ZTreeNodeServiceImpl implements ZTreeNodeService {

    @Autowired
    private SysModuleDao sysModuleDao;


    @Override
    public List<ZTreeNode> createModuleParentTree() {
        return sysModuleDao.selectSysModuleParentTree();
    }

    @Override
    public List<ZTreeNode> createModuleChildTree(SysModule module) {
        return sysModuleDao.selectSysModuleChildTree(module);
    }

    @Override
    public List<ZTreeNode> createModuleTree() {
        return sysModuleDao.selectSysModuleTree();
    }
}
