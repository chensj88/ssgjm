package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.EtStartEnd;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Evol
 * @title 自动分配Controller
 * @date 2018年6月15日15:05:15
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/autoAssign")
public class EtStartEndController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 自动分配
     *
     * @param etStartEnd
     * @param option     操作类型：0（删除）/1（新增）
     * @return
     */
    @RequestMapping(value = "/changeAuto.do")
    @ResponseBody
    public Map<String, Object> changeAuto(EtStartEnd etStartEnd, Integer option) {
        if (option == 0) {
            //删除数据
            getFacade().getEtStartEndService().removeEtStartEnd(etStartEnd);
        } else if (option == 1) {
            //新增数据
            etStartEnd.setId(ssgjHelper.createEtStartEndIdService());
            getFacade().getEtStartEndService().createEtStartEnd(etStartEnd);
        }
        resultMap.put("status", Constants.SUCCESS);
        return resultMap;
    }

    /**
     * 获取自动分配状态
     *
     * @param etStartEnd
     * @return
     */
    @RequestMapping(value = "/isAuto.do")
    @ResponseBody
    public Map<String, Object> isAuto(EtStartEnd etStartEnd) {
        etStartEnd = getFacade().getEtStartEndService().getEtStartEnd(etStartEnd);
        if (etStartEnd == null) {
            resultMap.put("status", 0);
        } else {
            resultMap.put("status", 1);
        }

        return resultMap;
    }

}
