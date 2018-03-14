package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @RequestMapping(value = "/list.do")
    @ResponseBody
    @ILog
    public Map<String, Object> queryHospitalUserInfo(Row row) {

       /* SysUserInfo user = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        long c_id = (long) user.getMap().get("C_ID");*/
        long c_id = 9879L;
        SysUserInfo queryUser = new SysUserInfo();
        queryUser.setRow(row);
        queryUser.setSsgs(c_id);
        queryUser.setUserType(Constants.User.USER_TYPE_HOSPITAL);
        List<SysUserInfo> queryUserList = super.getFacade().getSysUserInfoService().getSysUserInfoPaginatedList(queryUser);
        int total = super.getFacade().getSysUserInfoService().getSysUserInfoCount(queryUser);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", queryUserList);
        return result;

    }

    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addOrModifyHospitalUserInfo(SysUserInfo userInfo) {
        userInfo.setUserType(Constants.User.USER_TYPE_HOSPITAL);
        userInfo.setPassword(MD5.stringMD5(userInfo.getUserid()));
        userInfo.setStatus(Constants.PMIS_STATUS_USE);
        userInfo.setSsgs(9879L);
        if (userInfo.getId() == 0L) {
            userInfo.setId(ssgjHelper.createUserId());
            super.getFacade().getSysUserInfoService().createSysUserInfo(userInfo);
        } else {
            super.getFacade().getSysUserInfoService().modifySysUserInfo(userInfo);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/exportExcel.do")
    public HttpServletResponse wiriteExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long c_id = 9879L;
        SysUserInfo queryUser = new SysUserInfo();
        queryUser.setSsgs(c_id);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
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
}
