package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-05 15:18:13
 */
 
@Alias("sysUserInfo")
public class SysUserInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 允许登陆状态 1：是2：否
	 */
	private Integer status;
	
	/**
	 * @val 登陆账号ID
	 */
	private String userid;
	
	/**
	 * @val 用户名称
	 */
	private String yhmc;
	
	/**
	 * @val 用户姓名
	 */
	private String name;
	
	/**
	 * @val Email
	 */
	private String email;
	
	/**
	 * @val 手机号码
	 */
	private String mobile;
	
	/**
	 * @val 固定电话
	 */
	private String telephone;
	
	/**
	 * @val 密码
	 */
	private String password;
	
	/**
	 * @val 隶属组织
	 */
	private String orgid;
	
	private String ssgs;
	
	/**
	 * @val 用户关联ID
	 */
	private Long linkId;
	
	/**
	 * @val 类型：0 医院成员 1公司成员
	 */
	private String userType;
	
	/**
	 * @val 用户上级工号
	 */
	private String yhsjgh;
	
	public SysUserInfo() {

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
	 * @val 允许登陆状态 1：是2：否
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 允许登陆状态 1：是2：否
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 登陆账号ID
	 */
	public String getUserid() {
		return userid;
	}
	
	/**
	 * @val 登陆账号ID
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * @val 用户名称
	 */
	public String getYhmc() {
		return yhmc;
	}
	
	/**
	 * @val 用户名称
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	
	/**
	 * @val 用户姓名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @val Email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @val Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @val 手机号码
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * @val 手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * @val 固定电话
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * @val 固定电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * @val 密码
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @val 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @val 隶属组织
	 */
	public String getOrgid() {
		return orgid;
	}
	
	/**
	 * @val 隶属组织
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	public String getSsgs() {
		return ssgs;
	}
	
	public void setSsgs(String ssgs) {
		this.ssgs = ssgs;
	}
	
	/**
	 * @val 用户关联ID
	 */
	public Long getLinkId() {
		return linkId;
	}
	
	/**
	 * @val 用户关联ID
	 */
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	
	/**
	 * @val 类型：0 医院成员 1公司成员
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * @val 类型：0 医院成员 1公司成员
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * @val 用户上级工号
	 */
	public String getYhsjgh() {
		return yhsjgh;
	}
	
	/**
	 * @val 用户上级工号
	 */
	public void setYhsjgh(String yhsjgh) {
		this.yhsjgh = yhsjgh;
	}
	
}