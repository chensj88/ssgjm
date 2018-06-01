package cn.com.winning.ssgj.web.controller.vue;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.domain.EtProcessManager;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.winning.ssgj.domain.EtCustomerDetail;
import cn.com.winning.ssgj.service.EtCustomerDetailService;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客户明细信息添加修改
 *
 * @author 胡万里
 * @date 2018-03-8 15:56:49
 **/
@CrossOrigin
@Controller
@RequestMapping("/vue/etCustomerDetail")
public class EtCustomerDetailContorller extends BaseController {
    @Resource
    private EtCustomerDetailService ser;

    /**
     * @author: 胡万里
     * @Description: 客户明细信息添加
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/add")
    @ResponseBody
    @Transactional
    public Map<String, Object> addEtCustomerDetail(EtCustomerDetail etCustomerDetail) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer isSucceed = super.getFacade().getEtCustomerDetailService().createEtCustomerDetail(etCustomerDetail);
        result.put("isSucceed", isSucceed);
        return result;
    }

    /**
     * @author: 胡万里
     * @Description: 客户明细信息修改
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/etCustomerDetailUpdate")
    @ResponseBody
    @Transactional
    public Map<String, Object> updateEtCustomerDetail(EtCustomerDetail etCustomerDetail) {
        Map<String, Object> result = new HashMap<String, Object>();
        EtCustomerDetail old = new EtCustomerDetail();
        old.setId(etCustomerDetail.getId());
        old = super.getFacade().getEtCustomerDetailService().getEtCustomerDetail(old);
        int isSucceed = -1;
        System.out.println(old);
        if(old != null){
            etCustomerDetail.setOperatorTime(new Timestamp(new Date().getTime()));
            isSucceed = super.getFacade().getEtCustomerDetailService().modifyEtCustomerDetail(etCustomerDetail);
        }else{
            etCustomerDetail.setCreator(etCustomerDetail.getOperator());
            etCustomerDetail.setCreateTime(new Timestamp(new Date().getTime()));
            etCustomerDetail.setOperatorTime(new Timestamp(new Date().getTime()));
            isSucceed = super.getFacade().getEtCustomerDetailService().createEtCustomerDetail(etCustomerDetail);
        }
        EtProcessManager processManager = new EtProcessManager();
        processManager.setPmId(etCustomerDetail.getId());
        processManager = super.getFacade().getEtProcessManagerService().getEtProcessManager(processManager);
        processManager.setIsImprove(Constants.STATUS_USE);
        processManager.setOperator(etCustomerDetail.getOperator());
        processManager.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(processManager);
        result.put("isSucceed", isSucceed);
        return result;
    }

    /**
     * @author: 胡万里
     * @Description: 客户明细信息
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/etCustomerDetailFindById")
    @ResponseBody
    public Map<String, Object> etCustomerDetailFindById(EtCustomerDetail etCustomerDetail) {
        Map<String, Object> result = new HashMap<String, Object>();
        EtCustomerDetail detail = super.getFacade().getEtCustomerDetailService().getMergeEtCustomerDetail(etCustomerDetail);
        result.put("result", detail);
        return result;
    }
    
   /*
    * @title  上传文件
    * @author chenshijie
    * @description   客户信息上传包含合同信息和实施清单信息
    * @date  2018/5/17 17:04
    */
   @RequestMapping( value = "/upload.do")
   @ResponseBody
   public Map<String, Object> upload(EtCustomerDetail detail, String type, HttpServletRequest request, MultipartFile file) throws IOException {
       Map<String,Object> result = new HashMap<String,Object>();
       detail = super.getFacade().getEtCustomerDetailService().getMergeEtCustomerDetail(detail);
       boolean ftpStatus = false;
       if(!file.isEmpty()){
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

           String remotePath = "";
               if("1".equals(type)){
                   remotePath = Constants.UPLOAD_PC_PREFIX+detail.getPmCode()+"/constract/" + DateUtil.getTimstamp() +"/"+filename;
                   detail.setConstractPath(remotePath);
               }else {
                   remotePath = Constants.UPLOAD_PC_PREFIX+detail.getPmCode()+"/task/" + DateUtil.getTimstamp() +"/"+filename;
                   detail.setTaskPath(remotePath);
               }

           try {
               CommonFtpUtils.uploadFile(remotePath,newFile);
               super.getFacade().getEtCustomerDetailService().modifyEtCustomerDetail(detail);
               newFile.delete();
               result.put("status", "success");
           } catch (Exception e) {
               e.printStackTrace();
               result.put("status", "error");
               result.put("msg", "上传文件失败,原因是："+e.getMessage());
           }
       }else {
           result.put("status", "error");
           result.put("msg", "上传文件失败,原因是：上传文件为空");
       }
       return result;
   }


   /*
    * @title  删除上传的文件
    * @author chenshijie
    * @description   删除上传的文件信息
    * @date  2018/5/17 17:14
    */
   @RequestMapping( value = "/deleteUploadFile.do")
   @ResponseBody
   public Map<String, Object> deleteUploadFile(EtCustomerDetail detail,String type) {
       detail = super.getFacade().getEtCustomerDetailService().getEtCustomerDetail(detail);
       String filePath = "";
       if("1".equals(type)){
           filePath = detail.getConstractPath();
           detail.setConstractPath("");
       }else {
           filePath = detail.getTaskPath();
           detail.setTaskPath("");
       }
       CommonFtpUtils.removeUploadFile(filePath);
       super.getFacade().getEtCustomerDetailService().modifyEtCustomerDetail(detail);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
   }
}
