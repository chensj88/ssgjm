package cn.com.winning.ssgj;

import cn.com.winning.ssgj.domain.support.UrlContent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj
 * @date 2018-04-10 16:14
 */
public class JsonTest {

    @Test
    public void convertJson(){
        UrlContent content = new UrlContent();
        content.setName("aaaaa");
        content.setUrl("aaaaaaaaaaaaaaaaaa");
        String cont = JSON.toJSONString(content);
        System.out.println(cont);
    }

    @Test
    public void convertJsonArray(){
        UrlContent content = new UrlContent();
        content.setName("food.jpeg");
        content.setUrl("https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100");
        JSONArray array = new JSONArray();
        array.add(content);
        content = new UrlContent();
        content.setName("food.jpeg");
        content.setUrl("https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100");
        array.add(content);
        String cont = array.toJSONString();
        System.out.println(cont);
    }
}
