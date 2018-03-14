package cn.com.winning.ssgj.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysReportInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;

/**
 * 报表类信息表Controller
 *
 * @date 2018-01-04
 */
@Controller
@RequestMapping("/admin/report")
public class SysReportInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysReportInfoController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/reportInfo.do")
    @ILog
    public String userinfo(HttpServletRequest request, Model model) {
        return "auth/module/sysReportInfo";
    }

    /**
     * 报表类信息表
     *
     * @param row
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    @ILog(operationName = "报表类信息表", operationType = "list")
    public Map<String, Object> list(SysReportInfo info, Row row) {
        info.setRow(row);
        info.setStatus(1);
        List<SysReportInfo> infos = getFacade().getSysReportInfoService().getSysReportInfoPaginatedListForSelectiveKey(info);
        int total = getFacade().getSysReportInfoService().getSysReportInfoCountForSelectiveKey(info);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", infos);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过产品ID查询报表类信息表
     *
     * @param t
     * @return
     */
    @RequestMapping("/getById.do")
    @ResponseBody
    @ILog(operationName = "通过产品ID查询报表类信息表", operationType = "getById")
    public Map<String, Object> getById(SysReportInfo t) {
        System.err.println("通过产品ID查询报表类信息表");
        t = getFacade().getSysReportInfoService().getSysReportInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", t);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过产品ID删除报表类信息表
     *
     * @return
     */
    @RequestMapping("/deleteById.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "通过产品ID删除报表类信息表", operationType = "deleteById")
    public Map<String, Object> deleteById(SysReportInfo t) {
        t.setStatus(0);
        getFacade().getSysReportInfoService().modifySysReportInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 添加报表类信息表
     *
     * @param
     * @return
     */
    @RequestMapping("/addSysReportInfo.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "添加报表类信息表", operationType = "addSysReportInfo")
    public Map<String, Object> addSysReportInfo(SysReportInfo t) {
        Long id = ssgjHelper.createReportInfoId();
        String code = ssgjHelper.createReportInfoCode();
        t.setReportCode(code);
        t.setId(id);
        t.setReportCode(ssgjHelper.createReportInfoCode());
        t.setStatus(1);
        getFacade().getSysReportInfoService().createSysReportInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 修改报表类信息表
     *
     * @param t
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "修改报表类信息表", operationType = "update")
    public Map<String, Object> update(SysReportInfo t) {
        getFacade().getSysReportInfoService().modifySysReportInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 报表类信息表
     *
     * @param info
     * @return
     */
    @RequestMapping("/listNoPage.do")
    @ResponseBody
    @ILog(operationName = "报表类信息表", operationType = "list")
    public Map<String, Object> listNoPage(SysReportInfo info) {
        info.setStatus(1);
        List<SysReportInfo> infos = getFacade().getSysReportInfoService().getSysReportInfolistNoPage(info);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", infos);
        map.put("status", Constants.SUCCESS);
        return map;
    }
}
