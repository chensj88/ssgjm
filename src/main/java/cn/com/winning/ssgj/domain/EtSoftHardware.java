package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:35
 */
 
@Alias("etSoftHardware")
public class EtSoftHardware extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 合同ID
	 */
	private Long cId;
	
	/**
	 * @val 项目ID
	 */
	private Long pmId;
	
	/**
	 * @val 单据号
	 */
	private String serialNo;
	
	/**
	 * @val 产品条线ID
	 */
	private Long plId;
	
	/**
	 * @val 硬件名称
	 */
	private String hwName;
	
	/**
	 * @val 硬件编码
	 */
	private String hwCode;
	
	/**
	 * @val 推荐品牌
	 */
	private String brand;
	
	/**
	 * @val 推荐型号
	 */
	private String model;
	
	/**
	 * @val 数量
	 */
	private Integer num;
	
	/**
	 * @val 用途
	 */
	private String useContent;
	
	/**
	 * @val 是否本期范围：0 否   1 是
	 */
	private Integer isScope;
	
	/**
	 * @val 不在实施范围原因
	 */
	private String noScopeCode;
	
	/**
	 * @val 完成情况 0:未完成 1：完成
	 */
	private String content;
	
	/**
	 * @val 数据来源 0 PMIS;1 自定义
	 */
	private Integer sourceType;
	
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
	
	public EtSoftHardware() {

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
	 * @val 单据号
	 */
	public String getSerialNo() {
		return serialNo;
	}
	
	/**
	 * @val 单据号
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * @val 产品条线ID
	 */
	public Long getPlId() {
		return plId;
	}
	
	/**
	 * @val 产品条线ID
	 */
	public void setPlId(Long plId) {
		this.plId = plId;
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
	 * @val 硬件编码
	 */
	public String getHwCode() {
		return hwCode;
	}
	
	/**
	 * @val 硬件编码
	 */
	public void setHwCode(String hwCode) {
		this.hwCode = hwCode;
	}
	
	/**
	 * @val 推荐品牌
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * @val 推荐品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * @val 推荐型号
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * @val 推荐型号
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * @val 数量
	 */
	public Integer getNum() {
		return num;
	}
	
	/**
	 * @val 数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	
	/**
	 * @val 用途
	 */
	public String getUseContent() {
		return useContent;
	}
	
	/**
	 * @val 用途
	 */
	public void setUseContent(String useContent) {
		this.useContent = useContent;
	}
	
	/**
	 * @val 是否本期范围：0 否   1 是
	 */
	public Integer getIsScope() {
		return isScope;
	}
	
	/**
	 * @val 是否本期范围：0 否   1 是
	 */
	public void setIsScope(Integer isScope) {
		this.isScope = isScope;
	}
	
	/**
	 * @val 不在实施范围原因
	 */
	public String getNoScopeCode() {
		return noScopeCode;
	}
	
	/**
	 * @val 不在实施范围原因
	 */
	public void setNoScopeCode(String noScopeCode) {
		this.noScopeCode = noScopeCode;
	}
	
	/**
	 * @val 完成情况 0:未完成 1：完成
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 完成情况 0:未完成 1：完成
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @val 数据来源 0 PMIS;1 自定义
	 */
	public Integer getSourceType() {
		return sourceType;
	}
	
	/**
	 * @val 数据来源 0 PMIS;1 自定义
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
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