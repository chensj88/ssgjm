package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModuleDao;
import cn.com.winning.ssgj.domain.SysModule;
import cn.com.winning.ssgj.service.SysModuleService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

    @Resource
    private SysModuleDao sysModuleDao;


    public Integer createSysModule(SysModule t) {
        return this.sysModuleDao.insertEntity(t);
    }

    public SysModule getSysModule(SysModule t) {
        return this.sysModuleDao.selectEntity(t);
    }

    public Integer getSysModuleCount(SysModule t) {
        return (Integer) this.sysModuleDao.selectEntityCount(t);
    }

    public List<SysModule> getSysModuleList(SysModule t) {
        return this.sysModuleDao.selectEntityList(t);
    }

    public int modifySysModule(SysModule t) {
        return this.sysModuleDao.updateEntity(t);
    }

    public int removeSysModule(SysModule t) {
        return this.sysModuleDao.deleteEntity(t);
    }

    public List<SysModule> getSysModulePaginatedList(SysModule t) {
        return this.sysModuleDao.selectEntityPaginatedList(t);
    }

}
