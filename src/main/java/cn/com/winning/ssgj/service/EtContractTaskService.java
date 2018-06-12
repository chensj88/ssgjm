package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.domain.MobileSiteQuestion;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-26 16:19:08
 */
public interface EtContractTaskService {

	Integer createEtContractTask(EtContractTask t);

	int modifyEtContractTask(EtContractTask t);

	int removeEtContractTask(EtContractTask t);

	EtContractTask getEtContractTask(EtContractTask t);

	List<EtContractTask> getEtContractTaskList(EtContractTask t);

	Integer getEtContractTaskCount(EtContractTask t);

	List<EtContractTask> getEtContractTaskPaginatedList(EtContractTask t);

	Integer getEtContractTaskMergeCount(EtContractTask t);

	List<EtContractTask> getEtContractTaskPageMergeList(EtContractTask t);

	/**
	 * 生成Excel文档
	 * @param task 产品任务单
	 * @param path 路径
	 */
    void generateEtContractTask(EtContractTask task, String path);

	String checkEtContractTaskIsUse(EtContractTask task);

    List<String> getEtContractTaskFirstInitCode(String serialNo);

	List<MobileSiteQuestion<EtContractTask>> getWechatContractTaskData(String serialNo);
}