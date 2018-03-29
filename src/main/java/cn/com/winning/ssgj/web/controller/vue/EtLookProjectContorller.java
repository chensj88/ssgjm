package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.*;
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

    /**
     * 添加新的项目记录信息
     * @param projectId 项目ID
     * @param userid 用户ID
     * @return
     */
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
        project.setCId(basicInfo.getHtxx());
        project.setSerialNo(basicInfo.getKhxx()+"");
        project.setPmId(projectId);
        project.setLoginTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtUserLookProjectService().createEtUserLookProject(project);
        createProcessManager(project);
        SysOrgExt orgExt = new SysOrgExt();
        orgExt.setOrgId(userInfo.getOrgid());
        orgExt = super.getFacade().getSysOrgExtService().getSysOrgExt(orgExt);
        userInfo.getMap().put("C_ID",basicInfo.getHtxx()); //合同ID
        userInfo.getMap().put("CU_ID",basicInfo.getKhxx()); //客户ID
        userInfo.getMap().put("PM_ID",projectId); //项目ID
        userInfo.getMap().put("orgExt",orgExt.getOrgNameExt());
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("user", userInfo);
        return result;
    }

    /**
     * 创建新的项目流程信息
     * @param project
     */
    private void createProcessManager(EtUserLookProject project){
        EtProcessManager processManager = new EtProcessManager();
        processManager.setPmId(project.getPmId());
        processManager.setCId(project.getCId());
        processManager = super.getFacade().getEtProcessManagerService().getEtProcessManager(processManager);
        if(processManager == null){
            processManager = new EtProcessManager();
            processManager.setId(ssgjHelper.createEtProcessManagerIdService());
            processManager.setPmId(project.getPmId());
            processManager.setCId(project.getCId());
            processManager.setIsStart(Constants.STATUS_USE);
            processManager.setIsAccept(Constants.STATUS_USE);
            processManager.setCreator(project.getUserId());
            processManager.setCreateTime(new Timestamp(new  Date().getTime()));
            super.getFacade().getEtProcessManagerService().createEtProcessManager(processManager);
        }

    }
}
