package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.EtBusinessProcess;
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
 * @title VUE业务流程调研
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-11 16:34
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/businessProcess/")
public class EtBusinessProcessController extends BaseController {

    @RequestMapping(value = "/list.do")
    @ResponseBody
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
}
