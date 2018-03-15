package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-13 16:27
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue")
public class VueLoginController extends BaseController{


    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Map<String,Object> vueLoginUser(String userid,String password){

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        /*result.put("data", );*/
        return result;

    }

}
