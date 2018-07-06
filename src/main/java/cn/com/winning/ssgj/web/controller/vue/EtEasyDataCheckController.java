package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.service.EtEasyDataCheckDetailService;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.jcraft.jsch.ChannelSftp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.io.*;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    private static final String DOCTOR_MAINTAIN_LIST = "doctorMaintainNum";
    private static final String DOCTOR_NOT_MAINTAIN_LIST = "doctorNotMaintainNum";
    private static final String DEPT_MAINTAIN_LIST = "deptMaintainNum";
    private static final String DEPT_NOT_MAINTAIN_LIST = "deptNotMaintainNum";
    @Autowired
    private SSGJHelper ssgjHelper;


    /**
     * 数据初始化
     *
     * @param pmId
     * @param operator
     * @param dataType
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
        EtEasyDataCheck etEasyDataCheck = new EtEasyDataCheck();
        etEasyDataCheck.setPmId(pmId);
        etEasyDataCheck.setcId(cId);
        etEasyDataCheck.setSerialNo(serialNo.toString());
        //根据pmId获取易用数据校验数据
        List<EtEasyDataCheck> etEasyDataChecks = getFacade().getEtEasyDataCheckService().getInitEtEasyDataCheck(etEasyDataCheck);
        for (EtEasyDataCheck easyDataCheck : etEasyDataChecks) {
            EtEasyDataCheck etEasyDataCheckTemp = new EtEasyDataCheck();
            etEasyDataCheckTemp.setPmId(easyDataCheck.getPmId());
            etEasyDataCheckTemp.setSourceId(easyDataCheck.getSourceId());
            etEasyDataCheckTemp = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(etEasyDataCheckTemp);
            if (etEasyDataCheckTemp == null) {
                //不存在则插入
                easyDataCheck.setId(ssgjHelper.createEtEasyDataCheckId());
                getFacade().getEtEasyDataCheckService().createEtEasyDataCheck(easyDataCheck);
            }
        }

        map.put("status", Constants.SUCCESS);
        map.put("msg", "初始化数据成功！");
        return map;
    }

    /**
     * 易用数据数据校验表
     *
     * @param row
     * @param etEasyDataCheck
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
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

        EtEasyDataCheck easyDataCheckTemp = new EtEasyDataCheck();

        easyDataCheckTemp.setRow(row);

        easyDataCheckTemp.setPmId(pmId);

        easyDataCheckTemp.setcId(contractId);

        easyDataCheckTemp.setSerialNo(customerId.toString());

        //获取基础数据校验
        List<EtEasyDataCheck> etEasyDataChecks =
                getFacade().getEtEasyDataCheckService().getEtEasyDataCheckPaginatedList(easyDataCheckTemp);
        int total = getFacade().getEtEasyDataCheckService().getEtEasyDataCheckCount(easyDataCheckTemp);
        //封装外参数
        PmisProductLineInfo pmisProductLineInfo = null;
        for (EtEasyDataCheck easyDataCheck : etEasyDataChecks) {
            //封装属性
            Map propMap = new HashMap();
            SysDataCheckScript sysDataCheckScript = new SysDataCheckScript();
            sysDataCheckScript.setId(easyDataCheck.getSourceId());
            sysDataCheckScript = getFacade().getSysDataCheckScriptService().getSysDataCheckScript(sysDataCheckScript);
            String content = easyDataCheck.getContent();
            if (StringUtil.isEmptyOrNull(content) || "正常".equals(content)) {
                propMap.put("state", 0);
            } else {
                propMap.put("state", 1);
            }
            if (sysDataCheckScript != null) {
                String scriptPath = sysDataCheckScript.getRemotePath();
                scriptPath = scriptPath.substring(scriptPath.lastIndexOf("/") + 1, scriptPath.lastIndexOf("."));
                propMap.put("type", scriptPath);
            }
            easyDataCheck.setMap(propMap);
        }
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        //获取ipList
        EtDatabasesList etDatabasesList = new EtDatabasesList();
        etDatabasesList.setPmId(pmId);
        List<EtDatabasesList> etDatabasesListList = getFacade().getEtDatabasesListService().getEtDatabasesListList(etDatabasesList);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", etEasyDataChecks);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        map.put("ipList", etDatabasesListList);
        map.put("process", etProcessManager);
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
    public Map<String, Object> detail(EtEasyDataCheckDetail t) {
        //根据sourceId获取表属性
        List<EtEasyDataCheckDetail> etEasyDataCheckDetails = getFacade().getEtEasyDataCheckDetailService().getEtEasyDataCheckDetailList(t);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", etEasyDataCheckDetails);
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
    @Transactional
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file, EtEasyDataCheck t, Long pmId) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //根据id获取表属性
        EtEasyDataCheck etEasyDataCheck = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(t);
        //根据应用数据id删除detail表数据
        EtEasyDataCheckDetail etEasyDataCheckDetail = new EtEasyDataCheckDetail();
        etEasyDataCheckDetail.setSourceId(t.getId());
        getFacade().getEtEasyDataCheckDetailService().removeEtEasyDataCheckDetail(etEasyDataCheckDetail);
        //获取产品条线名称
        PmisProductLineInfo pmisProductLineInfo = new PmisProductLineInfo();
        pmisProductLineInfo.setId(etEasyDataCheck.getPlId());
        pmisProductLineInfo = getFacade().getPmisProductLineInfoService().getPmisProductLineInfo(pmisProductLineInfo);
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/check/");
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
            Map<String, Integer> map = null;
            //解析Excel文件
            try {
                map = parseExcel(newFile.getPath(), etEasyDataCheck.getId(), getFacade().getEtEasyDataCheckDetailService());

                if (map == null) {
                    result.put("status", "error");
                    result.put("msg", "文件解析出错！");
                    return result;
                }

                //获取解析集合
                Integer doctorMaintainNum = map.get(DOCTOR_MAINTAIN_LIST);
                Integer doctorNotMaintainNum = map.get(DOCTOR_NOT_MAINTAIN_LIST);
                Integer deptMaintainNum = map.get(DEPT_MAINTAIN_LIST);
                Integer deptNotMaintainNum = map.get(DEPT_NOT_MAINTAIN_LIST);

                String content = "";
                //当无未维护数据时，校验正常
                if (doctorNotMaintainNum == 0 && deptNotMaintainNum == 0) {
                    content = "校验正常";

                } else {
                    //计算医生维护率
                    String docStr = NumberParseUtil.parsePercent(doctorMaintainNum, doctorMaintainNum + doctorNotMaintainNum);
                    //计算科室维护率
                    String deptStr = NumberParseUtil.parsePercent(deptMaintainNum, deptMaintainNum + deptNotMaintainNum);
                    content = docStr + "的医生维护；" + deptStr + "的科室维护。";
                }
                //更新易用校验数据content
                etEasyDataCheck.setContent(content);
                //文件夹路径
                String dir = Constants.UPLOAD_PC_PREFIX + pmId + "/easyDataCheck/" + pmisProductLineInfo.getName() + "/";
                String src = newFile.getAbsolutePath();
                String fileName = newFile.getName();
                etEasyDataCheck.setScriptPath(dir + fileName);
                getFacade().getEtEasyDataCheckService().modifyEtEasyDataCheck(etEasyDataCheck);
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
     * excel文件解析
     *
     * @param filePath
     * @param sourceId
     * @return
     * @throws Exception
     */
    private Map<String, Integer> parseExcel(String filePath, Long sourceId, EtEasyDataCheckDetailService service) throws Exception {
        EtEasyDataCheckDetail temp = new EtEasyDataCheckDetail();
        temp.setSourceId(sourceId);
        //清库
        service.removeEtEasyDataCheckDetail(temp);
        Map<String, Integer> map = new HashMap<>();
        //创建Excel工作薄
        File finalXlsxFile = new File(filePath);
        Workbook work = null;
        try {
            work = ExcelUtil.getWorkbook(finalXlsxFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        org.apache.poi.ss.usermodel.Row row = null;
        Cell cell = null;
        //已经维护的个人协定数据
        Integer doctorMaintainNum = 0;
        //已经维护的科室协定数据
        Integer deptMaintainNum = 0;
        //未维护的个人协定数据
        Integer doctorNotMaintainNum = 0;
        //未维护的科室协定数据
        Integer deptNotMaintainNum = 0;

        //标识
        String flag = "";
        //获取第一页
        sheet = work.getSheetAt(0);
        //遍历当前sheet中的所有行
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            row = sheet.getRow(j);
            if (row == null) {
                continue;
            }
            if ("已经维护的个人协定方信息统计:".equals(row.getCell(0) + "")) {
                flag = DOCTOR_MAINTAIN_LIST;
                continue;
            } else if ("工号".equals(row.getCell(0) + "")) {
                flag = DOCTOR_NOT_MAINTAIN_LIST;
                continue;
            } else if ("已经维护的科室协定方信息统计:".equals(row.getCell(0) + "")) {
                flag = DEPT_MAINTAIN_LIST;
                continue;
            } else if ("科室代码".equals(row.getCell(0) + "")) {
                flag = DEPT_NOT_MAINTAIN_LIST;
                continue;
            }
            if (DOCTOR_MAINTAIN_LIST.equals(flag)) {
                //已经维护的个人协定数据封装
                doctorMaintainNum++;
            } else if (DOCTOR_NOT_MAINTAIN_LIST.equals(flag)) {
                //未维护的个人协定数据封装
                EtEasyDataCheckDetail etEasyDataCheckDetail = new EtEasyDataCheckDetail();
                etEasyDataCheckDetail.setId(ssgjHelper.createEtEasyDataCheckDetailId());
                etEasyDataCheckDetail.setSourceId(sourceId);
                etEasyDataCheckDetail.setDeptDoctorCode(row.getCell(0) == null ? null : (int) Double.parseDouble(row.getCell(0).toString()) + "");
                etEasyDataCheckDetail.setDeptDoctorName(row.getCell(1) == null ? null : row.getCell(1).toString());
                service.createEtEasyDataCheckDetail(etEasyDataCheckDetail);
                doctorNotMaintainNum++;
            } else if (DEPT_MAINTAIN_LIST.equals(flag)) {
                //已经维护的科室协定数据封装
                deptMaintainNum++;
            } else if (DEPT_NOT_MAINTAIN_LIST.equals(flag)) {
                //未维护的科室协定数据封装
                EtEasyDataCheckDetail etEasyDataCheckDetail = new EtEasyDataCheckDetail();
                etEasyDataCheckDetail.setId(ssgjHelper.createEtEasyDataCheckDetailId());
                etEasyDataCheckDetail.setSourceId(sourceId);
                etEasyDataCheckDetail.setDeptDoctorCode(row.getCell(0) == null ? null : (int) Double.parseDouble(row.getCell(0).toString()) + "");
                etEasyDataCheckDetail.setDeptDoctorName(row.getCell(1) == null ? null : row.getCell(1).toString());
                service.createEtEasyDataCheckDetail(etEasyDataCheckDetail);
                deptNotMaintainNum++;
            }
        }
        map.put(DOCTOR_MAINTAIN_LIST, doctorMaintainNum);
        map.put(DOCTOR_NOT_MAINTAIN_LIST, doctorNotMaintainNum);
        map.put(DEPT_MAINTAIN_LIST, deptMaintainNum);
        map.put(DEPT_NOT_MAINTAIN_LIST, deptNotMaintainNum);
        return map;
    }


    /**
     * @param response
     * @return
     * @throws IOException
     * @description 导出sql
     */
    @RequestMapping(value = "/exportSql.do")
    @ResponseBody
    public Map<String, Object> exportSql(HttpServletResponse response, EtEasyDataCheck t) throws IOException {
        //获取数据校验信息
        EtEasyDataCheck etEasyDataCheck = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(t);
        SysDataCheckScript temp = new SysDataCheckScript();
        Long sourceId = etEasyDataCheck.getSourceId();
        Map map = new HashMap();
        if (sourceId == null) {
            return null;
        }
        temp.setId(sourceId);
        SysDataCheckScript sysDataCheckScript = getFacade().getSysDataCheckScriptService().getSysDataCheckScript(temp);
        //增加null判断
        if (sysDataCheckScript == null) {
            logger.error("Script is not exist!");
            map.put("error", "Script is not exist!");
            return map;
        }
        //获取脚本地址
        String scriptPath = sysDataCheckScript.getRemotePath();
        if (StringUtil.isEmptyOrNull(scriptPath)) {
            logger.error("Script path is null or empty!");
            map.put("error", "Script path is null or empty!");
            return map;
        }
        //获取文件名
        String filename = scriptPath.substring(scriptPath.lastIndexOf("/") + 1);
        String path = scriptPath.replace(filename, "");
        byte[] bytes = null;
        try {
            bytes = CommonFtpUtils.downloadFileAsByte(path, filename);

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
        map.put("status", Constants.SUCCESS);
        return map;
    }


    /**
     * 确认完成
     *
     * @param etProcessManager
     * @return
     */
    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> confirm(EtProcessManager etProcessManager) {
        EtProcessManager temp = new EtProcessManager();
        temp.setPmId(etProcessManager.getPmId());
        temp = super.getFacade().getEtProcessManagerService().getEtProcessManager(temp);
        temp.setOperator(etProcessManager.getOperator());
        temp.setOperatorTime(new Timestamp(new Date().getTime()));
        temp.setIsEasyDataCheck(etProcessManager.getIsEasyDataCheck());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    /**
     * 维护状态改变
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "/changeUse.do")
    @ResponseBody
    public void changeUse(EtEasyDataCheck t) {
        EtEasyDataCheck etEasyDataCheck = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(t);
        t.setOperatorTime(new Timestamp(new Date().getTime()));
        //null校验
        if (etEasyDataCheck == null) {
            return;
        }
        //更新维护状态
        getFacade().getEtEasyDataCheckService().modifyEtEasyDataCheck(t);

    }

    /**
     * @param response
     * @return
     * @throws IOException
     * @description 导出sql
     */
    @RequestMapping(value = "/exportModel.do")
    @ResponseBody
    public void exportModel(HttpServletResponse response, EtDataCheck t) throws IOException {

        String realPath = Thread.currentThread().getContextClassLoader().getResource("/template").getPath();
        //获取文件名
        String filename = realPath + "\\EasyDataCheckModel.xlsx";
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream inputStream = new BufferedInputStream(fileInputStream);
        String excelName = "易用数据校验模板" + DateUtil.format(DateUtil.PATTERN_14) + ".xlsx";
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        response.setCharacterEncoding("utf-8");
        //设置响应内容的类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(excelName, "UTF-8"))));
        BufferedOutputStream buff = null;
        OutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(bytes);
            buff.flush();
            buff.close();
        } catch (Exception e) {
            logger.error("导出模板出错，e:{}", e);
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception e) {
                logger.error("关闭流对象出错 e:{}", e);
            }
        }
    }


    /**
     * 批量导出sql压缩包
     *
     * @param response
     * @param idsStr
     * @throws IOException
     */
    @RequestMapping(value = "/batchExport.do")
    @ResponseBody
    public void batchExport(HttpServletResponse response, String idsStr) throws IOException {
        if (StringUtil.isEmptyOrNull(idsStr)) {
            return;
        }
        String[] split = idsStr.split(",");
        List<String> ids = Arrays.asList(split);
        EtEasyDataCheck easyDataCheck = new EtEasyDataCheck();
        HashMap map = new HashMap();
        map.put("ids", ids);
        easyDataCheck.setMap(map);
        List<List> fileByteList = new ArrayList();
        //获取数据校验信息
        List<EtEasyDataCheck> etEasyDataChecks = getFacade().getEtEasyDataCheckService().getEtEasyDataCheckList(easyDataCheck);
        for (int i = 0; i < etEasyDataChecks.size(); i++) {
            EtEasyDataCheck etEasyDataCheck = etEasyDataChecks.get(i);
            //获取
            SysDataCheckScript temp = new SysDataCheckScript();
            Long sourceId = etEasyDataCheck.getSourceId();
            if (sourceId == null) {
                return;
            }
            temp.setId(sourceId);
            SysDataCheckScript sysDataCheckScript = getFacade().getSysDataCheckScriptService().getSysDataCheckScript(temp);
            if (sysDataCheckScript == null) {
                return;
            }
            //获取脚本地址
            String scriptPath = sysDataCheckScript.getRemotePath();
            if (StringUtil.isEmptyOrNull(scriptPath)) {
                return;
            }
            //获取文件名
            String filename = scriptPath.substring(scriptPath.lastIndexOf("/") + 1);
//            String saveFile = "/sql/" + filename.substring(0, filename.indexOf(".")) + "_" + i + "." + filename.substring(filename.indexOf(".") + 1);
            String ftpPath = scriptPath.replace(filename, "");
//            File file = null;
            List byteList = new ArrayList();
            byteList.add(0, filename);
            try {
                byte[] fileByte = CommonFtpUtils.downloadFileAsByte(filename, ftpPath);
                byteList.add(1, fileByte);
                fileByteList.add(byteList);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //压缩输出流
        response.setContentType("application/zip");
        response.setCharacterEncoding("utf-8");
        //这里需要针对打出的压缩包名称的编码格式进行乱码处理：new String(("压缩包名称").getBytes("GBK"), "iso8859-1")
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + new String((new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip").getBytes("GBK"), "iso8859-1") + "\"");
        BufferedOutputStream buff = null;
        OutputStream outStr = null;
        outStr = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outStr));
        int len = 0;
        FileInputStream fileInputStream = null;
        String fileName = null;
        //循环打包到输出流
        for (List currentList : fileByteList) {
            fileName = (String) currentList.get(0);
            byte[] buf = (byte[]) currentList.get(1);
            //放入压缩zip包中;
            zipOutputStream.putNextEntry(new ZipEntry(fileName));

            //读取文件;
            zipOutputStream.write(buf);

            //关闭;
            zipOutputStream.closeEntry();
            if (fileInputStream != null) {
                fileInputStream.close();

            }
        }
        zipOutputStream.flush();
        zipOutputStream.close();
    }


    /**
     * 更改范围
     *
     * @param etEasyDataCheck
     * @return
     */
    @RequestMapping(value = "/changeScope.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> changeScope(EtEasyDataCheck etEasyDataCheck) {
        String noScopeCode = etEasyDataCheck.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etEasyDataCheck.setIsScope(1);
        } else {
            etEasyDataCheck.setIsScope(0);
        }
        Map map = new HashMap();
        etEasyDataCheck.setOperatorTime(new Timestamp(new Date().getTime()));
        getFacade().getEtEasyDataCheckService().modifyEtEasyDataCheck(etEasyDataCheck);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "范围修改成功！");
        return map;
    }


    /**
     * 脚本校验
     *
     * @param etEasyDataCheck
     * @return
     */
    @RequestMapping(value = "/doScriptCheck.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> doScriptCheck(EtEasyDataCheck etEasyDataCheck) {
        if (etEasyDataCheck.getIpId() == null) {
            resultMap.put("status", Constants.FAILD);
            return resultMap;
        }
        //ipId
        EtDatabasesList etDatabasesList = new EtDatabasesList();
        etDatabasesList.setId(etEasyDataCheck.getIpId());
        etDatabasesList = getFacade().getEtDatabasesListService().getEtDatabasesList(etDatabasesList);
        if (etDatabasesList == null) {
            resultMap.put("status", Constants.FAILD);
            return resultMap;
        }
        //数据库参数
        String ip = etDatabasesList.getIp();
        String userName = etDatabasesList.getUserName();
        String pw = etDatabasesList.getPw();
//        String databaseName = etDataCheck.getDatabaseName();
        String databaseName = etDatabasesList.getDatabaseName();
        //连接数据库
        Connection connection = ConnectionUtil.getConnection(ip, userName, pw, databaseName);
        if (connection == null) {
            resultMap.put("status", Constants.FAILD);
            return resultMap;
        }
        EtEasyDataCheck temp = new EtEasyDataCheck();
        temp.setId(etEasyDataCheck.getId());
        temp = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(temp);
        SysDataCheckScript sysDataCheckScript = new SysDataCheckScript();
        sysDataCheckScript.setId(temp.getSourceId());
        sysDataCheckScript = getFacade().getSysDataCheckScriptService().getSysDataCheckScript(sysDataCheckScript);
        //获取select sql 集合
        String sqlStr = sysDataCheckScript.getsDesc();
        String[] sqlArr = sqlStr.split(";");
        List<String> sqlList = Arrays.asList(sqlArr);
        //已经维护的个人协定数据
        Integer doctorMaintainNum = 0;
        //已经维护的科室协定数据
        Integer deptMaintainNum = 0;
        //未维护的个人协定数据
        Integer doctorNotMaintainNum = 0;
        //未维护的科室协定数据
        Integer deptNotMaintainNum = 0;
        //清空校验数据
        EtEasyDataCheckDetail detailTemp = new EtEasyDataCheckDetail();
        detailTemp.setSourceId(etEasyDataCheck.getId());
        getFacade().getEtEasyDataCheckDetailService().removeEtEasyDataCheckDetail(detailTemp);
        List<EtEasyDataCheckDetail> etEasyDataCheckDetails = new ArrayList<>();
        logger.info("start insert detail>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info("start time:{}",System.currentTimeMillis());
        for (int i = 0; i < sqlList.size(); i++) {
            ResultSet rs = null;
            logger.info("sql:{}", sqlList.get(i));
            try {
                PreparedStatement ps = connection.prepareStatement(sqlList.get(i));
                rs = ps.executeQuery();
                if (i == 0) {
                    while (rs.next()) {
                        doctorMaintainNum++;
                    }
                } else if (i == 1) {
                    while (rs.next()) {
                        doctorNotMaintainNum++;
                        if (doctorNotMaintainNum > 1) {
                            EtEasyDataCheckDetail detail = new EtEasyDataCheckDetail();
                            detail.setId(ssgjHelper.createEtEasyDataCheckDetailId());
                            detail.setSourceId(etEasyDataCheck.getId());
                            detail.setDeptDoctorCode(rs.getString(1));
                            detail.setDeptDoctorName(rs.getString(2));
                            etEasyDataCheckDetails.add(detail);
//                            getFacade().getEtEasyDataCheckDetailService().createEtEasyDataCheckDetail(detail);

                        }
                    }
                } else if (i == 2) {
                    while (rs.next()) {
                        deptMaintainNum++;
                    }
                } else if (i == 3) {
                    while (rs.next()) {
                        deptNotMaintainNum++;
                        if (deptNotMaintainNum > 1) {
                            EtEasyDataCheckDetail detail = new EtEasyDataCheckDetail();
                            detail.setId(ssgjHelper.createEtEasyDataCheckDetailId());
                            detail.setSourceId(etEasyDataCheck.getId());
                            detail.setDeptDoctorCode(rs.getString(1));
                            detail.setDeptDoctorName(rs.getString(2));
                            etEasyDataCheckDetails.add(detail);
//                            getFacade().getEtEasyDataCheckDetailService().createEtEasyDataCheckDetail(detail);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("status", Constants.FAILD);
                resultMap.put("msg", e.getMessage());
                return resultMap;
            }
        }

        getFacade().getEtEasyDataCheckDetailService().insertEtEasyDataCheckDetailByList(etEasyDataCheckDetails);
        logger.info("start time:{}",System.currentTimeMillis());
        logger.info("end insert detail>>>>>>>>>>>>>>>>>>>>>>>>>");
        doctorMaintainNum--;
        doctorNotMaintainNum--;
        deptMaintainNum--;
        deptNotMaintainNum--;
        String content = "";
        //当无未维护数据时，校验正常
        if (doctorNotMaintainNum == 0 && deptNotMaintainNum == 0) {
            content = "校验正常";

        } else {
            //计算医生维护率
            String docStr = NumberParseUtil.parsePercent(doctorMaintainNum, doctorMaintainNum + doctorNotMaintainNum);
            //计算科室维护率
            String deptStr = NumberParseUtil.parsePercent(deptMaintainNum, deptMaintainNum + deptNotMaintainNum);
            content = docStr + "的医生维护；" + deptStr + "的科室维护。";
        }
        //更新易用校验数据content
        etEasyDataCheck.setContent(content);
        getFacade().getEtEasyDataCheckService().modifyEtEasyDataCheck(etEasyDataCheck);
        resultMap.put("status", Constants.SUCCESS);
        return resultMap;
    }
}


