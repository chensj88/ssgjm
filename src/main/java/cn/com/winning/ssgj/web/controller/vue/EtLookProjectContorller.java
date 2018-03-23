package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtUserLookProject;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-23 12:44
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/etUserLook")
public class EtLookProjectContorller extends BaseController {


    @Autowired
    private SSGJHelper ssgjHelper;
    @RequestMapping(value = "/add.do")
    @ResponseBody
    public Map<String,Object> addLookProject(Long projectId,long userid){
        SysUserInfo userInfo = super.getFacade().getSysUserInfoService().getSysUserInfoById(userid);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(projectId);
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        EtUserLookProject project = new EtUserLookProject();
        project.setId(ssgjHelper.createEtLookProjectIdService());
        project.setUserId(userInfo.getId());
        project.setCId(basicInfo.getKhxx());
        project.setPmId(projectId);
        project.setLoginTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtUserLookProjectService().createEtUserLookProject(project);
        userInfo.getMap().put("C_ID",basicInfo.getKhxx());
        userInfo.getMap().put("PM_ID",projectId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("user", userInfo);
        return result;

    }
}
