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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.SysUserInfo;
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
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;

/**
 * @author huwanli
 * @title 项目组员信息
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-22 10:17
 */
@CrossOrigin
@Controller
@RequestMapping("/vue/etUserInfo")
public class EtUserInfoContorller extends BaseController {
    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> rtOnlineUserList(EtUserInfo etUserInfo, Row row) {
        super.getFacade().getCommonQueryService().generateEtUserInfoFromPmisProjectUser(etUserInfo.getPmId());
        etUserInfo.setRow(row);
//        etUserInfo.setIsDel(Constants.STATUS_USE);
        List<EtUserInfo> etUserInfoList = super.getFacade().getEtUserInfoService().getEtUserInfoPaginatedList(etUserInfo);
        int total = super.getFacade().getEtUserInfoService().getEtUserInfoCount(etUserInfo);
        //根据pmid获取项目进程
        EtProcessManager etProcessManager = new EtProcessManager();
        etProcessManager.setPmId(etUserInfo.getPmId());
        etProcessManager = getFacade().getEtProcessManagerService().getEtProcessManager(etProcessManager);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etUserInfoList);
        result.put("process", etProcessManager);
        return result;

    }

    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> addOrModifyHospitalUserInfo(EtUserInfo etUserInfo) {
        Map<String, Object> result = new HashMap<String, Object>();
        EtUserInfo temp = new EtUserInfo();
        temp.setPmId(etUserInfo.getPmId());
        temp.setSerialNo(etUserInfo.getSerialNo());
        temp.setcId(etUserInfo.getcId());
        temp.setUserCard(etUserInfo.getUserCard());
        temp = getFacade().getEtUserInfoService().getEtUserInfo(temp);
        if (etUserInfo.getId() == 0L || etUserInfo.getId() == null) {
            //新增
            //工号查重
            if (temp != null && temp.getIsDel() != 2) {
                result.put("status", Constants.FAILD);
                return result;
            } else {
                etUserInfo.setId(ssgjHelper.createEtUserInfoIdService());
                etUserInfo.setIsDel(1);
                super.getFacade().getEtUserInfoService().createEtUserInfo(etUserInfo);
            }
        } else {
            //编辑
            //工号查重
            if (temp != null && temp.getId() != etUserInfo.getId()) {
                result.put("status", Constants.FAILD);
                return result;
            } else {
                super.getFacade().getEtUserInfoService().modifyEtUserInfo(etUserInfo);
            }
        }
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/exportExcel.do")
    public HttpServletResponse wiriteExcel(EtUserInfo etUserInfo, HttpServletResponse response) throws IOException {
        etUserInfo.setIsDel(Constants.STATUS_USE);
        String fileName = "EtUserInfo.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        super.getFacade().getEtUserInfoService().generateEtUserInfo(etUserInfo, path);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("项目组成员信息.xls", "UTF-8"));
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
    @Transactional
    public Map<String, Object> uploadHospitalUserTemplate(EtUserInfo userInfo, HttpServletRequest request,
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
                List<List<Object>> etUserList = ExcelUtil.importExcel(newFile.getPath(), 1);
                super.getFacade().getEtUserInfoService().createEtUserInfoList(etUserList, userInfo);
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
    @Transactional
    public Map<String, Object> deleteHospitalUser(EtUserInfo userInfo) {
        userInfo = super.getFacade().getEtUserInfoService().getEtUserInfo(userInfo);
        userInfo.setPositionName("4");
        userInfo.setIsDel(Constants.STATUS_UNUSE);
        super.getFacade().getEtUserInfoService().modifyEtUserInfo(userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

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
        temp.setIsPmEntrance(etProcessManager.getIsPmEntrance());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/queryUser.do")
    @ResponseBody
    public Map<String, Object> queryUserForUser(SysUserInfo user, Row row) {
        user.setRow(row);
        List<SysUserInfo> userInfos = super.getFacade().getSysUserInfoService().getSysUserInfoQueryPaginatedList(user);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", userInfos);
        return result;

    }

}