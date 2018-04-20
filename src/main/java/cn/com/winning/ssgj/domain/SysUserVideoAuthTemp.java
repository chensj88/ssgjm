package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chenshijie
 * @title 视频权限临时表
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.domain
 * @date 2018-04-20 23:00
 */
public class SysUserVideoAuthTemp extends BaseDomain implements Serializable{

    private static final long serialVersionUID = -1L;

    private Long id;

    private String userCode;

    private String userName;

    private String menuName;

    private String menuCode;

    private String suserCode;

    private String userId;

    private String serialNo;

    private Integer isExist;

    private Long creator;

    private java.sql.Timestamp createTime;

    public SysUserVideoAuthTemp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getSuserCode() {
        return suserCode;
    }

    public void setSuserCode(String suserCode) {
        this.suserCode = suserCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getIsExist() {
        return isExist;
    }

    public void setIsExist(Integer isExist) {
        this.isExist = isExist;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
