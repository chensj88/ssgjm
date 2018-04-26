package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:36
 */
 
@Alias("etUserInfo")
public class EtUserInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
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
	 * @val 客户号
	 */
	private String serialNo;
	
	/**
	 * @val 用户ID
	 */
	private Long userId;
	
	/**
	 * @val 用户类型 0客户 1公司 
	 */
	private Integer userType;
	
	/**
	 * @val 工号
	 */
	private String userCard;
	
	/**
	 * @val 姓名
	 */
	private String cName;
	
	/**
	 * @val 部门
	 */
	private String orgName;
	
	/**
	 * @val 职务 用户配置的角色名称
	 */
	private String positionName;
	
	/**
	 * @val 电话
	 */
	private String telephone;
	
	/**
	 * @val 邮箱
	 */
	private String email;
	
	/**
	 * @val 备注
	 */
	private String remark;
	
	/**
	 * @val 是否删除 0 否；1是
	 */
	private Integer isDel;
	
	public EtUserInfo() {

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

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @val 用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * @val 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * @val 用户类型 0客户 1公司 
	 */
	public Integer getUserType() {
		return userType;
	}
	
	/**
	 * @val 用户类型 0客户 1公司 
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	/**
	 * @val 工号
	 */
	public String getUserCard() {
		return userCard;
	}
	
	/**
	 * @val 工号
	 */
	public void setUserCard(String userCard) {
		this.userCard = userCard;
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
	 * @val 部门
	 */
	public String getOrgName() {
		return orgName;
	}
	
	/**
	 * @val 部门
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	/**
	 * @val 职务 用户配置的角色名称
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * @val 职务 用户配置的角色名称
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
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
	 * @val 是否删除 0 否；1是
	 */
	public Integer getIsDel() {
		return isDel;
	}
	
	/**
	 * @val 是否删除 0 否；1是
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}