package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysFlowInfoDao extends EntityDao<SysFlowInfo> {

    List<SysFlowInfo> querySysFlowInfoList(SysFlowInfo t);

    List<SysFlowInfo> querySysFlowInfoByFlowTypeAndFlowCode(SysFlowInfo t);
}
