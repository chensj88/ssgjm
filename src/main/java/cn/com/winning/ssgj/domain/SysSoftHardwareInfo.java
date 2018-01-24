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

@Alias("sysSoftHardwareInfo")
public class SysSoftHardwareInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String shCode;

    private String shName;

    private Integer shType;

    private String shDesc;

    private String shBrand;

    private String shBrandType;

    private String shBrandConfig;

    private String shEnvType;

    private Integer isPhoto;

    private Long lastUpdator;

    private Date lastUpdateTime;

    public SysSoftHardwareInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShCode() {
        return shCode;
    }

    public void setShCode(String shCode) {
        this.shCode = shCode;
    }

    public String getShName() {
        return shName;
    }

    public void setShName(String shName) {
        this.shName = shName;
    }

    public Integer getShType() {
        return shType;
    }

    public void setShType(Integer shType) {
        this.shType = shType;
    }

    public String getShDesc() {
        return shDesc;
    }

    public void setShDesc(String shDesc) {
        this.shDesc = shDesc;
    }

    public String getShBrand() {
        return shBrand;
    }

    public void setShBrand(String shBrand) {
        this.shBrand = shBrand;
    }

    public String getShBrandType() {
        return shBrandType;
    }

    public void setShBrandType(String shBrandType) {
        this.shBrandType = shBrandType;
    }

    public String getShBrandConfig() {
        return shBrandConfig;
    }

    public void setShBrandConfig(String shBrandConfig) {
        this.shBrandConfig = shBrandConfig;
    }

    public String getShEnvType() {
        return shEnvType;
    }

    public void setShEnvType(String shEnvType) {
        this.shEnvType = shEnvType;
    }

    public Integer getIsPhoto() {
        return isPhoto;
    }

    public void setIsPhoto(Integer isPhoto) {
        this.isPhoto = isPhoto;
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