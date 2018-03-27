package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtContractTaskDao;
import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.service.EtContractTaskService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-26 16:19:08
 */
@Service
public class EtContractTaskServiceImpl implements EtContractTaskService {

	@Resource
	private EtContractTaskDao etContractTaskDao;
	

	public Integer createEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.insertEntity(t);
	}

	public EtContractTask getEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.selectEntity(t);
	}

	public Integer getEtContractTaskCount(EtContractTask t) {
		return (Integer) this.etContractTaskDao.selectEntityCount(t);
	}

	public List<EtContractTask> getEtContractTaskList(EtContractTask t) {
		return this.etContractTaskDao.selectEntityList(t);
	}

	public int modifyEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.updateEntity(t);
	}

	public int removeEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.deleteEntity(t);
	}

	public List<EtContractTask> getEtContractTaskPaginatedList(EtContractTask t) {
		return this.etContractTaskDao.selectEntityPaginatedList(t);
	}

	@Override
	public Integer getEtContractTaskMergeCount(EtContractTask t) {
		return this.etContractTaskDao.selectEtContractTaskMergeCount(t);
	}

	@Override
	public List<EtContractTask> getEtContractTaskPageMergeList(EtContractTask t) {
		return this.etContractTaskDao.selectEtContractTaskMergePageList(t);
	}

}
