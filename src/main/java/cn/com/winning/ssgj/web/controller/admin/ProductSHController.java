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
 * @title 产品基础数据Controller
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-07 11:15
 */
@Controller
@RequestMapping(value = "/admin")
public class ProductSHController extends BaseController {

    @RequestMapping(value = "/mapping/pShInfo.do")
    public String queryMappingPage(HttpServletRequest request, Model model) {
        return "auth/mapping/productSHMapping";
    }

    @RequestMapping(value = "/pShInfo/queryById.do")
    @ResponseBody
    public Map<String, Object> queryBdataInfoById(SysProductShInfo shInfo,SysSoftHardwareInfo data) {
        List<SysProductShInfo> shInfoList = super.getFacade().getSysProductShInfoService().getSysProductShInfoList(shInfo);
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        if (shInfoList != null && shInfoList.size() > 0) {
            List<String> idString = super.getFacade().getSysProductShInfoService().getSoftwareHardwareInfoId(shInfoList);
            param.put("pks", idString);
            data.setMap(param);
            data.setStatus(Constants.STATUS_USE);
            List<SysSoftHardwareInfo> sysSoftHardwareInfoList = super.getFacade().getSysSoftHardwareInfoService().getSysSoftHardwareInfoListByIds(data);
            result.put("status", Constants.SUCCESS);
            result.put("data", sysSoftHardwareInfoList);
            return result;
        }
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/pShInfo/queryProductSHInfoById.do")
    @ResponseBody
    public Map<String, Object> queryProductSHInfoById(Integer pdId, String shIds) {
        List<SysProductShInfo> dataInfos = super.getFacade().getSysProductShInfoService().getSysProductShInfoByIds(pdId, shIds);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", dataInfos);
        return result;
    }

    @RequestMapping(value = "/pShInfo/removeMapping.do")
    @ResponseBody
    @ILog
    public Map<String, Object> removePSMapping(String idList) {
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        super.getFacade().getSysProductShInfoService().removeSysProductShInfoMapping(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", "");
        return result;

    }

    @RequestMapping(value = "/pShInfo/addMapping.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addPSMapping(String idList) throws ParseException {
        System.out.println(idList);
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        super.getFacade().getSysProductShInfoService().addSysProductShInfoMapping(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }
}
