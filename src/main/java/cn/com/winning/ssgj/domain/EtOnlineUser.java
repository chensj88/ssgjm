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
 
@Alias("etOnlineUser")
public class EtOnlineUser extends BaseDomain implements Serializable {

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
	 * @val 工号
	 */
	private String userCode;
	
	/**
	 * @val 姓名
	 */
	private String cName;
	
	/**
	 * @val 角色名称
	 */
	private String roleName;
	
	/**
	 * @val 负责部门
	 */
	private String responseDept;
	
	/**
	 * @val 负责站点
	 */
	private String responseSite;
	
	/**
	 * @val 电话
	 */
	private String telephone;
	
	/**
	 * @val 微信号
	 */
	private String wechatNo;
	
	/**
	 * @val 邮箱
	 */
	private String email;
	
	/**
	 * @val 住宿信息
	 */
	private String lodging;
	
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
	 * 状态0:无效1:有效
	 */
	private Integer status;
	
	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public EtOnlineUser() {

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
	 * @val 工号
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * @val 工号
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	/**
	 * @val 姓名
	 */
	public String getCName() {
		return cName;
	}
	
	/**
	 * @val 姓名
	 */
	public void setCName(String cName) {
		this.cName = cName;
	}
	
	/**
	 * @val 角色名称
	 */
	public String getRoleName() {
		return roleName;
	}
	
	/**
	 * @val 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * @val 负责部门
	 */
	public String getResponseDept() {
		return responseDept;
	}
	
	/**
	 * @val 负责部门
	 */
	public void setResponseDept(String responseDept) {
		this.responseDept = responseDept;
	}
	
	/**
	 * @val 负责站点
	 */
	public String getResponseSite() {
		return responseSite;
	}
	
	/**
	 * @val 负责站点
	 */
	public void setResponseSite(String responseSite) {
		this.responseSite = responseSite;
	}
	
	/**
	 * @val 电话
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * @val 电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * @val 微信号
	 */
	public String getWechatNo() {
		return wechatNo;
	}
	
	/**
	 * @val 微信号
	 */
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}
	
	/**
	 * @val 邮箱
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @val 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @val 住宿信息
	 */
	public String getLodging() {
		return lodging;
	}
	
	/**
	 * @val 住宿信息
	 */
	public void setLodging(String lodging) {
		this.lodging = lodging;
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