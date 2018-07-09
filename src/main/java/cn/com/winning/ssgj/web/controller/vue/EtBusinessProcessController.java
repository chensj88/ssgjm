package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.FileUtis;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

/**
 * @author chenshijie
 * @title VUE业务流程调研
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-11 16:34
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/businessProcess/")
public class EtBusinessProcessController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 主页面数据展示
     * @param process
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    @Transactional
    public  Map<String,Object> queryFlowInfoByPmId(EtBusinessProcess process, Row row,String startDate,String endDate) throws ParseException {
        //生成业务流程信息
        super.getFacade().getCommonQueryService().generateEtBusinessProcessByProject(process);
        process.setRow(row);
        if(!"null".equals(startDate) && !"null".equals(endDate) && !StringUtil.isEmptyOrNull(startDate) && !StringUtil.isEmptyOrNull(endDate)){
            Map<String,Object> param = process.getMap();
            param.put("startDate",DateUtil.convertDateStringToTimestap(startDate));
            param.put("endDate",DateUtil.convertDateStringToTimestap(endDate));
        }
        //查询流程信息
        List<EtBusinessProcess> processList = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessPaginatedList(process);
        int total = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        //查询流程总的数量
        process.setIsScope(Constants.STATUS_USE);
        int sumBussinessProcessNum  = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        //查询完成的流程数量
        process.setStatus(Constants.APPROVAL_STATUS_END);
        int completeBPNum = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("rows", processList);
        result.put("total", total);
        result.put("sumNum", sumBussinessProcessNum);
        result.put("comNum", completeBPNum);
        return result;
    }

    /**
     * 实施范围的修改
     * @param process
     * @return
     */
    @RequestMapping(value = "/changeScope.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> changeScope(EtBusinessProcess process){
        process.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 统计完成和未完成数量
     * @param process
     * @return
     */
    @RequestMapping(value = "/countInfo.do")
    @ResponseBody
    public Map<String,Object> countProcessInfo(EtBusinessProcess process){
        //查询流程总的数量
        process.setIsScope(Constants.STATUS_USE);
        int sumBussinessProcessNum  = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        //查询完成的流程数量
        process.setStatus(Constants.APPROVAL_STATUS_END);
        int completeBPNum = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("sumNum", sumBussinessProcessNum);
        result.put("comNum", completeBPNum);
        return result;
    }

    /**
     * 添加流程信息
     * @param process
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> addOrModifyProcessInfo(EtBusinessProcess process){
        if (process.getId() == 0L) {
            process.setId(ssgjHelper.createEtBusinessProcessIdService());
            process.setFlowCode(ssgjHelper.createCustomerFlowCode());
            process.setSourceType(Constants.STATUS_USE);
            process.setStatus(Constants.STATUS_USE);
            process.setCreator(process.getOperator());
            process.setCreateTime(new Timestamp(new Date().getTime()));
            process.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtBusinessProcessService().createEtBusinessProcess(process);
        } else {
            process.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 删除流程调研信息
     * @param process
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> deleteBusinessProcess(EtBusinessProcess process){
        super.getFacade().getEtBusinessProcessService().removeEtBusinessProcess(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/deleteFile.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> deleteFile(EtBusinessProcess process){
        long operator = process.getOperator();
        process.setOperator(null);
        process = super.getFacade().getEtBusinessProcessService().getEtBusinessProcess(process);
        try {
            CommonFtpUtils.removeUploadFile(process.getUploadPath());
        }catch (Exception e){
            e.printStackTrace();
        }

        process.setUploadPath("");
        process.setOperator(operator);
        process.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
    /**
     * 流程数量确认
     * @param manager
     * @return
     */
    @RequestMapping(value = "/confirmFlowNum.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> comfirmBusinessProcess(EtProcessManager manager){
        int status = manager.getIsFlowAffirm();
        manager.setIsFlowAffirm(null);
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        manager.setIsFlowAffirm(status);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     *  流程调研与配置
     * @param manager
     * @return
     */
    @RequestMapping(value = "/confirmFlowConfig.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> confirmFlowConfig(EtProcessManager manager){
        int status = manager.getIsFlowConfig();
        manager.setIsFlowConfig(null);
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        manager.setIsFlowConfig(status);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 查看是否确认完成确认数量
     * @param manager
     * @return
     */
    @RequestMapping(value = "/checkAffirm.do")
    @ResponseBody
    public Map<String,Object> checkBusinessProcessAffirm(EtProcessManager manager){
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("isActive", manager.getIsFlowAffirm());
        result.put("isConfig", manager.getIsFlowConfig());
        return result;

    }

    /**
     * 查看是否完成流程配置工作
     * @param manager
     * @return
     */
    @RequestMapping(value = "/checkConfig.do")
    @ResponseBody
    public Map<String,Object> checkBusinessProcessConfig(EtProcessManager manager){
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", manager.getIsFlowConfig());
        return result;
    }

    /**
     * 上传文件
     * @param process
     * @param request
     * @param file
     * @return
     * @throws IOException
     * @info 上传文件目录为 /upload/PM_ID/process/流程表ID/文件名  避免重复
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> uploadFile(EtBusinessProcess process, HttpServletRequest request, MultipartFile file) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        process = super.getFacade().getEtBusinessProcessService().getEtBusinessProcess(process);
        boolean ftpStatus = false;
        if(!file.isEmpty()){
            //上传文件路径
            String path = request.getServletContext().getRealPath("/temp/");
            //上传文件名
//            String filename = FileUtis.generateFileName(file.getOriginalFilename());
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            File newFile = new File(path + File.separator + filename);
            if (newFile.exists()) {
                newFile.delete();
            }
            file.transferTo(newFile);
            String remotePath = Constants.UPLOAD_PC_PREFIX+process.getPmId()+"/process/" + DateUtil.getTimstamp() +"/"+filename;
            try {
                CommonFtpUtils.uploadFile(remotePath,newFile);
                process.setStatus(1);
                process.setUploadPath(remotePath);
                super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
                newFile.delete();
                result.put("status", "success");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是："+e.getMessage());
            }
        }else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;

    }

    /**
     * 加载流程的配置信息
     * @param flowInfo
     * @return map
     */
    @RequestMapping(value = "/loadConfig.do")
    @ResponseBody
    public Map<String, Object> loadConfig(SysFlowInfo flowInfo){
        List<SysFlowInfo> configFlows = super.getFacade().getSysFlowInfoService().getSysFlowInfoList(flowInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        result.put("type", configFlows == null ? 0 : configFlows.size() > 0 ? 1 : 0  );
        result.put("data", configFlows);
        return result;
    }

    /**
     * 执行配置的存储函数
     * @param id
     * @param sql
     * @param flowId
     * @param procName
     * @return
     */
    @RequestMapping(value = "/useSql.do")
    @ResponseBody
    public Map<String, Object> useSql(Long id,String sql,Long flowId,String procName,String procParam){
        ResultSet rs = null;
        Map<String, Object> result = new HashMap<>();
        //医院数据库连接
        EtDatabasesList entity = new EtDatabasesList();
        entity.setId(id);
        entity = super.getFacade().getEtDatabasesListService().getEtDatabasesList(entity);
        String url ="jdbc:sqlserver://"+entity.getIp()+";DatabaseName="+entity.getDatabaseName()+";user="+entity.getUserName()+";password="+entity.getPw();
        //获取
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url);
            if (connection == null) {
                resultMap.put("status", Constants.FAILD);
                resultMap.put("msg", "数据库无法连接，请检查网络!");
                return result;
            }
            //判断存储过程是否存在
            String existsProcSql = "if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[" + procName + "]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)   begin drop procedure [dbo].[" + procName + "] end";
            //配置
            String preSql = "set QUOTED_IDENTIFIER  OFF;\n" + "set ANSI_NULLS  OFF;\n" + "set ANSI_NULL_DFLT_ON OFF;\n" +
                    "set ANSI_PADDING OFF ;\n" + "set ANSI_WARNINGS OFF; ";
            //存储过程
            String runProcSql = "exec " + procName + procParam;
            PreparedStatement ps = connection.prepareStatement(existsProcSql);
            ps.execute();
            ps = connection.prepareStatement(preSql);
            ps.execute();
            sql = sql.replace("\"", "\'");
            ps = connection.prepareStatement(sql);
            ps.execute();
            ps = connection.prepareStatement(runProcSql);
            int i = ps.executeUpdate();
            //将已经执行的业务流程调研状态修改为1
            EtBusinessProcess process = new EtBusinessProcess();
            process.setId(flowId);
            process.setIsConfig(1);
            super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);

            result.put("status",Constants.SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            result.put("status",Constants.FAILD);
            resultMap.put("msg", e.getMessage());
        }


        return result;

    }


}
