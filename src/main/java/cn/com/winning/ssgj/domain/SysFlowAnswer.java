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

@Alias("sysFlowAnswer")
public class SysFlowAnswer extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long quesId;

    private Long answerPid;

    private String answerCode;

    private String answerContent;
    /**
     * 答案类型 0普通 1其他
     */
    private Integer answerType;

    /**
     * @val 状态 0 失效 1生效
     */
    private Integer status;

    private Long lastUpdator;

    private java.sql.Timestamp lastUpdateTime;

    public SysFlowAnswer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public Long getAnswerPid() {
        return answerPid;
    }

    public void setAnswerPid(Long answerPid) {
        this.answerPid = answerPid;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Long getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(Long lastUpdator) {
        this.lastUpdator = lastUpdator;
    }

    public java.sql.Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(java.sql.Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getAnswerType() {
        return answerType;
    }

    public void setAnswerType(Integer answerType) {
        this.answerType = answerType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}