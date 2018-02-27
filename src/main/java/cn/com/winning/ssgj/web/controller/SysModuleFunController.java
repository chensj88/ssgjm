package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysModFun;
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
 * @date 2018-02-26 14:31
 */
@Controller
@RequestMapping(value = "/admin/moduleFun")
public class SysModuleFunController extends BaseController {

    @RequestMapping(value = "/query.do")
    @ResponseBody
    public Map<String,Object> queryModuleFunMapping(SysModFun modFun){
        List<Long> funcIdList = super.getFacade().getSysModFunService().getFunIdsList(modFun);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",funcIdList);
        return result;
    }

    @RequestMapping(value = "/add.do")
    @ResponseBody
    public Map<String,Object> addModuleFunMapping(String idList){
        super.getFacade().getSysModFunService().createSysModFunForIdList(idList);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public Map<String,Object> deleteModuleFunMapping(SysModFun fun){
        super.getFacade().getSysModFunService().removeSysModFun(fun);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


}
