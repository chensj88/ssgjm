package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.domain.MobileSiteQuestion;

import javax.servlet.http.HttpServletResponse;

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
	 * @param response  响应
	 * @param fileName 文件保存名称
	 */
    void generateEtContractTask(EtContractTask task, HttpServletResponse response, String fileName);

	String checkEtContractTaskIsUse(EtContractTask task);

    List<String> getEtContractTaskFirstInitCode(String serialNo);

	List<MobileSiteQuestion<EtContractTask>> getWechatContractTaskData(String serialNo);
}