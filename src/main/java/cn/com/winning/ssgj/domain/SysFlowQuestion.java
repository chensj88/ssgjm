package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * SSGJ
 *
 * @author SSGJ
 * @date 2018-01-30 11:27:25
 */
 
@Alias("sysFlowQuestion")
public class SysFlowQuestion extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 流程序号
	 */
	private Long flowId;

	/**
	 * @val 流程名称
	 */
	 private String flowName ;

	 /**
	  * @val 流程编号
	  */
	  private String flowCode ;

	
	/**
	 * @val 问题编号
	 */
	private String quesCode;
	
	/**
	 * @val 流程名称
	 */
	private String quesName;
	
	/**
	 * @val 问题类型 0 单选；1多选；2 单选混合； 3 多选混合；4 是否选择；
	 */
	private Integer quesType;
	
	/**
	 * @val 答案数
	 */
	private Integer resultNum;
	
	/**
	 * @val 流程描述
	 */
	private String quesDesc;
	
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
	private Date lastUpdateTime;
	
	public SysFlowQuestion() {

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
	 * @val 流程序号
	 */
	public Long getFlowId() {
		return flowId;
	}
	
	/**
	 * @val 流程序号
	 */
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	
	/**
	 * @val 问题编号
	 */
	public String getQuesCode() {
		return quesCode;
	}
	
	/**
	 * @val 问题编号
	 */
	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
	}
	
	/**
	 * @val 流程名称
	 */
	public String getQuesName() {
		return quesName;
	}
	
	/**
	 * @val 流程名称
	 */
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
	
	/**
	 * @val 问题类型 0 单选；1多选；2 单选混合； 3 多选混合；4 是否选择；
	 */
	public Integer getQuesType() {
		return quesType;
	}
	
	/**
	 * @val 问题类型 0 单选；1多选；2 单选混合； 3 多选混合；4 是否选择；
	 */
	public void setQuesType(Integer quesType) {
		this.quesType = quesType;
	}
	
	/**
	 * @val 答案数
	 */
	public Integer getResultNum() {
		return resultNum;
	}
	
	/**
	 * @val 答案数
	 */
	public void setResultNum(Integer resultNum) {
		this.resultNum = resultNum;
	}
	
	/**
	 * @val 流程描述
	 */
	public String getQuesDesc() {
		return quesDesc;
	}
	
	/**
	 * @val 流程描述
	 */
	public void setQuesDesc(String quesDesc) {
		this.quesDesc = quesDesc;
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
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
}