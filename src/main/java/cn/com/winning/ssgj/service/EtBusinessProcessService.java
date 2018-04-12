package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtBusinessProcess;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-12 10:44:43
 */
public interface EtBusinessProcessService {

	Integer createEtBusinessProcess(EtBusinessProcess t);

	int modifyEtBusinessProcess(EtBusinessProcess t);

	int removeEtBusinessProcess(EtBusinessProcess t);

	EtBusinessProcess getEtBusinessProcess(EtBusinessProcess t);

	List<EtBusinessProcess> getEtBusinessProcessList(EtBusinessProcess t);

	Integer getEtBusinessProcessCount(EtBusinessProcess t);

	List<EtBusinessProcess> getEtBusinessProcessPaginatedList(EtBusinessProcess t);

}