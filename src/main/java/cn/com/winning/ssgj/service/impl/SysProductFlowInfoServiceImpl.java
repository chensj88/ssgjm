package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductFlowInfoDao;
import cn.com.winning.ssgj.domain.SysProductFlowInfo;
import cn.com.winning.ssgj.service.SysProductFlowInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysProductFlowInfoServiceImpl implements SysProductFlowInfoService {

    @Resource
    private SysProductFlowInfoDao sysProductFlowInfoDao;


    public Integer createSysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.insertEntity(t);
    }

    public SysProductFlowInfo getSysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.selectEntity(t);
    }

    public Integer getSysProductFlowInfoCount(SysProductFlowInfo t) {
        return (Integer) this.sysProductFlowInfoDao.selectEntityCount(t);
    }

    public List<SysProductFlowInfo> getSysProductFlowInfoList(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.selectEntityList(t);
    }

    public int modifySysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.updateEntity(t);
    }

    public int removeSysProductFlowInfo(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.deleteEntity(t);
    }

    public List<SysProductFlowInfo> getSysProductFlowInfoPaginatedList(SysProductFlowInfo t) {
        return this.sysProductFlowInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<SysProductFlowInfo> getSysProductFlowInfoByPdIdAndFlowId(Integer pdId, String flowIds) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("pdId",pdId);
        param.put("flowIds",flowIds);
        System.out.println(pdId);
        System.out.println(flowIds);
        return this.sysProductFlowInfoDao.selectSysProductFlowInfoByPdIdAndFlowId(param);
    }

    @Override
    public void addSysProductFlowInfoMapping(String idList, SysUserInfo user) throws ParseException {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids", StringUtil.generateSqlString(idList,"PD_ID","FLOW_ID"));
        List<SysProductFlowInfo> updateList = this.sysProductFlowInfoDao.selectProductFlowInfoForIds(param);
        List<String> idsList = new ArrayList<String>();
        for (SysProductFlowInfo info : updateList) {
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(user.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductFlowInfoDao.updateEntity(info);
            idsList.add(info.getPdId()+","+info.getFlowId());
        }

        List<String> addInfo = StringUtil.compareStringWithList(idList,idsList);

        for (String s : addInfo) {
            SysProductFlowInfo info = new SysProductFlowInfo();
            info.setPdId(Long.valueOf(s.split(",")[0]));
            info.setFlowId(Long.valueOf(s.split(",")[1]));
            info.setEffectiveDate(new Date());
            info.setExpireDate(DateUtil.parse("9999-12-31"));
            info.setLastUpdator(user.getId());
            info.setLastUpdateTime(new Timestamp(new Date().getTime()));
            this.sysProductFlowInfoDao.insertEntity(info);
        }
    }

    @Override
    public Integer removeSysProductFlowInfoMappingByIds(String idList, SysUserInfo userInfo) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids", StringUtil.generateSqlString(idList,"PD_ID","FLOW_ID"));
        param.put("user",userInfo.getId());
        return this.sysProductFlowInfoDao.removeSysProductFlowInfoMappingByIds(param);
    }

}
