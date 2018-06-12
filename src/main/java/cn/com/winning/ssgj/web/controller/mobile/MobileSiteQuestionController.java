package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.FtpUtils;
import cn.com.winning.ssgj.base.util.SFtpUtils;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title 微信服务号问题采集中心
 * @package cn.com.winning.ssgj.web.controller.mobile
 * @date 2018-06-09 13:57
 */
@Controller
@CrossOrigin
@RequestMapping("/mobile/wechatSiteQuestion")
public class MobileSiteQuestionController  extends BaseController {


    @Autowired
    private SSGJHelper ssgjHelper;
    /**
     * 采集中心新增/修改问题客户问题
     * @param model model  主要用来传输参数
     * @param userId 用户id
     * @param serialNo 客户号
     * @return
     */
    @RequestMapping(value = "/addPage.do")
    public String add(Model model,Long questionId,Long userId,String serialNo) {
        if(questionId != null){
            EtSiteQuestionInfo info = new EtSiteQuestionInfo();
            info.setId(questionId);
            model.addAttribute("siteQuestionInfo",this.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info));
        }else{
            model.addAttribute("siteQuestionInfo",null);
        }

        model.addAttribute("deptList", this.getDepartmentList(Long.parseLong(serialNo),null));
        model.addAttribute("appList", this.getProductDictInfo(serialNo));
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        return "mobile2/wechat/site-question";
    }

    /**
     * 显示当前操作人的全部问题
     * @param model  主要用来传输参数
     * @param userId 用户id
     * @param serialNo 客户号
     * @return
     */
    @RequestMapping(value = "/list.do")
    public String list(Model model,long userId,String serialNo) throws ParseException {
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setCreator(userId);
        info.setSerialNo(serialNo);
        model.addAttribute("questionList", getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(info));
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        return "mobile2/wechat/site-question-list";
    }

    @RequestMapping(value = "/openDept.do")
    public String openDept(Model model,Long userId,String serialNo,Long questionId,String type) throws ParseException {
        EtDepartment dept = new EtDepartment();
        dept.setSerialNo(Long.parseLong(serialNo));
        model.addAttribute("questionId", questionId);
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        model.addAttribute("type",type);
        if("1".equals(type)){
            model.addAttribute("title","科室病区");
            model.addAttribute("firstInit", getFacade().getEtDepartmentService().getEtDepartmentFirstInitCode(dept));
            model.addAttribute("depts", getFacade().getEtDepartmentService().getWechatDepartmentData(dept));
        }else {
            model.addAttribute("title","系统名称");
            model.addAttribute("firstInit", getFacade().getEtContractTaskService().getEtContractTaskFirstInitCode(serialNo));
            model.addAttribute("depts", getFacade().getEtContractTaskService().getWechatContractTaskData(serialNo));
        }

        return "mobile2/wechat/site-department";
    }

    @RequestMapping(value = "/changeDept.do")
    public String changeDept(Model model,String questionId,Long userId,String serialNo,String type,String siteName) {
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        if(StringUtils.isNotBlank(questionId)) {
            info.setId(Long.parseLong(questionId));
            info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
            if("1".equals(type)){
                info.setSiteName(siteName);
            }else {
                info.setProductName(siteName);
            }
            info.setOperator(userId);
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        }else{
            //生成系统预设 ID
            long id = ssgjHelper.createSiteQuestionIdService();
            questionId = id+"";
            info.setId(id); //站点问题ID
            info.setCId((long)-2); //11980游客
            info.setPmId((long)-2);//11980游客
            info.setSerialNo(serialNo);
            if("1".equals(type)){
                info.setSiteName(siteName);
            }else {
                info.setProductName(siteName);
            }
            info.setCreator(userId);
            info.setCreateTime(new Timestamp(new Date().getTime()));
            info.setOperator(userId);
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(info);
        }
        info = new EtSiteQuestionInfo();
        info.setId(Long.parseLong(questionId));
        model.addAttribute("siteQuestionInfo",this.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info));
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        return "mobile2/wechat/site-question";
    }

    /**
     * 上次文件
     * @param request
     * @param uploadFile
     * @return
     */
    @RequestMapping(value="/saveAndUpdate.do", method= RequestMethod.POST)
    @ResponseBody
    @ILog
    public Map<String,Object> saveAndUpdate(HttpServletRequest request, @RequestParam MultipartFile uploadFile) {
        Map<String,Object> map = new HashMap<String,Object>();
        String serialNo = request.getParameter("serialNo");
        String userId = request.getParameter("userId");
        String old_id = request.getParameter("old_id");

        try{
            if(!uploadFile.isEmpty()) {
                String pathLu= Constants.UPLOAD_PC_PREFIX+serialNo+"/site/";
                String path = request.getServletContext().getRealPath(pathLu);
                //上传文件名
                String filename = uploadFile.getOriginalFilename();
                filename = System.currentTimeMillis()+"."+ StringUtils.substringAfterLast(filename,".");
                File filepath = new File(path,filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                //将上传文件保存到一个目标文件当中
                File newFile = new File(path + File.separator + filename);
                if(newFile.exists()){
                    newFile.delete();
                }
                uploadFile.transferTo(newFile);
                String remotePath = pathLu+ filename;
                String remoteDir =pathLu ;
                boolean ftpStatus = false;
                String msg = "";
                ftpStatus = CommonFtpUtils.uploadFile(remotePath,newFile);
                EtSiteQuestionInfo info = new EtSiteQuestionInfo();
                if(StringUtils.isNotBlank(old_id)){
                    info.setId(Long.parseLong(old_id));
                    info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
                    if(StringUtils.isNotBlank(info.getImgPath())){
                        info.setImgPath(info.getImgPath()+";"+ remotePath);//拼接图片路径
                    }else{
                        info.setImgPath(remotePath);
                    }
                    info.setOperator(Long.parseLong(userId));
                    info.setOperatorTime(new Timestamp(new Date().getTime()));
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    map.put("id",String.valueOf(old_id));
                    map.put("status","1");
                }else{  //新增一个对象
                    if(ftpStatus){
                        //生成系统预设 ID
                        long id = ssgjHelper.createSiteQuestionIdService();
                        info.setId(id); //站点问题ID
                        info.setCId((long)-2); //11980游客
                        info.setPmId((long)-2);//11980游客
                        info.setSerialNo(serialNo);
                        info.setImgPath(remotePath);
                        info.setCreator(Long.parseLong(userId));
                        info.setCreateTime(new Timestamp(new Date().getTime()));
                        info.setOperator(Long.parseLong(userId));
                        info.setOperatorTime(new Timestamp(new Date().getTime()));
                        super.getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(info);
                        map.put("status","1");
                        map.put("id",String.valueOf(id));

                    }else if(!StringUtil.isEmptyOrNull(msg)){
                        map.put("status","0");
                    }
                }

                map.put("path",remotePath);
            }else {
                map.put("status","0");
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("status","0");
        }
        return map;
    }


    @RequestMapping(value="/addOrUpdate.do", method= RequestMethod.POST)
    @ResponseBody
    @ILog
    public Map<String,Object> addOrUpdate(EtSiteQuestionInfo info){
        if(info.getId() == null){
            long id = ssgjHelper.createSiteQuestionIdService();
            info.setId(id);
            info.setCreateTime(new Timestamp(new Date().getTime()));
            info.setOperator(info.getCreator());
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(info);
        }else {
            info.setOperator(info.getCreator());
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            info.setCreator(null);
            getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
}
