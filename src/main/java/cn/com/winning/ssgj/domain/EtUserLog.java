package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-19 13:56:50
 */
 
@Alias("etUserLog")
public class EtUserLog extends BaseDomain implements Serializable {

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
	 * @val 1.系统名称 2.关键字 3.科室
	 */
	private Integer sourceType;
	
	/**
	 * @val 操作内容
	 */
	private String content;
	
	/**
	 * @val 操作人
	 */
	private Long operator;
	
	/**
	 * @val 操作时间
	 */
	private Date operatorTime;

	/**
	 * @val 流程状态
	 */
	private Integer processStatus;
	
	public EtUserLog() {

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
	 * @val 1.系统名称 2.关键字 3.科室
	 */
	public Integer getSourceType() {
		return sourceType;
	}
	
	/**
	 * @val 1.系统名称 2.关键字 3.科室
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	/**
	 * @val 操作内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 操作内容
	 */
	public void setContent(String content) {
		this.content = content;
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
	public Date getOperatorTime() {
		return operatorTime;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	/**
	 * @val 流程状态
	 * @return
	 */
	public Integer getProcessStatus() {
		return processStatus;
	}

	/**
	 * @val 流程状态
	 * @param processStatus
	 */
	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}
}