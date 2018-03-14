package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtOnlineUserDao;
import cn.com.winning.ssgj.domain.EtOnlineUser;
import cn.com.winning.ssgj.service.EtOnlineUserService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtOnlineUserServiceImpl implements EtOnlineUserService {

    @Resource
    private EtOnlineUserDao etOnlineUserDao;



    public Integer createEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.insertEntity(t);
    }


    public EtOnlineUser getEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.selectEntity(t);
    }


    public Integer getEtOnlineUserCount(EtOnlineUser t) {
        return (Integer) this.etOnlineUserDao.selectEntityCount(t);
    }


    public List<EtOnlineUser> getEtOnlineUserList(EtOnlineUser t) {
        return this.etOnlineUserDao.selectEntityList(t);
    }


    public int modifyEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.updateEntity(t);
    }


    public int removeEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.deleteEntity(t);
    }


    public List<EtOnlineUser> getEtOnlineUserPaginatedList(EtOnlineUser t) {
        return this.etOnlineUserDao.selectEntityPaginatedList(t);
    }

}
