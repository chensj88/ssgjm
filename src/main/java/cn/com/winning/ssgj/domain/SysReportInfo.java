package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */

@Alias("sysReportInfo")
public class SysReportInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String reportCode;

    private String reportName;

    private Integer reportType;

    private Integer paperFormat;

    private String prdUseScope;

    private String prdUser;

    private String repoetDesc;

    private Long lastUpdator;

    private Date lastUpdateTime;

    public SysReportInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getPaperFormat() {
        return paperFormat;
    }

    public void setPaperFormat(Integer paperFormat) {
        this.paperFormat = paperFormat;
    }

    public String getPrdUseScope() {
        return prdUseScope;
    }

    public void setPrdUseScope(String prdUseScope) {
        this.prdUseScope = prdUseScope;
    }

    public String getPrdUser() {
        return prdUser;
    }

    public void setPrdUser(String prdUser) {
        this.prdUser = prdUser;
    }

    public String getRepoetDesc() {
        return repoetDesc;
    }

    public void setRepoetDesc(String repoetDesc) {
        this.repoetDesc = repoetDesc;
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