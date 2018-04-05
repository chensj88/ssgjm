package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * SSGJ
 * 接口表--用户信息表
 * @author SSGJ
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
	private Long orgid;
	/**
	 * @val 隶属公司
	 */
	private Long ssgs;

	/**
	 * @val 类型：0 医院成员 1公司成员
	 */
	private String userType;
	/**
	 * @val  用户关联ID
	 */
	private Long linkId;

	/**
	 * @val  用户微信ID
	 */
	private String openId;

	/**
	 * @val  视频权限
	 */
	private String videoDroit;

	/**
	 * @val  扩展字段
	 */
	private String clo1;

	/**
	 * @val  扩展字段
	 */
	private String clo2;

	/**
	 * @val  扩展字段
	 */
	private String clo3;

	/**
	 * @val  扩展字段
	 */
	private String clo4;

	/**
	 * @val  扩展字段
	 */
	private String clo5;

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
	public Long getOrgid() {
		return orgid;
	}
	
	/**
	 * @val 隶属组织
	 */
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	/**
	 * @val 隶属公司
	 */
	public Long getSsgs() {
		return ssgs;
	}
	/**
	 * @val 隶属公司
	 */
	public void setSsgs(Long ssgs) {
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getVideoDroit() {
		return videoDroit;
	}

	public void setVideoDroit(String videoDroit) {
		this.videoDroit = videoDroit;
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

	public String getClo4() {
		return clo4;
	}

	public void setClo4(String clo4) {
		this.clo4 = clo4;
	}

	public String getClo5() {
		return clo5;
	}

	public void setClo5(String clo5) {
		this.clo5 = clo5;
	}
<<<<<<< HEAD


	@Override
	public String toString() {
		return "SysUserInfo{" +
				"id=" + id +
				", status=" + status +
				", userid='" + userid + '\'' +
				", yhmc='" + yhmc + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", telephone='" + telephone + '\'' +
				", password='" + password + '\'' +
				", orgid=" + orgid +
				", ssgs=" + ssgs +
				", userType='" + userType + '\'' +
				", linkId=" + linkId +
				", openId='" + openId + '\'' +
				", videoDroit='" + videoDroit + '\'' +
				", clo1='" + clo1 + '\'' +
				", clo2='" + clo2 + '\'' +
				", clo3='" + clo3 + '\'' +
				", clo4='" + clo4 + '\'' +
				", clo5='" + clo5 + '\'' +
				'}';
	}
=======
>>>>>>> a340590b36085a7325c63510bc48d0535149fc66
}