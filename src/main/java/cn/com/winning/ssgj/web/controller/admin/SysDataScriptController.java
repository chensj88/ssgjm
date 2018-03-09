package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysDataCheckScript;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-03-09 16:39
 */
@Controller
@RequestMapping(value = "/admin/script")
public class SysDataScriptController extends BaseController {

    @RequestMapping(value = "/pageInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model){
        return  "online/scriptPage";
    }


    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryInfoByPage(SysDataCheckScript script, Row row){
        script.setRow(row);
        List<SysDataCheckScript> dataCheckScriptList = super.getFacade().getSysDataCheckScriptService().getSysDataCheckScriptPageListFuzzy(script);
        int total = super.getFacade().getSysDataCheckScriptService().getSysDataCheckScriptCountFuzzy(script);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", dataCheckScriptList);
        return result;

    }
}
