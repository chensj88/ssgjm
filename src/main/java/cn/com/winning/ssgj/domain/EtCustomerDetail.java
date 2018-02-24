package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:29
 */
 
@Alias("etCustomerDetail")
public class EtCustomerDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 客户名称
	 */
	private String custName;
	
	/**
	 * @val 医院等级
	 */
	private Integer hospitalLevel;
	
	/**
	 * @val 专科类型
	 */
	private Integer specialType;
	
	/**
	 * @val 特色科室
	 */
	private Integer characteristic;
	
	/**
	 * @val 所在地区
	 */
	private Integer areaCode;
	
	private Integer outpatientNum;
	
	private Integer bedNum;
	
	/**
	 * @val 创建人
	 */
	private Long creator;
	
	/**
	 * @val 创建时间
	 */
	private Date createTime;
	
	/**
	 * @val 操作人
	 */
	private Long operator;
	
	/**
	 * @val 操作时间
	 */
	private Date operatorTime;
	
	public EtCustomerDetail() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 客户名称
	 */
	public String getCustName() {
		return custName;
	}
	
	/**
	 * @val 客户名称
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	/**
	 * @val 医院等级
	 */
	public Integer getHospitalLevel() {
		return hospitalLevel;
	}
	
	/**
	 * @val 医院等级
	 */
	public void setHospitalLevel(Integer hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	
	/**
	 * @val 专科类型
	 */
	public Integer getSpecialType() {
		return specialType;
	}
	
	/**
	 * @val 专科类型
	 */
	public void setSpecialType(Integer specialType) {
		this.specialType = specialType;
	}
	
	/**
	 * @val 特色科室
	 */
	public Integer getCharacteristic() {
		return characteristic;
	}
	
	/**
	 * @val 特色科室
	 */
	public void setCharacteristic(Integer characteristic) {
		this.characteristic = characteristic;
	}
	
	/**
	 * @val 所在地区
	 */
	public Integer getAreaCode() {
		return areaCode;
	}
	
	/**
	 * @val 所在地区
	 */
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
	
	/**
	 * @val 创建人
	 */
	public Long getCreator() {
		return creator;
	}
	
	/**
	 * @val 创建人
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	/**
	 * @val 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * @val 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @val 操作人
	 */
	public Long getOperator() {
		return operator;
	}
	
	/**
	 * @val 操作人
	 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	
	/**
	 * @val 操作时间
	 */
	public Date getOperatorTime() {
		return operatorTime;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
}