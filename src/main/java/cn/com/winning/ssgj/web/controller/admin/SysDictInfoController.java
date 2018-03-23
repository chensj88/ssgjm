package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysDictInfo;
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
 * @date 2018-02-11 21:31
 */
@Controller
@RequestMapping(value = "/admin/dict")
public class SysDictInfoController extends BaseController {

    @RequestMapping(value = "/pageInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model) {
        return "config/dictInfo";
    }

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> querySysDictInfoList(Row row, SysDictInfo sysDictInfo) {
        sysDictInfo.setRow(row);
        List<SysDictInfo> dictInfoList = super.getFacade().getSysDictInfoService().getSysDictInfoPageForAnd(sysDictInfo);
        int total = super.getFacade().getSysDictInfoService().getSysDictInfoCountForAnd(sysDictInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", dictInfoList);
        return result;

    }


    @RequestMapping(value = "/add.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addSysDictInfo(SysDictInfo sysDictInfo) {
        super.getFacade().getSysDictInfoService().createSysDictInfo(sysDictInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/existDictValue.do")
    @ResponseBody
    public Map<String, Object> existDictValue(SysDictInfo dictInfo) {
        boolean isValid = super.getFacade().getSysDictInfoService().existDictValue(dictInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("valid", isValid);
        result.put("status", Constants.SUCCESS);
        return result;
    }


    @RequestMapping(value = "/update.do")
    @ResponseBody
    @ILog
    public Map<String, Object> updateSysDictInfo(SysDictInfo info) {
        super.getFacade().getSysDictInfoService().modifySysDictInfo(info);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> deleteSysDictInfo(SysDictInfo info) {
        super.getFacade().getSysDictInfoService().removeSysDictInfo(info);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/getCodes.do")
    @ResponseBody
    public Map<String, Object> queryDictInfo(SysDictInfo dict) {
        List<SysDictInfo> dictInfos = super.getFacade().getSysDictInfoService().getSysDictInfoList(dict);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", dictInfos);
        return result;

    }
}
