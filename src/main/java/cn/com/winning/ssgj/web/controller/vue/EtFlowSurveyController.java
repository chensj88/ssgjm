package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.EtFlowSurvey;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title VUE业务流程调研
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-11 16:34
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/flow/")
public class EtFlowSurveyController extends BaseController {

    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryFlowInfoByPmId(EtFlowSurvey flowSurvey){


        super.getFacade().getCommonQueryService().queryProductOfProjectByProjectIdAndType(flowSurvey.getPmId(),Constants.PMIS.CPLB_1);
        Map<String,Object> result = new HashMap<String,Object>();
        /*result.put("total", total);*/
        result.put("status", Constants.SUCCESS);
        /*result.put("rows", );*/
        return result;

    }
}
