package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysProductDataInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductInterfaceInfoDao;
import cn.com.winning.ssgj.domain.SysProductInterfaceInfo;
import cn.com.winning.ssgj.service.SysProductInterfaceInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductInterfaceInfoServiceImpl implements SysProductInterfaceInfoService {

    @Resource
    private SysProductInterfaceInfoDao sysProductInterfaceInfoDao;



    public Integer createSysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.insertEntity(t);
    }


    public SysProductInterfaceInfo getSysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.selectEntity(t);
    }


    public Integer getSysProductInterfaceInfoCount(SysProductInterfaceInfo t) {
        return (Integer) this.sysProductInterfaceInfoDao.selectEntityCount(t);
    }


    public List<SysProductInterfaceInfo> getSysProductInterfaceInfoList(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.selectEntityList(t);
    }


    public int modifySysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.updateEntity(t);
    }


    public int removeSysProductInterfaceInfo(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.deleteEntity(t);
    }


    public List<SysProductInterfaceInfo> getSysProductInterfaceInfoPaginatedList(SysProductInterfaceInfo t) {
        return this.sysProductInterfaceInfoDao.selectEntityPaginatedList(t);
    }

    /**
     * 从List对象集合中生成接口ID的List
     *
     * @param interfaceInfoList
     * @return interIds
     */
    @Override

    public List<String> getInterfaceIds(List<SysProductInterfaceInfo> interfaceInfoList) {
        List<String> interIds = new ArrayList<String>();
        for (SysProductInterfaceInfo productInterfaceInfo : interfaceInfoList) {
            interIds.add(productInterfaceInfo.getInterId() + "");
        }
        return interIds;
    }

    @Override

    public List<SysProductInterfaceInfo> getSysProductInterfaceInfoByIds(Integer pdId, String interIds) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pdId", pdId);
        param.put("interIds", interIds);
        return this.sysProductInterfaceInfoDao.selectSysProductInterfaceInfoByIds(param);
    }


    @Override

    public void removeProductInterInfo(String idList) {
        String ids = StringUtil.generateSqlString(idList, "PD_ID", "INTER_ID");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", ids);
        param.put("user", ((SysUserInfo) SecurityUtils.getSubject().getPrincipal()).getId());
        this.sysProductInterfaceInfoDao.removeSysProductInterInfoByIds(param);
    }

    @Override

    public void addSysProductInterfaceInfoMapping(String idList, SysUserInfo userInfo) throws ParseException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", StringUtil.generateSqlString(idList, "PD_ID", "INTER_ID"));
        System.out.println(StringUtil.generateSqlString(idList, "PD_ID", "INTER_ID"));
        List<SysProductInterfaceInfo> updateList = this.sysProductInterfaceInfoDao.selectSysProductInterfaceInfoForIds(param);
        List<String> idsList = new ArrayList<String>();
        for (SysProductInterfaceInfo info : updateList) {
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(userInfo.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductInterfaceInfoDao.updateEntity(info);
            idsList.add(info.getPdId() + "," + info.getInterId());
        }

        List<String> addPBInfo = StringUtil.compareStringWithList(idList, idsList);

        for (String s : addPBInfo) {
            SysProductInterfaceInfo info = new SysProductInterfaceInfo();
            info.setPdId(Long.valueOf(s.split(",")[0]));
            info.setInterId(Long.valueOf(s.split(",")[1]));
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(userInfo.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductInterfaceInfoDao.insertEntity(info);
        }

    }

}
