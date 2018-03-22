package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import java.util.List;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:35
 */
 
@Alias("etSiteQuestionInfo")
public class EtSiteQuestionInfo extends BaseDomain implements Serializable {

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
	 * @val 站点名称
	 */
	private String siteName;
	
	/**
	 * @val 系统名称
	 */
	private String productName;
	
	/**
	 * @val 菜单名称
	 */
	private String menuName;
	
	/**
	 * @val 问题类型
	 */
	private String questionType;
	
	/**
	 * @val 问题描述
	 */
	private String questionDesc;
	
	/**
	 * @val 问题照片路径
	 */
	private String imgPath;
	
	/**
	 * @val 处理方式 0 程序修改；1模板配置
	 */
	private Integer operType;
	
	/**
	 * @val 优先级 0 高；1中；2低；3不处理；4 加强管理，解释
	 */
	private Integer priority;
	
	/**
	 * @val 分配人员
	 */
	private Long allocateUser;
	
	/**
	 * @val 处理状态 0 否，1是
	 */
	private Integer isOperation;
	
	/**
	 * @val 备注
	 */
	private String remark;
	
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

	private List imgs;
	
	public EtSiteQuestionInfo() {

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
	 * @val 站点名称
	 */
	public String getSiteName() {
		return siteName;
	}
	
	/**
	 * @val 站点名称
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
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
	 * @val 菜单名称
	 */
	public String getMenuName() {
		return menuName;
	}
	
	/**
	 * @val 菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	/**
	 * @val 问题类型
	 */
	public String getQuestionType() {
		return questionType;
	}
	
	/**
	 * @val 问题类型
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	/**
	 * @val 问题描述
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}
	
	/**
	 * @val 问题描述
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	
	/**
	 * @val 问题照片路径
	 */
	public String getImgPath() {
		return imgPath;
	}
	
	/**
	 * @val 问题照片路径
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	/**
	 * @val 处理方式 0 程序修改；1模板配置
	 */
	public Integer getOperType() {
		return operType;
	}
	
	/**
	 * @val 处理方式 0 程序修改；1模板配置
	 */
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	
	/**
	 * @val 优先级 0 高；1中；2低；3不处理；4 加强管理，解释
	 */
	public Integer getPriority() {
		return priority;
	}
	
	/**
	 * @val 优先级 0 高；1中；2低；3不处理；4 加强管理，解释
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	/**
	 * @val 分配人员
	 */
	public Long getAllocateUser() {
		return allocateUser;
	}
	
	/**
	 * @val 分配人员
	 */
	public void setAllocateUser(Long allocateUser) {
		this.allocateUser = allocateUser;
	}
	
	/**
	 * @val 处理状态 0 否，1是
	 */
	public Integer getIsOperation() {
		return isOperation;
	}
	
	/**
	 * @val 处理状态 0 否，1是
	 */
	public void setIsOperation(Integer isOperation) {
		this.isOperation = isOperation;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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

	public List getImgs() {
		return imgs;
	}

	public void setImgs(List imgs) {
		this.imgs = imgs;
	}
}