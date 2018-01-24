package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:44
 */

@Alias("etCustomerDetail")
public class EtCustomerDetail extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String custName;

    private Integer hospitalLevel;

    private Integer specialType;

    private Integer characteristic;

    private Integer areaCode;

    private Integer outpatientNum;

    private Integer bedNum;

    private Long creator;

    private Date createTime;

    private Long operator;

    private Date operatorTime;

    public EtCustomerDetail() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(Integer hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public Integer getSpecialType() {
        return specialType;
    }

    public void setSpecialType(Integer specialType) {
        this.specialType = specialType;
    }

    public Integer getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Integer characteristic) {
        this.characteristic = characteristic;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getOutpatientNum() {
        return outpatientNum;
    }

    public void setOutpatientNum(Integer outpatientNum) {
        this.outpatientNum = outpatientNum;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
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