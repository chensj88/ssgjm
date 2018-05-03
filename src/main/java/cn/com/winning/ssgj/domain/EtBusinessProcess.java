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
 * @date 2018-04-12 10:44:43
 */
 
@Alias("etBusinessProcess")
public class EtBusinessProcess extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long cId;
	
	private Long pmId;
	
	private String serialNo;
	
	private Long flowId;
	
	private String flowCode;
	
	private String flowName;
	
	private Integer isScope;
	
	private String noScopeCode;
	
	private String message;
	
	private Integer status;
	
	private Integer sourceType;
	
	private String uploadPath;
	
	private String downloadPath;

	private String imgPath;
	
	private Long creator;
	
	private java.sql.Timestamp createTime;
	
	private Long operator;
	
	private java.sql.Timestamp operatorTime;

	private List imgs;
	
	public EtBusinessProcess() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Long getPmId() {
		return pmId;
	}
	
	public void setPmId(Long pmId) {
		this.pmId = pmId;
	}
	
	public String getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public Long getFlowId() {
		return flowId;
	}
	
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	
	public String getFlowCode() {
		return flowCode;
	}
	
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	
	public String getFlowName() {
		return flowName;
	}
	
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	
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
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getSourceType() {
		return sourceType;
	}
	
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	public String getUploadPath() {
		return uploadPath;
	}
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	public String getDownloadPath() {
		return downloadPath;
	}
	
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	
	public Long getCreator() {
		return creator;
	}
	
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public Long getOperator() {
		return operator;
	}
	
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	
	public java.sql.Timestamp getOperatorTime() {
		return operatorTime;
	}
	
	public void setOperatorTime(java.sql.Timestamp operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public List getImgs() {
		return imgs;
	}

	public void setImgs(List imgs) {
		this.imgs = imgs;
	}

}