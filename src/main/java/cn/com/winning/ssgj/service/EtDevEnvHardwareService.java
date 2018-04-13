package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtDevEnvHardware;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtDevEnvHardwareService {

    Integer createEtDevEnvHardware(EtDevEnvHardware t);

    int modifyEtDevEnvHardware(EtDevEnvHardware t);

    int removeEtDevEnvHardware(EtDevEnvHardware t);

    EtDevEnvHardware getEtDevEnvHardware(EtDevEnvHardware t);

    List<EtDevEnvHardware> getEtDevEnvHardwareList(EtDevEnvHardware t);

    Integer getEtDevEnvHardwareCount(EtDevEnvHardware t);

    List<EtDevEnvHardware> getEtDevEnvHardwarePaginatedList(EtDevEnvHardware t);

    List<EtDevEnvHardware> selectEtDevEnvHardwareMergeList(EtDevEnvHardware etDevEnvHardware);

    //    批量插入数据
    void insertEtDevEnvHardwareByList(List<EtDevEnvHardware> etDevEnvHardwares);


    List<EtDevEnvHardware> selectPmisEtDevEnvHardwareByProductInfo(EtDevEnvHardware etDevEnvHardware);

}