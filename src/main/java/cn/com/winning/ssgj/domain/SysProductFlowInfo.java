package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:03
 */
 
@Alias("sysProductFlowInfo")
public class SysProductFlowInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val 产品/系统ID
	 */
	private Long pdId;
	
	/**
	 * @val 流程ID
	 */
	private Long flowId;
	
	/**
	 * @val 生效日期
	 */
	private Date effectiveDate;
	
	/**
	 * @val 失效日期
	 */
	private Date expireDate;
	
	/**
	 * @val 维护人员ID
	 */
	private Long lastUpdator;

	/**
	 * @val 维护人员
	 */
	private String lastUpdate;
	/**
	 * @val 产品编码
	 */
	private String pdCode;
	/**
	 * @val 产品名称
	 */
	private String pdName;
	/**
	 * @val 流程编号
	 */
	private String flowCode;
	/**
	 * @val 流程名称
	 */
	private String flowName;
	/**
	 * @val 流程描述
	 */
	private String flowDesc;
	
	/**
	 * @val 维护时间
	 */
	private java.sql.Timestamp lastUpdateTime;
	
	public SysProductFlowInfo() {

	}

	/**
	 * @val 产品/系统ID
	 */
	public Long getPdId() {
		return pdId;
	}
	
	/**
	 * @val 产品/系统ID
	 */
	public void setPdId(Long pdId) {
		this.pdId = pdId;
	}
	
	/**
	 * @val 流程ID
	 */
	public Long getFlowId() {
		return flowId;
	}
	
	/**
	 * @val 流程ID
	 */
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	
	/**
	 * @val 生效日期
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	
	/**
	 * @val 生效日期
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * @val 失效日期
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	
	/**
	 * @val 失效日期
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	/**
	 * @val 维护人员
	 */
	public Long getLastUpdator() {
		return lastUpdator;
	}
	
	/**
	 * @val 维护人员
	 */
	public void setLastUpdator(Long lastUpdator) {
		this.lastUpdator = lastUpdator;
	}
	
	/**
	 * @val 维护时间
	 */
	public java.sql.Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(java.sql.Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowDesc() {
		return flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}
}