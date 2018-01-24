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

@Alias("pmisProductLineInfo")
public class PmisProductLineInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String name;

    private Integer zt;

    private Integer lx;

    private String yxtnm;

    private String mklx;

    private String sx;

    private String hsdy;

    private String cpz;

    private String cpdm;

    private String cpxl;

    private String cpdl;

    public PmisProductLineInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    public String getYxtnm() {
        return yxtnm;
    }

    public void setYxtnm(String yxtnm) {
        this.yxtnm = yxtnm;
    }

    public String getMklx() {
        return mklx;
    }

    public void setMklx(String mklx) {
        this.mklx = mklx;
    }

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    public String getHsdy() {
        return hsdy;
    }

    public void setHsdy(String hsdy) {
        this.hsdy = hsdy;
    }

    public String getCpz() {
        return cpz;
    }

    public void setCpz(String cpz) {
        this.cpz = cpz;
    }

    public String getCpdm() {
        return cpdm;
    }

    public void setCpdm(String cpdm) {
        this.cpdm = cpdm;
    }

    public String getCpxl() {
        return cpxl;
    }

    public void setCpxl(String cpxl) {
        this.cpxl = cpxl;
    }

    public String getCpdl() {
        return cpdl;
    }

    public void setCpdl(String cpdl) {
        this.cpdl = cpdl;
    }

}