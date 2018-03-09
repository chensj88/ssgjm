package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductDataInfoDao;
import cn.com.winning.ssgj.domain.SysProductDataInfo;
import cn.com.winning.ssgj.service.SysProductDataInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductDataInfoServiceImpl implements SysProductDataInfoService {

    @Resource
    private SysProductDataInfoDao sysProductDataInfoDao;


    public Integer createSysProductDataInfo(SysProductDataInfo t) {
        return this.sysProductDataInfoDao.insertEntity(t);
    }

    public SysProductDataInfo getSysProductDataInfo(SysProductDataInfo t) {
        return this.sysProductDataInfoDao.selectEntity(t);
    }

    public Integer getSysProductDataInfoCount(SysProductDataInfo t) {
        return (Integer) this.sysProductDataInfoDao.selectEntityCount(t);
    }

    public List<SysProductDataInfo> getSysProductDataInfoList(SysProductDataInfo t) {
        return this.sysProductDataInfoDao.selectEntityList(t);
    }

    public int modifySysProductDataInfo(SysProductDataInfo t) {
        return this.sysProductDataInfoDao.updateEntity(t);
    }

    public int removeSysProductDataInfo(SysProductDataInfo t) {
        return this.sysProductDataInfoDao.deleteEntity(t);
    }

    public List<SysProductDataInfo> getSysProductDataInfoPaginatedList(SysProductDataInfo t) {
        return this.sysProductDataInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<String> getDataInfoId(List<SysProductDataInfo> sysProductDataInfoList) {
        List<String> idList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sysProductDataInfoList.size(); i++) {
            idList.add(sysProductDataInfoList.get(i).getBdId()+"");
        }
        return idList;
    }

    @Override
    public List<SysProductDataInfo> getSysProductDataInfoByIds(Integer pdId, String bdIds) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("pdId",pdId);
        param.put("bdIds",bdIds);
        return this.sysProductDataInfoDao.selectSysProductDataInfoByIds(param);
    }

    @Override
    public Integer removeSysProductDataInfo(String idList) {
        String ids = StringUtil.generateSqlString(idList,"PD_ID","BD_ID");
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids",ids);
        param.put("user", ((SysUserInfo) SecurityUtils.getSubject().getPrincipal()).getId());
        return this.sysProductDataInfoDao.removeSysProductDataInfoByIds(param);
    }

    @Override
    public void addSysProductDataInfoMapping(String idList, SysUserInfo user) throws ParseException {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids",StringUtil.generateSqlString(idList,"PD_ID","BD_ID"));
        System.out.println(StringUtil.generateSqlString(idList,"PD_ID","BD_ID"));
        List<SysProductDataInfo> updateList = this.sysProductDataInfoDao.selectSysProductDataInfoForIds(param);
        List<String> idsList = new ArrayList<String>();
        for (SysProductDataInfo info : updateList) {
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(user.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductDataInfoDao.updateEntity(info);
            idsList.add(info.getPdId()+","+info.getBdId());
        }

        List<String> addPBInfo = StringUtil.compareStringWithList(idList,idsList);

        for (String s : addPBInfo) {
            SysProductDataInfo info = new SysProductDataInfo();
            info.setPdId(Long.valueOf(s.split(",")[0]));
            info.setBdId(Long.valueOf(s.split(",")[1]));
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(user.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductDataInfoDao.insertEntity(info);
        }

    }

}
