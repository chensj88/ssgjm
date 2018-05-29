package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtBusinessProcessDao;
import cn.com.winning.ssgj.domain.EtBusinessProcess;
import cn.com.winning.ssgj.service.EtBusinessProcessService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-12 10:44:43
 */
@Service
public class EtBusinessProcessServiceImpl implements EtBusinessProcessService {

	@Resource
	private EtBusinessProcessDao etBusinessProcessDao;
	

	public Integer createEtBusinessProcess(EtBusinessProcess t) {
		return this.etBusinessProcessDao.insertEntity(t);
	}

	public EtBusinessProcess getEtBusinessProcess(EtBusinessProcess t) {
		return this.etBusinessProcessDao.selectEntity(t);
	}

	public Integer getEtBusinessProcessCount(EtBusinessProcess t) {
		return (Integer) this.etBusinessProcessDao.selectEntityCount(t);
	}

	public List<EtBusinessProcess> getEtBusinessProcessList(EtBusinessProcess t) {
		return this.etBusinessProcessDao.selectEntityList(t);
	}

	public int modifyEtBusinessProcess(EtBusinessProcess t) {
		return this.etBusinessProcessDao.updateEntity(t);
	}

	public int removeEtBusinessProcess(EtBusinessProcess t) {
		return this.etBusinessProcessDao.deleteEntity(t);
	}

	public List<EtBusinessProcess> getEtBusinessProcessPaginatedList(EtBusinessProcess t) {
		return this.etBusinessProcessDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<Long> getUnInitEtBusinessProcess(EtBusinessProcess process) {
		return this.etBusinessProcessDao.selectUnInitEtBusinessProcess(process);
	}

}
