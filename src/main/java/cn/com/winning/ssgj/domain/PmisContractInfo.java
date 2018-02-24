package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:36
 */
 
@Alias("pmisContractInfo")
public class PmisContractInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	private Long zhtxx;
	
	/**
	 * @val 合同用户方
	 */
	private Long htyhf;
	
	/**
	 * @val 安装任务单编号
	 */
	private String code;
	
	/**
	 * @val 档案名称
	 */
	private String damc;
	
	/**
	 * @val 安装任务单名称
	 */
	private String name;
	
	/**
	 * @val 签订日期
	 */
	private String sqrq;
	
	/**
	 * @val 考核日期
	 */
	private String sxrq;
	
	/**
	 * @val 签订年月
	 */
	private String qdny;
	
	/**
	 * @val 签订年度
	 */
	private String nf;
	
	/**
	 * @val 合同类型
	 */
	private Long htlx;
	
	/**
	 * @val 开票类型 1|软件;2|硬件;3|服务
	 */
	private Integer kplx;
	
	/**
	 * @val 项目类型
	 */
	private Integer xmlx;
	
	/**
	 * @val 是否挂起
	 */
	private Integer sfgq;
	
	/**
	 * @val 合同金额
	 */
	private String htje;
	
	/**
	 * @val 客户
	 */
	private Long khxx;
	
	/**
	 * @val 客户签约方
	 */
	private String htqyf;
	
	/**
	 * @val 签约公司
	 */
	private Long qygs;
	
	/**
	 * @val 销售员
	 */
	private Long htqdry;
	
	/**
	 * @val 跟踪人员
	 */
	private Long htgzry;
	
	/**
	 * @val 销售所属机构
	 */
	private Long xsssjg;
	
	/**
	 * @val 指标机构
	 */
	private Long zbjg;
	
	/**
	 * @val 销售公司
	 */
	private Long xsssgs;
	
	public PmisContractInfo() {

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
	
	public Long getZhtxx() {
		return zhtxx;
	}
	
	public void setZhtxx(Long zhtxx) {
		this.zhtxx = zhtxx;
	}
	
	/**
	 * @val 合同用户方
	 */
	public Long getHtyhf() {
		return htyhf;
	}
	
	/**
	 * @val 合同用户方
	 */
	public void setHtyhf(Long htyhf) {
		this.htyhf = htyhf;
	}
	
	/**
	 * @val 安装任务单编号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @val 安装任务单编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @val 档案名称
	 */
	public String getDamc() {
		return damc;
	}
	
	/**
	 * @val 档案名称
	 */
	public void setDamc(String damc) {
		this.damc = damc;
	}
	
	/**
	 * @val 安装任务单名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 安装任务单名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @val 签订日期
	 */
	public String getSqrq() {
		return sqrq;
	}
	
	/**
	 * @val 签订日期
	 */
	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}
	
	/**
	 * @val 考核日期
	 */
	public String getSxrq() {
		return sxrq;
	}
	
	/**
	 * @val 考核日期
	 */
	public void setSxrq(String sxrq) {
		this.sxrq = sxrq;
	}
	
	/**
	 * @val 签订年月
	 */
	public String getQdny() {
		return qdny;
	}
	
	/**
	 * @val 签订年月
	 */
	public void setQdny(String qdny) {
		this.qdny = qdny;
	}
	
	/**
	 * @val 签订年度
	 */
	public String getNf() {
		return nf;
	}
	
	/**
	 * @val 签订年度
	 */
	public void setNf(String nf) {
		this.nf = nf;
	}
	
	/**
	 * @val 合同类型
	 */
	public Long getHtlx() {
		return htlx;
	}
	
	/**
	 * @val 合同类型
	 */
	public void setHtlx(Long htlx) {
		this.htlx = htlx;
	}
	
	/**
	 * @val 开票类型 1|软件;2|硬件;3|服务
	 */
	public Integer getKplx() {
		return kplx;
	}
	
	/**
	 * @val 开票类型 1|软件;2|硬件;3|服务
	 */
	public void setKplx(Integer kplx) {
		this.kplx = kplx;
	}
	
	/**
	 * @val 项目类型
	 */
	public Integer getXmlx() {
		return xmlx;
	}
	
	/**
	 * @val 项目类型
	 */
	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}
	
	/**
	 * @val 是否挂起
	 */
	public Integer getSfgq() {
		return sfgq;
	}
	
	/**
	 * @val 是否挂起
	 */
	public void setSfgq(Integer sfgq) {
		this.sfgq = sfgq;
	}
	
	/**
	 * @val 合同金额
	 */
	public String getHtje() {
		return htje;
	}
	
	/**
	 * @val 合同金额
	 */
	public void setHtje(String htje) {
		this.htje = htje;
	}
	
	/**
	 * @val 客户
	 */
	public Long getKhxx() {
		return khxx;
	}
	
	/**
	 * @val 客户
	 */
	public void setKhxx(Long khxx) {
		this.khxx = khxx;
	}
	
	/**
	 * @val 客户签约方
	 */
	public String getHtqyf() {
		return htqyf;
	}
	
	/**
	 * @val 客户签约方
	 */
	public void setHtqyf(String htqyf) {
		this.htqyf = htqyf;
	}
	
	/**
	 * @val 签约公司
	 */
	public Long getQygs() {
		return qygs;
	}
	
	/**
	 * @val 签约公司
	 */
	public void setQygs(Long qygs) {
		this.qygs = qygs;
	}
	
	/**
	 * @val 销售员
	 */
	public Long getHtqdry() {
		return htqdry;
	}
	
	/**
	 * @val 销售员
	 */
	public void setHtqdry(Long htqdry) {
		this.htqdry = htqdry;
	}
	
	/**
	 * @val 跟踪人员
	 */
	public Long getHtgzry() {
		return htgzry;
	}
	
	/**
	 * @val 跟踪人员
	 */
	public void setHtgzry(Long htgzry) {
		this.htgzry = htgzry;
	}
	
	/**
	 * @val 销售所属机构
	 */
	public Long getXsssjg() {
		return xsssjg;
	}
	
	/**
	 * @val 销售所属机构
	 */
	public void setXsssjg(Long xsssjg) {
		this.xsssjg = xsssjg;
	}
	
	/**
	 * @val 指标机构
	 */
	public Long getZbjg() {
		return zbjg;
	}
	
	/**
	 * @val 指标机构
	 */
	public void setZbjg(Long zbjg) {
		this.zbjg = zbjg;
	}
	
	/**
	 * @val 销售公司
	 */
	public Long getXsssgs() {
		return xsssgs;
	}
	
	/**
	 * @val 销售公司
	 */
	public void setXsssgs(Long xsssgs) {
		this.xsssgs = xsssgs;
	}
	
}