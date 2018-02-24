package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:35
 */
 
@Alias("etTrainViedoRecord")
public class EtTrainViedoRecord extends BaseDomain implements Serializable {

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
	 * @val 关联视频ID
	 */
	private Long sourceId;
	
	/**
	 * @val 播放时间记录
	 */
	private Integer lookRecord;
	
	/**
	 * @val 用户ID
	 */
	private Long userId;
	
	public EtTrainViedoRecord() {

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
	 * @val 关联视频ID
	 */
	public Long getSourceId() {
		return sourceId;
	}
	
	/**
	 * @val 关联视频ID
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	
	/**
	 * @val 播放时间记录
	 */
	public Integer getLookRecord() {
		return lookRecord;
	}
	
	/**
	 * @val 播放时间记录
	 */
	public void setLookRecord(Integer lookRecord) {
		this.lookRecord = lookRecord;
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
	
}