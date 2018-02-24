package cn.com.winning.ssgj.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.winning.ssgj.base.helper.SSGJHelper;

@Controller
@RequestMapping("/admin/func")
public class SysFunctionController {

	@Autowired
	private SSGJHelper ssgjHelper;

	@RequestMapping(value = "/funcInfo.do")
	public String getFuncInfoPage(HttpServletRequest request, Model model) {
		return "auth/user/funcinfo";
	}
//	@RequestMapping("/list.do")
//	@ResponseBody
//	@ILog(operationName = "功能查询列表", operationType = "list")
//	public Map<String, Object> listFunction(Row row) {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		String sortColumn = params.get("sort").toString();
//		String orderType = params.get("sortOrder").toString();
//		int pageNo = Integer.valueOf(params.get("page").toString());
//		int rows = Integer.valueOf(params.get("rows").toString());
//		PageHelper.startPage(pageNo, rows);
//		PageInfo<SysFunction> pageInfo = new PageInfo<SysFunction>();
//		sysFunctionServiceImpl.findAllFunctionByPage(pageInfo);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("total", pageInfo.getTotal());
//		result.put("rows", pageInfo.getList());
//		result.put("status", Constants.SUCCESS);
//		return result;
//	}
//
//
//	@RequestMapping("/add.do")
//	@ResponseBody
//	@Transactional
//	@ILog(operationName = "添加新的功能", operationType = "add")
//	public Map<String, Object> addFunction(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		SysFunction function = new SysFunction();
//		BeanUtils.copyProperties(function, params);
//		function.setFuncId(Integer.valueOf(ssgjHelper.getFuncId()+""));
//		function.setLastUpdateTime(new Date());
//		//TODO 添加操作人
//		sysFunctionServiceImpl.addFunction(function);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("status", Constants.SUCCESS);
//		return result;
//	}
//
//	@RequestMapping("/update.do")
//	@ResponseBody
//	@Transactional
//	@ILog(operationName = "修改功能信息", operationType = "update")
//	public Map<String, Object> updateFunction(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		SysFunction function = new SysFunction();
//		BeanUtils.copyProperties(function, params);
//		function.setLastUpdateTime(new Date());
//		//TODO 添加操作人
//		sysFunctionServiceImpl.updateFunction(function);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("status", Constants.SUCCESS);
//		return result;
//	}
//
//	@RequestMapping("/deleteById.do")
//	@ResponseBody
//	@Transactional
//	@ILog(operationName = "删除功能信息", operationType = "delete")
//	public Map<String, Object> deleteFunctionById(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		SysFunction function = new SysFunction();
//		BeanUtils.copyProperties(function, params);
//		//TODO 添加操作人
//		sysFunctionServiceImpl.deleteFunctionById(function);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("status", Constants.SUCCESS);
//		return result;
//	}
//
//	@RequestMapping("/getById.do")
//	@ResponseBody
//	@ILog(operationName = "使用ID查询功能信息", operationType = "query")
//	public Map<String, Object> queryFunctiontById(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		SysFunction function = new SysFunction();
//		function.setFuncId(Integer.valueOf(params.get("funcId").toString()));
//		function = sysFunctionServiceImpl.getFunctionById(function);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("data", function);
//		result.put("status", Constants.SUCCESS);
//		return result;
//	}
//
//	@RequestMapping("/validatefuncNameExist.do")
//	@ResponseBody
//	@ILog(operationName = "校验功能名称是否存在", operationType = "validate")
//	public Map<String, Object> validatefuncNameExist(HttpServletRequest request){
//		Map<String, Object> params = RequestUtil.getRequestParams(request);
//		SysFunction function = new SysFunction();
//		function.setFuncName(params.get("funcName").toString());
//		boolean isValid = sysFunctionServiceImpl.validateFunctionNameExist(function);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("valid", isValid);
//		result.put("status", Constants.SUCCESS);
//		return result;
//	}

}
