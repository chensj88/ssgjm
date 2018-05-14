package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
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
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 医院用户
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-12 10:17
 */
@CrossOrigin
@Controller
@RequestMapping("/vue/hospital")
public class HospitalUserController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 查询项目下医院用户信息
     * @param queryUser
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> queryHospitalUserInfo(SysUserInfo queryUser,Row row) {

       /* SysUserInfo user = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        long c_id = (long) user.getMap().get("C_ID");*/
        queryUser.setRow(row);
        queryUser.setUserType(Constants.User.USER_TYPE_HOSPITAL);
        queryUser.setStatus(Constants.PMIS_STATUS_USE);
        List<SysUserInfo> queryUserList = super.getFacade().getSysUserInfoService().getSysUserInfoPaginatedList(queryUser);
        int total = super.getFacade().getSysUserInfoService().getSysUserInfoCount(queryUser);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", queryUserList);
        return result;

    }

    /**
     * 添加或者修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModifyHospitalUserInfo(SysUserInfo userInfo) {
        //用户重复性校验
        SysUserInfo oldUser = new SysUserInfo();
        oldUser.setUserType(Constants.User.USER_TYPE_HOSPITAL);
        oldUser.setStatus(Constants.PMIS_STATUS_USE);
        oldUser.setSsgs(userInfo.getSsgs());
        oldUser.setUserid(userInfo.getUserid());
        oldUser = super.getFacade().getSysUserInfoService().getSysUserInfo(oldUser);
//        oldUser.setId(userInfo.getId());
        if (userInfo.getId() == 0 && oldUser == null ) {
            userInfo.setUserType(Constants.User.USER_TYPE_HOSPITAL);
            userInfo.setStatus(Constants.PMIS_STATUS_USE);
            userInfo.setId(ssgjHelper.createUserId());
            super.getFacade().getSysUserInfoService().createSysUserInfo(userInfo);
        } else {
            //userInfo.setId(oldUser.getId());
            userInfo.setUserType(Constants.User.USER_TYPE_HOSPITAL);
            userInfo.setStatus(Constants.PMIS_STATUS_USE);
            super.getFacade().getSysUserInfoService().modifySysUserInfo(userInfo);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出医院用户信息
     * @param queryUser
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public HttpServletResponse wiriteExcel(SysUserInfo queryUser, HttpServletResponse response) throws IOException {
        queryUser.setStatus(Constants.PMIS_STATUS_USE);
        queryUser.setUserType(Constants.User.USER_TYPE_HOSPITAL);
        String fileName = "userinfo.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        super.getFacade().getSysUserInfoService().generateUserInfo(queryUser, path);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("医院用户信息.xls","UTF-8"));
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

    /**
     * 上传医院用户信息Excel
     * @param request
     * @param userInfo
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> uploadHospitalUserTemplate(HttpServletRequest request,SysUserInfo userInfo,
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
                super.getFacade().getSysUserInfoService().createHospitalUserInfo(userList,userInfo);
                newFile.delete();
                result.put("status", "success");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是："+e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }

    /**
     * 删除医院用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> deleteHospitalUser(SysUserInfo userInfo){
        userInfo.setStatus(Constants.PMIS_STATUS_UNUSE);
        super.getFacade().getSysUserInfoService().modifySysUserInfo(userInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
}
