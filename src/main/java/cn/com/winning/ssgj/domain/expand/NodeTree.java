package cn.com.winning.ssgj.domain.expand;

import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.domain.expand
 * @date 2018-02-24 21:51
 */
public class NodeTree {

    /**
     * ID属性
     */
     private Long nodeId;

     /**
      * 显示文本
      */
      private String text ;

      /**
       * 上级ID
       */
       private Long nodePid ;

       private List<NodeTree> nodes;


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
}
