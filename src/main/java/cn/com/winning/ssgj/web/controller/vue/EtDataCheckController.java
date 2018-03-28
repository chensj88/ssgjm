package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * 基础数据类型列表
     *
     * @param row
     * @param proStr 项目id
     * @return
     * @description 根据项目id获取基础数据
     */
    @RequestMapping("/list.do")
    @ResponseBody
    @ILog(operationName = "基础数据校验表", operationType = "list")
    public Map<String, Object> list(Row row, String proStr) {
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

        etDataCheck.setRow(row);

        etDataCheck.setPmId(proId);

        etDataCheck.setCId(contractId);

        etDataCheck.setSerialNo(customerId.toString());
        //获取基础数据校验
        List<EtDataCheck> pmisProductInfos =
                getFacade().getEtDataCheckService().getEtDataCheckPaginatedList(etDataCheck);
        PmisProductInfo pmisProductInfo=new PmisProductInfo();
        SysDataCheckScript sysDataCheckScript=new SysDataCheckScript();
        //封装外参数
        for (EtDataCheck e : pmisProductInfos) {
            pmisProductInfo.setId(e.getPdId());
            sysDataCheckScript.setAppId(e.getPlId());
            pmisProductInfo=getFacade().getPmisProductInfoService().getPmisProductInfo(pmisProductInfo);
            sysDataCheckScript=getFacade().getSysDataCheckScriptService().getSysDataCheckScript(sysDataCheckScript);
            Map<String,Object> map=new HashMap();
            map.put("subSystem",pmisProductInfo.getName());
            map.put("type",sysDataCheckScript.getAppName());
            String checkResult = e.getCheckResult();
            if(checkResult==null||"没问题".equals(checkResult)||"".equals(checkResult)){
                map.put("state",0);
            }else {
                map.put("state",1);
            }
            e.setMap(map);
        }
        int total = getFacade().getEtDataCheckService().getEtDataCheckCount(etDataCheck);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", pmisProductInfos);
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
        JSONArray jsonArray = JSONArray.parseArray(content);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", jsonArray);
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
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file, EtDataCheck t) throws IOException {
        //根据id获取表属性
        EtDataCheck etDataCheck = getFacade().getEtDataCheckService().getEtDataCheck(t);
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
            try {
                List<List<Object>> etDataCheckList = ExcelUtil.importExcel(newFile.getPath());
                for (List<Object> e : etDataCheckList) {
                    jsonArray.add(e);
                }
                System.out.println(jsonArray.toJSONString());
                etDataCheck.setContent(jsonArray.toJSONString());
                getFacade().getEtDataCheckService().modifyEtDataCheck(etDataCheck);
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
     * @description Excel文件导出
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public void wiriteExcel(HttpServletResponse response, String proStr, String dataType) {
        Long proId = Long.parseLong(proStr);
        if (proId == null) {
            return;
        }
        //根据项目id获取产品
        List<PmisProductInfo> pmisProductInfos =
                getFacade().getCommonQueryService().queryProductOfProjectByProjectIdAndType(proId, 1);
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
        sysDataInfo.setDataType(Integer.parseInt(dataType));
        sysDataInfo.setMap(propMap);
        //根据产品id和dataType获取基础数据 dataType:0 国标数据;1 行标数据；2 共享数据；3 易用数据；
        List<SysDataInfo> sysDataInfoList = getFacade().getSysDataInfoService().selectSysDataInfoListByPidAndDataType(sysDataInfo);
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < sysDataInfoList.size(); i++) {
            dataList.add(ConnectionUtil.objectToMap(sysDataInfoList.get(i)));
        }
        //属性数组
        Field[] fields = SysDataInfo.class.getDeclaredFields();
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            attrNameList.add(fields[i].getName());
        }
        String filename = "DataInfo" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList, response, workbook, filename);
    }

    /**
     * @param response
     * @return
     * @throws IOException
     * @description 导出datainfo
     */
    @RequestMapping(value = "/exportDataInfo.do")
    @ResponseBody
    @ILog
    public void exportDataInfo(HttpServletResponse response, SysDataInfo t) throws IOException {
        //根据id获取表属性
        SysDataInfo sysDataInfo = getFacade().getSysDataInfoService().getSysDataInfo(t);
        //表名
        String tableName = sysDataInfo.getTableName();
        logger.info("tableName:{}", tableName);
        //数据库
        String dbName = sysDataInfo.getDbName();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ").append(dbName).append(".dbo.").append(tableName);
        String sql = sqlBuilder.toString();
        logger.info("sql:{}", sql);
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        JSONArray jsonArray = null;
        //创建Excel工作簿 Excel 2003
        Workbook wb = new HSSFWorkbook();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            //将表数据写入表格
            ResultSetUtil.resultSetToExcel(resultSet, wb);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        String filename = tableName + ".xls";
        response.setContentType("application/msexcel;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
        OutputStream toClient = response.getOutputStream();
        wb.write(toClient);
        toClient.flush();
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
        //获取脚本地址
        String scriptPath = etDataCheck.getScriptPath();
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
}


