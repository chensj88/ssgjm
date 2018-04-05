package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * SSGJ
 *
 * @author SSGJ
 * @date 2018-01-30 11:27:25
 */
 
@Alias("sysSoftHardwareInfo")
public class SysSoftHardwareInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 软硬件代码
	 */
	private String shCode;
	
	/**
	 * @val 软硬件名称
	 */
	private String shName;
	
	/**
	 * @val 分类, 0 软件,1 硬件
	 */
	private Integer shType;
	
	/**
	 * @val 设备描述
	 */
	private String shDesc;
	
	/**
	 * @val 推荐品牌
	 */
	private String shBrand;
	
	/**
	 * @val 推荐型号
	 */
	private String shBrandType;
	
	/**
	 * @val 推荐配置
	 */
	private String shBrandConfig;
	
	/**
	 * @val 应用环境  0：测试，1正式，2：测试&正式
	 */
	private String shEnvType;
	
	/**
	 * @val 是否需要照片 0 否 1是
	 */
	private Integer isPhoto;
	
	/**
	 * @val 状态 0 失效 1生效
	 */
	private Integer status;
	
	/**
	 * @val 维护人员
	 */
	private Long lastUpdator;
	
	/**
	 * @val 维护时间
	 */
	private java.sql.Timestamp lastUpdateTime;
	
	public SysSoftHardwareInfo() {

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
	 * @val 软硬件代码
	 */
	public String getShCode() {
		return shCode;
	}
	
	/**
	 * @val 软硬件代码
	 */
	public void setShCode(String shCode) {
		this.shCode = shCode;
	}
	
	/**
	 * @val 软硬件名称
	 */
	public String getShName() {
		return shName;
	}
	
	/**
	 * @val 软硬件名称
	 */
	public void setShName(String shName) {
		this.shName = shName;
	}
	
	/**
	 * @val 分类, 0 软件,1 硬件
	 */
	public Integer getShType() {
		return shType;
	}
	
	/**
	 * @val 分类, 0 软件,1 硬件
	 */
	public void setShType(Integer shType) {
		this.shType = shType;
	}
	
	/**
	 * @val 设备描述
	 */
	public String getShDesc() {
		return shDesc;
	}
	
	/**
	 * @val 设备描述
	 */
	public void setShDesc(String shDesc) {
		this.shDesc = shDesc;
	}
	
	/**
	 * @val 推荐品牌
	 */
	public String getShBrand() {
		return shBrand;
	}
	
	/**
	 * @val 推荐品牌
	 */
	public void setShBrand(String shBrand) {
		this.shBrand = shBrand;
	}
	
	/**
	 * @val 推荐型号
	 */
	public String getShBrandType() {
		return shBrandType;
	}
	
	/**
	 * @val 推荐型号
	 */
	public void setShBrandType(String shBrandType) {
		this.shBrandType = shBrandType;
	}
	
	/**
	 * @val 推荐配置
	 */
	public String getShBrandConfig() {
		return shBrandConfig;
	}
	
	/**
	 * @val 推荐配置
	 */
	public void setShBrandConfig(String shBrandConfig) {
		this.shBrandConfig = shBrandConfig;
	}
	
	/**
	 * @val 应用环境
	 */
	public String getShEnvType() {
		return shEnvType;
	}
	
	/**
	 * @val 应用环境
	 */
	public void setShEnvType(String shEnvType) {
		this.shEnvType = shEnvType;
	}
	
	/**
	 * @val 是否需要照片 0 否 1是
	 */
	public Integer getIsPhoto() {
		return isPhoto;
	}
	
	/**
	 * @val 是否需要照片 0 否 1是
	 */
	public void setIsPhoto(Integer isPhoto) {
		this.isPhoto = isPhoto;
	}
	
	/**
	 * @val 状态 0 失效 1生效
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 状态 0 失效 1生效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 维护人员
	 */
	public Long getLastUpdator() {
		return lastUpdator;
	}
	
	/**
	 * @val 维护人员
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
	
}