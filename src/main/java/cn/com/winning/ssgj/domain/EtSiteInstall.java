package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:35
 */
 
@Alias("etSiteInstall")
public class EtSiteInstall extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 合同ID
	 */
	private Long cId;
	
	/**
	 * @val 项目ID
	 */
	private Long pmId;
	
	/**
	 * @val 单据号
	 */
	private String serialNo;
	
	/**
	 * @val 科室名称
	 */
	private String deptName;
	
	/**
	 * @val 所需软件(多个产品的ID)
	 */
	private String pdId;
	
	/**
	 * @val 硬件(多ID 用英文逗号 , 分割)
	 */
	private String hwId;
	
	/**
	 * @val 是否本期范围：0 否   1 是
	 */
	private Integer isScope;
	
	/**
	 * @val 不在实施范围原因
	 */
	private String noScopeCode;
	
	/**
	 * @val 成员信息
	 */
	private Long puserId;
	
	/**
	 * @val 站点数
	 */
	private Integer num;
	
	/**
	 * @val 状态：0：未安装 1：安装中 2：已安装
	 */
	private Integer status;
	
	/**
	 * @val 创建人
	 */
	private Long creator;
	
	/**
	 * @val 创建时间
	 */
	private java.sql.Timestamp createTime;
	
	/**
	 * @val 操作人
	 */
	private Long operator;
	
	/**
	 * @val 操作时间
	 */
	private java.sql.Timestamp operatorTime;
	
	/**
	 * @val 备注
	 */
	private String remark;

	/**
	 * @val 部门编码
	 */
	private String deptCode;

	/**
	 * @val 使用系统（合同主系统）
	 */
	private String cpzxt;
	
	public EtSiteInstall() {

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
	 * @val 合同ID
	 */
	public Long getCId() {
		return cId;
	}
	
	/**
	 * @val 合同ID
	 */
	public void setCId(Long cId) {
		this.cId = cId;
	}
	
	/**
	 * @val 项目ID
	 */
	public Long getPmId() {
		return pmId;
	}
	
	/**
	 * @val 项目ID
	 */
	public void setPmId(Long pmId) {
		this.pmId = pmId;
	}
	
	/**
	 * @val 单据号
	 */
	public String getSerialNo() {
		return serialNo;
	}
	
	/**
	 * @val 单据号
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * @val 科室名称
	 */
	public String getDeptName() {
		return deptName;
	}
	
	/**
	 * @val 科室名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	/**
	 * @val 所需软件(多个产品的ID)
	 */
	public String getPdId() {
		return pdId;
	}
	
	/**
	 * @val 所需软件(多个产品的ID)
	 */
	public void setPdId(String pdId) {
		this.pdId = pdId;
	}
	
	/**
	 * @val 硬件(多ID 用英文逗号 , 分割)
	 */
	public String getHwId() {
		return hwId;
	}
	
	/**
	 * @val 硬件(多ID 用英文逗号 , 分割)
	 */
	public void setHwId(String hwId) {
		this.hwId = hwId;
	}
	
	/**
	 * @val 是否本期范围：0 否   1 是
	 */
	public Integer getIsScope() {
		return isScope;
	}
	
	/**
	 * @val 是否本期范围：0 否   1 是
	 */
	public void setIsScope(Integer isScope) {
		this.isScope = isScope;
	}
	
	/**
	 * @val 不在实施范围原因
	 */
	public String getNoScopeCode() {
		return noScopeCode;
	}
	
	/**
	 * @val 不在实施范围原因
	 */
	public void setNoScopeCode(String noScopeCode) {
		this.noScopeCode = noScopeCode;
	}
	
	/**
	 * @val 成员信息
	 */
	public Long getPuserId() {
		return puserId;
	}
	
	/**
	 * @val 成员信息
	 */
	public void setPuserId(Long puserId) {
		this.puserId = puserId;
	}
	
	/**
	 * @val 站点数
	 */
	public Integer getNum() {
		return num;
	}
	
	/**
	 * @val 站点数
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	
	/**
	 * @val 状态：0：未安装 1：安装中 2：已安装
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 状态：0：未安装 1：安装中 2：已安装
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 创建人
	 */
	public Long getCreator() {
		return creator;
	}
	
	/**
	 * @val 创建人
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	/**
	 * @val 创建时间
	 */
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}
	
	/**
	 * @val 创建时间
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @val 操作人
	 */
	public Long getOperator() {
		return operator;
	}
	
	/**
	 * @val 操作人
	 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	
	/**
	 * @val 操作时间
	 */
	public java.sql.Timestamp getOperatorTime() {
		return operatorTime;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOperatorTime(java.sql.Timestamp operatorTime) {
		this.operatorTime = operatorTime;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getCpzxt() {
		return cpzxt;
	}

	public void setCpzxt(String cpzxt) {
		this.cpzxt = cpzxt;
	}
	
}