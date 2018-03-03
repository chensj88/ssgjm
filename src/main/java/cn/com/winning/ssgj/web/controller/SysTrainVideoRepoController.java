package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 培训视频
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-28 10:39
 */
@Controller
@RequestMapping(value = "/admin/train")
public class SysTrainVideoRepoController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;
    @RequestMapping(value = "/pageInfo.do")
    public String getPageInfo(HttpServletRequest request, Model model){
        return "mobile/trainVideoPage";
    }


    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> getTrainVideoList(SysTrainVideoRepo repo, Row row){
        repo.setRow(row);
        /*repo.setStatus(Constants.STATUS_USE);*/
        List<SysTrainVideoRepo> repoList = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoPageListBySelective(repo);
        int total = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepoCountBySelective(repo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", repoList);
        return result;
    }

    @RequestMapping(value = "/add.do")
    @ResponseBody
    @Transactional
    public Map<String,Object>  addTrainVideo(SysTrainVideoRepo repo){
     repo.setId(ssgjHelper.createSysTrainVideoRepoId());
     /*repo.setLastUpdateTime(new Date());*/
     repo.setLastUpdator(((SysUserInfo)SecurityUtils.getSubject().getPrincipal()).getId());
     repo.setStatus(Constants.STATUS_USE);
     super.getFacade().getSysTrainVideoRepoService().createSysTrainVideoRepo(repo);
     Map<String,Object> result = new HashMap<String,Object>();
     result.put("status", Constants.SUCCESS);
     return result;
    }

    @RequestMapping(value = "/update.do")
    @ResponseBody
    @Transactional
    public Map<String,Object>  updateTrainVideo(SysTrainVideoRepo repo){
        /*repo.setLastUpdateTime(new Date());*/
        repo.setLastUpdator(((SysUserInfo)SecurityUtils.getSubject().getPrincipal()).getId());
        super.getFacade().getSysTrainVideoRepoService().modifySysTrainVideoRepo(repo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    @RequestMapping(value = "/getById.do")
    @ResponseBody
    public Map<String,Object>  getTrainVideoById(SysTrainVideoRepo repo){
        repo = super.getFacade().getSysTrainVideoRepoService().getSysTrainVideoRepo(repo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", repo);
        return result;
    }


    @RequestMapping(value = "/modifyById.do")
    @ResponseBody
    @Transactional
    public Map<String,Object>  modifyTrainVideoById(SysTrainVideoRepo repo){
       /* repo.setLastUpdateTime(new Date());*/
        repo.setLastUpdator(((SysUserInfo)SecurityUtils.getSubject().getPrincipal()).getId());
        if(repo.getStatus() == Constants.STATUS_UNUSE){
            repo.setStatus(Constants.STATUS_USE);
        }else{
            repo.setStatus(Constants.STATUS_UNUSE);
        }
        super.getFacade().getSysTrainVideoRepoService().modifySysTrainVideoRepo(repo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/existVideoName.do")
    @ResponseBody
    public Map<String,Object> existVideoName(SysTrainVideoRepo repo){
        boolean exists = super.getFacade().getSysTrainVideoRepoService().existVideoName(repo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("valid", exists);
        result.put("status", Constants.SUCCESS);
        return result;

    }
}
