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

@Alias("etReport")
public class EtReport extends BaseDomain implements Serializable {

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
     * @val 报表分类 0 凭条;1 发票;2 缴款;3 缴款单据;4 单据;5 台账;6 处方医嘱;7 申请单;8 治疗单据;9 医疗文书;10 临时医嘱;11 报告调阅;12 巡视单;13 报表;
     */
    private Integer reportType;

    /**
     * @val 票据&报表名称
     */
    private String reportName;

    /**
     * @val 是否本期范围：0 否   1 是
     */
    private Integer isScope;

    /**
     * @val 不在实施范围原因
     */
    private String noScopeCode;

    /**
     * @val 要求
     */
    private String require;

    /**
     * @val 状态： 0 待审核（新增）1：通过 2:未通过
     */
    private Integer status;

    /**
     * @val 图片路径
     */
    private String imgPath;

    /**
     * @val 备注
     */
    private String remark;

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

    public EtReport() {

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

    public Long getcId() {
        return cId;
    }

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
     * @val 报表分类 0 凭条;1 发票;2 缴款;3 缴款单据;4 单据;5 台账;6 处方医嘱;7 申请单;8 治疗单据;9 医疗文书;10 临时医嘱;11 报告调阅;12 巡视单;13 报表;
     */
    public Integer getReportType() {
        return reportType;
    }

    /**
     * @val 报表分类 0 凭条;1 发票;2 缴款;3 缴款单据;4 单据;5 台账;6 处方医嘱;7 申请单;8 治疗单据;9 医疗文书;10 临时医嘱;11 报告调阅;12 巡视单;13 报表;
     */
    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    /**
     * @val 票据&报表名称
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * @val 票据&报表名称
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
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
     * @val 要求
     */
    public String getRequire() {
        return require;
    }

    /**
     * @val 要求
     */
    public void setRequire(String require) {
        this.require = require;
    }

    /**
     * @val 状态： 0 待审核（新增）1：通过 2:未通过
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @val 状态： 0 待审核（新增）1：通过 2:未通过
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @val 图片路径
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @val 图片路径
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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