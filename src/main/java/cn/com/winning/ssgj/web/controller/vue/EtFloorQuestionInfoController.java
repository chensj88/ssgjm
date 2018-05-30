package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
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
public class EtFloorQuestionInfoController extends BaseController {


    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> queryEtFloorQuestionInfoList(EtFloorQuestionInfo info, Row row, String startDate, String endDate,String fName) throws ParseException {
        info.setRow(row);
        info.getMap().put("f_name",fName);
        if (!"null".equals(startDate) && !"null".equals(endDate) && !StringUtil.isEmptyOrNull(startDate) && !StringUtil.isEmptyOrNull(endDate)) {
            info.getMap().put("startDate", DateUtil.convertDateStringToTimestap(startDate));
            info.getMap().put("endDate", DateUtil.convertDateStringToTimestap(endDate));
        }
        List<EtFloorQuestionInfo> infoList = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfoPaginatedList(info);
        int total = super.getFacade().getEtFloorQuestionInfoService().getEtFloorQuestionInfoCount(info);
        //问题分类
        SysDictInfo sysDictInfo = new SysDictInfo();
        sysDictInfo.setDictCode("questionType");
        List<SysDictInfo> sysDictInfoList = getFacade().getSysDictInfoService().getSysDictInfoList(sysDictInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", infoList);
        result.put("typeList", sysDictInfoList);
        return result;

    }

}
