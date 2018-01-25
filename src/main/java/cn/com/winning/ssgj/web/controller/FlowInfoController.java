package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysFlowInfo;
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
 * @title ${file_name}
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-01-25 17:59
 */
@Controller
@RequestMapping(value = "/admin/flow")
public class FlowInfoController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping("/flowInfo.do")
    public String gotoPage(HttpServletRequest request, Model model) {
        return "auth/module/flowinfo";
    }

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> getFlowList(Row row){
        SysFlowInfo flowInfo = new SysFlowInfo();
        flowInfo.setRow(row);
        List<SysFlowInfo> flowInfos = super.getFacade().getSysFlowInfoService().getSysFlowInfoPaginatedList(flowInfo);
        int total = super.getFacade().getSysFlowInfoService().getSysFlowInfoCount(flowInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("data",flowInfos);
        System.out.println(flowInfos);
        return result;

    }
}