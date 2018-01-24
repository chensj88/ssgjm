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

@Alias("etProcessManager")
public class EtProcessManager extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long cId;

    private Long pmId;

    private Integer isStart;

    private Integer isAccept;

    private Integer isImprove;

    private Integer isTestHardware;

    private Integer isPmEntrance;

    private Integer isCreateTestEnv;

    private Integer isPmScope;

    private Integer isEtPlan;

    private Integer isPmStartMeeting;

    private Integer isBasicDataUse;

    private Integer isEasyDataUse;

    private Integer isBasicDataCheck;

    private Integer isEasyDataCheck;

    private Integer isFlowAffirm;

    private Integer isFlowConfig;

    private Integer isInterfaceAffirm;

    private Integer isInterfaceDev;

    private Integer isPaperAffirm;

    private Integer isPaperDev;

    private Integer isHardwareList;

    private Integer isSiteInstall;

    private Integer isTraining;

    private Integer isSimulation;

    private Integer isOnline;

    private Integer isSwitchPlan;

    private Integer isSupportStaff;

    private Integer isEnd;

    private Long creator;

    private Date createTime;

    private Long operator;

    private Date operatorTime;

    public EtProcessManager() {

    }

    public Long getId() {
        return id;
    }

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

    public Integer getIsStart() {
        return isStart;
    }

    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }

    public Integer getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(Integer isAccept) {
        this.isAccept = isAccept;
    }

    public Integer getIsImprove() {
        return isImprove;
    }

    public void setIsImprove(Integer isImprove) {
        this.isImprove = isImprove;
    }

    public Integer getIsTestHardware() {
        return isTestHardware;
    }

    public void setIsTestHardware(Integer isTestHardware) {
        this.isTestHardware = isTestHardware;
    }

    public Integer getIsPmEntrance() {
        return isPmEntrance;
    }

    public void setIsPmEntrance(Integer isPmEntrance) {
        this.isPmEntrance = isPmEntrance;
    }

    public Integer getIsCreateTestEnv() {
        return isCreateTestEnv;
    }

    public void setIsCreateTestEnv(Integer isCreateTestEnv) {
        this.isCreateTestEnv = isCreateTestEnv;
    }

    public Integer getIsPmScope() {
        return isPmScope;
    }

    public void setIsPmScope(Integer isPmScope) {
        this.isPmScope = isPmScope;
    }

    public Integer getIsEtPlan() {
        return isEtPlan;
    }

    public void setIsEtPlan(Integer isEtPlan) {
        this.isEtPlan = isEtPlan;
    }

    public Integer getIsPmStartMeeting() {
        return isPmStartMeeting;
    }

    public void setIsPmStartMeeting(Integer isPmStartMeeting) {
        this.isPmStartMeeting = isPmStartMeeting;
    }

    public Integer getIsBasicDataUse() {
        return isBasicDataUse;
    }

    public void setIsBasicDataUse(Integer isBasicDataUse) {
        this.isBasicDataUse = isBasicDataUse;
    }

    public Integer getIsEasyDataUse() {
        return isEasyDataUse;
    }

    public void setIsEasyDataUse(Integer isEasyDataUse) {
        this.isEasyDataUse = isEasyDataUse;
    }

    public Integer getIsBasicDataCheck() {
        return isBasicDataCheck;
    }

    public void setIsBasicDataCheck(Integer isBasicDataCheck) {
        this.isBasicDataCheck = isBasicDataCheck;
    }

    public Integer getIsEasyDataCheck() {
        return isEasyDataCheck;
    }

    public void setIsEasyDataCheck(Integer isEasyDataCheck) {
        this.isEasyDataCheck = isEasyDataCheck;
    }

    public Integer getIsFlowAffirm() {
        return isFlowAffirm;
    }

    public void setIsFlowAffirm(Integer isFlowAffirm) {
        this.isFlowAffirm = isFlowAffirm;
    }

    public Integer getIsFlowConfig() {
        return isFlowConfig;
    }

    public void setIsFlowConfig(Integer isFlowConfig) {
        this.isFlowConfig = isFlowConfig;
    }

    public Integer getIsInterfaceAffirm() {
        return isInterfaceAffirm;
    }

    public void setIsInterfaceAffirm(Integer isInterfaceAffirm) {
        this.isInterfaceAffirm = isInterfaceAffirm;
    }

    public Integer getIsInterfaceDev() {
        return isInterfaceDev;
    }

    public void setIsInterfaceDev(Integer isInterfaceDev) {
        this.isInterfaceDev = isInterfaceDev;
    }

    public Integer getIsPaperAffirm() {
        return isPaperAffirm;
    }

    public void setIsPaperAffirm(Integer isPaperAffirm) {
        this.isPaperAffirm = isPaperAffirm;
    }

    public Integer getIsPaperDev() {
        return isPaperDev;
    }

    public void setIsPaperDev(Integer isPaperDev) {
        this.isPaperDev = isPaperDev;
    }

    public Integer getIsHardwareList() {
        return isHardwareList;
    }

    public void setIsHardwareList(Integer isHardwareList) {
        this.isHardwareList = isHardwareList;
    }

    public Integer getIsSiteInstall() {
        return isSiteInstall;
    }

    public void setIsSiteInstall(Integer isSiteInstall) {
        this.isSiteInstall = isSiteInstall;
    }

    public Integer getIsTraining() {
        return isTraining;
    }

    public void setIsTraining(Integer isTraining) {
        this.isTraining = isTraining;
    }

    public Integer getIsSimulation() {
        return isSimulation;
    }

    public void setIsSimulation(Integer isSimulation) {
        this.isSimulation = isSimulation;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getIsSwitchPlan() {
        return isSwitchPlan;
    }

    public void setIsSwitchPlan(Integer isSwitchPlan) {
        this.isSwitchPlan = isSwitchPlan;
    }

    public Integer getIsSupportStaff() {
        return isSupportStaff;
    }

    public void setIsSupportStaff(Integer isSupportStaff) {
        this.isSupportStaff = isSupportStaff;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
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