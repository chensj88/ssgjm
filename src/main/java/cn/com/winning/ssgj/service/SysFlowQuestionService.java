package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysFlowQuestion;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysFlowQuestionService {

    Integer createSysFlowQuestion(SysFlowQuestion t);

    int modifySysFlowQuestion(SysFlowQuestion t);

    int removeSysFlowQuestion(SysFlowQuestion t);

    SysFlowQuestion getSysFlowQuestion(SysFlowQuestion t);

    List<SysFlowQuestion> getSysFlowQuestionList(SysFlowQuestion t);

    Integer getSysFlowQuestionCount(SysFlowQuestion t);

    List<SysFlowQuestion> getSysFlowQuestionPaginatedList(SysFlowQuestion t);

    Integer getSysFlowQuestionPageCount(SysFlowQuestion t);

    List<SysFlowQuestion> getSysFlowQuestionPageList(SysFlowQuestion t);

}