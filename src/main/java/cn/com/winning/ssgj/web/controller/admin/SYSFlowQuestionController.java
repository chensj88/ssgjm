package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.domain.SysFlowQuestion;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 流程问卷信息
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-22 9:33
 */
@Controller
@RequestMapping(value = "/admin/fq")
public class SYSFlowQuestionController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;
    /**
     * 获取页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fqInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model){
        return "auth/module/flowQuestionInfo";
    }

    /**
     * 查询问题列表信息
     * @param question
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryFlowQuestionList(SysFlowQuestion question, Row row){
        question.setRow(row);
        List<SysFlowQuestion> questionList = super.getFacade().getSysFlowQuestionService().getSysFlowQuestionPageList(question);
        int total = super.getFacade().getSysFlowQuestionService().getSysFlowQuestionPageCount(question);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", questionList);
        return result;

    }

    /**
     * 模糊查询流程编号
     * @param flowCode
     * @param matchCount
     * @return
     */
    @RequestMapping(value = "/queryFlowCode.do")
    @ResponseBody
    public Map<String,Object> queryFlowCode(String flowCode,int matchCount){
        Row row = new Row(0,matchCount);
        SysFlowInfo flowInfo = new SysFlowInfo();
        flowInfo.setRow(row);
        flowInfo.setFlowCode(flowCode);
        flowInfo.setFlowType(Constants.Flow.FLOW_TYPE_SMALL);
        List<SysFlowInfo> flowInfos = super.getFacade().getSysFlowInfoService().querySysFlowInfoList(flowInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", matchCount);
        result.put("status", Constants.SUCCESS);
        result.put("data", flowInfos);
        return result;
    }

    /**
     * 模糊查询流程名称
     * @param flowName
     * @param matchCount
     * @return
     */
    @RequestMapping(value = "/queryFlowName.do")
    @ResponseBody
    public Map<String,Object> queryFlowName(String flowName,int matchCount){
        Row row = new Row(0,matchCount);
        SysFlowInfo flowInfo = new SysFlowInfo();
        flowInfo.setRow(row);
        flowInfo.setFlowName(flowName);
        flowInfo.setFlowType(Constants.Flow.FLOW_TYPE_SMALL);
        List<SysFlowInfo> flowInfos = super.getFacade().getSysFlowInfoService().querySysFlowInfoListForName(flowInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", matchCount);
        result.put("status", Constants.SUCCESS);
        result.put("data", flowInfos);
        return result;
    }

    /**
     * 新增问题
     * @param question
     * @return
     */
    @RequestMapping(value = "/add.do")
    @ResponseBody
    public Map<String,Object> addSysFlowQuestion(SysFlowQuestion question){
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        question.setId(ssgjHelper.createSysFlowQuestionId());
        question.setQuesCode(ssgjHelper.createSysFlowQuestionCode());
        question.setLastUpdateTime(new Timestamp(new Date().getTime()));
        question.setLastUpdator(userInfo.getId());
        super.getFacade().getSysFlowQuestionService().createSysFlowQuestion(question);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 更新问题
     * @param question
     * @return
     */
    @RequestMapping(value = "/update.do")
    @ResponseBody
    public Map<String,Object> updateSysFlowQuestion(SysFlowQuestion question){
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        question.setLastUpdateTime(new Timestamp(new Date().getTime()));
        question.setLastUpdator(userInfo.getId());
        super.getFacade().getSysFlowQuestionService().modifySysFlowQuestion(question);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
    /**
     * 删除问题 修改问题状态
     * @param question
     * @return
     */
    @RequestMapping(value = "/deleteById.do")
    @ResponseBody
    public Map<String,Object> deleteSysFlowQuestion(SysFlowQuestion question){
        question.setLastUpdateTime(new Timestamp(new Date().getTime()));
        question.setStatus(Constants.STATUS_UNUSE);
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        question.setLastUpdator(userInfo.getId());
        super.getFacade().getSysFlowQuestionService().modifySysFlowQuestion(question);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


}
