package cn.com.winning.ssgj.base.util;

import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 编码
 *
 * @author ChenKuai
 * @create 2018-03-01 下午 6:36
 **/
public class Base64Utils{

    public static void main(String[] args) throws Exception
    {

        String data = Base64Utils.encryptBASE64("http://aub.iteye.com/".getBytes());
        System.out.println("加密前："+data);

        byte[] byteArray = Base64Utils.decryptBASE64(data);
        System.out.println("解密后："+new String(byteArray));
    }

    /**
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }


}
