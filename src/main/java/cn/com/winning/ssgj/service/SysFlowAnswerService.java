package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysFlowAnswer;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysFlowAnswerService {

    Integer createSysFlowAnswer(SysFlowAnswer t);

    int modifySysFlowAnswer(SysFlowAnswer t);

    int removeSysFlowAnswer(SysFlowAnswer t);

    SysFlowAnswer getSysFlowAnswer(SysFlowAnswer t);

    List<SysFlowAnswer> getSysFlowAnswerList(SysFlowAnswer t);

    Integer getSysFlowAnswerCount(SysFlowAnswer t);

    List<SysFlowAnswer> getSysFlowAnswerPaginatedList(SysFlowAnswer t);

}