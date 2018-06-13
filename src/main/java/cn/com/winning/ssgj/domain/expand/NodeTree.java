package cn.com.winning.ssgj.domain.expand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshijie
 * @title 前台显示树结构
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.domain.expand
 * @date 2018-02-24 21:51
 * @be_careful 使用该组件时候nodeId为前端组件自动生成的Id，可以不设置，但是Id字段必须设置
 */
public class NodeTree {

    /**
     * 节点ID
     */
     private Long nodeId;
    /**
     * ID值
     */
    private Long id;

     /**
      * 显示文本
      */
      private String text ;

      /**
       * 上级ID
       */
       private Long nodePid ;
    /**
     * 节点图标
     */
    private String nodeIcon;
    /**
     * 访问路径
     */
    private String urlPath;
    /**
     * 功能信息
     */
    private String funInfo;
    /**
     * 节点层级
     */
    private int level;

    private List<NodeTree> nodes = new ArrayList<>();


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNodePid() {
        return nodePid;
    }

    public void setNodePid(Long nodePid) {
        this.nodePid = nodePid;
    }

    public List<NodeTree> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeTree> nodes) {
        this.nodes = nodes;
    }

    public String getNodeIcon() {
        return nodeIcon;
    }

    public void setNodeIcon(String nodeIcon) {
        this.nodeIcon = nodeIcon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }


    public String getFunInfo() {
        return funInfo;
    }

    public void setFunInfo(String funInfo) {
        this.funInfo = funInfo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addNode(NodeTree tree){
        this.nodes.add(tree);
    }
}
