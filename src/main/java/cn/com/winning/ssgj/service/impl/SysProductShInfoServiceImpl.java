package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductShInfoDao;
import cn.com.winning.ssgj.domain.SysProductShInfo;
import cn.com.winning.ssgj.service.SysProductShInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductShInfoServiceImpl implements SysProductShInfoService {

    @Resource
    private SysProductShInfoDao sysProductShInfoDao;


    public Integer createSysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.insertEntity(t);
    }

    public SysProductShInfo getSysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.selectEntity(t);
    }

    public Integer getSysProductShInfoCount(SysProductShInfo t) {
        return (Integer) this.sysProductShInfoDao.selectEntityCount(t);
    }

    public List<SysProductShInfo> getSysProductShInfoList(SysProductShInfo t) {
        return this.sysProductShInfoDao.selectEntityList(t);
    }

    public int modifySysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.updateEntity(t);
    }

    public int removeSysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.deleteEntity(t);
    }

    public List<SysProductShInfo> getSysProductShInfoPaginatedList(SysProductShInfo t) {
        return this.sysProductShInfoDao.selectEntityPaginatedList(t);
    }

}
