package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
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
    @RequestMapping(value = "/common/queryCustomerMenu.do")
    @ResponseBody
    public Map<String,Object> queryUserCustomerAndProjectInfo(long userid){
        //TODO  测试使用
       // userid = 225L;
        List<NodeTree> nodeTreeList = super.getFacade().getCommonQueryService().queryUserCustomerProjectTreeInfo(userid);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", nodeTreeList);
        return result;
    }

    /**
     * 查询用户的信息
     * @return
     */
    @RequestMapping(value = "/common/user.do")
    @ResponseBody
    @Deprecated
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
    
    /**
     * 加载字典信息
     * @param dict
     * @return
     */
    @RequestMapping(value = "/common/getCodes.do")
    @ResponseBody
    public Map<String, Object> queryDictInfo(SysDictInfo dict) {
        List<SysDictInfo> dictInfos = super.getFacade().getSysDictInfoService().getSysDictInfoList(dict);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", dictInfos);
        return result;

    }

    @RequestMapping( value = "/common/checkAuth.do")
    @ResponseBody
    public Map<String,Object> checkAuth(PmisProjctUser user){
        if(user.getRy() == 100001L){
            user.setRyfl(0);
        }else{
            user = super.getFacade().getPmisProjctUserService().getPmisProjctUser(user);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        if( user == null ){
            result.put("data", 1 );
        }else{
            result.put("data", user.getRyfl() );
        }

        result.put("status", Constants.SUCCESS);

        return result;

    }


    @RequestMapping(value = "/common/queryUser.do")
    @ResponseBody
    public Map<String,Object> queryUser(SysUserInfo user, Row row){
        user.setRow(row);
        user.setStatus(Constants.STATUS_USE);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("rows", super.getFacade().getSysUserInfoService().getSysUserInfoQueryPageListASC(user));
        return result;

    }
}
