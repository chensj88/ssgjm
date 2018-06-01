package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.EtSimulateRecord;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
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
import java.util.*;

/**
 * @author chenshijie
 * @title 模拟运行Controller
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-08 17:02
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/simulate")
public class EtSimulateRecordController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;
    /**
     * 获取全部的模拟运行记录
     * @param record
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryEtSimulateRecord(EtSimulateRecord record){
        record.setStatus(Constants.STATUS_USE);
        List<EtSimulateRecord> simulateRecords = super.getFacade().getEtSimulateRecordService().getEtSimulateRecordList(record);
        Map<String,Object> result = new HashMap<>();
        result.put("total", simulateRecords.size());
        result.put("status", Constants.SUCCESS);
        result.put("rows", simulateRecords);
        return result;
    }

    /**
     * 新增或者修改模拟运行记录
     * 第一次添加模拟运行记录信息，默认该项工作完成
     * @param record
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> addEtSimulateRecord(EtSimulateRecord record){
        EtProcessManager manager = new EtProcessManager();
        manager.setPmId(record.getPmId());
        if(record.getId() != null){
            record.setStatus(Constants.STATUS_USE);
            record.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSimulateRecordService().modifyEtSimulateRecord(record);
            manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
            manager.setIsSimulation(Constants.STATUS_USE);
            manager.setCreator(record.getOperator());
            manager.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtProcessManagerService().modifyEtProcessManager(manager);
        }else{
            record.setId(ssgjHelper.createEtSimulateRecordIdService());
            record.setStatus(Constants.STATUS_USE);
            record.setCreator(record.getOperator());
            record.setCreateTime(new Timestamp(new Date().getTime()));
            record.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSimulateRecordService().createEtSimulateRecord(record);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
    /**
     * 删除模拟运行记录 只是修改状态
     * @param record
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> deleteEtSimulateRecord(EtSimulateRecord record){
        record.setStatus(Constants.STATUS_UNUSE);
        record.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSimulateRecordService().modifyEtSimulateRecord(record);
        Map<String,Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 模拟运行任务完成
     * @param record
     * @return
     */
    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> confirmEtSimulateRecord(EtSimulateRecord record){
        EtProcessManager processManager = new EtProcessManager();
        processManager.setPmId(record.getPmId());
        processManager = super.getFacade().getEtProcessManagerService().getEtProcessManager(processManager);
        processManager.setIsSimulation(Constants.STATUS_USE);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(processManager);
        Map<String,Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 前端上传文件到后台
     * @param request
     * @param record
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public Map<String,Object> uploadEtSimulateRecord(HttpServletRequest request,EtSimulateRecord record,MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/temp/");
            //上传文件名
            // String filename = FileUtis.generateFileName(file.getOriginalFilename());
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
            String remotePath = Constants.UPLOAD_PC_PREFIX+record.getPmId()+"/simulate/" + DateUtil.getTimstamp() + "/" + filename;
            String url = Constants.FTP_SHARE_FLODER + remotePath;
            SysUserInfo user = super.getFacade().getSysUserInfoService().getSysUserInfoById(record.getOperator());
            UrlContent urlContent = new UrlContent(ssgjHelper.createUrlContentIdService(),record.getId(),filename,url,user.getName(),DateUtil.getCurrentDayByFormatter());
            boolean ftpStatus;
            String msg = "";
            try {
                ftpStatus = CommonFtpUtils.uploadFile(remotePath,newFile);
            }catch (IOException e){
                msg = e.getMessage();
                ftpStatus = false;
            }
            JSONArray jsonArray = new JSONArray();
            if (ftpStatus) {
                record = super.getFacade().getEtSimulateRecordService().getEtSimulateRecord(record);
                String  filePath = record.getFilePath();
                //判断是否已经传输了文件过来，如果传输了，就增加信息的，没有的话就添加
                if(StringUtil.isEmptyOrNull(filePath) ){
                    jsonArray.add(urlContent);
                    record.setFilePath(jsonArray.toJSONString());
                }else{
                    List<UrlContent> array = JSONArray.parseArray(filePath,UrlContent.class);
                    array.add(urlContent);
                    record.setFilePath(JSON.toJSONString(array));
                }
                super.getFacade().getEtSimulateRecordService().modifyEtSimulateRecord(record);
                newFile.delete();
                result.put("status", "success");
            } else if (!StringUtil.isEmptyOrNull(msg)) {
                newFile.delete();
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
     * 前端删除文件处理
     * @param content
     * @return
     */
    @RequestMapping(value = "/deleteFile.do")
    @ResponseBody
    @Transactional
    public Map<String,Object> deleteUploadFile(UrlContent content){
        EtSimulateRecord record = new EtSimulateRecord();
        record.setId(content.getSourceId());
        record = super.getFacade().getEtSimulateRecordService().getEtSimulateRecord(record);
        List<UrlContent> array = JSONArray.parseArray(record.getFilePath(),UrlContent.class);
        for (int i=0;i<array.size();i++) {
            if(content.getId() == array.get(i).getId()){
                content = array.get(i);
                array.remove(i);
            }
        }
        String source = content.getUrl().substring(Constants.FTP_SHARE_FLODER.length());
        CommonFtpUtils.removeUploadFile(source);
        record.setFilePath(JSON.toJSONString(array));
        super.getFacade().getEtSimulateRecordService().modifyEtSimulateRecord(record);
        Map<String,Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

}
