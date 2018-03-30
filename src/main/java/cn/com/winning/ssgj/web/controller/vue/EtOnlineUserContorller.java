package cn.com.winning.ssgj.web.controller.vue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
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
public class EtOnlineUserContorller extends BaseController{
	    @Autowired
	    private SSGJHelper ssgjHelper;

	    @RequestMapping(value = "/list.do")
	    @ResponseBody
	    public Map<String, Object> rtOnlineUserList(Row row) {
	    	System.err.println("上线人员信息。。。。。。。。。。。。。。。。。。。。。");
	    	EtOnlineUser etOnlineUser = new EtOnlineUser();
	    	etOnlineUser.setRow(row);
	        List<EtOnlineUser> etOnlineUserList = super.getFacade().getEtOnlineUserService().getEtOnlineUserPaginatedList(etOnlineUser);
	        int total = super.getFacade().getEtOnlineUserService().getEtOnlineUserCount(etOnlineUser);
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("total", total);
	        result.put("status", Constants.SUCCESS);
	        result.put("rows", etOnlineUserList);
	        return result;
	    }

	    @RequestMapping(value = "/addOrModify.do")
	    @ResponseBody
	    @ILog
	    public Map<String, Object> addOrModifyHospitalUserInfo(EtOnlineUser etOnlineUser) {
	        //userInfo.setUserType(Constants.User.USER_TYPE_HOSPITAL);
	        //userInfo.setPassword(MD5.stringMD5(userInfo.getUserid()));
	       // userInfo.setSsgs(9879L);
	    	etOnlineUser.setStatus(Constants.STATUS_USE);
	        if (etOnlineUser.getId() == 0L) {
	        	etOnlineUser.setId(ssgjHelper.createEtOnlineInfoIdService());
	            super.getFacade().getEtOnlineUserService().createEtOnlineUser(etOnlineUser);
	        } else {
	            super.getFacade().getEtOnlineUserService().modifyEtOnlineUser(etOnlineUser);
	        }
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", Constants.SUCCESS);
	        return result;
	    }

	    @RequestMapping(value = "/exportExcel.do")
	    @ILog
	    public HttpServletResponse wiriteExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        EtOnlineUser etOnlineUser = new EtOnlineUser();
	        etOnlineUser.setStatus(Constants.STATUS_USE);
	        String fileName = "etonlineuser.xls";
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
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String("HospitalUserInfo.xls".getBytes()));
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
	    public Map<String, Object> uploadHospitalUserTemplate(HttpServletRequest request,
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
	                super.getFacade().getEtOnlineUserService().createEtOnlineUserList(userList);
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


	    @RequestMapping(value = "/delete.do")
	    @ResponseBody
	    @ILog
	    public Map<String, Object> deleteEtOnlineUser(EtOnlineUser etOnlineUser){
	    	etOnlineUser.setStatus(Constants.STATUS_UNUSE);
	        super.getFacade().getEtOnlineUserService().modifyEtOnlineUser(etOnlineUser);
	        Map<String,Object> result = new HashMap<String,Object>();
	        result.put("status", Constants.SUCCESS);
	        return result;

	    }

}
