package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

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

}
