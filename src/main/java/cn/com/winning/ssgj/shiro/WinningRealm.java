package cn.com.winning.ssgj.shiro;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import cn.com.winning.ssgj.base.Constants.User;
import cn.com.winning.ssgj.dao.EntityDao;

/**
 * @description 自定义认证、授权管理类
 * @author {wen yong}
 * @date 2018年2月24日 下午12:45:07 
 * @update 2018年2月24日 下午12:45:07 
 * @version 
 * @param <T>
 *
 * 
 */
@Component
public class WinningRealm<T> extends AuthorizingRealm {
	@Resource
	private EntityDao<T> userdao;//需要查询用户基本信息及权限的dao接口
	/**
	 * 
	 * @param principals
	 * @return
	 * @Description 用户授权
	 * @Date 2018年2月24日 下午1:13:36 
	 *
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();// 取得用户登录名
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		try {
			auth.setRoles(userdao.listRolesByMEember(username));// 获取所有的角色
			auth.setStringPermissions(userdao.listActionsByMEember(username));// 获取所有的权限
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auth;
	}

	/**
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 * @Description 用户认证
	 * @Date 2018年2月24日 下午1:13:03 
	 *
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		try {
			User vo = userdao.login(username);//获取用户基本信息
			if (vo == null) {
				throw new UnknownAccountException("该用户不存在");
			} else {
				String password = new String((char[]) token.getCredentials());
				if (vo.getPassword().equals(password)) {
					return new SimpleAuthenticationInfo(username, password, "memberRealm");
				} else {
					throw new IncorrectCredentialsException("密码错误");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}
	
}
