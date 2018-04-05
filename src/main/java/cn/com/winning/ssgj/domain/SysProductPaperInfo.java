package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:02
 */
 
@Alias("sysProductPaperInfo")
public class SysProductPaperInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val 产品/系统ID
	 */
	private Long pdId;
	
	/**
	 * @val 报表类主键
	 */
	private Long rId;
	
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
	private java.sql.Timestamp lastUpdateTime;
	private String pdName;
	private String pdCode;
	private String reportName;
	private String reportCode;
	private String reportDesc;
	private String lastUpdate;

	
	public SysProductPaperInfo() {

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
	public java.sql.Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(java.sql.Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
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

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getReportDesc() {
		return reportDesc;
	}

	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}