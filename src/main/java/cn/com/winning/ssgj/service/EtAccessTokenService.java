package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtAccessToken;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-20 11:22:30
 */
public interface EtAccessTokenService {

	Integer createEtAccessToken(EtAccessToken t);

	int modifyEtAccessToken(EtAccessToken t);

	int removeEtAccessToken(EtAccessToken t);

	EtAccessToken getEtAccessToken(EtAccessToken t);

	List<EtAccessToken> getEtAccessTokenList(EtAccessToken t);

	Integer getEtAccessTokenCount(EtAccessToken t);

	List<EtAccessToken> getEtAccessTokenPaginatedList(EtAccessToken t);

}