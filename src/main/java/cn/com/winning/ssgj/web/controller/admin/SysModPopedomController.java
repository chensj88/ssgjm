package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.domain.SysRoleInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-26 11:32
 */
@Controller
@RequestMapping(value = "/admin/rolemodule")
public class SysModPopedomController extends BaseController {

    @RequestMapping(value = "/query.do")
    @ResponseBody
    public Map<String, Object> queryRoleModuleMapping(Long roleId) {
        SysModPopedom modPopedom = new SysModPopedom();
        modPopedom.setRoleId(roleId);
        List<Long> modIdList = super.getFacade().getSysModPopedomService().getModuleIdList(modPopedom);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", modIdList);
        return result;
    }

    @RequestMapping(value = "/add.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addModPopedomMapping(String idList) {
        super.getFacade().getSysModPopedomService().createSysModPopedomForIdList(idList);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> deleteModPopedomMapping(SysModPopedom modPopedom) {
        super.getFacade().getSysModPopedomService().removeSysModPopedom(modPopedom);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    @RequestMapping(value = "/queryBtn.do")
    @ResponseBody
    public Map<String, Object> queryModPopedomMapping(Long roleId) {
        SysRoleInfo roleInfo = new SysRoleInfo();
        roleInfo.setId(roleId);
        List<NodeTree> modIdList = super.getFacade().getSysModuleService().getRoleMenu(roleInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", modIdList);
        return result;
    }

    @RequestMapping(value = "/addPopedom.do")
    @ResponseBody
    @ILog
    public Map<String, Object> upagteModPopedomMapping(String idList) {
        super.getFacade().getSysModPopedomService().modifyModPopedomMapping(idList);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/queryRolePopedom.do")
    @ResponseBody
    public Map<String, Object> queryRolePopedom(Long roleId) {
        SysModPopedom modPopedom = new SysModPopedom();
        modPopedom.setRoleId(roleId);
        List<SysModPopedom> modPopedomList = super.getFacade().getSysModPopedomService().getSysModPopedomHasPopedomList(modPopedom);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", modPopedomList);
        return result;
    }
}
