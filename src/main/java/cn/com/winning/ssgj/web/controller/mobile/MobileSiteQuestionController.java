package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.CommonFtpUtils;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.*;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @title 微信服务号问题采集中心
 * @package cn.com.winning.ssgj.web.controller.mobile
 * @date 2018-06-09 13:57
 */
@Controller
@CrossOrigin
@RequestMapping("/mobile/wechatSiteQuestion")
public class MobileSiteQuestionController extends BaseController {


    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 采集中心新增/修改问题客户问题
     *
     * @param model    model  主要用来传输参数
     * @param userId   用户id
     * @param serialNo 客户号
     * @return
     */
    @RequestMapping(value = "/addPage.do")
    public String add(Model model, Long questionId, Long userId, String serialNo, String source) {
        if (questionId != null) {
            EtSiteQuestionInfo info = new EtSiteQuestionInfo();
            info.setId(questionId);
            info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
            if (StringUtils.isNotBlank(info.getImgPath())) {
                String[] imgs = info.getImgPath().split(";");
                List<String> lists = Arrays.asList(imgs);
                info.setImgs(lists);
            }
            model.addAttribute("siteQuestionInfo", info);
        } else {
            model.addAttribute("siteQuestionInfo", null);
        }
        EtUserHospitalLog log = new EtUserHospitalLog();
        log.setSerialNo(serialNo);
        log.setOperator(userId);
        log.setSourceType(1);
        model.addAttribute("logInfo", getFacade().getEtUserHospitalLogService().getEtUserHospitalLog(log));
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        model.addAttribute("source", source);
        return "mobile2/wechat/site-question";
    }

    /**
     * 显示当前操作人的全部问题 微信号
     *
     * @param model    主要用来传输参数
     * @param userId   用户id
     * @param serialNo 客户号
     * @return
     */
    @RequestMapping(value = "/list.do")
    public String list(Model model, long userId, String serialNo, String searchText) throws ParseException {
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setCreator(userId);
        info.setSerialNo(serialNo);
        //只查询 新建,已分配，待处理，打回
        info.getMap().put("processStatus", "1,2,3,6");
        info.getMap().put("search_text", searchText);
        model.addAttribute("questionList", getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(info));
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        model.addAttribute("search_text", searchText);
        return "mobile2/wechat/site-question-list";
    }

    /**
     * 打开科室或者病区选择页面
     *
     * @param model
     * @param userId     用户ID
     * @param serialNo   客户好
     * @param questionId 问题ID
     * @param type       类型 1 科室病区 2系统
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/openDept.do")
    public String openDept(Model model, Long userId, String serialNo, Long questionId, String type,
                           String siteName, String productName,Long logId,
                           String menuName, String questionDesc, String priority, String source) throws ParseException {
        EtDepartment dept = new EtDepartment();
        dept.setSerialNo(Long.parseLong(serialNo));
        EtUserHospitalLog log = new EtUserHospitalLog();
        log.setId(logId);
        log.setSerialNo(serialNo);
        log.setSiteName("0".equals(siteName)? null : siteName);
        log.setSourceType(1);
        log.setProductName("0".equals(productName)? null : productName);
        log.setOperator(userId);
        model.addAttribute("questionId", questionId);
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        model.addAttribute("type", type);
        model.addAttribute("source", source);
        model.addAttribute("menuName", menuName);
        model.addAttribute("questionDesc", questionDesc);
        model.addAttribute("priority", priority);
        model.addAttribute("logInfo", getFacade().getEtUserHospitalLogService().getEtUserHospitalLog(log));
        if ("1".equals(type)) {
            model.addAttribute("title", "科室病区");
            model.addAttribute("firstInit", getFacade().getEtDepartmentService().getEtDepartmentFirstInitCode(dept));
            model.addAttribute("depts", getFacade().getEtDepartmentService().getWechatDepartmentData(dept));
        } else {
            model.addAttribute("title", "系统名称");
            model.addAttribute("firstInit", getFacade().getEtContractTaskService().getEtContractTaskFirstInitCode(serialNo));
            model.addAttribute("depts", getFacade().getEtContractTaskService().getWechatContractTaskData(serialNo));
        }

        return "mobile2/wechat/site-department";
    }

    /**
     * 修改科室名称和系统名称
     *
     * @param model
     * @param questionId 问题ID
     * @param userId     用户ID
     * @param serialNo   客户号
     * @param type       类型 1 科室病区 2系统
     * @return
     */
    @RequestMapping(value = "/changeDept.do")
    public String changeDept(Model model, String questionId, Long userId, String serialNo, String type,
                             Long logId,String source, String menuName, String questionDesc, String priority) {
        if (StringUtils.isNotBlank(questionId)) {
            EtSiteQuestionInfo info = new EtSiteQuestionInfo();
            info.setId(Long.parseLong(questionId));
            info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
            if (StringUtils.isNotBlank(info.getImgPath())) {
                String[] imgs = info.getImgPath().split(";");
                List<String> lists = Arrays.asList(imgs);
                info.setImgs(lists);
            }
            model.addAttribute("siteQuestionInfo", info);
        } else {
            model.addAttribute("siteQuestionInfo", null);
        }
        EtUserHospitalLog log = new EtUserHospitalLog();
        log.setId(logId);
        model.addAttribute("logInfo", getFacade().getEtUserHospitalLogService().getEtUserHospitalLog(log));
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        model.addAttribute("source", source);
        model.addAttribute("menuName", menuName);
        model.addAttribute("questionDesc", questionDesc);
        model.addAttribute("priority", priority);
        return "mobile2/wechat/site-question";
    }

    /**
     * 文件上传 ajax
     *
     * @param request
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/saveAndUpdate.do", method = RequestMethod.POST)
    @ResponseBody
    @ILog
    public Map<String, Object> saveAndUpdate(HttpServletRequest request, @RequestParam MultipartFile uploadFile) {
        Map<String, Object> map = new HashMap<String, Object>();
        String serialNo = request.getParameter("serialNo");
        String userId = request.getParameter("userId");
        String old_id = request.getParameter("old_id");

        try {
            if (!uploadFile.isEmpty()) {
                String pathLu = Constants.UPLOAD_PC_PREFIX + serialNo + "/site/";
                String path = request.getServletContext().getRealPath(pathLu);
                //上传文件名
                String filename = uploadFile.getOriginalFilename();
                filename = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(filename, ".");
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
                uploadFile.transferTo(newFile);
                String remotePath = pathLu + filename;
                String remoteDir = pathLu;
                boolean ftpStatus = false;
                String msg = "";
                ftpStatus = CommonFtpUtils.uploadFile(remotePath, newFile);
                EtSiteQuestionInfo info = new EtSiteQuestionInfo();
                if (StringUtils.isNotBlank(old_id)) {
                    info.setId(Long.parseLong(old_id));
                    info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
                    if (StringUtils.isNotBlank(info.getImgPath())) {
                        info.setImgPath(info.getImgPath() + ";" + remotePath);//拼接图片路径
                    } else {
                        info.setImgPath(remotePath);
                    }
                    info.setOperator(Long.parseLong(userId));
                    info.setOperatorTime(new Timestamp(new Date().getTime()));
                    addEtLog(serialNo, "ET_SITE_QUESTION_INFO", info.getId(), "内容变更:上传图片", 1, Long.parseLong(userId));
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    map.put("id", String.valueOf(old_id));
                    map.put("status", "1");
                } else {  //新增一个对象
                    if (ftpStatus) {
                        //生成系统预设 ID
                        long id = ssgjHelper.createSiteQuestionIdService();
                        info.setId(id); //站点问题ID
                        info.setCId((long) -2); //11980游客
                        info.setPmId((long) -2);//11980游客
                        info.setSerialNo(serialNo);
                        info.setImgPath(remotePath);
                        info.setPriority(3);
                        info.setCreator(Long.parseLong(userId));
                        info.setCreateTime(new Timestamp(new Date().getTime()));
                        info.setOperator(Long.parseLong(userId));
                        info.setOperatorTime(new Timestamp(new Date().getTime()));
                        addEtLog(serialNo, "ET_SITE_QUESTION_INFO", info.getId(), "创建问题:上传图片", 1, Long.parseLong(userId));
                        super.getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(info);
                        map.put("status", "1");
                        map.put("id", String.valueOf(id));

                    } else if (!StringUtil.isEmptyOrNull(msg)) {
                        map.put("status", "0");
                    }
                }

                map.put("path", remotePath);
            } else {
                map.put("status", "0");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "0");
        }
        return map;
    }

    /**
     * 问题新增或者修改
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/addOrUpdate.do", method = RequestMethod.POST)
    @ResponseBody
    @ILog
    public Map<String, Object> addOrUpdate(EtSiteQuestionInfo info) {
        if (info.getId() == null) {
            long id = ssgjHelper.createSiteQuestionIdService();
            info.setId(id);
            if (info.getPriority() == null) {
                info.setPriority(3);
            }
            info.setCId((long) -2); //11980游客
            info.setPmId((long) -2);//11980游客
            info.setCreateTime(new Timestamp(new Date().getTime()));
            info.setOperator(info.getCreator());
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            addEtLog(info.getSerialNo(), "ET_SITE_QUESTION_INFO", info.getId(), "内容创建:新建问题", 1, info.getCreator());
            EtStartEnd end = new EtStartEnd();
            end.setSerialNo(info.getSerialNo());
            end = super.getFacade().getEtStartEndService().getEtStartEnd(end);
            if (end != null) { //判断是否自动分配
                EtContractTask task = this.getAllocateUser(info.getSerialNo(), Long.parseLong(info.getProductName()));
                addEtLog(info.getSerialNo(), "ET_SITE_QUESTION_INFO", info.getId(), "问题分配:自动分配给{" + task.getAllocateUser() + "}", 1, info.getCreator());
                info.setAllocateUser(task.getAllocateUser());
                info.setProcessStatus(Constants.ACCEPTED_UNTREATED); //默认将状态分配到接收待处理
                info.setHopeFinishDate(getHopeFinishDateByPriority(info.getPriority()));
            }
            getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(info);
        } else {
            info.setOperator(info.getCreator());
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            info.setCreator(null);
            EtStartEnd end = new EtStartEnd();
            end.setSerialNo(info.getSerialNo());
            end = super.getFacade().getEtStartEndService().getEtStartEnd(end);
            if (end != null) { //判断是否自动分配
                EtContractTask task = this.getAllocateUser(info.getSerialNo(), Long.parseLong(info.getProductName()));
                addEtLog(info.getSerialNo(), "ET_SITE_QUESTION_INFO", info.getId(), "问题分配:自动分配给{" + task.getAllocateUser() + "}", 1, info.getCreator());
                info.setAllocateUser(task.getAllocateUser());
                info.setProcessStatus(Constants.ACCEPTED_UNTREATED); //默认将状态分配到接收待处理
                info.setHopeFinishDate(getHopeFinishDateByPriority(info.getPriority()));
            }
            addEtLog(info.getSerialNo(), "ET_SITE_QUESTION_INFO", info.getId(), "内容变更:问题修改", 1, info.getCreator());
            getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 图片删除
     *
     * @param id      问题ID
     * @param imgPath 问题路径
     * @return
     */
    @RequestMapping("/deleteImg.do")
    @ResponseBody
    @ILog
    public Map<String, Boolean> deleteImg(Long id, String imgPath, String serialNo, Long userId) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setId(id);
        try {
            info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
            CommonFtpUtils.removeUploadFile(imgPath);
            if (StringUtils.isBlank(info.getSiteName())) {
                String[] imgs = info.getImgPath().split(";");
                String str = "";
                if (imgs.length > 1) {
                    for (int i = 0; i < imgs.length; i++) {
                        if (imgPath.equals(imgs[i])) {

                        } else {
                            str += imgs[i] + ";";
                        }
                    }
                    info.setImgPath(str.substring(0, str.length() - 1));
                    addEtLog(serialNo, "ET_SITE_QUESTION_INFO", id, "内容变更:删除上传图片", 1, userId);
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                } else {
                    addEtLog(serialNo, "ET_SITE_QUESTION_INFO", id, "内容变更:问题删除", 1, userId);
                    super.getFacade().getEtSiteQuestionInfoService().removeEtSiteQuestionInfo(info);
                }
            } else {
                String[] imgs = info.getImgPath().split(";");
                String str = "";
                if (imgs.length > 1) {
                    for (int i = 0; i < imgs.length; i++) {
                        if (imgPath.equals(imgs[i])) {

                        } else {
                            str += imgs[i] + ";";
                        }
                    }
                    info.setImgPath(str.substring(0, str.length() - 1));
                } else {
                    info.setImgPath("");
                }
                addEtLog(serialNo, "ET_SITE_QUESTION_INFO", id, "内容变更:删除上传图片", 1, userId);
                super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
            }
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
        }
        return map;
    }

    /**
     * 判断当前的问题信息是否可以删除
     *
     * @param info
     * @return
     */
    @RequestMapping("/checkQuestion.do")
    @ResponseBody
    @ILog
    public Map<String, Object> checkForDelete(EtSiteQuestionInfo info) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", getFacade().getEtSiteQuestionInfoService().checkEtSiteQuestionInfoStatus(info));
        return result;
    }

    /**
     * 检查问题的标题是否为空，为空则返回 0 反之返回 1
     *
     * @param info
     * @return
     */
    @RequestMapping("/checkQuestionStatus.do")
    @ResponseBody
    @ILog
    public Map<String, Object> checkQuestionStatus(EtSiteQuestionInfo info) {
        int status = super.getFacade().getEtSiteQuestionInfoService().checkQuestionStatus(info);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", status);
        return result;
    }

    /**
     * 校验当前客户是否是自动分配问题
     *
     * @param end
     * @return
     */
    @RequestMapping(value = "/checkIsAutomatic.do")
    @ResponseBody
    public Map<String, Object> checkIsAutomatic(EtStartEnd end) {
        end = super.getFacade().getEtStartEndService().getEtStartEnd(end);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("yesOrNo", end == null ? 0 : 1);
        return result;
    }

    /**
     * 问题删除
     *
     * @param info
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> deleteQuestion(EtSiteQuestionInfo info) {
        getFacade().getEtSiteQuestionInfoService().removeEtSiteQuestionInfo(info);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

//    企业微信号相关controller》》》》》》》》》》》》》》》》》》》start

    /**
     * 跳转编辑页面
     *
     * @param model
     * @param id
     * @param userId
     * @param serialNo
     * @return
     */
    @RequestMapping("/goView.do")
    public String goView(Model model, Long id, Long userId, String serialNo, Integer isManager, String status) {
        EtSiteQuestionInfo questionInfo = new EtSiteQuestionInfo();
        questionInfo.setId(id);
        questionInfo = getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(questionInfo);
        if (StringUtils.isNotBlank(questionInfo.getImgPath())) {
            String[] imgs = questionInfo.getImgPath().split(";");
            List<String> lists = Arrays.asList(imgs);
            questionInfo.setImgs(lists);
        }
        resultMap.put("questionInfo", questionInfo);
        resultMap.put("userId", userId);
        resultMap.put("serialNo", serialNo);
        resultMap.put("isManager", isManager);
        resultMap.put("status", status);
        model.addAllAttributes(resultMap);
        return "mobile2/enterprise/site-question-view";
    }

    /**
     * 跳转编辑页面
     *
     * @param model
     * @param id
     * @param userId
     * @param serialNo
     * @return
     */
    @RequestMapping("/goUpdate.do")
    public String goUpdate(Model model, Long id, Long userId, String serialNo) {
        EtSiteQuestionInfo questionInfo = new EtSiteQuestionInfo();
        questionInfo.setId(id);
        questionInfo = getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(questionInfo);
        if (StringUtils.isNotBlank(questionInfo.getImgPath())) {
            String[] imgs = questionInfo.getImgPath().split(";");
            List<String> lists = Arrays.asList(imgs);
            questionInfo.setImgs(lists);
        }
        resultMap.put("questionInfo", questionInfo);
        resultMap.put("userId", userId);
        resultMap.put("serialNo", serialNo);
        model.addAllAttributes(resultMap);
        return "mobile2/enterprise/site-question-edit";
    }

    /**
     * 跳转分配页面
     *
     * @param model
     * @param id
     * @param userId
     * @param serialNo
     * @return
     */
    @RequestMapping("/goDistribute.do")
    public String goDistribute(Model model, Long id, Long userId, String serialNo) {
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setSerialNo(serialNo);
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().selectEtSiteQuestionInfoUserTotalBySerialNo(info);
        //调出对应的信息
        EtSiteQuestionInfo info_old = new EtSiteQuestionInfo();
        info_old.setId(id);
        info_old = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info_old);


        //人员信息
        List<String> nameList = new ArrayList<String>();
        List<Integer> numList = new ArrayList<Integer>();
        if (infoList != null && infoList.size() > 0) {
            for (EtSiteQuestionInfo en : infoList) {
                nameList.add((String) en.getMap().get("c_name"));
                numList.add((Integer) en.getMap().get("num"));
            }
        }
        String jsonName = JSON.toJSONString(nameList);
        model.addAttribute("numList", numList);
        model.addAttribute("jsonName", jsonName);
        model.addAttribute("infoList", infoList);
        model.addAttribute("siteName", info_old.getSiteName());
        model.addAttribute("info_old", info_old);
        model.addAttribute("userId", userId);
        model.addAttribute("serialNo", serialNo);
        return "mobile2/enterprise/site-question-distribution";
    }


    /**
     * 跳转分配详情页面
     *
     * @param model
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping("/goDistributeDetail.do")
    public String goDistributeDetail(Model model, Long id, Long userId, String serialNo, Long allocateUser) {
        EtSiteQuestionInfo questionInfo = new EtSiteQuestionInfo();
        questionInfo.setId(id);
        questionInfo = getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(questionInfo);
        if (StringUtils.isNotBlank(questionInfo.getImgPath())) {
            String[] imgs = questionInfo.getImgPath().split(";");
            List<String> lists = Arrays.asList(imgs);
            questionInfo.setImgs(lists);
        }
        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setId(allocateUser);
        sysUserInfo = getFacade().getSysUserInfoService().getSysUserInfo(sysUserInfo);
        resultMap.put("questionInfo", questionInfo);
        resultMap.put("userId", userId);
        resultMap.put("serialNo", serialNo);
        resultMap.put("sysUserInfo", sysUserInfo);
        model.addAllAttributes(resultMap);
        return "mobile2/enterprise/site-question-distribution-detail";
    }


    /**
     * 保存分配
     *
     * @param model
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping("/saveDistribution.do")
    @ResponseBody
    public Map saveDistribution(Model model, Long id, Long userId, String serialNo, Long allocateUser, String hopeDate) {
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        info.setId(id);
        info.setAllocateUser(allocateUser);
        info.setHopeFinishDate(hopeDate);
        int isManager = getPosition(serialNo, allocateUser);
        if (isManager == 0) {
            //接受人权限为项目经理默认直接接受（未处理）
            info.setProcessStatus(Constants.ACCEPTED_UNTREATED);
        } else {
            info.setProcessStatus(Constants.ALLOCATED_UNACCEPTED);
        }
        try {
            super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
            resultMap.put("status", true);
        } catch (Exception e) {
            resultMap.put("status", false);
        }
        return resultMap;
    }

    /**
     * 打回
     *
     * @param model
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public Map changeStatus(Model model, Long id, Long userId, String serialNo, String suggest, String solutionResult, Integer option) {
        try {
            EtSiteQuestionInfo info = new EtSiteQuestionInfo();
            info.setId(id);
            switch (option) {
                case 2:
                    //已分配，待接受
                    info.setProcessStatus(Constants.ALLOCATED_UNACCEPTED);
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    break;
                case 3:
                    //已接收任务，未处理
                    info.setProcessStatus(Constants.ACCEPTED_UNTREATED);
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    break;
                case 4:
                    //已处理
                    info.setSolutionResult(solutionResult);
                    info.setProcessStatus(Constants.TREATED_COMPLETE);
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    break;
                case 5:
                    //院方确认完成
                    info.setProcessStatus(Constants.CONFIRM_END);
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    break;
                case 6:
                    //院方打回
                    info.setProcessStatus(Constants.REFUSE);
                    super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
                    break;
                case 7:
                    //工程师拒绝接受或打回
                    info.setSuggest(suggest);
                    info.setHopeFinishDate(null);
                    info.setAllocateUser(null);
                    info.setProcessStatus(Constants.ENGINEER_REFUSE);
                    super.getFacade().getEtSiteQuestionInfoService().updateProcessStatus(info);
                    break;
                default:
                    break;
            }
            resultMap.put("status", true);
        } catch (Exception e) {
            resultMap.put("status", false);
        }
        return resultMap;
    }

    //    企业微信号相关controller》》》》》》》》》》》》》》》》》》》end


    /**
     * 按照优先级获取期望完成时间
     * @param priority
     * @return
     */
    private String getHopeFinishDateByPriority(int priority){
        String endDate = "";
        switch (priority){
            case 1 :
                endDate = DateUtil.plusDay(0);
                break;
            case 2 :
                endDate = DateUtil.plusDay(2);
                break;
            case 3 :
                endDate = DateUtil.plusDay(6);
                break;
            case 4 :
                endDate = DateUtil.plusDay(14);
                break;
            default:
                endDate = DateUtil.plusDay(6);
        }
        return endDate;
    }

}
