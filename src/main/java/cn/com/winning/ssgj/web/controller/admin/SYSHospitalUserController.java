package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenshijie
 * @title 医院用户
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.admin
 * @date 2018-04-20 8:17
 */
@Controller
@RequestMapping(value = "/admin/huser")
public class SYSHospitalUserController extends BaseController {


    @RequestMapping(value = "/pageInfo.do")
    public String pageInfo(){
        return  "hospital/hospitalUser";
    }
}
