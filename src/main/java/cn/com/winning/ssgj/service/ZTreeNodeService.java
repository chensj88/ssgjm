package cn.com.winning.ssgj.service;

import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.domain.expand.ZTreeNode;

import java.util.List;

/**
 * @author chensj
 * @title
 * @email chensj@winning.com.cn
 * @package cn.com.winning.ssgj.service
 * @date: 2018-11-06 14:06
 */
public interface ZTreeNodeService {

    public List<ZTreeNode> createModuleParentTree();

    public List<ZTreeNode> createModuleChildTree(SysModule module);

    public List<ZTreeNode> createModuleTree();
}