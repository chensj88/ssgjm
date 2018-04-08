package cn.com.winning.ssgj.base.auth;

import java.util.UUID;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.auth
 * @date 2018-04-04 11:17
 */
public class Token {

    public static String generateTokenString(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
