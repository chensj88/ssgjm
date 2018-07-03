package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtDatabasesListDao;
import cn.com.winning.ssgj.domain.EtDatabasesList;
import cn.com.winning.ssgj.service.EtDatabasesListService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-07-03 11:15:56
 */
@Service
public class EtDatabasesListServiceImpl implements EtDatabasesListService {

	@Resource
	private EtDatabasesListDao etDatabasesListDao;
	

	public Integer createEtDatabasesList(EtDatabasesList t) {
		return this.etDatabasesListDao.insertEntity(t);
	}

	public EtDatabasesList getEtDatabasesList(EtDatabasesList t) {
		return this.etDatabasesListDao.selectEntity(t);
	}

	public Integer getEtDatabasesListCount(EtDatabasesList t) {
		return (Integer) this.etDatabasesListDao.selectEntityCount(t);
	}

	public List<EtDatabasesList> getEtDatabasesListList(EtDatabasesList t) {
		return this.etDatabasesListDao.selectEntityList(t);
	}

	public int modifyEtDatabasesList(EtDatabasesList t) {
		return this.etDatabasesListDao.updateEntity(t);
	}

	public int removeEtDatabasesList(EtDatabasesList t) {
		return this.etDatabasesListDao.deleteEntity(t);
	}

	public List<EtDatabasesList> getEtDatabasesListPaginatedList(EtDatabasesList t) {
		return this.etDatabasesListDao.selectEntityPaginatedList(t);
	}

}
