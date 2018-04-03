package cn.com.winning.ssgj.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;

import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.util.FtpUtils;
import cn.com.winning.ssgj.base.util.SFtpUtils;
import cn.com.winning.ssgj.dao.SysDictInfoDao;
import cn.com.winning.ssgj.domain.SysDictInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysTrainVideoRepoDao;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.service.SysTrainVideoRepoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-28 09:00:21
 */
@Service
public class SysTrainVideoRepoServiceImpl implements SysTrainVideoRepoService {

    @Resource
    private SysTrainVideoRepoDao sysTrainVideoRepoDao;
    @Resource
    private SysDictInfoDao sysDictInfoDao;



    public Integer createSysTrainVideoRepo(SysTrainVideoRepo t) {
        t = initObjectValue(t);
        return this.sysTrainVideoRepoDao.insertEntity(t);
    }


    public SysTrainVideoRepo getSysTrainVideoRepo(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.selectEntity(t);
    }


    public Integer getSysTrainVideoRepoCount(SysTrainVideoRepo t) {
        return (Integer) this.sysTrainVideoRepoDao.selectEntityCount(t);
    }


    public List<SysTrainVideoRepo> getSysTrainVideoRepoList(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.selectEntityList(t);
    }


    public int modifySysTrainVideoRepo(SysTrainVideoRepo t) {
        t = initObjectValue(t);
        return this.sysTrainVideoRepoDao.updateEntity(t);
    }


    public int removeSysTrainVideoRepo(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.deleteEntity(t);
    }


    public List<SysTrainVideoRepo> getSysTrainVideoRepoPaginatedList(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.selectEntityPaginatedList(t);
    }

    @Override

    public Integer getSysTrainVideoRepoCountBySelective(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.selectSysTrainVideoRepoCountBySelective(t);
    }

    @Override

    public List<SysTrainVideoRepo> getSysTrainVideoRepoPageListBySelective(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.selectSysTrainVideoRepoPageListBySelective(t);
    }


    public List<SysTrainVideoRepo> getSysTrainVideoRepoTypeList(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.selectSysTrainVideoRepoTypeList(t);
    }

    @Override

    public boolean existVideoName(SysTrainVideoRepo repo) {
        int count = this.sysTrainVideoRepoDao.selectCountByVideoName(repo);
        if (count > 0) {
            return false;
        }
        return true;
    }


    public List<SysTrainVideoRepo> getSysTrainVideoWithRecoedList(SysTrainVideoRepo t) {
        return this.sysTrainVideoRepoDao.selectSysTrainVideoWithRecoedList(t);
    }

    @Override

    public boolean deleteSysTrainVideoRepo(SysTrainVideoRepo repo) {
        repo = this.sysTrainVideoRepoDao.selectEntity(repo);
        String msg = "";
        boolean ftpStatus = false;
        if (repo.getRemotePath() == null) {
            ftpStatus = true;
        } else {
            if (port == 21) {
                try {
                    ftpStatus = FtpUtils.deleteFtpFile(repo.getRemotePath());
                } catch (IOException e) {
                    msg = e.getMessage();
                }
            } else if (port == 22) {
                try {
                    SFtpUtils.rmFile(repo.getRemotePath());
                    ftpStatus = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    ftpStatus = false;
                    msg = e.getMessage();
                }
            }
        }

        if (msg != "") {
            throw new SSGJException(msg);
        }
        System.out.println(msg);
        System.out.println(ftpStatus);
        /*if (ftpStatus) {*/
            this.sysTrainVideoRepoDao.deleteEntity(repo);
       /* }*/
        return ftpStatus;
    }

    /**
     * 设置字典显示值
     *
     * @param repo
     * @return
     */
    private SysTrainVideoRepo initObjectValue(SysTrainVideoRepo repo) {
        if (!Constants.VIDEO_TYPE_OF_CUSTOMER.equals(repo.getVideoType())) {
            repo.setVideoCType(null);
        } else {
            SysDictInfo dictInfo = getDictLabel("videoCType", repo.getVideoCType());
            repo.setVideoCLabel(dictInfo.getDictLabel());
        }
        SysDictInfo dictInfo = getDictLabel("videoType", repo.getVideoType());
        repo.setTypeLabel(dictInfo.getDictLabel());
        repo.setDictDesc(dictInfo.getDictDesc());
        return repo;
    }

    private SysDictInfo getDictLabel(String dictType, String dictValue) {
        SysDictInfo dictInfo = new SysDictInfo();
        dictInfo.setDictCode(dictType);
        dictInfo.setDictValue(dictValue);
        return this.sysDictInfoDao.selectEntity(dictInfo);
    }
}
