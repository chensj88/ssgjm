package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.EtSimulateRecord;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 模拟运行Controller
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-08 17:02
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/simulate")
public class EtSimulateRecordController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;
    /**
     * 获取全部的模拟运行记录
     * @param record
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> queryEtSimulateRecord(EtSimulateRecord record){
        record.setStatus(Constants.STATUS_USE);
        List<EtSimulateRecord> simulateRecords = super.getFacade().getEtSimulateRecordService().getEtSimulateRecordPaginatedList(record);
        int total = super.getFacade().getEtSimulateRecordService().getEtSimulateRecordCount(record);
        Map<String,Object> result = new HashMap<>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", simulateRecords);
        return result;
    }

    /**
     * 新增或者修改模拟运行记录
     * @param record
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> addEtSimulateRecord(EtSimulateRecord record){
        EtSimulateRecord oldRecord = new EtSimulateRecord();
        oldRecord.setId(record.getId());
        oldRecord = super.getFacade().getEtSimulateRecordService().getEtSimulateRecord(oldRecord);

        if(oldRecord != null){
            record.setStatus(Constants.STATUS_USE);
            record.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSimulateRecordService().modifyEtSimulateRecord(record);
        }else{
            record.setId(ssgjHelper.createEtSimulateRecordIdService());
            record.setStatus(Constants.STATUS_USE);
            record.setCreator(record.getOperator());
            record.setCreateTime(new Timestamp(new Date().getTime()));
            record.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSimulateRecordService().createEtSimulateRecord(record);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
    /**
     * 删除模拟运行记录 只是修改状态
     * @param record
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> deleteEtSimulateRecord(EtSimulateRecord record){
        record.setStatus(Constants.STATUS_UNUSE);
        record.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSimulateRecordService().modifyEtSimulateRecord(record);
        Map<String,Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 模拟运行任务完成
     * @param record
     * @return
     */
    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @Transactional
    @ILog
    public Map<String,Object> confirmEtSimulateRecord(EtSimulateRecord record){
        EtProcessManager processManager = new EtProcessManager();
        processManager.setPmId(record.getPmId());
        processManager = super.getFacade().getEtProcessManagerService().getEtProcessManager(processManager);
        processManager.setIsSimulation(Constants.STATUS_USE);
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(processManager);
        Map<String,Object> result = new HashMap<>();
        result.put("status", Constants.SUCCESS);
        return result;
    }
}
