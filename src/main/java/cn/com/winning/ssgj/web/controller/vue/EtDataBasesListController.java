package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.PinyinTools;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * chen,kuai
 * 2018-7-3 13:34:08
 * 医院数据库信息维护
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/databasesList")
public class EtDataBasesListController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 查询项目下医院科室信息
     * @param databases
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> queryDatabasesList(EtDatabasesList databases, Row row) {
        databases.setRow(row);
        List<EtDatabasesList> queryDatabasesList = super.getFacade().getEtDatabasesListService().getEtDatabasesListList(databases);
        int total = super.getFacade().getEtDatabasesListService().getEtDatabasesListCount(databases);
        //获取数据库类型集合
        SysDictInfo info = new SysDictInfo();
        info.setDictCode("dbType");
        List<SysDictInfo> dbTypes = super.getFacade().getSysDictInfoService().getSysDictInfoList(info);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("dbTypes",dbTypes);
        result.put("status", Constants.SUCCESS);
        result.put("rows", queryDatabasesList);
        return result;

    }


    /**
     * 获取全部数据库的连接
     * @param process
     * @return
     */
    @RequestMapping(value = "/changeDatabases.do")
    @ResponseBody
    public Map<String,Object> changeDatabases(EtDatabasesList process){
        Map<String,Object> result = new HashMap<String,Object>();
        List<String> databases = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url ="jdbc:sqlserver://"+process.getIp()+";user="+process.getUserName()+";password="+process.getPw();
        //String url = "jdbc:sqlserver://172.16.0.200:1433;user=sa;password=zyc@8468";//sa身份连接
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            String SQL = "SELECT * FROM sys.databases;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                databases.add(rs.getString(1));
            }
            result.put("status","true");

        }catch (Exception e){
            e.printStackTrace();
            result.put("status","false");
        }
        result.put("databases",databases);
        return result;
    }

    /**
     * 添加或者修改用户信息
     * @param database
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> addOrModify(EtDatabasesList database) {
        Map<String, Object> result = new HashMap<String, Object>();
        //IP+数据库名称 确定唯一性
        EtDatabasesList unique = new EtDatabasesList();
        unique.setSerialNo(database.getSerialNo());
        unique.setIp(database.getIp());
        unique.setDatabaseName(database.getDatabaseName());
        List<EtDatabasesList> uniqueList = new ArrayList<EtDatabasesList>();
        if(database.getId() == 0L){  //新增
            uniqueList = super.getFacade().getEtDatabasesListService().getEtDatabasesListList(unique);
            if(uniqueList.size() > 0){
                result.put("status", "repeat");
            }else{
                database.setId(ssgjHelper.createEtDataBasesList());
                database.setCreator(database.getOperator());
                database.setCreateTime(new Timestamp(new java.util.Date().getTime()));
                database.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtDatabasesListService().createEtDatabasesList(database);
            }
        }else{ //修改
            database.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtDatabasesListService().modifyEtDatabasesList(database);
        }

        return result;
    }


    @RequestMapping(value = "/deleteDatabase.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> deleteDatabase(EtDatabasesList database){
        Map<String,Object> result = new HashMap<String,Object>();
        super.getFacade().getEtDatabasesListService().removeEtDatabasesList(database);
        result.put("status", Constants.SUCCESS);
        return result;
    }

}
