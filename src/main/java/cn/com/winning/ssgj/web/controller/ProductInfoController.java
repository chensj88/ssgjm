package cn.com.winning.ssgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.com.winning.ssgj.domain.PmisProductInfo;
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
 * 产品信息处理Controller
 *
 * @author thinkpad
 * @date 2018-01-04
 */
@Controller
@RequestMapping("/admin/productInfo")
public class ProductInfoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ProductInfoController.class);
	@Autowired
	private SSGJHelper ssgjHelper;
	@RequestMapping(value = "/product.do")
	public String userinfo(HttpServletRequest request, Model model){
		return "auth/module/productInfo";
	}
	/**
     * 用户信息列表
     * @param row
     * @return
     */
	@RequestMapping("/list.do")
	@ResponseBody
	@ILog(operationName="用户信息列表",operationType="list")
	public Map<String, Object> list(Row row) {
		PmisProductInfo productInfo = new PmisProductInfo();
		productInfo.setRow(row);
		List<PmisProductInfo> productInfos = getFacade().getPmisProductInfoService().getPmisProductInfoPaginatedList(productInfo);
		int total =  getFacade().getPmisProductInfoService().getPmisProductInfoCount(productInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", productInfos);
		map.put("total", total);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID查询产品信息
     * @param user
     * @return
     */
	@RequestMapping("/getById.do")
	@ResponseBody
	@ILog(operationName="通过产品ID查询产品信息",operationType="getProductInfoById")
	public  Map<String, Object> getProductInfoById(PmisProductInfo t){
		System.err.println("通过产品ID查询产品信息");
		t  =  getFacade().getPmisProductInfoService().getPmisProductInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", t);
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 通过产品ID删除产品信息
     * @param user
     * @return
     */
	@RequestMapping("/deleteById.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="通过产品ID删除产品信息",operationType="deleteById")
	public  Map<String, Object> deleteById(PmisProductInfo t) {
		t.setZt(2);
        getFacade().getPmisProductInfoService().modifyPmisProductInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 添加产品信息
     * @param productInfo
     * @return
     */
	@RequestMapping("/addProductInfo.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="添加产品信息",operationType="addProductInfo")
	public Map<String, Object> addProductInfo(PmisProductInfo t)  {
		Long id = ssgjHelper.createPuductId();
		t.setId(id);
		t.setZt(1);
		System.err.println(t);
		getFacade().getPmisProductInfoService().createPmisProductInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
	/**
     * 修改产品信息
     * @param user
     * @return
     */
	@RequestMapping("/update.do")
	@ResponseBody
	@Transactional
	@ILog(operationName="修改产品信息",operationType="update")
	public Map<String, Object> updateUser(PmisProductInfo t) {
        getFacade().getPmisProductInfoService().modifyPmisProductInfo(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.SUCCESS);
		return map;
	}
}
