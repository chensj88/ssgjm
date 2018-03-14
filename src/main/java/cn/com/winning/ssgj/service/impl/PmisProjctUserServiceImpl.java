package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProjctUserDao;
import cn.com.winning.ssgj.domain.PmisProjctUser;
import cn.com.winning.ssgj.service.PmisProjctUserService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class PmisProjctUserServiceImpl implements PmisProjctUserService {

    @Resource
    private PmisProjctUserDao pmisProjctUserDao;



    public Integer createPmisProjctUser(PmisProjctUser t) {
        return this.pmisProjctUserDao.insertEntity(t);
    }


    public PmisProjctUser getPmisProjctUser(PmisProjctUser t) {
        return this.pmisProjctUserDao.selectEntity(t);
    }


    public Integer getPmisProjctUserCount(PmisProjctUser t) {
        return (Integer) this.pmisProjctUserDao.selectEntityCount(t);
    }


    public List<PmisProjctUser> getPmisProjctUserList(PmisProjctUser t) {
        return this.pmisProjctUserDao.selectEntityList(t);
    }


    public int modifyPmisProjctUser(PmisProjctUser t) {
        return this.pmisProjctUserDao.updateEntity(t);
    }


    public int removePmisProjctUser(PmisProjctUser t) {
        return this.pmisProjctUserDao.deleteEntity(t);
    }


    public List<PmisProjctUser> getPmisProjctUserPaginatedList(PmisProjctUser t) {
        return this.pmisProjctUserDao.selectEntityPaginatedList(t);
    }

}
