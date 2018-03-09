package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:33
 */
 
@Alias("etDevEnvHardware")
public class EtDevEnvHardware extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	/**
	 * @val 项目ID
	 */
	private Long pmId;
	
	/**
	 * @val 合同ID
	 */
	private Long cId;
	
	/**
	 * @val 业务单据编码
	 */
	private String serialNo;
	
	/**
	 * @val 系统名称
	 */
	private String productName;
	
	/**
	 * @val 硬件名称
	 */
	private String hwName;
	
	/**
	 * @val 推荐品牌
	 */
	private String hwBrand;
	
	/**
	 * @val 推荐型号
	 */
	private String hwBrandModel;
	
	/**
	 * @val 数量
	 */
	private Integer hwNum;
	
	/**
	 * @val 用途
	 */
	private String hwUse;
	
	/**
	 * @val 是否本期范围 0 否，1是
	 */
	private Integer isScope;
	
	/**
	 * @val 创建人
	 */
	private Long creator;
	
	/**
	 * @val 创建时间
	 */
	private java.sql.Timestamp createTime;
	
	/**
	 * @val 操作人
	 */
	private Long operator;
	
	/**
	 * @val 操作时间
	 */
	private java.sql.Timestamp operatorTime;
	
	public EtDevEnvHardware() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 项目ID
	 */
	public Long getPmId() {
		return pmId;
	}
	
	/**
	 * @val 项目ID
	 */
	public void setPmId(Long pmId) {
		this.pmId = pmId;
	}
	
	/**
	 * @val 合同ID
	 */
	public Long getCId() {
		return cId;
	}
	
	/**
	 * @val 合同ID
	 */
	public void setCId(Long cId) {
		this.cId = cId;
	}
	
	/**
	 * @val 业务单据编码
	 */
	public String getSerialNo() {
		return serialNo;
	}
	
	/**
	 * @val 业务单据编码
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * @val 系统名称
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * @val 系统名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * @val 硬件名称
	 */
	public String getHwName() {
		return hwName;
	}
	
	/**
	 * @val 硬件名称
	 */
	public void setHwName(String hwName) {
		this.hwName = hwName;
	}
	
	/**
	 * @val 推荐品牌
	 */
	public String getHwBrand() {
		return hwBrand;
	}
	
	/**
	 * @val 推荐品牌
	 */
	public void setHwBrand(String hwBrand) {
		this.hwBrand = hwBrand;
	}
	
	/**
	 * @val 推荐型号
	 */
	public String getHwBrandModel() {
		return hwBrandModel;
	}
	
	/**
	 * @val 推荐型号
	 */
	public void setHwBrandModel(String hwBrandModel) {
		this.hwBrandModel = hwBrandModel;
	}
	
	/**
	 * @val 数量
	 */
	public Integer getHwNum() {
		return hwNum;
	}
	
	/**
	 * @val 数量
	 */
	public void setHwNum(Integer hwNum) {
		this.hwNum = hwNum;
	}
	
	/**
	 * @val 用途
	 */
	public String getHwUse() {
		return hwUse;
	}
	
	/**
	 * @val 用途
	 */
	public void setHwUse(String hwUse) {
		this.hwUse = hwUse;
	}
	
	/**
	 * @val 是否本期范围 0 否，1是
	 */
	public Integer getIsScope() {
		return isScope;
	}
	
	/**
	 * @val 是否本期范围 0 否，1是
	 */
	public void setIsScope(Integer isScope) {
		this.isScope = isScope;
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
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}
	
	/**
	 * @val 创建时间
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
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
	public java.sql.Timestamp getOperatorTime() {
		return operatorTime;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOperatorTime(java.sql.Timestamp operatorTime) {
		this.operatorTime = operatorTime;
	}
	
}