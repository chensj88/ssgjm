package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-09 16:50:09
 */
 
@Alias("sysDataCheckScript")
public class SysDataCheckScript extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String name;
	
	private String desc;
	
	private String remotePath;
	
	private Integer status;
	
	private Long lastUpdator;
	
	private Date lastUpdateTime;
	
	public SysDataCheckScript() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getRemotePath() {
		return remotePath;
	}
	
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getLastUpdator() {
		return lastUpdator;
	}
	
	public void setLastUpdator(Long lastUpdator) {
		this.lastUpdator = lastUpdator;
	}
	
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}