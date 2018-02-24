package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:37
 */
 
@Alias("pmisProductLineInfo")
public class PmisProductLineInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 名称
	 */
	private String name;
	
	/**
	 * @val 状态
	 */
	private Integer zt;
	
	/**
	 * @val 类型
	 */
	private Integer lx;
	
	/**
	 * @val 原系统内码
	 */
	private String yxtnm;
	
	/**
	 * @val 对应合同模块产品类型
	 */
	private String mklx;
	
	/**
	 * @val 顺序
	 */
	private String sx;
	
	/**
	 * @val 核算单元
	 */
	private String hsdy;
	
	/**
	 * @val 产品组
	 */
	private String cpz;
	
	/**
	 * @val 产品小类
	 */
	private String cpxl;
	
	/**
	 * @val 产品大类
	 */
	private String cpdl;
	
	public PmisProductLineInfo() {

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
	 * @val 名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @val 状态
	 */
	public Integer getZt() {
		return zt;
	}
	
	/**
	 * @val 状态
	 */
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	
	/**
	 * @val 类型
	 */
	public Integer getLx() {
		return lx;
	}
	
	/**
	 * @val 类型
	 */
	public void setLx(Integer lx) {
		this.lx = lx;
	}
	
	/**
	 * @val 原系统内码
	 */
	public String getYxtnm() {
		return yxtnm;
	}
	
	/**
	 * @val 原系统内码
	 */
	public void setYxtnm(String yxtnm) {
		this.yxtnm = yxtnm;
	}
	
	/**
	 * @val 对应合同模块产品类型
	 */
	public String getMklx() {
		return mklx;
	}
	
	/**
	 * @val 对应合同模块产品类型
	 */
	public void setMklx(String mklx) {
		this.mklx = mklx;
	}
	
	/**
	 * @val 顺序
	 */
	public String getSx() {
		return sx;
	}
	
	/**
	 * @val 顺序
	 */
	public void setSx(String sx) {
		this.sx = sx;
	}
	
	/**
	 * @val 核算单元
	 */
	public String getHsdy() {
		return hsdy;
	}
	
	/**
	 * @val 核算单元
	 */
	public void setHsdy(String hsdy) {
		this.hsdy = hsdy;
	}
	
	/**
	 * @val 产品组
	 */
	public String getCpz() {
		return cpz;
	}
	
	/**
	 * @val 产品组
	 */
	public void setCpz(String cpz) {
		this.cpz = cpz;
	}
	
	/**
	 * @val 产品小类
	 */
	public String getCpxl() {
		return cpxl;
	}
	
	/**
	 * @val 产品小类
	 */
	public void setCpxl(String cpxl) {
		this.cpxl = cpxl;
	}
	
	/**
	 * @val 产品大类
	 */
	public String getCpdl() {
		return cpdl;
	}
	
	/**
	 * @val 产品大类
	 */
	public void setCpdl(String cpdl) {
		this.cpdl = cpdl;
	}
	
}