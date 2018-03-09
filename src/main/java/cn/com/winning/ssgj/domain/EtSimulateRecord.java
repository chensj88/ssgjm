package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:34
 */
 
@Alias("etSimulateRecord")
public class EtSimulateRecord extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

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
	 * @val 模拟范围
	 */
	private String simulateCode;
	
	/**
	 * @val 模拟效果: 0：很不好  1：不好 2：良好3：达到上线要求
	 */
	private String simulateResult;
	
	/**
	 * @val 是否达到上线要求: 0：否 1：是
	 */
	private Integer isOnline;
	
	/**
	 * @val 主要问题
	 */
	private String question;
	
	/**
	 * @val 备注
	 */
	private String remark;
	
	/**
	 * @val 文件路径
	 */
	private String filePath;
	
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
	
	public EtSimulateRecord() {

	}

	public Long getId() {
		return id;
	}
	
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
	 * @val 模拟范围
	 */
	public String getSimulateCode() {
		return simulateCode;
	}
	
	/**
	 * @val 模拟范围
	 */
	public void setSimulateCode(String simulateCode) {
		this.simulateCode = simulateCode;
	}
	
	/**
	 * @val 模拟效果: 0：很不好  1：不好 2：良好3：达到上线要求
	 */
	public String getSimulateResult() {
		return simulateResult;
	}
	
	/**
	 * @val 模拟效果: 0：很不好  1：不好 2：良好3：达到上线要求
	 */
	public void setSimulateResult(String simulateResult) {
		this.simulateResult = simulateResult;
	}
	
	/**
	 * @val 是否达到上线要求: 0：否 1：是
	 */
	public Integer getIsOnline() {
		return isOnline;
	}
	
	/**
	 * @val 是否达到上线要求: 0：否 1：是
	 */
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	
	/**
	 * @val 主要问题
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * @val 主要问题
	 */
	public void setQuestion(String question) {
		this.question = question;
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
	
	/**
	 * @val 文件路径
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * @val 文件路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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