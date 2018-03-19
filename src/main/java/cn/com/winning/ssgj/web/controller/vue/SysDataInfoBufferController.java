package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
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
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
@RequestMapping("/vue/basicData")
public class SysDataInfoBufferController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysDataInfoBufferController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/dataInfo.do")
    @ILog
    public String userinfo(HttpServletRequest request, Model model) {
        return "auth/module/sysDataInfo";
    }

    /**
     * 基础数据类型列表
     *
     * @param row
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    @ILog(operationName = "基础数据类型列表", operationType = "list")
    public Map<String, Object> list(Row row) {
        SysDataInfo sysDataInfo = new SysDataInfo();
        sysDataInfo.setRow(row);
        List<SysDataInfo> sysDataInfos = getFacade().getSysDataInfoService().getSysDataInfoPaginatedListForSelectiveKey(sysDataInfo);
        int total = getFacade().getSysDataInfoService().getSysDataInfoCountForSelectiveKey(sysDataInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", sysDataInfos);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        return map;
    }


    /**
     * 通过产品ID查询基础数据类型
     *
     * @param t
     * @return
     */
    @RequestMapping("/detail.do")
    @ResponseBody
    @ILog
    public Map<String, Object> detail(SysDataInfo t) {
        SysDataInfo sysDataInfo = getFacade().getSysDataInfoService().getSysDataInfo(t);
        List<SysDataInfo> details = new ArrayList<SysDataInfo>();
        details.add(sysDataInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", details);
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
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @description 文件导出
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public HttpServletResponse wiriteExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "datainfo.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String("DataInfo.xls".getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return response;
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
        //获取数据信息
        SysDataInfo sysDataInfo = getFacade().getSysDataInfoService().getSysDataInfo(t);
        logger.info("data_id:" + sysDataInfo.getId());
        Class clazz = sysDataInfo.getClass();
        //获取属性集合
        Field[] fields = clazz.getDeclaredFields();
        //获取属性名集合
        List<String> attribute = new ArrayList<String>();
        for (int i = 0; i < fields.length; i++) {
            attribute.add(fields[i].getName());
        }
        //去掉无用bean属性
        attribute.remove("isSerialVersionUID");
        attribute.remove("serialVersionUID");
        attribute.remove("nodeTree");
        for (int i = 0; i < attribute.size(); i++) {
            System.out.println(attribute.get(i));
        }

        //创建Excel工作簿 Excel 2003
        Workbook wb = new HSSFWorkbook();
        try {//获取第一页
            HSSFSheet sheet = (HSSFSheet) wb.createSheet();
            //属性行
            HSSFRow row_1 = sheet.createRow(0);
            for (int i = 0; i < attribute.size(); i++) {
                row_1.createCell(i).setCellValue(attribute.get(i));
            }
            //数据行
            HSSFRow row_2 = sheet.createRow(1);
            for (int i = 0; i < attribute.size(); i++) {
                PropertyDescriptor pd = new PropertyDescriptor(attribute.get(i),
                        clazz);
                Method readMethod = pd.getReadMethod();//获得get方法
                Object o = readMethod.invoke(sysDataInfo);//执行get方法返回一个Object
                row_2.createCell(i).setCellValue(o == null ? "" : o.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String filename = sysDataInfo.getTableName() + ".xls";
        // 清空response
        //response.reset();
        // 设置response的Header
        //response.setHeader("Access-Control-Allow-Origin", "*")
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
    public void exportSql(HttpServletResponse response, SysDataInfo t) throws IOException {
        //获取数据信息
        SysDataInfo sysDataInfo = getFacade().getSysDataInfoService().getSysDataInfo(t);
        logger.info("data_id:" + sysDataInfo.getId());
        //导出文件名
        String filename = sysDataInfo.getTableName() + ".sql";
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM SYS_DATA_INFO WHERE ID = '").append(t.getId()
        ).append("'");
        //sql语句
        String sql = sqlBuilder.toString();
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
            buff.write(sql.getBytes("UTF-8"));
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

    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addOrModify(SysDataInfo sysDataInfo) {
        if (sysDataInfo.getId() == 0L || sysDataInfo == null) {
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
}


