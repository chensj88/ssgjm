package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenshijie
 * @title 医院用户
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-12 10:17
 */
@CrossOrigin
@Controller
@RequestMapping("/vue/processManager")
public class HospitalUserController {

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryHospitalUserInfo(){

        SysUserInfo user = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();

        Map<String,Object> result = new HashMap<String,Object>();
      /*  result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", );*/
        return result;

    }
}
