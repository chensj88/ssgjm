package cn.com.winning.ssgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
 
/**
 * 基础数据类型处理Controller
 *
 * @author thinkpad
 * @date 2018-01-04
 */
@Controller
@RequestMapping("/admin/basicData")
public class SysDataInfoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysDataInfoController.class);
	@Autowired
	private SSGJHelper ssgjHelper;
	@RequestMapping(value = "/dataInfo.do")
	public String userinfo(HttpServletRequest request, Model model){
		return "auth/module/sysDataInfo";
	}
	/**
     * 基础数据类型列表
     * @param row
     * @return
     */
	@RequestMapping("/list.do")
	@ResponseBody
	@ILog(operationName="基础数据类型列表",operationType="list")
	public Map<String, Object> list(Row row,SysDataInfo sysDataInfo) {
		sysDataInfo.setRow(row);
		List<SysDataInfo> sysDataInfos = getFacade().getSysDataInfoService().getSysDataInfoPaginatedListForSelectiveKey(sysDataInfo);
		int total =  getFacade().getSysDataInfoService().getSysDataInfoCountForSelectiveKey(sysDataInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", sysDataInfos);
		map.put("total", total);
		map.put("status", Constants.SUCCESS);
		return map;
	}

	/**
	 * 基础数据类型列表不分页
	 * @param sysDataInfo
	 * @return
	 */
	@RequestMapping("/listNoPage.do")
	@ResponseBody
	@ILog(operationName="基础数据类型列表",operationType="list")
	public Map<String, Object> listNoPage(SysDataInfo sysDataInfo) {
		List<SysDataInfo> sysDataInfos = getFacade().getSysDataInfoService().getSysDataInfoListForSelectiveKey(sysDataInfo);
		int total =  getFacade().getSysDataInfoService().getSysDataInfoCount(sysDataInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", sysDataInfos);
		map.put("total", total);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID查询基础数据类型
     * @param t
     * @return
     */
	@RequestMapping("/getById.do")
	@ResponseBody
	@ILog(operationName="通过产品ID查询基础数据类型",operationType="getProductInfoById")
	public  Map<String, Object> getDataInfoById(SysDataInfo t){
		System.err.println("通过产品ID查询基础数据类型");
		t  =  getFacade().getSysDataInfoService().getSysDataInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", t);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID删除产品信息
     * @param t
     * @return
     */
	@RequestMapping("/deleteById.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="通过产品ID删除基础数据类型",operationType="deleteById")
	public  Map<String, Object> deleteById(SysDataInfo t) {
		t.setStatus(0);
        getFacade().getSysDataInfoService().modifySysDataInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 添加基础数据类型
     * @param 
     * @return
     */
	@RequestMapping("/addSysDataInfo.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="添加基础数据类型",operationType="addSysDataInfo")
	public Map<String, Object> addDataInfo(SysDataInfo t)  {
		Long id = ssgjHelper.createDataId();
		System.err.println(id);
		t.setId(id);
		t.setStatus(1);
		getFacade().getSysDataInfoService().createSysDataInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 修改基础数据类型
     * @param t
     * @return
     */
	@RequestMapping("/update.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="修改基础数据类型",operationType="update")
	public Map<String, Object> update(SysDataInfo t) {
        getFacade().getSysDataInfoService().modifySysDataInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
}
