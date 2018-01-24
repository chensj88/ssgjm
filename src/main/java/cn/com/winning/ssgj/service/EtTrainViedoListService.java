package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtTrainViedoList;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtTrainViedoListService {

    Integer createEtTrainViedoList(EtTrainViedoList t);

    int modifyEtTrainViedoList(EtTrainViedoList t);

    int removeEtTrainViedoList(EtTrainViedoList t);

    EtTrainViedoList getEtTrainViedoList(EtTrainViedoList t);

    List<EtTrainViedoList> getEtTrainViedoListList(EtTrainViedoList t);

    Integer getEtTrainViedoListCount(EtTrainViedoList t);

    List<EtTrainViedoList> getEtTrainViedoListPaginatedList(EtTrainViedoList t);

}