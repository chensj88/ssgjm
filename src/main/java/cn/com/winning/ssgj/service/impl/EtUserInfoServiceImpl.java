package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtUserInfoDao;
import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.service.EtUserInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtUserInfoServiceImpl implements EtUserInfoService {

    @Resource
    private EtUserInfoDao etUserInfoDao;



    public Integer createEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.insertEntity(t);
    }


    public EtUserInfo getEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.selectEntity(t);
    }


    public Integer getEtUserInfoCount(EtUserInfo t) {
        return (Integer) this.etUserInfoDao.selectEntityCount(t);
    }


    public List<EtUserInfo> getEtUserInfoList(EtUserInfo t) {
        return this.etUserInfoDao.selectEntityList(t);
    }


    public int modifyEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.updateEntity(t);
    }


    public int removeEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.deleteEntity(t);
    }


    public List<EtUserInfo> getEtUserInfoPaginatedList(EtUserInfo t) {
        return this.etUserInfoDao.selectEntityPaginatedList(t);
    }


	@Override
	public void generateEtUserInfo(EtUserInfo etUserInfo, String path) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createEtUserInfoList(List<List<Object>> etUserList) {
		// TODO Auto-generated method stub
		
	}

}
