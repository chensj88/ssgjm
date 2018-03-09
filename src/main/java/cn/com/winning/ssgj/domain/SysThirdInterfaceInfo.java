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
 
@Alias("sysThirdInterfaceInfo")
public class SysThirdInterfaceInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	/**
	 * @val 接口代码
	 */
	private String interCode;
	
	/**
	 * @val 接口名称
	 */
	private String interName;
	
	/**
	 * @val 第三方产品名称
	 */
	private String refProductName;
	
	/**
	 * @val 接口描述
	 */
	private String interDesc;
	
	/**
	 * @val 接口性质 0 内部；1外部
	 */
	private Integer interProps;
	
	/**
	 * @val 状态 0失效;1 生效
	 */
	private Integer status;
	
	/**
	 * @val 接口描述
	 */
	private Long lastUpdator;
	
	/**
	 * @val 维护时间
	 */
	private java.sql.Timestamp lastUpdateTime;
	
	public SysThirdInterfaceInfo() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 接口代码
	 */
	public String getInterCode() {
		return interCode;
	}
	
	/**
	 * @val 接口代码
	 */
	public void setInterCode(String interCode) {
		this.interCode = interCode;
	}
	
	/**
	 * @val 接口名称
	 */
	public String getInterName() {
		return interName;
	}
	
	/**
	 * @val 接口名称
	 */
	public void setInterName(String interName) {
		this.interName = interName;
	}
	
	/**
	 * @val 第三方产品名称
	 */
	public String getRefProductName() {
		return refProductName;
	}
	
	/**
	 * @val 第三方产品名称
	 */
	public void setRefProductName(String refProductName) {
		this.refProductName = refProductName;
	}
	
	/**
	 * @val 接口描述
	 */
	public String getInterDesc() {
		return interDesc;
	}
	
	/**
	 * @val 接口描述
	 */
	public void setInterDesc(String interDesc) {
		this.interDesc = interDesc;
	}
	
	/**
	 * @val 接口性质 0 内部；1外部
	 */
	public Integer getInterProps() {
		return interProps;
	}
	
	/**
	 * @val 接口性质 0 内部；1外部
	 */
	public void setInterProps(Integer interProps) {
		this.interProps = interProps;
	}
	
	/**
	 * @val 状态 0失效;1 生效
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 状态 0失效;1 生效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 接口描述
	 */
	public Long getLastUpdator() {
		return lastUpdator;
	}
	
	/**
	 * @val 接口描述
	 */
	public void setLastUpdator(Long lastUpdator) {
		this.lastUpdator = lastUpdator;
	}
	
	/**
	 * @val 维护时间
	 */
	public java.sql.Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * @val 维护时间
	 */
	public void setLastUpdateTime(java.sql.Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}