package cn.com.winning.ssgj.web.controller.vue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.EtSiteInstallDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtOnlineUser;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;

/**
 * @author huwanli
 * @title 上线人员支持
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-22 10:17
 */
@CrossOrigin
@Controller
@RequestMapping("/vue/onlineUser")
public class EtOnlineUserContorller extends BaseController {
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> rtOnlineUserList(EtOnlineUser etOnlineUser, Long serialNo, Row row,String startDate, String endDate) throws ParseException {
        Long pmId = etOnlineUser.getPmId();
        etOnlineUser.setRow(row);
        etOnlineUser.setStatus(Constants.PMIS_STATUS_USE);
        if (!"null".equals(startDate) && !"null".equals(endDate) && !StringUtil.isEmptyOrNull(startDate) && !StringUtil.isEmptyOrNull(endDate)) {
            etOnlineUser.getMap().put("startDate", DateUtil.convertDateStringToTimestap(startDate));
            etOnlineUser.getMap().put("endDate", DateUtil.convertDateStringToTimestap(endDate));
        }
        List<EtOnlineUser> etOnlineUserList = super.getFacade().getEtOnlineUserService().getEtOnlineUserPaginatedList(etOnlineUser);
        Long deptId = null;
        Long siteId = null;
        //封装属性
        for (EtOnlineUser onlineUser : etOnlineUserList) {
            deptId = NumberParseUtil.parseLong(onlineUser.getResponseDept());
            siteId = NumberParseUtil.parseLong(onlineUser.getResponseSite());
            if (deptId != null) {
                EtDepartment departmentTemp = new EtDepartment();
                departmentTemp.setId(deptId);
                departmentTemp = super.getFacade().getEtDepartmentService().getEtDepartment(departmentTemp);
                onlineUser.getMap().put("department", departmentTemp == null ? null : departmentTemp.getDeptName());
            }
            if (siteId != null) {
                EtSiteInstallDetail siteInstallDetail = new EtSiteInstallDetail();
                siteInstallDetail.setId(siteId);
                siteInstallDetail = this.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(siteInstallDetail);
                onlineUser.getMap().put("site", siteInstallDetail == null ? null : siteInstallDetail.getSiteName());
            }
        }
        int total = super.getFacade().getEtOnlineUserService().getEtOnlineUserCount(etOnlineUser);
        EtDepartment queryDepart = new EtDepartment();
        queryDepart.setSerialNo(serialNo);
        queryDepart.setIsDel(Constants.PMIS_STATUS_USE);
        List<EtDepartment> etDepartments = super.getFacade().getEtDepartmentService().getEtDepartmentList(queryDepart);
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(pmId);
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etOnlineUserList);
        result.put("process", etProcessManager);
        result.put("deptList", etDepartments);
        return result;
    }

    @RequestMapping(value = "/checkWork.do")
    @ResponseBody
    public Map<String, Object> checkWork(EtProcessManager manager) {
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("workStatus", manager.getIsSupportStaff() == 1 ? true : false);
        return result;
    }

    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModifyHospitalUserInfo(EtOnlineUser etOnlineUser) {
        etOnlineUser.setStatus(Constants.STATUS_USE);
        Map<String, Object> result = new HashMap<String, Object>();
        EtOnlineUser temp = new EtOnlineUser();
        temp.setUserCode(etOnlineUser.getUserCode());
        temp = getFacade().getEtOnlineUserService().getEtOnlineUser(temp);
        if (etOnlineUser.getId() == 0L) {
            //工号查重
            if (temp != null && temp.getStatus() == 1) {
                result.put("status", Constants.FAILD);
                return result;
            } else {
                if (temp == null) {
                    etOnlineUser.setId(ssgjHelper.createEtOnlineInfoIdService());
                    etOnlineUser.setCreator(etOnlineUser.getOperator());
                    etOnlineUser.setCreateTime(new Timestamp(new Date().getTime()));
                    etOnlineUser.setOperatorTime(new Timestamp(new Date().getTime()));
                    super.getFacade().getEtOnlineUserService().createEtOnlineUser(etOnlineUser);
                } else {
                    etOnlineUser.setId(temp.getId());
                    etOnlineUser.setStatus(1);
                    etOnlineUser.setOperatorTime(new Timestamp(new Date().getTime()));
                    super.getFacade().getEtOnlineUserService().modifyEtOnlineUser(etOnlineUser);
                }
            }
        } else {
            //工号查重
            if (temp != null && temp.getId() != etOnlineUser.getId()) {
                result.put("status", Constants.FAILD);
                return result;
            }
            etOnlineUser.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtOnlineUserService().modifyEtOnlineUser(etOnlineUser);
        }
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public HttpServletResponse wiriteExcel(EtOnlineUser etOnlineUser, HttpServletResponse response) throws IOException {
        etOnlineUser.setStatus(Constants.STATUS_USE);
        String fileName = "EtOnLineUserInfo.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        super.getFacade().getEtOnlineUserService().generateEtOnlineUser(etOnlineUser, path);
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("上线支持人员信息.xls", "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return response;
    }

    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> uploadHospitalUserTemplate(EtOnlineUser etOnlineUser, Long serialNo, HttpServletRequest request,
                                                          MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
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

            try {
                List<List<Object>> userList = ExcelUtil.importExcel(newFile.getPath());
                super.getFacade().getEtOnlineUserService().createEtOnlineUserList(userList, etOnlineUser, serialNo);
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


    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> deleteEtOnlineUser(EtOnlineUser etOnlineUser) {
        etOnlineUser.setStatus(Constants.STATUS_UNUSE);
        super.getFacade().getEtOnlineUserService().modifyEtOnlineUser(etOnlineUser);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> confirmEtOnlineUser(EtProcessManager etProcessManager) {
        EtProcessManager temp = new EtProcessManager();
        temp.setPmId(etProcessManager.getPmId());
        temp = super.getFacade().getEtProcessManagerService().getEtProcessManager(temp);
        temp.setOperator(etProcessManager.getOperator());
        temp.setOperatorTime(new Timestamp(new Date().getTime()));
        temp.setIsSupportStaff(etProcessManager.getIsSupportStaff());
        temp.setIsEnd(etProcessManager.getIsEnd());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/responseSiteList.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> responseSiteList(EtDepartment etDepartment) {
        List<EtSiteInstallDetail> etSiteInstallDetails = null;
        if (etDepartment.getId() != null) {
            etSiteInstallDetails = super.getFacade().getEtSiteInstallDetailService().getSiteListByDeptId(etDepartment);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        result.put("siteList", etSiteInstallDetails);
        return result;
    }


}
