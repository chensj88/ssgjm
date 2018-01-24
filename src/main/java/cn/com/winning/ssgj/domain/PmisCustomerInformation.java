package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:46
 */

@Alias("pmisCustomerInformation")
public class PmisCustomerInformation extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String code;

    private String name;

    private String py;

    private Integer khlb;

    private String khlx;

    private String qyxx;

    private String city;

    private String sfcj;

    private String yylx;

    private Long mzl;

    private Long cws;

    private Long yysl;

    private Long fwjgs;

    private String zyyw;

    private String gsgm;

    private String ssgs;

    private String ssjg;

    private String yydj;

    private String yygm;

    private String yybjdj;

    private String khdz;

    private String yzbm;

    private String xxkz;

    private String zgyz;

    private String sfszkhjl;

    private String khjl;

    private String sycptx;

    private String fwtry;

    private String zdkhbz;

    private String lxr;

    private String lxfs;

    private String glgs;

    private String remark;

    private String gxr;

    private String gxsj;

    private String djr;

    private String djsj;

    private Integer zt;

    public PmisCustomerInformation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public Integer getKhlb() {
        return khlb;
    }

    public void setKhlb(Integer khlb) {
        this.khlb = khlb;
    }

    public String getKhlx() {
        return khlx;
    }

    public void setKhlx(String khlx) {
        this.khlx = khlx;
    }

    public String getQyxx() {
        return qyxx;
    }

    public void setQyxx(String qyxx) {
        this.qyxx = qyxx;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSfcj() {
        return sfcj;
    }

    public void setSfcj(String sfcj) {
        this.sfcj = sfcj;
    }

    public String getYylx() {
        return yylx;
    }

    public void setYylx(String yylx) {
        this.yylx = yylx;
    }

    public Long getMzl() {
        return mzl;
    }

    public void setMzl(Long mzl) {
        this.mzl = mzl;
    }

    public Long getCws() {
        return cws;
    }

    public void setCws(Long cws) {
        this.cws = cws;
    }

    public Long getYysl() {
        return yysl;
    }

    public void setYysl(Long yysl) {
        this.yysl = yysl;
    }

    public Long getFwjgs() {
        return fwjgs;
    }

    public void setFwjgs(Long fwjgs) {
        this.fwjgs = fwjgs;
    }

    public String getZyyw() {
        return zyyw;
    }

    public void setZyyw(String zyyw) {
        this.zyyw = zyyw;
    }

    public String getGsgm() {
        return gsgm;
    }

    public void setGsgm(String gsgm) {
        this.gsgm = gsgm;
    }

    public String getSsgs() {
        return ssgs;
    }

    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }

    public String getSsjg() {
        return ssjg;
    }

    public void setSsjg(String ssjg) {
        this.ssjg = ssjg;
    }

    public String getYydj() {
        return yydj;
    }

    public void setYydj(String yydj) {
        this.yydj = yydj;
    }

    public String getYygm() {
        return yygm;
    }

    public void setYygm(String yygm) {
        this.yygm = yygm;
    }

    public String getYybjdj() {
        return yybjdj;
    }

    public void setYybjdj(String yybjdj) {
        this.yybjdj = yybjdj;
    }

    public String getKhdz() {
        return khdz;
    }

    public void setKhdz(String khdz) {
        this.khdz = khdz;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getXxkz() {
        return xxkz;
    }

    public void setXxkz(String xxkz) {
        this.xxkz = xxkz;
    }

    public String getZgyz() {
        return zgyz;
    }

    public void setZgyz(String zgyz) {
        this.zgyz = zgyz;
    }

    public String getSfszkhjl() {
        return sfszkhjl;
    }

    public void setSfszkhjl(String sfszkhjl) {
        this.sfszkhjl = sfszkhjl;
    }

    public String getKhjl() {
        return khjl;
    }

    public void setKhjl(String khjl) {
        this.khjl = khjl;
    }

    public String getSycptx() {
        return sycptx;
    }

    public void setSycptx(String sycptx) {
        this.sycptx = sycptx;
    }

    public String getFwtry() {
        return fwtry;
    }

    public void setFwtry(String fwtry) {
        this.fwtry = fwtry;
    }

    public String getZdkhbz() {
        return zdkhbz;
    }

    public void setZdkhbz(String zdkhbz) {
        this.zdkhbz = zdkhbz;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getGlgs() {
        return glgs;
    }

    public void setGlgs(String glgs) {
        this.glgs = glgs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGxr() {
        return gxr;
    }

    public void setGxr(String gxr) {
        this.gxr = gxr;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    public String getDjsj() {
        return djsj;
    }

    public void setDjsj(String djsj) {
        this.djsj = djsj;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

}