package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtLogDao;
import cn.com.winning.ssgj.domain.EtLog;
import cn.com.winning.ssgj.service.EtLogService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-09 14:58:09
 */
@Service
public class EtLogServiceImpl implements EtLogService {

	@Resource
	private EtLogDao etLogDao;
	

	public Integer createEtLog(EtLog t) {
		return this.etLogDao.insertEntity(t);
	}

	public EtLog getEtLog(EtLog t) {
		return this.etLogDao.selectEntity(t);
	}

	public Integer getEtLogCount(EtLog t) {
		return (Integer) this.etLogDao.selectEntityCount(t);
	}

	public List<EtLog> getEtLogList(EtLog t) {
		return this.etLogDao.selectEntityList(t);
	}

	public int modifyEtLog(EtLog t) {
		return this.etLogDao.updateEntity(t);
	}

	public int removeEtLog(EtLog t) {
		return this.etLogDao.deleteEntity(t);
	}

	public List<EtLog> getEtLogPaginatedList(EtLog t) {
		return this.etLogDao.selectEntityPaginatedList(t);
	}

}
