package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.work.client.BizProcessResult;
import cn.com.winning.ssgj.ws.work.service.PmisWorkingPaperService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @RequestMapping(value = "/question.do")
    @ResponseBody
    public Map<String,Object> testSiteQuestion(){
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setCreator(7110L);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(info));
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


    @RequestMapping(value = "/testExcel.do")
    public void wiriteExcel(HttpServletResponse response){
        List<Map<String,Object>> validateRoles = new ArrayList<>();
        //问题优先级
        SysDictInfo dictInfo = new SysDictInfo();
        dictInfo.setDictCode("priorityType");
        List<SysDictInfo> dictInfos = getFacade().getSysDictInfoService().getSysDictInfoList(dictInfo);
        Map<String,Object> dictValidate = new HashMap<>();
        String[] dictArr = new String[dictInfos.size()];
        for (int i = 0; i < dictInfos.size(); i++) {
            dictArr[i] = dictInfos.get(i).getDictLabel();
        }
        dictValidate.put("roles",dictArr);
        dictValidate.put("firstRow",2);
        dictValidate.put("lastRow",4000);
        dictValidate.put("firstCol",1);
        dictValidate.put("lastCol",1);
        validateRoles.add(dictValidate);
        //站点名称/科室名称
        String serialNo = "10159";
        EtDepartment dept = new EtDepartment();
        List<EtDepartment> deps = getDepartmentList(Long.parseLong(serialNo),null);
        Map<String,Object> deptValidate = new HashMap<>();
        String[] deptArr = new String[deps.size()];
        for (int i = 0; i < deps.size(); i++) {
            deptArr[i] = deps.get(i).getDeptName();
        }
        deptValidate.put("roles",deptArr);
        deptValidate.put("firstRow",2);
        deptValidate.put("lastRow",4000);
        deptValidate.put("firstCol",2);
        deptValidate.put("lastCol",2);
        validateRoles.add(deptValidate);
        //系统
        EtContractTask task = new EtContractTask();
        task.setSerialNo(serialNo);
        List<EtContractTask> tasks = getFacade().getEtContractTaskService().getEtContractTaskList(task);
        Map<String,Object> taskValidate = new HashMap<>();
        String[] taskArr = new String[tasks.size()];
        for (int i = 0; i < deps.size(); i++) {
            taskArr[i] = deps.get(i).getDeptName();
        }
        taskValidate.put("roles",taskArr);
        taskValidate.put("firstRow",2);
        taskValidate.put("lastRow",4000);
        taskValidate.put("firstCol",3);
        taskValidate.put("lastCol",3);
        validateRoles.add(deptValidate);
        //底稿类型
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("manuscriptStatus");
        dictInfos = getFacade().getSysDictInfoService().getSysDictInfoList(dictInfo);
        Map<String,Object> masValidate = new HashMap<>();
        dictArr = new String[dictInfos.size()];
        for (int i = 0; i < dictInfos.size(); i++) {
            dictArr[i] = dictInfos.get(i).getDictLabel();
        }
        masValidate.put("roles",dictArr);
        masValidate.put("firstRow",2);
        masValidate.put("lastRow",4000);
        masValidate.put("firstCol",6);
        masValidate.put("lastCol",6);
        validateRoles.add(dictValidate);

        List<String> colRow = new ArrayList<>();
        colRow.add("问题优先级");
        colRow.add("站点名称/科室名称");
        colRow.add("系统");
        colRow.add("菜单");
        colRow.add("问题描述");
        colRow.add("底稿类型");

        //创建工作簿
        Workbook workbook = new XSSFWorkbook();
        ExcelUtil.writeTemplateExcel(response,colRow,workbook,validateRoles,"工作簿1.xls");
    }

}
