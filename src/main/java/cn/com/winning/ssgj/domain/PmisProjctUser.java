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

@Alias("pmisProjctUser")
public class PmisProjctUser extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long xmlcb;

    private Integer ryfl;

    private Long ry;

    public PmisProjctUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getXmlcb() {
        return xmlcb;
    }

    public void setXmlcb(Long xmlcb) {
        this.xmlcb = xmlcb;
    }

    public Integer getRyfl() {
        return ryfl;
    }

    public void setRyfl(Integer ryfl) {
        this.ryfl = ryfl;
    }

    public Long getRy() {
        return ry;
    }

    public void setRy(Long ry) {
        this.ry = ry;
    }

}