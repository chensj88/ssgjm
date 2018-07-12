package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import jdk.net.SocketFlow;
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
    public Map<String, Object> queryUserCustomerAndProjectInfo(long userid) {
        //TODO  测试使用
        // userid = 225L;
        List<NodeTree> nodeTreeList = super.getFacade().getCommonQueryService().queryUserCustomerProjectTreeInfo(userid);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", nodeTreeList);
        return result;
    }

    /**
     * 查询用户的信息
     *
     * @return
     */
    @RequestMapping(value = "/common/user.do")
    @ResponseBody
    @Deprecated
    public Map<String, Object> queryUserInfo() {
      /*  SysUserInfo loginUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();*/
        SysUserInfo loginUser = new SysUserInfo();
        loginUser.setYhmc("admin");
        loginUser = super.getFacade().getSysUserInfoService().getSysUserInfo(loginUser);
        Long orgid = loginUser.getOrgid();
        SysOrgExt orgExt = new SysOrgExt();
        orgExt.setOrgId(orgid);
        orgExt = super.getFacade().getSysOrgExtService().getSysOrgExt(orgExt);
        loginUser.getMap().put("orgExt", orgExt.getOrgNameExt());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", loginUser);
        return result;
    }

    /**
     * 加载字典信息
     *
     * @param dict
     * @return
     */
    @RequestMapping(value = "/common/getCodes.do")
    @ResponseBody
    public Map<String, Object> queryDictInfo(SysDictInfo dict) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", this.getDictInfoList(dict));
        return result;

    }

    /**
     * 用户权限判断
     * 1、admin 默认角色项目经理
     * 2、在项目中的人员，不在PMIS中的默认权限为2，支持人员
     * 3、在项目中的人员，在PMIS中的，按照PMIS来加载权限
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/common/checkAuth.do")
    @ResponseBody
    public Map<String, Object> checkAuth(PmisProjctUser user) {
        long userAuth = -1;
        if (user.getRy() == 100001L) {
            userAuth = 0;
        } else {
            user = super.getFacade().getPmisProjctUserService().getPmisProjctUser(user);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if (user == null) {
            userAuth = 2;
        } else if(user.getRy() != 100001L) {
            userAuth = user.getRyfl();
        }
        result.put("status", Constants.SUCCESS);
        result.put("data", userAuth);
        return result;

    }

    /**
     * 前端组件获取用户信息，主要用于条件模糊查询
     *
     * @param user
     * @param row
     * @return
     */
    @RequestMapping(value = "/common/queryUser.do")
    @ResponseBody
    public Map<String, Object> queryUser(SysUserInfo user, Row row) {
        user.setRow(row);
        user.setStatus(Constants.STATUS_USE);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("rows", super.getFacade().getSysUserInfoService().getSysUserInfoQueryPageListASC(user));
        return result;
    }

    /**
     * 用户权限信息查询
     * 1、继承checkAuth中的权限查询，并且按照SSGJ中配置的信息进行扩展
     * 扩展为 先查询PMIS，在查询SSGJ，PMIS存在結果，使用PMIS，无结果，使用SSGJ
     *        两者均无，则默认为2 支持人员
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/common/checkAllAuth.do")
    @ResponseBody
    public Map<String, Object> checkAllAuth(PmisProjctUser user) {
        long pmId = user.getXmlcb();
        long userid = user.getRy();
        long userAuth = -1;
        Map<String, Object> result = new HashMap<String, Object>();
        //管理员
        if (user.getRy() == 100001L) {
            userAuth = 0;
        } else {
            //不是管理员，查询PMIS提供数据
            user = super.getFacade().getPmisProjctUserService().getPmisProjctUser(user);
            //PMIS查询无结果
            if (user == null) {
                EtUserInfo info = new EtUserInfo();
                info.setPmId(pmId);
                info.setUserId(userid);
                info = super.getFacade().getEtUserInfoService().getEtUserInfo(info);
                //SSGJ 无结果
                if (info == null) {
                    userAuth = 2;
                } else { //有结果
                    userAuth = Long.valueOf(info.getPositionName());
                }
            } else { //PMIS 有结果
                result.put("data", user.getRyfl());
                userAuth = user.getRyfl();
            }
        }
        result.put("status", Constants.SUCCESS);
        result.put("data", userAuth);
        return result;
    }
}
