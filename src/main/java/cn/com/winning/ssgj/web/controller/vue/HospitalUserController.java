package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/vue/hospital")
public class HospitalUserController extends BaseController {

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryHospitalUserInfo(){

       /* SysUserInfo user = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        long c_id = (long) user.getMap().get("C_ID");*/
        long c_id = 9879L;
        SysUserInfo queryUser =  new SysUserInfo();
        Row row = new Row(0,10);
        queryUser.setRow(row);
        queryUser.setSsgs(c_id);
        queryUser.setUserType(Constants.User.USER_TYPE_HOSPITAL);
        List<SysUserInfo> queryUserList = super.getFacade().getSysUserInfoService().getSysUserInfoPaginatedList(queryUser);
        int total = super.getFacade().getSysUserInfoService().getSysUserInfoCount(queryUser);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", queryUserList);
        return result;

    }
}
