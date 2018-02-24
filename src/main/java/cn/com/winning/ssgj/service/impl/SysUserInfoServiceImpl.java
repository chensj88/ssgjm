package cn.com.winning.ssgj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.expand.FlotDataInfo;
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

    @ILog(operationName="按照条件查询List总数",operationType="querylistCount")
    @Override
    public Integer getSysUserInfoQueryCount(SysUserInfo t) {
        return this.sysUserInfoDao.selectSysUserInfoQueryCount(t);
    }

    @ILog(operationName="按照条件查询List",operationType="querylist")
    @Override
    public List<SysUserInfo> getSysUserInfoQueryPaginatedList(SysUserInfo t) {
        return this.sysUserInfoDao.selectSysUserInfoQueryPaginatedList(t);
    }

    @Override
    public List<FlotDataInfo> countUserInfoByType() {
        List<FlotDataInfo> userinfos = this.sysUserInfoDao.countUserInfoByType();
        for (FlotDataInfo userinfo : userinfos) {
            if (Constants.User.USER_TYPE_ADMIN_LABEL.equals(userinfo.getLabel())){
                userinfo.setColor("#DA5430");
            }else if(Constants.User.USER_TYPE_COMPANY_LABEL.equals(userinfo.getLabel())){
                userinfo.setColor("#68BC31");
            }else if(Constants.User.USER_TYPE_HOSPITAL_LABEL.equals(userinfo.getLabel())){
                userinfo.setColor("#2091CF");
            }
        }
        return userinfos;
    }

}
