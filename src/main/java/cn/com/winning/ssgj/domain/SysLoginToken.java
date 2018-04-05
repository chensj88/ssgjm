package cn.com.winning.ssgj.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.domain
 * @date 2018-04-04 12:30
 */
@Alias("sysLoginToken")
public class SysLoginToken extends BaseDomain implements Serializable {

    private String token;

    public SysLoginToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
