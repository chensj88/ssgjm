package cn.com.winning.ssgj.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ComboTree implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String text;// 树节点名称
	private String iconCls;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private Map<String, Object> attributes = new HashMap<String, Object>();// 其他参数
	private List<ComboTree> children;// 子节点
	private String state = "open";// 是否展开(open,closed)
	private Integer is_stope;// 是否停用
	private Integer is_del;// 是否删除
	private Integer level_count;// 层级
	private Integer is_leaf;// 是否叶子节点

	public Integer getIs_stope() {
		return is_stope;
	}

	public void setIs_stope(Integer is_stope) {
		this.is_stope = is_stope;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<ComboTree> getChildren() {
		return children;
	}

	public void setChildren(List<ComboTree> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getLevel_count() {
		return level_count;
	}

	public void setLevel_count(Integer level_count) {
		this.level_count = level_count;
	}

	public Integer getIs_leaf() {
		return is_leaf;
	}

	public void setIs_leaf(Integer is_leaf) {
		this.is_leaf = is_leaf;
	}
}
