package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-24 14:03:01
 */
 
@Alias("etCustomerDetail")
public class EtCustomerDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String pmCode;
	
	private String pmName;
	
	private Long cId;
	
	private String serialNo;
	
	private String custName;
	
	private Integer hospitalLevel;
	
	private String specialType;
	
	private String characteristic;

	private Integer cityCode;

	private Integer areaCode;
	
	private Integer outpatientNum;
	
	private Integer bedNum;
	
	private Long creator;

	private java.sql.Timestamp createTime;
	
	private Long operator;

	private java.sql.Timestamp operatorTime;

	
	public EtCustomerDetail() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPmCode() {
		return pmCode;
	}
	
	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}
	
	public String getPmName() {
		return pmName;
	}
	
	public void setPmName(String pmName) {
		this.pmName = pmName;
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
	
	public String getSpecialType() {
		return specialType;
	}
	
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	
	public String getCharacteristic() {
		return characteristic;
	}
	
	public void setCharacteristic(String characteristic) {
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
	
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public Long getOperator() {
		return operator;
	}
	
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	
	public java.sql.Timestamp getOperatorTime() {
		return operatorTime;
	}
	
	public void setOperatorTime(java.sql.Timestamp operatorTime) {
		this.operatorTime = operatorTime;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
}