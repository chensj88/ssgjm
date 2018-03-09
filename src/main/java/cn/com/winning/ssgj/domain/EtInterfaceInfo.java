package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:33
 */
 
@Alias("etInterfaceInfo")
public class EtInterfaceInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	/**
	 * @val 项目ID
	 */
	private Long pmId;
	
	/**
	 * @val 合同ID
	 */
	private Long cId;
	
	/**
	 * @val 业务单据编码
	 */
	private String serialNo;
	
	/**
	 * @val 接口名称
	 */
	private String interName;
	
	/**
	 * @val 模块名称
	 */
	private String moduleName;
	
	/**
	 * @val 模块明细
	 */
	private String moduleDetail;
	
	/**
	 * @val 备注信息
	 */
	private String remark;
	
	/**
	 * @val 数据来源 0 PMIS;1 自定义
	 */
	private Integer sourceType;
	
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
	
	public EtInterfaceInfo() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	 * @val 业务单据编码
	 */
	public String getSerialNo() {
		return serialNo;
	}
	
	/**
	 * @val 业务单据编码
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * @val 接口名称
	 */
	public String getInterName() {
		return interName;
	}
	
	/**
	 * @val 接口名称
	 */
	public void setInterName(String interName) {
		this.interName = interName;
	}
	
	/**
	 * @val 模块名称
	 */
	public String getModuleName() {
		return moduleName;
	}
	
	/**
	 * @val 模块名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	/**
	 * @val 模块明细
	 */
	public String getModuleDetail() {
		return moduleDetail;
	}
	
	/**
	 * @val 模块明细
	 */
	public void setModuleDetail(String moduleDetail) {
		this.moduleDetail = moduleDetail;
	}
	
	/**
	 * @val 备注信息
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 数据来源 0 PMIS;1 自定义
	 */
	public Integer getSourceType() {
		return sourceType;
	}
	
	/**
	 * @val 数据来源 0 PMIS;1 自定义
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
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
	
}