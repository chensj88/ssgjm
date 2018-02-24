package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:32
 */
 
@Alias("etDataCheck")
public class EtDataCheck extends BaseDomain implements Serializable {

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
	 * @val 产品条线ID
	 */
	private Long plId;
	
	/**
	 * @val 子系统ID
	 */
	private Long pdId;
	
	/**
	 * @val 脚本路径
	 */
	private String scriptPath;
	
	/**
	 * @val 检验结果
	 */
	private String checkResult;
	
	/**
	 * @val 查看内容
	 */
	private String content;
	
	/**
	 * @val 创建人
	 */
	private Long creator;
	
	/**
	 * @val 创建时间
	 */
	private Date createTime;
	
	/**
	 * @val 操作人
	 */
	private Long operator;
	
	/**
	 * @val 操作时间
	 */
	private Date operatorTime;
	
	public EtDataCheck() {

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
	 * @val 子系统ID
	 */
	public Long getPdId() {
		return pdId;
	}
	
	/**
	 * @val 子系统ID
	 */
	public void setPdId(Long pdId) {
		this.pdId = pdId;
	}
	
	/**
	 * @val 脚本路径
	 */
	public String getScriptPath() {
		return scriptPath;
	}
	
	/**
	 * @val 脚本路径
	 */
	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}
	
	/**
	 * @val 检验结果
	 */
	public String getCheckResult() {
		return checkResult;
	}
	
	/**
	 * @val 检验结果
	 */
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	
	/**
	 * @val 查看内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 查看内容
	 */
	public void setContent(String content) {
		this.content = content;
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
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * @val 创建时间
	 */
	public void setCreateTime(Date createTime) {
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
	public Date getOperatorTime() {
		return operatorTime;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
}