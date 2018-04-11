package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ConnectionUtil;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
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
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author chenfeng
 * @Description 接口信息controller
 * @Date 2018年3月29日09:59:48
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/thirdInterface")
public class EtThirdIntterfaceController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 获取接口信息集合
     *
     * @param etThirdIntterface
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> list(EtThirdIntterface etThirdIntterface, Row row) {
        etThirdIntterface.setRow(row);
        //根据pmid获取所有接口数据
        List<EtThirdIntterface> etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().selectEtThirdIntterfaceMergePageList(etThirdIntterface);
        //根据pmid获取接口数
        Integer total = getFacade().getEtThirdIntterfaceService().selectEtThirdIntterfaceMergeCount(etThirdIntterface);
        PmisProductLineInfo pmisProductLineInfo = null;
        Map map = null;
        //封装产品条线名
        for (EtThirdIntterface intterface : etThirdIntterfaces) {
            pmisProductLineInfo = new PmisProductLineInfo();
            pmisProductLineInfo.setId(intterface.getPlId());
            pmisProductLineInfo = getFacade().getPmisProductLineInfoService().getPmisProductLineInfo(pmisProductLineInfo);
            map = new HashMap();
            map.put("plName", pmisProductLineInfo == null ? null : pmisProductLineInfo.getName());
            intterface.setMap(map);
        }
        //获取所有生效产品条线

        List<PmisProductLineInfo> pmisProductLineInfoList = this.getProductLineList(1);
        //获取所有不在本期范围原因
        SysDictInfo sysDictInfo = new SysDictInfo();
        sysDictInfo.setDictCode("NotInScope");
        List<SysDictInfo> sysDictInfoList = getFacade().getSysDictInfoService().getSysDictInfoList(sysDictInfo);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etThirdIntterfaces);
        result.put("plList", pmisProductLineInfoList);
        result.put("resonList", sysDictInfoList);
        return result;
    }

    /**
     * 添加或修改接口
     *
     * @param etThirdIntterface
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModify(EtThirdIntterface etThirdIntterface) {
        //创建临时变量
        EtThirdIntterface etThirdIntterfaceTemp = new EtThirdIntterface();
        String noScopeCode = etThirdIntterface.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etThirdIntterface.setIsScope(1);
        } else {
            etThirdIntterface.setIsScope(0);
        }
        etThirdIntterfaceTemp.setId(etThirdIntterface.getId());
        etThirdIntterfaceTemp = super.getFacade().getEtThirdIntterfaceService().getEtThirdIntterface(etThirdIntterfaceTemp);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etThirdIntterface.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        etThirdIntterface.setSerialNo(basicInfo.getKhxx() + "");
        etThirdIntterface.setcId(basicInfo.getHtxx());
        if (etThirdIntterfaceTemp != null) {
            etThirdIntterface.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtThirdIntterfaceService().modifyEtThirdIntterface(etThirdIntterface);
        } else {
            etThirdIntterface.setId(ssgjHelper.createThirdInterfaceId());
            etThirdIntterface.setCreator(etThirdIntterface.getOperator());
            etThirdIntterface.setCreateTime(new Timestamp(new Date().getTime()));
            etThirdIntterface.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtThirdIntterfaceService().createEtThirdIntterface(etThirdIntterface);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 删除接口信息
     *
     * @param etThirdIntterface
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> delete(EtThirdIntterface etThirdIntterface) {
        super.getFacade().getEtThirdIntterfaceService().removeEtThirdIntterface(etThirdIntterface);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 确认完成
     *
     * @param pmId
     * @return
     */
    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> confirm(Long pmId) {

        if (pmId == null) {
            return null;
        }
        //根据项目id获取项目基本信息
        PmisProjectBasicInfo pmisProjectBasicInfo = getFacade().getCommonQueryService().queryPmisProjectBasicInfoByProjectId(pmId);
        //获取合同id
        Long contractId = pmisProjectBasicInfo.getHtxx();
        //获取单据号即客户
        Long customerId = pmisProjectBasicInfo.getKhxx();

        EtThirdIntterface etThirdIntterface = new EtThirdIntterface();

        etThirdIntterface.setPmId(pmId);

        etThirdIntterface.setcId(contractId);

        etThirdIntterface.setSerialNo(customerId.toString());
        int total = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfaceCount(etThirdIntterface);

        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (total > 0) {
            etProcessManager.setIsInterfaceDev(1);
            getFacade().getEtProcessManagerService().updateEtProcessManagerByPmId(etProcessManager);
            map.put("type", Constants.SUCCESS);
            map.put("msg", "确认成功！");
        } else {
            map.put("type", "info");
            map.put("msg", "无数据，确认失败！");
        }
        return map;
    }


    /**
     * 更改范围
     *
     * @param etThirdIntterface
     * @return
     */
    @RequestMapping(value = "/changeScope.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> changeScope(EtThirdIntterface etThirdIntterface) {
        String noScopeCode = etThirdIntterface.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etThirdIntterface.setIsScope(1);
        } else {
            etThirdIntterface.setIsScope(0);
        }
        Map map = new HashMap();
        getFacade().getEtThirdIntterfaceService().modifyEtThirdIntterface(etThirdIntterface);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "范围修改成功！");
        return map;
    }

}
