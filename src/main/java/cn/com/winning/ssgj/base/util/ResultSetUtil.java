package cn.com.winning.ssgj.base.util;

import cn.com.winning.ssgj.web.controller.vue.SysDataInfoBufferController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * jdbc resultSet 工具类
 */

public class ResultSetUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ResultSetUtil.class);


    /**
     * @param resultSet
     * @return
     * @description resultSet转换成JSONArray
     */
    public static JSONArray resultSetToJSONArray(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();
        if (resultSet == null) {
            return jsonArray;
        }
        try {//获取第一页
            ResultSetMetaData metaData = resultSet.getMetaData();
            //表列数
            int colNum = metaData.getColumnCount();
            while (resultSet.next()) {
                JSONObject jsonObj = new JSONObject();
                for (int i = 1; i <= colNum; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                jsonArray.add(jsonObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * @param resultSet
     * @param wb
     * @return
     * @description resultSet写入Excel
     */
    public static boolean resultSetToExcel(ResultSet resultSet, Workbook wb) {
        if (resultSet == null || wb == null) {
            return false;
        }
        try {//获取第一页

            ResultSetMetaData metaData = null;
            metaData = resultSet.getMetaData();
            //表列数
            int colNum = metaData.getColumnCount();
            HSSFSheet sheet = (HSSFSheet) wb.createSheet();
            //表格行号
            int rowNum = 0;
            //属性行
            HSSFRow row_1 = sheet.createRow(rowNum);

            //将属性值赋值第一行
            for (int i = 1; i <= colNum; i++) {
                String columnName = metaData.getColumnLabel(i);
                row_1.createCell(i).setCellValue(columnName);
            }
            //数据行
            HSSFRow row_2 = sheet.createRow(1);
            while (resultSet.next()) {
                //循环创建row
                HSSFRow row = sheet.createRow(++rowNum);
                for (int i = 1; i <= colNum; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(columnName);
                    row.createCell(i).setCellValue(value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * @param resultSet
     * @return
     * @description resultSet转换成String sql(sqlServer)
     */
    public static String resultSetToSql(ResultSet resultSet, String dbName, String tableName) {
        if (resultSet == null) {
            return "";
        }
        //存储列名和列类型
        Map<String, String> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder("USE ").append(dbName).append(";").append("INSERT INTO ").append(dbName).append(".dbo.").append(tableName);
        stringBuilder.append(" (");

        try {//获取第一页
            ResultSetMetaData metaData = resultSet.getMetaData();
            //表列数
            int colNum = metaData.getColumnCount();
            //列名 同时将列名和参数类型封装到map中
            for (int i = 1; i <= colNum; i++) {
                String columnName = metaData.getColumnLabel(i);
                String colType = metaData.getColumnTypeName(i);
                logger.info("columnName:{}，colType:{}", columnName, colType);
                map.put(columnName, colType);
                stringBuilder.append(columnName);
                if (i != colNum) {
                    stringBuilder.append(",");
                }
            }

            stringBuilder.append(") VALUES ");
            //data 字符串集合
            List<String> stringList = new ArrayList<>();
            while (resultSet.next()) {
                StringBuilder dataStr = new StringBuilder();
                dataStr.append("(");
                for (int i = 1; i <= colNum; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(columnName);
                    //获取colType
                    String colType = map.get(columnName);
                    if(value==null){
                        System.out.println(columnName);
                    }
                    if (value==null) {
                        dataStr.append("null");
                    } else if(colType.toLowerCase().contains("int")||"null".equals(value.toLowerCase())){
                        dataStr.append(value);
                    }else{
                        dataStr.append("'").append(value).append("'");
                    }
                    if (i != colNum) {
                        //一行中最后一个数据后不用加逗号
                        dataStr.append(",");
                    }
                }
                dataStr.append(")");
                stringList.add(dataStr.toString());
            }
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i != (stringList.size() - 1)) {
                    stringBuilder.append(",");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String tableName = "SYS_DATA_INFO";
        String dbName = "dbo";
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ").append(dbName).append(".").append(tableName);
        String sql = sqlBuilder.toString();
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlStr = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            sqlStr = ResultSetUtil.resultSetToSql(resultSet, dbName, tableName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(sqlStr);
    }


}
