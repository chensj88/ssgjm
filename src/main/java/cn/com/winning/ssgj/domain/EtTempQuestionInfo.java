package cn.com.winning.ssgj.domain;

import java.io.Serializable; 

import org.apache.ibatis.type.Alias; 

import cn.com.winning.ssgj.domain.BaseDomain;



/**
 * @author chensj
 * @title 
 * @email chensj@163.com
 * @package cn.com.winning.ssgj.domain
 * @date 2018-23-04 17:23:21
 */
@Alias("etTempQuestionInfo")
public class EtTempQuestionInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 字段名：ID
     * 备注: 
     * 默认值：无
     */
    private Long id;
    /**
     * 字段名：C_ID
     * 备注: 
     * 默认值：无
     */
    private Long cId;
    /**
     * 字段名：PM_ID
     * 备注: 
     * 默认值：无
     */
    private Long pmId;
    /**
     * 字段名：SERIAL_NO
     * 备注: 
     * 默认值：无
     */
    private String serialNo;
    /**
     * 字段名：PRIORITY_TYPE
     * 备注: 
     * 默认值：无
     */
    private String priorityType;
    /**
     * 字段名：PRIORITY
     * 备注: 
     * 默认值：无
     */
    private Integer priority;
    /**
     * 字段名：SITE_NAME
     * 备注: 
     * 默认值：无
     */
    private String siteName;
    /**
     * 字段名：SITE_ID
     * 备注: 
     * 默认值：无
     */
    private String siteId;
    /**
     * 字段名：PRODUCT_NAME
     * 备注: 
     * 默认值：无
     */
    private String productName;
    /**
     * 字段名：PRODUCT_ID
     * 备注: 
     * 默认值：无
     */
    private String productId;
    /**
     * 字段名：MENU_NAME
     * 备注: 
     * 默认值：无
     */
    private String menuName;
    /**
     * 字段名：QUESTION_DESC
     * 备注: 
     * 默认值：无
     */
    private String questionDesc;
    /**
     * 字段名：QUESTION_VAR
     * 备注: 
     * 默认值：无
     */
    private String questionVar;
    /**
     * 字段名：QUESTION_TYPE
     * 备注: 
     * 默认值：无
     */
    private Integer questionType;
    /**
     * 字段名：REASON_VAR
     * 备注: 
     * 默认值：无
     */
    private String reasonVar;
    /**
     * 字段名：REASON_TYPE
     * 备注: 
     * 默认值：无
     */
    private Integer reasonType;
    /**
     * 字段名：MANUSCRIPT_VAR
     * 备注: 
     * 默认值：无
     */
    private String manuscriptVar;
    /**
     * 字段名：MANUSCRIPT_STATUS
     * 备注: 
     * 默认值：无
     */
    private Integer manuscriptStatus;
    /**
     * 字段名：DIFFCULT_VAR
     * 备注: 
     * 默认值：无
     */
    private String diffcultVar;
    /**
     * 字段名：DIFFCULT_LEVEL
     * 备注: 
     * 默认值：无
     */
    private Integer diffcultLevel;
    /**
     * 字段名：DEV_USER
     * 备注: 
     * 默认值：无
     */
    private String devUser;
    /**
     * 字段名：DEV_USER_NAME
     * 备注: 
     * 默认值：无
     */
    private String devUserName;
    /**
     * 字段名：INTRODUCER
     * 备注: 
     * 默认值：无
     */
    private String introducer;
    /**
     * 字段名：INTRODUCER_NAME
     * 备注: 
     * 默认值：无
     */
    private String introducerName;
    /**
     * 字段名：INTRODUCER_DATE
     * 备注: 
     * 默认值：无
     */
    private String introducerDate;
    /**
     * 字段名：LINKMAN
     * 备注: 
     * 默认值：无
     */
    private String linkman;
    /**
     * 字段名：MOBILE
     * 备注: 
     * 默认值：无
     */
    private String mobile;
    /**
     * 字段名：OPER_VAR
     * 备注: 
     * 默认值：无
     */
    private String operVar;
    /**
     * 字段名：OPER_TYPE
     * 备注: 
     * 默认值：无
     */
    private Integer operType;
    /**
     * 字段名：HOPE_FINISH_DATE
     * 备注: 
     * 默认值：无
     */
    private String hopeFinishDate;
    /**
     * 字段名：USER_MESSAGE
     * 备注: 
     * 默认值：无
     */
    private String userMessage;
    /**
     * 字段名：REQUIREMENT_NO
     * 备注: 
     * 默认值：无
     */
    private String requirementNo;

    public EtTempQuestionInfo (){

    }

   /**
   * 字段名：ID
   * get方法
   * 备注: 
   */
   public Long getId(){

        return id;
   }

   /**
   * 字段名：ID
   * set方法
   * 备注: 
   */
   public void setId(Long id){
        this.id = id;
   }
   /**
   * 字段名：C_ID
   * get方法
   * 备注: 
   */
   public Long getCId(){

        return cId;
   }

   /**
   * 字段名：C_ID
   * set方法
   * 备注: 
   */
   public void setCId(Long cId){
        this.cId = cId;
   }
   /**
   * 字段名：PM_ID
   * get方法
   * 备注: 
   */
   public Long getPmId(){

        return pmId;
   }

   /**
   * 字段名：PM_ID
   * set方法
   * 备注: 
   */
   public void setPmId(Long pmId){
        this.pmId = pmId;
   }
   /**
   * 字段名：SERIAL_NO
   * get方法
   * 备注: 
   */
   public String getSerialNo(){

        return serialNo;
   }

   /**
   * 字段名：SERIAL_NO
   * set方法
   * 备注: 
   */
   public void setSerialNo(String serialNo){
        this.serialNo = serialNo;
   }
   /**
   * 字段名：PRIORITY_TYPE
   * get方法
   * 备注: 
   */
   public String getPriorityType(){

        return priorityType;
   }

   /**
   * 字段名：PRIORITY_TYPE
   * set方法
   * 备注: 
   */
   public void setPriorityType(String priorityType){
        this.priorityType = priorityType;
   }
   /**
   * 字段名：PRIORITY
   * get方法
   * 备注: 
   */
   public Integer getPriority(){

        return priority;
   }

   /**
   * 字段名：PRIORITY
   * set方法
   * 备注: 
   */
   public void setPriority(Integer priority){
        this.priority = priority;
   }
   /**
   * 字段名：SITE_NAME
   * get方法
   * 备注: 
   */
   public String getSiteName(){

        return siteName;
   }

   /**
   * 字段名：SITE_NAME
   * set方法
   * 备注: 
   */
   public void setSiteName(String siteName){
        this.siteName = siteName;
   }
   /**
   * 字段名：SITE_ID
   * get方法
   * 备注: 
   */
   public String getSiteId(){

        return siteId;
   }

   /**
   * 字段名：SITE_ID
   * set方法
   * 备注: 
   */
   public void setSiteId(String siteId){
        this.siteId = siteId;
   }
   /**
   * 字段名：PRODUCT_NAME
   * get方法
   * 备注: 
   */
   public String getProductName(){

        return productName;
   }

   /**
   * 字段名：PRODUCT_NAME
   * set方法
   * 备注: 
   */
   public void setProductName(String productName){
        this.productName = productName;
   }
   /**
   * 字段名：PRODUCT_ID
   * get方法
   * 备注: 
   */
   public String getProductId(){

        return productId;
   }

   /**
   * 字段名：PRODUCT_ID
   * set方法
   * 备注: 
   */
   public void setProductId(String productId){
        this.productId = productId;
   }
   /**
   * 字段名：MENU_NAME
   * get方法
   * 备注: 
   */
   public String getMenuName(){

        return menuName;
   }

   /**
   * 字段名：MENU_NAME
   * set方法
   * 备注: 
   */
   public void setMenuName(String menuName){
        this.menuName = menuName;
   }
   /**
   * 字段名：QUESTION_DESC
   * get方法
   * 备注: 
   */
   public String getQuestionDesc(){

        return questionDesc;
   }

   /**
   * 字段名：QUESTION_DESC
   * set方法
   * 备注: 
   */
   public void setQuestionDesc(String questionDesc){
        this.questionDesc = questionDesc;
   }
   /**
   * 字段名：QUESTION_VAR
   * get方法
   * 备注: 
   */
   public String getQuestionVar(){

        return questionVar;
   }

   /**
   * 字段名：QUESTION_VAR
   * set方法
   * 备注: 
   */
   public void setQuestionVar(String questionVar){
        this.questionVar = questionVar;
   }
   /**
   * 字段名：QUESTION_TYPE
   * get方法
   * 备注: 
   */
   public Integer getQuestionType(){

        return questionType;
   }

   /**
   * 字段名：QUESTION_TYPE
   * set方法
   * 备注: 
   */
   public void setQuestionType(Integer questionType){
        this.questionType = questionType;
   }
   /**
   * 字段名：REASON_VAR
   * get方法
   * 备注: 
   */
   public String getReasonVar(){

        return reasonVar;
   }

   /**
   * 字段名：REASON_VAR
   * set方法
   * 备注: 
   */
   public void setReasonVar(String reasonVar){
        this.reasonVar = reasonVar;
   }
   /**
   * 字段名：REASON_TYPE
   * get方法
   * 备注: 
   */
   public Integer getReasonType(){

        return reasonType;
   }

   /**
   * 字段名：REASON_TYPE
   * set方法
   * 备注: 
   */
   public void setReasonType(Integer reasonType){
        this.reasonType = reasonType;
   }
   /**
   * 字段名：MANUSCRIPT_VAR
   * get方法
   * 备注: 
   */
   public String getManuscriptVar(){

        return manuscriptVar;
   }

   /**
   * 字段名：MANUSCRIPT_VAR
   * set方法
   * 备注: 
   */
   public void setManuscriptVar(String manuscriptVar){
        this.manuscriptVar = manuscriptVar;
   }
   /**
   * 字段名：MANUSCRIPT_STATUS
   * get方法
   * 备注: 
   */
   public Integer getManuscriptStatus(){

        return manuscriptStatus;
   }

   /**
   * 字段名：MANUSCRIPT_STATUS
   * set方法
   * 备注: 
   */
   public void setManuscriptStatus(Integer manuscriptStatus){
        this.manuscriptStatus = manuscriptStatus;
   }
   /**
   * 字段名：DIFFCULT_VAR
   * get方法
   * 备注: 
   */
   public String getDiffcultVar(){

        return diffcultVar;
   }

   /**
   * 字段名：DIFFCULT_VAR
   * set方法
   * 备注: 
   */
   public void setDiffcultVar(String diffcultVar){
        this.diffcultVar = diffcultVar;
   }
   /**
   * 字段名：DIFFCULT_LEVEL
   * get方法
   * 备注: 
   */
   public Integer getDiffcultLevel(){

        return diffcultLevel;
   }

   /**
   * 字段名：DIFFCULT_LEVEL
   * set方法
   * 备注: 
   */
   public void setDiffcultLevel(Integer diffcultLevel){
        this.diffcultLevel = diffcultLevel;
   }
   /**
   * 字段名：DEV_USER
   * get方法
   * 备注: 
   */
   public String getDevUser(){

        return devUser;
   }

   /**
   * 字段名：DEV_USER
   * set方法
   * 备注: 
   */
   public void setDevUser(String devUser){
        this.devUser = devUser;
   }
   /**
   * 字段名：DEV_USER_NAME
   * get方法
   * 备注: 
   */
   public String getDevUserName(){

        return devUserName;
   }

   /**
   * 字段名：DEV_USER_NAME
   * set方法
   * 备注: 
   */
   public void setDevUserName(String devUserName){
        this.devUserName = devUserName;
   }
   /**
   * 字段名：INTRODUCER
   * get方法
   * 备注: 
   */
   public String getIntroducer(){

        return introducer;
   }

   /**
   * 字段名：INTRODUCER
   * set方法
   * 备注: 
   */
   public void setIntroducer(String introducer){
        this.introducer = introducer;
   }
   /**
   * 字段名：INTRODUCER_NAME
   * get方法
   * 备注: 
   */
   public String getIntroducerName(){

        return introducerName;
   }

   /**
   * 字段名：INTRODUCER_NAME
   * set方法
   * 备注: 
   */
   public void setIntroducerName(String introducerName){
        this.introducerName = introducerName;
   }
   /**
   * 字段名：INTRODUCER_DATE
   * get方法
   * 备注: 
   */
   public String getIntroducerDate(){

        return introducerDate;
   }

   /**
   * 字段名：INTRODUCER_DATE
   * set方法
   * 备注: 
   */
   public void setIntroducerDate(String introducerDate){
        this.introducerDate = introducerDate;
   }
   /**
   * 字段名：LINKMAN
   * get方法
   * 备注: 
   */
   public String getLinkman(){

        return linkman;
   }

   /**
   * 字段名：LINKMAN
   * set方法
   * 备注: 
   */
   public void setLinkman(String linkman){
        this.linkman = linkman;
   }
   /**
   * 字段名：MOBILE
   * get方法
   * 备注: 
   */
   public String getMobile(){

        return mobile;
   }

   /**
   * 字段名：MOBILE
   * set方法
   * 备注: 
   */
   public void setMobile(String mobile){
        this.mobile = mobile;
   }
   /**
   * 字段名：OPER_VAR
   * get方法
   * 备注: 
   */
   public String getOperVar(){

        return operVar;
   }

   /**
   * 字段名：OPER_VAR
   * set方法
   * 备注: 
   */
   public void setOperVar(String operVar){
        this.operVar = operVar;
   }
   /**
   * 字段名：OPER_TYPE
   * get方法
   * 备注: 
   */
   public Integer getOperType(){

        return operType;
   }

   /**
   * 字段名：OPER_TYPE
   * set方法
   * 备注: 
   */
   public void setOperType(Integer operType){
        this.operType = operType;
   }
   /**
   * 字段名：HOPE_FINISH_DATE
   * get方法
   * 备注: 
   */
   public String getHopeFinishDate(){

        return hopeFinishDate;
   }

   /**
   * 字段名：HOPE_FINISH_DATE
   * set方法
   * 备注: 
   */
   public void setHopeFinishDate(String hopeFinishDate){
        this.hopeFinishDate = hopeFinishDate;
   }
   /**
   * 字段名：USER_MESSAGE
   * get方法
   * 备注: 
   */
   public String getUserMessage(){

        return userMessage;
   }

   /**
   * 字段名：USER_MESSAGE
   * set方法
   * 备注: 
   */
   public void setUserMessage(String userMessage){
        this.userMessage = userMessage;
   }
   /**
   * 字段名：REQUIREMENT_NO
   * get方法
   * 备注: 
   */
   public String getRequirementNo(){

        return requirementNo;
   }

   /**
   * 字段名：REQUIREMENT_NO
   * set方法
   * 备注: 
   */
   public void setRequirementNo(String requirementNo){
        this.requirementNo = requirementNo;
   }

}