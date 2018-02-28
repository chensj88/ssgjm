package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:42:37
 */
 
@Alias("sysProductShInfo")
public class SysProductShInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val 产品/系统ID
	 */
	private Long pdId;
	
	/**
	 * @val 软硬件ID
	 */
	private Long shId;
	
	/**
	 * @val 生效日期
	 */
	private Date effectiveDate;
	
	/**
	 * @val 失效日期
	 */
	private Date expireDate;
	
	/**
	 * @val 维护人员
	 */
	private Long lastUpdator;
	
	/**
	 * @val 维护时间
	 */
	private Date lastUpdateTime;

	private String pdName;
	private String pdCode;
	private String shName;
	private String shCode;
	private String shDesc;
	private String lastUpdate;
	
	public SysProductShInfo() {

	}

	/**
	 * @val 产品/系统ID
	 */
	public Long getPdId() {
		return pdId;
	}
	
	/**
	 * @val 产品/系统ID
	 */
	public void setPdId(Long pdId) {
		this.pdId = pdId;
	}
	
	/**
	 * @val 软硬件ID
	 */
	public Long getShId() {
		return shId;
	}
	
	/**
	 * @val 软硬件ID
	 */
	public void setShId(Long shId) {
		this.shId = shId;
	}
	
	/**
	 * @val 生效日期
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	
	/**
	 * @val 生效日期
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * @val 失效日期
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	
	/**
	 * @val 失效日期
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getShName() {
		return shName;
	}

	public void setShName(String shName) {
		this.shName = shName;
	}

	public String getShCode() {
		return shCode;
	}

	public void setShCode(String shCode) {
		this.shCode = shCode;
	}

	public String getShDesc() {
		return shDesc;
	}

	public void setShDesc(String shDesc) {
		this.shDesc = shDesc;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}