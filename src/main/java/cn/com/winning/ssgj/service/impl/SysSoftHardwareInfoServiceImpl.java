package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysSoftHardwareInfoDao;
import cn.com.winning.ssgj.domain.SysSoftHardwareInfo;
import cn.com.winning.ssgj.service.SysSoftHardwareInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysSoftHardwareInfoServiceImpl implements SysSoftHardwareInfoService {

    @Resource
    private SysSoftHardwareInfoDao sysSoftHardwareInfoDao;


    public Integer createSysSoftHardwareInfo(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.insertEntity(t);
    }

    public SysSoftHardwareInfo getSysSoftHardwareInfo(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.selectEntity(t);
    }

    public Integer getSysSoftHardwareInfoCount(SysSoftHardwareInfo t) {
        return (Integer) this.sysSoftHardwareInfoDao.selectEntityCount(t);
    }

    public List<SysSoftHardwareInfo> getSysSoftHardwareInfoList(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.selectEntityList(t);
    }

    public int modifySysSoftHardwareInfo(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.updateEntity(t);
    }

    public int removeSysSoftHardwareInfo(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.deleteEntity(t);
    }

    public List<SysSoftHardwareInfo> getSysSoftHardwareInfoPaginatedList(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public Integer getSysSoftHardwareInfoCountForSelectiveKey(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.selectSysSoftHardwareInfoCountByselective(t);
    }

    @Override
    public List<SysSoftHardwareInfo> getSysSoftHardwareInfoPaginatedListForSelectiveKey(SysSoftHardwareInfo t) {
        return this.sysSoftHardwareInfoDao.selectSysSoftHardwareInfoPaginatedListByselective(t);
    }

}
