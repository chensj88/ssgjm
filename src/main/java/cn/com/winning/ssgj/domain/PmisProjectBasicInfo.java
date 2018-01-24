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

@Alias("pmisProjectBasicInfo")
public class PmisProjectBasicInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String fwlx;

    private String xmlx;

    private String xmdj;

    private String name;

    private Integer khjdqc;

    private Integer khjdtj;

    private Integer khjdlj;

    private String qs;

    private String xmms;

    private String jhnr;

    private Long xmjl;

    private Integer zjzt;

    private Integer jhzt;

    private Long zkry;

    private Long ssgs;

    private Long jsdq;

    private Long ssjg;

    private Long khjg;

    private String htxx;

    private String htlx;

    private String bzsm;

    private String gzrq;

    private String gzsm;

    private Long khxx;

    private Long khsr;

    private Long ywckhsr;

    private Long khxs;

    private Long khxssr;

    private String wcrq;

    private String gxsj;

    private Long gxr;

    private Integer zt;

    private Integer thfs;

    private Integer jdqrzt;

    private String qrbzsm;

    private String xdrq;

    private String yjzt;

    private String yjrq;

    public PmisProjectBasicInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFwlx() {
        return fwlx;
    }

    public void setFwlx(String fwlx) {
        this.fwlx = fwlx;
    }

    public String getXmlx() {
        return xmlx;
    }

    public void setXmlx(String xmlx) {
        this.xmlx = xmlx;
    }

    public String getXmdj() {
        return xmdj;
    }

    public void setXmdj(String xmdj) {
        this.xmdj = xmdj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKhjdqc() {
        return khjdqc;
    }

    public void setKhjdqc(Integer khjdqc) {
        this.khjdqc = khjdqc;
    }

    public Integer getKhjdtj() {
        return khjdtj;
    }

    public void setKhjdtj(Integer khjdtj) {
        this.khjdtj = khjdtj;
    }

    public Integer getKhjdlj() {
        return khjdlj;
    }

    public void setKhjdlj(Integer khjdlj) {
        this.khjdlj = khjdlj;
    }

    public String getQs() {
        return qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public String getXmms() {
        return xmms;
    }

    public void setXmms(String xmms) {
        this.xmms = xmms;
    }

    public String getJhnr() {
        return jhnr;
    }

    public void setJhnr(String jhnr) {
        this.jhnr = jhnr;
    }

    public Long getXmjl() {
        return xmjl;
    }

    public void setXmjl(Long xmjl) {
        this.xmjl = xmjl;
    }

    public Integer getZjzt() {
        return zjzt;
    }

    public void setZjzt(Integer zjzt) {
        this.zjzt = zjzt;
    }

    public Integer getJhzt() {
        return jhzt;
    }

    public void setJhzt(Integer jhzt) {
        this.jhzt = jhzt;
    }

    public Long getZkry() {
        return zkry;
    }

    public void setZkry(Long zkry) {
        this.zkry = zkry;
    }

    public Long getSsgs() {
        return ssgs;
    }

    public void setSsgs(Long ssgs) {
        this.ssgs = ssgs;
    }

    public Long getJsdq() {
        return jsdq;
    }

    public void setJsdq(Long jsdq) {
        this.jsdq = jsdq;
    }

    public Long getSsjg() {
        return ssjg;
    }

    public void setSsjg(Long ssjg) {
        this.ssjg = ssjg;
    }

    public Long getKhjg() {
        return khjg;
    }

    public void setKhjg(Long khjg) {
        this.khjg = khjg;
    }

    public String getHtxx() {
        return htxx;
    }

    public void setHtxx(String htxx) {
        this.htxx = htxx;
    }

    public String getHtlx() {
        return htlx;
    }

    public void setHtlx(String htlx) {
        this.htlx = htlx;
    }

    public String getBzsm() {
        return bzsm;
    }

    public void setBzsm(String bzsm) {
        this.bzsm = bzsm;
    }

    public String getGzrq() {
        return gzrq;
    }

    public void setGzrq(String gzrq) {
        this.gzrq = gzrq;
    }

    public String getGzsm() {
        return gzsm;
    }

    public void setGzsm(String gzsm) {
        this.gzsm = gzsm;
    }

    public Long getKhxx() {
        return khxx;
    }

    public void setKhxx(Long khxx) {
        this.khxx = khxx;
    }

    public Long getKhsr() {
        return khsr;
    }

    public void setKhsr(Long khsr) {
        this.khsr = khsr;
    }

    public Long getYwckhsr() {
        return ywckhsr;
    }

    public void setYwckhsr(Long ywckhsr) {
        this.ywckhsr = ywckhsr;
    }

    public Long getKhxs() {
        return khxs;
    }

    public void setKhxs(Long khxs) {
        this.khxs = khxs;
    }

    public Long getKhxssr() {
        return khxssr;
    }

    public void setKhxssr(Long khxssr) {
        this.khxssr = khxssr;
    }

    public String getWcrq() {
        return wcrq;
    }

    public void setWcrq(String wcrq) {
        this.wcrq = wcrq;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public Long getGxr() {
        return gxr;
    }

    public void setGxr(Long gxr) {
        this.gxr = gxr;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getThfs() {
        return thfs;
    }

    public void setThfs(Integer thfs) {
        this.thfs = thfs;
    }

    public Integer getJdqrzt() {
        return jdqrzt;
    }

    public void setJdqrzt(Integer jdqrzt) {
        this.jdqrzt = jdqrzt;
    }

    public String getQrbzsm() {
        return qrbzsm;
    }

    public void setQrbzsm(String qrbzsm) {
        this.qrbzsm = qrbzsm;
    }

    public String getXdrq() {
        return xdrq;
    }

    public void setXdrq(String xdrq) {
        this.xdrq = xdrq;
    }

    public String getYjzt() {
        return yjzt;
    }

    public void setYjzt(String yjzt) {
        this.yjzt = yjzt;
    }

    public String getYjrq() {
        return yjrq;
    }

    public void setYjrq(String yjrq) {
        this.yjrq = yjrq;
    }

}