package cn.com.winning.ssgj.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.base.Constants;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysFun;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/func")
public class SysFunctionController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/funcInfo.do")
    public String getFuncInfoPage(HttpServletRequest request, Model model) {
        return "auth/user/funcinfo";
    }

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> listFunction(SysFun fun, Row row) {
        fun.setRow(row);
        fun.setIsDel(Constants.STATUS_UNUSE);
        List<SysFun> funList = super.getFacade().getSysFunService().getSysFunPaginatedListFuzzy(fun);
        int total = super.getFacade().getSysFunService().getSysFunCountFuzzy(fun);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("rows", funList);
        result.put("status", Constants.SUCCESS);
        return result;
    }


    @RequestMapping("/add.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> addFunction(SysFun fun) {
        fun.setId(ssgjHelper.createFuncId());
        fun.setIsDel(Constants.STATUS_UNUSE);
        super.getFacade().getSysFunService().createSysFun(fun);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> updateFunction(SysFun fun) {
        super.getFacade().getSysFunService().modifySysFun(fun);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/deleteById.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> deleteFunctionById(SysFun fun) throws IllegalAccessException, InvocationTargetException {
        fun.setIsDel(Constants.STATUS_USE);
        super.getFacade().getSysFunService().modifySysFun(fun);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/getById.do")
    @ResponseBody
    public Map<String, Object> queryFunctiontById(SysFun fun) throws IllegalAccessException, InvocationTargetException {
        fun = super.getFacade().getSysFunService().getSysFun(fun);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", fun);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/tree.do")
    @ResponseBody
    public Map<String, Object> queryFunctiontTree(SysFun fun) throws IllegalAccessException, InvocationTargetException {
        List<NodeTree> treeList = super.getFacade().getSysFunService().createSysFunTree(fun);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", treeList);
        result.put("status", Constants.SUCCESS);
        return result;
    }

}
