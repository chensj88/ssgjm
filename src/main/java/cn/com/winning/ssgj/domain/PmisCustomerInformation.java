package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import cn.com.winning.ssgj.domain.expand.NodeTree;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-24 10:57:36
 */
 
@Alias("pmisCustomerInformation")
public class PmisCustomerInformation extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * @val ID
	 */
	private Long id;
	
	/**
	 * @val 客户编码
	 */
	private String code;
	
	/**
	 * @val 客户名称
	 */
	private String name;
	
	/**
	 * @val 拼音简写
	 */
	private String py;
	
	/**
	 * @val 客户类别 1 |新客户;2|老客户
	 */
	private Integer khlb;
	
	/**
	 * @val 客户类型 1|医院;2|医疗机构;3|公司
	 */
	private String khlx;
	
	/**
	 * @val 地区
	 */
	private String qyxx;
	
	/**
	 * @val 城市
	 */
	private String city;
	
	/**
	 * @val 是否筹建中
	 */
	private String sfcj;
	
	/**
	 * @val 医院类型
	 */
	private String yylx;
	
	/**
	 * @val 门诊量
	 */
	private Long mzl;
	
	/**
	 * @val 床位数
	 */
	private Long cws;
	
	/**
	 * @val 二三级医院数
	 */
	private Long yysl;
	
	/**
	 * @val 卫生服务机构数
	 */
	private Long fwjgs;
	
	/**
	 * @val 主营业务
	 */
	private String zyyw;
	
	/**
	 * @val 公司规模
	 */
	private String gsgm;
	
	/**
	 * @val 所属公司
	 */
	private String ssgs;
	
	/**
	 * @val 实施机构
	 */
	private String ssjg;
	
	/**
	 * @val 医院等级
	 */
	private String yydj;
	
	/**
	 * @val 医院规模 1|小型;2|中型;3|大型;4|特大型
	 */
	private String yygm;
	
	/**
	 * @val 医院报价等级
	 */
	private String yybjdj;
	
	/**
	 * @val 地址
	 */
	private String khdz;
	
	/**
	 * @val 邮编
	 */
	private String yzbm;
	
	/**
	 * @val 信息科长
	 */
	private String xxkz;
	
	/**
	 * @val 主管院长
	 */
	private String zgyz;
	
	/**
	 * @val 是否设置客户经理
	 */
	private String sfszkhjl;
	
	/**
	 * @val 常驻客户经理
	 */
	private String khjl;
	
	/**
	 * @val 使用产品条线
	 */
	private String sycptx;
	
	/**
	 * @val 服务台人员(事件)
	 */
	private String fwtry;
	
	/**
	 * @val 重点客户标志
	 */
	private String zdkhbz;
	
	/**
	 * @val 联系人
	 */
	private String lxr;
	
	/**
	 * @val 联系方式
	 */
	private String lxfs;
	
	/**
	 * @val 关联公司
	 */
	private String glgs;
	
	/**
	 * @val 备注
	 */
	private String remark;
	
	/**
	 * @val 最后更新人
	 */
	private String gxr;
	
	/**
	 * @val 最后更新时间
	 */
	private String gxsj;
	
	/**
	 * @val 登记人
	 */
	private String djr;
	
	/**
	 * @val 登记时间
	 */
	private String djsj;
	
	/**
	 * @val 状态 1|生效;2|作废
	 */
	private Integer zt;

	private NodeTree nodeTree = new NodeTree();
	
	public PmisCustomerInformation() {

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
	 * @val 客户编码
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @val 客户编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @val 客户名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 客户名称
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
	 * @val 客户类别 1 |新客户;2|老客户
	 */
	public Integer getKhlb() {
		return khlb;
	}
	
	/**
	 * @val 客户类别 1 |新客户;2|老客户
	 */
	public void setKhlb(Integer khlb) {
		this.khlb = khlb;
	}
	
	/**
	 * @val 客户类型 1|医院;2|医疗机构;3|公司
	 */
	public String getKhlx() {
		return khlx;
	}
	
	/**
	 * @val 客户类型 1|医院;2|医疗机构;3|公司
	 */
	public void setKhlx(String khlx) {
		this.khlx = khlx;
	}
	
	/**
	 * @val 地区
	 */
	public String getQyxx() {
		return qyxx;
	}
	
	/**
	 * @val 地区
	 */
	public void setQyxx(String qyxx) {
		this.qyxx = qyxx;
	}
	
	/**
	 * @val 城市
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @val 城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @val 是否筹建中
	 */
	public String getSfcj() {
		return sfcj;
	}
	
	/**
	 * @val 是否筹建中
	 */
	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}
	
	/**
	 * @val 医院类型
	 */
	public String getYylx() {
		return yylx;
	}
	
	/**
	 * @val 医院类型
	 */
	public void setYylx(String yylx) {
		this.yylx = yylx;
	}
	
	/**
	 * @val 门诊量
	 */
	public Long getMzl() {
		return mzl;
	}
	
	/**
	 * @val 门诊量
	 */
	public void setMzl(Long mzl) {
		this.mzl = mzl;
	}
	
	/**
	 * @val 床位数
	 */
	public Long getCws() {
		return cws;
	}
	
	/**
	 * @val 床位数
	 */
	public void setCws(Long cws) {
		this.cws = cws;
	}
	
	/**
	 * @val 二三级医院数
	 */
	public Long getYysl() {
		return yysl;
	}
	
	/**
	 * @val 二三级医院数
	 */
	public void setYysl(Long yysl) {
		this.yysl = yysl;
	}
	
	/**
	 * @val 卫生服务机构数
	 */
	public Long getFwjgs() {
		return fwjgs;
	}
	
	/**
	 * @val 卫生服务机构数
	 */
	public void setFwjgs(Long fwjgs) {
		this.fwjgs = fwjgs;
	}
	
	/**
	 * @val 主营业务
	 */
	public String getZyyw() {
		return zyyw;
	}
	
	/**
	 * @val 主营业务
	 */
	public void setZyyw(String zyyw) {
		this.zyyw = zyyw;
	}
	
	/**
	 * @val 公司规模
	 */
	public String getGsgm() {
		return gsgm;
	}
	
	/**
	 * @val 公司规模
	 */
	public void setGsgm(String gsgm) {
		this.gsgm = gsgm;
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
	 * @val 实施机构
	 */
	public String getSsjg() {
		return ssjg;
	}
	
	/**
	 * @val 实施机构
	 */
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}
	
	/**
	 * @val 医院等级
	 */
	public String getYydj() {
		return yydj;
	}
	
	/**
	 * @val 医院等级
	 */
	public void setYydj(String yydj) {
		this.yydj = yydj;
	}
	
	/**
	 * @val 医院规模 1|小型;2|中型;3|大型;4|特大型
	 */
	public String getYygm() {
		return yygm;
	}
	
	/**
	 * @val 医院规模 1|小型;2|中型;3|大型;4|特大型
	 */
	public void setYygm(String yygm) {
		this.yygm = yygm;
	}
	
	/**
	 * @val 医院报价等级
	 */
	public String getYybjdj() {
		return yybjdj;
	}
	
	/**
	 * @val 医院报价等级
	 */
	public void setYybjdj(String yybjdj) {
		this.yybjdj = yybjdj;
	}
	
	/**
	 * @val 地址
	 */
	public String getKhdz() {
		return khdz;
	}
	
	/**
	 * @val 地址
	 */
	public void setKhdz(String khdz) {
		this.khdz = khdz;
	}
	
	/**
	 * @val 邮编
	 */
	public String getYzbm() {
		return yzbm;
	}
	
	/**
	 * @val 邮编
	 */
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	
	/**
	 * @val 信息科长
	 */
	public String getXxkz() {
		return xxkz;
	}
	
	/**
	 * @val 信息科长
	 */
	public void setXxkz(String xxkz) {
		this.xxkz = xxkz;
	}
	
	/**
	 * @val 主管院长
	 */
	public String getZgyz() {
		return zgyz;
	}
	
	/**
	 * @val 主管院长
	 */
	public void setZgyz(String zgyz) {
		this.zgyz = zgyz;
	}
	
	/**
	 * @val 是否设置客户经理
	 */
	public String getSfszkhjl() {
		return sfszkhjl;
	}
	
	/**
	 * @val 是否设置客户经理
	 */
	public void setSfszkhjl(String sfszkhjl) {
		this.sfszkhjl = sfszkhjl;
	}
	
	/**
	 * @val 常驻客户经理
	 */
	public String getKhjl() {
		return khjl;
	}
	
	/**
	 * @val 常驻客户经理
	 */
	public void setKhjl(String khjl) {
		this.khjl = khjl;
	}
	
	/**
	 * @val 使用产品条线
	 */
	public String getSycptx() {
		return sycptx;
	}
	
	/**
	 * @val 使用产品条线
	 */
	public void setSycptx(String sycptx) {
		this.sycptx = sycptx;
	}
	
	/**
	 * @val 服务台人员(事件)
	 */
	public String getFwtry() {
		return fwtry;
	}
	
	/**
	 * @val 服务台人员(事件)
	 */
	public void setFwtry(String fwtry) {
		this.fwtry = fwtry;
	}
	
	/**
	 * @val 重点客户标志
	 */
	public String getZdkhbz() {
		return zdkhbz;
	}
	
	/**
	 * @val 重点客户标志
	 */
	public void setZdkhbz(String zdkhbz) {
		this.zdkhbz = zdkhbz;
	}
	
	/**
	 * @val 联系人
	 */
	public String getLxr() {
		return lxr;
	}
	
	/**
	 * @val 联系人
	 */
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	
	/**
	 * @val 联系方式
	 */
	public String getLxfs() {
		return lxfs;
	}
	
	/**
	 * @val 联系方式
	 */
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	
	/**
	 * @val 关联公司
	 */
	public String getGlgs() {
		return glgs;
	}
	
	/**
	 * @val 关联公司
	 */
	public void setGlgs(String glgs) {
		this.glgs = glgs;
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
	
	/**
	 * @val 最后更新人
	 */
	public String getGxr() {
		return gxr;
	}
	
	/**
	 * @val 最后更新人
	 */
	public void setGxr(String gxr) {
		this.gxr = gxr;
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
	 * @val 登记人
	 */
	public String getDjr() {
		return djr;
	}
	
	/**
	 * @val 登记人
	 */
	public void setDjr(String djr) {
		this.djr = djr;
	}
	
	/**
	 * @val 登记时间
	 */
	public String getDjsj() {
		return djsj;
	}
	
	/**
	 * @val 登记时间
	 */
	public void setDjsj(String djsj) {
		this.djsj = djsj;
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

	public NodeTree getNodeTree() {
		nodeTree.setId(id);
		nodeTree.setNodeId(id);
		nodeTree.setText(code+"_"+name);
		return nodeTree;
	}

	public void setNodeTree(NodeTree nodeTree) {
		this.nodeTree = nodeTree;
	}
}