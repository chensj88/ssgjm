package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.dao.EntityDao;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-26 16:19:08
 */
public interface EtContractTaskDao extends EntityDao<EtContractTask> {


    List<EtContractTask> selectEtContractTaskMergePageList(EtContractTask paramT);
    int selectEtContractTaskMergeCount(EtContractTask paramT);

    /**
     * 检查当前删除的系统是否被软硬件清单使用
     * @param task
     * @return
     */
    int selectEtContractTaskForEtSoftHardCount(EtContractTask task);

    /**
     * 检查当前删除的系统是否被站点问题使用
     * @param task
     * @return
     */
    int selectEtContractTaskForEtSitemQuestionCount(EtContractTask task);

    /**
     * 检查当前删除的系统是否被站点安装使用
     * @param task
     * @return
     */
    String selectEtContractTaskForSiteInstall(EtContractTask task);
}
