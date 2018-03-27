package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-26 8:52
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/projectProduct")
public class EtContractProjectController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String,Object> listProductOfProject(PmisContractProductInfo productInfo, Row row){
        EtContractTask task = new EtContractTask();
        task.setPmId(productInfo.getXmlcb());
        task.setRow(row);
        List<EtContractTask> productInfoList = super.getFacade().getEtContractTaskService().getEtContractTaskPageMergeList(task);
        int total = super.getFacade().getEtContractTaskService().getEtContractTaskMergeCount(task);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", productInfoList);
        return result;
    }

    @RequestMapping(value = "/queryProduct.do")
    @ResponseBody
    public Map<String,Object> queryProduct(PmisProductInfo productInfo){
        Row row = new Row(0,10);
        productInfo.setRow(row);
        productInfo.setZt(Constants.PMIS_STATUS_USE);
        productInfo.setCplb("1");
        List<PmisProductInfo> productInfos = super.getFacade().getPmisProductInfoService().getPmisProductInfoPaginatedListByCodeAndName(productInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",productInfos);
        return result;
    }

    @RequestMapping(value = "/addOrModifyProduct.do")
    @ResponseBody
    public Map<String,Object> addOrModifyProduct(EtContractTask task){
        EtContractTask oldTask = new EtContractTask();
        oldTask.setId(task.getId());
        oldTask = super.getFacade().getEtContractTaskService().getEtContractTask(oldTask);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(task.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        task.setSerialNo(basicInfo.getKhxx()+"");
        if(oldTask != null){
            task.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtContractTaskService().modifyEtContractTask(task);
        }else{
            task.setId(ssgjHelper.createEtContractTaskIdService());
            task.setCreator(task.getOperator());
            task.setCreateTime(new Timestamp(new Date().getTime()));
            task.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtContractTaskService().createEtContractTask(task);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/deleteProduct.do")
    @ResponseBody
    public Map<String,Object> deleteProduct(EtContractTask task){
        super.getFacade().getEtContractTaskService().removeEtContractTask(task);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

}
