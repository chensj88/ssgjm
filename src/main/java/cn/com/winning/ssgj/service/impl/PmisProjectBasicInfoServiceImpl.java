package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.PmisProjctUser;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProjectBasicInfoDao;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.service.PmisProjectBasicInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class PmisProjectBasicInfoServiceImpl implements PmisProjectBasicInfoService {

    @Resource
    private PmisProjectBasicInfoDao pmisProjectBasicInfoDao;


    public Integer createPmisProjectBasicInfo(PmisProjectBasicInfo t) {
        return this.pmisProjectBasicInfoDao.insertEntity(t);
    }


    public PmisProjectBasicInfo getPmisProjectBasicInfo(PmisProjectBasicInfo t) {
        return this.pmisProjectBasicInfoDao.selectEntity(t);
    }


    public Integer getPmisProjectBasicInfoCount(PmisProjectBasicInfo t) {
        return (Integer) this.pmisProjectBasicInfoDao.selectEntityCount(t);
    }


    public List<PmisProjectBasicInfo> getPmisProjectBasicInfoList(PmisProjectBasicInfo t) {
        return this.pmisProjectBasicInfoDao.selectEntityList(t);
    }


    public int modifyPmisProjectBasicInfo(PmisProjectBasicInfo t) {
        return this.pmisProjectBasicInfoDao.updateEntity(t);
    }


    public int removePmisProjectBasicInfo(PmisProjectBasicInfo t) {
        return this.pmisProjectBasicInfoDao.deleteEntity(t);
    }


    public List<PmisProjectBasicInfo> getPmisProjectBasicInfoPaginatedList(PmisProjectBasicInfo t) {
        return this.pmisProjectBasicInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<PmisProjectBasicInfo> getUserProcjectBasicInfo(List<PmisProjctUser> userList) {
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setJhzt(Constants.PMIS.JHZXZT_RUNING);
        basicInfo.setFwlx(Constants.PMIS.FWLX_SSXM);
        List<String> idString = new ArrayList<String>();
        for (PmisProjctUser projctUser : userList) {
            idString.add(projctUser.getXmlcb()+"");
        }
        basicInfo.getMap().put("pks",idString);

        return this.pmisProjectBasicInfoDao.selectUserProcjectBasicInfo(basicInfo);
    }

    @Override
    public List<PmisProjectBasicInfo> getPmisProjectBasicByKHXXAndIds(PmisProjectBasicInfo project) {
        return this.pmisProjectBasicInfoDao.selectPmisProjectBasicByKHXXAndIds(project);
    }

    @Override
    public PmisProjectBasicInfo queryPmisProjectBasicInfoByProjectId(long pmId) {
        return this.pmisProjectBasicInfoDao.queryPmisProjectBasicInfoByProjectId(pmId);
    }

}
