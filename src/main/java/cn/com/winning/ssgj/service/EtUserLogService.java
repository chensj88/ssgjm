package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtUserLog;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-19 13:56:50
 */
public interface EtUserLogService {

	Integer createEtUserLog(EtUserLog t);

	int modifyEtUserLog(EtUserLog t);

	int removeEtUserLog(EtUserLog t);

	EtUserLog getEtUserLog(EtUserLog t);

	List<EtUserLog> getEtUserLogList(EtUserLog t);

	Integer getEtUserLogCount(EtUserLog t);

	List<EtUserLog> getEtUserLogPaginatedList(EtUserLog t);

}