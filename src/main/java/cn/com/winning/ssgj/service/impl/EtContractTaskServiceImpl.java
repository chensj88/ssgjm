package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.domain.support.Row;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtContractTaskDao;
import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.service.EtContractTaskService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-26 16:19:08
 */
@Service
public class EtContractTaskServiceImpl implements EtContractTaskService {

	@Resource
	private EtContractTaskDao etContractTaskDao;
	

	public Integer createEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.insertEntity(t);
	}

	public EtContractTask getEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.selectEntity(t);
	}

	public Integer getEtContractTaskCount(EtContractTask t) {
		return (Integer) this.etContractTaskDao.selectEntityCount(t);
	}

	public List<EtContractTask> getEtContractTaskList(EtContractTask t) {
		return this.etContractTaskDao.selectEntityList(t);
	}

	public int modifyEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.updateEntity(t);
	}

	public int removeEtContractTask(EtContractTask t) {
		return this.etContractTaskDao.deleteEntity(t);
	}

	public List<EtContractTask> getEtContractTaskPaginatedList(EtContractTask t) {
		return this.etContractTaskDao.selectEntityPaginatedList(t);
	}

	@Override
	public Integer getEtContractTaskMergeCount(EtContractTask t) {
		return this.etContractTaskDao.selectEtContractTaskMergeCount(t);
	}

	@Override
	public List<EtContractTask> getEtContractTaskPageMergeList(EtContractTask t) {
		return this.etContractTaskDao.selectEtContractTaskMergePageList(t);
	}

	@Override
	public void generateEtContractTask(EtContractTask task, String path) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int total = this.etContractTaskDao.selectEtContractTaskMergeCount(task);
		Row row = new Row(0,total);
		task.setRow(row);
		List<EtContractTask> contractTaskList = this.etContractTaskDao.selectEtContractTaskMergePageList(task);
		List<String> colList = new ArrayList<String>();
		colList.add("zxtmc");
		colList.add("cpmc");
		colList.add("mx");
		colList.add("bz");
		List<Map> dataList = new ArrayList<Map>();

		for (EtContractTask t : contractTaskList) {
			Map<String, String> userMap = new HashMap<>();
			userMap.put("zxtmc", t.getZxtmc());
			userMap.put("cpmc", t.getCpmc());
			userMap.put("mx", t.getMx());
			userMap.put("bz", t.getBz());
			dataList.add(userMap);
		}
		dataMap.put("colList", colList);
		dataMap.put("colSize", colList.size());
		dataMap.put("data", dataList);
		ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
	}

}
