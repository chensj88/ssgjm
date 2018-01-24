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

@Alias("sysProductInterfaceInfo")
public class SysProductInterfaceInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long pdId;

    private Long interId;

    private Long lastUpdator;

    private Date lastUpdateTime;

    public SysProductInterfaceInfo() {

    }

    public Long getPdId() {
        return pdId;
    }

    public void setPdId(Long pdId) {
        this.pdId = pdId;
    }

    public Long getInterId() {
        return interId;
    }

    public void setInterId(Long interId) {
        this.interId = interId;
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