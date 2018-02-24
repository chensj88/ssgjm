package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysFlowQuestion;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysFlowQuestionDao extends EntityDao<SysFlowQuestion> {

    Integer selectFlowQuestionPageCount(SysFlowQuestion question);
    List<SysFlowQuestion> selectFlowQuestionPageList(SysFlowQuestion question);
}
