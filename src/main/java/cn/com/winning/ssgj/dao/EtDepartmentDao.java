package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-23 14:32:40
 */
public interface EtDepartmentDao extends EntityDao<EtDepartment> {

    List<EtDepartment> selectDepartmentTypeList(EtDepartment t);

    int selectDepartmentForSiteQuestionCount(EtDepartment department);

    int selectDepartmentForSiteInstallCount(EtDepartment department);

    List<String> selectDepartmentInitCode(EtDepartment department);

    List<EtDepartment> selectDepartmentByInitCode(EtDepartment department);

    List<EtDepartment> selectDepartmentByInitCodeForNum(EtDepartment department);
}
