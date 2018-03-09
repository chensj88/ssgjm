package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtFloorQuestionInfoDao extends EntityDao<EtFloorQuestionInfo> {

    List<EtFloorQuestionInfo> selectEtFloorQuestionInfoWithHospitalList(EtFloorQuestionInfo t);

    List<EtFloorQuestionInfo> selectEtFloorQuestionInfoSummaryList(EtFloorQuestionInfo t);
}
