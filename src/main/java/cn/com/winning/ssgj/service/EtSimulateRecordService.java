package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtSimulateRecord;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtSimulateRecordService {

    Integer createEtSimulateRecord(EtSimulateRecord t);

    int modifyEtSimulateRecord(EtSimulateRecord t);

    int removeEtSimulateRecord(EtSimulateRecord t);

    EtSimulateRecord getEtSimulateRecord(EtSimulateRecord t);

    List<EtSimulateRecord> getEtSimulateRecordList(EtSimulateRecord t);

    Integer getEtSimulateRecordCount(EtSimulateRecord t);

    List<EtSimulateRecord> getEtSimulateRecordPaginatedList(EtSimulateRecord t);

}