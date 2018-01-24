package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDataInfoDao;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.service.SysDataInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysDataInfoServiceImpl implements SysDataInfoService {

    @Resource
    private SysDataInfoDao sysDataInfoDao;


    public Integer createSysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.insertEntity(t);
    }

    public SysDataInfo getSysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.selectEntity(t);
    }

    public Integer getSysDataInfoCount(SysDataInfo t) {
        return (Integer) this.sysDataInfoDao.selectEntityCount(t);
    }

    public List<SysDataInfo> getSysDataInfoList(SysDataInfo t) {
        return this.sysDataInfoDao.selectEntityList(t);
    }

    public int modifySysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.updateEntity(t);
    }

    public int removeSysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.deleteEntity(t);
    }

    public List<SysDataInfo> getSysDataInfoPaginatedList(SysDataInfo t) {
        return this.sysDataInfoDao.selectEntityPaginatedList(t);
    }

}
