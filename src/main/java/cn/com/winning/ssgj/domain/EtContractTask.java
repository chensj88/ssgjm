package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-26 16:19:07
 */
 
@Alias("etContractTask")
public class EtContractTask extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long cId;
	
	private Long pmId;
	
	private String serialNo;
	
	private String zxtmc;
	
	private Long cpzxt;
	
	private String teamMembers;
	/**
	 * 主力工程师
	 */
	private Long allocateUser;
	
	private String mx;
	
	private String bz;
	
	private Long creator;
	
	private Date createTime;
	
	private Long operator;
	
	private Date operatorTime;

	private Long sourceId;
	/**
	 * 项目组成员ID列表
	 */
	private List<Long> memebers;
	
	public EtContractTask() {

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
	
	public String getZxtmc() {
		return zxtmc;
	}
	
	public void setZxtmc(String zxtmc) {
		this.zxtmc = zxtmc;
	}
	
	public Long getCpzxt() {
		return cpzxt;
	}
	
	public void setCpzxt(Long cpzxt) {
		this.cpzxt = cpzxt;
	}

	public String getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(String teamMembers) {
		this.teamMembers = teamMembers;
	}

	public Long getAllocateUser() {
		return allocateUser;
	}

	public void setAllocateUser(Long allocateUser) {
		this.allocateUser = allocateUser;
	}

	public String getMx() {
		return mx;
	}
	
	public void setMx(String mx) {
		this.mx = mx;
	}
	
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
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

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public List<Long> getMemebers() {
		return  this.teamMembers == null ? new ArrayList<>() : JSON.parseArray("["+this.teamMembers+"]",Long.class);
	}

	public void setMemebers(List<Long> memebers) {
		this.memebers = memebers;
	}
}