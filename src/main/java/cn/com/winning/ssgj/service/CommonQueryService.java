package cn.com.winning.ssgj.service;

import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.service
 * @date 2018-03-20 10:29
 */
public interface CommonQueryService {

    public List<NodeTree> queryUserCustomerProjectTreeInfo(Long userId);
}
