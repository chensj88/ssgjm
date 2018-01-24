package cn.com.winning.ssgj.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.util.RequestUtil;
import cn.com.winning.ssgj.base.Constants;

@Controller
@RequestMapping("/admin/role/")
public class RoleController {

	/*@Autowired
	private ISysRoleService sysRoleServiceImpl;
	
	@RequestMapping("/list.do")
	@ResponseBody
	@ILog(operationName="查询角色列表",operationType="list")
	public Map<String, Object> listRole(HttpServletRequest request){
		 Map<String, Object> params = RequestUtil.getRequestParams(request);
		 String sortColumn = params.get("sort").toString();
		 String orderType = params.get("sortOrder").toString();
		 int pageNo = Integer.valueOf(params.get("page").toString());
		 int rows = Integer.valueOf(params.get("rows").toString());
		 PageHelper.startPage(pageNo, rows);
		 PageInfo<SysRole> pageInfo = new PageInfo<SysRole>();
		 sysRoleServiceImpl.findAllRoleByPage(pageInfo);
		 Map<String, Object> result = new HashMap<String, Object>();
		 System.out.println(pageInfo.getList().size());
		 System.out.println( pageInfo.getTotal());
		 result.put("total", pageInfo.getTotal());
		 result.put("rows", pageInfo.getList());
		 result.put("status", Constants.SUCCESS);
		 return result;
	}
	@RequestMapping("/queryTree.do")
	@ResponseBody
	@ILog(operationName="查询角色树",operationType="roleTree")
	public Map<String, Object> queryRoleTree(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("data", sysRoleServiceImpl.queryRoleForRoleTree());
		return result;
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	@ILog(operationName="添加角色",operationType="addRole")
	public Map<String, Object> addRole(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException{
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		SysRole role = new SysRole();
		BeanUtils.copyProperties(role, params);
		role.setLastUpdateTime(new Date());
		//TODO 添加操作人
		sysRoleServiceImpl.addRole(role);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", Constants.SUCCESS);
		return result;
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	@ILog(operationName="更新角色",operationType="updateRole")
	public Map<String, Object> updateRole(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException{
		Map<String, Object> params = RequestUtil.getRequestParams(request);
		SysRole role = new SysRole();
		BeanUtils.copyProperties(role, params);
		role.setLastUpdateTime(new Date());
		//TODO 添加操作人
		sysRoleServiceImpl.updateRole(role);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", Constants.SUCCESS);
		return result;
	}
	@RequestMapping("/validateRoleNameExist.do")
	@ResponseBody
	@ILog(operationName="验证角色名称",operationType="validateRoleNameExist")
	public Map<String, Object> validateRoleNameExist(HttpServletRequest request){
		Map<String, Object>  params = RequestUtil.getRequestParams(request);
		System.out.println(params);
		String roleName = params.get("roleName").toString();
		String roleType = params.get("roleType").toString();
		SysRole role = new SysRole();
		role.setRoleName(roleName);
		role.setRoleType(roleType);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("valid", sysRoleServiceImpl.validateRoleNameExist(role));
		result.put("status", Constants.SUCCESS);
		return result;
	}
	
	@RequestMapping("/getById.do")
	@ResponseBody
	@ILog(operationName="使用ID查询角色",operationType="queryRoleById")
	public Map<String, Object> queryRoleById(HttpServletRequest request){
		Map<String, Object>  params = RequestUtil.getRequestParams(request);
		int roleId= Integer.valueOf(params.get("roleId").toString());
        SysRole role = new SysRole();
        role.setRoleId(roleId);
        role = sysRoleServiceImpl.getRoleById(role);		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", role);
		result.put("status", Constants.SUCCESS);
		return result;
	}
	
	@RequestMapping("/deleteById.do")
	@ResponseBody
	@ILog(operationName="使用ID删除角色",operationType="deleteRoleById")
	public Map<String, Object> deleteRoleById(HttpServletRequest request){
		Map<String, Object>  params = RequestUtil.getRequestParams(request);
		int roleId= Integer.valueOf(params.get("roleId").toString());
        SysRole role = new SysRole();
        role.setRoleId(roleId);
        sysRoleServiceImpl.deleteRoleById(role);		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", Constants.SUCCESS);
		return result;
	}*/
}
