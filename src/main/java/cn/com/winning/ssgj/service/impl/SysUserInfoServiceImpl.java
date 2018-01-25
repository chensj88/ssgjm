package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.annoation.ILog;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysUserInfoDao;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.service.SysUserInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Resource
    private SysUserInfoDao sysUserInfoDao;

    @ILog(operationName="添加用户",operationType="addUser")
    public Integer createSysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.insertEntity(t);
    }

    @ILog(operationName="通过用户ID查询用户信息",operationType="getUserByUserId")
    public SysUserInfo getSysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.selectEntity(t);
    }
    @ILog(operationName="用户信息列表",operationType="list")
    public Integer getSysUserInfoCount(SysUserInfo t) {
        return (Integer) this.sysUserInfoDao.selectEntityCount(t);
    }

    public List<SysUserInfo> getSysUserInfoList(SysUserInfo t) {
        return this.sysUserInfoDao.selectEntityList(t);
    }
    @ILog(operationName="通过用户ID删除或更新用户信息",operationType="deleteUserById")
    public int modifySysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.updateEntity(t);
    }
    @ILog(operationName="通过用户ID删除用户信息",operationType="deleteUserById")
    public int removeSysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.deleteEntity(t);
    }
    @ILog(operationName="用户信息列表",operationType="list")
    public List<SysUserInfo> getSysUserInfoPaginatedList(SysUserInfo t) {
        return this.sysUserInfoDao.selectEntityPaginatedList(t);
    }

}
