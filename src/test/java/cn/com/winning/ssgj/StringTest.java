package cn.com.winning.ssgj;

import cn.com.winning.ssgj.base.util.MD5;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author chenshijie
 * @title ${file_name}
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj
 * @date 2018-01-29 15:55
 */
public class StringTest {


    @Test
    public void spilceString(){
        String idList = "156,1;156,2";
        String[] objectList = idList.split(";");
        System.out.println(objectList[0]);
        System.out.println(objectList[1]);
        StringBuilder idString = new StringBuilder();
        idString.append("and ( ");
        for (int i=0;i < objectList.length ; i++) {
          String[] strArr = objectList[i].split(",");
          if( i == objectList.length -1  ) {
              idString.append("( PD_ID =" + strArr[0] + " and BD_ID = " + strArr[1] + ") ");
          }else{
              idString.append("( PD_ID =" + strArr[0] + " and BD_ID = " + strArr[1] + ") or ");
          }
        }
        idString.append(" ) ");
        System.out.println(idString);
    }

    @Test
    public void decodePassword() throws IOException {
        String password = "";
        final BASE64Decoder decoder = new BASE64Decoder();
        System.out.println(new String(decoder.decodeBuffer(password), "UTF-8"));
    }

    @Test
    public void enecodePassword() throws IOException {
        String password = "admin";
        final BASE64Encoder encoder = new BASE64Encoder();
        System.out.println(new String(encoder.encodeBuffer(password.getBytes("UTF-8"))));
    }

    @Test
    public void enecodeMD5() throws IOException {
        String password = "123456";
        String decryptP = MD5.stringMD5(password);
        System.out.println(decryptP);
    }
}
