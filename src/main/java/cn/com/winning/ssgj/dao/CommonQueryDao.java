package cn.com.winning.ssgj.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chensj
 * @title
 * @email chensj@winning.com.cn
 * @package cn.com.winning.ssgj.dao
 * @date: 2018-11-01 16:02
 */
public interface CommonQueryDao {

    Set<String> listUserRolesByUserId(@Param(value="userId")String userId) ;

    List<String> loadButtonFlagForPageByUrlAndRoles(@Param(value="param")Map<String, String> param);
}
