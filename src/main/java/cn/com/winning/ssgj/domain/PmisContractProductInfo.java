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

@Alias("pmisContractProductInfo")
public class PmisContractProductInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long khxx;

    private Integer htcplb;

    private Long fbjelx;

    private String cpdlmc;

    private String zxtmc;

    private String cpmc;

    private Long zhtxx;

    private Long htxx;

    private Long xmlcb;

    private Long xmfqjh;

    private Long htmk;

    private Long htyhf;

    private Long cpxx;

    private Long cpzxt;

    private Long sjcp;

    private Long cpjzj;

    private Integer bjxs;

    private Integer zt;

    public PmisContractProductInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKhxx() {
        return khxx;
    }

    public void setKhxx(Long khxx) {
        this.khxx = khxx;
    }

    public Integer getHtcplb() {
        return htcplb;
    }

    public void setHtcplb(Integer htcplb) {
        this.htcplb = htcplb;
    }

    public Long getFbjelx() {
        return fbjelx;
    }

    public void setFbjelx(Long fbjelx) {
        this.fbjelx = fbjelx;
    }

    public String getCpdlmc() {
        return cpdlmc;
    }

    public void setCpdlmc(String cpdlmc) {
        this.cpdlmc = cpdlmc;
    }

    public String getZxtmc() {
        return zxtmc;
    }

    public void setZxtmc(String zxtmc) {
        this.zxtmc = zxtmc;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public Long getZhtxx() {
        return zhtxx;
    }

    public void setZhtxx(Long zhtxx) {
        this.zhtxx = zhtxx;
    }

    public Long getHtxx() {
        return htxx;
    }

    public void setHtxx(Long htxx) {
        this.htxx = htxx;
    }

    public Long getXmlcb() {
        return xmlcb;
    }

    public void setXmlcb(Long xmlcb) {
        this.xmlcb = xmlcb;
    }

    public Long getXmfqjh() {
        return xmfqjh;
    }

    public void setXmfqjh(Long xmfqjh) {
        this.xmfqjh = xmfqjh;
    }

    public Long getHtmk() {
        return htmk;
    }

    public void setHtmk(Long htmk) {
        this.htmk = htmk;
    }

    public Long getHtyhf() {
        return htyhf;
    }

    public void setHtyhf(Long htyhf) {
        this.htyhf = htyhf;
    }

    public Long getCpxx() {
        return cpxx;
    }

    public void setCpxx(Long cpxx) {
        this.cpxx = cpxx;
    }

    public Long getCpzxt() {
        return cpzxt;
    }

    public void setCpzxt(Long cpzxt) {
        this.cpzxt = cpzxt;
    }

    public Long getSjcp() {
        return sjcp;
    }

    public void setSjcp(Long sjcp) {
        this.sjcp = sjcp;
    }

    public Long getCpjzj() {
        return cpjzj;
    }

    public void setCpjzj(Long cpjzj) {
        this.cpjzj = cpjzj;
    }

    public Integer getBjxs() {
        return bjxs;
    }

    public void setBjxs(Integer bjxs) {
        this.bjxs = bjxs;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

}