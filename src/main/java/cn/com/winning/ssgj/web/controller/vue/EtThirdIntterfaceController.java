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
     * 初始化数据
     *
     * @param pmId
     * @param operator
     */
    @RequestMapping(value = "/initSourceData.do")
    @ResponseBody
    public Map<String, Object> initSourceData(Long pmId, Long operator) {
        Map map = new HashMap();
        EtThirdIntterface etThirdIntterface = new EtThirdIntterface();
        etThirdIntterface.setPmId(pmId);
        //根据pmid获取所有接口信息
        List<EtThirdIntterface> etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().selectPmisInterfaceList(etThirdIntterface);
        EtThirdIntterface temp = null;
        for (EtThirdIntterface intterface : etThirdIntterfaces) {
            temp = new EtThirdIntterface();
            temp.setPmId(pmId);
            temp.setSourceId(intterface.getSourceId());
            //查询数据是否入库
            temp = getFacade().getEtThirdIntterfaceService().getEtThirdIntterface(temp);
            if (temp == null) {
                //不存在则将数据插入
                intterface.setId(ssgjHelper.createThirdInterfaceId());
                getFacade().getEtThirdIntterfaceService().createEtThirdIntterface(intterface);
            }
        }
        map.put("status", Constants.SUCCESS);
        map.put("msg", "初始化数据成功！");
        return map;
    }

    /**
     * 获取接口信息集合
     *
     * @param etThirdIntterface
     * @param row
     * @return
     */
    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String, Object> initData(EtThirdIntterface etThirdIntterface, Row row) {
        etThirdIntterface.setRow(row);
        //根据pmid获取分页接口数据
        List<EtThirdIntterface> etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfacePaginatedList(etThirdIntterface);
        //根据pmid获取接口数
        Integer total = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfaceCount(etThirdIntterface);
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(etThirdIntterface.getPmId());
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etThirdIntterfaces);
        result.put("process", etProcessManager);
        return result;
    }

    /**
     * 获取接口信息集合
     *
     * @param etThirdIntterface
     * @param userId
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> list(EtThirdIntterface etThirdIntterface, Long userId, Row row) {
        Long pmId = etThirdIntterface.getPmId();
        if (pmId == null) {
            return null;
        }
        //完成数
        Integer completeNum = getCompleteNum(etThirdIntterface);
        etThirdIntterface.setRow(row);
        //根据pmid获取所有分页接口数据
        List<EtThirdIntterface> etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfacePaginatedList(etThirdIntterface);
        //根据pmid获取接口数
        Integer total = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfaceCount(etThirdIntterface);
        Map map = null;
        String contentType = null;
        String[] contentArr = null;
        //封装产品条线名、完成情况
        for (EtThirdIntterface intterface : etThirdIntterfaces) {
            map = new HashMap();
            contentType = intterface.getContentType();
            if (contentType == null) {
                contentArr = "".split(",");
            } else {
                contentArr = contentType.split(",");
            }
            map.put("contentList", contentArr);
            intterface.setMap(map);
        }
        //获取所有不在本期范围原因
        SysDictInfo sysDictInfo = new SysDictInfo();
        sysDictInfo.setDictCode("NotInScope");
        List<SysDictInfo> sysDictInfoList = getFacade().getSysDictInfoService().getSysDictInfoList(sysDictInfo);
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        //根据pmid和userid查询当前用户
        PmisProjctUser user = new PmisProjctUser();
        user.setXmlcb(pmId);
        user.setRy(userId);
        if (user.getRy() == 100001L) {
            user.setRyfl(0);
        } else {
            user = super.getFacade().getPmisProjctUserService().getPmisProjctUser(user);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("completeNum", completeNum);
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etThirdIntterfaces);
        result.put("resonList", sysDictInfoList);
        result.put("process", etProcessManager);
        result.put("user", user);
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
            etThirdIntterface.setPlId(0L);
            etThirdIntterface.setStatus(1);
            etThirdIntterface.setSourceType(1);
            etThirdIntterface.setSourceId(0L);
            etThirdIntterface.setCreateTime(new Timestamp(new Date().getTime()));
            etThirdIntterface.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtThirdIntterfaceService().createEtThirdIntterface(etThirdIntterface);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }
    /**
     * 接口对应系统导出Excel
     *
     * @param response
     * @param etThirdIntterface
     * @throws IOException
     */
    @RequestMapping(value = "/export.do")
    @ILog
    public void export(HttpServletResponse response, EtThirdIntterface etThirdIntterface) throws IOException {
        //根据pmid获取所有接口数据
        List<EtThirdIntterface> etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().selectEtThirdIntterfaceMergeList(etThirdIntterface);
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < etThirdIntterfaces.size(); i++) {
            dataList.add(ConnectionUtil.objectToMap(etThirdIntterfaces.get(i)));
        }
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        attrNameList.add("productName");
        attrNameList.add("interfaceName");
        attrNameList.add("moduleDetail");
        attrNameList.add("remark");
        //表名集合
        List<String> tableNameList = new ArrayList<>();
        tableNameList.add("产品名称");
        tableNameList.add("模块名称");
        tableNameList.add("明细");
        tableNameList.add("备注");
        String filename = "接口对应系统表" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList,tableNameList, response, workbook, filename);
    }

    /**
     * 第三方接口导出Excel
     *
     * @param response
     * @param etThirdIntterface
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public void wiriteExcel(HttpServletResponse response, EtThirdIntterface etThirdIntterface) throws IOException {
        //根据pmid获取所有接口数据
        List<EtThirdIntterface> etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().selectEtThirdIntterfaceMergeList(etThirdIntterface);
        //参数集合
        List<Map> dataList = new ArrayList<>();
        for (int i = 0; i < etThirdIntterfaces.size(); i++) {
            dataList.add(ConnectionUtil.objectToMap(etThirdIntterfaces.get(i)));
        }
        //属性数组
        Field[] fields = EtThirdIntterface.class.getDeclaredFields();
        //属性集合
        List<String> attrNameList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            attrNameList.add(fields[i].getName());
        }
        String filename = "InterfaceInfo" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, attrNameList,null, response, workbook, filename);
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
     * 确认数量完成
     *
     * @param etProcessManager
     * @return
     */
    @RequestMapping(value = "/confirmNum.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> confirmNum(EtProcessManager etProcessManager) {
        EtProcessManager temp = new EtProcessManager();
        temp.setPmId(etProcessManager.getPmId());
        temp = super.getFacade().getEtProcessManagerService().getEtProcessManager(temp);
        temp.setOperator(etProcessManager.getOperator());
        temp.setOperatorTime(new Timestamp(new Date().getTime()));
        temp.setIsInterfaceAffirm(etProcessManager.getIsInterfaceAffirm());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 确认开发完成
     *
     * @param etProcessManager
     * @return
     */
    @RequestMapping(value = "/confirmDev.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> confirmDev(EtProcessManager etProcessManager) {
        EtProcessManager temp = new EtProcessManager();
        temp.setPmId(etProcessManager.getPmId());
        temp = super.getFacade().getEtProcessManagerService().getEtProcessManager(temp);
        temp.setOperator(etProcessManager.getOperator());
        temp.setOperatorTime(new Timestamp(new Date().getTime()));
        temp.setIsInterfaceDev(etProcessManager.getIsInterfaceDev());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
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
        Map map = new HashMap();
        String noScopeCode = etThirdIntterface.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etThirdIntterface.setIsScope(1);
        } else {
            etThirdIntterface.setIsScope(0);
        }
        etThirdIntterface.setOperatorTime(new Timestamp(new Date().getTime()));
        getFacade().getEtThirdIntterfaceService().modifyEtThirdIntterface(etThirdIntterface);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "范围修改成功！");
        return map;
    }

    /**
     * 更改完成情况
     *
     * @param etThirdIntterface
     * @return
     */
    @RequestMapping(value = "/changeContent.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> changeContent(EtThirdIntterface etThirdIntterface) {
        EtThirdIntterface thirdIntterface = new EtThirdIntterface();
        thirdIntterface.setPmId(etThirdIntterface.getPmId());
        etThirdIntterface.setOperatorTime(new Timestamp(new Date().getTime()));
        getFacade().getEtThirdIntterfaceService().modifyEtThirdIntterface(etThirdIntterface);
        //完成数量
        Integer completeNum = getCompleteNum(thirdIntterface);
        Map map = new HashMap();
        map.put("type", Constants.SUCCESS);
        map.put("msg", "完成情况修改成功！");
        map.put("completeNum", completeNum);
        return map;
    }

    /**
     * 更改审核状态
     *
     * @param etThirdIntterface
     * @return
     */
    @RequestMapping(value = "/changeStatus.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> changeStatus(EtThirdIntterface etThirdIntterface) {
        Map map = new HashMap();
        etThirdIntterface.setOperatorTime(new Timestamp(new Date().getTime()));
        getFacade().getEtThirdIntterfaceService().modifyEtThirdIntterface(etThirdIntterface);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "完成情况修改成功！");
        return map;
    }


    /**
     * 计算完成数量
     *
     * @param etThirdIntterface
     * @return
     */
    public Integer getCompleteNum(EtThirdIntterface etThirdIntterface) {
        EtThirdIntterface thirdIntterface = new EtThirdIntterface();
        thirdIntterface.setPmId(etThirdIntterface.getPmId());
        Integer completeNum = 0;
        String contentType = null;
        //获取所有数据
        List<EtThirdIntterface> etThirdIntterfaceList = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfaceList(thirdIntterface);
        for (EtThirdIntterface intterface : etThirdIntterfaceList) {
            //完成情况
            contentType = intterface.getContentType();
            if (contentType != null && contentType.contains("1") && contentType.contains("2") && contentType.contains("3")) {
                ++completeNum;
            }
        }
        return completeNum;
    }


}
