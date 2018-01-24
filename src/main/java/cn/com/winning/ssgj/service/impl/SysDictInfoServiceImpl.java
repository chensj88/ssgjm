package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDictInfoDao;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.service.SysDictInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysDictInfoServiceImpl implements SysDictInfoService {

    @Resource
    private SysDictInfoDao sysDictInfoDao;


    public Integer createSysDictInfo(SysDictInfo t) {
        return this.sysDictInfoDao.insertEntity(t);
    }

    public SysDictInfo getSysDictInfo(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntity(t);
    }

    public Integer getSysDictInfoCount(SysDictInfo t) {
        return (Integer) this.sysDictInfoDao.selectEntityCount(t);
    }

    public List<SysDictInfo> getSysDictInfoList(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityList(t);
    }

    public int modifySysDictInfo(SysDictInfo t) {
        return this.sysDictInfoDao.updateEntity(t);
    }

    public int removeSysDictInfo(SysDictInfo t) {
        return this.sysDictInfoDao.deleteEntity(t);
    }

    public List<SysDictInfo> getSysDictInfoPaginatedList(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityPaginatedList(t);
    }

}
