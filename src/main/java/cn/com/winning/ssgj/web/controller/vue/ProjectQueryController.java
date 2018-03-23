package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
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
 * @title 用户项目和客户信息查询
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-20 8:50
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue")
public class ProjectQueryController extends BaseController {

    /**
     * 查询用户的客户列表和项目列表
     * @return
     */
    @RequestMapping(value = "/common/queryCMenu.do")
    @ResponseBody
    public Map<String,Object> queryUserCustomerAndProjectInfo(){

        /*SysUserInfo loginUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        Long userid = loginUser.getId();*/
        Long userid = 225L;
        List<NodeTree> nodeTreeList = super.getFacade().getCommonQueryService().queryUserCustomerProjectTreeInfo(userid);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", nodeTreeList);
        return result;
    }


    @RequestMapping(value = "/common/user.do")
    @ResponseBody
    public Map<String,Object> queryUserInfo(){
      /*  SysUserInfo loginUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();*/
        SysUserInfo loginUser = new SysUserInfo();
        loginUser.setYhmc("admin");
        loginUser = super.getFacade().getSysUserInfoService().getSysUserInfo(loginUser);
        Long orgid = loginUser.getOrgid();
        SysOrgExt orgExt = new SysOrgExt();
        orgExt.setOrgId(orgid);
        orgExt = super.getFacade().getSysOrgExtService().getSysOrgExt(orgExt);
        loginUser.getMap().put("orgExt",orgExt.getOrgNameExt());
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", loginUser);
        return result;
    }

    @RequestMapping(value = "/common/queryP.do")
    @ResponseBody
    public Map<String,Object> queryProductByProjectId(){
        /*SysUserInfo loginUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        long cId = (long) loginUser.getMap().get("C_ID");
        long pmId = (long) loginUser.getMap().get("PM_ID");*/
        long pmId = 1L;
        List<PmisProductInfo> productInfos = super.getFacade().getCommonQueryService().queryProductOfProjectByProjectIdAndType(pmId,Constants.PMIS.CPLB_1);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", productInfos);
        return result;

    }
}
