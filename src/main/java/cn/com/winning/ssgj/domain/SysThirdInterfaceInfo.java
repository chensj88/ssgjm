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

@Alias("sysThirdInterfaceInfo")
public class SysThirdInterfaceInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String interCode;

    private String interName;

    private String refProductName;

    private String interDesc;

    private Integer interProps;

    private Integer status;

    private Long lastUpdator;

    private Date lastUpdateTime;

    public SysThirdInterfaceInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterCode() {
        return interCode;
    }

    public void setInterCode(String interCode) {
        this.interCode = interCode;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getRefProductName() {
        return refProductName;
    }

    public void setRefProductName(String refProductName) {
        this.refProductName = refProductName;
    }

    public String getInterDesc() {
        return interDesc;
    }

    public void setInterDesc(String interDesc) {
        this.interDesc = interDesc;
    }

    public Integer getInterProps() {
        return interProps;
    }

    public void setInterProps(Integer interProps) {
        this.interProps = interProps;
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