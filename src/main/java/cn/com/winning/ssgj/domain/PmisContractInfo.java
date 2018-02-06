package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 * 接口表--合同信息表
 * @author SSGJ
 * @date 2018-01-18 10:11:45
 */

@Alias("pmisContractInfo")
public class PmisContractInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long zhtxx;

    private Long htyhf;

    private String code;

    private String damc;

    private String name;

    private String sqrq;

    private String sxrq;

    private String qdny;

    private String nf;

    private Long htlx;

    private Integer kplx;

    private Integer xmlx;

    private Integer sfgq;

    private String htje;

    private Long khxx;

    private String htqyf;

    private Long qygs;

    private Long htqdry;

    private Long htgzry;

    private Long xsssjg;

    private Long zbjg;

    private Long xsssgs;

    public PmisContractInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getZhtxx() {
        return zhtxx;
    }

    public void setZhtxx(Long zhtxx) {
        this.zhtxx = zhtxx;
    }

    public Long getHtyhf() {
        return htyhf;
    }

    public void setHtyhf(Long htyhf) {
        this.htyhf = htyhf;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDamc() {
        return damc;
    }

    public void setDamc(String damc) {
        this.damc = damc == null ? null : damc.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSqrq() {
        return sqrq;
    }

    public void setSqrq(String sqrq) {
        this.sqrq = sqrq == null ? null : sqrq.trim();
    }

    public String getSxrq() {
        return sxrq;
    }

    public void setSxrq(String sxrq) {
        this.sxrq = sxrq == null ? null : sxrq.trim();
    }

    public String getQdny() {
        return qdny;
    }

    public void setQdny(String qdny) {
        this.qdny = qdny == null ? null : qdny.trim();
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf == null ? null : nf.trim();
    }

    public Long getHtlx() {
        return htlx;
    }

    public void setHtlx(Long htlx) {
        this.htlx = htlx;
    }

    public Integer getKplx() {
        return kplx;
    }

    public void setKplx(Integer kplx) {
        this.kplx = kplx;
    }

    public Integer getXmlx() {
        return xmlx;
    }

    public void setXmlx(Integer xmlx) {
        this.xmlx = xmlx;
    }

    public Integer getSfgq() {
        return sfgq;
    }

    public void setSfgq(Integer sfgq) {
        this.sfgq = sfgq;
    }

    public String getHtje() {
        return htje;
    }

    public void setHtje(String htje) {
        this.htje = htje == null ? null : htje.trim();
    }

    public Long getKhxx() {
        return khxx;
    }

    public void setKhxx(Long khxx) {
        this.khxx = khxx;
    }

    public String getHtqyf() {
        return htqyf;
    }

    public void setHtqyf(String htqyf) {
        this.htqyf = htqyf == null ? null : htqyf.trim();
    }

    public Long getQygs() {
        return qygs;
    }

    public void setQygs(Long qygs) {
        this.qygs = qygs;
    }

    public Long getHtqdry() {
        return htqdry;
    }

    public void setHtqdry(Long htqdry) {
        this.htqdry = htqdry;
    }

    public Long getHtgzry() {
        return htgzry;
    }

    public void setHtgzry(Long htgzry) {
        this.htgzry = htgzry;
    }

    public Long getXsssjg() {
        return xsssjg;
    }

    public void setXsssjg(Long xsssjg) {
        this.xsssjg = xsssjg;
    }

    public Long getZbjg() {
        return zbjg;
    }

    public void setZbjg(Long zbjg) {
        this.zbjg = zbjg;
    }

    public Long getXsssgs() {
        return xsssgs;
    }

    public void setXsssgs(Long xsssgs) {
        this.xsssgs = xsssgs;
    }

}