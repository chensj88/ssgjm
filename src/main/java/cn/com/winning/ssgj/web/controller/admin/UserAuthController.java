package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.dao.CommonQueryDao;
import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chensj
 * @title
 * @email chensj@winning.com.cn
 * @package cn.com.winning.ssgj.web.controller.admin
 * @date: 2018-11-01 15:55
 */
@Controller
@RequestMapping(value = "/auth")
public class UserAuthController extends BaseController {




    @RequestMapping(value = "/initBtnList.do")
    @ResponseBody
    public Map<String, Object> initBtnList(String modUrl){
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        Set<String> roles = (Set<String>) userInfo.getMap().get("roles");
        Map<String, Object> result = new HashMap<String, Object>();
        if(roles == null || roles.size() == 0 ){
            result.put("status", Constants.SUCCESS);
            result.put("data", modUrl);
        }else{
            SysModPopedom modPopedom = new SysModPopedom();
            modPopedom.setModUrl(modUrl+".do");
            modPopedom.getMap().put("pks",roles);
            Set<String> btnList = getFacade().getSysModPopedomService().getButtonFlagForPageByModUrlAndRoles(modPopedom);
            result.put("status", Constants.SUCCESS);
            result.put("data", btnList);
            result.put("roles", roles);
        }
        return result;

    }
}
