package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtTrainVideoRecord;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-27 16:02:55
 */
public interface EtTrainVideoRecordService {

	Integer createEtTrainVideoRecord(EtTrainVideoRecord t);

	int modifyEtTrainVideoRecord(EtTrainVideoRecord t);

	int removeEtTrainVideoRecord(EtTrainVideoRecord t);

	EtTrainVideoRecord getEtTrainVideoRecord(EtTrainVideoRecord t);

	List<EtTrainVideoRecord> getEtTrainVideoRecordList(EtTrainVideoRecord t);

	Integer getEtTrainVideoRecordCount(EtTrainVideoRecord t);

	List<EtTrainVideoRecord> getEtTrainVideoRecordPaginatedList(EtTrainVideoRecord t);

}