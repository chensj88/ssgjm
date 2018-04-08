package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.EtProjectImplPath;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 项目信息/实施路径
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-04-04 9:31
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/implPath")
public class EtImplPathController extends BaseController {


    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String,Object> listImplPathInfo(Row row){
        EtProjectImplPath entity = new EtProjectImplPath();
        entity.setRow(row);
        List<EtProjectImplPath>  etProjectImplPathList = super.getFacade().getEtProjectImplPathService().getEtProjectImplPathPaginatedList(entity);
        int total = super.getFacade().getEtProjectImplPathService().getEtProjectImplPathCount(entity);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etProjectImplPathList);
        return result;
    }

    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public HttpServletResponse wiriteExcel(SysUserInfo queryUser, HttpServletResponse response) throws IOException {

        String fileName = "EtImplPath.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        //将导出内容写入Excel中
        super.getFacade().getEtProjectImplPathService().generateEtProjectImplPathInfo(path);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("实施路径.xls","UTF-8"));
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

}
