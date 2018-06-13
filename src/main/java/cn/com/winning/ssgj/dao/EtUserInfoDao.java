package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtUserInfoDao extends EntityDao<EtUserInfo> {

    EtUserInfo getAllEtUserInfo(EtUserInfo t);

}
