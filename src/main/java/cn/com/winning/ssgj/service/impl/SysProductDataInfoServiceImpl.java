package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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


        return null;
    }


}
