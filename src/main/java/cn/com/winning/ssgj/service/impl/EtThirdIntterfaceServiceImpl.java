package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtThirdIntterfaceDao;
import cn.com.winning.ssgj.domain.EtThirdIntterface;
import cn.com.winning.ssgj.service.EtThirdIntterfaceService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtThirdIntterfaceServiceImpl implements EtThirdIntterfaceService {

    @Resource
    private EtThirdIntterfaceDao etThirdIntterfaceDao;


    public Integer createEtThirdIntterface(EtThirdIntterface t) {
        return this.etThirdIntterfaceDao.insertEntity(t);
    }


    public EtThirdIntterface getEtThirdIntterface(EtThirdIntterface t) {
        return this.etThirdIntterfaceDao.selectEntity(t);
    }


    public Integer getEtThirdIntterfaceCount(EtThirdIntterface t) {
        return (Integer) this.etThirdIntterfaceDao.selectEntityCount(t);
    }


    public List<EtThirdIntterface> getEtThirdIntterfaceList(EtThirdIntterface t) {
        return this.etThirdIntterfaceDao.selectEntityList(t);
    }


    public int modifyEtThirdIntterface(EtThirdIntterface t) {
        return this.etThirdIntterfaceDao.updateEntity(t);
    }


    public int removeEtThirdIntterface(EtThirdIntterface t) {
        return this.etThirdIntterfaceDao.deleteEntity(t);
    }


    public List<EtThirdIntterface> getEtThirdIntterfacePaginatedList(EtThirdIntterface t) {
        return this.etThirdIntterfaceDao.selectEntityPaginatedList(t);
    }

    /**
     * 根据pmid获取接口分页信息
     *
     * @param etThirdIntterface
     * @return
     */
    @Override
    public List<EtThirdIntterface> selectEtThirdIntterfaceMergePageList(EtThirdIntterface etThirdIntterface) {
        return this.etThirdIntterfaceDao.selectEtThirdIntterfaceMergePageList(etThirdIntterface);
    }

    /**
     * 获取分页total
     *
     * @param etThirdIntterface
     * @return
     */
    @Override
    public Integer selectEtThirdIntterfaceMergeCount(EtThirdIntterface etThirdIntterface) {
        return this.etThirdIntterfaceDao.selectEtThirdIntterfaceMergeCount(etThirdIntterface);
    }

    /**
     * 根据pmid获取所有接口信息
     *
     * @param etThirdIntterface
     * @return
     */
    @Override
    public List<EtThirdIntterface> selectEtThirdIntterfaceMergeList(EtThirdIntterface etThirdIntterface) {
        return this.etThirdIntterfaceDao.selectEtThirdIntterfaceMergeList(etThirdIntterface);
    }

}
