package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.UrlContent;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-24 14:03:01
 */
 
@Alias("etCustomerDetail")
public class EtCustomerDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String pmCode;
	
	private String pmName;
	
	private Long cId;
	
	private String serialNo;
	
	private String custName;
	
	private Integer hospitalLevel;
	
	private String specialType;
	
	private String characteristic;

	private Integer cityCode;

	private Integer areaCode;
	
	private Integer outpatientNum;
	
	private Integer bedNum;
	
	private Long creator;

	private java.sql.Timestamp createTime;
	
	private Long operator;

	private java.sql.Timestamp operatorTime;

	private String leader;

	private String leaderMobile;

	private String constractPath;

	private String taskPath;

	private String constractPathJson;

	private String taskPathJson;

	public EtCustomerDetail() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPmCode() {
		return pmCode;
	}
	
	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}
	
	public String getPmName() {
		return pmName;
	}
	
	public void setPmName(String pmName) {
		this.pmName = pmName;
	}
	
	public Long getCId() {
		return cId;
	}
	
	public void setCId(Long cId) {
		this.cId = cId;
	}
	
	public String getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getCustName() {
		return custName;
	}
	
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public Integer getHospitalLevel() {
		return hospitalLevel;
	}
	
	public void setHospitalLevel(Integer hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	
	public String getSpecialType() {
		return specialType;
	}
	
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	
	public String getCharacteristic() {
		return characteristic;
	}
	
	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	
	public Integer getAreaCode() {
		return areaCode;
	}
	
	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}
	
	public Integer getOutpatientNum() {
		return outpatientNum;
	}
	
	public void setOutpatientNum(Integer outpatientNum) {
		this.outpatientNum = outpatientNum;
	}
	
	public Integer getBedNum() {
		return bedNum;
	}
	
	public void setBedNum(Integer bedNum) {
		this.bedNum = bedNum;
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

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getLeaderMobile() {
		return leaderMobile;
	}

	public void setLeaderMobile(String leaderMobile) {
		this.leaderMobile = leaderMobile;
	}

	public String getConstractPath() {

		return constractPath;
	}

	public void setConstractPath(String constractPath) {
		this.constractPath = constractPath;
	}

	public String getTaskPath() {
		return taskPath;
	}

	public void setTaskPath(String taskPath) {
		this.taskPath = taskPath;
	}

	public String getConstractPathJson() {
		UrlContent content = new UrlContent();
		if(constractPath != null  && !"".equals(constractPath) ){
			content.setName(constractPath.substring(constractPath.lastIndexOf("/")+1,constractPath.lastIndexOf(".")));
			content.setUrl(Constants.FTP_SHARE_FLODER + constractPath);
			content.setOperDate(DateUtil.format(this.operatorTime,DateUtil.DEFAULT_PATTERN));
			content.setUserName(this.getMap().get("userName").toString());
		}
		return constractPath != null ? JSON.toJSONString(content) : null;
	}

	public void setConstractPathJson(String constractPathJson) {
		this.constractPathJson = constractPathJson;
	}

	public String getTaskPathJson() {
		UrlContent content = new UrlContent();
		if(taskPath != null && !"".equals(taskPath)){
			content.setName(taskPath.substring(taskPath.lastIndexOf("/")+1,taskPath.lastIndexOf(".")));
			content.setUrl(Constants.FTP_SHARE_FLODER + taskPath);
			content.setOperDate(DateUtil.format(this.operatorTime,DateUtil.DEFAULT_PATTERN));
			content.setUserName(this.getMap().get("userName").toString());
		}
		return taskPath != null ? JSON.toJSONString(content) : null;
	}

	public void setTaskPathJson(String taskPathJson) {
		this.taskPathJson = taskPathJson;
	}
}