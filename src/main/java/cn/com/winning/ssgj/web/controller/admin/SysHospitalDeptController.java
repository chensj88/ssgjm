package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysFloors;
import cn.com.winning.ssgj.domain.SysHospitalDept;
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
@RequestMapping(value = "/admin/dept")
public class SysHospitalDeptController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/pageInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model){
        return "hospital/hospitalDept";
    }


    @RequestMapping(value ="/list.do")
    @ResponseBody
    public Map<String,Object> list(SysHospitalDept dept, Row row){
        dept.setRow(row);
        List<SysHospitalDept> hospitalDeptList = super.getFacade().getSysHospitalDeptService().getSysHospitalDeptPageListByFuzzy(dept);
        int total = super.getFacade().getSysHospitalDeptService().getSysHospitalDeptCountByFuzzy(dept);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows",hospitalDeptList );
        return result;
    }

    @RequestMapping(value ="/existName.do")
    @ResponseBody
    public Map<String,Object> existName(SysHospitalDept dept){
        boolean exists = super.getFacade().getSysHospitalDeptService().getSysHospitalDeptName(dept);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("valid", exists);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value ="/getById.do")
    @ResponseBody
    public Map<String,Object> getById(SysHospitalDept dept){
        dept = super.getFacade().getSysHospitalDeptService().getSysHospitalDept(dept);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", dept);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value ="/modifyById.do")
    @ResponseBody
    @ILog
    public Map<String,Object> modifyById(SysHospitalDept dept){
        dept = super.getFacade().getSysHospitalDeptService().getSysHospitalDept(dept);
        if (dept.getIsDel() == Constants.STATUS_UNUSE) {
            dept.setIsDel(Constants.STATUS_USE);
        } else {
            dept.setIsDel(Constants.STATUS_UNUSE);
        }
        super.getFacade().getSysHospitalDeptService().modifySysHospitalDept(dept);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/add.do")
    @ResponseBody
    @ILog
    public Map<String,Object> add(SysHospitalDept dept){
        dept.setIsDel(Constants.STATUS_USE);
        dept.setId(ssgjHelper.createSysHospitalDeptIdService());
        dept.setDeptCode(String.valueOf(dept.getId()));
        super.getFacade().getSysHospitalDeptService().createSysHospitalDept(dept);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/update.do")
    @ResponseBody
    @ILog
    public Map<String,Object> update(SysHospitalDept dept){
        super.getFacade().getSysHospitalDeptService().modifySysHospitalDept(dept);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
}
