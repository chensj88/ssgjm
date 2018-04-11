package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.NumberParseUtil;
import cn.com.winning.ssgj.base.util.SFtpUtils;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
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

    private static final String DOCTOR_MAINTAIN_LIST = "doctorMaintainList";
    private static final String DOCTOR_NOT_MAINTAIN_LIST = "doctorNotMaintainList";
    private static final String DEPT_MAINTAIN_LIST = "deptMaintainList";
    private static final String DEPT_NOT_MAINTAIN_LIST = "deptNotMaintainList";
    @Autowired
    private SSGJHelper ssgjHelper;


    /**
     * 数据初始化
     *
     * @param pmId
     * @param operator
     * @param dataType
     */
    private void dataInit(Long pmId, Long operator, Integer dataType) {
        if (pmId == null) {
            return;
        }
        //根据pmId获取项目基础信息
        PmisProjectBasicInfo pmisProjectBasicInfo = this.getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //cId
        Long cId = pmisProjectBasicInfo.getHtxx();
        //serialNo
        Long serialNo = pmisProjectBasicInfo.getKhxx();
        //根据项目id获取产品
        List<PmisProductInfo> pmisProductInfos =
                getFacade().getCommonQueryService().queryProductOfProjectByProjectIdAndType(pmId, 1);
        //产品id集合
        List pidList = new ArrayList();
        for (int i = 0; i < pmisProductInfos.size(); i++) {
            pidList.add(pmisProductInfos.get(i).getId());
        }
        logger.info("idList:{}", pidList);
        //创建map，封装其他属性
        Map<String, Object> propMap = new HashMap<String, Object>();
        //pks为mapping xml中设定的属性名
        propMap.put("pidList", pidList);

        SysDataInfo sysDataInfo = new SysDataInfo();
        sysDataInfo.setDataType(dataType);
        sysDataInfo.setMap(propMap);
        SysProductDataInfo sysProductDataInfo = new SysProductDataInfo();
        PmisProductInfo pmisProductInfo = new PmisProductInfo();
        //根据产品id和dataType获取基础数据 dataType:0 国标数据;1 行标数据；2 共享数据；3 易用数据；
        List<SysDataInfo> sysDataInfos = getFacade().getSysDataInfoService().selectSysDataInfoListByPidAndDataType(sysDataInfo);
        for (int i = 0; i < sysDataInfos.size(); i++) {
            //根据bdId查找sysProductDataInfo
            sysProductDataInfo.setBdId(sysDataInfos.get(i).getId());
            sysProductDataInfo = getFacade().getSysProductDataInfoService().getSysProductDataInfo(sysProductDataInfo);
            //根据pdid查找ProductInfo
            pmisProductInfo.setId(sysProductDataInfo.getPdId());
            pmisProductInfo = getFacade().getPmisProductInfoService().getPmisProductInfo(pmisProductInfo);

            EtEasyDataCheck etEasyDataCheck = new EtEasyDataCheck();
            etEasyDataCheck.setcId(cId);
            etEasyDataCheck.setPmId(pmId);
            etEasyDataCheck.setSerialNo(serialNo.toString());
            etEasyDataCheck.setDatabaseName(sysDataInfos.get(i).getDbName());
            etEasyDataCheck.setTableName(sysDataInfos.get(i).getTableName());
            etEasyDataCheck.setPlId(NumberParseUtil.parseLong(pmisProductInfo.getCptx()));
            //按条件查询数据是否存在
            EtEasyDataCheck etEasyDataCheckTemp = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(etEasyDataCheck);
            if (etEasyDataCheckTemp == null) {
                //当数据不存在时增加数据
                etEasyDataCheck.setId(ssgjHelper.createEtEasyDataCheckId());
                etEasyDataCheck.setMeaning(sysDataInfos.get(i).getTableCnName());
                etEasyDataCheck.setOperator(operator);
                etEasyDataCheck.setCreator(operator);
                etEasyDataCheck.setCreateTime(new Timestamp(new java.util.Date().getTime()));
                etEasyDataCheck.setOperatorTime(new Timestamp(new Date().getTime()));
                getFacade().getEtEasyDataCheckService().createEtEasyDataCheck(etEasyDataCheck);
            }
        }
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
    @ILog(operationName = "基础数据校验表", operationType = "list")
    public Map<String, Object> list(Row row, EtEasyDataCheck etEasyDataCheck) {
        //项目id
        Long pmId = etEasyDataCheck.getPmId();
        if (pmId == null) {
            return null;
        }
        //初始化数据
        dataInit(pmId, etEasyDataCheck.getOperator(), 3);
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

        for (EtEasyDataCheck easyDataCheck : etEasyDataChecks) {
            //封装属性
            Map propMap = new HashMap();
            String content = easyDataCheck.getContent();
            if (StringUtil.isEmptyOrNull(content) || "正常".equals(content)) {
                propMap.put("state", 0);
            } else {
                propMap.put("state", 1);
            }
            easyDataCheck.setMap(propMap);
        }
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
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file, EtEasyDataCheck t) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //根据id获取表属性
        EtEasyDataCheck etEasyDataCheck = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(t);
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
            Map<String, List<EtEasyDataCheckDetail>> map = null;
            //解析Excel文件
            try {
                map = parseExcel(newFile.getPath(), etEasyDataCheck.getId());

                if (map == null) {
                    result.put("status", "error");
                    result.put("msg", "文件解析出错！");
                    return result;
                }

                //获取解析集合
                List<EtEasyDataCheckDetail> doctorMaintainList = map.get(DOCTOR_MAINTAIN_LIST);
                List<EtEasyDataCheckDetail> doctorNotMaintainList = map.get(DOCTOR_NOT_MAINTAIN_LIST);
                List<EtEasyDataCheckDetail> deptMaintainList = map.get(DEPT_MAINTAIN_LIST);
                List<EtEasyDataCheckDetail> deptNotMaintainList = map.get(DEPT_NOT_MAINTAIN_LIST);

                //将未维护数据入库EtEasyDataCheckDetail
                getFacade().getEtEasyDataCheckDetailService().insertEtEasyDataCheckDetailByList(doctorNotMaintainList);
                getFacade().getEtEasyDataCheckDetailService().insertEtEasyDataCheckDetailByList(deptNotMaintainList);

                String content = "";
                //当无未维护数据时，校验正常
                if (doctorNotMaintainList.size() == 0 && deptNotMaintainList.size() == 0) {
                    content = "校验正常";

                } else {
                    //计算医生维护率
                    Integer num1 = doctorMaintainList.size();
                    Integer num2 = doctorNotMaintainList.size() + num1;
                    String docStr = NumberParseUtil.parsePercent(num1, num2);
                    //计算科室维护率
                    Integer num3 = deptMaintainList.size();
                    Integer num4 = deptNotMaintainList.size() + num3;
                    String deptStr = NumberParseUtil.parsePercent(num3, num4);
                    content = docStr + "的医生维护；" + deptStr + "的科室维护。";
                }
                //更新易用校验数据content
                etEasyDataCheck.setContent(content);
                //文件夹路径
                String dir = "/easyCheck/" + pmisProductLineInfo.getName() + "/";
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
    private Map<String, List<EtEasyDataCheckDetail>> parseExcel(String filePath, Long sourceId) throws Exception {
        Map<String, List<EtEasyDataCheckDetail>> map = new HashMap<>();
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
        List<EtEasyDataCheckDetail> doctorMaintainList = new ArrayList<>();
        //已经维护的科室协定数据
        List<EtEasyDataCheckDetail> deptMaintainList = new ArrayList<>();
        //未维护的个人协定数据
        List<EtEasyDataCheckDetail> doctorNotMaintainList = new ArrayList<>();
        //未维护的科室协定数据
        List<EtEasyDataCheckDetail> deptNotMaintainList = new ArrayList<>();
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
            if ("已经维护的个人协定方信息统计:".equals(ExcelUtil.getCellValue(row.getCell(0)) + "")) {
                flag = DOCTOR_MAINTAIN_LIST;
                continue;
            } else if ("工号".equals(ExcelUtil.getCellValue(row.getCell(0)) + "")) {
                flag = DOCTOR_NOT_MAINTAIN_LIST;
                continue;
            } else if ("已经维护的科室协定方信息统计:".equals(ExcelUtil.getCellValue(row.getCell(0)) + "")) {
                flag = DEPT_MAINTAIN_LIST;
                continue;
            } else if ("科室代码".equals(ExcelUtil.getCellValue(row.getCell(0)) + "")) {
                flag = DEPT_NOT_MAINTAIN_LIST;
                continue;
            }
            if (DOCTOR_MAINTAIN_LIST.equals(flag)) {
                //已经维护的个人协定数据封装
                EtEasyDataCheckDetail etEasyDataCheckDetail = new EtEasyDataCheckDetail();
                etEasyDataCheckDetail.setSourceId(sourceId);
                etEasyDataCheckDetail.setDeptDoctorName(ExcelUtil.getCellValue(row.getCell(0)).toString());
                etEasyDataCheckDetail.setNum(NumberParseUtil.parseInt(ExcelUtil.getCellValue(row.getCell(1)) == null ? null : ExcelUtil.getCellValue(row.getCell(1)).toString()));
                doctorMaintainList.add(etEasyDataCheckDetail);
            } else if (DOCTOR_NOT_MAINTAIN_LIST.equals(flag)) {
                //未维护的个人协定数据封装
                EtEasyDataCheckDetail etEasyDataCheckDetail = new EtEasyDataCheckDetail();
                etEasyDataCheckDetail.setId(ssgjHelper.createEtEasyDataCheckDetailId());
                etEasyDataCheckDetail.setSourceId(sourceId);
                etEasyDataCheckDetail.setDeptDoctorCode(ExcelUtil.getCellValue(row.getCell(0)) == null ? null : ExcelUtil.getCellValue(row.getCell(0)).toString());
                etEasyDataCheckDetail.setDeptDoctorName(ExcelUtil.getCellValue(row.getCell(1)) == null ? null : ExcelUtil.getCellValue(row.getCell(1)).toString());
                doctorNotMaintainList.add(etEasyDataCheckDetail);

            } else if (DEPT_MAINTAIN_LIST.equals(flag)) {
                //已经维护的科室协定数据封装
                EtEasyDataCheckDetail etEasyDataCheckDetail = new EtEasyDataCheckDetail();
                etEasyDataCheckDetail.setSourceId(sourceId);
                etEasyDataCheckDetail.setDeptDoctorName(ExcelUtil.getCellValue(row.getCell(0)) == null ? null : ExcelUtil.getCellValue(row.getCell(0)).toString());
                etEasyDataCheckDetail.setNum(NumberParseUtil.parseInt(ExcelUtil.getCellValue(row.getCell(1)) == null ? null : ExcelUtil.getCellValue(row.getCell(1)).toString()));
                deptMaintainList.add(etEasyDataCheckDetail);

            } else if (DEPT_NOT_MAINTAIN_LIST.equals(flag)) {
                //未维护的科室协定数据封装
                EtEasyDataCheckDetail etEasyDataCheckDetail = new EtEasyDataCheckDetail();
                etEasyDataCheckDetail.setId(ssgjHelper.createEtEasyDataCheckDetailId());
                etEasyDataCheckDetail.setSourceId(sourceId);
                etEasyDataCheckDetail.setDeptDoctorCode(ExcelUtil.getCellValue(row.getCell(0)) == null ? null : ExcelUtil.getCellValue(row.getCell(0)).toString());
                etEasyDataCheckDetail.setDeptDoctorName(ExcelUtil.getCellValue(row.getCell(1)) == null ? null : ExcelUtil.getCellValue(row.getCell(1)).toString());
                deptNotMaintainList.add(etEasyDataCheckDetail);
            }
        }
        map.put(DOCTOR_MAINTAIN_LIST, doctorMaintainList);
        map.put(DOCTOR_NOT_MAINTAIN_LIST, doctorNotMaintainList);
        map.put(DEPT_MAINTAIN_LIST, deptMaintainList);
        map.put(DEPT_NOT_MAINTAIN_LIST, deptNotMaintainList);
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
    public void exportSql(HttpServletResponse response, EtEasyDataCheck t) throws IOException {
        //获取数据校验信息
        EtEasyDataCheck etEasyDataCheck = getFacade().getEtEasyDataCheckService().getEtEasyDataCheck(t);
        SysDataCheckScript temp = new SysDataCheckScript();
        Long plId = etEasyDataCheck.getPlId();
        if (plId == null) {
            return;
        }
        temp.setAppId(etEasyDataCheck.getPlId());
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


    /**
     * 确认完成
     *
     * @param proStr
     * @return
     */
    @RequestMapping(value = "/confirm.do")
    @ResponseBody
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

        EtEasyDataCheck etEasyDataCheck = new EtEasyDataCheck();

        etEasyDataCheck.setPmId(proId);

        etEasyDataCheck.setcId(contractId);

        etEasyDataCheck.setSerialNo(customerId.toString());
        int total = getFacade().getEtEasyDataCheckService().getEtEasyDataCheckCount(etEasyDataCheck);

        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(proId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (total > 0) {
            etProcessManager.setIsEasyDataCheck(1);
            getFacade().getEtProcessManagerService().updateEtProcessManagerByPmId(etProcessManager);
            map.put("type", Constants.SUCCESS);
            map.put("msg", "确认成功！");
        } else {
            map.put("type", "info");
            map.put("msg", "无数据，确认失败！");
        }
        return map;
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
        //null校验
        if (etEasyDataCheck == null) {
            return;
        }
        //更新维护状态
        getFacade().getEtEasyDataCheckService().modifyEtEasyDataCheck(t);

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
        List<File> fileList = new ArrayList();
        //获取数据校验信息
        List<EtEasyDataCheck> etEasyDataChecks = getFacade().getEtEasyDataCheckService().getEtEasyDataCheckList(easyDataCheck);
        for (int i = 0; i < etEasyDataChecks.size(); i++) {
            EtEasyDataCheck etEasyDataCheck = etEasyDataChecks.get(i);
            //获取
            SysDataCheckScript temp = new SysDataCheckScript();
            Long plId = etEasyDataCheck.getPlId();
            if (plId == null) {
                return;
            }
            temp.setAppId(etEasyDataCheck.getPlId());
            SysDataCheckScript sysDataCheckScript = getFacade().getSysDataCheckScriptService().getSysDataCheckScript(temp);
            //获取脚本地址
            String scriptPath = sysDataCheckScript.getRemotePath();
            //获取文件名
            String filename = scriptPath.substring(scriptPath.lastIndexOf("/") + 1);
            String saveFile = "/sql/" + filename.substring(0, filename.indexOf(".")) + "_" + i + "." + filename.substring(filename.indexOf(".") + 1);
            ChannelSftp sftpConnect = null;
            File file = null;
            try {
                sftpConnect = SFtpUtils.getSftpConnect();
                //sftpConnect.setFilenameEncoding("GBK");
                file = SFtpUtils.download(scriptPath, saveFile, sftpConnect);
                fileList.add(file);
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
        for (File currentFile : fileList) {
            fileName = currentFile.getName();
            fileInputStream = new FileInputStream(currentFile);
            byte[] buf = new byte[fileInputStream.available()];
            //放入压缩zip包中;
            zipOutputStream.putNextEntry(new ZipEntry(fileName));

            //读取文件;
            while ((len = fileInputStream.read(buf)) > 0) {
                zipOutputStream.write(buf, 0, len);
            }
            //关闭;
            zipOutputStream.closeEntry();
            if (fileInputStream != null) {
                fileInputStream.close();

            }
            currentFile.delete();
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
    @ILog
    @Transactional
    public Map<String, Object> changeScope(EtEasyDataCheck etEasyDataCheck) {
        String noScopeCode = etEasyDataCheck.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etEasyDataCheck.setIsScope(1);
        } else {
            etEasyDataCheck.setIsScope(0);
        }
        Map map = new HashMap();
        getFacade().getEtEasyDataCheckService().modifyEtEasyDataCheck(etEasyDataCheck);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "范围修改成功！");
        return map;
    }
}


