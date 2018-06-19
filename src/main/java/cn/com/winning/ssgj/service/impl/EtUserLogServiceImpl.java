package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtUserLogDao;
import cn.com.winning.ssgj.domain.EtUserLog;
import cn.com.winning.ssgj.service.EtUserLogService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-19 13:56:50
 */
@Service
public class EtUserLogServiceImpl implements EtUserLogService {

	@Resource
	private EtUserLogDao etUserLogDao;
	

	public Integer createEtUserLog(EtUserLog t) {
		return this.etUserLogDao.insertEntity(t);
	}

	public EtUserLog getEtUserLog(EtUserLog t) {
		return this.etUserLogDao.selectEntity(t);
	}

	public Integer getEtUserLogCount(EtUserLog t) {
		return (Integer) this.etUserLogDao.selectEntityCount(t);
	}

	public List<EtUserLog> getEtUserLogList(EtUserLog t) {
		return this.etUserLogDao.selectEntityList(t);
	}

	public int modifyEtUserLog(EtUserLog t) {
		return this.etUserLogDao.updateEntity(t);
	}

	public int removeEtUserLog(EtUserLog t) {
		return this.etUserLogDao.deleteEntity(t);
	}

	public List<EtUserLog> getEtUserLogPaginatedList(EtUserLog t) {
		return this.etUserLogDao.selectEntityPaginatedList(t);
	}

}
