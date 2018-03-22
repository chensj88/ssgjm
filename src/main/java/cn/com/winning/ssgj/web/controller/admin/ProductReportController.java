package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 产品报表Controller
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-07 11:15
 */
@Controller
@RequestMapping(value = "/admin")
public class ProductReportController extends BaseController {

    @RequestMapping(value = "/mapping/pReportinfo.do")
    public String queryMappingPage(HttpServletRequest request, Model model) {
        return "auth/mapping/productReportMapping";
    }

    @RequestMapping(value = "/pReport/queryById.do")
    @ResponseBody
    public Map<String, Object> queryProductReportById(SysProductPaperInfo paperInfo) {
        List<SysProductPaperInfo> paperInfoList = super.getFacade().
                getSysProductPaperInfoService().getSysProductPaperInfoList(paperInfo);
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        if (paperInfoList != null && paperInfoList.size() > 0) {
            List<String> idString = super.getFacade().getSysProductPaperInfoService().getSysPaperInfoIds(paperInfoList);
            param.put("pks", idString);
            SysReportInfo reportInfo = new SysReportInfo();
            reportInfo.setMap(param);
            List<SysReportInfo> sysReportInfoList = super.getFacade().getSysReportInfoService().getSysReportInfoListById(reportInfo);
            result.put("status", Constants.SUCCESS);
            result.put("data", sysReportInfoList);
            return result;
        }
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/pReport/queryProductReportInfoById.do")
    @ResponseBody
    public Map<String, Object> queryProductReportInfoById(Integer pdId, String reportids) {
        List<SysProductPaperInfo> paperInfos = super.getFacade().getSysProductPaperInfoService()
                .getSysProductPaperInfoByIds(pdId, reportids);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", paperInfos);
        return result;
    }

    @RequestMapping(value = "/pReport/removeMapping.do")
    @ResponseBody
    @ILog
    public Map<String, Object> removePRMapping(String idList) {
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        super.getFacade().getSysProductPaperInfoService().removeSysProductPaperInfoMapping(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/pReport/addMapping.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addPRMapping(String idList) throws ParseException {
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        super.getFacade().getSysProductPaperInfoService().
                addSysProductPaperInfoMapping(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }
}
