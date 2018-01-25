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

@Alias("pmisProductInfo")
public class PmisProductInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id; 

    private Long fid;

    private Long grade;

    private String type;

    private String fdncode;

    private String code;

    private String name;

    private String py;

    private String bbh;

    private String gnms;

    private String xssx;

    private String cpxspx;

    private String cpdj;
    
    private String cptxName;
    
    private String cptx;

    private String cpzxsx;

    private String cplb;

    private Integer cplx;

    private Integer cpxz;

    private String ssgs;

    private Integer cpbz;

    private Integer xcpkhxs;

    private Integer zt;



	public String getCptxName() {
		return cptxName;
	}

	public void setCptxName(String cptxName) {
		this.cptxName = cptxName;
	}

	public PmisProductInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFdncode() {
        return fdncode;
    }

    public void setFdncode(String fdncode) {
        this.fdncode = fdncode;
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

    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh;
    }

    public String getGnms() {
        return gnms;
    }

    public void setGnms(String gnms) {
        this.gnms = gnms;
    }

    public String getXssx() {
        return xssx;
    }

    public void setXssx(String xssx) {
        this.xssx = xssx;
    }

    public String getCpxspx() {
        return cpxspx;
    }

    public void setCpxspx(String cpxspx) {
        this.cpxspx = cpxspx;
    }

    public String getCpdj() {
        return cpdj;
    }

    public void setCpdj(String cpdj) {
        this.cpdj = cpdj;
    }

    public String getCptx() {
        return cptx;
    }

    public void setCptx(String cptx) {
        this.cptx = cptx;
    }

    public String getCpzxsx() {
        return cpzxsx;
    }

    public void setCpzxsx(String cpzxsx) {
        this.cpzxsx = cpzxsx;
    }

    public String getCplb() {
        return cplb;
    }

    public void setCplb(String cplb) {
        this.cplb = cplb;
    }

    public Integer getCplx() {
        return cplx;
    }

    public void setCplx(Integer cplx) {
        this.cplx = cplx;
    }

    public Integer getCpxz() {
        return cpxz;
    }

    public void setCpxz(Integer cpxz) {
        this.cpxz = cpxz;
    }

    public String getSsgs() {
        return ssgs;
    }

    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }

    public Integer getCpbz() {
        return cpbz;
    }

    public void setCpbz(Integer cpbz) {
        this.cpbz = cpbz;
    }

    public Integer getXcpkhxs() {
        return xcpkhxs;
    }

    public void setXcpkhxs(Integer xcpkhxs) {
        this.xcpkhxs = xcpkhxs;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

}