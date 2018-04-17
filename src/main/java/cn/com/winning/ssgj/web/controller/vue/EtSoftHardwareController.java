package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
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
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

/**
 * 基础数据类型处理Controller
 *
 * @author FengChen
 * @date 2018年4月17日13:43:51
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/softHardware")
public class EtSoftHardwareController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EtSoftHardwareController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 数据初始化
     *
     * @param etSoftHardware
     * @return
     */
    @RequestMapping("/initSourceData.do")
    @ResponseBody
    private Map<String, Object> initSourceData(EtSoftHardware etSoftHardware) {
        Long pmId = etSoftHardware.getPmId();
        if (pmId == null) {
            return null;
        }
        //根据pmId获取项目基础信息
        PmisProjectBasicInfo pmisProjectBasicInfo = this.getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //cId
        Long cId = pmisProjectBasicInfo.getHtxx();
        //serialNo
        Long serialNo = pmisProjectBasicInfo.getKhxx();
        //根据项目id获取产品
        List<PmisProductInfo> pmisProductInfos = getFacade().getCommonQueryService().queryProductOfProjectByProjectIdAndType(pmId, 1);
        Map map = new HashMap();
        map.put("productList", pmisProductInfos);
        etSoftHardware.setcId(cId);
        etSoftHardware.setSerialNo(serialNo.toString());
        etSoftHardware.setMap(map);
        //根据产品集合获取硬件集合
        List<EtSoftHardware> etSoftHardwares = getFacade().getEtSoftHardwareService().selectEtSoftHardwareByProductInfo(etSoftHardware);
        //添加id
        for (EtSoftHardware softHardware : etSoftHardwares) {
            softHardware.setId(ssgjHelper.createEtSoftHardwareIdService());
        }
        //批量插入数据
        getFacade().getEtSoftHardwareService().insertEtSoftHardwareByList(etSoftHardwares);
        HashMap result = new HashMap();
        result.put("status", Constants.SUCCESS);
        result.put("msg", "初始化数据成功！");
        return result;
    }

    /**
     * 根据项目id获取硬件信息
     *
     * @param row
     * @param etSoftHardware
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> list(Row row, EtSoftHardware etSoftHardware) {
        Long pmId = etSoftHardware.getPmId();
        if (pmId == null) {
            return null;
        }
        etSoftHardware.setRow(row);
        List<EtSoftHardware> etSoftHardwarePaginatedList = getFacade().getEtSoftHardwareService().getEtSoftHardwarePaginatedList(etSoftHardware);
        int total = etSoftHardwarePaginatedList.size();
        PmisProductLineInfo pmisProductLineInfo = null;
        //封装系统名称
        for (EtSoftHardware softHardware : etSoftHardwarePaginatedList) {
            pmisProductLineInfo = new PmisProductLineInfo();
            pmisProductLineInfo.setId(softHardware.getPlId());
            pmisProductLineInfo = getFacade().getPmisProductLineInfoService().getPmisProductLineInfo(pmisProductLineInfo);
            Map nameMap = new HashMap();
            nameMap.put("plName", pmisProductLineInfo.getName());
            softHardware.setMap(nameMap);
        }
        //根据项目Id
        List<PmisProductLineInfo> pmisProductLineInfoList = this.getProductLineList(pmId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", etSoftHardwarePaginatedList);
        result.put("total", total);
        result.put("plList", pmisProductLineInfoList);
        result.put("status", Constants.SUCCESS);
        return result;
    }


    /**
     * 添加或者修改硬件信息
     *
     * @param etSoftHardware
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModify(EtSoftHardware etSoftHardware) {
        String noScopeCode = etSoftHardware.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etSoftHardware.setIsScope(1);
        } else {
            etSoftHardware.setIsScope(0);
        }
        //创建临时变量
        EtSoftHardware etSoftHardwareTemp = new EtSoftHardware();
        etSoftHardwareTemp.setId(etSoftHardware.getId());
        etSoftHardwareTemp = super.getFacade().getEtSoftHardwareService().getEtSoftHardware(etSoftHardwareTemp);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etSoftHardware.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        etSoftHardware.setSerialNo(basicInfo.getKhxx() + "");
        etSoftHardware.setcId(basicInfo.getHtxx());
        if (etSoftHardwareTemp != null) {
            etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSoftHardwareService().modifyEtSoftHardware(etSoftHardware);
        } else {
            etSoftHardware.setId(ssgjHelper.createEtSoftHardwareIdService());
            etSoftHardware.setSourceId(0L);
            etSoftHardware.setCreator(etSoftHardware.getOperator());
            etSoftHardware.setCreateTime(new Timestamp(new Date().getTime()));
            etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSoftHardwareService().createEtSoftHardware(etSoftHardware);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 删除硬件信息
     *
     * @param etSoftHardware
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> delete(EtSoftHardware etSoftHardware) {
        super.getFacade().getEtSoftHardwareService().removeEtSoftHardware(etSoftHardware);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出Excel
     *
     * @param response
     * @param etSoftHardware
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    public void wiriteExcel(HttpServletResponse response, EtSoftHardware etSoftHardware) throws IOException {
        //根据pmid获取所有硬件数据
        List<EtSoftHardware> etSoftHardwares = getFacade().getEtSoftHardwareService().getEtSoftHardwareList(etSoftHardware);
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < etSoftHardwares.size(); i++) {
            dataList.add(ConnectionUtil.objectToMap(etSoftHardwares.get(i)));
        }
        //属性数组
        Field[] fields = EtSoftHardware.class.getDeclaredFields();
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            if (!"serialVersionUID".equals(fields[i].getName())) {
                attrNameList.add(fields[i].getName());
            }
        }
        String filename = "EtSoftHardware" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
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
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file, EtDevEnvHardware param) throws IOException {
        //获取项目id
        Long pmId = param.getPmId();
        //根据项目id获取项目基本信息
        PmisProjectBasicInfo pmisProjectBasicInfo = getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //获取合同id
        Long contractId = pmisProjectBasicInfo.getHtxx();
        //获取单据号即客户
        Long customerId = pmisProjectBasicInfo.getKhxx();

        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/hardware/");
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
            //导入的数据集合
            List<EtSoftHardware> eList = new ArrayList<>();
            try {
                List<List<Object>> etSoftHardwareList = ExcelUtil.importExcel(newFile.getPath());
                for (List<Object> temp : etSoftHardwareList) {
                    EtSoftHardware etSoftHardware = new EtSoftHardware();
                    etSoftHardware.setId(ssgjHelper.createEtDevEnvHardwareId());
                    etSoftHardware.setPmId(pmId);
                    etSoftHardware.setcId(contractId);
                    etSoftHardware.setSerialNo(customerId.toString());
                    etSoftHardware.setSourceId(0L);
                    etSoftHardware.setPlId(NumberParseUtil.parseLong(temp.get(0).toString()));
                    etSoftHardware.setHwName(temp.get(1).toString());
                    etSoftHardware.setHwCode(temp.get(2).toString());
                    etSoftHardware.setBrand(temp.get(3).toString());
                    etSoftHardware.setModel(temp.get(4).toString());
                    if (!StringUtil.isEmptyOrNull(temp.get(5).toString())) {
                        etSoftHardware.setNum(Integer.parseInt(temp.get(5).toString()));
                    }else{
                        etSoftHardware.setNum(1);
                    }
                    etSoftHardware.setUseContent(temp.get(6).toString());
                    if (!StringUtil.isEmptyOrNull(temp.get(7).toString())) {
                        etSoftHardware.setIsScope(Integer.parseInt(temp.get(6).toString()));
                    }
                    etSoftHardware.setNoScopeCode(temp.get(7).toString());
                    etSoftHardware.setCreateTime(new Timestamp(new Date().getTime()));
                    etSoftHardware.setCreator(param.getOperator());
                    etSoftHardware.setOperator(param.getOperator());
                    etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
                    eList.add(etSoftHardware);
                }
                getFacade().getEtSoftHardwareService().insertEtSoftHardwareByList(eList);
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


