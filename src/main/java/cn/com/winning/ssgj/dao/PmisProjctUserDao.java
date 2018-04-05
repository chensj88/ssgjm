package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.PmisProjctUser;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface PmisProjctUserDao extends EntityDao<PmisProjctUser> {

    List<Long> selectPmisProjctUserIdListByProjectIdList(PmisProjctUser projctUser);
}
