package cn.com.winning.ssgj.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.domain
 * @date 2018-04-04 19:41
 */
@Alias("sysLoginUser")
public class SysLoginUser  extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private  String token;

    private Long userId;

    private java.sql.Timestamp loginTime;

    private java.sql.Timestamp logoutTime;

    public SysLoginUser() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }
}
