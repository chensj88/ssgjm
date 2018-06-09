package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtLog;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-09 14:58:09
 */
public interface EtLogService {

	Integer createEtLog(EtLog t);

	int modifyEtLog(EtLog t);

	int removeEtLog(EtLog t);

	EtLog getEtLog(EtLog t);

	List<EtLog> getEtLogList(EtLog t);

	Integer getEtLogCount(EtLog t);

	List<EtLog> getEtLogPaginatedList(EtLog t);

}