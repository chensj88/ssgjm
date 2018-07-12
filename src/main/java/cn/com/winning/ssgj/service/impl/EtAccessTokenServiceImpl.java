package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtAccessTokenDao;
import cn.com.winning.ssgj.domain.EtAccessToken;
import cn.com.winning.ssgj.service.EtAccessTokenService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-20 11:22:30
 */
@Service
public class EtAccessTokenServiceImpl implements EtAccessTokenService {

	@Resource
	private EtAccessTokenDao etAccessTokenDao;
	

	public Integer createEtAccessToken(EtAccessToken t) {
		return this.etAccessTokenDao.insertEntity(t);
	}

	public EtAccessToken getEtAccessToken(EtAccessToken t) {
		return this.etAccessTokenDao.selectEntity(t);
	}

	public Integer getEtAccessTokenCount(EtAccessToken t) {
		return (Integer) this.etAccessTokenDao.selectEntityCount(t);
	}

	public List<EtAccessToken> getEtAccessTokenList(EtAccessToken t) {
		return this.etAccessTokenDao.selectEntityList(t);
	}

	public int modifyEtAccessToken(EtAccessToken t) {
		return this.etAccessTokenDao.updateEntity(t);
	}

	public int removeEtAccessToken(EtAccessToken t) {
		return this.etAccessTokenDao.deleteEntity(t);
	}

	public List<EtAccessToken> getEtAccessTokenPaginatedList(EtAccessToken t) {
		return this.etAccessTokenDao.selectEntityPaginatedList(t);
	}

}
