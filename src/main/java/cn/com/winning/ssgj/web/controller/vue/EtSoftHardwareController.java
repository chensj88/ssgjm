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
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
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

//    /**
//     * 数据初始化
//     *
//     * @param etSoftHardware
//     * @return
//     */
//    @RequestMapping("/initSourceData.do")
//    @ResponseBody
//    public Map<String, Object> initSourceData(EtSoftHardware etSoftHardware) {
//        Long pmId = etSoftHardware.getPmId();
//        if (pmId == null) {
//            return null;
//        }
//        //根据pmId获取项目基础信息
//        PmisProjectBasicInfo pmisProjectBasicInfo = this.getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
//        //cId
//        Long cId = pmisProjectBasicInfo.getHtxx();
//        //serialNo
//        Long serialNo = pmisProjectBasicInfo.getKhxx();
//        //根据项目id获取产品
//        List<PmisProductInfo> pmisProductInfos = getFacade().getCommonQueryService().queryProductOfProjectByProjectIdAndType(pmId, 1);
//        List<EtSoftHardware> etSoftHardwares = null;
//        if (pmisProductInfos != null && pmisProductInfos.size() != 0) {
//            Map map = new HashMap();
//            map.put("productList", pmisProductInfos);
//            etSoftHardware.setcId(cId);
//            etSoftHardware.setSerialNo(serialNo.toString());
//            etSoftHardware.setMap(map);
//            //根据产品集合获取硬件集合
//            etSoftHardwares = getFacade().getEtSoftHardwareService().selectEtSoftHardwareByProductInfo(etSoftHardware);
//            //循环查询是否数据师傅存在，不存在则插入
//            EtSoftHardware softHardwareTemp = null;
//            synchronized (this) {
//                for (EtSoftHardware softHardware : etSoftHardwares) {
//                    //查询数据是否入库
//                    softHardwareTemp = getFacade().getEtSoftHardwareService().getEtSoftHardware(softHardware);
//                    if (softHardwareTemp == null) {
//                        softHardware.setId(ssgjHelper.createEtSoftHardwareIdService());
//                        softHardware.setContent("0");
//                        getFacade().getEtSoftHardwareService().createEtSoftHardware(softHardware);
//                    }
//                }
//            }
//        }
//        HashMap result = new HashMap();
//        result.put("status", Constants.SUCCESS);
//        result.put("msg", "初始化数据成功！");
//        return result;
//    }

    /**
     * 根据项目id获取硬件信息
     *
     * @param row
     * @param etSoftHardware
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> list(Row row, EtSoftHardware etSoftHardware, String startDate, String endDate) throws ParseException {
        Long pmId = etSoftHardware.getPmId();
        if (pmId == null) {
            return null;
        }
        etSoftHardware.setRow(row);
        String noScopeCode = etSoftHardware.getNoScopeCode();
        if ("1".equals(noScopeCode)) {
            etSoftHardware.setIsScope(1);
            etSoftHardware.setNoScopeCode(null);
        }
        if (!"null".equals(startDate) && !"null".equals(endDate) && !StringUtil.isEmptyOrNull(startDate) && !StringUtil.isEmptyOrNull(endDate)) {
            etSoftHardware.getMap().put("startDate", DateUtil.convertDateStringToTimestap(startDate));
            etSoftHardware.getMap().put("endDate", DateUtil.convertDateStringToTimestap(endDate));
        }
        List<EtSoftHardware> etSoftHardwarePaginatedList = getFacade().getEtSoftHardwareService().getEtSoftHardwarePaginatedList(etSoftHardware);
        int total = getFacade().getEtSoftHardwareService().getEtSoftHardwareCount(etSoftHardware);
        //系统名称
        List<EtContractTask> productDictInfo = this.getProductDictInfo(etSoftHardware.getSerialNo());
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        //获取默认硬件（字母排序）
        SysDictInfo sysDictInfo = new SysDictInfo();
        sysDictInfo.setDictCode("hardware");
        sysDictInfo.getMap().put("type", "1");
        List<SysDictInfo> firstDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(sysDictInfo);
        sysDictInfo.getMap().put("type", "2");
        List<SysDictInfo> secondDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(sysDictInfo);
        sysDictInfo.getMap().put("type", "3");
        List<SysDictInfo> thirdDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(sysDictInfo);
        sysDictInfo.getMap().put("type", "4");
        List<SysDictInfo> fourDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(sysDictInfo);
        Map<String, Object> result = new HashMap();
        result.put("data1", firstDicts);
        result.put("data2", secondDicts);
        result.put("data3", thirdDicts);
        result.put("data4", fourDicts);
        result.put("rows", etSoftHardwarePaginatedList);
        result.put("total", total);
        result.put("plList", productDictInfo);
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
    @Transactional
    public Map<String, Object> addOrModify(EtSoftHardware etSoftHardware) {
        String noScopeCode = etSoftHardware.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etSoftHardware.setIsScope(1);
        } else {
            etSoftHardware.setIsScope(0);
        }
        //参数值
        Long plId = etSoftHardware.getPlId();
        Long etSoftHardwareId = etSoftHardware.getId();
        String hwName = etSoftHardware.getHwName();
        Long pmId = etSoftHardware.getPmId();
        Long cid = etSoftHardware.getcId();
        String serialNo = etSoftHardware.getSerialNo();
        //创建临时变量
        EtSoftHardware etSoftHardwareTemp = new EtSoftHardware();
        if (plId != null) {
            //已分配系统硬件更新
            etSoftHardwareTemp.setPlId(plId);
        }
        etSoftHardwareTemp.setcId(cid);
        etSoftHardwareTemp.setSerialNo(serialNo);
        etSoftHardwareTemp.setPmId(pmId);
        etSoftHardwareTemp.setHwName(hwName);
        //根据plId和hwName获取数据
        List<EtSoftHardware> etSoftHardwareTemps = getFacade().getEtSoftHardwareService().getEtSoftHardwareList(etSoftHardwareTemp);
        if (etSoftHardware.getId() != null && etSoftHardware.getId() != 0) {
            EtSoftHardware temp = new EtSoftHardware();
            temp.setId(etSoftHardware.getId());
            temp = getFacade().getEtSoftHardwareService().getEtSoftHardware(temp);
            if (temp.getPlId() == null) {
                if (plId != null && etSoftHardwareTemps.size() > 0) {
                    return null;
                }
            }
            if (temp.getPlId() != null) {
                if (plId == null && etSoftHardwareTemps.size() > 0) {
                    return null;
                }
            }
            etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSoftHardwareService().modifyEtSoftHardware(etSoftHardware);
        } else {
            //新增
            if (plId == null) {
                for (EtSoftHardware hardware : etSoftHardwareTemps) {
                    if (hardware.getPlId() == null) {
                        return null;
                    }
                }

            }
            if (plId != null && etSoftHardwareTemps.size() > 0) {
                return null;
            }
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
     * 批量新增硬件
     *
     * @param etSoftHardware
     * @param idList
     * @return
     */
    @RequestMapping(value = "/addBatch.do")
    @ResponseBody
    public Map<String, Object> addBatch(EtSoftHardware etSoftHardware, String idList) {
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < idList.split(",").length; i++) {
            ids.add(idList.split(",")[i]);
        }
        for (String dictValue : ids) {
            //查询字典表硬件
            SysDictInfo sysDictInfo = new SysDictInfo();
            sysDictInfo.setDictCode("hardware");
            sysDictInfo.setDictValue(dictValue);
            sysDictInfo = getFacade().getSysDictInfoService().getSysDictInfo(sysDictInfo);
            //获取硬件名
            String hwName = sysDictInfo.getDictLabel();
            EtSoftHardware temp = new EtSoftHardware();
            temp.setHwName(hwName);
            temp.setPmId(etSoftHardware.getPmId());
            temp.setSerialNo(etSoftHardware.getSerialNo());
            temp.setcId(etSoftHardware.getcId());
            temp = getFacade().getEtSoftHardwareService().getEtSoftHardware(temp);
            if (temp == null) {
                //不存在则新增
                etSoftHardware.setId(ssgjHelper.createEtSoftHardwareIdService());
                etSoftHardware.setSourceId(0L);
                etSoftHardware.setContent("0");
                etSoftHardware.setHwName(hwName);
                etSoftHardware.setIsScope(1);
                etSoftHardware.setNum(1);
                etSoftHardware.setOperator(etSoftHardware.getCreator());
                etSoftHardware.setCreateTime(new Timestamp(new Date().getTime()));
                etSoftHardware.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtSoftHardwareService().createEtSoftHardware(etSoftHardware);
            }
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
            //导出字段封装
            if (etSoftHardwares.get(i).getPlId() == null) {
                etSoftHardwares.get(i).getMap().put("plName", "");
            } else {
                EtContractTask etContractTask = new EtContractTask();
                etContractTask.setId(etSoftHardwares.get(i).getPlId());
                etContractTask = getFacade().getEtContractTaskService().getEtContractTask(etContractTask);
                etSoftHardwares.get(i).getMap().put("plName", etContractTask == null ? "" : etContractTask.getZxtmc());
            }
            dataList.add(ConnectionUtil.objectToMap(etSoftHardwares.get(i)));
        }
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        attrNameList.add("map.plName");
        attrNameList.add("hwName");
//        attrNameList.add("brand");
        attrNameList.add("model");
        attrNameList.add("num");
        attrNameList.add("useContent");
        //表名集合
        List<String> tableNameList = new ArrayList<>();
        tableNameList.add("系统名称");
        tableNameList.add("硬件名称");
//        tableNameList.add("推荐品牌");
        tableNameList.add("推荐型号");
        tableNameList.add("数量");
        tableNameList.add("用途");
        String filename = "硬件清单表" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList, tableNameList, response, workbook, filename);
    }

    /**
     * @param response
     * @return
     * @throws IOException
     * @description 导出模板
     */
    @RequestMapping(value = "/downloadModel.do")
    @ResponseBody
    public Map<String, Object> downloadModel(HttpServletResponse response) throws IOException {

        String realPath = Thread.currentThread().getContextClassLoader().getResource("/template").getPath();
        //获取文件名
        String filename = realPath + "\\hardwareModel.xls";
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream inputStream = new BufferedInputStream(fileInputStream);
        String excelName = "硬件清单模板" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
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
        Map map = new HashMap();
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
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file, EtDevEnvHardware param) throws IOException {
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

            //系统名称
            List<EtContractTask> productDictInfos = this.getProductDictInfo(param.getSerialNo());
            try {
                List<List<Object>> etSoftHardwareList = ExcelUtil.importExcel(newFile.getPath());
                for (List<Object> temp : etSoftHardwareList) {
                    //系统名称
                    String plName = temp.get(0) == null ? null : temp.get(0).toString().trim();
                    String hwName = temp.get(1) == null ? null : temp.get(1).toString().trim();
                    Long plId = null;
                    if (!StringUtil.isEmptyOrNull(plName)) {
                        for (EtContractTask etContractTask : productDictInfos) {
                            String nameTemp = etContractTask.getZxtmc();
                            if (nameTemp.equals(plName)) {
                                //存在条线
                                plId = etContractTask.getId();
                                break;
                            }
                        }
                        if (plId == null) {
                            //导入系统不存在，则跳过该条数据
                            continue;
                        }
                    }
                    //创建临时变量
                    EtSoftHardware etSoftHardwareTemp = new EtSoftHardware();
                    if (plId != null) {
                        //已分配系统硬件更新
                        etSoftHardwareTemp.setPlId(plId);
                    }
                    etSoftHardwareTemp.setcId(param.getcId());
                    etSoftHardwareTemp.setSerialNo(param.getSerialNo());
                    etSoftHardwareTemp.setPmId(param.getPmId());
                    etSoftHardwareTemp.setHwName(hwName);
                    List<EtSoftHardware> etSoftHardwareTemps = getFacade().getEtSoftHardwareService().getEtSoftHardwareList(etSoftHardwareTemp);
                    if (etSoftHardwareTemps.size() > 1) {
                        Boolean doAdd = true;
                        for (EtSoftHardware hardware : etSoftHardwareTemps) {
                            if (hardware.getPlId() == null) {
                                doAdd = false;
                                break;
                            }
                        }
                        if (doAdd) {
                            etSoftHardwareTemp = null;
                        } else {
                            continue;
                        }
                    } else if (etSoftHardwareTemps.size() == 1) {
                        etSoftHardwareTemp = etSoftHardwareTemps.get(0);
                    } else {
                        etSoftHardwareTemp = null;
                    }
                    //新增
                    if (etSoftHardwareTemp == null || (plId == null && etSoftHardwareTemp.getPlId() != null)) {
                        EtSoftHardware etSoftHardware = new EtSoftHardware();
                        etSoftHardware.setId(ssgjHelper.createEtSoftHardwareIdService());
                        etSoftHardware.setPmId(param.getPmId());
                        etSoftHardware.setcId(param.getcId());
                        etSoftHardware.setSerialNo(param.getSerialNo());
                        etSoftHardware.setSourceId(0L);
                        etSoftHardware.setPlId(plId);
                        //硬件名称
                        etSoftHardware.setHwName(hwName);
                        //推荐品牌
//                    etSoftHardware.setBrand(temp.get(2) == null ? null : temp.get(2).toString());
                        etSoftHardware.setModel(temp.get(2) == null ? null : temp.get(2).toString());
                        if (temp.get(3) != null && !StringUtil.isEmptyOrNull(temp.get(3).toString())) {
                            etSoftHardware.setNum(Integer.parseInt(temp.get(3).toString()));
                        } else {
                            etSoftHardware.setNum(1);
                        }
                        //用途
                        etSoftHardware.setUseContent(temp.get(4) == null ? null : temp.get(4).toString());
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
                }
                if (eList.size() > 0) {
                    getFacade().getEtSoftHardwareService().insertEtSoftHardwareByList(eList);
                }
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


