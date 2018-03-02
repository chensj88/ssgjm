package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-28 09:00:20
 */
 
@Alias("sysTrainVideoRepo")
public class SysTrainVideoRepo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String videoName;
	
	private String videoDesc;
	
	private String videoType;
	
	private String remotePath;
	/**
	 * 视频时长
	 */
	private Long videoTime;
	
	/**
	 * @val 状态 0失效;1 生效
	 */
	private Integer status;
	
	/**
	 * @val 接口描述
	 */
	private Long lastUpdator;
	
	/**
	 * @val 维护时间
	 */
	private Date lastUpdateTime;
	
	public SysTrainVideoRepo() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getVideoName() {
		return videoName;
	}
	
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
	public String getVideoDesc() {
		return videoDesc;
	}
	
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	
	public String getVideoType() {
		return videoType;
	}
	
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	
	public String getRemotePath() {
		return remotePath;
	}
	
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}
	
	/**
	 * @val 状态 0失效;1 生效
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 状态 0失效;1 生效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 接口描述
	 */
	public Long getLastUpdator() {
		return lastUpdator;
	}
	
	/**
	 * @val 接口描述
	 */
	public void setLastUpdator(Long lastUpdator) {
		this.lastUpdator = lastUpdator;
	}
	
	/**
	 * @val 维护时间
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(Long videoTime) {
		this.videoTime = videoTime;
	}
}