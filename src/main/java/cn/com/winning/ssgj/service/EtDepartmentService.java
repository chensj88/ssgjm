package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.MobileSiteQuestion;

import javax.servlet.http.HttpServletResponse;

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

    void generateDepartInfo(EtDepartment department, HttpServletResponse response, String fileName);

    List<EtDepartment> selectDepartmentTypeList(EtDepartment info);

    String checkEtDepartmentIsUse(EtDepartment department);

    List<MobileSiteQuestion<EtDepartment>> getWechatDepartmentData(EtDepartment department);

    List<String> getEtDepartmentFirstInitCode(EtDepartment dept);
}