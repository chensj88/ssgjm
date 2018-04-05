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
 
@Alias("pmisProductInfo")
public class PmisProductInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 上级节点
	 */
	private Long fid;
	
	/**
	 * @val 节点级别
	 */
	private Long grade;
	
	/**
	 * @val 节点类型 0|普通节点;1|根节点;2|末节点
	 */
	private String type;
	
	/**
	 * @val 上级域编码
	 */
	private String fdncode;
	
	/**
	 * @val 产品编号
	 */
	private String code;
	
	/**
	 * @val 产品名称
	 */
	private String name;
	
	/**
	 * @val 拼音简写
	 */
	private String py;
	
	/**
	 * @val 版本号
	 */
	private String bbh;
	
	/**
	 * @val 功能简述
	 */
	private String gnms;
	
	/**
	 * @val 显示顺序
	 */
	private String xssx;
	
	/**
	 * @val 报表序号
	 */
	private String cpxspx;
	
	/**
	 * @val 产品等级
	 */
	private String cpdj;

	/**
	 * @val 产品条线名称
	 */
	private String cptxName;
	/**
	 * @val 产品条线
	 */
	private String cptx;
	
	/**
	 * @val 专项属性
	 */
	private String cpzxsx;
	
	/**
	 * @val 产品类别
	 */
	private String cplb;
	
	/**
	 * @val 产品类型 1|自主开发;2|外购 
	 */
	private Integer cplx;
	
	/**
	 * @val 产品性质 0|基本;1|扩展
	 */
	private Integer cpxz;
	
	/**
	 * @val 所属公司
	 */
	private String ssgs;
	
	/**
	 * @val 产品标识 0|旧产品;1|新产品
	 */
	private Integer cpbz;
	
	/**
	 * @val 新产品考核系数
	 */
	private String xcpkhxs;
	
	/**
	 * @val 状态 1|生效;2|作废
	 */
	private Integer zt;
	
	public PmisProductInfo() {

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
	 * @val 上级节点
	 */
	public Long getFid() {
		return fid;
	}
	
	/**
	 * @val 上级节点
	 */
	public void setFid(Long fid) {
		this.fid = fid;
	}
	
	/**
	 * @val 节点级别
	 */
	public Long getGrade() {
		return grade;
	}
	
	/**
	 * @val 节点级别
	 */
	public void setGrade(Long grade) {
		this.grade = grade;
	}
	
	/**
	 * @val 节点类型 0|普通节点;1|根节点;2|末节点
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @val 节点类型 0|普通节点;1|根节点;2|末节点
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @val 上级域编码
	 */
	public String getFdncode() {
		return fdncode;
	}
	
	/**
	 * @val 上级域编码
	 */
	public void setFdncode(String fdncode) {
		this.fdncode = fdncode;
	}
	
	/**
	 * @val 产品编号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @val 产品编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @val 产品名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 产品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @val 拼音简写
	 */
	public String getPy() {
		return py;
	}
	
	/**
	 * @val 拼音简写
	 */
	public void setPy(String py) {
		this.py = py;
	}
	
	/**
	 * @val 版本号
	 */
	public String getBbh() {
		return bbh;
	}
	
	/**
	 * @val 版本号
	 */
	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	
	/**
	 * @val 功能简述
	 */
	public String getGnms() {
		return gnms;
	}
	
	/**
	 * @val 功能简述
	 */
	public void setGnms(String gnms) {
		this.gnms = gnms;
	}
	
	/**
	 * @val 显示顺序
	 */
	public String getXssx() {
		return xssx;
	}
	
	/**
	 * @val 显示顺序
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	
	/**
	 * @val 报表序号
	 */
	public String getCpxspx() {
		return cpxspx;
	}
	
	/**
	 * @val 报表序号
	 */
	public void setCpxspx(String cpxspx) {
		this.cpxspx = cpxspx;
	}
	
	/**
	 * @val 产品等级
	 */
	public String getCpdj() {
		return cpdj;
	}
	
	/**
	 * @val 产品等级
	 */
	public void setCpdj(String cpdj) {
		this.cpdj = cpdj;
	}
	
	/**
	 * @val 产品条线
	 */
	public String getCptx() {
		return cptx;
	}
	
	/**
	 * @val 产品条线
	 */
	public void setCptx(String cptx) {
		this.cptx = cptx;
	}
	
	/**
	 * @val 专项属性
	 */
	public String getCpzxsx() {
		return cpzxsx;
	}
	
	/**
	 * @val 专项属性
	 */
	public void setCpzxsx(String cpzxsx) {
		this.cpzxsx = cpzxsx;
	}
	
	/**
	 * @val 产品类别
	 */
	public String getCplb() {
		return cplb;
	}
	
	/**
	 * @val 产品类别
	 */
	public void setCplb(String cplb) {
		this.cplb = cplb;
	}
	
	/**
	 * @val 产品类型 1|自主开发;2|外购 
	 */
	public Integer getCplx() {
		return cplx;
	}
	
	/**
	 * @val 产品类型 1|自主开发;2|外购 
	 */
	public void setCplx(Integer cplx) {
		this.cplx = cplx;
	}
	
	/**
	 * @val 产品性质 0|基本;1|扩展
	 */
	public Integer getCpxz() {
		return cpxz;
	}
	
	/**
	 * @val 产品性质 0|基本;1|扩展
	 */
	public void setCpxz(Integer cpxz) {
		this.cpxz = cpxz;
	}
	
	/**
	 * @val 所属公司
	 */
	public String getSsgs() {
		return ssgs;
	}
	
	/**
	 * @val 所属公司
	 */
	public void setSsgs(String ssgs) {
		this.ssgs = ssgs;
	}
	
	/**
	 * @val 产品标识 0|旧产品;1|新产品
	 */
	public Integer getCpbz() {
		return cpbz;
	}
	
	/**
	 * @val 产品标识 0|旧产品;1|新产品
	 */
	public void setCpbz(Integer cpbz) {
		this.cpbz = cpbz;
	}
	
	/**
	 * @val 新产品考核系数
	 */
	public String getXcpkhxs() {
		return xcpkhxs;
	}
	
	/**
	 * @val 新产品考核系数
	 */
	public void setXcpkhxs(String xcpkhxs) {
		this.xcpkhxs = xcpkhxs;
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
	 * @val 产品条线名称
	 */
	public String getCptxName() {
		return cptxName;
	}
	/**
	 * @val 产品条线名称
	 */
	public void setCptxName(String cptxName) {
		this.cptxName = cptxName;
	}
	
}