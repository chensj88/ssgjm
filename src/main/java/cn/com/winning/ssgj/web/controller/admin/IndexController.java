package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-23 16:47
 */
@Controller
@RequestMapping(value = "/home")
public class IndexController extends BaseController {

    @RequestMapping(value = "/index.do")
    public String indexPageInfo(HttpServletRequest request, Model model) {
        return "index/index";
    }

    @RequestMapping(value = "/count.do")
    @ResponseBody
    public Map<String,Object> queryPmisInfo(){
        List<String> itemData = new ArrayList<>();
        List<Integer> numData = new ArrayList<>();
        PmisContractInfo contractInfo = new PmisContractInfo();
        int htNum = super.getFacade().getPmisContractInfoService().getPmisContractInfoCount(contractInfo);
        itemData.add("合同信息");
        numData.add(htNum);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        int pNum = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfoCount(basicInfo);
        itemData.add("项目信息");
        numData.add(pNum);
        PmisProductInfo info = new PmisProductInfo();
        int ppNum = super.getFacade().getPmisProductInfoService().getPmisProductInfoCount(info );
        itemData.add("产品信息");
        numData.add(ppNum);
        SysUserInfo user = new SysUserInfo();
        user.setUserType(Constants.User.USER_TYPE_COMPANY);
        user.setStatus(1);
        int userNum = super.getFacade().getSysUserInfoService().getSysUserInfoCount(user);
        itemData.add("医院用户信息");
        numData.add(userNum);
        user.setUserType(Constants.User.USER_TYPE_HOSPITAL);
        user.setStatus(1);
        userNum = super.getFacade().getSysUserInfoService().getSysUserInfoCount(user);
        itemData.add("医院用户信息");
        numData.add(userNum);
        EtProcessManager manager = new EtProcessManager();
        int pmNum = super.getFacade().getEtProcessManagerService().getEtProcessManagerCount(manager);
        itemData.add("系统统计项目");
        numData.add(pmNum);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("itemData",itemData);
        result.put("numData",numData);
        return result;

    }

}
