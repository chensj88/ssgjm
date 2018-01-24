package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModFunDao;
import cn.com.winning.ssgj.domain.SysModFun;
import cn.com.winning.ssgj.service.SysModFunService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysModFunServiceImpl implements SysModFunService {

    @Resource
    private SysModFunDao sysModFunDao;


    public Integer createSysModFun(SysModFun t) {
        return this.sysModFunDao.insertEntity(t);
    }

    public SysModFun getSysModFun(SysModFun t) {
        return this.sysModFunDao.selectEntity(t);
    }

    public Integer getSysModFunCount(SysModFun t) {
        return (Integer) this.sysModFunDao.selectEntityCount(t);
    }

    public List<SysModFun> getSysModFunList(SysModFun t) {
        return this.sysModFunDao.selectEntityList(t);
    }

    public int modifySysModFun(SysModFun t) {
        return this.sysModFunDao.updateEntity(t);
    }

    public int removeSysModFun(SysModFun t) {
        return this.sysModFunDao.deleteEntity(t);
    }

    public List<SysModFun> getSysModFunPaginatedList(SysModFun t) {
        return this.sysModFunDao.selectEntityPaginatedList(t);
    }

}
