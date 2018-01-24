package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFlowInfoDao;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.service.SysFlowInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysFlowInfoServiceImpl implements SysFlowInfoService {

    @Resource
    private SysFlowInfoDao sysFlowInfoDao;


    public Integer createSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.insertEntity(t);
    }

    public SysFlowInfo getSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntity(t);
    }

    public Integer getSysFlowInfoCount(SysFlowInfo t) {
        return (Integer) this.sysFlowInfoDao.selectEntityCount(t);
    }

    public List<SysFlowInfo> getSysFlowInfoList(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntityList(t);
    }

    public int modifySysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.updateEntity(t);
    }

    public int removeSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.deleteEntity(t);
    }

    public List<SysFlowInfo> getSysFlowInfoPaginatedList(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntityPaginatedList(t);
    }

}
