package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.MobileSiteQuestion;
import cn.com.winning.ssgj.domain.support.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
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

<<<<<<< HEAD
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
		int total = (Integer) this.etContractTaskDao.selectEntityCount(task);;
		Row row = new Row(0,total);
		task.setRow(row);
		List<EtContractTask> contractTaskList = this.etContractTaskDao.selectEntityList(task);
		List<String> colList = new ArrayList<String>();
		colList.add("zxtmc");
		colList.add("allocateUser");
		colList.add("teamMemeber");
		colList.add("mx");
		colList.add("bz");
		List<Map> dataList = new ArrayList<Map>();

		for (EtContractTask t : contractTaskList) {
			Map<String, String> userMap = new HashMap<>();
			userMap.put("zxtmc", t.getZxtmc());
			userMap.put("allocateUser", t.getMap().get("allocateName").toString());
			userMap.put("teamMemeber", queryUserByIdList(t));
			userMap.put("mx", t.getMx());
			userMap.put("bz", t.getBz());
			dataList.add(userMap);
		}
		dataMap.put("colList", colList);
		dataMap.put("colSize", colList.size());
		dataMap.put("data", dataList);
		ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
	}

	private String queryUserByIdList(EtContractTask t) {
		String teamMemeber = "";
		if(!StringUtil.isEmptyOrNull(t.getTeamMembers())){
			teamMemeber = etContractTaskDao.selectTaskTeamMemebers(t);
		}
		return teamMemeber;
	}

	/**
	 * 检查当前系统信息是否被使用
	 * @param task
	 * @return
	 */
	@Override
	public String  checkEtContractTaskIsUse(EtContractTask task) {
		int shCount = etContractTaskDao.selectEtContractTaskForEtSoftHardCount(task);
		int sqCount = etContractTaskDao.selectEtContractTaskForEtSitemQuestionCount(task);
		String pdIdStr = etContractTaskDao.selectEtContractTaskForSiteInstall(task);
		boolean isSiteInstall = false;
		if(pdIdStr != null){
			for (int i = 0; i < pdIdStr.split(",").length; i++) {
				if(task.getId() == Long.parseLong(pdIdStr.split(",")[i])){
					isSiteInstall = true;
				}
			}
		}

		String msg = "";
		if(shCount > 0){
			msg += "系统在硬件清单中使用，";
		}
		if(sqCount > 0){
			msg += "系统在站点问题中使用,";
		}
		if(isSiteInstall){
			msg += "系统在站点安装中使用,";
		}
		return msg;
	}
=======
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
    public void generateEtContractTask(EtContractTask task, HttpServletResponse response, String fileName) {
        List<EtContractTask> contractTaskList = this.etContractTaskDao.selectEntityList(task);
        List<String> colList = new ArrayList<String>();
        colList.add("zxtmc");
        colList.add("allocateUser");
        colList.add("teamMemeber");
        colList.add("mx");
        colList.add("bz");

        List<String> colNameList = new ArrayList<String>();
        colNameList.add("系统名称");
        colNameList.add("主力工程师");
        colNameList.add("组员");
        colNameList.add("产品大类");
        colNameList.add("备注");
        List<Map> dataList = new ArrayList<Map>();
        for (EtContractTask t : contractTaskList) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("zxtmc", t.getZxtmc());
            userMap.put("allocateUser", t.getMap().get("allocateName").toString());
            userMap.put("teamMemeber", queryUserByIdList(t));
            userMap.put("mx", t.getMx());
            userMap.put("bz", t.getBz());
            dataList.add(userMap);
        }

        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, colList, colNameList, response, workbook, fileName);
    }

    private String queryUserByIdList(EtContractTask t) {
        String teamMemeber = "";
        if (!StringUtil.isEmptyOrNull(t.getTeamMembers())) {
            teamMemeber = etContractTaskDao.selectTaskTeamMemebers(t);
        }
        return teamMemeber;
    }

    /**
     * 检查当前系统信息是否被使用
     *
     * @param task
     * @return
     */
    @Override
    public String checkEtContractTaskIsUse(EtContractTask task) {
        int shCount = etContractTaskDao.selectEtContractTaskForEtSoftHardCount(task);
        int sqCount = etContractTaskDao.selectEtContractTaskForEtSitemQuestionCount(task);
        String pdIdStr = etContractTaskDao.selectEtContractTaskForSiteInstall(task);
        boolean isSiteInstall = false;
        if (pdIdStr != null) {
            for (int i = 0; i < pdIdStr.split(",").length; i++) {
                if (task.getId() == Long.parseLong(pdIdStr.split(",")[i])) {
                    isSiteInstall = true;
                }
            }
        }
        String msg = "";
        if (shCount > 0) {
            msg += "系统在硬件清单中使用，";
        }
        if (sqCount > 0) {
            msg += "系统在站点问题中使用,";
        }
        if (isSiteInstall) {
            msg += "系统在站点安装中使用,";
        }
        return msg;
    }

    @Override
    public List<String> getEtContractTaskFirstInitCode(String serialNo) {
        EtContractTask task = new EtContractTask();
        task.setSerialNo(serialNo);
        return etContractTaskDao.selectEtContractTaskFirstInitCode(task);
    }

    @Override
    public List<MobileSiteQuestion<EtContractTask>> getWechatContractTaskData(String serialNo) {
        EtContractTask task = new EtContractTask();
        task.setSerialNo(serialNo);
        List<String> bzs = etContractTaskDao.selectEtContractTaskFirstInitCode(task);
        List<MobileSiteQuestion<EtContractTask>> data = new ArrayList<>();
        for (String name : bzs) {
            MobileSiteQuestion<EtContractTask> ctask = new MobileSiteQuestion<>();
            task.setBz(name);
            ctask.setGroupName(name);
            ctask.setListQuery(
                    "#".equals(name)?
                            etContractTaskDao.selectWechatContractTaskDataForNum(task) :
                            etContractTaskDao.selectWechatContractTaskData(task));
            data.add(ctask);
        }
        return data;
    }
>>>>>>> 88475f27c4d1fa9b299c02adb5d2d4a630a9b4ce

}
