package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
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
    public Map<String,Object> queryModPopedomMapping(Long roleId){
        SysModPopedom modPopedom = new SysModPopedom();
        modPopedom.setRoleId(roleId);
        List<Long> modIdList = super.getFacade().getSysModPopedomService().getModuleIdList(modPopedom);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", modIdList);
        return result;
    }

    @RequestMapping(value = "/add.do")
    @ResponseBody
    public Map<String,Object> addModPopedomMapping(String idList){
        super.getFacade().getSysModPopedomService().createSysModPopedomForIdList(idList);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public Map<String,Object> deleteModPopedomMapping(SysModPopedom modPopedom){
        super.getFacade().getSysModPopedomService().removeSysModPopedom(modPopedom);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
}
