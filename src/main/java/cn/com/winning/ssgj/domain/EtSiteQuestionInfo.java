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
	 * @val 系统名称 PMIS中菜单名称
	 */
	private String productName;
	
	/**
	 * @val 菜单名称  PMIS中模块名称
	 */
	private String menuName;
	
	/**
	 * @val 问题类型 来源于PMIS
	 * 1|解释类;2|操作错误类;3|系统BUG类;4|新增需求类;5|报表类
	 */
	private Integer questionType;
	
	/**
	 * @val 问题描述
	 */
	private String questionDesc;
	
	/**
	 * @val 问题照片路径
	 */
	private String imgPath;
	
	/**
	 * @val 处理方式  1|现场;2|远程;3|电话
	 * PMIS解决方式
	 */
	private Integer operType;
	
	/**
	 * @val 优先级  来源于PMIS
	 *  1|A级(紧急);2|B级(急);3|C级(一般);4|D级(暂缓)
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

	/**
	 * @val 批次
	 */
	private Integer batchNo;

	/**
	 * @val 原因分类 1|功能;2|基础数据;3|界面;4|其他
	 */
	private Integer reasonType;

	/**
	 * @val 底稿状态：1|新收集;2|登记未答复;3|答复未确认;4|协调过程中;5|编码过程;6|测试过程中;9|测试通过未升级;7|升级未确认;8|已确认关闭
	 */
	private Integer manuscriptStatus;

	/**
	 * @val 工程师工号
	 */
	private String devUser;

	/**
	 * @val 公司方接收人
	 */
	private String devUserName;

	/**
	 * @val 联系人
	 */
	private String linkman;

	/**
	 * @val 联系电话
	 */
	private String mobile;

	/**
	 * @val 希望完成日期
	 */
	private String hopeFinishDate;

	/**
	 * @val 解决日期
	 */
	private String resolveDate;

	/**
	 * @val 解决方式 1|现场;2|远程;3|电话;
	 */
	private Integer solutionType;

	private String solutionResult;

	/**
	 * @val 难度级别 1|高;2|中;3|低
	 */
	private Integer diffcultLevel;

	/**
	 * @val 用户方信息
	 */
	private String userMessage;

	/**
	 * @val 登记人工号
	 */
	private String createNo;

	/**
	 * @val 导入状态 1 导入 2 未导入
	 */
	private Integer pmisStatus;
	/**
	 * @val 工作量
	 */
	private String workLoad;

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
	public Integer getQuestionType() {
		return questionType;
	}
	
	/**
	 * @val 问题类型
	 */
	public void setQuestionType(Integer questionType) {
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


	/**
	 * @val 批次
	 */
	public Integer getBatchNo() {
		return batchNo;
	}

	/**
	 * @val 批次
	 */
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @val 原因分类 1|功能;2|基础数据;3|界面;4|其他
	 */
	public Integer getReasonType() {
		return reasonType;
	}

	/**
	 * @val 原因分类 1|功能;2|基础数据;3|界面;4|其他
	 */
	public void setReasonType(Integer reasonType) {
		this.reasonType = reasonType;
	}

	/**
	 * @val 底稿状态：1|新收集;2|登记未答复;3|答复未确认;4|协调过程中;5|编码过程;6|测试过程中;9|测试通过未升级;7|升级未确认;8|已确认关闭
	 */
	public Integer getManuscriptStatus() {
		return manuscriptStatus;
	}

	/**
	 * @val 底稿状态：1|新收集;2|登记未答复;3|答复未确认;4|协调过程中;5|编码过程;6|测试过程中;9|测试通过未升级;7|升级未确认;8|已确认关闭
	 */
	public void setManuscriptStatus(Integer manuscriptStatus) {
		this.manuscriptStatus = manuscriptStatus;
	}

	/**
	 * @val 工程师工号
	 */
	public String getDevUser() {
		return devUser;
	}

	/**
	 * @val 工程师工号
	 */
	public void setDevUser(String devUser) {
		this.devUser = devUser;
	}

	/**
	 * @val 公司方接收人
	 */
	public String getDevUserName() {
		return devUserName;
	}

	/**
	 * @val 公司方接收人
	 */
	public void setDevUserName(String devUserName) {
		this.devUserName = devUserName;
	}

	/**
	 * @val 联系人
	 */
	public String getLinkman() {
		return linkman;
	}

	/**
	 * @val 联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	/**
	 * @val 联系电话
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @val 联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @val 希望完成日期
	 */
	public String getHopeFinishDate() {
		return hopeFinishDate;
	}

	/**
	 * @val 希望完成日期
	 */
	public void setHopeFinishDate(String hopeFinishDate) {
		this.hopeFinishDate = hopeFinishDate;
	}

	/**
	 * @val 解决日期
	 */
	public String getResolveDate() {
		return resolveDate;
	}

	/**
	 * @val 解决日期
	 */
	public void setResolveDate(String resolveDate) {
		this.resolveDate = resolveDate;
	}

	/**
	 * @val 解决方式 1|现场;2|远程;3|电话;
	 */
	public Integer getSolutionType() {
		return solutionType;
	}

	/**
	 * @val 解决方式 1|现场;2|远程;3|电话;
	 */
	public void setSolutionType(Integer solutionType) {
		this.solutionType = solutionType;
	}

	public String getSolutionResult() {
		return solutionResult;
	}

	public void setSolutionResult(String solutionResult) {
		this.solutionResult = solutionResult;
	}

	/**
	 * @val 难度级别 1|高;2|中;3|低
	 */
	public Integer getDiffcultLevel() {
		return diffcultLevel;
	}

	/**
	 * @val 难度级别 1|高;2|中;3|低
	 */
	public void setDiffcultLevel(Integer diffcultLevel) {
		this.diffcultLevel = diffcultLevel;
	}

	/**
	 * @val 用户方信息
	 */
	public String getUserMessage() {
		return userMessage;
	}

	/**
	 * @val 用户方信息
	 */
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	/**
	 * @val 登记人工号
	 */
	public String getCreateNo() {
		return createNo;
	}

	/**
	 * @val 登记人工号
	 */
	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}

	/**
	 * @val 导入状态 1 导入 2 未导入
	 */
	public Integer getPmisStatus() {
		return pmisStatus;
	}

	/**
	 * @val 导入状态 1 导入 2 未导入
	 */
	public void setPmisStatus(Integer pmisStatus) {
		this.pmisStatus = pmisStatus;
	}

	public String getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(String workLoad) {
		this.workLoad = workLoad;
	}
}