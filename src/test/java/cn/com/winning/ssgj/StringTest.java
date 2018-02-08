package cn.com.winning.ssgj;

import org.junit.Test;

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

}
