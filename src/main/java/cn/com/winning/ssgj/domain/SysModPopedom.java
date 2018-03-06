package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import cn.com.winning.ssgj.domain.expand.NodeTree;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:46
 */

@Alias("sysModPopedom")
public class SysModPopedom extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long modId;

    private Long userId;

    private Long roleId;

    private String popedomCode;

    private Long parId;

    private String modName;

    private Integer modLevel;

    private NodeTree nodeTree = new NodeTree();

    public SysModPopedom() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModId() {
        return modId;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPopedomCode() {
        return popedomCode;
    }

    public void setPopedomCode(String popedomCode) {
        this.popedomCode = popedomCode;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public Integer getModLevel() {
        return modLevel;
    }

    public void setModLevel(Integer modLevel) {
        this.modLevel = modLevel;
    }


    public NodeTree getNodeTree() {
        nodeTree.setId(id);
        nodeTree.setNodeId(id);
        nodeTree.setText(modName);
        nodeTree.setLevel(modLevel);
        nodeTree.setNodePid(parId);
        return nodeTree;
    }

    public void setNodeTree(NodeTree nodeTree) {
        this.nodeTree = nodeTree;
    }
}