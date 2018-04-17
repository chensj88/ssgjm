package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtOnlineFile;
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
 * @title 上线可行性报告和上线切换报告
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-17 21:23
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/onLineReport")
public class EtOnlineFileController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String,Object> queryOnlineInfo(EtOnlineFile file){
        long pmId = file.getPmId();
        long cId = file.getcId();
        String serialNo = file.getSerialNo();
        Map<String,List> data = super.getFacade().getCommonQueryService().queryCompletionOfProject(file.getPmId());
        file = super.getFacade().getEtOnlineFileService().getEtOnlineFile(file);
        if(file == null){
            file = new EtOnlineFile();
            file.setId(ssgjHelper.createEtOnlineFileIdService());
            file.setPmId(pmId);
            file.setcId(cId);
            file.setSerialNo(serialNo);
            file.setCreator(super.getCurrentUserInfo().getId());
            file.setCreateTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtOnlineFileService().createEtOnlineFile(file);
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("completeData",(List<Integer>)data.get("success"));
        result.put("failData",(List<Integer>)data.get("handle"));
        result.put("itemData",(List<String>)data.get("item"));
        result.put("data",file);
        return result;

    }
}
