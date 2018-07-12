package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtDatabasesList;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-07-03 11:15:56
 */
public interface EtDatabasesListService {

	Integer createEtDatabasesList(EtDatabasesList t);

	int modifyEtDatabasesList(EtDatabasesList t);

	int removeEtDatabasesList(EtDatabasesList t);

	EtDatabasesList getEtDatabasesList(EtDatabasesList t);

	List<EtDatabasesList> getEtDatabasesListList(EtDatabasesList t);

	Integer getEtDatabasesListCount(EtDatabasesList t);

	List<EtDatabasesList> getEtDatabasesListPaginatedList(EtDatabasesList t);

}