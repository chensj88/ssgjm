package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysParams;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2018-02-12 13:05
 */
@Controller
@RequestMapping(value = "/admin/param")
public class SysParamController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/pageInfo.do")
    @ILog
    public String getPageInfo(HttpServletRequest request, Model model) {
        return "config/paramInfo";
    }

    @RequestMapping(value = "/list.do")
    @ResponseBody
    @ILog
    public Map<String, Object> querySysParamPageList(Row row, SysParams params) {
        params.setRow(row);
        List<SysParams> paramsList = super.getFacade().getSysParamsService().getSysParamsPageListBySelectiveKey(params);
        int total = super.getFacade().getSysParamsService().getSysParamsPageCountBySelectiveKey(params);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", paramsList);
        return result;
    }

    @RequestMapping(value = "/add.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addSysParamsInfo(SysParams params) {
        params.setId(ssgjHelper.createSysParamsId());
        super.getFacade().getSysParamsService().createSysParams(params);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/update.do")
    @ResponseBody
    @ILog
    public Map<String, Object> updateSysParamsInfo(SysParams params) {
        super.getFacade().getSysParamsService().modifySysParams(params);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> deleteSysParamsInfo(SysParams params) {
        super.getFacade().getSysParamsService().removeSysParams(params);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }
}
