package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtStartEnd;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-15 10:57:58
 */
public interface EtStartEndService {

	Integer createEtStartEnd(EtStartEnd t);

	int modifyEtStartEnd(EtStartEnd t);

	int removeEtStartEnd(EtStartEnd t);

	EtStartEnd getEtStartEnd(EtStartEnd t);

	List<EtStartEnd> getEtStartEndList(EtStartEnd t);

	Integer getEtStartEndCount(EtStartEnd t);

	List<EtStartEnd> getEtStartEndPaginatedList(EtStartEnd t);

}