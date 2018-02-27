package cn.com.winning.ssgj.service.impl;

import java.util.*;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysProductDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SSGJHelper ssgjHelper;

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

    @Override
    public List<Long> getRoleIdList(SysRoleUser roleUser) {
        List<SysRoleUser> roleUserList = this.sysRoleUserDao.selectEntityList(roleUser);
        List<Long> roleIdList = new ArrayList<Long>();
        for (SysRoleUser sysRoleUser : roleUserList) {
            roleIdList.add(sysRoleUser.getRoleId());
        }
        return roleIdList;
    }

    @Override
    public void createSysRoleUserByIdString(String idStr) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids", StringUtil.generateDeleteSqlString(idStr,"USER_ID"));
        System.out.println(StringUtil.generateDeleteSqlString(idStr,"USER_ID"));
        this.sysRoleUserDao.deleteSysRoleUserForIds(param);
        List<String>  addUserRoleInfo = StringUtil.generateStringList(idStr);
        for (String s : addUserRoleInfo) {
            SysRoleUser info = new SysRoleUser();
            info.setUserId(Long.valueOf(s.split(",")[0]));
            info.setRoleId(Long.valueOf(s.split(",")[1]));
            info.setId(ssgjHelper.createRoleUserId());
            this.sysRoleUserDao.insertEntity(info);
        }
    }

}
