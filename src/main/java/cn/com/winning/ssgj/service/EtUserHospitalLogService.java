package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtUserHospitalLog;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-22 16:38:32
 */
public interface EtUserHospitalLogService {

	Integer createEtUserHospitalLog(EtUserHospitalLog t);

	int modifyEtUserHospitalLog(EtUserHospitalLog t);

	int removeEtUserHospitalLog(EtUserHospitalLog t);

	EtUserHospitalLog getEtUserHospitalLog(EtUserHospitalLog t);

	List<EtUserHospitalLog> getEtUserHospitalLogList(EtUserHospitalLog t);

	Integer getEtUserHospitalLogCount(EtUserHospitalLog t);

	List<EtUserHospitalLog> getEtUserHospitalLogPaginatedList(EtUserHospitalLog t);

}