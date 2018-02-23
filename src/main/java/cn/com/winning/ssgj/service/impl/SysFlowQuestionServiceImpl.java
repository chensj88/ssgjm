package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFlowQuestionDao;
import cn.com.winning.ssgj.domain.SysFlowQuestion;
import cn.com.winning.ssgj.service.SysFlowQuestionService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysFlowQuestionServiceImpl implements SysFlowQuestionService {

    @Resource
    private SysFlowQuestionDao sysFlowQuestionDao;


    public Integer createSysFlowQuestion(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.insertEntity(t);
    }

    public SysFlowQuestion getSysFlowQuestion(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.selectEntity(t);
    }

    public Integer getSysFlowQuestionCount(SysFlowQuestion t) {
        return (Integer) this.sysFlowQuestionDao.selectEntityCount(t);
    }

    public List<SysFlowQuestion> getSysFlowQuestionList(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.selectEntityList(t);
    }

    public int modifySysFlowQuestion(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.updateEntity(t);
    }

    public int removeSysFlowQuestion(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.deleteEntity(t);
    }

    public List<SysFlowQuestion> getSysFlowQuestionPaginatedList(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.selectEntityPaginatedList(t);
    }

    @Override
    public Integer getSysFlowQuestionPageCount(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.selectFlowQuestionPageCount(t);
    }

    @Override
    public List<SysFlowQuestion> getSysFlowQuestionPageList(SysFlowQuestion t) {
        return this.sysFlowQuestionDao.selectFlowQuestionPageList(t);
    }

}
