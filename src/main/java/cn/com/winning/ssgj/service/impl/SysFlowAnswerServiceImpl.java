package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFlowAnswerDao;
import cn.com.winning.ssgj.domain.SysFlowAnswer;
import cn.com.winning.ssgj.service.SysFlowAnswerService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysFlowAnswerServiceImpl implements SysFlowAnswerService {

    @Resource
    private SysFlowAnswerDao sysFlowAnswerDao;
    @Autowired
    private SSGJHelper ssgjHelper;


    public Integer createSysFlowAnswer(SysFlowAnswer t) {
        return this.sysFlowAnswerDao.insertEntity(t);
    }

    public SysFlowAnswer getSysFlowAnswer(SysFlowAnswer t) {
        return this.sysFlowAnswerDao.selectEntity(t);
    }

    public Integer getSysFlowAnswerCount(SysFlowAnswer t) {
        return (Integer) this.sysFlowAnswerDao.selectEntityCount(t);
    }

    public List<SysFlowAnswer> getSysFlowAnswerList(SysFlowAnswer t) {
        return this.sysFlowAnswerDao.selectEntityList(t);
    }

    public int modifySysFlowAnswer(SysFlowAnswer t) {
        return this.sysFlowAnswerDao.updateEntity(t);
    }

    public int removeSysFlowAnswer(SysFlowAnswer t) {
        return this.sysFlowAnswerDao.deleteEntity(t);
    }

    public List<SysFlowAnswer> getSysFlowAnswerPaginatedList(SysFlowAnswer t) {
        return this.sysFlowAnswerDao.selectEntityPaginatedList(t);
    }

    @Override
    public void createSysFlowAnswer(String info,Long quesId) {
        SysFlowAnswer delAnswer = new SysFlowAnswer();
        delAnswer.setQuesId(quesId);
        delAnswer.setStatus(Constants.STATUS_UNUSE);
        this.sysFlowAnswerDao.updateEntity(delAnswer);
        String[] answers = info.split(";");
        for (String ans : answers) {
            String[] items = ans.split(",");
            SysFlowAnswer answer = new SysFlowAnswer();
            answer.setId(ssgjHelper.createSysFlowAnswerId());
            answer.setAnswerCode(ssgjHelper.createSysFlowAnswerCode());
            answer.setQuesId(Long.parseLong(items[0]));
            answer.setAnswerType(Integer.parseInt(items[1]));
            answer.setAnswerContent(items[2]);
            answer.setStatus(Constants.STATUS_USE);
            answer.setLastUpdateTime(new Timestamp(new Date().getTime()));
            SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
            answer.setLastUpdator(userInfo.getId());
            this.sysFlowAnswerDao.insertEntity(answer);
        }
    }

}
