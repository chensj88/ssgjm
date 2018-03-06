package cn.com.winning.ssgj.service.impl;

import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductShInfoDao;
import cn.com.winning.ssgj.domain.SysProductShInfo;
import cn.com.winning.ssgj.service.SysProductShInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductShInfoServiceImpl implements SysProductShInfoService {

    @Resource
    private SysProductShInfoDao sysProductShInfoDao;


    public Integer createSysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.insertEntity(t);
    }

    public SysProductShInfo getSysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.selectEntity(t);
    }

    public Integer getSysProductShInfoCount(SysProductShInfo t) {
        return (Integer) this.sysProductShInfoDao.selectEntityCount(t);
    }

    public List<SysProductShInfo> getSysProductShInfoList(SysProductShInfo t) {
        return this.sysProductShInfoDao.selectEntityList(t);
    }

    public int modifySysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.updateEntity(t);
    }

    public int removeSysProductShInfo(SysProductShInfo t) {
        return this.sysProductShInfoDao.deleteEntity(t);
    }

    public List<SysProductShInfo> getSysProductShInfoPaginatedList(SysProductShInfo t) {
        return this.sysProductShInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<String> getSoftwareHardwareInfoId(List<SysProductShInfo> shInfoList) {
        List<String> idList = new ArrayList<String>();
        for (SysProductShInfo shInfo : shInfoList) {
            idList.add(shInfo.getShId() +"");
        }
        return idList;
    }

    @Override
    public List<SysProductShInfo> getSysProductShInfoByIds(Integer pdId, String shIds) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("pdId",pdId);
        param.put("shIds",shIds);
        return this.sysProductShInfoDao.selectSysProductShInfoByIds(param);
    }

    @Override
    public int removeSysProductShInfoMapping(String idList, SysUserInfo userInfo) {
        String ids = StringUtil.generateSqlString(idList,"PD_ID","SH_ID");
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids",ids);
        param.put("user", userInfo.getId());
        return this.sysProductShInfoDao.removeSysProductShInfoMapping(param);
    }

    @Override
    public int addSysProductShInfoMapping(String idList, SysUserInfo userInfo) throws ParseException {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids",StringUtil.generateSqlString(idList,"PD_ID","SH_ID"));
        List<SysProductShInfo> updateList = this.sysProductShInfoDao.selectSysProductSHInfoByIdMap(param);
        List<String> idsList = new ArrayList<String>();
        for (SysProductShInfo info : updateList) {
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(userInfo.getId());
            info.setLastUpdateTime(new Date());
            this.sysProductShInfoDao.updateEntity(info);
            idsList.add(info.getPdId()+","+info.getShId());
        }

        List<String> addPBInfo = StringUtil.compareStringWithList(idList,idsList);

        for (String s : addPBInfo) {
            SysProductShInfo info = new SysProductShInfo();
            info.setPdId(Long.valueOf(s.split(",")[0]));
            info.setShId(Long.valueOf(s.split(",")[1]));
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(userInfo.getId());
            info.setLastUpdateTime(new Date());
            this.sysProductShInfoDao.insertEntity(info);
        }

        return 0;
    }

}
