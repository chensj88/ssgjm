package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-09 14:58:09
 */
 
@Alias("etLog")
public class EtLog extends BaseDomain implements Serializable {

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
	
	private Long sourceId;
	
	private String sourceType;
	
	private Integer status;
	
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
	 * @val 机器MAC
	 */
	private String clientMac;
	
	/**
	 * @val 机器IP
	 */
	private String clientIp;
	
	/**
	 * @val 机器名称
	 */
	private String clientName;
	
	private String clo1;
	
	private String clo2;
	
	private String clo3;
	
	public EtLog() {

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
	
	public Long getSourceId() {
		return sourceId;
	}
	
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	
	public String getSourceType() {
		return sourceType;
	}
	
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @val 机器MAC
	 */
	public String getClientMac() {
		return clientMac;
	}
	
	/**
	 * @val 机器MAC
	 */
	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}
	
	/**
	 * @val 机器IP
	 */
	public String getClientIp() {
		return clientIp;
	}
	
	/**
	 * @val 机器IP
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	/**
	 * @val 机器名称
	 */
	public String getClientName() {
		return clientName;
	}
	
	/**
	 * @val 机器名称
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getClo1() {
		return clo1;
	}
	
	public void setClo1(String clo1) {
		this.clo1 = clo1;
	}
	
	public String getClo2() {
		return clo2;
	}
	
	public void setClo2(String clo2) {
		this.clo2 = clo2;
	}
	
	public String getClo3() {
		return clo3;
	}
	
	public void setClo3(String clo3) {
		this.clo3 = clo3;
	}
	
}