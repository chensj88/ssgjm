package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.TemplateUtils;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.domain.SysLoginUser;
import cn.com.winning.ssgj.domain.expand.NodeTree;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-06-04 12:22
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/commons")
public class VueCommonController extends BaseController {
    /**
     * 下载工作底稿导入模板
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadWorkreport.do")
    public void exportWorkTemplate( HttpServletResponse response,String serialNo) throws IOException {
        TemplateUtils.createWorkreport(getFacade(),response,serialNo,"工作底稿导入.xls");
    }

    /**
     * 获取产品信息
     * @return result
     */
    @RequestMapping(value = "/getTypeCodes.do" )
    @ResponseBody
    public Map<String,Object> getProductCodes(){
        SysDictInfo dict = new SysDictInfo();
        dict.setDictCode(Constants.DictInfo.PRODUCT_NAME);
        dict.getMap().put("type","1");
        List<SysDictInfo> firstDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByDesc(dict);
        dict.getMap().put("type","2");
        List<SysDictInfo> secondDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByDesc(dict);
        dict.getMap().put("type","3");
        List<SysDictInfo> thirdDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByDesc(dict);
        dict.getMap().put("type","4");
        List<SysDictInfo> fourDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByDesc(dict);
        dict.getMap().put("type","5");
        List<SysDictInfo> fiveDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByDesc(dict);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data1",firstDicts);
        result.put("data2",secondDicts );
        result.put("data3",thirdDicts);
        result.put("data4",fourDicts);
        result.put("data5",fiveDicts);
        return result;

    }

    /**
     * 根据项目ID查询可以分配的项目组成员信息
     * @param pmId
     * @return
     */
    @RequestMapping(value = "/queryWorkAssig.do" )
    @ResponseBody
    public Map<String,Object> queryWorkAssig(Long pmId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", this.getEtUserInfo(pmId));
        return result;

    }


    @RequestMapping(value = "/queryMenu.do")
    @ResponseBody
    public Map<String, Object> queryUserCustomerAndProjectInfo(long userid,String name) {
        List<NodeTree> nodeTreeList = super.getFacade().getCommonQueryService().queryUserManagerCustomer(userid,name);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", nodeTreeList);
        return result;
    }


    @RequestMapping(value = "/checkToken.do")
    @ResponseBody
    public Map<String, Object> checkToken(String token){
        SysLoginUser loginUser = super.getFacade().getSysLoginUserService().getSysLoginUserBySelectiveKey(token);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", loginUser == null ? 0 : 1 );
        return result;
    }

}
