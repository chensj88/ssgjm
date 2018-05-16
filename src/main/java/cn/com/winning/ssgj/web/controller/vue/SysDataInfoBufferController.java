package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSONArray;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
@RequestMapping("/vue/basicData")
public class SysDataInfoBufferController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysDataInfoBufferController.class);
    @Autowired
    private SSGJHelper ssgjHelper;


    /**
     * 基础数据类型列表
     *
     * @param row
     * @param pmId     项目id
     * @param dataType 0 国标数据;1 行标数据；2 共享数据；3 易用数据；
     * @return
     * @description 根据项目id获取基础数据
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> list(Row row, Long pmId, Integer dataType) {
        //项目id
        if (pmId == null) {
            return null;
        }
//        //创建map，封装其他属性
//        Map<String, Object> propMap = new HashMap<String, Object>();
//        //pks为mapping xml中设定的属性名
//        propMap.put("pmId", pmId);

        SysDataInfo sysDataInfo = new SysDataInfo();
        sysDataInfo.setDataType(dataType);
        sysDataInfo.setRow(row);
//        sysDataInfo.setMap(propMap);
        //根据产品id和dataType获取基础数据 dataType:0 国标数据;1 行标数据；2 共享数据；3 易用数据；
        List<SysDataInfo> sysDataInfos = getFacade().getSysDataInfoService().selectSysDataInfoPaginatedListByDataType(sysDataInfo);
        int total = getFacade().getSysDataInfoService().countSysDataInfoListByDataType(sysDataInfo);
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", sysDataInfos);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
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
    @ILog
    public Map<String, Object> detail(SysDataInfo t) {
        //根据id获取表属性
        SysDataInfo sysDataInfo = getFacade().getSysDataInfoService().getSysDataInfo(t);
        //表名
        String tableName = sysDataInfo.getTableName();
        logger.info("tableName:{}", tableName);
        if (StringUtil.isEmptyOrNull(tableName)) {
            logger.warn("tableName is null or empty!");
            return null;
        }
        //数据库
        String dbName ="data";
        String checkTableName = null;
        String dataBase = null;
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");
        if (StringUtil.isEmptyOrNull(dbName)) {
            //当存在库名时查询本库
            sqlBuilder.append(tableName);
            checkTableName = tableName;
            dataBase = "sysobjects";
        } else {
            sqlBuilder.append(dbName).append(".dbo.").append(tableName);
            checkTableName = dbName + ".dbo." + tableName;
            dataBase = dbName + ".dbo." + "sysobjects";
        }
        Map propMap = new HashMap();
        propMap.put("dataBase", dataBase);
        propMap.put("tableName", checkTableName);
        Integer num = getFacade().getCommonQueryService().countTable(propMap);
        if (num == null || num == 0) {
            logger.warn("table is not exist!");
            return null;
        }
        String sql = sqlBuilder.toString();
        logger.info("sql:{}", sql);
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        JSONArray jsonArray = null;
        //列名集合
        List cols = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            jsonArray = ResultSetUtil.resultSetToJSONArray(resultSet);
            //获取列名
            ResultSetMetaData metaData = resultSet.getMetaData();
            //表列数
            int colNum = metaData.getColumnCount();
            for (int i = 1; i <= colNum; i++) {
                cols.add(metaData.getColumnName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", jsonArray);
        map.put("status", Constants.SUCCESS);
        map.put("cols", cols);
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
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/temp/");
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

            try {
                List<List<Object>> sysDataInfoList = ExcelUtil.importExcel(newFile.getPath());
                super.getFacade().getSysDataInfoService().createSysDataInfoByList(sysDataInfoList);
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
     * 文件导出
     *
     * @param response
     * @param pmId
     * @param dataType
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public void wiriteExcel(HttpServletResponse response, Long pmId, Integer dataType) {
        if (pmId == null) {
            return;
        }
//        //创建map，封装其他属性
//        Map<String, Object> propMap = new HashMap<String, Object>();
//        //pks为mapping xml中设定的属性名
//        propMap.put("pmId", pmId);
        SysDataInfo sysDataInfo = new SysDataInfo();
        sysDataInfo.setDataType(dataType);
        sysDataInfo.setStatus(1);
//        sysDataInfo.setMap(propMap);
        //根据产品id和dataType获取基础数据 dataType:0 国标数据;1 行标数据；2 共享数据；3 易用数据；
        List<SysDataInfo> sysDataInfoList = getFacade().getSysDataInfoService().getSysDataInfoList(sysDataInfo);
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < sysDataInfoList.size(); i++) {
            dataList.add(ConnectionUtil.objectToMap(sysDataInfoList.get(i)));
        }
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        attrNameList.add("tableName");
        attrNameList.add("tableCnName");
        attrNameList.add("standardCode");
        attrNameList.add("tableAttention");
        //表名集合
        List<String> tableNameList = new ArrayList<>();
        tableNameList.add("This表名");
        tableNameList.add("数据涵义");
        tableNameList.add("标准文号");
        tableNameList.add("注意事项");
        String filename = null;
        if (dataType == 0) {
            filename = "国标数据";
        } else if (dataType == 1) {
            filename = "行标数据";
        } else if (dataType == 2) {
            filename = "共享数据";
        } else if (dataType == 3) {
            filename = "易用数据";
        }
        filename += DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList, tableNameList, response, workbook, filename);
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
    public Map<String, Object> exportDataInfo(HttpServletResponse response, SysDataInfo t) throws IOException {
        //根据id获取表属性
        SysDataInfo sysDataInfo = getFacade().getSysDataInfoService().getSysDataInfo(t);
        //表名
        String tableName = sysDataInfo.getTableName();
        logger.info("tableName:{}", tableName);
        Map map = new HashMap();
        if (StringUtil.isEmptyOrNull(tableName)) {
            logger.warn("tableName is null or empty!");
            map.put("error", "tableName is null or empty!");
            return map;
        }
        //数据库
        String dbName = "data";
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");
        String checkTableName = null;
        String dataBase = null;
        if (StringUtil.isEmptyOrNull(dbName)) {
            //当存在库名时查询本库
            sqlBuilder.append(tableName);
            checkTableName = tableName;
            dataBase = "sysobjects";
        } else {
            sqlBuilder.append(dbName).append(".dbo.").append(tableName);
            checkTableName = dbName + ".dbo." + tableName;
            dataBase = dbName + ".dbo." + "sysobjects";
        }
        Map propMap = new HashMap();
        propMap.put("dataBase", dataBase);
        propMap.put("tableName", checkTableName);
        Integer num = getFacade().getCommonQueryService().countTable(propMap);
        if (num == null || num == 0) {
            logger.warn("table is not exist!");
            map.put("error", "table is not exist!");
            return map;
        }
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
        map.put("status", Constants.SUCCESS);
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
    @ILog
    public Map<String, Object> exportSql(HttpServletResponse response, SysDataInfo t) throws IOException {
        //获取数据信息
        SysDataInfo sysDataInfo = getFacade().getSysDataInfoService().getSysDataInfo(t);
        String dbName = "data";
        String curDbName=sysDataInfo.getDbName();
        String tableName = sysDataInfo.getTableName();
        Map map = new HashMap();
        if (StringUtil.isEmptyOrNull(tableName)) {
            logger.warn("tableName is null or empty!");
            map.put("error", "tableName is null or empty!");
            return map;
        }
        //导出文件名
        String filename = sysDataInfo.getTableName() + ".sql";
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");
        String checkTableName = null;
        String dataBase = null;
        if (StringUtil.isEmptyOrNull(dbName)) {
            //当存在库名时查询本库
            sqlBuilder.append(tableName);
            checkTableName = tableName;
            dataBase = "sysobjects";
        } else {
            sqlBuilder.append(dbName).append(".dbo.").append(tableName);
            checkTableName = dbName + ".dbo." + tableName;
            dataBase = dbName + ".dbo." + "sysobjects";
        }
        Map propMap = new HashMap();
        propMap.put("dataBase", dataBase);
        propMap.put("tableName", checkTableName);
        Integer num = getFacade().getCommonQueryService().countTable(propMap);
        if (num == null || num == 0) {
            logger.warn("table is not exist!");
            map.put("error", "table is not exist!");
            return map;
        }
        String sql = sqlBuilder.toString();
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlStr = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            sqlStr = ResultSetUtil.resultSetToSql(resultSet, curDbName, tableName);

        } catch (SQLException e) {
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
            buff.write(sqlStr.getBytes("UTF-8"));
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
     * 新增基础数据
     *
     * @param sysDataInfo
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addOrModify(SysDataInfo sysDataInfo) {
        if (sysDataInfo.getId() == 0L || sysDataInfo.getId() == null) {
            //不存在id或id为初始值，则为新增数据
            sysDataInfo.setId(ssgjHelper.createDataId());
            super.getFacade().getSysDataInfoService().createSysDataInfo(sysDataInfo);
        } else {
            //存在id，则为更新数据
            super.getFacade().getSysDataInfoService().modifySysDataInfo(sysDataInfo);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    /**
     * 确认完成
     *
     * @param pmId
     * @param dataType
     * @return
     */
    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @ILog
    public Map<String, Object> confirm(Long pmId, Integer dataType) {
        //项目id
        if (pmId == null) {
            return null;
        }
        //创建map，封装其他属性
        Map<String, Object> propMap = new HashMap<String, Object>();
        //pks为mapping xml中设定的属性名
        propMap.put("pmId", pmId);
        SysDataInfo sysDataInfo = new SysDataInfo();
        sysDataInfo.setDataType(dataType);
        sysDataInfo.setMap(propMap);
        int total = getFacade().getSysDataInfoService().countSysDataInfoListByPmIdAndDataType(sysDataInfo);
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (total > 0) {
            if (dataType == 0 || dataType == 1 || dataType == 2) {
                etProcessManager.setIsBasicDataUse(1);
            } else if (dataType == 3) {
                etProcessManager.setIsEasyDataUse(1);

            }
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


