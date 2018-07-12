package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
<<<<<<< HEAD
import cn.com.winning.ssgj.domain.EtBusinessProcess;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.PmisProductInfo;
=======
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.*;
>>>>>>> 88475f27c4d1fa9b299c02adb5d2d4a630a9b4ce
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
import cn.com.winning.ssgj.ws.work.client.BizProcessResult;
import cn.com.winning.ssgj.ws.work.client.QueryResult;
import cn.com.winning.ssgj.ws.work.service.PmisWorkingPaperService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
>>>>>>> 88475f27c4d1fa9b299c02adb5d2d4a630a9b4ce
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
    @Autowired
    private PmisWebServiceClient pmisWebServiceClient;

    @RequestMapping(value = "/page.do")
    public String pageInfo(){
        return "test/test";
    }
    @RequestMapping(value = "/upload.do")
    public String upload(){
        return "test/upload";
    }
    @RequestMapping(value = "/script.do")
    public String script(){
        return "test/scriptPage";
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
    public Map<String,Object> testSiteQuestion() throws ParseException {
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
        String serialNo = "10159";
        EtDepartment dept = new EtDepartment();
        List<EtDepartment> deps = getDepartmentList(Long.parseLong(serialNo),null);
        List<Map<String,Object>> validateRoles = new ArrayList<>();
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

    }


<<<<<<< HEAD
=======
    @RequestMapping(value = "/testExcel.do")
    public void wiriteExcel(HttpServletResponse response){
        List<Map<String,Object>> validateRoles = new ArrayList<>();
        List<String> colRow = new ArrayList<>();
        colRow.add("问题优先级");
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
        dictValidate.put("firstRow",1);
        dictValidate.put("lastRow",4000);
        dictValidate.put("firstCol",0);
        dictValidate.put("lastCol",0);
        validateRoles.add(dictValidate);
        colRow.add("站点名称/科室名称");
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
        deptValidate.put("firstRow",1);
        deptValidate.put("lastRow",4000);
        deptValidate.put("firstCol",1);
        deptValidate.put("lastCol",1);
        validateRoles.add(deptValidate);

        colRow.add("系统");
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
        taskValidate.put("firstRow",1);
        taskValidate.put("lastRow",4000);
        taskValidate.put("firstCol",2);
        taskValidate.put("lastCol",2);
        validateRoles.add(deptValidate);
        colRow.add("菜单");
        colRow.add("问题描述");
        colRow.add("底稿类型");
        //底稿类型
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("manuscriptType");
        dictInfos = getFacade().getSysDictInfoService().getSysDictInfoList(dictInfo);
        Map<String,Object> masValidate = new HashMap<>();
        dictArr = new String[dictInfos.size()];
        for (int i = 0; i < dictInfos.size(); i++) {
            dictArr[i] = dictInfos.get(i).getDictLabel();
        }
        masValidate.put("roles",dictArr);
        masValidate.put("firstRow",1);
        masValidate.put("lastRow",4000);
        masValidate.put("firstCol",5);
        masValidate.put("lastCol",5);
        validateRoles.add(masValidate);

        colRow.add("原因分类");
        //原因分类
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("reasonType");
        dictInfos = getFacade().getSysDictInfoService().getSysDictInfoList(dictInfo);
        Map<String,Object> reasonTypeValidate = new HashMap<>();
        dictArr = new String[dictInfos.size()];
        for (int i = 0; i < dictInfos.size(); i++) {
            dictArr[i] = dictInfos.get(i).getDictLabel();
        }
        reasonTypeValidate.put("roles",dictArr);
        reasonTypeValidate.put("firstRow",1);
        reasonTypeValidate.put("lastRow",4000);
        reasonTypeValidate.put("firstCol",6);
        reasonTypeValidate.put("lastCol",6);
        validateRoles.add(reasonTypeValidate);

        colRow.add("底稿状态");
        //底稿状态 manuscriptStatus
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("manuscriptStatus");
        dictInfos = getFacade().getSysDictInfoService().getSysDictInfoList(dictInfo);
        Map<String,Object> manuscriptStatusValidate = new HashMap<>();
        dictArr = new String[dictInfos.size()];
        for (int i = 0; i < dictInfos.size(); i++) {
            dictArr[i] = dictInfos.get(i).getDictLabel();
        }
        manuscriptStatusValidate.put("roles",dictArr);
        manuscriptStatusValidate.put("firstRow",1);
        manuscriptStatusValidate.put("lastRow",4000);
        manuscriptStatusValidate.put("firstCol",7);
        manuscriptStatusValidate.put("lastCol",7);
        validateRoles.add(manuscriptStatusValidate);

        colRow.add("难度");
        //难度 manuscriptStatus
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("diffcultLevel");
        dictInfos = getFacade().getSysDictInfoService().getSysDictInfoList(dictInfo);
        Map<String,Object> diffcultLevelValidate = new HashMap<>();
        dictArr = new String[dictInfos.size()];
        for (int i = 0; i < dictInfos.size(); i++) {
            dictArr[i] = dictInfos.get(i).getDictLabel();
        }
        diffcultLevelValidate.put("roles",dictArr);
        diffcultLevelValidate.put("firstRow",1);
        diffcultLevelValidate.put("lastRow",4000);
        diffcultLevelValidate.put("firstCol",8);
        diffcultLevelValidate.put("lastCol",8);
        validateRoles.add(diffcultLevelValidate);

        colRow.add("联系人");
        colRow.add("联系电话");
        colRow.add("解决方式");
        //解决方式 operType
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("operType");
        dictInfos = getFacade().getSysDictInfoService().getSysDictInfoList(dictInfo);
        Map<String,Object> operTypeValidate = new HashMap<>();
        dictArr = new String[dictInfos.size()];
        for (int i = 0; i < dictInfos.size(); i++) {
            dictArr[i] = dictInfos.get(i).getDictLabel();
        }
        operTypeValidate.put("roles",dictArr);
        operTypeValidate.put("firstRow",1);
        operTypeValidate.put("lastRow",4000);
        operTypeValidate.put("firstCol",11);
        operTypeValidate.put("lastCol",11);
        validateRoles.add(operTypeValidate);

        colRow.add("期望完成时间");
        colRow.add("用户方确认人签名及确认意见");
        colRow.add("需求编号");
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //ExcelUtil.writeTemplateExcel(response,colRow,workbook,validateRoles,"工作簿1.xls");
    }


    @RequestMapping(value = "/testWxUI.do")
    public String qyeryReportInfo(){
        return "mobile2/wechat/site-jquery-weui";
    }

    @RequestMapping(value = "/impl.do")
    public String qyeryImplInfo(){
        return "mobile2/service/implement-work";
    }

    @Test
    public void sssTest(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url = "jdbc:sqlserver://172.16.0.211:1433;user=sa;password=zyc@8468";//sa身份连接
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);

            String SQL = "SELECT * FROM sys.databases;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString(1) );
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public Map<String, Object> userLogin(String userid,String password) throws Exception {
        System.out.println(userid);
        System.out.println(password);
        //QueryResult queryResult = pmisWorkingPaperService.userLoginPmis(userid,MD5.stringMD5(password));
        cn.com.winning.ssgj.ws.client.QueryResult
        queryResult =
        pmisWebServiceClient.userLoginValidateByPmis(userid,MD5.stringMD5(password));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", queryResult);
        return result;
    }



>>>>>>> 88475f27c4d1fa9b299c02adb5d2d4a630a9b4ce
}
