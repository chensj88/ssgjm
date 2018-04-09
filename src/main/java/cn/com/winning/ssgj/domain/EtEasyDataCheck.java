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

@Alias("etEasyDataCheck")
public class EtEasyDataCheck extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

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
     * @val 数据库名称
     */
    private String databaseName;

    /**
     * @val 表名
     */
    private String tableName;

    /**
     * @val 含义
     */
    private String meaning;

    /**
     * @val 是否维护:0 否 1是
     */
    private Integer isUse;

    /**
     * @val 完成情况
     */
    private String content;

    /**
     * @val 脚本路径
     */
    private String scriptPath;

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
     * @val 产品线ID
     */
    private Long plId;

    /**
     * @val 是否本期实施范围
     */
    private Integer isScope;

    /**
     * @val 不在实施范围原因
     */
    private String noScopeCode;


    public Integer getIsScope() {
        return isScope;
    }

    public void setIsScope(Integer isScope) {
        this.isScope = isScope;
    }

    public String getNoScopeCode() {
        return noScopeCode;
    }

    public void setNoScopeCode(String noScopeCode) {
        this.noScopeCode = noScopeCode;
    }

    public Long getPlId() {
        return plId;
    }

    public void setPlId(Long plId) {
        this.plId = plId;
    }

    public EtEasyDataCheck() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @val 合同ID
     */
    public Long getcId() {
        return cId;
    }


    /**
     * @val 合同ID
     */
    public void setcId(Long cId) {
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
     * @val 数据库名称
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @val 数据库名称
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * @val 表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @val 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @val 含义
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * @val 含义
     */
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    /**
     * @val 是否维护:0 否 1是
     */
    public Integer getIsUse() {
        return isUse;
    }

    /**
     * @val 是否维护:0 否 1是
     */
    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    /**
     * @val 完成情况
     */
    public String getContent() {
        return content;
    }

    /**
     * @val 完成情况
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @val 脚本路径
     */
    public String getScriptPath() {
        return scriptPath;
    }

    /**
     * @val 脚本路径
     */
    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
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