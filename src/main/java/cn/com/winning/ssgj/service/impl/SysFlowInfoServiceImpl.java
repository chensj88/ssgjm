package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.support.Row;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFlowInfoDao;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.service.SysFlowInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysFlowInfoServiceImpl implements SysFlowInfoService {

    @Resource
    private SysFlowInfoDao sysFlowInfoDao;

    @ILog(operationName = "createSysFlowInfo",operationType = "createSysFlowInfo")
    public Integer createSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.insertEntity(t);
    }
    @ILog(operationName = "getSysFlowInfo",operationType = "getSysFlowInfo")
    public SysFlowInfo getSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntity(t);
    }

    public Integer getSysFlowInfoCount(SysFlowInfo t) {
        return (Integer) this.sysFlowInfoDao.selectEntityCount(t);
    }
    @ILog(operationName = "getSysFlowInfoList",operationType = "getSysFlowInfoList")
    public List<SysFlowInfo> getSysFlowInfoList(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntityList(t);
    }
    @ILog(operationName = "modifySysFlowInfo",operationType = "modifySysFlowInfo")
    public int modifySysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.updateEntity(t);
    }
    @ILog(operationName = "removeSysFlowInfo",operationType = "removeSysFlowInfo")
    public int removeSysFlowInfo(SysFlowInfo t) {
        return this.sysFlowInfoDao.deleteEntity(t);
    }
    @ILog(operationName = "getSysFlowInfoPaginatedList",operationType = "getSysFlowInfoPaginatedList")
    public List<SysFlowInfo> getSysFlowInfoPaginatedList(SysFlowInfo t) {
        return this.sysFlowInfoDao.selectEntityPaginatedList(t);
    }

    @ILog(operationType = "querySysFlowInfoList",operationName = "querySysFlowInfoList")
    @Override
    public List<SysFlowInfo> querySysFlowInfoList(SysFlowInfo t) {
        return this.sysFlowInfoDao.querySysFlowInfoList(t);
    }


    @Override
    @ILog(operationType = "createFlowCode",operationName = "createFlowCode")
    public String createFlowCode(String flowCode,String flowType) {
        SysFlowInfo flowInfo = new SysFlowInfo();
        flowInfo.setFlowType(flowType);
        flowInfo.setFlowCode(flowCode);
        List<SysFlowInfo> flowList = sysFlowInfoDao.querySysFlowInfoByFlowTypeAndFlowCode(flowInfo);
        //没有符合要求则为0001
        if(flowList == null){
            return "0001";
        }
        List<Integer> tempList = new ArrayList<Integer>();
        for(SysFlowInfo flow : flowList){
            String flowInfoCode = flow.getFlowCode().trim();
            String number = flowInfoCode.substring(flowInfoCode.lastIndexOf("-")+1);
            Integer num = Integer.parseInt(number);
            tempList.add(num);
        }
        int i = 1;
        boolean flag = true;

        while(flag){
            if(tempList.contains(i)){
                i++;
            }else{
                flag = false;
            }
        }

        String flowCodeNumber = "";
        if( i/10 == 0 ){
            flowCodeNumber = "000"+i;
        }else if(i/100 == 0){
            flowCodeNumber = "00"+i;
        }else if(i/1000 == 0){
            flowCodeNumber = "0"+i;
        }else{
            flowCodeNumber = ""+i;
        }
        return flowCodeNumber;
    }


}
