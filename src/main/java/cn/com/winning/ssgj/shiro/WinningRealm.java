package cn.com.winning.ssgj.shiro;

import java.sql.SQLException;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.dao.EtUserLookProjectDao;
import cn.com.winning.ssgj.dao.SysOrgExtDao;
import cn.com.winning.ssgj.dao.SysUserInfoDao;
import cn.com.winning.ssgj.domain.EtUserLookProject;
import cn.com.winning.ssgj.domain.SysOrgExt;
import cn.com.winning.ssgj.domain.SysUserInfo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.winning.ssgj.base.Constants.User;
import cn.com.winning.ssgj.dao.EntityDao;

/**
 * @description 自定义认证、授权管理类
 * @author {wen yong}
 * @date 2018年2月24日 下午12:45:07 
 * @update 2018年2月24日 下午12:45:07 
 * @version 
 * @param
 *
 * 
 */
@Component
public class WinningRealm extends AuthorizingRealm {
	@Autowired
	private SysUserInfoDao sysUserInfoDao; //需要查询用户基本信息及权限的dao接口
	@Autowired
	private EtUserLookProjectDao etUserLookProjectDao;
	@Autowired
	private SysOrgExtDao sysOrgExtDao;
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
	/*	try {
			auth.setRoles(userdao.listRolesByMEember(username));// 获取所有的角色
			auth.setStringPermissions(userdao.listActionsByMEember(username));// 获取所有的权限
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
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
		String userid = (String) token.getPrincipal();
		SysUserInfo userInfo = new SysUserInfo();
		userInfo.setUserid(userid);
		userInfo.setStatus(Constants.PMIS_STATUS_USE);
		userInfo = sysUserInfoDao.selectEntity(userInfo);//获取用户基本信息
		if (userInfo == null) {
			throw new UnknownAccountException("该用户不存在");
		} else {
			String password = new String((char[]) token.getCredentials());
			if (userInfo.getPassword().equals(password)) {
				EtUserLookProject etUserLookProject = etUserLookProjectDao.selectLastUserLookProject(userInfo.getId());
				SysOrgExt orgExt = sysOrgExtDao.selectUserOrgExtByUserOrgId(userInfo.getSsgs());
				if(etUserLookProject != null){
					userInfo.getMap().put("C_ID",etUserLookProject.getCId());
					userInfo.getMap().put("CU_ID",etUserLookProject.getSerialNo());
					userInfo.getMap().put("PM_ID",etUserLookProject.getPmId());
				}
				userInfo.getMap().put("orgExt",orgExt.getOrgNameExt());
				return new SimpleAuthenticationInfo(userInfo, password, "memberRealm");
			} else {
				throw new IncorrectCredentialsException("密码错误");
			}
		}

	}
	
}
