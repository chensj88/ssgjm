package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * SSGJ
 *
 * @author SSGJ
 * @date 2018-01-19 16:52:12
 */
 
@Alias("etUserLookProject")
public class EtUserLookProject extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long cId;
	
	private Long pmId;
	
	private Long userId;
	
	private Date loginTime;
	
	public EtUserLookProject() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCId() {
		return cId;
	}
	
	public void setCId(Long cId) {
		this.cId = cId;
	}
	
	public Long getPmId() {
		return pmId;
	}
	
	public void setPmId(Long pmId) {
		this.pmId = pmId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}
	
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}