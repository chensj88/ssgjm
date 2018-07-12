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

	/**
	 * 查询未初始化的流程ID信息
	 * @param process
	 * @return
	 */
    List<Long> getUnInitEtBusinessProcess(EtBusinessProcess process);

	/**
	 * 流程配置信息创建的时候，自动更新业务流程信息的配置
	 * @param process
	 */
	int modifyEtBusinessProcessConfigBatch(EtBusinessProcess process);
}