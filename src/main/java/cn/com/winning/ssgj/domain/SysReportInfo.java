package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-02 09:25:05
 */
 
@Alias("sysReportInfo")
public class SysReportInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val 主键ID
	 */
	private Long id;
	
	/**
	 * @val 编码
	 */
	private String reportCode;
	
	/**
	 * @val 名称
	 */
	private String reportName;
	
	/**
	 * @val 类型 0 凭条;1 发票;2 缴款;3 缴款单据;4 单据;5 台账;6 处方医嘱;7 申请单;8 治疗单据;9 医疗文书;10 临时医嘱;11 报告调阅;12 巡视单;13 报表;
	 */
	private Integer reportType;
	
	/**
	 * @val 纸张类型 
	 */
	private Integer paperFormat;
	
	/**
	 * @val 生产应用场景 适用于单据等，不包含报表
	 */
	private String prdUseScope;
	
	/**
	 * @val 生产使用者,适用于报表中
	 */
	private String prdUser;
	
	/**
	 * @val 描述
	 */
	private String repoetDesc;
	
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
	private Date lastUpdateTime;
	
	public SysReportInfo() {

	}

	/**
	 * @val 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 编码
	 */
	public String getReportCode() {
		return reportCode;
	}
	
	/**
	 * @val 编码
	 */
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	
	/**
	 * @val 名称
	 */
	public String getReportName() {
		return reportName;
	}
	
	/**
	 * @val 名称
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	/**
	 * @val 类型 0 凭条;1 发票;2 缴款;3 缴款单据;4 单据;5 台账;6 处方医嘱;7 申请单;8 治疗单据;9 医疗文书;10 临时医嘱;11 报告调阅;12 巡视单;13 报表;
	 */
	public Integer getReportType() {
		return reportType;
	}
	
	/**
	 * @val 类型 0 凭条;1 发票;2 缴款;3 缴款单据;4 单据;5 台账;6 处方医嘱;7 申请单;8 治疗单据;9 医疗文书;10 临时医嘱;11 报告调阅;12 巡视单;13 报表;
	 */
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	
	/**
	 * @val 纸张类型 
	 */
	public Integer getPaperFormat() {
		return paperFormat;
	}
	
	/**
	 * @val 纸张类型 
	 */
	public void setPaperFormat(Integer paperFormat) {
		this.paperFormat = paperFormat;
	}
	
	/**
	 * @val 生产应用场景 适用于单据等，不包含报表
	 */
	public String getPrdUseScope() {
		return prdUseScope;
	}
	
	/**
	 * @val 生产应用场景 适用于单据等，不包含报表
	 */
	public void setPrdUseScope(String prdUseScope) {
		this.prdUseScope = prdUseScope;
	}
	
	/**
	 * @val 生产使用者,适用于报表中
	 */
	public String getPrdUser() {
		return prdUser;
	}
	
	/**
	 * @val 生产使用者,适用于报表中
	 */
	public void setPrdUser(String prdUser) {
		this.prdUser = prdUser;
	}
	
	/**
	 * @val 描述
	 */
	public String getRepoetDesc() {
		return repoetDesc;
	}
	
	/**
	 * @val 描述
	 */
	public void setRepoetDesc(String repoetDesc) {
		this.repoetDesc = repoetDesc;
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
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}