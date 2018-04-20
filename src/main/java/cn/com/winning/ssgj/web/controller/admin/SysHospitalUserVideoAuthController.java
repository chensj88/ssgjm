package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.SysUserVideoAuth;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 医院用户视频权限
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.admin
 * @date 2018-04-19 10:57
 */
@Controller
@RequestMapping(value = "/admin/videoAuth")
public class SysHospitalUserVideoAuthController  extends BaseController {

    @RequestMapping(value = "/pageInfo.do")
    public String  pageInfo(){
        return "hospital/userVideoAuth";
    }

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryUserVideoAuthUploadList(SysUserVideoAuth auth, Row row){
        auth.setRow(row);
        List<SysUserVideoAuth> infoList = super.getFacade().getSysUserVideoAuthService().getSysUserVideoAuthPaginatedList(auth);
        int total = super.getFacade().getSysUserVideoAuthService().getSysUserVideoAuthCount(auth);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows",infoList);
        return result;

    }
}
