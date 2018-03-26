package cn.com.winning.ssgj.web.controller.vue;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.annoation.ILog;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.winning.ssgj.domain.EtCustomerDetail;
import cn.com.winning.ssgj.service.EtCustomerDetailService;
import cn.com.winning.ssgj.web.controller.common.BaseController;

/**
 * 客户明细信息添加修改
 *
 * @author 胡万里
 * @date 2018-03-8 15:56:49
 **/
@CrossOrigin
@Controller
@RequestMapping("/vue/etCustomerDetail")
public class EtCustomerDetailContorller extends BaseController {
    @Resource
    private EtCustomerDetailService ser;

    /**
     * @author: 胡万里
     * @Description: 客户明细信息添加
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/add")
    @ResponseBody
    @ILog
    public Map<String, Object> addEtCustomerDetail(EtCustomerDetail etCustomerDetail) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer isSucceed = super.getFacade().getEtCustomerDetailService().createEtCustomerDetail(etCustomerDetail);
        result.put("isSucceed", isSucceed);
        return result;
    }

    /**
     * @author: 胡万里
     * @Description: 客户明细信息修改
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/etCustomerDetailUpdate")
    @ResponseBody
    @ILog
    public Map<String, Object> updateEtCustomerDetail(EtCustomerDetail etCustomerDetail) {
        Map<String, Object> result = new HashMap<String, Object>();
        EtCustomerDetail old = super.getFacade().getEtCustomerDetailService().getEtCustomerDetail(etCustomerDetail);
        int isSucceed = -1;
        System.out.println(old);
        if(old != null){
            etCustomerDetail.setOperatorTime(new Timestamp(new Date().getTime()));
            isSucceed = super.getFacade().getEtCustomerDetailService().modifyEtCustomerDetail(etCustomerDetail);
        }else{
            etCustomerDetail.setCreator(etCustomerDetail.getOperator());
            etCustomerDetail.setCreateTime(new Timestamp(new Date().getTime()));
            etCustomerDetail.setOperatorTime(new Timestamp(new Date().getTime()));
            isSucceed = super.getFacade().getEtCustomerDetailService().createEtCustomerDetail(etCustomerDetail);
        }
        result.put("isSucceed", isSucceed);
        return result;
    }

    /**
     * @author: 胡万里
     * @Description: 客户明细信息
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/etCustomerDetailFindById")
    @ResponseBody
    public Map<String, Object> etCustomerDetailFindById(EtCustomerDetail etCustomerDetail) {
        Map<String, Object> result = new HashMap<String, Object>();
        EtCustomerDetail detail = super.getFacade().getEtCustomerDetailService().getMergeEtCustomerDetail(etCustomerDetail);
        result.put("result", detail);
        return result;
    }
}
