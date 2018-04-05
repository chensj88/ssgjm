package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtUserLookProjectDao;
import cn.com.winning.ssgj.domain.EtUserLookProject;
import cn.com.winning.ssgj.service.EtUserLookProjectService;

/**
 * SSGJ
 *
 * @author SSGJ
 * @date 2018-01-19 16:52:12
 */
@Service
public class EtUserLookProjectServiceImpl implements EtUserLookProjectService {

    @Resource
    private EtUserLookProjectDao etUserLookProjectDao;



    public Integer createEtUserLookProject(EtUserLookProject t) {
        return this.etUserLookProjectDao.insertEntity(t);
    }


    public EtUserLookProject getEtUserLookProject(EtUserLookProject t) {
        return this.etUserLookProjectDao.selectEntity(t);
    }


    public Integer getEtUserLookProjectCount(EtUserLookProject t) {
        return (Integer) this.etUserLookProjectDao.selectEntityCount(t);
    }


    public List<EtUserLookProject> getEtUserLookProjectList(EtUserLookProject t) {
        return this.etUserLookProjectDao.selectEntityList(t);
    }


    public int modifyEtUserLookProject(EtUserLookProject t) {
        return this.etUserLookProjectDao.updateEntity(t);
    }


    public int removeEtUserLookProject(EtUserLookProject t) {
        return this.etUserLookProjectDao.deleteEntity(t);
    }


    public List<EtUserLookProject> getEtUserLookProjectPaginatedList(EtUserLookProject t) {
        return this.etUserLookProjectDao.selectEntityPaginatedList(t);
    }

}
