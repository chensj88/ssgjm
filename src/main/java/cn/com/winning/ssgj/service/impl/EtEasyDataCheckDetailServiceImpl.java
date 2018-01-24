package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtEasyDataCheckDetailDao;
import cn.com.winning.ssgj.domain.EtEasyDataCheckDetail;
import cn.com.winning.ssgj.service.EtEasyDataCheckDetailService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtEasyDataCheckDetailServiceImpl implements EtEasyDataCheckDetailService {

    @Resource
    private EtEasyDataCheckDetailDao etEasyDataCheckDetailDao;


    public Integer createEtEasyDataCheckDetail(EtEasyDataCheckDetail t) {
        return this.etEasyDataCheckDetailDao.insertEntity(t);
    }

    public EtEasyDataCheckDetail getEtEasyDataCheckDetail(EtEasyDataCheckDetail t) {
        return this.etEasyDataCheckDetailDao.selectEntity(t);
    }

    public Integer getEtEasyDataCheckDetailCount(EtEasyDataCheckDetail t) {
        return (Integer) this.etEasyDataCheckDetailDao.selectEntityCount(t);
    }

    public List<EtEasyDataCheckDetail> getEtEasyDataCheckDetailList(EtEasyDataCheckDetail t) {
        return this.etEasyDataCheckDetailDao.selectEntityList(t);
    }

    public int modifyEtEasyDataCheckDetail(EtEasyDataCheckDetail t) {
        return this.etEasyDataCheckDetailDao.updateEntity(t);
    }

    public int removeEtEasyDataCheckDetail(EtEasyDataCheckDetail t) {
        return this.etEasyDataCheckDetailDao.deleteEntity(t);
    }

    public List<EtEasyDataCheckDetail> getEtEasyDataCheckDetailPaginatedList(EtEasyDataCheckDetail t) {
        return this.etEasyDataCheckDetailDao.selectEntityPaginatedList(t);
    }

}
