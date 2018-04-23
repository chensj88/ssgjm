package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.base.util.NumberParseUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDataInfoDao;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.service.SysDataInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysDataInfoServiceImpl implements SysDataInfoService {

    @Resource
    private SysDataInfoDao sysDataInfoDao;
    @Autowired
    private SSGJHelper ssgjHelper;


    public Integer createSysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.insertEntity(t);
    }


    public SysDataInfo getSysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.selectEntity(t);
    }


    public Integer getSysDataInfoCount(SysDataInfo t) {
        return (Integer) this.sysDataInfoDao.selectEntityCount(t);
    }


    public List<SysDataInfo> getSysDataInfoList(SysDataInfo t) {
        return this.sysDataInfoDao.selectEntityList(t);
    }


    public int modifySysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.updateEntity(t);
    }


    public int removeSysDataInfo(SysDataInfo t) {
        return this.sysDataInfoDao.deleteEntity(t);
    }


    public List<SysDataInfo> getSysDataInfoPaginatedList(SysDataInfo t) {
        return this.sysDataInfoDao.selectEntityPaginatedList(t);
    }

    @Override

    public Integer getSysDataInfoCountForSelectiveKey(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoCountForSelectiveKey(t);
    }

    @Override

    public List<SysDataInfo> getSysDataInfoPaginatedListForSelectiveKey(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoPaginatedListForSelectiveKey(t);
    }

    @Override

    public List<SysDataInfo> getSysDataInfoListForSelectiveKey(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoListForSelectiveKey(t);
    }

    @Override

    public List<SysDataInfo> getSysDataInfoListById(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoListByIds(t);
    }

    @Override
    public void createSysDataInfoByList(List<List<Object>> sysDataInfoList) {
        for (List<Object> params : sysDataInfoList) {
            //循环获取sysDataInfo
            String tableCode = params.get(0) == null ? null : params.get(0).toString();
            String dbName = params.get(1) == null ? null : params.get(1).toString();
            String tableName = params.get(2) == null ? null : params.get(2).toString();
            String tableCnName = params.get(3) == null ? null : params.get(3).toString();
            String standardCode = params.get(4) == null ? null : params.get(4).toString();
            String standardCnName = params.get(5) == null ? null : params.get(5).toString();
            Integer dataType = params.get(6) == null ? null : NumberParseUtil.parseInt(params.get(6).toString());
            Integer isEasy = params.get(7) == null ? null : NumberParseUtil.parseInt(params.get(7).toString());
            String tableAttention = params.get(8) == null ? null : params.get(8).toString();
            Integer tableCount = params.get(9) == null ? null : NumberParseUtil.parseInt(params.get(9).toString());
            Integer status = params.get(10) == null ? null : NumberParseUtil.parseInt(params.get(10).toString());
//            Long lastUpdator = params.get(11) == null ? null : Long.parseLong(params.get(11).toString());
//            String lastUpdateName = params.get(12) == null ? null : params.get(12).toString();

            SysDataInfo sysDataInfo = new SysDataInfo();
            sysDataInfo.setId(ssgjHelper.createDataId());
            sysDataInfo.setTableCode(tableCode);
            sysDataInfo.setDbName(dbName);
            sysDataInfo.setTableName(tableName);
            sysDataInfo.setTableCnName(tableCnName);
            sysDataInfo.setStandardCode(standardCode);
            sysDataInfo.setStandardCnName(standardCnName);
            sysDataInfo.setDataType(dataType);
            sysDataInfo.setIsEasy(isEasy);
            sysDataInfo.setTableAttention(tableAttention);
            sysDataInfo.setTableCount(tableCount);
            sysDataInfo.setStatus(status);
//            sysDataInfo.setLastUpdator(lastUpdator);
//            sysDataInfo.setLastUpdateName(lastUpdateName);
            this.sysDataInfoDao.insertEntity(sysDataInfo);
        }
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoPaginatedListByIds(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoPaginatedListByIds(t);
    }

    @Override
    public Integer selectSysDataInfoCountByIds(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoCountByIds(t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoPaginatedListByPmIdAndDataType(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoPaginatedListByPmIdAndDataType(t);
    }

    @Override
    public Integer countSysDataInfoListByPmIdAndDataType(SysDataInfo t) {
        return this.sysDataInfoDao.countSysDataInfoListByPmIdAndDataType(t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListByPmIdAndDataType(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoListByPmIdAndDataType(t);
    }

    @Override
    public List<SysDataInfo> getSysDataInfoListForORKey(SysDataInfo sysDataInfo) {
        return this.sysDataInfoDao.selectSysDataInfoListForORKey(sysDataInfo);
    }
}
