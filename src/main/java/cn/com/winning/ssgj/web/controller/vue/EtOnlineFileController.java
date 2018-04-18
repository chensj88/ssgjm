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
        List<EtOnlineFile> fileList = super.getFacade().getEtOnlineFileService().getEtOnlineFileList(file);
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
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",super.getFacade().getEtOnlineFileService().getEtOnlineFileList(file));
        return result;
    }


    /**
     * 删除上线评估报告
     * @param content
     * @return
     */
    @RequestMapping(value = "/deleteOnline.do")
    @ResponseBody
    @ILog
    public Map<String,Object> deleteOnlineFile(UrlContent content){
        EtOnlineFile file = new EtOnlineFile();
        file.setId(content.getSourceId());
        file = super.getFacade().getEtOnlineFileService().getEtOnlineFile(file);
        List<UrlContent> array = JSONArray.parseArray(file.getFileSuggestPath(),UrlContent.class);
        for (int i=0;i<array.size();i++) {
            if(content.getId() == array.get(i).getId()){
                content = array.get(i);
                array.remove(i);
            }
        }
        String source = content.getUrl().substring(Constants.FTP_SHARE_FLODER.length());
        CommonFtpUtils.removeUploadFile(source);
        file.setFileSuggestPath(JSON.toJSONString(array));
        super.getFacade().getEtOnlineFileService().modifyEtOnlineFile(file);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 删除切换报告
     * @param content
     * @return
     */
    @RequestMapping(value = "/deleteSwitch.do")
    @ResponseBody
    @ILog
    public Map<String,Object> deleteSwitchFile(UrlContent content){
        EtOnlineFile file = new EtOnlineFile();
        file.setId(content.getSourceId());
        file = super.getFacade().getEtOnlineFileService().getEtOnlineFile(file);
        List<UrlContent> array = JSONArray.parseArray(file.getFileChangePath(),UrlContent.class);
        for (int i=0;i<array.size();i++) {
            if(content.getId() == array.get(i).getId()){
                content = array.get(i);
                array.remove(i);
            }
        }
        String source = content.getUrl().substring(Constants.FTP_SHARE_FLODER.length());
        CommonFtpUtils.removeUploadFile(source);
        file.setFileChangePath(JSON.toJSONString(array));
        super.getFacade().getEtOnlineFileService().modifyEtOnlineFile(file);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 删除切换报告图片
     * @param content
     * @return
     */
    @RequestMapping(value = "/deleteSwitchImg.do")
    @ResponseBody
    @ILog
    public Map<String,Object> deleteSwitchImgFile(UrlContent content){
        EtOnlineFile file = new EtOnlineFile();
        file.setId(content.getSourceId());
        file = super.getFacade().getEtOnlineFileService().getEtOnlineFile(file);
        List<UrlContent> array = JSONArray.parseArray(file.getImgPath(),UrlContent.class);
        for (int i=0;i<array.size();i++) {
            if(content.getId() == array.get(i).getId()){
                content = array.get(i);
                array.remove(i);
            }
        }
        String source = content.getUrl().substring(Constants.FTP_SHARE_FLODER.length());
        CommonFtpUtils.removeUploadFile(source);
        file.setImgPath(JSON.toJSONString(array));
        super.getFacade().getEtOnlineFileService().modifyEtOnlineFile(file);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

}
