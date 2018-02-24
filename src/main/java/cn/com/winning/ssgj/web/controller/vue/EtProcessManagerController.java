package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目流程管理
 *
 * @author ChenKuai
 * @date 2018-02-22 15:56:49
 **/
@CrossOrigin
@Controller
@RequestMapping("/vue/processManager")
public class EtProcessManagerController extends BaseController {

    /**
     * @author: Chen,Kuai
     * @Description: 项目流程信息
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/info")
    @ResponseBody
    public Map<String,Object> processManagerInfo(Long c_id, Long pm_id){
        Map<String,Object> result = new HashMap<String,Object>();
        EtProcessManager entity = new EtProcessManager();
        entity.setCId((long)4);//合同ID
        entity.setPmId((long)15);//项目id
        List<EtProcessManager> processManagerList = super.getFacade().getEtProcessManagerService()
                .getEtProcessManagerList(entity);
        JSONArray jsonArray = JSONArray.fromObject(processManagerList);
        System.out.println(jsonArray.toString());
        result.put("result",processManagerList);
        return result;

    }



}
