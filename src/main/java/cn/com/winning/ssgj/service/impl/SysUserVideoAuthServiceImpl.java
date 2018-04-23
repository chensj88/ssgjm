package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.dao.SysUserVideoAuthTempDao;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.SysUserVideoAuthTemp;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysUserVideoAuthDao;
import cn.com.winning.ssgj.domain.SysUserVideoAuth;
import cn.com.winning.ssgj.service.SysUserVideoAuthService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-20 15:01:08
 */
@Service
public class SysUserVideoAuthServiceImpl implements SysUserVideoAuthService {

	@Resource
	private SysUserVideoAuthDao sysUserVideoAuthDao;
	@Autowired
	private SSGJHelper ssgjHelper;
	@Autowired
	private SysUserVideoAuthTempDao sysUserVideoAuthTempDao;

	public Integer createSysUserVideoAuth(SysUserVideoAuth t) {
		return this.sysUserVideoAuthDao.insertEntity(t);
	}

	public SysUserVideoAuth getSysUserVideoAuth(SysUserVideoAuth t) {
		return this.sysUserVideoAuthDao.selectEntity(t);
	}

	public Integer getSysUserVideoAuthCount(SysUserVideoAuth t) {
		return (Integer) this.sysUserVideoAuthDao.selectEntityCount(t);
	}

	public List<SysUserVideoAuth> getSysUserVideoAuthList(SysUserVideoAuth t) {
		return this.sysUserVideoAuthDao.selectEntityList(t);
	}

	public int modifySysUserVideoAuth(SysUserVideoAuth t) {
		return this.sysUserVideoAuthDao.updateEntity(t);
	}

	public int removeSysUserVideoAuth(SysUserVideoAuth t) {
		return this.sysUserVideoAuthDao.deleteEntity(t);
	}

	public List<SysUserVideoAuth> getSysUserVideoAuthPaginatedList(SysUserVideoAuth t) {
		return this.sysUserVideoAuthDao.selectEntityPaginatedList(t);
	}

	@Override
	public void generateUserVideoAuthInfo(List<List<Object>> userAuth, SysUserVideoAuth auth) {
		SysUserVideoAuthTemp authTemp = new SysUserVideoAuthTemp();
		authTemp.setSerialNo(auth.getSerialNo());
		this.sysUserVideoAuthDao.deleteSysUserVideoAuthBySerialNo(auth);
		this.sysUserVideoAuthTempDao.deleteSysUserVideoAuthTempBySerialNo(authTemp);
		SysUserInfo user = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
		for (List<Object> auths : userAuth) {
			String userCode = auths.get(0).toString();
			if(!"0".equals(userCode.trim())){
				SysUserVideoAuthTemp videoAuthTemp = new SysUserVideoAuthTemp();
				videoAuthTemp.setId(ssgjHelper.createSysUserVideoAuthTempIdService());
				videoAuthTemp.setUserCode(auths.get(0).toString());
				videoAuthTemp.setUserName(auths.get(1).toString());
				videoAuthTemp.setMenuName(auths.get(2).toString().trim());
				videoAuthTemp.setSerialNo(auth.getSerialNo());
				videoAuthTemp.setSuserCode(auth.getSerialNo()+""+auths.get(0).toString());
				videoAuthTemp.setCreator(user.getId());
				videoAuthTemp.setCreateTime(new Timestamp(new Date().getTime()));
				this.sysUserVideoAuthTempDao.insertEntity(videoAuthTemp);
			}
		}

		this.sysUserVideoAuthTempDao.batchUpdteMenuName(authTemp);
		this.sysUserVideoAuthTempDao.validateExistsInSySUserInfo(authTemp);
		this.sysUserVideoAuthTempDao.updateExistsUserId(authTemp);
		List<SysUserVideoAuthTemp> authTempList =  this.sysUserVideoAuthTempDao.selectSumUserVideoAuthList(authTemp);
		for (SysUserVideoAuthTemp temp : authTempList) {
			SysUserVideoAuth videoAuth = new SysUserVideoAuth();
			videoAuth.setId(ssgjHelper.createSysUserVideoAuthIdService());
			videoAuth.setUserCode(temp.getUserCode());
			videoAuth.setUserName(temp.getUserName());
			videoAuth.setMenuName(temp.getMenuName());
			videoAuth.setMenuCode(temp.getMenuCode());
			videoAuth.setUserId(temp.getUserId());
			videoAuth.setSerialNo(temp.getSerialNo());
			videoAuth.setSuserCode(temp.getSuserCode());
			videoAuth.setIsExist(temp.getIsExist());
			videoAuth.setCreator(user.getId());
			videoAuth.setCreateTime(new Timestamp(new Date().getTime()));
			this.sysUserVideoAuthDao.insertEntity(videoAuth);
		}
		this.sysUserVideoAuthTempDao.deleteSysUserVideoAuthTempBySerialNo(authTemp);
	}

	@Override
	public void modifyHospitalUserVideoAuth(SysUserVideoAuth auth) {
		this.sysUserVideoAuthDao.modifyHospitalUserVideoAuth(auth);
	}


}
