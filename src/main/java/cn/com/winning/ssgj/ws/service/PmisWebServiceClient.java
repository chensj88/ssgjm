package cn.com.winning.ssgj.ws.service;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.util.ConnectionUtil;
import cn.com.winning.ssgj.base.util.PmisWSUtil;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.ws.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title PMIS接口表导入数据表
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws.service
 * @date 2018-02-05 13:09
 */
@Component
public class PmisWebServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmisWebServiceClient.class);

    public static void main(String[] args) {
        PmisWebServiceClient.insertData();
//         PmisWebServiceClient.insertPMISInterfaceData("2");
    }

    /**
     * PMIS接口表信息
     */
    private static final Map<String, String> pmisInfoData;
    private static final String[] sqls = {
            "INSERT INTO PMIS_CONTRACT_INFO (ID, ZHTXX, HTYHF, CODE, DAMC, NAME, SQRQ, SXRQ, QDNY, NF, HTLX, KPLX, XMLX, SFGQ, HTJE, KHXX, HTQYF, QYGS, HTQDRY, HTGZRY, XSSSJG, ZBJG, XSSSGS) VALUES (-99999, -99999, -99999, '实施工具研发', '实施工具研发', '实施工具研发', '2008-09-24 00:00:00', '2008-09-01 00:00:00', '200809', '2008', 1, 1, 1, 0, '800000.00', -99999, '实施工具研发', 2, 171, 171, 1137, 1121, 2)",
            "INSERT INTO PMIS_CUSTOMER_INFORMATION (ID, CODE, NAME, PY, KHLB, KHLX, QYXX, CITY, SFCJ, YYLX, MZL, CWS, YYSL, FWJGS, ZYYW, GSGM, SSGS, SSJG, YYDJ, YYGM, YYBJDJ, KHDZ, YZBM, XXKZ, ZGYZ, SFSZKHJL, KHJL, SYCPTX, FWTRY, ZDKHBZ, LXR, LXFS, GLGS, REMARK, GXR, GXSJ, DJR, DJSJ, ZT) VALUES (-99999, '-99999', '实施工具研发', 'ssgjyf', 2, '0', '31', '0', '0', '0', 2222, 12, 0, 0, '0', '0', '99', '1686', '6', '1', '2', '安徽合肥', null, '0', '0', '1', '0', 'CIS;HIS;LIS;无线;系统集成;硬件', null, '0', null, null, '0', null, '7110', '2018-06-26 14:56:51', '0', null, 1)",
            "INSERT INTO PMIS_PROJECT_BASIC_INFO (ID, FWLX, XMLX, XMDJ, NAME, KHJDQC, KHJDTJ, KHJDLJ, QS, XMMS, JHNR, XMJL, ZJZT, JHZT, ZKRY, SSGS, JSDQ, SSJG, KHJG, HTXX, HTLX, BZSM, GZRQ, GZSM, KHXX, KHSR, YWCKHSR, KHXS, KHXSSR, WCRQ, GXSJ, GXR, ZT, THFS, JDQRZT, QRBZSM, XDRQ, YJZT, YJRQ) VALUES (-99999, 0, '0', 0, '99999_实施工具研发', '0', '0.00', '20', 1, '', '实施工具研发（一期）', 158, 1, 0, 0, 2, 1034, 1034, 1034, -99999, 1, '实施工具研发', '', '', -99999, '0', '0', '1.000000', '0', '2014-12-31 00:00:00', '2018-02-01 17:12:33', 6393, 1, 0, 1, '', '2012-08-17 00:00:00', 1, '2016-09-23 00:00:00 ')",
            "INSERT INTO PMIS_PROJCT_USER (ID, XMLCB, RYFL, RY) VALUES (-99999, -99999, 0, 7110)",
            "INSERT INTO PMIS_PROJCT_USER (ID, XMLCB, RYFL, RY) VALUES (-99998, -99999, 0, 7284)",
            "INSERT INTO PMIS_PROJCT_USER (ID, XMLCB, RYFL, RY) VALUES (-99997, -99999, 0, 7507)",
            "INSERT INTO PMIS_PROJCT_USER (ID, XMLCB, RYFL, RY) VALUES (-99996, -99999, 0, 100001)"
    };

    static {
        pmisInfoData = new HashMap<String, String>();
        pmisInfoData.put("1", "SYS_USER_INFO"); //用户信息 成功
        pmisInfoData.put("2", "PMIS_CUSTOMER_INFORMATION"); //客户信息库 成功
        pmisInfoData.put("3", "PMIS_PROJECT_BASIC_INFO"); //  项目基本信息 OK
        pmisInfoData.put("4", "PMIS_PROJCT_USER"); // OK  项目成员信息 OK
        pmisInfoData.put("5", "PMIS_CONTRACT_INFO"); // 合同信息 OK
        pmisInfoData.put("6", "PMIS_PRODUCT_INFO");// 产品信息库 OK
        pmisInfoData.put("7", "PMIS_CONTRACT_PRODUCT_INFO"); // 合同产品清单 OK
        pmisInfoData.put("8", "SYS_ORGANIZATION"); //组织机构
        pmisInfoData.put("9", "PMIS_PRODUCT_LINE_INFO"); //产品条线信息
    }

    /**
     * 加载PMIS系统接口数据
     */
    public static void insertData() {
        for (String dataType : pmisInfoData.keySet()) {
            String tableNam = pmisInfoData.get(dataType);
            generateDymaicSql(tableNam, dataType);
        }
        for (String sql : sqls) {
            LOGGER.info(sql);
            executeSqlInfo(sql);
        }

    }

    /**
     * 加载PMIS系统接口数据
     */
    public void importPmisData() {
        for (String dataType : pmisInfoData.keySet()) {
            String tableNam = pmisInfoData.get(dataType);
            generateDymaicSql(tableNam, dataType);
        }
        //添加实施工具客户、项目、合同信息
        for (String sql : sqls) {
            LOGGER.info(sql);
            executeSqlInfo(sql);
        }

    }

    /**
     * 工作底稿导入
     * @param info
     * @return
     */
    public BizProcessResult importWorkDataToPmis(EtSiteQuestionInfo info){
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(info);
        List<LbParameter> variables = new ArrayList<LbParameter>();
        BizProcessResult result = lbeBusinessService.execBizProcess(loginResult.getSessionId(),
                Constants.PmisWSConstants.WORK_WS_SERVICE_OBJECT_NAME,"",
                params,variables);
        if(result.getResult() != 1){
            throw  new SSGJException(result.getMessage());
        }
        PmisWSUtil.createLogoutResult(loginResult);
        return result;
    }

    /**
     * 查询工作底稿状态
     * @param reportNo
     * @return
     */
    public  String[]  queryReportWorkStatus(String reportNo){
        String[] attrs = {"",""};
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createQueryLbParameter(Constants.PmisWSConstants.QUERY_TYPE_NAME_FOR_REPORT,reportNo);
        QueryOption queryOption = PmisWSUtil.createFirstCountValueOption();
        List<LbParameter> variables = new ArrayList<LbParameter>();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(),
                 Constants.PmisWSConstants.QUERY_WORK_WS_SERVICE_OBJECT_NAME,params,"",queryOption);
        PmisWSUtil.createLogoutResult(loginResult);
        if (result.getResult() <= 0) {
            throw new SSGJException(result.getMessage());
        } else {
            int total = result.getCount();
            LbRecord records = result.getRecords().get(0);
            attrs[0] = records.getValues().get(0).toString();
            attrs[1] = records.getValues().get(1).toString();
        }
        return attrs;
    }

    /**
     * 用户登录(PMIS验证)
     * @param userid 工号
     * @param password 密码(MD5)
     * @return
     */
    public QueryResult userLoginValidateByPmis(String userid,String password){
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createUserLoginLbParameter(userid,password);
        QueryResult queryResult = lbeBusinessService.query(
                loginResult.getSessionId(),
                Constants.PmisWSConstants.USER_LOGIN_WS_SERVICE_OBJECT_NAME,
                params,
                "",
                new QueryOption());
        PmisWSUtil.createLogoutResult(loginResult);
        return queryResult;
    }
    /**
     * 导入特定PMIS接口表数据
     *
     * @param dataType
     */
    public static void insertPMISInterfaceData(String dataType) {
        String tableName = pmisInfoData.get(dataType);
        generateDymaicSql(tableName, dataType);
        if("5".equals(dataType)){
            executeSqlInfo(sqls[0]);
        }else if("2".equals(dataType)){
            executeSqlInfo(sqls[1]);
        }else if("3".equals(dataType)){
            executeSqlInfo(sqls[2]);
        }else if("4".equals(dataType)){
            executeSqlInfo(sqls[3]);
            executeSqlInfo(sqls[4]);
            executeSqlInfo(sqls[5]);
            executeSqlInfo(sqls[6]);
        }
    }


    /**
     * SQL数据生成
     *
     * @param tableName
     * @param dataType
     */
    private static void generateDymaicSql(String tableName, String dataType) {
        LOGGER.info("删除表" + tableName + "数据开始");
        String sql = "";
        if (dataType.equals(Constants.PmisWSConstants.WS_SERVICE_QUERY_USER)) {
            sql = "DELETE FROM " + tableName + " WHERE USER_TYPE='1';";
        } else {
            sql = "DELETE FROM " + tableName + " ;";
        }
        LOGGER.info("删除表SQL：" + sql);
        executeSqlInfo(sql);
        LOGGER.info("删除表" + tableName + "数据结束");
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(dataType);
        QueryOption queryOption = PmisWSUtil.createFirstCountValueOption();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                params, "", queryOption);
        if (result.getResult() <= 0) {
            throw new SSGJException(result.getMessage());
        } else {
            long statTime = System.currentTimeMillis();
            int total = result.getCount();
            LOGGER.info("导入表" + tableName + "数据开始:");
            LOGGER.info(tableName + "表数据量：" + total + "  列数：" + result.getMetaData().getColumnCount());
            int pageSize = Constants.PmisWSConstants.QUERY_BATCH_SIZE;
            int lastPageSize = total % pageSize;
            int page = 0;
            boolean pageEnd = false;
            if (lastPageSize > 0) {
                page = total / pageSize + 1;
                pageEnd = true;
            } else {
                page = total / pageSize;
                pageEnd = false;
            }
//            System.out.println(lastPageSize);
//            System.out.println(page);
//            System.out.println(pageEnd);
            for (int i = 1; i <= page; i++) {
                if ((i == page) && pageEnd) {
                    queryOption = PmisWSUtil.createQueryValueOption(i, pageSize);
                    result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                            params, "", queryOption);
                    resolveWSResult(result, tableName);
                } else {
                    queryOption = PmisWSUtil.createQueryValueOption(i);
                    result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                            params, "", queryOption);
                    resolveWSResult(result, tableName);
                }
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("导入表" + tableName + "数据结束，耗时：" + (endTime - statTime));
        }
        PmisWSUtil.createLogoutResult(loginResult);
    }

    private static void resolveWSResult(QueryResult result, String tableName) {
        if (tableName.toUpperCase().equals("SYS_USER_INFO")) {
            resolveUserInfoData(result);
        } else {
            generateSQLInfo(result, tableName);
        }
    }

    /**
     * 生成其他接口表信息
     *
     * @param result
     * @param tableName
     */
    private static void generateSQLInfo(QueryResult result, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into " + tableName.toUpperCase() + " values \n");
        List<LbRecord> recordList = result.getRecords();
        List<ColInfo> colInfos = result.getMetaData().getColInfo();

        for (int i = 0; i < recordList.size(); i++) {
            LbRecord record = recordList.get(i);
            List<Object> values = record.getValues();
            for (int j = 0; j < values.size(); j++) {
//                 开始数据为数字
                if (j == 0 && colInfos.get(j).getType() == 1) {
                    sb.append("(" + resolveValue(values.get(j)) + ",");
//              开始数据为字符或者时间
                } else if (j == 0 && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3)) {
                    sb.append("(\'" + resolveStringValue(values.get(j)) + "\',");
//              全部结束数据为数字
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1) &&
                        (i == recordList.size() - 1)) {
                    sb.append(resolveValue(values.get(j)) + " );  \n");
//              全部结束数据为字符或者时间
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3) &&
                        (i == recordList.size() - 1)) {
                    sb.append("\'" + resolveStringValue(values.get(j)) + "\' );  \n");
//              单行结束为数字
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1)) {
                    sb.append(resolveValue(values.get(j)) + "  ),  \n");
//              单行结束为字符或者时间
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3)) {
                    sb.append("\'" + resolveStringValue(values.get(j)) + " \' ),\n");
//              字段处理 数字
                } else if (colInfos.get(j).getType() == 1) {
                    sb.append(resolveValue(values.get(j)) + ",");
                } else if (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3) {
//              字段处理 字符或者时间
                    sb.append("\'" + resolveStringValue(values.get(j)) + "\',");
                }
            }
        }
//        System.out.println(sb.toString());
        executeSqlInfo(sb.toString());
    }

    /**
     * 处理字符串中存在的单引号问题
     *
     * @param o
     * @return
     */
    private static String resolveStringValue(Object o) {
        return o.toString().replace("'", "‘");
    }

    /**
     * 生成用户信息SQL
     *
     * @param result
     */
    private static void resolveUserInfoData(QueryResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into SYS_USER_INFO (");
        List<LbRecord> recordList = result.getRecords();
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        for ( int colNum = 0 ; colNum < colInfos.size() ;colNum++) {
            if(colNum == colInfos.size() -1){
                sb.append(colInfos.get(colNum).getLabel().toUpperCase()+",USER_TYPE");
            }else{
                sb.append(colInfos.get(colNum).getLabel().toUpperCase()+",");
            }
        }
        sb.append(" ) values \n");
        for (int i = 0; i < recordList.size(); i++) {
            LbRecord record = recordList.get(i);
            List<Object> values = record.getValues();
            for (int j = 0; j < values.size(); j++) {
                if (j == 0 && colInfos.get(j).getType() == 1) {
                    sb.append("(" + values.get(j) + ",");
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1) &&
                        (i == recordList.size() - 1)) {
                    sb.append(values.get(j) + ",\'1\');  \n");
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1)) {
                    sb.append(values.get(j) + ",\'1\'),  \n");
                } else if (j == 0 && colInfos.get(j).getType() == 0) {
                    sb.append("(\'" + values.get(j) + "\',");
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0)) {
                    sb.append("\'" + values.get(j) + " \' ),");
                } else if (colInfos.get(j).getType() == 1) {
                    sb.append(values.get(j) + ",");
                } else if (colInfos.get(j).getType() == 0) {
                    sb.append("\'" + values.get(j) + "\',");
                }
            }
        }
        System.out.println(sb);
        executeSqlInfo(sb.toString());
    }

    /**
     * 转换接口中存在数字为空的数据
     *
     * @param o
     * @return
     */
    private static Object resolveValue(Object o) {
        return o.toString().equals("") ? 0 : o;
    }

    /**
     * 执行生成SQL脚本
     *
     * @param sql
     */
    private static void executeSqlInfo(String sql) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionUtil.getConnection();
            connection.setAutoCommit(true);
            ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeAll(connection, ps, rs);
        }
    }
}
