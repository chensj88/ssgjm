package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:36
 */
 
@Alias("etUserLookProject")
public class EtUserLookProject extends BaseDomain implements Serializable {

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
	 * @val 用户ID
	 */
	private Long userId;
	
	/**
	 * @val 登录时间
	 */
	private Date loginTime;
	
	public EtUserLookProject() {

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
	 * @val 登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	
	/**
	 * @val 登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}