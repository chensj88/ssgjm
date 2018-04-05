package cn.com.winning.ssgj.base.util;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-03-23 12:00
 */
public class PasswordUtils {
    /**
     * 解密Base64加密的密码
     * @param password 加密的密码
     * @return
     * @throws Exception
     */
    public static String decryptPasswordByBase64(String password) throws Exception {
        String decryptPassword = "";
        try {
            decryptPassword = new String(Base64Utils.decryptBASE64(password),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }
        return decryptPassword;
    }

}
