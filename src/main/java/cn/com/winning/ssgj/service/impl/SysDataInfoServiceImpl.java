package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.MD5;
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
            String tableName = params.get(0).toString();
            String tableCnName = params.get(1).toString();
            String dataType = params.get(2).toString();
            SysDataInfo sysDataInfo = new SysDataInfo();
            sysDataInfo.setTableName(tableName);
            sysDataInfo.setTableCnName(tableCnName);
            if (!StringUtil.isEmptyOrNull(dataType)) {
                sysDataInfo.setDataType(Integer.parseInt(dataType));
            }
            //根据导入数据查找datainfo
            SysDataInfo curSysDataInfo = this.getSysDataInfo(sysDataInfo);
            if (curSysDataInfo != null) {
                //数据已存在，则更新
                curSysDataInfo.setTableName(sysDataInfo.getTableName());
                curSysDataInfo.setTableCnName(sysDataInfo.getTableCnName());
                curSysDataInfo.setDataType(sysDataInfo.getDataType());
                this.sysDataInfoDao.updateEntity(curSysDataInfo);
            } else {
                //数据不存在
                curSysDataInfo = sysDataInfo;
                curSysDataInfo.setId(ssgjHelper.createDataId());
                this.sysDataInfoDao.insertEntity(curSysDataInfo);
            }
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
    public List<SysDataInfo> selectSysDataInfoPaginatedListByPidAndDataType(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoPaginatedListByPidAndDataType(t);
    }

    @Override
    public Integer countSysDataInfoPaginatedListByPidAndDataType(SysDataInfo t) {
        return this.sysDataInfoDao.countSysDataInfoPaginatedListByPidAndDataType(t);
    }

    @Override
    public List<SysDataInfo> selectSysDataInfoListByPidAndDataType(SysDataInfo t) {
        return this.sysDataInfoDao.selectSysDataInfoListByPidAndDataType(t);
    }

    @Override
    public List<SysDataInfo> getSysDataInfoListForORKey(SysDataInfo sysDataInfo) {
        return this.sysDataInfoDao.selectSysDataInfoListForORKey(sysDataInfo);
    }
}
