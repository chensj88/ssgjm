package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-03-15 10:41
 */
@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController{

    @RequestMapping(value = "/exec.do")
    @ResponseBody
    public Map<String,Object> executeProcedure(){
        super.getFacade().getSysOrgExtService().callProcedure();
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/test.do")
    @ResponseBody
    public Map<String,Object> testJsonArray(){
        Map<String,List> data = super.getFacade().getCommonQueryService().queryCompletionOfProject(24661L);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("success",(List<Integer>)data.get("success"));
        result.put("handle",(List<Integer>)data.get("handle"));
        result.put("item",(List<String>)data.get("item"));
        return result;

    }
}
