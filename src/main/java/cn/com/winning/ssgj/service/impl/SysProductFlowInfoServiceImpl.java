package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductFlowInfoDao;
import cn.com.winning.ssgj.domain.SysProductFlowInfo;
import cn.com.winning.ssgj.service.SysProductFlowInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductFlowInfoServiceImpl implements SysProductFlowInfoService {

    @Resource
    private SysProductFlowInfoDao sysProductFlowInfoDao;


    public Integer createSysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.insertEntity(t);
    }

    public SysProductFlowInfo getSysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.selectEntity(t);
    }

    public Integer getSysProductFlowInfoCount(SysProductFlowInfo t) {
        return (Integer) this.sysProductFlowInfoDao.selectEntityCount(t);
    }

    public List<SysProductFlowInfo> getSysProductFlowInfoList(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.selectEntityList(t);
    }

    public int modifySysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.updateEntity(t);
    }

    public int removeSysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.deleteEntity(t);
    }

    public List<SysProductFlowInfo> getSysProductFlowInfoPaginatedList(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.selectEntityPaginatedList(t);
    }

}
