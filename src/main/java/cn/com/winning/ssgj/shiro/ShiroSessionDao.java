package cn.com.winning.ssgj.shiro;

import cn.com.winning.ssgj.base.util.SerializableUtils;
import cn.com.winning.ssgj.dao.SysLoginUserDao;
import cn.com.winning.ssgj.domain.SysLoginUser;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.shiro
 * @date 2018-06-07 16:48
 */
public class ShiroSessionDao extends CachingSessionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSessionDao.class);
    @Autowired
    private SysLoginUserDao sysLoginUserDao;

    //在创建完session之后会调用
    @Override
    protected Serializable doCreate(Session session) {
        LOGGER.info("创建登录session");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
      /*  SysUserInfo user = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        SysLoginUser loginUser = new SysLoginUser();
        loginUser.setToken(sessionId.toString());
        loginUser.setUserId(user.getId());
        loginUser.setSessionString(SerializableUtils.serialize(session));
        loginUser.setLoginTime(new Timestamp(new Date().getTime()));
        sysLoginUserDao.insertEntity(loginUser);*/
        return session.getId();
    }
    //更新session最后访问时间、停止session、设置超时时间、移除属性值时会调用
    @Override
    protected void doUpdate(Session session) {
        LOGGER.info("更新session");
//        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
//            return; //如果会话过期/停止 没必要再更新了
//        }
//        SysLoginUser loginUser = new SysLoginUser();
//        loginUser.setToken(session.getId().toString());
//        loginUser.setSessionString(SerializableUtils.serialize(session));
//        sysLoginUserDao.updateEntity(loginUser);
    }

    //用户logout、session过期会调用
    @Override
    protected void doDelete(Session session) {
        LOGGER.info("注销session");
//        SysLoginUser loginUser = new SysLoginUser();
//        loginUser.setToken(session.getId().toString());
//        loginUser.setLogoutTime(new Timestamp(new Date().getTime()));
//        sysLoginUserDao.updateEntity(loginUser);
    }


    /**
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        SysLoginUser loginUser = new SysLoginUser();
        loginUser.setToken(sessionId.toString());
        List<SysLoginUser> sessionStrList = sysLoginUserDao.selectEntityList(loginUser);
        if(sessionStrList.size() == 0) return null;
        return SerializableUtils.deserialize(sessionStrList.get(0).getSessionString());
    }
}
