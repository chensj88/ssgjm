package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.jcraft.jsch.ChannelSftp;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        etThirdIntterface.setSourceId(0L);
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
    public Map<String, Object> list(EtThirdIntterface etThirdIntterface, Long userId, Row row, Integer isPmis, String startDate, String endDate) throws ParseException {
        Long pmId = etThirdIntterface.getPmId();
        if (pmId == null) {
            return null;
        }
        //统计总数
        Integer countNum = getCountNum(etThirdIntterface);
        //完成数
        Integer completeNum = getCompleteNum(etThirdIntterface);
        Integer total = 0;
        List<EtThirdIntterface> etThirdIntterfaces = null;
        etThirdIntterface.setRow(row);
        String noScopeCode=etThirdIntterface.getNoScopeCode();
        if("1".equals(noScopeCode)){
            etThirdIntterface.setIsScope(1);
            etThirdIntterface.setNoScopeCode(null);
        }
        if (!"null".equals(startDate) && !"null".equals(endDate) && !StringUtil.isEmptyOrNull(startDate) && !StringUtil.isEmptyOrNull(endDate)) {
            etThirdIntterface.getMap().put("startDate", DateUtil.convertDateStringToTimestap(startDate));
            etThirdIntterface.getMap().put("endDate", DateUtil.convertDateStringToTimestap(endDate));
        }
        if (isPmis == 1) {
            //pmis数据
            etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().selectPmisInterfacePaginatedList(etThirdIntterface);
            total = getFacade().getEtThirdIntterfaceService().selectPmisInterfaceCount(etThirdIntterface);
        } else if (isPmis == 0) {
            //非pmis数据
            etThirdIntterface.setSourceId(0L);
            //根据pmid获取所有分页接口数据
            etThirdIntterfaces = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfacePaginatedList(etThirdIntterface);
            //根据pmid获取接口数
            total = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfaceCount(etThirdIntterface);
        }

        //封装产品条线名、完成情况
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        for (EtThirdIntterface intterface : etThirdIntterfaces) {
            //创建人id
            Long creator = intterface.getCreator();
            String creatorName = null;
            if (creator != null && creator != 0) {
                EtUserInfo etUserInfo = new EtUserInfo();
                etUserInfo.setUserId(creator);
                List<EtUserInfo> etUserInfos = getFacade().getEtUserInfoService().getEtUserInfoList(etUserInfo);
                creatorName = etUserInfos.size() > 0 ? etUserInfos.get(0).getCName() : "";
            } else {
                creatorName = "admin";
            }
            Map map = new HashMap();
            String contentType = intterface.getContentType();
            String[] contentArr = null;
            if (contentType == null) {
                contentArr = "".split(",");
            } else {
                contentArr = contentType.split(",");
            }
            String createDate = df.format(intterface.getCreateTime());
            Long plId = intterface.getPlId();
            PmisProductLineInfo pmisProductLineInfo = new PmisProductLineInfo();
            if (plId != null && plId != 0) {
                pmisProductLineInfo.setId(plId);
                pmisProductLineInfo = getFacade().getPmisProductLineInfoService().getPmisProductLineInfo(pmisProductLineInfo);
            }
            String filePath = intterface.getFilePath();
            map.put("plName", pmisProductLineInfo.getName());
            map.put("contentList", contentArr);
            map.put("creator", creatorName);
            map.put("createDate", createDate);
            map.put("isUpload", !StringUtil.isEmptyOrNull(filePath));
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
        //根据项目Id
        List<PmisProductLineInfo> pmisProductLineInfoList = this.getProductLineList(pmId);
        //当无法根据pmid找到产品条线时，给出所有产品条线
        if (pmisProductLineInfoList == null || pmisProductLineInfoList.size() == 0) {
            PmisProductLineInfo temp = new PmisProductLineInfo();
            temp.setZt(1);
            pmisProductLineInfoList = getFacade().getPmisProductLineInfoService().getPmisProductLineInfoList(temp);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("countNum", countNum);
        result.put("completeNum", completeNum);
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etThirdIntterfaces);
        result.put("resonList", sysDictInfoList);
        result.put("process", etProcessManager);
        result.put("plList", pmisProductLineInfoList);
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
        ExcelUtil.exportExcelByStream(dataList, attrNameList, tableNameList, response, workbook, filename);
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
        ExcelUtil.exportExcelByStream(dataList, attrNameList, null, response, workbook, filename);
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
        //获取接口
        EtThirdIntterface temp = super.getFacade().getEtThirdIntterfaceService().getEtThirdIntterface(etThirdIntterface);
        super.getFacade().getEtThirdIntterfaceService().removeEtThirdIntterface(etThirdIntterface);
        if (temp.getFilePath() != null && !"".equals(temp.getFilePath().trim())) {
            //如果已上传文件，则删除之前上传的文件
            CommonFtpUtils.removeUploadFile(temp.getFilePath());
        }
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
        Integer countNum = getCountNum(etThirdIntterface);
        //已完成数
        Integer completeNum = getCompleteNum(etThirdIntterface);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "范围修改成功！");
        map.put("countNum", countNum);
        map.put("completeNum", completeNum);
        map.put("isScope", etThirdIntterface.getIsScope());
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
        Map map = new HashMap();
        map.put("type", Constants.SUCCESS);
        map.put("msg", "完成情况修改成功！");
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
        //完成数量
        Integer completeNum = getCompleteNum(etThirdIntterface);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "完成情况修改成功！");
        map.put("completeNum", completeNum);
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
        thirdIntterface.setIsScope(1);
        thirdIntterface.setStatus(9);
        thirdIntterface.setSourceId(0L);
        //获取所有数据
        List<EtThirdIntterface> etThirdIntterfaceList = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfaceList(thirdIntterface);
        Integer completeNum = etThirdIntterfaceList.size();
        return completeNum;
    }

    /**
     * 计算统计总数
     *
     * @param etThirdIntterface
     * @return
     */
    public Integer getCountNum(EtThirdIntterface etThirdIntterface) {
        EtThirdIntterface thirdIntterface = new EtThirdIntterface();
        thirdIntterface.setPmId(etThirdIntterface.getPmId());
        thirdIntterface.setIsScope(1);
        thirdIntterface.setSourceId(0L);
        //获取所有数据
        List<EtThirdIntterface> etThirdIntterfaceList = getFacade().getEtThirdIntterfaceService().getEtThirdIntterfaceList(thirdIntterface);
        Integer countNum = etThirdIntterfaceList.size();
        return countNum;
    }

    /**
     * 上传文件
     *
     * @param etThirdIntterface
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> upload(EtThirdIntterface etThirdIntterface, HttpServletRequest request, MultipartFile file, Long pmId) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        EtThirdIntterface temp = new EtThirdIntterface();
        temp.setId(etThirdIntterface.getId());
        temp = getFacade().getEtThirdIntterfaceService().getEtThirdIntterface(temp);
        boolean ftpStatus = false;
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
            String fileType = filename.substring(filename.lastIndexOf("."));
            String remotePath = Constants.UPLOAD_PC_PREFIX + pmId + "/interface/" + System.currentTimeMillis() + fileType;
            try {
                CommonFtpUtils.uploadFile(remotePath, newFile);
                etThirdIntterface.setOperatorTime(new Timestamp(new Date().getTime()));
                if (temp.getFilePath() != null && !"".equals(temp.getFilePath().trim())) {
                    //如果已上传文件，则删除之前上传的文件
                    CommonFtpUtils.removeUploadFile(temp.getFilePath());
                }
                etThirdIntterface.setFilePath(remotePath);
                super.getFacade().getEtThirdIntterfaceService().modifyEtThirdIntterface(etThirdIntterface);
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
     * @param response
     * @return
     * @throws IOException
     * @description 下载
     */
    @RequestMapping(value = "/download.do")
    @ResponseBody
    public Map<String, Object> download(HttpServletResponse response, EtThirdIntterface etThirdIntterface) throws IOException {
        //获取数据校验信息
        etThirdIntterface = getFacade().getEtThirdIntterfaceService().getEtThirdIntterface(etThirdIntterface);
        String filePath = etThirdIntterface.getFilePath();
        Map map = new HashMap();
        if (StringUtil.isEmptyOrNull(filePath)) {
            logger.error("Script is not exist!");
            map.put("error", "File is not exist!");
            return map;
        }
        //获取文件名
        String filename = etThirdIntterface.getInterfaceName() + filePath.substring(filePath.lastIndexOf("."));
        ChannelSftp sftpConnect = null;
        byte[] bytes = null;
        try {
            sftpConnect = SFtpUtils.getSftpConnect();
            //sftpConnect.setFilenameEncoding("GBK");
            bytes = SFtpUtils.downloadAsByte(filePath, sftpConnect);

        } catch (Exception e) {
            e.printStackTrace();
        }


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
            buff.write(bytes);
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
        map.put("status", Constants.SUCCESS);
        return map;
    }

}
