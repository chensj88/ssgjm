package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysParamsDao;
import cn.com.winning.ssgj.domain.SysParams;
import cn.com.winning.ssgj.service.SysParamsService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysParamsServiceImpl implements SysParamsService {

    @Resource
    private SysParamsDao sysParamsDao;


    public Integer createSysParams(SysParams t) {
        return this.sysParamsDao.insertEntity(t);
    }

    public SysParams getSysParams(SysParams t) {
        return this.sysParamsDao.selectEntity(t);
    }

    public Integer getSysParamsCount(SysParams t) {
        return (Integer) this.sysParamsDao.selectEntityCount(t);
    }

    public List<SysParams> getSysParamsList(SysParams t) {
        return this.sysParamsDao.selectEntityList(t);
    }

    public int modifySysParams(SysParams t) {
        return this.sysParamsDao.updateEntity(t);
    }

    public int removeSysParams(SysParams t) {
        return this.sysParamsDao.deleteEntity(t);
    }

    public List<SysParams> getSysParamsPaginatedList(SysParams t) {
        return this.sysParamsDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<SysParams> getSysParamsPageListBySelectiveKey(SysParams params) {
        return this.sysParamsDao.selectSysParamsPageListBySelectiveKey(params);
    }

    @Override
    public int getSysParamsPageCountBySelectiveKey(SysParams params) {
        return this.sysParamsDao.selectSysParamsPageCountBySelectiveKey(params);
    }

}
