package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.SFtpUtils;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
@RequestMapping("/vue/easyDataCheck")
public class EtEasyDataCheckController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EtEasyDataCheckController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 易用数据数据校验表
     *
     * @param row
     * @param etEasyDataCheck
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    @ILog(operationName = "基础数据校验表", operationType = "list")
    public Map<String, Object> list(Row row, EtEasyDataCheck etEasyDataCheck) {
        //项目id
        Long pmId = etEasyDataCheck.getPmId();
        if (pmId == null) {
            return null;
        }
        //根据项目id获取项目基本信息
        PmisProjectBasicInfo pmisProjectBasicInfo = getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //获取合同id
        Long contractId = pmisProjectBasicInfo.getHtxx();
        //获取单据号即客户
        Long customerId = pmisProjectBasicInfo.getKhxx();

        etEasyDataCheck.setRow(row);

        etEasyDataCheck.setcId(contractId);

        etEasyDataCheck.setSerialNo(customerId.toString());
        //获取基础数据校验
        List<EtEasyDataCheck> etEasyDataChecks =
                getFacade().getEtEasyDataCheckService().getEtEasyDataCheckPaginatedList(etEasyDataCheck);
        int total = getFacade().getEtEasyDataCheckService().getEtEasyDataCheckCount(etEasyDataCheck);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", etEasyDataChecks);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
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
    public Map<String, Object> confirm(String proStr) {

        //项目id
        Long proId = Long.parseLong(proStr);
        if (proId == null) {
            return null;
        }
        //根据项目id获取项目基本信息
        PmisProjectBasicInfo pmisProjectBasicInfo = getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(proId);
        //获取合同id
        Long contractId = pmisProjectBasicInfo.getHtxx();
        //获取单据号即客户
        Long customerId = pmisProjectBasicInfo.getKhxx();

        EtDataCheck etDataCheck = new EtDataCheck();

        etDataCheck.setPmId(proId);

        etDataCheck.setCId(contractId);

        etDataCheck.setSerialNo(customerId.toString());
        int total = getFacade().getEtDataCheckService().getEtDataCheckCount(etDataCheck);

        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(proId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (total > 0) {
            etProcessManager.setIsBasicDataCheck(1);
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


