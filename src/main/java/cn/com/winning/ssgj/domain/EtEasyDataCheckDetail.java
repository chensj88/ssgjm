package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:33
 */
 
@Alias("etEasyDataCheckDetail")
public class EtEasyDataCheckDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 关联主表ID
	 */
	private Long sourceId;
	
	/**
	 * @val 科室或医生代码
	 */
	private String deptDoctorCode;
	
	/**
	 * @val 科室或医生名称
	 */
	private String deptDoctorName;
	
	/**
	 * @val 数量
	 */
	private Integer num;
	
	/**
	 * @val 备注
	 */
	private String remark;
	
	public EtEasyDataCheckDetail() {

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
	 * @val 关联主表ID
	 */
	public Long getSourceId() {
		return sourceId;
	}
	
	/**
	 * @val 关联主表ID
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	
	/**
	 * @val 科室或医生代码
	 */
	public String getDeptDoctorCode() {
		return deptDoctorCode;
	}
	
	/**
	 * @val 科室或医生代码
	 */
	public void setDeptDoctorCode(String deptDoctorCode) {
		this.deptDoctorCode = deptDoctorCode;
	}
	
	/**
	 * @val 科室或医生名称
	 */
	public String getDeptDoctorName() {
		return deptDoctorName;
	}
	
	/**
	 * @val 科室或医生名称
	 */
	public void setDeptDoctorName(String deptDoctorName) {
		this.deptDoctorName = deptDoctorName;
	}
	
	/**
	 * @val 数量
	 */
	public Integer getNum() {
		return num;
	}
	
	/**
	 * @val 数量
	 */
	public void setNum(Integer num) {
		this.num = num;
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
	
}