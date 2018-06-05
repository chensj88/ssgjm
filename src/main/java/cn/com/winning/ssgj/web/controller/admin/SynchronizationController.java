package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.service.SysOrgExtService;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class SynchronizationController extends BaseController {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private PmisWebServiceClient pmisWebServiceClient;
    @Autowired
    private SysOrgExtService sysOrgExtService;


    @RequestMapping(value = "/synchronization/index.do")
    public String index(HttpServletRequest request, Model model) {
        return "synchronization/synchronization";
    }

    @RequestMapping(value = "/synchronization/synchronization.do")
    @ResponseBody
    public Map<String, Object> synchronization(Integer type) {
        if (type == 0) {
            //同步所有数据
            logger.info("开始导入PMIS数据>>>>>>>>>>>>>>>>>>");
            pmisWebServiceClient.importPmisData();
            logger.info("PMIS数据导入结束>>>>>>>>>>>>>>>>>>");
            logger.info("开始生成机构全路径数据>>>>>>>>>>>>");
            sysOrgExtService.callProcedure();
            logger.info("机构全路径数据导入结束>>>>>>>>>>>>");
        } else if (type == 8) {
            //组织机构
            PmisWebServiceClient.insertPMISInterfaceData(type.toString());
            logger.info("开始生成机构全路径数据，开始时间为[{}]", sdf.format(new Date()));
            sysOrgExtService.callProcedure();
            logger.info("机构全路径数据导入结束，结束时间为[{}]", sdf.format(new Date()));
        } else {
            PmisWebServiceClient.insertPMISInterfaceData(type.toString());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

}
