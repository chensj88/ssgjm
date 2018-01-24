package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:45
 */

@Alias("etDevEnvHardware")
public class EtDevEnvHardware extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long pmId;

    private Long cId;

    private String serialNo;

    private String productName;

    private String hwName;

    private String hwBrand;

    private String hwBrandModel;

    private Integer hwNum;

    private String hwUse;

    private Integer isScope;

    private Long creator;

    private Date createTime;

    private Long operator;

    private Date operatorTime;

    public EtDevEnvHardware() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPmId() {
        return pmId;
    }

    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

    public Long getCId() {
        return cId;
    }

    public void setCId(Long cId) {
        this.cId = cId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHwName() {
        return hwName;
    }

    public void setHwName(String hwName) {
        this.hwName = hwName;
    }

    public String getHwBrand() {
        return hwBrand;
    }

    public void setHwBrand(String hwBrand) {
        this.hwBrand = hwBrand;
    }

    public String getHwBrandModel() {
        return hwBrandModel;
    }

    public void setHwBrandModel(String hwBrandModel) {
        this.hwBrandModel = hwBrandModel;
    }

    public Integer getHwNum() {
        return hwNum;
    }

    public void setHwNum(Integer hwNum) {
        this.hwNum = hwNum;
    }

    public String getHwUse() {
        return hwUse;
    }

    public void setHwUse(String hwUse) {
        this.hwUse = hwUse;
    }

    public Integer getIsScope() {
        return isScope;
    }

    public void setIsScope(Integer isScope) {
        this.isScope = isScope;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

}