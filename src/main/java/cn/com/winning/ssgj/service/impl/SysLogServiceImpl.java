package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysLogDao;
import cn.com.winning.ssgj.domain.SysLog;
import cn.com.winning.ssgj.service.SysLogService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogDao sysLogDao;


    public Integer createSysLog(SysLog t) {
        return this.sysLogDao.insertEntity(t);
    }

    public SysLog getSysLog(SysLog t) {
        return this.sysLogDao.selectEntity(t);
    }

    public Integer getSysLogCount(SysLog t) {
        return (Integer) this.sysLogDao.selectEntityCount(t);
    }

    public List<SysLog> getSysLogList(SysLog t) {
        return this.sysLogDao.selectEntityList(t);
    }

    public int modifySysLog(SysLog t) {
        return this.sysLogDao.updateEntity(t);
    }

    public int removeSysLog(SysLog t) {
        return this.sysLogDao.deleteEntity(t);
    }

    public List<SysLog> getSysLogPaginatedList(SysLog t) {
        return this.sysLogDao.selectEntityPaginatedList(t);
    }

}
