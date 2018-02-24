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
 
@Alias("pmisProjectBasicInfo")
public class PmisProjectBasicInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	/**
	 * @val 类型 0|实施项目;1|服务合同项目;2|免费维护项目
	 */
	private Integer fwlx;
	
	/**
	 * @val 项目类型
	 */
	private String xmlx;
	
	/**
	 * @val 项目等级 0|无;1|绿;2|黄;3|红
	 */
	private Integer xmdj;
	
	/**
	 * @val 项目名称
	 */
	private String name;
	
	/**
	 * @val 期初
	 */
	private String khjdqc;
	
	/**
	 * @val 推进
	 */
	private String khjdtj;
	
	/**
	 * @val 累计
	 */
	private String khjdlj;
	
	/**
	 * @val 期数
	 */
	private Integer qs;
	
	/**
	 * @val 项目描述
	 */
	private String xmms;
	
	/**
	 * @val 项目内容
	 */
	private String jhnr;
	
	/**
	 * @val 项目经理
	 */
	private Long xmjl;
	
	/**
	 * @val 在建状态
	 */
	private Integer zjzt;
	
	/**
	 * @val 执行状态
	 */
	private Integer jhzt;
	
	/**
	 * @val 质控人员
	 */
	private Long zkry;
	
	/**
	 * @val 所属公司
	 */
	private Long ssgs;
	
	/**
	 * @val 指标机构
	 */
	private Long jsdq;
	
	/**
	 * @val 实施机构
	 */
	private Long ssjg;
	
	/**
	 * @val 考核机构
	 */
	private Long khjg;
	
	/**
	 * @val 合同
	 */
	private Long htxx;
	
	/**
	 * @val 合同类型
	 */
	private Long htlx;
	
	/**
	 * @val 备注说明
	 */
	private String bzsm;
	
	/**
	 * @val 暂停/搁置到期日期
	 */
	private String gzrq;
	
	/**
	 * @val 搁置说明
	 */
	private String gzsm;
	
	/**
	 * @val 客户
	 */
	private Long khxx;
	
	/**
	 * @val 考核收入
	 */
	private String khsr;
	
	/**
	 * @val 已完成考核收入
	 */
	private String ywckhsr;
	
	/**
	 * @val 考核系数
	 */
	private String khxs;
	
	/**
	 * @val 调整后收入
	 */
	private String khxssr;
	
	/**
	 * @val 考核完成日期
	 */
	private String wcrq;
	
	/**
	 * @val 最后更新时间
	 */
	private String gxsj;
	
	/**
	 * @val 最后更新人
	 */
	private Long gxr;
	
	/**
	 * @val 状态 1|生效;2|作废
	 */
	private Integer zt;
	
	/**
	 * @val 替换状态
	 */
	private Integer thfs;
	
	/**
	 * @val 公司确认状态 0|未确认;1|已确认
	 */
	private Integer jdqrzt;
	
	/**
	 * @val 确认情况
	 */
	private String qrbzsm;
	
	/**
	 * @val 最后下单日期
	 */
	private String xdrq;
	
	/**
	 * @val 移交状态
	 */
	private Integer yjzt;
	
	/**
	 * @val 移交日期
	 */
	private String yjrq;
	
	public PmisProjectBasicInfo() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 类型 0|实施项目;1|服务合同项目;2|免费维护项目
	 */
	public Integer getFwlx() {
		return fwlx;
	}
	
	/**
	 * @val 类型 0|实施项目;1|服务合同项目;2|免费维护项目
	 */
	public void setFwlx(Integer fwlx) {
		this.fwlx = fwlx;
	}
	
	/**
	 * @val 项目类型
	 */
	public String getXmlx() {
		return xmlx;
	}
	
	/**
	 * @val 项目类型
	 */
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	
	/**
	 * @val 项目等级 0|无;1|绿;2|黄;3|红
	 */
	public Integer getXmdj() {
		return xmdj;
	}
	
	/**
	 * @val 项目等级 0|无;1|绿;2|黄;3|红
	 */
	public void setXmdj(Integer xmdj) {
		this.xmdj = xmdj;
	}
	
	/**
	 * @val 项目名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 项目名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @val 期初
	 */
	public String getKhjdqc() {
		return khjdqc;
	}
	
	/**
	 * @val 期初
	 */
	public void setKhjdqc(String khjdqc) {
		this.khjdqc = khjdqc;
	}
	
	/**
	 * @val 推进
	 */
	public String getKhjdtj() {
		return khjdtj;
	}
	
	/**
	 * @val 推进
	 */
	public void setKhjdtj(String khjdtj) {
		this.khjdtj = khjdtj;
	}
	
	/**
	 * @val 累计
	 */
	public String getKhjdlj() {
		return khjdlj;
	}
	
	/**
	 * @val 累计
	 */
	public void setKhjdlj(String khjdlj) {
		this.khjdlj = khjdlj;
	}
	
	/**
	 * @val 期数
	 */
	public Integer getQs() {
		return qs;
	}
	
	/**
	 * @val 期数
	 */
	public void setQs(Integer qs) {
		this.qs = qs;
	}
	
	/**
	 * @val 项目描述
	 */
	public String getXmms() {
		return xmms;
	}
	
	/**
	 * @val 项目描述
	 */
	public void setXmms(String xmms) {
		this.xmms = xmms;
	}
	
	/**
	 * @val 项目内容
	 */
	public String getJhnr() {
		return jhnr;
	}
	
	/**
	 * @val 项目内容
	 */
	public void setJhnr(String jhnr) {
		this.jhnr = jhnr;
	}
	
	/**
	 * @val 项目经理
	 */
	public Long getXmjl() {
		return xmjl;
	}
	
	/**
	 * @val 项目经理
	 */
	public void setXmjl(Long xmjl) {
		this.xmjl = xmjl;
	}
	
	/**
	 * @val 在建状态
	 */
	public Integer getZjzt() {
		return zjzt;
	}
	
	/**
	 * @val 在建状态
	 */
	public void setZjzt(Integer zjzt) {
		this.zjzt = zjzt;
	}
	
	/**
	 * @val 执行状态
	 */
	public Integer getJhzt() {
		return jhzt;
	}
	
	/**
	 * @val 执行状态
	 */
	public void setJhzt(Integer jhzt) {
		this.jhzt = jhzt;
	}
	
	/**
	 * @val 质控人员
	 */
	public Long getZkry() {
		return zkry;
	}
	
	/**
	 * @val 质控人员
	 */
	public void setZkry(Long zkry) {
		this.zkry = zkry;
	}
	
	/**
	 * @val 所属公司
	 */
	public Long getSsgs() {
		return ssgs;
	}
	
	/**
	 * @val 所属公司
	 */
	public void setSsgs(Long ssgs) {
		this.ssgs = ssgs;
	}
	
	/**
	 * @val 指标机构
	 */
	public Long getJsdq() {
		return jsdq;
	}
	
	/**
	 * @val 指标机构
	 */
	public void setJsdq(Long jsdq) {
		this.jsdq = jsdq;
	}
	
	/**
	 * @val 实施机构
	 */
	public Long getSsjg() {
		return ssjg;
	}
	
	/**
	 * @val 实施机构
	 */
	public void setSsjg(Long ssjg) {
		this.ssjg = ssjg;
	}
	
	/**
	 * @val 考核机构
	 */
	public Long getKhjg() {
		return khjg;
	}
	
	/**
	 * @val 考核机构
	 */
	public void setKhjg(Long khjg) {
		this.khjg = khjg;
	}
	
	/**
	 * @val 合同
	 */
	public Long getHtxx() {
		return htxx;
	}
	
	/**
	 * @val 合同
	 */
	public void setHtxx(Long htxx) {
		this.htxx = htxx;
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
	 * @val 备注说明
	 */
	public String getBzsm() {
		return bzsm;
	}
	
	/**
	 * @val 备注说明
	 */
	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}
	
	/**
	 * @val 暂停/搁置到期日期
	 */
	public String getGzrq() {
		return gzrq;
	}
	
	/**
	 * @val 暂停/搁置到期日期
	 */
	public void setGzrq(String gzrq) {
		this.gzrq = gzrq;
	}
	
	/**
	 * @val 搁置说明
	 */
	public String getGzsm() {
		return gzsm;
	}
	
	/**
	 * @val 搁置说明
	 */
	public void setGzsm(String gzsm) {
		this.gzsm = gzsm;
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
	 * @val 考核收入
	 */
	public String getKhsr() {
		return khsr;
	}
	
	/**
	 * @val 考核收入
	 */
	public void setKhsr(String khsr) {
		this.khsr = khsr;
	}
	
	/**
	 * @val 已完成考核收入
	 */
	public String getYwckhsr() {
		return ywckhsr;
	}
	
	/**
	 * @val 已完成考核收入
	 */
	public void setYwckhsr(String ywckhsr) {
		this.ywckhsr = ywckhsr;
	}
	
	/**
	 * @val 考核系数
	 */
	public String getKhxs() {
		return khxs;
	}
	
	/**
	 * @val 考核系数
	 */
	public void setKhxs(String khxs) {
		this.khxs = khxs;
	}
	
	/**
	 * @val 调整后收入
	 */
	public String getKhxssr() {
		return khxssr;
	}
	
	/**
	 * @val 调整后收入
	 */
	public void setKhxssr(String khxssr) {
		this.khxssr = khxssr;
	}
	
	/**
	 * @val 考核完成日期
	 */
	public String getWcrq() {
		return wcrq;
	}
	
	/**
	 * @val 考核完成日期
	 */
	public void setWcrq(String wcrq) {
		this.wcrq = wcrq;
	}
	
	/**
	 * @val 最后更新时间
	 */
	public String getGxsj() {
		return gxsj;
	}
	
	/**
	 * @val 最后更新时间
	 */
	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
	}
	
	/**
	 * @val 最后更新人
	 */
	public Long getGxr() {
		return gxr;
	}
	
	/**
	 * @val 最后更新人
	 */
	public void setGxr(Long gxr) {
		this.gxr = gxr;
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
	
	/**
	 * @val 替换状态
	 */
	public Integer getThfs() {
		return thfs;
	}
	
	/**
	 * @val 替换状态
	 */
	public void setThfs(Integer thfs) {
		this.thfs = thfs;
	}
	
	/**
	 * @val 公司确认状态 0|未确认;1|已确认
	 */
	public Integer getJdqrzt() {
		return jdqrzt;
	}
	
	/**
	 * @val 公司确认状态 0|未确认;1|已确认
	 */
	public void setJdqrzt(Integer jdqrzt) {
		this.jdqrzt = jdqrzt;
	}
	
	/**
	 * @val 确认情况
	 */
	public String getQrbzsm() {
		return qrbzsm;
	}
	
	/**
	 * @val 确认情况
	 */
	public void setQrbzsm(String qrbzsm) {
		this.qrbzsm = qrbzsm;
	}
	
	/**
	 * @val 最后下单日期
	 */
	public String getXdrq() {
		return xdrq;
	}
	
	/**
	 * @val 最后下单日期
	 */
	public void setXdrq(String xdrq) {
		this.xdrq = xdrq;
	}
	
	/**
	 * @val 移交状态
	 */
	public Integer getYjzt() {
		return yjzt;
	}
	
	/**
	 * @val 移交状态
	 */
	public void setYjzt(Integer yjzt) {
		this.yjzt = yjzt;
	}
	
	/**
	 * @val 移交日期
	 */
	public String getYjrq() {
		return yjrq;
	}
	
	/**
	 * @val 移交日期
	 */
	public void setYjrq(String yjrq) {
		this.yjrq = yjrq;
	}
	
}