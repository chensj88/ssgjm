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
 
@Alias("pmisProjctUser")
public class PmisProjctUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	/**
	 * @val 项目里程碑
	 */
	private Long xmlcb;
	
	/**
	 * @val 人员分类  0|项目/客户经理;1|实施服务人员;2|支持人员
	 */
	private Integer ryfl;
	
	/**
	 * @val 人员
	 */
	private Long ry;
	
	public PmisProjctUser() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 项目里程碑
	 */
	public Long getXmlcb() {
		return xmlcb;
	}
	
	/**
	 * @val 项目里程碑
	 */
	public void setXmlcb(Long xmlcb) {
		this.xmlcb = xmlcb;
	}
	
	/**
	 * @val 人员分类  0|项目/客户经理;1|实施服务人员;2|支持人员
	 */
	public Integer getRyfl() {
		return ryfl;
	}
	
	/**
	 * @val 人员分类  0|项目/客户经理;1|实施服务人员;2|支持人员
	 */
	public void setRyfl(Integer ryfl) {
		this.ryfl = ryfl;
	}
	
	/**
	 * @val 人员
	 */
	public Long getRy() {
		return ry;
	}
	
	/**
	 * @val 人员
	 */
	public void setRy(Long ry) {
		this.ry = ry;
	}
	
}