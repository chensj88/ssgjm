package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDataPopedomDao;
import cn.com.winning.ssgj.domain.SysDataPopedom;
import cn.com.winning.ssgj.service.SysDataPopedomService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysDataPopedomServiceImpl implements SysDataPopedomService {

    @Resource
    private SysDataPopedomDao sysDataPopedomDao;



    public Integer createSysDataPopedom(SysDataPopedom t) {
        return this.sysDataPopedomDao.insertEntity(t);
    }


    public SysDataPopedom getSysDataPopedom(SysDataPopedom t) {
        return this.sysDataPopedomDao.selectEntity(t);
    }


    public Integer getSysDataPopedomCount(SysDataPopedom t) {
        return (Integer) this.sysDataPopedomDao.selectEntityCount(t);
    }


    public List<SysDataPopedom> getSysDataPopedomList(SysDataPopedom t) {
        return this.sysDataPopedomDao.selectEntityList(t);
    }


    public int modifySysDataPopedom(SysDataPopedom t) {
        return this.sysDataPopedomDao.updateEntity(t);
    }


    public int removeSysDataPopedom(SysDataPopedom t) {
        return this.sysDataPopedomDao.deleteEntity(t);
    }


    public List<SysDataPopedom> getSysDataPopedomPaginatedList(SysDataPopedom t) {
        return this.sysDataPopedomDao.selectEntityPaginatedList(t);
    }

}
