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
 
@Alias("etFlowSurvey")
public class EtFlowSurvey extends BaseDomain implements Serializable {

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
	 * @val 流程名称
	 */
	private String serialName;
	
	/**
	 * @val 是否本期范围：0 否   1 是
	 */
	private Integer isScope;
	
	/**
	 * @val 不在实施范围原因
	 */
	private String noScopeCode;
	
	/**
	 * @val 意见
	 */
	private String message;
	
	/**
	 * @val 产品条线ID
	 */
	private Long plId;
	
	/**
	 * @val 状态： 0 待审核（新增）1：已审核
	 */
	private Integer status;
	
	/**
	 * @val 数据来源 0 PMIS;1 自定义
	 */
	private Integer sourceType;
	
	/**
	 * @val 上传问卷
	 */
	private String uploadPath;
	
	/**
	 * @val 下载问卷
	 */
	private String downloadPath;
	
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
	
	public EtFlowSurvey() {

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
	 * @val 流程名称
	 */
	public String getSerialName() {
		return serialName;
	}
	
	/**
	 * @val 流程名称
	 */
	public void setSerialName(String serialName) {
		this.serialName = serialName;
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
	 * @val 意见
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @val 意见
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @val 产品条线ID
	 */
	public Long getPlId() {
		return plId;
	}
	
	/**
	 * @val 产品条线ID
	 */
	public void setPlId(Long plId) {
		this.plId = plId;
	}
	
	/**
	 * @val 状态： 0 待审核（新增）1：已审核
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 状态： 0 待审核（新增）1：已审核
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @val 上传问卷
	 */
	public String getUploadPath() {
		return uploadPath;
	}
	
	/**
	 * @val 上传问卷
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	/**
	 * @val 下载问卷
	 */
	public String getDownloadPath() {
		return downloadPath;
	}
	
	/**
	 * @val 下载问卷
	 */
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
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