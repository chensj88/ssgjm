package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
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
            etReportTemp = new EtReport();
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
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> list(EtReport etReport, Row row) {
        etReport.setRow(row);
        List<EtReport> etReports = super.getFacade().getEtReportService().getEtReportPaginatedList(etReport);
        int total = super.getFacade().getEtReportService().getEtReportCount(etReport);
        Integer completeNum = 0;
        String imgPath = null;
        for (EtReport report : etReports) {
            imgPath = report.getImgPath();
            if (StringUtil.isEmptyOrNull(imgPath)) {
                //如果未上传图片，则为未完成
                completeNum++;
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("rows", etReports);
        result.put("total", total);
        result.put("completeNum", completeNum);
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
        etReport.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtReportService().modifyEtReport(etReport);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 统计完成和未完成数量
     *
     * @param process
     * @return
     */
    @RequestMapping(value = "/countInfo.do")
    @ResponseBody
    public Map<String, Object> countProcessInfo(EtBusinessProcess process) {
        //查询流程总的数量
        process.setIsScope(Constants.STATUS_USE);
        int sumBussinessProcessNum = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        //查询完成的流程数量
        process.setStatus(Constants.APPROVAL_STATUS_END);
        int completeBPNum = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("sumNum", sumBussinessProcessNum);
        result.put("comNum", completeBPNum);
        return result;
    }

    /**
     * 添加流程信息
     *
     * @param process
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> addOrModifyProcessInfo(EtBusinessProcess process) {
        if (process.getId() == 0L) {
            process.setId(ssgjHelper.createEtBusinessProcessIdService());
            process.setSourceType(Constants.STATUS_USE);
            process.setStatus(Constants.STATUS_USE);
            process.setCreator(process.getOperator());
            process.setCreateTime(new Timestamp(new Date().getTime()));
            process.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtBusinessProcessService().createEtBusinessProcess(process);
        } else {
            process.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 删除流程调研信息
     *
     * @param process
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> deleteBusinessProcess(EtBusinessProcess process) {
        super.getFacade().getEtBusinessProcessService().removeEtBusinessProcess(process);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 流程数量确认
     *
     * @param manager
     * @return
     */
    @RequestMapping(value = "/confirmFlowNum.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> comfirmBusinessProcess(EtProcessManager manager) {
        int status = manager.getIsFlowAffirm();
        manager.setIsFlowAffirm(null);
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        manager.setIsFlowAffirm(status);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(manager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 流程调研与配置
     *
     * @param manager
     * @return
     */
    @RequestMapping(value = "/confirmFlowConfig.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String, Object> confirmFlowConfig(EtProcessManager manager) {
        int status = manager.getIsFlowConfig();
        manager.setIsFlowConfig(null);
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        manager.setIsFlowConfig(status);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(manager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 查看是否确认完成确认数量
     *
     * @param manager
     * @return
     */
    @RequestMapping(value = "/checkAffirm.do")
    @ResponseBody
    public Map<String, Object> checkBusinessProcessAffirm(EtProcessManager manager) {
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", manager.getIsFlowAffirm());
        return result;

    }

    /**
     * 查看是否完成流程配置工作
     *
     * @param manager
     * @return
     */
    @RequestMapping(value = "/checkConfig.do")
    @ResponseBody
    public Map<String, Object> checkBusinessProcessConfig(EtProcessManager manager) {
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", manager.getIsFlowConfig());
        return result;
    }

    /**
     * 查看是否完成流程配置工作
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/checkAuth.do")
    @ResponseBody
    public Map<String, Object> checkUserAuth(PmisProjctUser user) {
        if (user.getRy() == 100001L) {
            user.setRyfl(0);
        } else {
            user = super.getFacade().getPmisProjctUserService().getPmisProjctUser(user);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", user.getRyfl());
        return result;
    }

    /**
     * 上传文件
     *
     * @param process
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> uploadFile(EtBusinessProcess process, HttpServletRequest request, MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        process = super.getFacade().getEtBusinessProcessService().getEtBusinessProcess(process);
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
            ;
            String remotePath = "/process/" + DateUtil.getCurrentDay() + "/" + filename;
            try {
                CommonFtpUtils.uploadFile(remotePath, newFile);
                process.setStatus(1);
                process.setUploadPath(Constants.FTP_SHARE_FLODER + remotePath);
                super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
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
