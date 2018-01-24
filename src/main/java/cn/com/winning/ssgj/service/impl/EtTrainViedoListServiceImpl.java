package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtTrainViedoListDao;
import cn.com.winning.ssgj.domain.EtTrainViedoList;
import cn.com.winning.ssgj.service.EtTrainViedoListService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtTrainViedoListServiceImpl implements EtTrainViedoListService {

    @Resource
    private EtTrainViedoListDao etTrainViedoListDao;


    public Integer createEtTrainViedoList(EtTrainViedoList t) {
        return this.etTrainViedoListDao.insertEntity(t);
    }

    public EtTrainViedoList getEtTrainViedoList(EtTrainViedoList t) {
        return this.etTrainViedoListDao.selectEntity(t);
    }

    public Integer getEtTrainViedoListCount(EtTrainViedoList t) {
        return (Integer) this.etTrainViedoListDao.selectEntityCount(t);
    }

    public List<EtTrainViedoList> getEtTrainViedoListList(EtTrainViedoList t) {
        return this.etTrainViedoListDao.selectEntityList(t);
    }

    public int modifyEtTrainViedoList(EtTrainViedoList t) {
        return this.etTrainViedoListDao.updateEntity(t);
    }

    public int removeEtTrainViedoList(EtTrainViedoList t) {
        return this.etTrainViedoListDao.deleteEntity(t);
    }

    public List<EtTrainViedoList> getEtTrainViedoListPaginatedList(EtTrainViedoList t) {
        return this.etTrainViedoListDao.selectEntityPaginatedList(t);
    }

}
