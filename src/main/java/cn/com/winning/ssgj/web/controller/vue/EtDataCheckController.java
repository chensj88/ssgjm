package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础数据类型处理Controller
 *
 * @author FengChen
 * @date 2018年3月18日10:37:18
 */
@CrossOrigin
@Controller
@RequestMapping("/vue/dataCheck")
public class EtDataCheckController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EtDataCheckController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 根据项目id初始化数据源
     *
     * @param pmId
     * @param operator
     */
    @RequestMapping("/initSourceData.do")
    @ResponseBody
    public Map<String, Object> initSourceData(Long pmId, Long operator, Integer dataType) {
        Map map = new HashMap();
        if (pmId == null) {
            return null;
        }
        //根据pmId获取项目基础信息
        PmisProjectBasicInfo pmisProjectBasicInfo = this.getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //cId
        Long cId = pmisProjectBasicInfo.getHtxx();
        //serialNo
        Long serialNo = pmisProjectBasicInfo.getKhxx();
        EtDataCheck etDataCheck = new EtDataCheck();
        etDataCheck.setPmId(pmId);
        etDataCheck.setcId(cId);
        etDataCheck.setSerialNo(serialNo.toString());
        //根据pmId获取基础数据校验数据
        List<EtDataCheck> etDataCheckList = getFacade().getEtDataCheckService().selectEtDataCheckListByPmIdAndDataType(etDataCheck);
        EtDataCheck etDataCheckTemp = null;
        for (EtDataCheck dataCheck : etDataCheckList) {
            etDataCheckTemp = new EtDataCheck();
            etDataCheckTemp.setPmId(dataCheck.getPmId());
            etDataCheckTemp.setPlId(dataCheck.getPlId());
            etDataCheckTemp = getFacade().getEtDataCheckService().getEtDataCheck(etDataCheckTemp);
            if (etDataCheckTemp == null) {
                //不存在则插入
                dataCheck.setId(ssgjHelper.createEtDataCheckId());
                getFacade().getEtDataCheckService().createEtDataCheck(dataCheck);
            }
        }
        map.put("status", Constants.SUCCESS);
        map.put("msg", "初始化数据成功！");
        return map;
    }

    /**
     * 基础数据类型列表
     *
     * @param row
     * @param pmId 项目id
     * @return
     * @description 根据项目id获取基础数据
     */
    @RequestMapping("/list.do")
    @ResponseBody
    @ILog(operationName = "基础数据校验表", operationType = "list")
    public Map<String, Object> list(Row row, Long pmId, String operator) {
        //项目id
        if (pmId == null) {
            return null;
        }
        //根据项目id获取项目基本信息
        PmisProjectBasicInfo pmisProjectBasicInfo = getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //获取合同id
        Long contractId = pmisProjectBasicInfo.getHtxx();
        //获取单据号即客户
        Long customerId = pmisProjectBasicInfo.getKhxx();

        EtDataCheck etDataCheck = new EtDataCheck();

        etDataCheck.setRow(row);

        etDataCheck.setPmId(pmId);

        etDataCheck.setcId(contractId);

        etDataCheck.setSerialNo(customerId.toString());
        //获取基础数据校验
        List<EtDataCheck> etDataCheckList =
                getFacade().getEtDataCheckService().getEtDataCheckPaginatedList(etDataCheck);
        //封装外参数
        PmisProductLineInfo pmisProductLineInfo = null;
        //封装外参数
        for (EtDataCheck e : etDataCheckList) {
            pmisProductLineInfo=new PmisProductLineInfo();
            pmisProductLineInfo.setId(e.getPlId());
            pmisProductLineInfo = getFacade().getPmisProductLineInfoService().getPmisProductLineInfo(pmisProductLineInfo);
            Map<String, Object> map = new HashMap();
            map.put("type", pmisProductLineInfo.getName());
            String checkResult = e.getCheckResult();
            if (checkResult == null || "没问题".equals(checkResult) || "校验正常".equals(checkResult) || "".equals(checkResult)) {
                map.put("state", 0);
            } else {
                map.put("state", 1);
            }
            e.setMap(map);
        }
        int total = getFacade().getEtDataCheckService().getEtDataCheckCount(etDataCheck);
        //根据pmid获取项目进程
        EtProcessManager etProcessManager=new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", etDataCheckList);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        map.put("process",etProcessManager);
        return map;
    }


    /**
     * 获取基础数据详情
     *
     * @param t
     * @return
     */
    @RequestMapping("/detail.do")
    @ResponseBody
    @ILog
    public Map<String, Object> detail(EtDataCheck t) {
        //根据id获取表属性
        EtDataCheck etDataCheck = getFacade().getEtDataCheckService().getEtDataCheck(t);
        //获取content
        String content = etDataCheck.getContent();
        if (StringUtil.isEmptyOrNull(content)) {
            return null;
        }
        List<List<Object>> parse = (List<List<Object>>) JSONArray.parse(content);
        //封装content
        List<JSONObject> detailList = new ArrayList<>();
        for (List<Object> objList : parse) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("question", objList.get(1).toString());
            jsonObject.put("everyTime", objList.get(2).toString());
            detailList.add(jsonObject);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", detailList);
        map.put("status", Constants.SUCCESS);
        return map;
    }


    /**
     * @param request
     * @param file
     * @return
     * @throws IOException
     * @description 文件上传
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file, EtDataCheck t) throws IOException {
        //根据id获取表属性
        EtDataCheck etDataCheck = getFacade().getEtDataCheckService().getEtDataCheck(t);
        SysDataCheckScript sysDataCheckScript = new SysDataCheckScript();
        sysDataCheckScript.setAppId(etDataCheck.getPlId());
        sysDataCheckScript = getFacade().getSysDataCheckScriptService().getSysDataCheckScript(sysDataCheckScript);
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/script/");
            //上传文件名
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
            JSONArray jsonArray = new JSONArray();
            //错误计数
            Integer failNum = 0;
            //检测结果
            String checkResult = "";
            try {
                List<List<Object>> etDataCheckList = ExcelUtil.importExcel(newFile.getPath());
                for (List<Object> e : etDataCheckList) {
                    for (int i = 0; i < e.size(); i++) {
                        if ("F".equalsIgnoreCase(e.get(i).toString())) {
                            failNum++;
                        }
                    }
                    jsonArray.add(e);
                }
                System.out.println(jsonArray.toJSONString());
                etDataCheck.setContent(jsonArray.toJSONString());
                if (failNum == null || failNum == 0) {
                    checkResult = "校验正常";
                } else {
                    checkResult = "校验出" + failNum + "个问题";
                }
                etDataCheck.setCheckResult(checkResult);
                //文件夹路径
                String dir = "/check/" + sysDataCheckScript.getAppName() + "/";
                String src = newFile.getAbsolutePath();
                String fileName = newFile.getName();
                etDataCheck.setScriptPath(dir + fileName);
                etDataCheck.setOperatorTime(new Timestamp(new Date().getTime()));
                getFacade().getEtDataCheckService().modifyEtDataCheck(etDataCheck);
                //将文件上传到ftp服务器
                SFtpUtils.uploadFile(src, dir, fileName);
                newFile.delete();
                result.put("status", "success");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是：" + e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }


    /**
     * @param response
     * @return
     * @throws IOException
     * @description 导出sql
     */
    @RequestMapping(value = "/exportSql.do")
    @ResponseBody
    @ILog
    public void exportSql(HttpServletResponse response, EtDataCheck t) throws IOException {
        //获取数据校验信息
        EtDataCheck etDataCheck = getFacade().getEtDataCheckService().getEtDataCheck(t);
        SysDataCheckScript temp = new SysDataCheckScript();
        temp.setAppId(etDataCheck.getPlId());
        SysDataCheckScript sysDataCheckScript = getFacade().getSysDataCheckScriptService().getSysDataCheckScript(temp);
        //获取脚本地址
        if (sysDataCheckScript == null) {
            return;
        }
        String scriptPath = sysDataCheckScript.getRemotePath();
        //获取文件名
        String filename = scriptPath.substring(scriptPath.lastIndexOf("/") + 1);
        ChannelSftp sftpConnect = null;
        byte[] bytes = null;
        try {
            sftpConnect = SFtpUtils.getSftpConnect();
            //sftpConnect.setFilenameEncoding("GBK");
            bytes = SFtpUtils.downloadAsByte(scriptPath, sftpConnect);

        } catch (Exception e) {
            e.printStackTrace();
        }


        response.setCharacterEncoding("utf-8");
        //设置响应内容的类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
        BufferedOutputStream buff = null;
        OutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(bytes);
            buff.flush();
            buff.close();
        } catch (Exception e) {
            logger.error("导出文件文件出错，e:{}", e);
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception e) {
                logger.error("关闭流对象出错 e:{}", e);
            }
        }
    }


    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> confirm(EtDataCheck etDataCheck) {

        //项目id
        Long pmId = etDataCheck.getPmId();
        if (pmId == null) {
            return null;
        }
        EtDataCheck dataCheck=new EtDataCheck();
        dataCheck.setPmId(pmId);
        int total = getFacade().getEtDataCheckService().getEtDataCheckCount(dataCheck);
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (total > 0) {
            etProcessManager.setIsBasicDataCheck(1);
            etProcessManager.setOperator(etDataCheck.getOperator());
            etProcessManager.setOperatorTime(new Timestamp(new Date().getTime()));
            getFacade().getEtProcessManagerService().updateEtProcessManagerByPmId(etProcessManager);
            map.put("type", Constants.SUCCESS);
            map.put("msg", "确认成功！");
        } else {
            map.put("type", "info");
            map.put("msg", "无数据，确认失败！");
        }
        return map;
    }
}


