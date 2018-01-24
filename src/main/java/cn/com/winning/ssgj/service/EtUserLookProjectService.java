package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtUserLookProject;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-01-19 16:52:12
 */
public interface EtUserLookProjectService {

	Integer createEtUserLookProject(EtUserLookProject t);

	int modifyEtUserLookProject(EtUserLookProject t);

	int removeEtUserLookProject(EtUserLookProject t);

	EtUserLookProject getEtUserLookProject(EtUserLookProject t);

	List<EtUserLookProject> getEtUserLookProjectList(EtUserLookProject t);

	Integer getEtUserLookProjectCount(EtUserLookProject t);

	List<EtUserLookProject> getEtUserLookProjectPaginatedList(EtUserLookProject t);

}