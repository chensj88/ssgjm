package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtOnlineFile;
import cn.com.winning.ssgj.domain.EtSimulateRecord;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.UrlContent;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
 * @title 上线可行性报告和上线切换报告
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-17 21:23
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/onLineReport")
public class EtOnlineFileController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 初始化数据
     * @param file
     * @return
     */
    @RequestMapping(value = "/initData.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> queryOnlineInfo(EtOnlineFile file){
        long pmId = file.getPmId();
        long cId = file.getcId();
        String serialNo = file.getSerialNo();
        Map<String,List> data = super.getFacade().getCommonQueryService().queryCompletionOfProject(pmId);
        //移动端只存在客户信息
        file.setPmId(null);
        file.setcId(null);
        file.setFileType(Constants.REPORT_TYPE_ONLINE_FILE);
        file.setStatus(Constants.STATUS_USE);
        List<UrlContent> fileList = super.getFacade().getEtOnlineFileService().getUrlContentFromEtOnlineFileList(file);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("completeData",(List<Integer>)data.get("success"));
        result.put("failData",(List<Integer>)data.get("handle"));
        result.put("itemData",(List<String>)data.get("item"));
        result.put("data",fileList);
        return result;
    }

    /**
     * 重新查询数据
     * @param file
     * @return
     */
    @RequestMapping(value = "/refreshFile.do")
    @ResponseBody
    public Map<String,Object> refreshUploadFile(EtOnlineFile file){
        file.setPmId(null);
        file.setcId(null);
        file.setFileType(Constants.REPORT_TYPE_ONLINE_FILE);
        file.setStatus(Constants.STATUS_USE);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",super.getFacade().getEtOnlineFileService().getUrlContentFromEtOnlineFileList(file));
        return result;
    }


    /**
     * 删除上线评估报告
     * @param file
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String,Object> deleteOnlineFile(EtOnlineFile file){
        file = super.getFacade().getEtOnlineFileService().getEtOnlineFile(file);
        String source = file.getImgPath();
        CommonFtpUtils.removeUploadFile(source);
        file.setImgPath(null);
        file.setStatus(Constants.STATUS_UNUSE);
        super.getFacade().getEtOnlineFileService().modifyEtOnlineFile(file);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 上线评估报告
     * @param request
     * @param onlineFile
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> uploadOnline(HttpServletRequest request, EtOnlineFile onlineFile, MultipartFile file) throws IOException {
        long operator = onlineFile.getOperator();
        String prefix = "";
        if(Constants.REPORT_TYPE_ONLINE_FILE.equals(onlineFile.getFileType())){
            prefix = "online";
        }else if(Constants.REPORT_TYPE_ONLINE_SWITCH_IMG_FILE.equals(onlineFile.getFileType())){
            prefix = "switch_image";
        }else if(Constants.REPORT_TYPE_ONLINE_SWITCH_FILE.equals(onlineFile.getFileType())){
            prefix = "switch";
        }else{
            prefix = "common";
        }
        String parentFile = prefix+"_"+System.currentTimeMillis();
        Map<String, Object> result = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String remotePath = "/"+prefix+"/" + parentFile + "/" + filename;
            String msg = "";
            boolean ftpStatus =commonUploadInfo(request,msg,remotePath,file);
            if (ftpStatus) {
                onlineFile.setId(ssgjHelper.createEtOnlineFileIdService());
                onlineFile.setImgPath(remotePath);
                onlineFile.setStatus(Constants.STATUS_USE);
                onlineFile.setDataType("1");
                onlineFile.setDataName(filename.substring(0,filename.lastIndexOf(".")));
                onlineFile.setCreator(operator);
                onlineFile.setCreateTime(new Timestamp(new Date().getTime()));
                onlineFile.setOperator(operator);
                onlineFile.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtOnlineFileService().createEtOnlineFile(onlineFile);
                result.put("status", "success");
            } else if (!StringUtil.isEmptyOrNull(msg)) {
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是：" + msg);
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;

    }

    /**
     * 切换报告和图片
     * @param onlineFile
     * @return
     */
    @RequestMapping(value = "/initSwitchData.do")
    @ResponseBody
    public Map<String,Object> initSwitchData(EtOnlineFile onlineFile){
        onlineFile.setStatus(Constants.STATUS_USE);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        onlineFile.setFileType(Constants.REPORT_TYPE_ONLINE_SWITCH_IMG_FILE);
        result.put("switchImgFile",super.getFacade().getEtOnlineFileService().getUrlContentFromEtOnlineFileList(onlineFile));
        onlineFile.setFileType(Constants.REPORT_TYPE_ONLINE_SWITCH_FILE);
        result.put("switchFile",super.getFacade().getEtOnlineFileService().getUrlContentFromEtOnlineFileList(onlineFile));
        return result;
    }
    private boolean  commonUploadInfo(HttpServletRequest request,String msg,String remotePath,MultipartFile file) throws IOException {
        boolean ftpStatus = false;
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
            ftpStatus = CommonFtpUtils.uploadFile(remotePath,newFile);
        }catch (IOException e){
            msg = e.getMessage();
            ftpStatus = false;
        }
        newFile.delete();
        return ftpStatus;
    }

}
