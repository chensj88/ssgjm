package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import cn.com.winning.ssgj.base.Constants;
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

	/**
	 * @val ID 主键
	 */
	private Long id;

	/**
	 * @val 视频名称
	 */
	private String videoName;
	/**
	 * @val 视频描述
	 */
	private String videoDesc;

	/**
	 * @val 分类名称
	 */
	private String typeLabel;
	/**
	 * @val 视频远程保存地址
	 */
	private String remotePath;

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
	private java.sql.Timestamp lastUpdateTime;

	/**
	 * @val 视频时长
	 */
	private Long videoTime;

	/**
	 * @val 视频分类
	 */
	private String videoType;

	/**
	 * @val 客户ID
	 */
	private Long cId;

	/**
	 * @val 客户名称
	 */
	private String custName;

	/**
	 * @val 客户视频分类
	 */
	 private String videoCType ;

	/**
	 * @val 客户视频分类名称
	 */
	 private String  videoCLabel;




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
	public java.sql.Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(java.sql.Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(Long videoTime) {
		this.videoTime = videoTime;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getVideoCType() {
		return videoCType;
	}

	public void setVideoCType(String videoCType) {
		this.videoCType = videoCType;
	}

	public String getVideoCLabel() {
		return videoCLabel;
	}

	public void setVideoCLabel(String videoCLabel) {
		this.videoCLabel = videoCLabel;
	}

	/**
	 * 生成上传路径<br/>
	 *  1、非用户自定义视频  路径为 typeLabel<br/>
	 *  2、用户自定义视频    路径为 custName +"/" + videoCLabel<br/>
	 * @return remotePath
	 */
	public String generateRemoteFile(){
		StringBuilder remotePath = new StringBuilder();
		if (!Constants.VIDEO_TYPE_OF_CUSTOMER.equals(videoType)){
			remotePath.append(typeLabel);
		}else{
			remotePath.append(custName +"/" + videoCLabel);
		}
	return  remotePath.toString();
	}
}