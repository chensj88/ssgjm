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

    private Integer fwlx;

    private String xmlx;

    private Integer xmdj;

    private String name;

    private String khjdqc;

    private String khjdtj;

    private String khjdlj;

    private Integer qs;

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

    private Long htxx;

    private Long htlx;

    private String bzsm;

    private String gzrq;

    private String gzsm;

    private Long khxx;

    private String khsr;

    private String ywckhsr;

    private String khxs;

    private String khxssr;

    private String wcrq;

    private String gxsj;

    private Long gxr;

    private Integer zt;

    private Integer thfs;

    private Integer jdqrzt;

    private String qrbzsm;

    private String xdrq;

    private Integer yjzt;

    private String yjrq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFwlx() {
        return fwlx;
    }

    public void setFwlx(Integer fwlx) {
        this.fwlx = fwlx;
    }

    public String getXmlx() {
        return xmlx;
    }

    public void setXmlx(String xmlx) {
        this.xmlx = xmlx == null ? null : xmlx.trim();
    }

    public Integer getXmdj() {
        return xmdj;
    }

    public void setXmdj(Integer xmdj) {
        this.xmdj = xmdj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKhjdqc() {
        return khjdqc;
    }

    public void setKhjdqc(String khjdqc) {
        this.khjdqc = khjdqc == null ? null : khjdqc.trim();
    }

    public String getKhjdtj() {
        return khjdtj;
    }

    public void setKhjdtj(String khjdtj) {
        this.khjdtj = khjdtj == null ? null : khjdtj.trim();
    }

    public String getKhjdlj() {
        return khjdlj;
    }

    public void setKhjdlj(String khjdlj) {
        this.khjdlj = khjdlj == null ? null : khjdlj.trim();
    }

    public Integer getQs() {
        return qs;
    }

    public void setQs(Integer qs) {
        this.qs = qs;
    }

    public String getXmms() {
        return xmms;
    }

    public void setXmms(String xmms) {
        this.xmms = xmms == null ? null : xmms.trim();
    }

    public String getJhnr() {
        return jhnr;
    }

    public void setJhnr(String jhnr) {
        this.jhnr = jhnr == null ? null : jhnr.trim();
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

    public Long getHtxx() {
        return htxx;
    }

    public void setHtxx(Long htxx) {
        this.htxx = htxx;
    }

    public Long getHtlx() {
        return htlx;
    }

    public void setHtlx(Long htlx) {
        this.htlx = htlx;
    }

    public String getBzsm() {
        return bzsm;
    }

    public void setBzsm(String bzsm) {
        this.bzsm = bzsm == null ? null : bzsm.trim();
    }

    public String getGzrq() {
        return gzrq;
    }

    public void setGzrq(String gzrq) {
        this.gzrq = gzrq == null ? null : gzrq.trim();
    }

    public String getGzsm() {
        return gzsm;
    }

    public void setGzsm(String gzsm) {
        this.gzsm = gzsm == null ? null : gzsm.trim();
    }

    public Long getKhxx() {
        return khxx;
    }

    public void setKhxx(Long khxx) {
        this.khxx = khxx;
    }

    public String getKhsr() {
        return khsr;
    }

    public void setKhsr(String khsr) {
        this.khsr = khsr == null ? null : khsr.trim();
    }

    public String getYwckhsr() {
        return ywckhsr;
    }

    public void setYwckhsr(String ywckhsr) {
        this.ywckhsr = ywckhsr == null ? null : ywckhsr.trim();
    }

    public String getKhxs() {
        return khxs;
    }

    public void setKhxs(String khxs) {
        this.khxs = khxs == null ? null : khxs.trim();
    }

    public String getKhxssr() {
        return khxssr;
    }

    public void setKhxssr(String khxssr) {
        this.khxssr = khxssr == null ? null : khxssr.trim();
    }

    public String getWcrq() {
        return wcrq;
    }

    public void setWcrq(String wcrq) {
        this.wcrq = wcrq == null ? null : wcrq.trim();
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj == null ? null : gxsj.trim();
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
        this.qrbzsm = qrbzsm == null ? null : qrbzsm.trim();
    }

    public String getXdrq() {
        return xdrq;
    }

    public void setXdrq(String xdrq) {
        this.xdrq = xdrq == null ? null : xdrq.trim();
    }

    public Integer getYjzt() {
        return yjzt;
    }

    public void setYjzt(Integer yjzt) {
        this.yjzt = yjzt;
    }

    public String getYjrq() {
        return yjrq;
    }

    public void setYjrq(String yjrq) {
        this.yjrq = yjrq == null ? null : yjrq.trim();
    }

}