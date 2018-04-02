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
import com.jcraft.jsch.ChannelSftp;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * 基础数据类型处理Controller
 *
 * @author FengChen
 * @date 2018年3月18日10:37:18
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/devEnvHardware")
public class EtDevEnvHardwareController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EtDevEnvHardwareController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 根据项目id获取测试及硬件信息
     *
     * @param row
     * @param pmId
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> list(Row row, Long pmId) {
        if (pmId == null || pmId == 0) {
            return null;
        }
        //根据项目id获取项目基本信息
        PmisProjectBasicInfo pmisProjectBasicInfo = getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //获取合同id
        Long contractId = pmisProjectBasicInfo.getHtxx();
        //获取单据号即客户
        Long customerId = pmisProjectBasicInfo.getKhxx();

        EtDevEnvHardware etDevEnvHardware = new EtDevEnvHardware();

        etDevEnvHardware.setRow(row);

        etDevEnvHardware.setPmId(pmId);

        etDevEnvHardware.setcId(contractId);

        etDevEnvHardware.setSerialNo(customerId.toString());
        //获取基础数据校验
        List<EtDevEnvHardware> etDevEnvHardwarePaginatedList =
                getFacade().getEtDevEnvHardwareService().getEtDevEnvHardwarePaginatedList(etDevEnvHardware);
        int total = getFacade().getEtDevEnvHardwareService().getEtDevEnvHardwareCount(etDevEnvHardware);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", etDevEnvHardwarePaginatedList);
        map.put("total", total);
        map.put("status", Constants.SUCCESS);
        return map;
    }


    /**
     * 添加或者修改测试环境硬件信息
     *
     * @param etDevEnvHardware
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModify(EtDevEnvHardware etDevEnvHardware) {
        //创建临时变量
        EtDevEnvHardware etDevEnvHardwareTemp = new EtDevEnvHardware();
        etDevEnvHardwareTemp.setId(etDevEnvHardware.getId());
        etDevEnvHardwareTemp = super.getFacade().getEtDevEnvHardwareService().getEtDevEnvHardware(etDevEnvHardwareTemp);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etDevEnvHardware.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        etDevEnvHardware.setSerialNo(basicInfo.getKhxx() + "");
        etDevEnvHardware.setcId(basicInfo.getHtxx());
        if (etDevEnvHardwareTemp != null) {
            etDevEnvHardware.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtDevEnvHardwareService().modifyEtDevEnvHardware(etDevEnvHardware);
        } else {
            etDevEnvHardware.setId(ssgjHelper.createEtDevEnvHardwareId());
            etDevEnvHardware.setCreator(etDevEnvHardware.getOperator());
            etDevEnvHardware.setCreateTime(new Timestamp(new Date().getTime()));
            etDevEnvHardware.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtDevEnvHardwareService().createEtDevEnvHardware(etDevEnvHardware);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 删除测试环境硬件信息
     *
     * @param etDevEnvHardware
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> delete(EtDevEnvHardware etDevEnvHardware) {
        super.getFacade().getEtDevEnvHardwareService().removeEtDevEnvHardware(etDevEnvHardware);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出Excel
     * @param response
     * @param etDevEnvHardware
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public void wiriteExcel(HttpServletResponse response, EtDevEnvHardware etDevEnvHardware) throws IOException {
        //根据pmid获取所有接口数据
        List<EtDevEnvHardware> etDevEnvHardwares = getFacade().getEtDevEnvHardwareService().selectEtDevEnvHardwareMergeList(etDevEnvHardware);
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < etDevEnvHardwares.size(); i++) {
            dataList.add(ConnectionUtil.objectToMap(etDevEnvHardwares.get(i)));
        }
        //属性数组
        Field[] fields = EtDevEnvHardware.class.getDeclaredFields();
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            if(!"serialVersionUID".equals(fields[i].getName())){
                attrNameList.add(fields[i].getName());
            }
        }
        String filename = "EtDevEnvHardwareInfo" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList, response, workbook, filename);
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
}


