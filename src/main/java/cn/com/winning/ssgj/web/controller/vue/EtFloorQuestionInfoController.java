package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.domain.support.Row;
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
 * @title 楼层问题展示
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-02 13:04
 */

@CrossOrigin
@Controller
@RequestMapping(value = "/vue/floorQuestion")
public class EtFloorQuestionInfoController  extends BaseController{


    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryEtFloorQuestionInfoList(EtFloorQuestionInfo info,Row row){
        info.setRow(row);
        List<EtFloorQuestionInfo> infoList = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfoPaginatedList(info);
        int total = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfoCount(info);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", infoList);
        return result;

    }

}
