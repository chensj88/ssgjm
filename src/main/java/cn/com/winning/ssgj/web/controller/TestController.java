package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.EtBusinessProcess;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.work.client.BizProcessResult;
import cn.com.winning.ssgj.ws.work.service.PmisWorkingPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-03-15 10:41
 */
@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController{

    @Autowired
    private PmisWorkingPaperService pmisWorkingPaperService;

    @RequestMapping(value = "/page.do")
    public String pageInfo(){
        return "test/test";
    }

    @RequestMapping(value = "/exec.do")
    @ResponseBody
    public Map<String,Object> executeProcedure(){
        super.getFacade().getSysOrgExtService().callProcedure();
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/test.do")
    @ResponseBody
    public Map<String,Object> testJsonArray(){
        Map<String,List> data = super.getFacade().getCommonQueryService().queryCompletionOfProject(24661L);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("success",(List<Integer>)data.get("success"));
        result.put("handle",(List<Integer>)data.get("handle"));
        result.put("item",(List<String>)data.get("item"));
        return result;

    }

    @RequestMapping(value = "/flow.do")
    @ResponseBody
    public Map<String,Object> testflowINfo(){
        EtBusinessProcess flowSurvey = new EtBusinessProcess();
        flowSurvey.setPmId(24661L);
        flowSurvey.setcId(21269L);
        flowSurvey.setSerialNo("20781");
        super.getFacade().getCommonQueryService().generateEtBusinessProcessByProject(flowSurvey);
        Row row = new Row(0,10);
        flowSurvey.setRow(row);
        List<EtBusinessProcess> processList = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessPaginatedList(flowSurvey);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("row", processList);
        result.put("total", processList.size());
        return result;
    }

    @RequestMapping(value = "/menu.do")
    @ResponseBody
    public Map<String,Object> testUserMenu(){
        List<NodeTree> nodeTrees =  super.getFacade().getCommonQueryService().queryUserCustomerProjectTreeInfo(77L);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",nodeTrees);
        return result;

    }

    @RequestMapping(value = "/product.do")
    @ResponseBody
    public Map<String,Object> testUserProduct(){
        List<PmisProductInfo> nodeTrees =  super.getFacade().getCommonQueryService().queryProductOfProjectByProjectIdAndType(  28252L,1);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",nodeTrees);
        return result;

    }

    @RequestMapping(value = "/query.do")
    @ResponseBody
    public Map<String,Object> qyeryWorkreportInfo(String code){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",pmisWorkingPaperService.queryWorkReport(code));
        return result;

    }

}
