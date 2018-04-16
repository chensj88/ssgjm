package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtBusinessProcess;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
 * @title VUE业务流程调研
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-11 16:34
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/businessProcess/")
public class EtBusinessProcessController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 主页面数据展示
     * @param process
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> queryFlowInfoByPmId(EtBusinessProcess process, Row row){
        //生成业务流程信息
        super.getFacade().getCommonQueryService().generateEtBusinessProcessByProject(process);
        process.setRow(row);
        //查询流程信息
        List<EtBusinessProcess> processList = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessPaginatedList(process);
        //查询流程总的数量
        process.setIsScope(Constants.STATUS_USE);
        int sumBussinessProcessNum  = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        //查询完成的流程数量
        process.setStatus(Constants.APPROVAL_STATUS_END);
        int completeBPNum = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("rows", processList);
        result.put("total", processList.size());
        result.put("sumNum", sumBussinessProcessNum);
        result.put("comNum", completeBPNum);
        return result;
    }

    /**
     * 实施范围的修改
     * @param process
     * @return
     */
    @RequestMapping(value = "/changeScope.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> changeScope(EtBusinessProcess process){
        process.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 统计完成和未完成数量
     * @param process
     * @return
     */
    @RequestMapping(value = "/countInfo.do")
    @ResponseBody
    public Map<String,Object> countProcessInfo(EtBusinessProcess process){
        //查询流程总的数量
        process.setIsScope(Constants.STATUS_USE);
        int sumBussinessProcessNum  = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        //查询完成的流程数量
        process.setStatus(Constants.APPROVAL_STATUS_END);
        int completeBPNum = super.getFacade().getEtBusinessProcessService().getEtBusinessProcessCount(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("sumNum", sumBussinessProcessNum);
        result.put("comNum", completeBPNum);
        return result;
    }

    /**
     * 添加流程信息
     * @param process
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> addOrModifyProcessInfo(EtBusinessProcess process){
        if (process.getId() == 0L) {
            process.setId(ssgjHelper.createEtBusinessProcessIdService());
            process.setSourceType(Constants.STATUS_USE);
            process.setStatus(Constants.STATUS_UNUSE);
            process.setCreator(process.getOperator());
            process.setCreateTime(new Timestamp(new Date().getTime()));
            process.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtBusinessProcessService().createEtBusinessProcess(process);
        } else {
            process.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtBusinessProcessService().modifyEtBusinessProcess(process);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 删除流程调研信息
     * @param process
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> deleteBusinessProcess(EtBusinessProcess process){
        super.getFacade().getEtBusinessProcessService().removeEtBusinessProcess(process);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 流程数量确认
     * @param manager
     * @return
     */
    @RequestMapping(value = "/confirmFlowNum.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> comfirmBusinessProcess(EtProcessManager manager){
        manager.setIsFlowAffirm(Constants.STATUS_USE);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     *  流程调研与配置
     * @param manager
     * @return
     */
    @RequestMapping(value = "/confirmFlowConfig.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> confirmFlowConfig(EtProcessManager manager){
        manager.setIsFlowConfig(Constants.STATUS_USE);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 查看是否确认完成确认数量
     * @param manager
     * @return
     */
    @RequestMapping(value = "/checkAffirm.do")
    @ResponseBody
    public Map<String,Object> checkBusinessProcessAffirm(EtProcessManager manager){
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", manager.getIsFlowAffirm());
        return result;

    }

    /**
     * 查看是否完成流程配置工作
     * @param manager
     * @return
     */
    @RequestMapping(value = "/checkConfig.do")
    @ResponseBody
    public Map<String,Object> checkBusinessProcessConfig(EtProcessManager manager){
        manager = super.getFacade().getEtProcessManagerService().getEtProcessManager(manager);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", manager.getIsFlowConfig());
        return result;

    }

}
