package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-20 11:22:30
 */
 
@Alias("etAccessToken")
public class EtAccessToken extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String accessToken;
	
	private String expiresIn;
	
	/**
	 * @val 0：微信企业号1：微信服务号
	 */
	private Integer type;
	
	private java.sql.Timestamp lastTime;
	
	private String remark;
	
	public EtAccessToken() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getExpiresIn() {
		return expiresIn;
	}
	
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	/**
	 * @val 0：微信企业号1：微信服务号
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * @val 0：微信企业号1：微信服务号
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public java.sql.Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(java.sql.Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}