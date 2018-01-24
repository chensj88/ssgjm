package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtTrainViedoRecord;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtTrainViedoRecordService {

    Integer createEtTrainViedoRecord(EtTrainViedoRecord t);

    int modifyEtTrainViedoRecord(EtTrainViedoRecord t);

    int removeEtTrainViedoRecord(EtTrainViedoRecord t);

    EtTrainViedoRecord getEtTrainViedoRecord(EtTrainViedoRecord t);

    List<EtTrainViedoRecord> getEtTrainViedoRecordList(EtTrainViedoRecord t);

    Integer getEtTrainViedoRecordCount(EtTrainViedoRecord t);

    List<EtTrainViedoRecord> getEtTrainViedoRecordPaginatedList(EtTrainViedoRecord t);

}