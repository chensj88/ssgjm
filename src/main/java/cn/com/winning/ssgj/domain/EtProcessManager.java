package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:34
 */
 
@Alias("etProcessManager")
public class EtProcessManager extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	private Long cId;
	
	private Long pmId;

	/**
	 * @val 项目开始 0未开始，1结束，2异常
	 */
	private Integer isStart;
	
	/**
	 * @val 项目接单 0未开始，1结束，2异常
	 */
	private Integer isAccept;
	
	/**
	 * @val 完善项目信息 0未开始，1结束，2异常
	 */
	private Integer isImprove;
	
	/**
	 * @val  测试硬件清单制作 0未开始，1结束，2异常
	 */
	private Integer isTestHardware;
	
	/**
	 * @val 项目人员入场 0未开始，1结束，2异常
	 */
	private Integer isPmEntrance;

	/**
	 * @val 实施计划制定 0未开始，1结束，2异常
	 */
	private Integer isEtPlan;

	/**
	 * @val  测试环境搭建 0未开始，1结束，2异常
	 */
	private Integer isCreateTestEnv;
	
	/**
	 * @val 项目范围确认 0未开始，1结束，2异常
	 */
	private Integer isPmScope;
	
	/**
	 * @val 项目启动会 0未开始，1结束，2异常
	 */
	private Integer isPmStartMeeting;

	/**
	 * @val 系统模拟运行 0未开始，1结束，2异常，3实施中
	 */
	private Integer isSimulation;

	/**
	 * @val 培训客户&考核 0未开始，1结束，2异常，3实施中
	 */
	private Integer isTraining;

	/**
	 * @val 安装站点软硬件 0未开始，1结束，2异常，3实施中
	 */
	private Integer isSiteInstall;  //15

	/**
	 * @val 校验易用数据 0未开始，1结束，2异常，3实施中
	 */
	private Integer isEasyDataCheck; //16

	/**
	 * @val 校验基础数据 0未开始，1结束，2异常，3实施中
	 */
	private Integer isBasicDataCheck;

	/**
	 * @val 基础数据维护 0未开始，1结束，2异常，3实施中
	 */
	private Integer isBasicDataUse;

	/**
	 * @val 易用数据维护 0未开始，1结束，2异常，3实施中
	 */
	private Integer isEasyDataUse;

	/**
	 * @val 流程调研与配置 0未开始，1结束，2异常，3实施中
	 */
	private Integer isFlowConfig;

	/**
	 * @val 接口开发与交付 0未开始，1结束，2异常，3实施中
	 */
	private Integer isInterfaceDev;

	/**
	 * @val 单据报表&交付 0未开始，1结束，2异常，3实施中
	 */
	private Integer isPaperDev;

	/**
	 * @val 流程数量确认 0未开始，1结束，2异常，3实施中
	 */
	private Integer isFlowAffirm;

	/**
	 * @val 接口数量确认 0未开始，1结束，2异常，3实施中
	 */
	private Integer isInterfaceAffirm;

	/**
	 * @val 报表类确认 0未开始，1结束，2异常，3实施中
	 */
	private Integer isPaperAffirm; //25

	/**
	 * @val 硬件设施清单 0未开始，1结束，2异常，3实施中
	 */
	private Integer isHardwareList;

	/**
	 * @val 评估上线可行性 0未开始，1结束，2异常，3实施中
	 */
	private Integer isOnline;
	
	/**
	 * @val 审批切换方案 0未开始，1结束，2异常，3实施中
	 */
	private Integer isSwitchPlan;
	
	/**
	 * @val 安排人员到岗 0未开始，1结束，2异常，3实施中
	 */
	private Integer isSupportStaff;
	
	/**
	 * @val 项目切换 0未开始，1结束，2异常，3实施中
	 */
	private Integer isEnd;

	/**
	 * @val 客户ID
	 */
	private String serialNo;

	/**
	 * @val 创建人员
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
	
	public EtProcessManager() {

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
	
	public Long getCId() {
		return cId;
	}
	
	public void setCId(Long cId) {
		this.cId = cId;
	}
	
	public Long getPmId() {
		return pmId;
	}
	
	public void setPmId(Long pmId) {
		this.pmId = pmId;
	}
	
	/**
	 * @val 项目开始 0未开始，1结束
	 */
	public Integer getIsStart() {
		return isStart;
	}
	
	/**
	 * @val 项目开始 0未开始，1结束
	 */
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	
	/**
	 * @val 项目接单 0未开始，1结束
	 */
	public Integer getIsAccept() {
		return isAccept;
	}
	
	/**
	 * @val 项目接单 0未开始，1结束
	 */
	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}
	
	/**
	 * @val 完善项目信息 0未开始，1结束
	 */
	public Integer getIsImprove() {
		return isImprove;
	}
	
	/**
	 * @val 完善项目信息 0未开始，1结束
	 */
	public void setIsImprove(Integer isImprove) {
		this.isImprove = isImprove;
	}
	
	/**
	 * @val  测试硬件清单制作 0未开始，1结束
	 */
	public Integer getIsTestHardware() {
		return isTestHardware;
	}
	
	/**
	 * @val  测试硬件清单制作 0未开始，1结束
	 */
	public void setIsTestHardware(Integer isTestHardware) {
		this.isTestHardware = isTestHardware;
	}
	
	/**
	 * @val 项目人员入场 0未开始，1结束
	 */
	public Integer getIsPmEntrance() {
		return isPmEntrance;
	}
	
	/**
	 * @val 项目人员入场 0未开始，1结束
	 */
	public void setIsPmEntrance(Integer isPmEntrance) {
		this.isPmEntrance = isPmEntrance;
	}
	
	/**
	 * @val  测试环境搭建 0未开始，1结束
	 */
	public Integer getIsCreateTestEnv() {
		return isCreateTestEnv;
	}
	
	/**
	 * @val  测试环境搭建 0未开始，1结束
	 */
	public void setIsCreateTestEnv(Integer isCreateTestEnv) {
		this.isCreateTestEnv = isCreateTestEnv;
	}
	
	/**
	 * @val 项目范围确认 0未开始，1结束
	 */
	public Integer getIsPmScope() {
		return isPmScope;
	}
	
	/**
	 * @val 项目范围确认 0未开始，1结束
	 */
	public void setIsPmScope(Integer isPmScope) {
		this.isPmScope = isPmScope;
	}
	
	/**
	 * @val 实施计划制定 0未开始，1结束
	 */
	public Integer getIsEtPlan() {
		return isEtPlan;
	}
	
	/**
	 * @val 实施计划制定 0未开始，1结束
	 */
	public void setIsEtPlan(Integer isEtPlan) {
		this.isEtPlan = isEtPlan;
	}
	
	/**
	 * @val 项目启动会 0未开始，1结束
	 */
	public Integer getIsPmStartMeeting() {
		return isPmStartMeeting;
	}
	
	/**
	 * @val 项目启动会 0未开始，1结束
	 */
	public void setIsPmStartMeeting(Integer isPmStartMeeting) {
		this.isPmStartMeeting = isPmStartMeeting;
	}
	
	/**
	 * @val 基础数据维护 0未开始，1结束
	 */
	public Integer getIsBasicDataUse() {
		return isBasicDataUse;
	}
	
	/**
	 * @val 基础数据维护 0未开始，1结束
	 */
	public void setIsBasicDataUse(Integer isBasicDataUse) {
		this.isBasicDataUse = isBasicDataUse;
	}
	
	/**
	 * @val 易用数据维护 0未开始，1结束
	 */
	public Integer getIsEasyDataUse() {
		return isEasyDataUse;
	}
	
	/**
	 * @val 易用数据维护 0未开始，1结束
	 */
	public void setIsEasyDataUse(Integer isEasyDataUse) {
		this.isEasyDataUse = isEasyDataUse;
	}
	
	/**
	 * @val 基础数据校验 0未开始，1结束
	 */
	public Integer getIsBasicDataCheck() {
		return isBasicDataCheck;
	}
	
	/**
	 * @val 基础数据校验 0未开始，1结束
	 */
	public void setIsBasicDataCheck(Integer isBasicDataCheck) {
		this.isBasicDataCheck = isBasicDataCheck;
	}
	
	/**
	 * @val 易用数据校验 0未开始，1结束
	 */
	public Integer getIsEasyDataCheck() {
		return isEasyDataCheck;
	}
	
	/**
	 * @val 易用数据校验 0未开始，1结束
	 */
	public void setIsEasyDataCheck(Integer isEasyDataCheck) {
		this.isEasyDataCheck = isEasyDataCheck;
	}
	
	/**
	 * @val 流程数量确认 0未开始，1结束
	 */
	public Integer getIsFlowAffirm() {
		return isFlowAffirm;
	}
	
	/**
	 * @val 流程数量确认 0未开始，1结束
	 */
	public void setIsFlowAffirm(Integer isFlowAffirm) {
		this.isFlowAffirm = isFlowAffirm;
	}
	
	/**
	 * @val 流程调研与配置 0未开始，1结束
	 */
	public Integer getIsFlowConfig() {
		return isFlowConfig;
	}
	
	/**
	 * @val 流程调研与配置 0未开始，1结束
	 */
	public void setIsFlowConfig(Integer isFlowConfig) {
		this.isFlowConfig = isFlowConfig;
	}
	
	/**
	 * @val 接口数量确认 0未开始，1结束
	 */
	public Integer getIsInterfaceAffirm() {
		return isInterfaceAffirm;
	}
	
	/**
	 * @val 接口数量确认 0未开始，1结束
	 */
	public void setIsInterfaceAffirm(Integer isInterfaceAffirm) {
		this.isInterfaceAffirm = isInterfaceAffirm;
	}
	
	/**
	 * @val 接口开发与交付 0未开始，1结束
	 */
	public Integer getIsInterfaceDev() {
		return isInterfaceDev;
	}
	
	/**
	 * @val 接口开发与交付 0未开始，1结束
	 */
	public void setIsInterfaceDev(Integer isInterfaceDev) {
		this.isInterfaceDev = isInterfaceDev;
	}
	
	/**
	 * @val 报表类确认 0未开始，1结束
	 */
	public Integer getIsPaperAffirm() {
		return isPaperAffirm;
	}
	
	/**
	 * @val 报表类确认 0未开始，1结束
	 */
	public void setIsPaperAffirm(Integer isPaperAffirm) {
		this.isPaperAffirm = isPaperAffirm;
	}
	
	/**
	 * @val 报表类开发与交付 0未开始，1结束
	 */
	public Integer getIsPaperDev() {
		return isPaperDev;
	}
	
	/**
	 * @val 报表类开发与交付 0未开始，1结束
	 */
	public void setIsPaperDev(Integer isPaperDev) {
		this.isPaperDev = isPaperDev;
	}
	
	/**
	 * @val 硬件设施清单 0未开始，1结束
	 */
	public Integer getIsHardwareList() {
		return isHardwareList;
	}
	
	/**
	 * @val 硬件设施清单 0未开始，1结束
	 */
	public void setIsHardwareList(Integer isHardwareList) {
		this.isHardwareList = isHardwareList;
	}
	
	/**
	 * @val 站点设施安装 0未开始，1结束
	 */
	public Integer getIsSiteInstall() {
		return isSiteInstall;
	}
	
	/**
	 * @val 站点设施安装 0未开始，1结束
	 */
	public void setIsSiteInstall(Integer isSiteInstall) {
		this.isSiteInstall = isSiteInstall;
	}
	
	/**
	 * @val 用户培训与考核 0未开始，1结束
	 */
	public Integer getIsTraining() {
		return isTraining;
	}
	
	/**
	 * @val 用户培训与考核 0未开始，1结束
	 */
	public void setIsTraining(Integer isTraining) {
		this.isTraining = isTraining;
	}
	
	/**
	 * @val 系统模拟运行 0未开始，1结束
	 */
	public Integer getIsSimulation() {
		return isSimulation;
	}
	
	/**
	 * @val 系统模拟运行 0未开始，1结束
	 */
	public void setIsSimulation(Integer isSimulation) {
		this.isSimulation = isSimulation;
	}
	
	/**
	 * @val 上线可行性 0未开始，1结束
	 */
	public Integer getIsOnline() {
		return isOnline;
	}
	
	/**
	 * @val 上线可行性 0未开始，1结束
	 */
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	
	/**
	 * @val 切换方案 0未开始，1结束
	 */
	public Integer getIsSwitchPlan() {
		return isSwitchPlan;
	}
	
	/**
	 * @val 切换方案 0未开始，1结束
	 */
	public void setIsSwitchPlan(Integer isSwitchPlan) {
		this.isSwitchPlan = isSwitchPlan;
	}
	
	/**
	 * @val 支持人员 0未开始，1结束
	 */
	public Integer getIsSupportStaff() {
		return isSupportStaff;
	}
	
	/**
	 * @val 支持人员 0未开始，1结束
	 */
	public void setIsSupportStaff(Integer isSupportStaff) {
		this.isSupportStaff = isSupportStaff;
	}
	
	/**
	 * @val 项目切换 0未开始，1结束
	 */
	public Integer getIsEnd() {
		return isEnd;
	}
	
	/**
	 * @val 项目切换 0未开始，1结束
	 */
	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}

	/**
	 * @val 客户ID
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @val 客户ID
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @val 创建人员
	 */
	public Long getCreator() {
		return creator;
	}
	
	/**
	 * @val 创建人员
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