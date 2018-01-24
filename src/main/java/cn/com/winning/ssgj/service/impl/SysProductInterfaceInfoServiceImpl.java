package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductInterfaceInfoDao;
import cn.com.winning.ssgj.domain.SysProductInterfaceInfo;
import cn.com.winning.ssgj.service.SysProductInterfaceInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductInterfaceInfoServiceImpl implements SysProductInterfaceInfoService {

    @Resource
    private SysProductInterfaceInfoDao sysProductInterfaceInfoDao;


    public Integer createSysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.insertEntity(t);
    }

    public SysProductInterfaceInfo getSysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.selectEntity(t);
    }

    public Integer getSysProductInterfaceInfoCount(SysProductInterfaceInfo t) {
        return (Integer) this.sysProductInterfaceInfoDao.selectEntityCount(t);
    }

    public List<SysProductInterfaceInfo> getSysProductInterfaceInfoList(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.selectEntityList(t);
    }

    public int modifySysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.updateEntity(t);
    }

    public int removeSysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.deleteEntity(t);
    }

    public List<SysProductInterfaceInfo> getSysProductInterfaceInfoPaginatedList(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.selectEntityPaginatedList(t);
    }

}
