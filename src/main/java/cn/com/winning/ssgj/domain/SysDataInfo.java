package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import cn.com.winning.ssgj.domain.expand.NodeTree;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * SSGJ
 *
 * @author SSGJ
 * @date 2018-01-30 11:27:25
 */
 
@Alias("sysDataInfo")
public class SysDataInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 库表编号
	 */
	private String tableCode;
	
	/**
	 * @val 数据库名称
	 */
	private String dbName;
	
	/**
	 * @val 数据库表名
	 */
	private String tableName;
	
	/**
	 * @val 库表中文名
	 */
	private String tableCnName;
	
	/**
	 * @val 标准文号
	 */
	private String standardCode;
	
	private String standardCnName;
	
	/**
	 * @val 数据类型 0 国标数据;1 行标数据；2 共享数据；3 易用数据；
	 */
	private Integer dataType;
	
	/**
	 * @val 是否易用数据 0 否；1 是；
	 */
	private Integer isEasy;
	
	private String tableAttention;
	
	private Integer tableCount;
	
	/**
	 * @val 状态 0 失效 1生效
	 */
	private Integer status;
	
	/**
	 * @val 维护人
	 */
	private Long lastUpdator;
	
	/**
	 * @val 维护时间
	 */
	private java.sql.Timestamp lastUpdateName;

	private NodeTree nodeTree = new NodeTree();
	
	public SysDataInfo() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 库表编号
	 */
	public String getTableCode() {
		return tableCode;
	}
	
	/**
	 * @val 库表编号
	 */
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	
	/**
	 * @val 数据库名称
	 */
	public String getDbName() {
		return dbName;
	}
	
	/**
	 * @val 数据库名称
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	/**
	 * @val 数据库表名
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * @val 数据库表名
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * @val 库表中文名
	 */
	public String getTableCnName() {
		return tableCnName;
	}
	
	/**
	 * @val 库表中文名
	 */
	public void setTableCnName(String tableCnName) {
		this.tableCnName = tableCnName;
	}
	
	/**
	 * @val 标准文号
	 */
	public String getStandardCode() {
		return standardCode;
	}
	
	/**
	 * @val 标准文号
	 */
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	
	public String getStandardCnName() {
		return standardCnName;
	}
	
	public void setStandardCnName(String standardCnName) {
		this.standardCnName = standardCnName;
	}
	
	/**
	 * @val 数据类型 0 国标数据;1 行标数据；2 共享数据；3 易用数据；
	 */
	public Integer getDataType() {
		return dataType;
	}
	
	/**
	 * @val 数据类型 0 国标数据;1 行标数据；2 共享数据；3 易用数据；
	 */
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	/**
	 * @val 是否易用数据 0 否；1 是；
	 */
	public Integer getIsEasy() {
		return isEasy;
	}
	
	/**
	 * @val 是否易用数据 0 否；1 是；
	 */
	public void setIsEasy(Integer isEasy) {
		this.isEasy = isEasy;
	}
	
	public String getTableAttention() {
		return tableAttention;
	}
	
	public void setTableAttention(String tableAttention) {
		this.tableAttention = tableAttention;
	}
	
	public Integer getTableCount() {
		return tableCount;
	}
	
	public void setTableCount(Integer tableCount) {
		this.tableCount = tableCount;
	}
	
	/**
	 * @val 状态 0 失效 1生效
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 状态 0 失效 1生效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 维护人
	 */
	public Long getLastUpdator() {
		return lastUpdator;
	}
	
	/**
	 * @val 维护人
	 */
	public void setLastUpdator(Long lastUpdator) {
		this.lastUpdator = lastUpdator;
	}
	
	/**
	 * @val 维护时间
	 */
	public Timestamp getLastUpdateName() {
		return lastUpdateName;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateName(Timestamp lastUpdateName) {
		this.lastUpdateName = lastUpdateName;
	}

	public NodeTree getNodeTree() {
		nodeTree.setId(id);
		nodeTree.setNodeId(id);
		nodeTree.setText(tableCnName+'['+tableName+']');
		return nodeTree;
	}

	public void setNodeTree(NodeTree nodeTree) {
		this.nodeTree = nodeTree;
	}
}