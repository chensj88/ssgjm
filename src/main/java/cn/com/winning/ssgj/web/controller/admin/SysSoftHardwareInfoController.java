package cn.com.winning.ssgj.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.SysSoftHardwareInfo;
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
 * 软硬件设备类型信息表Controller
 *
 * @author thinkpad
 * @date 2018-01-04
 */
@Controller
@RequestMapping("/admin/hardware")
public class SysSoftHardwareInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysSoftHardwareInfoController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/shInfo.do")
    @ILog
    public String userinfo(HttpServletRequest request, Model model) {
        return "auth/module/sysSoftHardwareInfo";
    }

    /**
     * 软硬件设备类型信息表
     *
     * @param row
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    @ILog(operationName = "软硬件设备类型信息表", operationType = "list")
    public Map<String, Object> list(Row row, SysSoftHardwareInfo info) {
        info.setRow(row);
        System.out.println(info);
        List<SysSoftHardwareInfo> infos = getFacade().getSysSoftHardwareInfoService().getSysSoftHardwareInfoPaginatedListForSelectiveKey(info);
        int total = getFacade().getSysSoftHardwareInfoService().getSysSoftHardwareInfoCountForSelectiveKey(info);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", infos);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过产品ID查询软硬件设备类型信息表
     *
     * @param t
     * @return
     */
    @RequestMapping("/getById.do")
    @ResponseBody
    @ILog(operationName = "通过产品ID查询软硬件设备类型信息表", operationType = "getById")
    public Map<String, Object> getById(SysSoftHardwareInfo t) {
        System.err.println("通过产品ID查询软硬件设备类型信息表");
        t = getFacade().getSysSoftHardwareInfoService().getSysSoftHardwareInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", t);
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 通过产品ID删除软硬件设备类型信息表
     *
     * @return
     */
    @RequestMapping("/deleteById.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "通过产品ID删除软硬件设备类型信息表", operationType = "deleteById")
    public Map<String, Object> deleteById(SysSoftHardwareInfo t) {
        t.setStatus(0);
        getFacade().getSysSoftHardwareInfoService().modifySysSoftHardwareInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 添加软硬件设备类型信息表
     *
     * @param
     * @return
     */
    @RequestMapping("/addSysSoftHardwareInfo.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "添加软硬件设备类型信息表", operationType = "addSysSoftHardwareInfo")
    public Map<String, Object> addSysSoftHardwareInfo(SysSoftHardwareInfo t) {
        Long id = ssgjHelper.createSoftwareHardwareId();
        String code = ssgjHelper.createSoftwareHardwareCode();
        t.setShCode(code);
        t.setId(id);
        t.setStatus(1);
        getFacade().getSysSoftHardwareInfoService().createSysSoftHardwareInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }

    /**
     * 修改软硬件设备类型信息表
     *
     * @param t
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    @Transactional
    @ILog(operationName = "修改软硬件设备类型信息表", operationType = "update")
    public Map<String, Object> update(SysSoftHardwareInfo t) {
        getFacade().getSysSoftHardwareInfoService().modifySysSoftHardwareInfo(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", Constants.SUCCESS);
        return map;
    }


    /**
     * 软硬件设备类型信息表
     *
     * @param info
     * @return
     */
    @RequestMapping("/listNoPage.do")
    @ResponseBody
    @ILog(operationName = "软硬件设备类型信息表", operationType = "list")
    public Map<String, Object> listNoPage(SysSoftHardwareInfo info) {
        List<SysSoftHardwareInfo> infos = getFacade().getSysSoftHardwareInfoService().getSysSoftHardwareInfoListForNames(info);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", infos);
        map.put("status", Constants.SUCCESS);
        return map;
    }
}
