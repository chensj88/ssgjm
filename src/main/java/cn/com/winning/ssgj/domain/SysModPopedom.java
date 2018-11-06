package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import cn.com.winning.ssgj.domain.expand.NodeTree;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:46
 */

@Alias("sysModPopedom")
public class SysModPopedom extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long modId;

    private Long modLevel;

    private Long roleId;

    private String popedomCode;

    private String modUrl;

    private Long modPid;

    public SysModPopedom() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModId() {
        return modId;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }

    public Long getModLevel() {
        return modLevel;
    }

    public void setModLevel(Long modLevel) {
        this.modLevel = modLevel;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPopedomCode() {
        return popedomCode;
    }

    public void setPopedomCode(String popedomCode) {
        this.popedomCode = popedomCode;
    }

    public String getModUrl() {
        return modUrl;
    }

    public void setModUrl(String modUrl) {
        this.modUrl = modUrl;
    }

    public Long getModPid() {
        return modPid;
    }

    public void setModPid(Long modPid) {
        this.modPid = modPid;
    }
}