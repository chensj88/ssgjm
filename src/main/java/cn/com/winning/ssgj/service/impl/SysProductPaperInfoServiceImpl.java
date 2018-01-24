package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductPaperInfoDao;
import cn.com.winning.ssgj.domain.SysProductPaperInfo;
import cn.com.winning.ssgj.service.SysProductPaperInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductPaperInfoServiceImpl implements SysProductPaperInfoService {

    @Resource
    private SysProductPaperInfoDao sysProductPaperInfoDao;


    public Integer createSysProductPaperInfo(SysProductPaperInfo t) {
        return this.sysProductPaperInfoDao.insertEntity(t);
    }

    public SysProductPaperInfo getSysProductPaperInfo(SysProductPaperInfo t) {
        return this.sysProductPaperInfoDao.selectEntity(t);
    }

    public Integer getSysProductPaperInfoCount(SysProductPaperInfo t) {
        return (Integer) this.sysProductPaperInfoDao.selectEntityCount(t);
    }

    public List<SysProductPaperInfo> getSysProductPaperInfoList(SysProductPaperInfo t) {
        return this.sysProductPaperInfoDao.selectEntityList(t);
    }

    public int modifySysProductPaperInfo(SysProductPaperInfo t) {
        return this.sysProductPaperInfoDao.updateEntity(t);
    }

    public int removeSysProductPaperInfo(SysProductPaperInfo t) {
        return this.sysProductPaperInfoDao.deleteEntity(t);
    }

    public List<SysProductPaperInfo> getSysProductPaperInfoPaginatedList(SysProductPaperInfo t) {
        return this.sysProductPaperInfoDao.selectEntityPaginatedList(t);
    }

}
