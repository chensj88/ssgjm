package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysFlowAnswer;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.domain.SysFlowQuestion;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 流程问卷答案信息
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-22 9:33
 */
@Controller
@RequestMapping(value = "/admin/fa")
public class SYSFlowQuestionAnswerController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 查询问题答案列表信息
     *
     * @return
     */
    @RequestMapping(value = "/findByFQId.do")
    @ResponseBody
    @ILog
    public Map<String, Object> queryFlowQuestionAnswerList(Long quesId) {
        SysFlowAnswer answer = new SysFlowAnswer();
        answer.setQuesId(quesId);
        answer.setStatus(Constants.STATUS_USE);
        List<SysFlowAnswer> answerList = super.getFacade().getSysFlowAnswerService().getSysFlowAnswerList(answer);
        int total = super.getFacade().getSysFlowAnswerService().getSysFlowAnswerCount(answer);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", answerList);
        result.put("total", total);
        return result;
    }

    /**
     * 新增答案
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/add.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addSysFlowQuestion(String info, Long quesId) {
        super.getFacade().getSysFlowAnswerService().createSysFlowAnswer(info, quesId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }


}
