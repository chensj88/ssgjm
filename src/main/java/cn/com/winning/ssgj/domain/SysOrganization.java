package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 * 接口表--机构信息表
 * @author SSGJ
 * @date 2018-01-18 10:11:46
 */

@Alias("sysOrganization")
public class SysOrganization extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long fid;

    private Long grade;

    private Integer jglx;

    private String orgcode;

    private String name;

    private String jgqc;

    private String py;

    private String fdncode;

    private String bbxh;

    private Long yjbm;

    private Long ssgs;

    private Integer zt;

    private Date gxsj;

    public SysOrganization() {

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

    public Integer getJglx() {
        return jglx;
    }

    public void setJglx(Integer jglx) {
        this.jglx = jglx;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJgqc() {
        return jgqc;
    }

    public void setJgqc(String jgqc) {
        this.jgqc = jgqc;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getFdncode() {
        return fdncode;
    }

    public void setFdncode(String fdncode) {
        this.fdncode = fdncode;
    }

    public String getBbxh() {
        return bbxh;
    }

    public void setBbxh(String bbxh) {
        this.bbxh = bbxh;
    }

    public Long getYjbm() {
        return yjbm;
    }

    public void setYjbm(Long yjbm) {
        this.yjbm = yjbm;
    }

    public Long getSsgs() {
        return ssgs;
    }

    public void setSsgs(Long ssgs) {
        this.ssgs = ssgs;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

}