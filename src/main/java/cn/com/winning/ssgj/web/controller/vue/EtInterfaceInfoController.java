package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ConnectionUtil;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author chenfeng
 * @Description 接口信息controller
 * @Date 2018年3月29日09:59:48
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/interfaceInfo")
public class EtInterfaceInfoController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 获取接口信息集合
     * @param etInterfaceInfo
     * @param row
     * @return
     */
    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String, Object> list(EtInterfaceInfo etInterfaceInfo, Row row) {
        etInterfaceInfo.setRow(row);
        //根据pmid获取所有接口数据
        List<EtInterfaceInfo> etInterfaceInfos = getFacade().getEtInterfaceInfoService().selectEtInterfaceInfoMergePageList(etInterfaceInfo);
        //根据pmid获取接口数
        Integer total = getFacade().getEtInterfaceInfoService().selectEtInterfaceInfoMergeCount(etInterfaceInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etInterfaceInfos);
        return result;
    }

    /**
     * 添加或者修改项目产品信息
     *
     * @param etInterfaceInfo
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModify(EtInterfaceInfo etInterfaceInfo) {
        //创建临时变量
        EtInterfaceInfo etInterfaceInfoTemp = new EtInterfaceInfo();
        etInterfaceInfoTemp.setId(etInterfaceInfo.getId());
        etInterfaceInfoTemp = super.getFacade().getEtInterfaceInfoService().getEtInterfaceInfo(etInterfaceInfoTemp);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etInterfaceInfo.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        etInterfaceInfo.setSerialNo(basicInfo.getKhxx() + "");
        if (etInterfaceInfoTemp != null) {
            etInterfaceInfo.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtInterfaceInfoService().modifyEtInterfaceInfo(etInterfaceInfo);
        } else {
            etInterfaceInfo.setId(ssgjHelper.createInterfaceInfoId());
            etInterfaceInfo.setCreator(etInterfaceInfo.getOperator());
            etInterfaceInfo.setCreateTime(new Timestamp(new Date().getTime()));
            etInterfaceInfo.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtInterfaceInfoService().createEtInterfaceInfo(etInterfaceInfo);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 删除接口信息
     *
     * @param etInterfaceInfo
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> delete(EtInterfaceInfo etInterfaceInfo) {
        super.getFacade().getEtInterfaceInfoService().removeEtInterfaceInfo(etInterfaceInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出Excel
     * @param response
     * @param etInterfaceInfo
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public void wiriteExcel(HttpServletResponse response, EtInterfaceInfo etInterfaceInfo) throws IOException {
        //根据pmid获取所有接口数据
        List<EtInterfaceInfo> etInterfaceInfos = getFacade().getEtInterfaceInfoService().selectEtInterfaceInfoMergeList(etInterfaceInfo);
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < etInterfaceInfos.size(); i++) {
            dataList.add(ConnectionUtil.objectToMap(etInterfaceInfos.get(i)));
        }
        //属性数组
        Field[] fields = EtInterfaceInfo.class.getDeclaredFields();
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            attrNameList.add(fields[i].getName());
        }
        String filename = "EtInterfaceInfo" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList, response, workbook, filename);
    }
}
