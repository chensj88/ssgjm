package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.NumberParseUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title VUE业务流程调研
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-11 16:34
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/report/")
public class EtReportController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;


    /**
     * 数据初始化
     *
     * @param etReport
     * @return
     */
    @RequestMapping("/initSourceData.do")
    @ResponseBody
    public Map<String, Object> initSourceData(EtReport etReport) {
        Long pmId = etReport.getPmId();
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
        etReport.setcId(cId);
        etReport.setSerialNo(serialNo.toString());
        etReport.setMap(map);
        //根据产品集合获取硬件集合
        List<EtReport> etReports = getFacade().getEtReportService().selectEtReportByProductInfo(etReport);
        //循环查询是否数据师傅存在，不存在则插入
        EtReport etReportTemp = null;
        for (EtReport report : etReports) {
            etReportTemp=new EtReport();
            etReportTemp.setPmId(pmId);
            etReportTemp.setSourceType(report.getSourceType());
            etReportTemp = getFacade().getEtReportService().getEtReport(etReportTemp);
            if (etReportTemp == null) {
                report.setId(ssgjHelper.createEtReportIdService());
                getFacade().getEtReportService().createEtReport(report);
            }
        }
        HashMap result = new HashMap();
        result.put("status", Constants.SUCCESS);
        result.put("msg", "初始化数据成功！");
        return result;
    }


    /**
     * 主页面数据展示
     *
     * @param etReport
     * @param userId
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> list(EtReport etReport,Long userId, Row row) {
        Long pmId=etReport.getPmId();
        //统计总数
        Integer countNum = getCountNum(etReport);
        //已完成数
        Integer completeNum = getCompleteNum(etReport);

        etReport.setRow(row);
        List<EtReport> etReports = super.getFacade().getEtReportService().getEtReportPaginatedList(etReport);
        int total = super.getFacade().getEtReportService().getEtReportCount(etReport);
        String imgPath = null;
        SysDictInfo sysDictInfoTemp = null;
        for (EtReport report : etReports) {
            sysDictInfoTemp = new SysDictInfo();
            sysDictInfoTemp.setDictCode("paperType");
            sysDictInfoTemp.setDictValue(report.getReportType() == null ? null : report.getReportType().toString());
            sysDictInfoTemp = getFacade().getSysDictInfoService().getSysDictInfo(sysDictInfoTemp);
            Map map = new HashMap();
            if (sysDictInfoTemp != null) {
                map.put("type", sysDictInfoTemp.getDictLabel());
            } else {
                map.put("type", "");
            }
            //获取图片路径集合
            imgPath = report.getImgPath();
            if (!StringUtil.isEmptyOrNull(imgPath)) {
                String[] pathArr = imgPath.split(";");
                map.put("imgPath", pathArr);
            } else {
                map.put("imgPath", null);
            }
            report.setMap(map);
        }
        //获取所有报表分类
        SysDictInfo sysDictInfo = new SysDictInfo();
        sysDictInfo.setDictCode("paperType");
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
        result.put("status", Constants.SUCCESS);
        result.put("rows", etReports);
        result.put("total", total);
        result.put("typeList", sysDictInfoList);
        result.put("countNum", countNum);
        result.put("completeNum", completeNum);
        result.put("process", etProcessManager);
        result.put("user", user);
        return result;
    }

    /**
     * 实施范围的修改
     *
     * @param etReport
     * @return
     */
    @RequestMapping(value = "/changeScope.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> changeScope(EtReport etReport) {
        String noScopeCode = etReport.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etReport.setIsScope(1);
        } else {
            etReport.setIsScope(0);
        }
        etReport.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtReportService().modifyEtReport(etReport);
        Integer countNum = getCountNum(etReport);
        //已完成数
        Integer completeNum = getCompleteNum(etReport);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("countNum", countNum);
        result.put("completeNum", completeNum);
        result.put("isScope", etReport.getIsScope());
        return result;
    }

    /**
     * 添加或者修单据件信息
     *
     * @param etReport
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModify(EtReport etReport) {
        String noScopeCode = etReport.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            etReport.setIsScope(1);
        } else {
            etReport.setIsScope(0);
        }
        //创建临时变量
        EtReport etReportTemp = new EtReport();
        etReportTemp.setId(etReport.getId());
        etReportTemp = super.getFacade().getEtReportService().getEtReport(etReportTemp);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etReport.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        etReport.setSerialNo(basicInfo.getKhxx() + "");
        etReport.setcId(basicInfo.getHtxx());
        if (etReportTemp != null) {
            etReport.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtReportService().modifyEtReport(etReport);
        } else {
            etReport.setId(ssgjHelper.createEtReportIdService());
            etReport.setStatus(1);
            etReport.setSourceType(0);
            etReport.setCreator(etReport.getOperator());
            etReport.setCreateTime(new Timestamp(new Date().getTime()));
            etReport.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtReportService().createEtReport(etReport);
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
        temp.setIsPaperAffirm(etProcessManager.getIsPaperAffirm());
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
        temp.setIsPaperDev(etProcessManager.getIsPaperDev());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    /**
     * 上传文件
     *
     * @param report
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> upload(EtReport report, HttpServletRequest request, MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        EtReport temp = new EtReport();
        temp.setId(report.getId());
        temp = getFacade().getEtReportService().getEtReport(temp);
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
            String remotePath = "/report/" + DateUtil.getCurrentDay() + "/" + filename;
            try {
                CommonFtpUtils.uploadFile(remotePath, newFile);
                report.setOperatorTime(new Timestamp(new Date().getTime()));
                if (temp.getImgPath() != null && !"".equals(temp.getImgPath().trim())) {
                    report.setImgPath(temp.getImgPath() + ";" + Constants.FTP_SHARE_FLODER + remotePath);
                } else {
                    report.setImgPath(Constants.FTP_SHARE_FLODER + remotePath);
                }

                super.getFacade().getEtReportService().modifyEtReport(report);
                newFile.delete();
                //上传图像完成后返回已完成数
                Integer completeNum = getCompleteNum(report);
                result.put("status", "success");
                result.put("completeNum", completeNum);
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
     * 计算完成数量
     *
     * @param etReport
     * @return
     */
    public Integer getCompleteNum(EtReport etReport) {
        EtReport report = new EtReport();
        report.setPmId(etReport.getPmId());
        report.setIsScope(1);
        report.setStatus(9);
        //获取所有数据
        List<EtReport> reports = getFacade().getEtReportService().getEtReportList(report);
        Integer completeNum = reports.size();
        return completeNum;
    }

    /**
     * 计算统计总数
     *
     * @param etReport
     * @return
     */
    public Integer getCountNum(EtReport etReport) {
        EtReport report = new EtReport();
        report.setPmId(etReport.getPmId());
        report.setIsScope(1);
        //获取所有数据
        List<EtReport> etReports = getFacade().getEtReportService().getEtReportList(report);
        Integer countNum = etReports.size();
        return countNum;
    }

    /**
     * 更改审核状态
     *
     * @param etReport
     * @return
     */
    @RequestMapping(value = "/changeStatus.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> changeStatus(EtReport etReport) {
        Map map = new HashMap();
        etReport.setOperatorTime(new Timestamp(new Date().getTime()));
        getFacade().getEtReportService().modifyEtReport(etReport);
        //完成数量
        Integer completeNum = getCompleteNum(etReport);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "完成情况修改成功！");
        map.put("completeNum", completeNum);
        return map;
    }
}
