package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductPaperInfoDao;
import cn.com.winning.ssgj.domain.SysProductPaperInfo;
import cn.com.winning.ssgj.service.SysProductPaperInfoService;

/**
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

    @Override

    public List<String> getSysPaperInfoIds(List<SysProductPaperInfo> paperInfoList) {
        List<String> idList = new ArrayList<String>();
        for (SysProductPaperInfo sysProductPaperInfo : paperInfoList) {
            idList.add(sysProductPaperInfo.getrId() + "");
        }
        return idList;
    }

    @Override

    public List<SysProductPaperInfo> getSysProductPaperInfoByIds(Integer pdId, String reportids) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pdId", pdId);
        param.put("reportIds", reportids);
        return this.sysProductPaperInfoDao.selectSysProductPaperInfoByIds(param);
    }

    @Override

    public int removeSysProductPaperInfoMapping(String idList, SysUserInfo userInfo) {
        String ids = StringUtil.generateSqlString(idList, "PD_ID", "R_ID");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", ids);
        param.put("user", userInfo.getId());
        return this.sysProductPaperInfoDao.removeSysProductPaperInfoMapping(param);
    }

    @Override

    public int addSysProductPaperInfoMapping(String idList, SysUserInfo userInfo) throws ParseException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", StringUtil.generateSqlString(idList, "PD_ID", "R_ID"));
        List<SysProductPaperInfo> updateList = this.sysProductPaperInfoDao.selectSysProductPaperInfoByIdMap(param);
        List<String> idsList = new ArrayList<String>();
        for (SysProductPaperInfo info : updateList) {
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(userInfo.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductPaperInfoDao.updateEntity(info);
            idsList.add(info.getPdId() + "," + info.getrId());
        }

        List<String> addPBInfo = StringUtil.compareStringWithList(idList, idsList);

        for (String s : addPBInfo) {
            SysProductPaperInfo info = new SysProductPaperInfo();
            info.setPdId(Long.valueOf(s.split(",")[0]));
            info.setrId(Long.valueOf(s.split(",")[1]));
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(userInfo.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductPaperInfoDao.insertEntity(info);
        }
        return 0;
    }

}
