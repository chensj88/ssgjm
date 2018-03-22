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
public class ProductInterfaceController extends BaseController {

    @RequestMapping(value = "/mapping/pthirdinfo.do")
    public String queryMappingPage(HttpServletRequest request, Model model) {
        return "auth/mapping/productInterfaceMapping";
    }

    /**
     * 查询配置的接口数据
     *
     * @param interfaceInfo 传入产品ID
     * @return
     */
    @RequestMapping(value = "/pthirdinfo/queryById.do")
    @ResponseBody
    public Map<String, Object> queryBdataInfoById(SysProductInterfaceInfo interfaceInfo) {
        //获取符合要求的接口信息List
        List<SysProductInterfaceInfo> interfaceInfoList = super.getFacade().getSysProductInterfaceInfoService().getSysProductInterfaceInfoList(interfaceInfo);
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        //判断是否存在配置好的接口信息
        if (interfaceInfoList != null && interfaceInfoList.size() > 0) {
            List<String> idString = super.getFacade().getSysProductInterfaceInfoService().getInterfaceIds(interfaceInfoList);
            param.put("pks", idString);
            SysThirdInterfaceInfo sysThirdInterfaceInfo = new SysThirdInterfaceInfo();
            sysThirdInterfaceInfo.setMap(param);
            List<SysThirdInterfaceInfo> sysThirdInterfaceInfoList = super.getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfoListByIds(sysThirdInterfaceInfo);
            result.put("status", Constants.SUCCESS);
            result.put("data", sysThirdInterfaceInfoList);
            return result;
        }
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 根据产品Id和接口ID查询出待删除的数据
     *
     * @param pdId
     * @param interIds
     * @return
     */
    @RequestMapping(value = "/pthirdinfo/queryProductInterById.do")
    @ResponseBody
    public Map<String, Object> queryProductInterById(Integer pdId, String interIds) {
        List<SysProductInterfaceInfo> infoList = super.getFacade().getSysProductInterfaceInfoService().getSysProductInterfaceInfoByIds(pdId, interIds);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", infoList);
        return result;
    }

    @RequestMapping(value = "/pthirdinfo/removeMapping.do")
    @ResponseBody
    @ILog
    public Map<String, Object> removePBMapping(String idList) {
        super.getFacade().getSysProductInterfaceInfoService().removeProductInterInfo(idList);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", "");
        return result;

    }

    @RequestMapping(value = "/pthirdinfo/addMapping.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addPBMapping(String idList) throws ParseException {
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        super.getFacade().getSysProductInterfaceInfoService().addSysProductInterfaceInfoMapping(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }
}
