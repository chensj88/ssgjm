package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysProductFlowInfo;
import cn.com.winning.ssgj.domain.support.Row;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFlowInfoDao;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.service.SysFlowInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysFlowInfoServiceImpl implements SysFlowInfoService {

    @Resource
    private SysFlowInfoDao sysFlowInfoDao;

    public Integer createSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.insertEntity(t);
    }

    public SysFlowInfo getSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntity(t);
    }


    public Integer getSysFlowInfoCount(SysFlowInfo t) {
        return (Integer) this.sysFlowInfoDao.selectEntityCount(t);
    }

    public List<SysFlowInfo> getSysFlowInfoList(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntityList(t);
    }

    public int modifySysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.updateEntity(t);
    }


    public int removeSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.deleteEntity(t);
    }


    public List<SysFlowInfo> getSysFlowInfoPaginatedList(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<SysFlowInfo> querySysFlowInfoList(SysFlowInfo t) {
        return this.sysFlowInfoDao.querySysFlowInfoList(t);
    }

    @Override
    public List<SysFlowInfo> querySysFlowInfoListForName(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectSysFlowInfoListForName(t);
    }


    @Override
    public String createFlowCode(String flowCode, String flowType) {
        SysFlowInfo flowInfo = new SysFlowInfo();
        flowInfo.setFlowType(flowType);
        flowInfo.setFlowCode(flowCode);
        List<SysFlowInfo> flowList = sysFlowInfoDao.querySysFlowInfoByFlowTypeAndFlowCode(flowInfo);
        //没有符合要求则为0001
        if (flowList == null) {
            return "0001";
        }
        List<Integer> tempList = new ArrayList<Integer>();
        for (SysFlowInfo flow : flowList) {
            String flowInfoCode = flow.getFlowCode().trim();
            String number = flowInfoCode.substring(flowInfoCode.lastIndexOf("-") + 1);
            Integer num = Integer.parseInt(number);
            tempList.add(num);
        }
        int i = 1;
        boolean flag = true;

        while (flag) {
            if (tempList.contains(i)) {
                i++;
            } else {
                flag = false;
            }
        }

        String flowCodeNumber = "";
        if (i / 10 == 0) {
            flowCodeNumber = "000" + i;
        } else if (i / 100 == 0) {
            flowCodeNumber = "00" + i;
        } else if (i / 1000 == 0) {
            flowCodeNumber = "0" + i;
        } else {
            flowCodeNumber = "" + i;
        }
        return flowCodeNumber;
    }

    @Override
    public Integer getSysFlowInfoCountForSelective(SysFlowInfo t) {
        return this.sysFlowInfoDao.querySysFlowInfoCountForSelective(t);
    }

    @Override
    public List<SysFlowInfo> getSysFlowInfoPaginatedListForSelective(SysFlowInfo t) {
        return this.sysFlowInfoDao.querySysFlowInfoPaginatedListForSelective(t);
    }

    @Override
    public List<SysFlowInfo> getSysFlowInfoListForSelectiveKey(SysFlowInfo flowInfo) {

        return this.sysFlowInfoDao.selectSysFlowInfoListForSelectiveKey(flowInfo);
    }

    @Override
    public Integer getSysFlowInfoCountForSelectiveKey(SysFlowInfo flowInfo) {

        return this.sysFlowInfoDao.selectSysFlowInfoCountForSelectiveKey(flowInfo);
    }

    @Override
    public List<String> getFlowInfoId(List<SysProductFlowInfo> flowInfoList) {
        List<String> idList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < flowInfoList.size(); i++) {
            idList.add(flowInfoList.get(i).getFlowId() + "");
        }
        return idList;
    }

    @Override
    public List<SysFlowInfo> getSysFlowInfoListById(SysFlowInfo flowInfo) {
        return this.sysFlowInfoDao.selectSysFlowInfoListById(flowInfo);
    }

    @Override
    public boolean existFlowName(SysFlowInfo info) {
        info.setStatus(Constants.STATUS_USE);
        Integer count = (Integer) this.sysFlowInfoDao.selectEntityCount(info);
        if (count.intValue() > 1) {
            return false;
        }
        return true;
    }


}
