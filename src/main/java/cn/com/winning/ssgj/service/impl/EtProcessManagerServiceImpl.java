package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtProcessManagerDao;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.service.EtProcessManagerService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtProcessManagerServiceImpl implements EtProcessManagerService {

    @Resource
    private EtProcessManagerDao etProcessManagerDao;


    public Integer createEtProcessManager(EtProcessManager t) {
        return this.etProcessManagerDao.insertEntity(t);
    }



    public EtProcessManager getEtProcessManager(EtProcessManager t) {
        return this.etProcessManagerDao.selectEntity(t);
    }



    public Integer getEtProcessManagerCount(EtProcessManager t) {
        return (Integer) this.etProcessManagerDao.selectEntityCount(t);
    }



    public List<EtProcessManager> getEtProcessManagerList(EtProcessManager t) {
        return this.etProcessManagerDao.selectEntityList(t);
    }



    public int modifyEtProcessManager(EtProcessManager t) {
        return this.etProcessManagerDao.updateEntity(t);
    }



    public int removeEtProcessManager(EtProcessManager t) {
        return this.etProcessManagerDao.deleteEntity(t);
    }


    public List<EtProcessManager> getEtProcessManagerPaginatedList(EtProcessManager t) {
        return this.etProcessManagerDao.selectEntityPaginatedList(t);
    }

}
