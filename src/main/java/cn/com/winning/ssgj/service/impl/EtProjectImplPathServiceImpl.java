package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtProjectImplPathDao;
import cn.com.winning.ssgj.domain.EtProjectImplPath;
import cn.com.winning.ssgj.service.EtProjectImplPathService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-04 09:10:33
 */
@Service
public class EtProjectImplPathServiceImpl implements EtProjectImplPathService {

	@Resource
	private EtProjectImplPathDao etProjectImplPathDao;
	

	public Integer createEtProjectImplPath(EtProjectImplPath t) {
		return this.etProjectImplPathDao.insertEntity(t);
	}

	public EtProjectImplPath getEtProjectImplPath(EtProjectImplPath t) {
		return this.etProjectImplPathDao.selectEntity(t);
	}

	public Integer getEtProjectImplPathCount(EtProjectImplPath t) {
		return (Integer) this.etProjectImplPathDao.selectEntityCount(t);
	}

	public List<EtProjectImplPath> getEtProjectImplPathList(EtProjectImplPath t) {
		return this.etProjectImplPathDao.selectEntityList(t);
	}

	public int modifyEtProjectImplPath(EtProjectImplPath t) {
		return this.etProjectImplPathDao.updateEntity(t);
	}

	public int removeEtProjectImplPath(EtProjectImplPath t) {
		return this.etProjectImplPathDao.deleteEntity(t);
	}

	public List<EtProjectImplPath> getEtProjectImplPathPaginatedList(EtProjectImplPath t) {
		return this.etProjectImplPathDao.selectEntityPaginatedList(t);
	}

	@Override
	public void generateEtProjectImplPathInfo(String path) {
		EtProjectImplPath etProjectImplPath = new EtProjectImplPath();
		List<EtProjectImplPath> queryUserList = this.etProjectImplPathDao.selectEntityList(etProjectImplPath);
		//int total = (Integer) this.etProjectImplPathDao.selectEntityCount(etProjectImplPath);
		List<String> colList = new ArrayList<String>();
		colList.add("milepost");
		colList.add("mainStep");
		colList.add("stepType");
		colList.add("stepContent");
		colList.add("stepDesc");
		colList.add("stepOutput");
		colList.add("remark");
		List<Map> dataList = new ArrayList<Map>();

		for (EtProjectImplPath implPath : queryUserList) {
			Map<String, String> userMap = new HashMap<>();
			userMap.put("milepost", implPath.getMilepost());
			userMap.put("mainStep", implPath.getMainStep());
			userMap.put("stepType", implPath.getStepType());
			userMap.put("stepContent", implPath.getStepContent());
			userMap.put("stepDesc", implPath.getStepDesc());
			userMap.put("stepOutput", implPath.getStepOutput());
			userMap.put("remark", implPath.getRemark());
			dataList.add(userMap);
		}
		ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
	}

}
