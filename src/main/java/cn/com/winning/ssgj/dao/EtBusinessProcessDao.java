package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtBusinessProcess;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-12 10:44:43
 */
public interface EtBusinessProcessDao extends EntityDao<EtBusinessProcess> {

    List<Long> selectUnInitEtBusinessProcess(EtBusinessProcess process);

    int updateEtBusinessProcessConfigBatch(EtBusinessProcess process);
}
