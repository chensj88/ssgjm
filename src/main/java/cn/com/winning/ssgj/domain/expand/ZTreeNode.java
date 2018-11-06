package cn.com.winning.ssgj.domain.expand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensj
 * @title
 * @email chensj@winning.com.cn
 * @package cn.com.winning.ssgj.domain.expand
 * @date: 2018-11-06 14:01
 */
public class ZTreeNode {

    private String nodeId;
    private String nodePid;
    private String nodeName;
    private String nodeDesc;
    private Integer nodeLevel;
    private boolean parentFlag;
    private boolean hiddenFlag;
    private boolean checkedFlag;
    private boolean chkDisabled;
    private String icon;
    private String iconClose;
    private String iconOpen;
    private List<ZTreeNode> children = new ArrayList<>();

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodePid() {
        return nodePid;
    }

    public void setNodePid(String nodePid) {
        this.nodePid = nodePid;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public boolean isParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(boolean parentFlag) {
        this.parentFlag = parentFlag;
    }

    public boolean isHiddenFlag() {
        return hiddenFlag;
    }

    public void setHiddenFlag(boolean hiddenFlag) {
        this.hiddenFlag = hiddenFlag;
    }

    public boolean isCheckedFlag() {
        return checkedFlag;
    }

    public void setCheckedFlag(boolean checkedFlag) {
        this.checkedFlag = checkedFlag;
    }

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public List<ZTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ZTreeNode> children) {
        this.children = children;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }
}
