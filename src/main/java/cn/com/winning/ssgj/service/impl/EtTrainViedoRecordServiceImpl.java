package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtTrainViedoRecordDao;
import cn.com.winning.ssgj.domain.EtTrainViedoRecord;
import cn.com.winning.ssgj.service.EtTrainViedoRecordService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtTrainViedoRecordServiceImpl implements EtTrainViedoRecordService {

    @Resource
    private EtTrainViedoRecordDao etTrainViedoRecordDao;


    public Integer createEtTrainViedoRecord(EtTrainViedoRecord t) {
        return this.etTrainViedoRecordDao.insertEntity(t);
    }

    public EtTrainViedoRecord getEtTrainViedoRecord(EtTrainViedoRecord t) {
        return this.etTrainViedoRecordDao.selectEntity(t);
    }

    public Integer getEtTrainViedoRecordCount(EtTrainViedoRecord t) {
        return (Integer) this.etTrainViedoRecordDao.selectEntityCount(t);
    }

    public List<EtTrainViedoRecord> getEtTrainViedoRecordList(EtTrainViedoRecord t) {
        return this.etTrainViedoRecordDao.selectEntityList(t);
    }

    public int modifyEtTrainViedoRecord(EtTrainViedoRecord t) {
        return this.etTrainViedoRecordDao.updateEntity(t);
    }

    public int removeEtTrainViedoRecord(EtTrainViedoRecord t) {
        return this.etTrainViedoRecordDao.deleteEntity(t);
    }

    public List<EtTrainViedoRecord> getEtTrainViedoRecordPaginatedList(EtTrainViedoRecord t) {
        return this.etTrainViedoRecordDao.selectEntityPaginatedList(t);
    }

}
