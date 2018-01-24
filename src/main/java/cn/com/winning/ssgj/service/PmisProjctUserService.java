package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisProjctUser;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisProjctUserService {

    Integer createPmisProjctUser(PmisProjctUser t);

    int modifyPmisProjctUser(PmisProjctUser t);

    int removePmisProjctUser(PmisProjctUser t);

    PmisProjctUser getPmisProjctUser(PmisProjctUser t);

    List<PmisProjctUser> getPmisProjctUserList(PmisProjctUser t);

    Integer getPmisProjctUserCount(PmisProjctUser t);

    List<PmisProjctUser> getPmisProjctUserPaginatedList(PmisProjctUser t);

}