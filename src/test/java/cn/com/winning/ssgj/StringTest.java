package cn.com.winning.ssgj;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.MD5;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void uriResolive(){
        String uri = "/vue/dataCheck/aaa.do";
        System.out.println(uri.substring(uri.lastIndexOf("/")+1));
        System.out.println(uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf(".do")));
        String lastUri = uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf(".do"));
        String uploadRegex= Constants.Vue.COMMON_UPLOAD_TAG;
        Pattern uploadPattern =Pattern.compile(uploadRegex);
        Matcher uploadm=uploadPattern.matcher(lastUri);
        boolean result1=uploadm.find();
        String exportRegex= Constants.Vue.COMMON_EXPORT_TAG;
        Pattern exportPattern =Pattern.compile(exportRegex);
        Matcher exportm=exportPattern.matcher(lastUri);
        boolean result2=exportm.find();
        String downRegex= Constants.Vue.COMMON_EXPORT_TAG;
        Pattern downPattern =Pattern.compile(downRegex);
        Matcher downm=downPattern.matcher(lastUri);
        boolean result3=downm.find();
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        if(result1 || result2 || result3){
            System.out.println(true);
        }else{
            System.out.println(false);
        }

    }
}
