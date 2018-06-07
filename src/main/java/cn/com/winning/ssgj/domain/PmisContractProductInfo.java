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
 
@Alias("pmisContractProductInfo")
public class PmisContractProductInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 用户方
	 */
	private Long khxx;
	
	/**
	 * @val 产品类别
	 */
	private Integer htcplb;
	
	/**
	 * @val 成交价清单类型
	 */
	private Long fbjelx;
	
	/**
	 * @val 大类
	 */
	private String cpdlmc;
	
	/**
	 * @val 子系统
	 */
	private String zxtmc;
	
	private String cpmc;
	
	/**
	 * @val 合同
	 */
	private Long zhtxx;
	
	/**
	 * @val 任务单
	 */
	private Long htxx;
	
	/**
	 * @val 项目
	 */
	private Long xmlcb;
	
	/**
	 * @val 项目分期计划
	 */
	private Long xmfqjh;
	
	/**
	 * @val 合同模块
	 */
	private Long htmk;
	
	/**
	 * @val 合同用户方
	 */
	private Long htyhf;
	
	/**
	 * @val 标准产品
	 */
	private Long cpxx;
	
	/**
	 * @val 产品子系统
	 */
	private String cpzxt;
	
	/**
	 * @val 产品大类
	 */
	private String sjcp;
	
	/**
	 * @val 标准产品基准价
	 */
	private String cpjzj;
	
	/**
	 * @val 标准产品报价系数
	 */
	private String bjxs;
	
	/**
	 * @val 状态 1|生效;2|作废
	 */
	private Integer zt;

	private EtContractTask etContractTask = new EtContractTask();
	
	public PmisContractProductInfo() {

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
	 * @val 用户方
	 */
	public Long getKhxx() {
		return khxx;
	}
	
	/**
	 * @val 用户方
	 */
	public void setKhxx(Long khxx) {
		this.khxx = khxx;
	}
	
	/**
	 * @val 产品类别
	 */
	public Integer getHtcplb() {
		return htcplb;
	}
	
	/**
	 * @val 产品类别
	 */
	public void setHtcplb(Integer htcplb) {
		this.htcplb = htcplb;
	}
	
	/**
	 * @val 成交价清单类型
	 */
	public Long getFbjelx() {
		return fbjelx;
	}
	
	/**
	 * @val 成交价清单类型
	 */
	public void setFbjelx(Long fbjelx) {
		this.fbjelx = fbjelx;
	}
	
	/**
	 * @val 大类
	 */
	public String getCpdlmc() {
		return cpdlmc;
	}
	
	/**
	 * @val 大类
	 */
	public void setCpdlmc(String cpdlmc) {
		this.cpdlmc = cpdlmc;
	}
	
	/**
	 * @val 子系统
	 */
	public String getZxtmc() {
		return zxtmc;
	}
	
	/**
	 * @val 子系统
	 */
	public void setZxtmc(String zxtmc) {
		this.zxtmc = zxtmc;
	}
	
	public String getCpmc() {
		return cpmc;
	}
	
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	
	/**
	 * @val 合同
	 */
	public Long getZhtxx() {
		return zhtxx;
	}
	
	/**
	 * @val 合同
	 */
	public void setZhtxx(Long zhtxx) {
		this.zhtxx = zhtxx;
	}
	
	/**
	 * @val 任务单
	 */
	public Long getHtxx() {
		return htxx;
	}
	
	/**
	 * @val 任务单
	 */
	public void setHtxx(Long htxx) {
		this.htxx = htxx;
	}
	
	/**
	 * @val 项目
	 */
	public Long getXmlcb() {
		return xmlcb;
	}
	
	/**
	 * @val 项目
	 */
	public void setXmlcb(Long xmlcb) {
		this.xmlcb = xmlcb;
	}
	
	/**
	 * @val 项目分期计划
	 */
	public Long getXmfqjh() {
		return xmfqjh;
	}
	
	/**
	 * @val 项目分期计划
	 */
	public void setXmfqjh(Long xmfqjh) {
		this.xmfqjh = xmfqjh;
	}
	
	/**
	 * @val 合同模块
	 */
	public Long getHtmk() {
		return htmk;
	}
	
	/**
	 * @val 合同模块
	 */
	public void setHtmk(Long htmk) {
		this.htmk = htmk;
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
	 * @val 标准产品
	 */
	public Long getCpxx() {
		return cpxx;
	}
	
	/**
	 * @val 标准产品
	 */
	public void setCpxx(Long cpxx) {
		this.cpxx = cpxx;
	}
	
	/**
	 * @val 产品子系统
	 */
	public String getCpzxt() {
		return cpzxt;
	}
	
	/**
	 * @val 产品子系统
	 */
	public void setCpzxt(String cpzxt) {
		this.cpzxt = cpzxt;
	}
	
	/**
	 * @val 产品大类
	 */
	public String getSjcp() {
		return sjcp;
	}
	
	/**
	 * @val 产品大类
	 */
	public void setSjcp(String sjcp) {
		this.sjcp = sjcp;
	}
	
	/**
	 * @val 标准产品基准价
	 */
	public String getCpjzj() {
		return cpjzj;
	}
	
	/**
	 * @val 标准产品基准价
	 */
	public void setCpjzj(String cpjzj) {
		this.cpjzj = cpjzj;
	}
	
	/**
	 * @val 标准产品报价系数
	 */
	public String getBjxs() {
		return bjxs;
	}
	
	/**
	 * @val 标准产品报价系数
	 */
	public void setBjxs(String bjxs) {
		this.bjxs = bjxs;
	}
	
	/**
	 * @val 状态 1|生效;2|作废
	 */
	public Integer getZt() {
		return zt;
	}
	
	/**
	 * @val 状态 1|生效;2|作废
	 */
	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public EtContractTask getEtContractTask() {
		etContractTask.setId(id);
		etContractTask.setcId(htxx);
		etContractTask.setPmId(xmlcb);
		etContractTask.setSerialNo(khxx+"");
		etContractTask.setCpzxt(Long.valueOf(cpzxt));
		etContractTask.setZxtmc(zxtmc);
		etContractTask.setAllocateUser(-1L);
		etContractTask.setCpmc(cpmc);
		return etContractTask;
	}

	public void setEtContractTask(EtContractTask etContractTask) {
		this.etContractTask = etContractTask;
	}
}