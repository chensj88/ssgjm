package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:46
 */

@Alias("sysFlowInfo")
public class SysFlowInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * @val ID
     */
    private Long id;

    /**
     * @val 流程类型 0 大类，1小类
     */
    private String flowType;

    /**
     * @val 流程上级序号
     */
    private Long flowPid;

    /**
     * @val 流程编号
     */
    private String flowCode;

    /**
     * @val 流程名称
     */
    private String flowName;

    /**
     * @val 流程描述
     */
    private String flowDesc;

    /**
     * @val 状态 0 失效 1生效
     */
    private Integer status;

    /**
     * @val 维护人员
     */
    private Long lastUpdator;

    /**
     * @val 维护时间
     */
    private java.sql.Timestamp lastUpdateTime;
    /**
     * @val 流程调研报告上传地址
     */
    private String remotePath;
    /**
     * @val 是否必要 0 否，1 是
     */
    private Integer isMust;
    /**
     * @val 配置详细说明
     */
    private String contentDesc;
    /**
     * @val 配置实现SQL
     */
    private String configSQL;

    /**
     * @val 存储名称
     */
    private String procName;

    /**
     * @val 存储运行参数
     */
    private String dbType;

    public SysFlowInfo() {

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
     * @val 流程类型 0 大类，1小类
     */
    public String getFlowType() {
        return flowType;
    }

    /**
     * @val 流程类型 0 大类，1小类
     */
    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    /**
     * @val 流程上级序号
     */
    public Long getFlowPid() {
        return flowPid;
    }

    /**
     * @val 流程上级序号
     */
    public void setFlowPid(Long flowPid) {
        this.flowPid = flowPid;
    }

    /**
     * @val 流程编号
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * @val 流程编号
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    /**
     * @val 流程名称
     */
    public String getFlowName() {
        return flowName;
    }

    /**
     * @val 流程名称
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    /**
     * @val 流程描述
     */
    public String getFlowDesc() {
        return flowDesc;
    }

    /**
     * @val 流程描述
     */
    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc;
    }

    /**
     * @val 状态 0 失效 1生效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @val 状态 0 失效 1生效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @val 维护人员
     */
    public Long getLastUpdator() {
        return lastUpdator;
    }

    /**
     * @val 维护人员
     */
    public void setLastUpdator(Long lastUpdator) {
        this.lastUpdator = lastUpdator;
    }

    /**
     * @val 维护时间
     */
    public java.sql.Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @val 维护时间
     */
    public void setLastUpdateTime(java.sql.Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     *  上级流程代码
     */
     private String  flowParentCode;

     /**
      * 上级流程名称
      */
      private String flowParentName ;


    public String getFlowParentCode() {
        return flowParentCode;
    }

    public void setFlowParentCode(String flowParentCode) {
        this.flowParentCode = flowParentCode;
    }

    public String getFlowParentName() {
        return flowParentName;
    }

    public void setFlowParentName(String flowParentName) {
        this.flowParentName = flowParentName;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public Integer getIsMust() {
        return isMust;
    }

    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getConfigSQL() {
        return configSQL;
    }

    public void setConfigSQL(String configSQL) {
        this.configSQL = configSQL;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public String toString() {
        return "SysFlowInfo{" +
                "id=" + id +
                ", flowType='" + flowType + '\'' +
                ", flowPid=" + flowPid +
                ", flowCode='" + flowCode + '\'' +
                ", flowName='" + flowName + '\'' +
                ", flowDesc='" + flowDesc + '\'' +
                ", lastUpdator=" + lastUpdator +
                ", lastUpdateTime=" + lastUpdateTime +
                ", flowParentCode='" + flowParentCode + '\'' +
                ", flowParentName='" + flowParentName + '\'' +
                '}';
    }
}