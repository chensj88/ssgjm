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
    public Map<String, Object> initSourceData(EtSoftHardware etSoftHardware) {
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
        List<EtSoftHardware> etSoftHardwares = null;
        if (pmisProductInfos != null && pmisProductInfos.size() != 0) {
            Map map = new HashMap();
            map.put("productList", pmisProductInfos);
            etSoftHardware.setcId(cId);
            etSoftHardware.setSerialNo(serialNo.toString());
            etSoftHardware.setMap(map);
            //根据产品集合获取硬件集合
            etSoftHardwares = getFacade().getEtSoftHardwareService().selectEtSoftHardwareByProductInfo(etSoftHardware);
            //循环查询是否数据师傅存在，不存在则插入
            EtSoftHardware softHardwareTemp = null;
            for (EtSoftHardware softHardware : etSoftHardwares) {
                //查询数据是否入库
                softHardwareTemp = getFacade().getEtSoftHardwareService().getEtSoftHardware(softHardware);
                if (softHardwareTemp == null) {
                    softHardware.setId(ssgjHelper.createEtSoftHardwareIdService());
                    softHardware.setContent("0");
                    getFacade().getEtSoftHardwareService().createEtSoftHardware(softHardware);
                }
            }
        }
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
        int total = getFacade().getEtSoftHardwareService().getEtSoftHardwareCount(etSoftHardware);
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
        //当无法根据pmid找到产品条线时，给出所有产品条线
        if (pmisProductLineInfoList == null || pmisProductLineInfoList.size() == 0) {
            PmisProductLineInfo temp = new PmisProductLineInfo();
            temp.setZt(1);
            pmisProductLineInfoList = getFacade().getPmisProductLineInfoService().getPmisProductLineInfoList(temp);
        }
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", etSoftHardwarePaginatedList);
        result.put("total", total);
        result.put("plList", pmisProductLineInfoList);
        result.put("process", etProcessManager);
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
            etSoftHardware.setContent("0");
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
        PmisProductLineInfo pmisProductLineInfo = null;
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < etSoftHardwares.size(); i++) {
            //导出字段封装
            pmisProductLineInfo = new PmisProductLineInfo();
            pmisProductLineInfo.setId(etSoftHardwares.get(i).getPlId());
            pmisProductLineInfo = getFacade().getPmisProductLineInfoService().getPmisProductLineInfo(pmisProductLineInfo);
            etSoftHardwares.get(i).getMap().put("productLine", pmisProductLineInfo == null ? "" : pmisProductLineInfo.getName());
            dataList.add(ConnectionUtil.objectToMap(etSoftHardwares.get(i)));
        }
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        attrNameList.add("map.productLine");
        attrNameList.add("hwName");
        attrNameList.add("brand");
        attrNameList.add("model");
        attrNameList.add("num");
        attrNameList.add("useContent");
        //表名集合
        List<String> tableNameList = new ArrayList<>();
        tableNameList.add("系统名称");
        tableNameList.add("硬件名称");
        tableNameList.add("推荐品牌");
        tableNameList.add("推荐型号");
        tableNameList.add("数量");
        tableNameList.add("用途");
        String filename = "测试与硬件表" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList, tableNameList, response, workbook, filename);
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
            String productLine = null;
            PmisProductLineInfo pmisProductLineInfo = null;
            List<PmisProductLineInfo> pmisProductLineInfos = null;
            try {
                List<List<Object>> etSoftHardwareList = ExcelUtil.importExcel(newFile.getPath());
                for (List<Object> temp : etSoftHardwareList) {
                    EtSoftHardware etSoftHardware = new EtSoftHardware();
                    etSoftHardware.setId(ssgjHelper.createEtSoftHardwareIdService());
                    etSoftHardware.setPmId(pmId);
                    etSoftHardware.setcId(contractId);
                    etSoftHardware.setSerialNo(customerId.toString());
                    etSoftHardware.setSourceId(0L);
                    //获取产品条线名称
                    productLine = temp.get(0) == null ? null : temp.get(0).toString();
                    if (StringUtil.isEmptyOrNull(productLine)) {
                        etSoftHardware.setPlId(0L);
                    } else {
                        //根据产品条线名称查询产品条线id
                        pmisProductLineInfo = new PmisProductLineInfo();
                        pmisProductLineInfo.setName(productLine);
                        //防止重名
                        pmisProductLineInfos = getFacade().getPmisProductLineInfoService().getPmisProductLineInfoList(pmisProductLineInfo);
                        if (pmisProductLineInfos.size() == 0) {
                            etSoftHardware.setPlId(0L);
                        } else {
                            etSoftHardware.setPlId(pmisProductLineInfos.get(0).getId());
                        }
                    }
                    //硬件名称
                    etSoftHardware.setHwName(temp.get(1) == null ? null : temp.get(1).toString());
                    //推荐品牌
                    etSoftHardware.setBrand(temp.get(2) == null ? null : temp.get(2).toString());
                    etSoftHardware.setModel(temp.get(3) == null ? null : temp.get(3).toString());
                    if (temp.get(4) != null && !StringUtil.isEmptyOrNull(temp.get(4).toString())) {
                        etSoftHardware.setNum(Integer.parseInt(temp.get(4).toString()));
                    } else {
                        etSoftHardware.setNum(1);
                    }
                    //用途
                    etSoftHardware.setUseContent(temp.get(5) == null ? null : temp.get(5).toString());
                    //默认在当前范围
                    etSoftHardware.setIsScope(1);
                    //默认未完成
                    etSoftHardware.setContent("0");
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

    /**
     * 改变数量
     *
     * @param etSoftHardware
     * @return
     */
    @RequestMapping(value = "/numChange.do")
    @ResponseBody
    @ILog
    public Map<String, Object> numChange(EtSoftHardware etSoftHardware) {
        etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSoftHardwareService().modifyEtSoftHardware(etSoftHardware);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("msg", "数量更改成功！");
        return result;
    }

    /**
     * 更改范围
     *
     * @param etSoftHardware
     * @return
     */
    @RequestMapping(value = "/changeScope.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> changeScope(EtSoftHardware etSoftHardware) {
        String noScopeCode = etSoftHardware.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etSoftHardware.setIsScope(1);
        } else {
            etSoftHardware.setIsScope(0);
        }
        etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
        Map map = new HashMap();
        getFacade().getEtSoftHardwareService().modifyEtSoftHardware(etSoftHardware);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "范围修改成功！");
        return map;
    }

    /**
     * 更改完成情况
     *
     * @param etSoftHardware
     * @return
     */
    @RequestMapping(value = "/changeContent.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> changeContent(EtSoftHardware etSoftHardware) {
        etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
        getFacade().getEtSoftHardwareService().modifyEtSoftHardware(etSoftHardware);
        Map map = new HashMap();
        map.put("type", Constants.SUCCESS);
        map.put("msg", "完成情况修改成功！");
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
        temp.setIsHardwareList(etProcessManager.getIsHardwareList());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 项目启动确认完成
     *
     * @param etProcessManager
     * @return
     */
    @RequestMapping(value = "/doStartConfirm.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> doStartConfirm(EtProcessManager etProcessManager) {
        EtProcessManager temp = new EtProcessManager();
        temp.setPmId(etProcessManager.getPmId());
        temp = super.getFacade().getEtProcessManagerService().getEtProcessManager(temp);
        temp.setOperator(etProcessManager.getOperator());
        temp.setOperatorTime(new Timestamp(new Date().getTime()));
        temp.setIsCreateTestEnv(etProcessManager.getIsCreateTestEnv());
        temp.setIsTestHardware(etProcessManager.getIsTestHardware());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


}


