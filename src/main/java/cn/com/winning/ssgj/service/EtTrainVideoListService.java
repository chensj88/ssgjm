package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtTrainVideoList;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-27 16:02:55
 */
public interface EtTrainVideoListService {

	Integer createEtTrainVideoList(EtTrainVideoList t);

	int modifyEtTrainVideoList(EtTrainVideoList t);

	int removeEtTrainVideoList(EtTrainVideoList t);

	EtTrainVideoList getEtTrainVideoList(EtTrainVideoList t);

	List<EtTrainVideoList> getEtTrainVideoListList(EtTrainVideoList t);

	Integer getEtTrainVideoListCount(EtTrainVideoList t);

	List<EtTrainVideoList> getEtTrainVideoListPaginatedList(EtTrainVideoList t);

}