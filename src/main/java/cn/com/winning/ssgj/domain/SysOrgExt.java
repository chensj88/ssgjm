package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-15 10:21:03
 */
 
@Alias("sysOrgExt")
public class SysOrgExt extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long orgId;
	
	private String orgName;
	
	private String orgCode;
	
	private String orgNameExt;
	
	public SysOrgExt() {

	}

	public Long getOrgId() {
		return orgId;
	}
	
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	public String getOrgNameExt() {
		return orgNameExt;
	}
	
	public void setOrgNameExt(String orgNameExt) {
		this.orgNameExt = orgNameExt;
	}
	
}