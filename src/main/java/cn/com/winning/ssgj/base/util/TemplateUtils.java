package cn.com.winning.ssgj.base.util;

import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.service.Facade;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-06-11 12:55
 */
public class TemplateUtils {

    /**
     * 创建工作底稿的导入模板
     * @param facade
     * @param response
     * @param serialNo 客户号
     * @param fileName 文件名
     */
    public static  void  createWorkreport(Facade facade,HttpServletResponse response,
                                          String serialNo,String fileName){
        List<Map<String,Object>> validateRoles = new ArrayList<>();
        List<String> colRow = new ArrayList<>();
        colRow.add("问题优先级 *");
        //问题优先级
        SysDictInfo dictInfo = new SysDictInfo();
        dictInfo.setDictCode("priorityType");
        List<SysDictInfo> dictInfos = facade.getSysDictInfoService().getSysDictInfoList(dictInfo);
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


        colRow.add("站点名称/科室名称 *");
        //站点名称/科室名称
//        EtDepartment dept = new EtDepartment();
//        EtDepartment info = new EtDepartment();
//        info.setIsDel(1);
//        info.setSerialNo(Long.parseLong(serialNo));
//        List<EtDepartment> departmentList= facade.getEtDepartmentService().getEtDepartmentList(info);
//        Map<String,Object> deptValidate = new HashMap<>();
//        String[] deptArr = new String[departmentList.size()];
//        for (int i = 0; i < departmentList.size(); i++) {
//            deptArr[i] = departmentList.get(i).getDeptName();
//        }
//        deptValidate.put("roles",deptArr);
//        deptValidate.put("firstRow",1);
//        deptValidate.put("lastRow",4000);
//        deptValidate.put("firstCol",1);
//        deptValidate.put("lastCol",1);
//        validateRoles.add(deptValidate);

        colRow.add("系统 *");
        //系统
//        EtContractTask task = new EtContractTask();
//        task.setSerialNo(serialNo);
//        List<EtContractTask> tasks = facade.getEtContractTaskService().getEtContractTaskList(task);
//        Map<String,Object> taskValidate = new HashMap<>();
//        String[] taskArr = new String[tasks.size()];
//        for (int i = 0; i < tasks.size(); i++) {
//            taskArr[i] = tasks.get(i).getZxtmc();
//        }
//        taskValidate.put("roles",taskArr);
//        taskValidate.put("firstRow",1);
//        taskValidate.put("lastRow",4000);
//        taskValidate.put("firstCol",2);
//        taskValidate.put("lastCol",2);
//        validateRoles.add(taskValidate);


        colRow.add("菜单 *");
        colRow.add("问题描述 *");
        colRow.add("底稿类型 *");
        //底稿类型
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("manuscriptType");
        dictInfos = facade.getSysDictInfoService().getSysDictInfoList(dictInfo);
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

        colRow.add("原因分类 *");
        //原因分类
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("reasonType");
        dictInfos = facade.getSysDictInfoService().getSysDictInfoList(dictInfo);
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

        colRow.add("底稿状态 *");
        //底稿状态 manuscriptStatus
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("manuscriptStatus");
        dictInfos = facade.getSysDictInfoService().getSysDictInfoList(dictInfo);
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

        colRow.add("难度 *");
        //难度 manuscriptStatus
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("diffcultLevel");
        dictInfos = facade.getSysDictInfoService().getSysDictInfoList(dictInfo);
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

        colRow.add("联系人 *");
        colRow.add("联系电话 *");
        colRow.add("解决方式 *");
        //解决方式 operType
        dictInfo = new SysDictInfo();
        dictInfo.setDictCode("operType");
        dictInfos = facade.getSysDictInfoService().getSysDictInfoList(dictInfo);
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

        colRow.add("期望完成时间 *");
        colRow.add("用户方确认人签名及确认意见");
        colRow.add("需求编号");
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.writeTemplateExcel(response,colRow,workbook,validateRoles,fileName);
    }
}
