package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-23 14:32:40
 */
 
@Alias("etDepartment")
public class EtDepartment extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long serialNo;
	
	private String serialName;
	
	private String deptType;
	
	private String typeName;
	
	private String deptCode;
	
	private String deptName;
	
	private Integer isDel;
	/**
	 * 科室地理位置
	 */
	private String deptLocation;
	
	public EtDepartment() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getSerialName() {
		return serialName;
	}
	
	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}
	
	public String getDeptType() {
		return deptType;
	}
	
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getDeptCode() {
		return deptCode;
	}
	
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public Integer getIsDel() {
		return isDel;
	}
	
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}
}