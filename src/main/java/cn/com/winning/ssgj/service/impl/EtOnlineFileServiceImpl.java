package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtOnlineFileDao;
import cn.com.winning.ssgj.domain.EtOnlineFile;
import cn.com.winning.ssgj.service.EtOnlineFileService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtOnlineFileServiceImpl implements EtOnlineFileService {

    @Resource
    private EtOnlineFileDao etOnlineFileDao;


    public Integer createEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.insertEntity(t);
    }

    public EtOnlineFile getEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.selectEntity(t);
    }

    public Integer getEtOnlineFileCount(EtOnlineFile t) {
        return (Integer) this.etOnlineFileDao.selectEntityCount(t);
    }

    public List<EtOnlineFile> getEtOnlineFileList(EtOnlineFile t) {
        return this.etOnlineFileDao.selectEntityList(t);
    }

    public int modifyEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.updateEntity(t);
    }

    public int removeEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.deleteEntity(t);
    }

    public List<EtOnlineFile> getEtOnlineFilePaginatedList(EtOnlineFile t) {
        return this.etOnlineFileDao.selectEntityPaginatedList(t);
    }

}
