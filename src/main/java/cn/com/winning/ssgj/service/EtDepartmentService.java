package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtDepartment;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-23 14:32:40
 */
public interface EtDepartmentService {

	Integer createEtDepartment(EtDepartment t);

	int modifyEtDepartment(EtDepartment t);

	int removeEtDepartment(EtDepartment t);

	EtDepartment getEtDepartment(EtDepartment t);

	List<EtDepartment> getEtDepartmentList(EtDepartment t);

	Integer getEtDepartmentCount(EtDepartment t);

	List<EtDepartment> getEtDepartmentPaginatedList(EtDepartment t);

    void createEtDepartmentExcel(List<List<Object>> departList, EtDepartment department);

    void generateDepartInfo(EtDepartment department, String path);

    List<EtDepartment> selectDepartmentTypeList(EtDepartment info);

    String checkEtDepartmentIsUse(EtDepartment department);
}