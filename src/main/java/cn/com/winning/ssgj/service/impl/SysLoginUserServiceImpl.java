package cn.com.winning.ssgj.service.impl;

import cn.com.winning.ssgj.dao.SysLoginUserDao;
import cn.com.winning.ssgj.domain.SysLoginUser;
import cn.com.winning.ssgj.service.SysLoginUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.service.impl
 * @date 2018-04-04 20:00
 */
@Service
public class SysLoginUserServiceImpl implements SysLoginUserService {

    @Resource
    private SysLoginUserDao sysLoginUserDao;


    @Override
    public Integer createSysLoginUser(SysLoginUser t) {
        return this.sysLoginUserDao.insertEntity(t);
    }

    @Override
    public int modifySysLoginUser(SysLoginUser t) {
        return this.sysLoginUserDao.updateEntity(t);
    }

    @Override
    public int removeSysLoginUser(SysLoginUser t) {
        return this.sysLoginUserDao.deleteEntity(t);
    }

    @Override
    public SysLoginUser getSysLoginUser(SysLoginUser t) {
        return this.sysLoginUserDao.selectEntity(t);
    }

    @Override
    public List<SysLoginUser> getSysLoginUserList(SysLoginUser t) {
        return this.sysLoginUserDao.selectEntityList(t);
    }

    @Override
    public Integer getSysLoginUserCount(SysLoginUser t) {
        return (Integer) this.sysLoginUserDao.selectEntityCount(t);
    }

    @Override
    public List<SysLoginUser> getSysLoginUserPaginatedList(SysLoginUser t) {
        return this.sysLoginUserDao.selectEntityPaginatedList(t);
    }

    @Override
    public Integer createSysLoginUserBySelectiveKey(String token,Long userId) {
        SysLoginUser user = new SysLoginUser();
        user.setToken(token);
        user.setUserId(userId);
        user.setLoginTime(new Timestamp(new Date().getTime()));
        return this.sysLoginUserDao.insertEntity(user);
    }

    @Override
    public SysLoginUser getSysLoginUserBySelectiveKey(String token) {
        SysLoginUser user = new SysLoginUser();
        user.setToken(token);
        return this.sysLoginUserDao.selectEntity(user);
    }

}
