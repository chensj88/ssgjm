package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysThirdInterfaceInfoDao;
import cn.com.winning.ssgj.domain.SysThirdInterfaceInfo;
import cn.com.winning.ssgj.service.SysThirdInterfaceInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysThirdInterfaceInfoServiceImpl implements SysThirdInterfaceInfoService {

    @Resource
    private SysThirdInterfaceInfoDao sysThirdInterfaceInfoDao;


    public Integer createSysThirdInterfaceInfo(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.insertEntity(t);
    }

    public SysThirdInterfaceInfo getSysThirdInterfaceInfo(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.selectEntity(t);
    }

    public Integer getSysThirdInterfaceInfoCount(SysThirdInterfaceInfo t) {
        return (Integer) this.sysThirdInterfaceInfoDao.selectEntityCount(t);
    }

    public List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoList(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.selectEntityList(t);
    }

    public int modifySysThirdInterfaceInfo(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.updateEntity(t);
    }

    public int removeSysThirdInterfaceInfo(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.deleteEntity(t);
    }

    public List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoPaginatedList(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public Integer getSysThirdInterfaceInfoCountForSelectiveKey(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.selectSysThirdInterfaceInfoCountByselective(t);
    }

    @Override
    public List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoPaginatedListForSelectiveKey(SysThirdInterfaceInfo t) {
        return this.sysThirdInterfaceInfoDao.selectSysThirdInterfaceInfoPaginatedListByselective(t);
    }

}
