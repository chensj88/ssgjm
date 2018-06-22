package cn.com.winning.ssgj.web.controller.mobile;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.dao.EtUserHospitalLogDao;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.EtUserHospitalLog;
import cn.com.winning.ssgj.domain.EtUserLog;
import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LENOVO
 * @title 通用问题组件
 * @package cn.com.winning.ssgj.web.controller.mobile
 * @date 2018-06-16 2018/6/16
 */
@Controller
@CrossOrigin
@RequestMapping("/mobile/commons")
public class EnterpriseQuestionCommonController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;
    /**
     * 按照传输参数加载问题列表
     *
     * @param model
     * @param userId     用户ID
     * @param serialNo   客户号
     * @param status     问题状态
     * @param searchType 查询类型 1 系统 2 关键字 3科室
     * @param searchText 查询文字
     * @param isManager   用户类型 0 项目经理  1 实施工程师
     * @param priority   优先级
     * @return 访问路径
     */
    @RequestMapping(value = "/list.do")
    public String siteQuestionList(Model model, Long userId, String serialNo,
                                   String status, Integer searchType, String searchText,
                                   Integer isManager, Integer priority) throws ParseException {
        EtSiteQuestionInfo info = new EtSiteQuestionInfo();
        if (isManager == 1) { //判断用户类型  项目经理，默认查看所有问题信息
            info.setAllocateUser(userId);
        }
        info.setSerialNo(serialNo); //客户号
        //info.setProcessStatus(status); //状态
        if(status.split(",").length > 1){ //多个参数
            info.getMap().put("processStatus",status);
        }else{ //单一参数
            info.setProcessStatus(Integer.valueOf(status));
        }
        info.setPriority(priority); //优先级
        if (searchType != null) { //查询类型 可以为空
            if (searchType == 1 || searchType == 3) { //系统或者科室
                info.getMap().put("searchType", searchType);
                info.getMap().put("searchText", searchText);
            } else {
                info.getMap().put("search_text", searchText);
            }
            EtUserLog log = new EtUserLog();
            log.setId(ssgjHelper.createEtUserLogIdService());
            log.setSerialNo(serialNo);
            log.setPmId(-2L);
            log.setCId(-2L);
            log.setContent(searchText);
            log.setSourceType(searchType);
            log.setProcessStatus(status);
            log.setOperator(userId);
            log.setOperatorTime(new Timestamp(new Date().getTime()));
            getFacade().getEtUserLogService().createEtUserLog(log);
        }
        model.addAttribute("questionList", getFacade().getEtSiteQuestionInfoService().getSiteQuestionInfoByUser(info));
        model.addAttribute("serialNo", serialNo);
        model.addAttribute("userId", userId);
        model.addAttribute("status", status);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchText", searchText);
        model.addAttribute("isManager", isManager);
        model.addAttribute("priority", priority);
        return "mobile2/enterprise/common-question-list";
    }

    /**
     * 跳转查询列表
     * @param model
     * @param userId     用户ID
     * @param serialNo   客户号
     * @param status     问题状态
     * @param searchType 查询类型 1 系统 2 关键字 3科室
     * @param searchText 查询文字
     * @param isManager   用户类型 0 项目经理  1 实施工程师
     * @return 访问路径
     */
    @RequestMapping(value = "/query.do")
    public String queryPage(Model model, Long userId, String serialNo,
                            String status, Integer searchType, String searchText,
                            Integer isManager) throws ParseException {
        EtUserLog log = new EtUserLog();
        log.setOperator(userId);
        Row row = new Row();
        row.setCount(8);
        log.setRow(row);
        model.addAttribute("logList",getFacade().getEtUserLogService().getEtUserLogList(log));
        model.addAttribute("serialNo", serialNo);
        model.addAttribute("userId", userId);
        model.addAttribute("status", status);
        model.addAttribute("searchType", 2); //跳转页面提供默认值
        model.addAttribute("searchText", "");//跳转页面提供默认值
        model.addAttribute("isManager", isManager);
        return "mobile2/enterprise/common-query-list";
    }

    /**
     * 模糊查询客户名称
     * @param name 客户名称关键字
     * @param count 每次显示数量
     * @return
     */
    @RequestMapping(value = "/queryCustomerName.do")
    @ResponseBody
    public Map<String, Object> queryCustomerNameInfo(String name,int count) {
        PmisCustomerInformation customer = new PmisCustomerInformation();
        customer.setName(name);
        customer.setZt(Constants.PMIS_STATUS_USE);
        Row row = new Row(0, count);
        customer.setRow(row);
        List<PmisCustomerInformation> customerInformationList = super.getFacade().getPmisCustomerInformationService().getPmisCustomerInformationPageListFuzzy(customer);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", count);
        result.put("status", Constants.SUCCESS);
        result.put("data", customerInformationList);
        return result;
    }


    @RequestMapping(value = "/addOperateDeptOrProductLog.do")
    @ResponseBody
    public Map<String, Object> addOperateDeptOrProduct(EtUserHospitalLog log) {
        if(log.getId() == null){
            long id = ssgjHelper.createEtUserHospitalLog();
            log.setId(id);
            log.setCId(-2L);
            log.setPmId(-2L);
            getFacade().getEtUserHospitalLogService().createEtUserHospitalLog(log);
        }else{
            getFacade().getEtUserHospitalLogService().modifyEtUserHospitalLog(log);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", log.getId());
        return result;
    }

}

