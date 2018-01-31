package cn.com.winning.ssgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.SysProductInterfaceInfo;
import cn.com.winning.ssgj.domain.SysThirdInterfaceInfo;
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
 * 第三方接口类型信息处理Controller
 *
 * @author thinkpad
 * @date 2018-01-04
 */
@Controller
@RequestMapping("/admin/thirx")
public class SysInterFaceInfoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysInterFaceInfoController.class);
	@Autowired
	private SSGJHelper ssgjHelper;
	@RequestMapping(value = "/interfaceInfo.do")
	public String userinfo(HttpServletRequest request, Model model){
		return "auth/module/interFaceInfo";
	}
	/**
     * 第三方接口类型信息
     * @param row
     * @return
     */
	@RequestMapping("/list.do")
	@ResponseBody
	@ILog(operationName="第三方接口类型信息",operationType="list")
	public Map<String, Object> list(Row row) {
		SysThirdInterfaceInfo sysThirdInterfaceInfo = new SysThirdInterfaceInfo();
		sysThirdInterfaceInfo.setRow(row);
		List<SysThirdInterfaceInfo> sysThirdInterfaceInfos = getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfoPaginatedList(sysThirdInterfaceInfo);
		int total =  getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfoCount(sysThirdInterfaceInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", sysThirdInterfaceInfos);
		map.put("total", total);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID查询第三方接口类型信息
     * @param user
     * @return
     */
	@RequestMapping("/getById.do")
	@ResponseBody
	@ILog(operationName="通过产品ID查询第三方接口类型信息",operationType="getById")
	public  Map<String, Object> getById(SysThirdInterfaceInfo t){
		System.err.println("通过产品ID查询第三方接口类型信息");
		t  =  getFacade().getSysThirdInterfaceInfoService().getSysThirdInterfaceInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", t);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID删除第三方接口类型信息
     * @return
     */
	@RequestMapping("/deleteById.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="通过产品ID删除第三方接口类型信息",operationType="deleteById")
	public  Map<String, Object> deleteById(SysThirdInterfaceInfo t) {
		t.setStatus(0);
        getFacade().getSysThirdInterfaceInfoService().modifySysThirdInterfaceInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 添加第三方接口类型信息
     * @param 
     * @return
     */
	@RequestMapping("/addInterFaceInfo.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="添加第三方接口类型信息",operationType="addInterFaceInfo")
	public Map<String, Object> addInterFaceInfo(SysThirdInterfaceInfo t)  {
		Long id = ssgjHelper.createThirdInterfaceId();
		System.err.println(id);
		t.setId(id);
		t.setStatus(1);
		getFacade().getSysThirdInterfaceInfoService().createSysThirdInterfaceInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 修改第三方接口类型信息
     * @param user
     * @return
     */
	@RequestMapping("/update.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="修改第三方接口类型信息",operationType="update")
	public Map<String, Object> update(SysThirdInterfaceInfo t) {
        getFacade().getSysThirdInterfaceInfoService().modifySysThirdInterfaceInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
}
