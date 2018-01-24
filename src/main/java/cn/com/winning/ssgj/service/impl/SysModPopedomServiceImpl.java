package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModPopedomDao;
import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.service.SysModPopedomService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysModPopedomServiceImpl implements SysModPopedomService {

    @Resource
    private SysModPopedomDao sysModPopedomDao;


    public Integer createSysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.insertEntity(t);
    }

    public SysModPopedom getSysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.selectEntity(t);
    }

    public Integer getSysModPopedomCount(SysModPopedom t) {
        return (Integer) this.sysModPopedomDao.selectEntityCount(t);
    }

    public List<SysModPopedom> getSysModPopedomList(SysModPopedom t) {
        return this.sysModPopedomDao.selectEntityList(t);
    }

    public int modifySysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.updateEntity(t);
    }

    public int removeSysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.deleteEntity(t);
    }

    public List<SysModPopedom> getSysModPopedomPaginatedList(SysModPopedom t) {
        return this.sysModPopedomDao.selectEntityPaginatedList(t);
    }

}
