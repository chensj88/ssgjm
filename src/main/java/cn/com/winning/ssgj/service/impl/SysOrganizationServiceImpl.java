package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysOrganizationDao;
import cn.com.winning.ssgj.domain.SysOrganization;
import cn.com.winning.ssgj.service.SysOrganizationService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysOrganizationServiceImpl implements SysOrganizationService {

    @Resource
    private SysOrganizationDao sysOrganizationDao;



    public Integer createSysOrganization(SysOrganization t) {
        return this.sysOrganizationDao.insertEntity(t);
    }


    public SysOrganization getSysOrganization(SysOrganization t) {
        return this.sysOrganizationDao.selectEntity(t);
    }


    public Integer getSysOrganizationCount(SysOrganization t) {
        return (Integer) this.sysOrganizationDao.selectEntityCount(t);
    }


    public List<SysOrganization> getSysOrganizationList(SysOrganization t) {
        return this.sysOrganizationDao.selectEntityList(t);
    }


    public int modifySysOrganization(SysOrganization t) {
        return this.sysOrganizationDao.updateEntity(t);
    }


    public int removeSysOrganization(SysOrganization t) {
        return this.sysOrganizationDao.deleteEntity(t);
    }


    public List<SysOrganization> getSysOrganizationPaginatedList(SysOrganization t) {
        return this.sysOrganizationDao.selectEntityPaginatedList(t);
    }

    @Override
    public SysOrganization getSysOrganizationById(Long id) {
        SysOrganization org = new SysOrganization();
        org.setId(id);
        return this.sysOrganizationDao.selectEntity(org);
    }

}
