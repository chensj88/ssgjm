package cn.com.winning.ssgj.base.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.ResolverStyle;
import java.util.*;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-03-13 13:52
 */
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args) {
      /* List<Map> param = new ArrayList<Map>();
        for (int i=0;i<50;i++){
           Map m = new HashMap();
           m.put("BankName","test00"+i);
            m.put("Addr","test01"+i);
            m.put("Phone","test02"+i);
            param.add(m);
       }
       List<String> colList = new ArrayList<String>();
        colList.add("BankName");
        colList.add("Addr");
        colList.add("Phone");
       writeExcel(param,colList,3,"d:/a.xlsx");*/
        List<List<Object>> aaa = new ArrayList<>();
        try {
            aaa = importExcel("D:/download/userinfo.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (List<Object> objects : aaa) {
            StringBuilder sb = new StringBuilder();
            for (Object o : objects) {
                sb.append(o + ",");
            }
            System.out.println(sb);
        }
    }

    /**
     * 导出Excel
     *
     * @param dataList      数据Map
     * @param colList       Map中列名称
     * @param cloumnCount   列数
     * @param finalXlsxPath Excel放置位置
     */
    public static void writeExcel(List<Map> dataList, List<String> colList, int cloumnCount, String finalXlsxPath) {
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbook(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();  // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    sheet.removeRow(row);
                }

            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                for (int k = 0; k < columnNumCount; k++) {
                    // 在一行内循环
                    Cell cell = row.createCell(k);
                    String value = "";
                    if (dataMap.get(colList.get(k)) != null) {
                        value = dataMap.get(colList.get(k)).toString();
                    }

                    cell.setCellValue(value);

                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    public static List<List<Object>> importExcel(String fileName, int startSheet) throws Exception {
        List<List<Object>> list = null;

        //创建Excel工作薄
        File finalXlsxFile = new File(fileName);
        Workbook work = getWorkbook(finalXlsxFile);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = startSheet; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            list.addAll(resolveSheetData(sheet));
        }
        return list;
    }

    public static List<List<Object>> importExcel(String fileName) throws Exception {
        List<List<Object>> list = null;

        //创建Excel工作薄
        File finalXlsxFile = new File(fileName);
        Workbook work = getWorkbook(finalXlsxFile);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        list = new ArrayList<List<Object>>();
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            list.addAll(resolveSheetData(sheet));
        }
        return list;
    }

    private static List<List<Object>> resolveSheetData(Sheet sheet) {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Row row = null;
        Cell cell = null;
        //遍历当前sheet中的所有行
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            row = sheet.getRow(j);
            if (row == null || row.getFirstCellNum() == j) {
                continue;
            }

            //遍历所有的列
            List<Object> li = new ArrayList<Object>();
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                cell = row.getCell(y);
                li.add(getCellValue(cell));
            }
            list.add(li);
        }
        return list;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                short format = cell.getCellStyle().getDataFormat();
                if (format == 14 || format == 31 || format == 57 || format == 58
                        || (176<=format && format<=178) || (182<=format && format<=196)
                        || (210<=format && format<=213) || (208==format ) ) { // 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value =  sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue()));
                }else  if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                }else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 导出Excel
     *
     * @param dataList 数据Map
     * @param colList  Map中列名称：列名
     * @param response HTTP 响应
     * @param workBook Excel工作簿
     * @param filename Excel导出文件名
     */
    public static void exportExcelByStream(List<Map> dataList, List<String> colList, List<String> tableNameList, HttpServletResponse response, Workbook workBook, String filename) {
        try {
            //样式
            CellStyle cellStyle = workBook.createCellStyle();
            Font font=workBook.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            cellStyle.setFont(font);
            // sheet 对应一个工作页
            Sheet sheet = workBook.createSheet();
            //第一行保存列名
            Row colRow = sheet.createRow(0);
            if (tableNameList == null) {
                for (int i = 0; i < colList.size(); i++) {
                    Cell cell=colRow.createCell(i);
                    cell.setCellValue(colList.get(i).toString());
                    cell.setCellStyle(cellStyle);
                    sheet.setColumnWidth(i, 20 * 256);
                }
            } else {
                for (int i = 0; i < tableNameList.size(); i++) {
                    Cell cell=colRow.createCell(i);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(tableNameList.get(i).toString());
                    sheet.setColumnWidth(i, 20 * 256);
                }
            }

            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                for (int k = 0; k < colList.size(); k++) {
                    // 在一行内循环
                    Cell cell = row.createCell(k);
                    String value = "";
                    if (dataMap.get(colList.get(k)) != null) {
                        value = dataMap.get(colList.get(k)).toString();
                    }
                    cell.setCellValue(value);
                }
            }
            //获取响应输出流
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            // 设置response的Header
            response.setContentType("application/msexcel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workBook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("数据导出成功");
    }
}
