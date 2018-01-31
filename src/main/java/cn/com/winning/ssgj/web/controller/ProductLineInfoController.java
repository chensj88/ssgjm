package cn.com.winning.ssgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.PmisProductLineInfo;
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
 * 产品条线信息处理Controller
 *
 * @author thinkpad
 * @date 2018-01-04
 */
@Controller
@RequestMapping("/admin/productLineInfo")
public class ProductLineInfoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ProductLineInfoController.class);
	@Autowired
	private SSGJHelper ssgjHelper;
	@RequestMapping(value = "/productLine.do")
	public String userinfo(HttpServletRequest request, Model model){
		return "auth/module/productLineInfo";
	}
	/**
     * 产品条线信息列表
     * @param row
     * @return
     */
	@RequestMapping("/list.do")
	@ResponseBody
	@ILog(operationName="产品条线信息列表",operationType="list")
	public Map<String, Object> list(Row row) {
		PmisProductLineInfo productLineInfo = new PmisProductLineInfo();
		productLineInfo.setRow(row); 
		List<PmisProductLineInfo> productLineInfos = getFacade().getPmisProductLineInfoService().getPmisProductLineInfoPaginatedList(productLineInfo);
		int total =  getFacade().getPmisProductLineInfoService().getPmisProductLineInfoCount(productLineInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", productLineInfos);
		map.put("total", total);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID查询产品条线信息
     * @param user
     * @return
     */
	@RequestMapping("/getById.do")
	@ResponseBody
	@ILog(operationName="通过产品ID查询产品条线信息",operationType="getProductLineInfoById")
	public  Map<String, Object> getProductLineInfoById(PmisProductLineInfo t){
		System.err.println("通过产品ID查询产品条线信息");
		t  =  getFacade().getPmisProductLineInfoService().getPmisProductLineInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", t);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID删除产品条线信息
     * @param user
     * @return
     */
	@RequestMapping("/deleteById.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="通过产品ID删除产品条线信息",operationType="deleteById")
	public  Map<String, Object> deleteById(PmisProductLineInfo t) {
		t.setZt(2);
        getFacade().getPmisProductLineInfoService().modifyPmisProductLineInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 添加产品条线信息
     * @param productLineInfo
     * @return
     */
	@RequestMapping("/addProductLineInfo.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="添加产品条线信息",operationType="addProductLineInfo")
	public Map<String, Object> addProductLineInfo(PmisProductLineInfo t)  {
		Long id = ssgjHelper.productLineInfo();
		t.setId(id);
		t.setZt(1);
		System.err.println(t);
		getFacade().getPmisProductLineInfoService().createPmisProductLineInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 修改产品条线信息
     * @param 
     * @return
     */
	@RequestMapping("/update.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="修改产品条线信息",operationType="update")
	public Map<String, Object> update(PmisProductLineInfo t) {
        getFacade().getPmisProductLineInfoService().modifyPmisProductLineInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
}
