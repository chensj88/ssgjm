package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtStartEndDao;
import cn.com.winning.ssgj.domain.EtStartEnd;
import cn.com.winning.ssgj.service.EtStartEndService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-15 10:57:58
 */
@Service
public class EtStartEndServiceImpl implements EtStartEndService {

	@Resource
	private EtStartEndDao etStartEndDao;
	

	public Integer createEtStartEnd(EtStartEnd t) {
		return this.etStartEndDao.insertEntity(t);
	}

	public EtStartEnd getEtStartEnd(EtStartEnd t) {
		return this.etStartEndDao.selectEntity(t);
	}

	public Integer getEtStartEndCount(EtStartEnd t) {
		return (Integer) this.etStartEndDao.selectEntityCount(t);
	}

	public List<EtStartEnd> getEtStartEndList(EtStartEnd t) {
		return this.etStartEndDao.selectEntityList(t);
	}

	public int modifyEtStartEnd(EtStartEnd t) {
		return this.etStartEndDao.updateEntity(t);
	}

	public int removeEtStartEnd(EtStartEnd t) {
		return this.etStartEndDao.deleteEntity(t);
	}

	public List<EtStartEnd> getEtStartEndPaginatedList(EtStartEnd t) {
		return this.etStartEndDao.selectEntityPaginatedList(t);
	}

}
