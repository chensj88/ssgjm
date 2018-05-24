package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/module")
public class SysModuleController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/moduleInfo.do")
    public String getFuncInfoPage(HttpServletRequest request, Model model) {
        return "auth/user/modleinfo";
    }

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> listFunction(SysModule module, Row row) {
        module.setRow(row);
        module.setIsDel(Constants.STATUS_UNUSE);
        List<SysModule> moduleList = super.getFacade().getSysModuleService().getSysModulePaginatedListFuzzy(module);
        int total = super.getFacade().getSysModuleService().getSysModuleCountFuzzy(module);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("rows", moduleList);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/queryModule.do")
    @ResponseBody
    public Map<String, Object> queryModule(String modName, int matchCount) {
        SysModule module = new SysModule();
        module.setModName(modName);
        module.setModLevel(1);
        module.setIsDel(Constants.STATUS_UNUSE);
        Row row = new Row(0, matchCount);
        module.setRow(row);
        List<SysModule> moduleList = super.getFacade().getSysModuleService().getSysModulePaginatedListFuzzy(module);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", moduleList);
        return result;
    }

    @RequestMapping("/add.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> addModule(SysModule module) {
        module.setModId(ssgjHelper.createSysModId());
        module.setIsDel(Constants.STATUS_UNUSE);
        if (module.getModLevel() == 1) {
            module.setParId(-1L);
        }
        super.getFacade().getSysModuleService().createSysModule(module);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> updateFunction(SysModule module) {
        super.getFacade().getSysModuleService().modifySysModule(module);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/deleteById.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> deleteFunctionById(SysModule module) {
        module.setIsDel(Constants.STATUS_USE);
        super.getFacade().getSysModuleService().modifySysModule(module);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/getById.do")
    @ResponseBody
    public Map<String, Object> queryFunctiontById(SysModule module) {
        module = super.getFacade().getSysModuleService().getSysModule(module);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", module);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping("/tree.do")
    @ResponseBody
    public Map<String, Object> getModuleTree(SysModule module) {
        module.setIsDel(Constants.STATUS_UNUSE);
        List<NodeTree> treeList = super.getFacade().getSysModuleService().getSysModuleNodeTree(module);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", treeList);
        result.put("status", Constants.SUCCESS);
        return result;
    }

}
