package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysRoleUserDao;
import cn.com.winning.ssgj.domain.SysRoleUser;
import cn.com.winning.ssgj.service.SysRoleUserService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Resource
    private SysRoleUserDao sysRoleUserDao;


    public Integer createSysRoleUser(SysRoleUser t) {
        return this.sysRoleUserDao.insertEntity(t);
    }

    public SysRoleUser getSysRoleUser(SysRoleUser t) {
        return this.sysRoleUserDao.selectEntity(t);
    }

    public Integer getSysRoleUserCount(SysRoleUser t) {
        return (Integer) this.sysRoleUserDao.selectEntityCount(t);
    }

    public List<SysRoleUser> getSysRoleUserList(SysRoleUser t) {
        return this.sysRoleUserDao.selectEntityList(t);
    }

    public int modifySysRoleUser(SysRoleUser t) {
        return this.sysRoleUserDao.updateEntity(t);
    }

    public int removeSysRoleUser(SysRoleUser t) {
        return this.sysRoleUserDao.deleteEntity(t);
    }

    public List<SysRoleUser> getSysRoleUserPaginatedList(SysRoleUser t) {
        return this.sysRoleUserDao.selectEntityPaginatedList(t);
    }

}
