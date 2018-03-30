package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:34
 */
 
@Alias("etSiteInstallDetail")
public class EtSiteInstallDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 关联主表ID
	 */
	private Long sourceId;
	
	/**
	 * @val 站点名称
	 */
	private String siteName;
	
	/**
	 * @val IP地址
	 */
	private String ip;
	
	/**
	 * @val 楼宇
	 */
	private String building;
	
	/**
	 * @val 楼宇
	 */
	private Integer floorNum;
	
	/**
	 * @val PC机型号
	 */
	private String pcModel;
	
	/**
	 * @val 安装情况 :0：未安装 1：安装中 2：已安装
	 */
	private Integer install;
	
	/**
	 * @val 图片路径
	 */
	private String imgPath;
	
	/**
	 * @val 操作人
	 */
	private Long operator;
	
	/**
	 * @val 操作时间
	 */
	private java.sql.Timestamp operatorTime;

	private List imgs;
	
	public EtSiteInstallDetail() {

	}

	public EtSiteInstallDetail(Long id, Long sourceId, String siteName, String ip, String building, Integer floorNum, String pcModel, Integer install, String imgPath, Long operator, Timestamp operatorTime, List imgs) {
		this.id = id;
		this.sourceId = sourceId;
		this.siteName = siteName;
		this.ip = ip;
		this.building = building;
		this.floorNum = floorNum;
		this.pcModel = pcModel;
		this.install = install;
		this.imgPath = imgPath;
		this.operator = operator;
		this.operatorTime = operatorTime;
		this.imgs = imgs;
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
	 * @val 关联主表ID
	 */
	public Long getSourceId() {
		return sourceId;
	}
	
	/**
	 * @val 关联主表ID
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	
	/**
	 * @val 站点名称
	 */
	public String getSiteName() {
		return siteName;
	}
	
	/**
	 * @val 站点名称
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	/**
	 * @val IP地址
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * @val IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * @val 楼宇
	 */
	public String getBuilding() {
		return building;
	}
	
	/**
	 * @val 楼宇
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	
	/**
	 * @val 楼宇
	 */
	public Integer getFloorNum() {
		return floorNum;
	}
	
	/**
	 * @val 楼宇
	 */
	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}
	
	/**
	 * @val PC机型号
	 */
	public String getPcModel() {
		return pcModel;
	}
	
	/**
	 * @val PC机型号
	 */
	public void setPcModel(String pcModel) {
		this.pcModel = pcModel;
	}
	
	/**
	 * @val 安装情况 :0：未安装 1：安装中 2：已安装
	 */
	public Integer getInstall() {
		return install;
	}
	
	/**
	 * @val 安装情况 :0：未安装 1：安装中 2：已安装
	 */
	public void setInstall(Integer install) {
		this.install = install;
	}
	
	/**
	 * @val 图片路径
	 */
	public String getImgPath() {
		return imgPath;
	}
	
	/**
	 * @val 图片路径
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	/**
	 * @val 操作人
	 */
	public Long getOperator() {
		return operator;
	}
	
	/**
	 * @val 操作人
	 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	
	/**
	 * @val 操作时间
	 */
	public java.sql.Timestamp getOperatorTime() {
		return operatorTime;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOperatorTime(java.sql.Timestamp operatorTime) {
		this.operatorTime = operatorTime;
	}

	public List getImgs() {
		return imgs;
	}

	public void setImgs(List imgs) {
		this.imgs = imgs;
	}

}