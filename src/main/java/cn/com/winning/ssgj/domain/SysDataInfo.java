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

@Alias("sysDataInfo")
public class SysDataInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String tableCode;

    private String dbName;

    private String tableName;

    private String tableCnName;

    private String standardCode;

    private String standardCnName;

    private Integer dataType;

    private Integer isEasy;

    private String tableAttention;

    private Integer tableCount;

    private Long lastUpdator;

    private Date lastUpdateName;

    public SysDataInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCnName() {
        return tableCnName;
    }

    public void setTableCnName(String tableCnName) {
        this.tableCnName = tableCnName;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getStandardCnName() {
        return standardCnName;
    }

    public void setStandardCnName(String standardCnName) {
        this.standardCnName = standardCnName;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getIsEasy() {
        return isEasy;
    }

    public void setIsEasy(Integer isEasy) {
        this.isEasy = isEasy;
    }

    public String getTableAttention() {
        return tableAttention;
    }

    public void setTableAttention(String tableAttention) {
        this.tableAttention = tableAttention;
    }

    public Integer getTableCount() {
        return tableCount;
    }

    public void setTableCount(Integer tableCount) {
        this.tableCount = tableCount;
    }

    public Long getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(Long lastUpdator) {
        this.lastUpdator = lastUpdator;
    }

    public Date getLastUpdateName() {
        return lastUpdateName;
    }

    public void setLastUpdateName(Date lastUpdateName) {
        this.lastUpdateName = lastUpdateName;
    }

}