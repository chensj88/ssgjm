package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtSoftHardware;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtSoftHardwareService {

    Integer createEtSoftHardware(EtSoftHardware t);

    int modifyEtSoftHardware(EtSoftHardware t);

    int removeEtSoftHardware(EtSoftHardware t);

    EtSoftHardware getEtSoftHardware(EtSoftHardware t);

    List<EtSoftHardware> getEtSoftHardwareList(EtSoftHardware t);

    Integer getEtSoftHardwareCount(EtSoftHardware t);

    List<EtSoftHardware> getEtSoftHardwarePaginatedList(EtSoftHardware t);

    void insertEtSoftHardwareByList(List<EtSoftHardware> etSoftHardwares);

    List<EtSoftHardware> selectEtSoftHardwareByProductInfo(EtSoftHardware etSoftHardware);


}