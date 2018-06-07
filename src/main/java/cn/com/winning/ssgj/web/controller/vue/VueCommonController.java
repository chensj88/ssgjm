package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysDictInfo;
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
    public HttpServletResponse exportWorkTemplate( HttpServletResponse response) throws IOException {
        String fileName = "EtWorkReportTemplate.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("站点问题导入模板.xls","UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return response;
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
        List<SysDictInfo> firstDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(dict);
        dict.getMap().put("type","2");
        List<SysDictInfo> secondDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(dict);
        dict.getMap().put("type","3");
        List<SysDictInfo> thirdDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(dict);
        dict.getMap().put("type","4");
        List<SysDictInfo> fourDicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByType(dict);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data1",firstDicts);
        result.put("data2",secondDicts );
        result.put("data3",thirdDicts);
        result.put("data4",fourDicts);
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


    @RequestMapping(value = "/common/queryMenu.do")
    @ResponseBody
    public Map<String, Object> queryUserCustomerAndProjectInfo(long userid) {
        //TODO  测试使用
        List<NodeTree> nodeTreeList = super.getFacade().getCommonQueryService().queryUserManagerCustomer(userid);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", nodeTreeList);
        return result;
    }

}
