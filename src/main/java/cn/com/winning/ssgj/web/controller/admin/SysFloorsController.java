package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysFloors;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.admin
 * @date 2018-03-24 9:31
 */
@Controller
@RequestMapping(value = "/admin/floors")
public class SysFloorsController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;
    @RequestMapping(value = "/pageInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model){
        return "hospital/floorInfo";
    }


    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> listPageInfo(SysFloors floors, Row row){
        floors.setRow(row);
        floors.setIsDel(Constants.STATUS_USE);
        List<SysFloors> floorsList = super.getFacade().getSysFloorsService().getSysFloorsPageListByFuzzy(floors);
        int total = super.getFacade().getSysFloorsService().getSysFloorsCountByFuzzy(floors);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", floorsList);
        return result;
    }

    @RequestMapping(value = "/existName.do")
    @ResponseBody
    public Map<String,Object> existName(SysFloors floors){
        boolean exists = super.getFacade().getSysFloorsService().getSysFloorsExistsFloorsName(floors);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("valid", exists);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object> getById(SysFloors floors){
        floors = super.getFacade().getSysFloorsService().getSysFloors(floors);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", floors);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/modifyById.do")
    @ResponseBody
    @ILog
    public Map<String,Object> modifyById(SysFloors floors){
        floors = super.getFacade().getSysFloorsService().getSysFloors(floors);
        if (floors.getIsDel() == Constants.STATUS_UNUSE) {
            floors.setIsDel(Constants.STATUS_USE);
        } else {
            floors.setIsDel(Constants.STATUS_UNUSE);
        }
        super.getFacade().getSysFloorsService().modifySysFloors(floors);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    @RequestMapping(value = "/add.do")
    @ResponseBody
    @ILog
    public Map<String,Object> add(SysFloors floors){
       floors.setIsDel(Constants.STATUS_USE);
       floors.setId(ssgjHelper.createSysFloorsIdService());
        floors.setFloorCode(String.valueOf(floors.getId()));
        super.getFacade().getSysFloorsService().createSysFloors(floors);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/update.do")
    @ResponseBody
    @ILog
    public Map<String,Object> update(SysFloors floors){
        super.getFacade().getSysFloorsService().modifySysFloors(floors);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


}
