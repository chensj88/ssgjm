package cn.com.winning.ssgj.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysThirdInterfaceInfo;
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
 * 第三方接口类型信息处理Controller
 *
 * @author thinkpad
 * @date 2018-01-04
 */
@Controller
@RequestMapping("/admin/thirx")
public class SysInterFaceInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysInterFaceInfoController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/interfaceInfo.do")
    public String userinfo(HttpServletRequest request, Model model) {
        return "auth/module/interFaceInfo";
    }

    /**
     * 第三方接口类型信息
     *
     * @param row
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> list(Row row, SysThirdInterfaceInfo sysThirdInterfaceInfo) {
        sysThirdInterfaceInfo.setRow(row);
        sysThirdInterfaceInfo.setStatus(Constants.STATUS_USE);
        List<SysThirdInterfaceInfo> sysThirdInterfaceInfos = getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfoPaginatedListForSelectiveKey(sysThirdInterfaceInfo);
        int total = getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfoCountForSelectiveKey(sysThirdInterfaceInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", sysThirdInterfaceInfos);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过产品ID查询第三方接口类型信息
     *
     * @param t
     * @return
     */
    @RequestMapping("/getById.do")
    @ResponseBody
    public Map<String, Object> getById(SysThirdInterfaceInfo t) {
        System.err.println("通过产品ID查询第三方接口类型信息");
        t = getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", t);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过产品ID删除第三方接口类型信息
     *
     * @return
     */
    @RequestMapping("/deleteById.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "通过产品ID删除第三方接口类型信息", operationType = "deleteById")
    public Map<String, Object> deleteById(SysThirdInterfaceInfo t) {
        t.setStatus(0);
        getFacade().getSysThirdInterfaceInfoService().modifySysThirdInterfaceInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 添加第三方接口类型信息
     *
     * @param
     * @return
     */
    @RequestMapping("/addInterFaceInfo.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "添加第三方接口类型信息", operationType = "addInterFaceInfo")
    public Map<String, Object> addInterFaceInfo(SysThirdInterfaceInfo t) {
        Long id = ssgjHelper.createThirdInterfaceId();
        String code = ssgjHelper.createThirdInterfaceDataCode();
        t.setInterCode(code);
        t.setId(id);
        t.setStatus(1);
        getFacade().getSysThirdInterfaceInfoService().createSysThirdInterfaceInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 修改第三方接口类型信息
     *
     * @param t
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "修改第三方接口类型信息", operationType = "update")
    public Map<String, Object> update(SysThirdInterfaceInfo t) {
        getFacade().getSysThirdInterfaceInfoService().modifySysThirdInterfaceInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }


    @RequestMapping(value = "/listNoPage.do")
    @ResponseBody
    public Map<String, Object> queryInterfaceListNoPage(SysThirdInterfaceInfo interfaceInfo) {
        List<SysThirdInterfaceInfo> infoList = super.getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfoListForNames(interfaceInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("rows", infoList);
        return result;

    }

}
