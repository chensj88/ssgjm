package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtDepartmentDao;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.service.EtDepartmentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-23 14:32:40
 */
@Service
public class EtDepartmentServiceImpl implements EtDepartmentService {

	@Resource
	private EtDepartmentDao etDepartmentDao;
	@Autowired
	private SSGJHelper ssgjHelper;
	

	public Integer createEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.insertEntity(t);
	}

	public EtDepartment getEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.selectEntity(t);
	}

	public Integer getEtDepartmentCount(EtDepartment t) {
		return (Integer) this.etDepartmentDao.selectEntityCount(t);
	}

	public List<EtDepartment> getEtDepartmentList(EtDepartment t) {
		return this.etDepartmentDao.selectEntityList(t);
	}

	public int modifyEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.updateEntity(t);
	}

	public int removeEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.deleteEntity(t);
	}

	public List<EtDepartment> getEtDepartmentPaginatedList(EtDepartment t) {
		return this.etDepartmentDao.selectEntityPaginatedList(t);
	}

	public void createEtDepartmentExcel(List<List<Object>> departList, EtDepartment department) {

			for (List<Object> params : departList) {
				String typeName = params.get(0).toString();
				String deptName = params.get(1).toString();
				EtDepartment repeat = new EtDepartment();
				repeat.setDeptName(deptName);
				repeat.setTypeName(typeName);
				repeat.setIsDel(1);
				repeat.setSerialNo(department.getSerialNo());
				repeat = this.etDepartmentDao.selectEntity(repeat);
				if(repeat == null){
					repeat = new EtDepartment();
					repeat.setId(ssgjHelper.createSysHospitalDeptIdService());
					repeat.setDeptName(deptName);
					repeat.setDeptCode(String.valueOf(repeat.getId()));
					repeat.setTypeName(typeName);
					repeat.setIsDel(1);
					repeat.setSerialNo(department.getSerialNo());
					//分类code不能重复生成
					EtDepartment DeptType = new EtDepartment();
					DeptType.setTypeName(repeat.getTypeName());
					DeptType.setSerialNo(department.getSerialNo());
					DeptType.setIsDel(1);
					List<EtDepartment> deptTypeList= this.etDepartmentDao.selectEntityList(DeptType);
					if(deptTypeList.size()>0){
						repeat.setDeptType(deptTypeList.get(0).getDeptType());
					}else{
						repeat.setDeptType(String.valueOf(ssgjHelper.createSysHospitalDeptTypeIdService()));
					}
					this.etDepartmentDao.insertEntity(repeat);
				}
		}

	}

	@Override
	public void generateDepartInfo(EtDepartment department, String path) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<EtDepartment> departmentList = this.etDepartmentDao.selectEntityList(department);
		List<String> colList = new ArrayList<String>();
		colList.add("typeName");
		colList.add("deptName");
		List<Map> dataList = new ArrayList<Map>();

		for (EtDepartment deptInfo : departmentList) {
			Map<String, String> deptMap = new HashMap<>();
			deptMap.put("typeName",deptInfo.getTypeName());
			deptMap.put("deptName", deptInfo.getDeptName());
			dataList.add(deptMap);
		}
		dataMap.put("colList", colList);
		dataMap.put("colSize", colList.size());
		dataMap.put("data", dataList);
		ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
	}

	@Override
	public List<EtDepartment> selectDepartmentTypeList(EtDepartment t) {

		return this.etDepartmentDao.selectDepartmentTypeList(t);
	}

}
