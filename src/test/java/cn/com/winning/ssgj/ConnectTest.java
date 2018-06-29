package cn.com.winning.ssgj;

import cn.com.winning.ssgj.base.util.ConnectionUtil;
import cn.com.winning.ssgj.base.util.MD5;
import org.junit.Test;

import java.sql.*;

public class ConnectTest {
    /**
     * 存储过程调用
     */
    //@Test
    public void testConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection
                     = DriverManager.getConnection("jdbc:sqlserver://172.16.0.200:1433;DatabaseName=THIS4","sa","zyc@8468");
            System.out.println(connection);
            PreparedStatement ps = connection.prepareStatement(EXISTS_PROC_SQL);
            ps.execute();
            ps = connection.prepareStatement(CREATE_PROC_SQL);
            ps.execute();
            ps = connection.prepareStatement(RUN_PROC_SQL);
            ps.execute();
            ps = connection.prepareStatement(GET_PROC_RESULT_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1) +"  " +rs.getString(2)+"  " +rs.getString(3));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

   // @Test
    public void testMD5(){
        String md5 = MD5.stringMD5("admin");
        System.out.println(md5);
    }

   // @Test
    public void testConnectionUtil(){
        System.out.println(ConnectionUtil.getConnection());
    }

    private static String EXISTS_PROC_SQL = "if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[usp_yy_jcsj_his]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)   begin drop procedure [dbo].[usp_yy_jcsj_his] end";
    private static String CREATE_PROC_SQL =
            "create proc usp_yy_jcsj_his  \n" +
            "-- @bz  ut_bz=0\n" +
            "as\n" +
            "-- *********      \n" +
            "-- exec usp_yy_jcsj_his '0'\n" +
            "-- *********\n" +
            " set nocount on\n" +
            "\n" +
            "if exists (select 1 from  sysobjects where  id = object_id('temp_result') and   type = 'U')\n" +
            "begin\n" +
            "   drop table temp_result\n" +
            "end\n" +
            "\n" +
            "create table temp_result\n" +
            "(bz  varchar(5),  ---标志\n" +
            " sm  varchar(100),  ---中文说明\n" +
            " jcyj  varchar(500)  ---检测语句\n" +
            ")\n" +
            "\n" +
            "--01公共部分\n" +
            "\n" +
            "--检测可调参数(YY_CONFIG)\n" +
            "if not exists(select 1 from YY_CONFIG (nolock))\n" +
            "\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在可调参数,请检查数据！','select * from YY_CONFIG'\n" +
            "end\n" +
            "\n" +
            "--检测区县代码(YY_QXDMK)\n" +
            "if not exists(select 1 from YY_QXDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在区县代码,请检查数据！','select * from YY_QXDMK'\n" +
            "end\n" +
            "\n" +
            "--检测医院基本信息库(YY_JBCONFIG)\n" +
            "if not exists(select 1 from YY_JBCONFIG (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在医院基本信息库,请检查数据！','select * from YY_JBCONFIG'\n" +
            "end\n" +
            "\n" +
            "\n" +
            "--02科室人事部分\n" +
            "--检测一级科室(YY_YJKSK)\n" +
            "if not exists(select 1 from YY_YJKSK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在一级科室,请检查数据！','select * from YY_YJKSK'\n" +
            "end\n" +
            "\n" +
            "--检测二级科室(YY_EJKSK)\n" +
            "if not exists(select 1 from YY_EJKSK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在二级科室,请检查数据！','select * from YY_EJKSK'\n" +
            "end\n" +
            "\n" +
            "--检测二级科室(YY_EJKSK)逻辑错误\n" +
            "if exists(select 1 from YY_EJKSK (nolock) where yjks_id not in (select id from YY_YJKSK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在二级科室无对应的一级科室,请检查数据！','select yjks_id,* from YY_EJKSK (nolock) where yjks_id not in (select id from YY_YJKSK)'\n" +
            "end\n" +
            "\n" +
            "--检测科室代码设置(YY_KSBMK)\n" +
            "if not exists(select 1 from YY_KSBMK (nolock) where jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在科室代码,请检查数据！','select * from YY_KSBMK'\n" +
            "end\n" +
            "\n" +
            "--检测科室代码设置(YY_KSBMK)逻辑错误\n" +
            "if exists(select 1 from YY_KSBMK (nolock) where jlzt=0 and yjks_id not in (select id from YY_YJKSK) and jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在科室代码无对应的一级科室,请检查数据！','select yjks_id,* from YY_KSBMK (nolock) where jlzt=0 and yjks_id not in (select id from YY_YJKSK) and jlzt=0'\n" +
            "end\n" +
            "\n" +
            "--检测科室代码设置(YY_KSBMK)逻辑错误\n" +
            "if exists(select 1 from YY_KSBMK (nolock) where jlzt=0 and  ejks_id not in (select id from YY_EJKSK) and jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在科室代码无对应的二级科室,请检查数据！','select ejks_id,* from YY_KSBMK (nolock) where jlzt=0 and  ejks_id not in (select id from YY_EJKSK) and jlzt=0'\n" +
            "end\n" +
            "\n" +
            "\n" +
            "--检测病区代码设置(YY_KSBMK)\n" +
            "if not exists(select 1 from ZY_BQDMK (nolock) where jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在病区代码,请检查数据！','select * from ZY_BQDMK'\n" +
            "end\n" +
            "\n" +
            "--检测科室病区对应设置(YY_KSBQDYK)\n" +
            "if not exists(select 1 from YY_KSBQDYK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在科室病区对应库,请检查数据！','select * from YY_KSBQDYK'\n" +
            "end\n" +
            "\n" +
            "--检测科室病区对应设置(YY_KSBQDYK)逻辑错误\n" +
            "if exists(select 1 from YY_KSBQDYK (nolock) where ksdm not in (select id from YY_KSBMK where jlzt=0))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在病区对应无效科室,请检查数据！','select ksdm,* from YY_KSBQDYK (nolock) where ksdm not in (select id from YY_KSBMK where jlzt=0)'\n" +
            "end\n" +
            "\n" +
            "--检测科室病区对应设置(YY_KSBQDYK)逻辑错误\n" +
            "if exists(select 1 from YY_KSBQDYK (nolock) where bqdm not in (select id from ZY_BQDMK where jlzt=0))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在科室对应无效病区,请检查数据！','select bqdm,* from YY_KSBQDYK (nolock) where bqdm not in (select id from ZY_BQDMK where jlzt=0)'\n" +
            "end\n" +
            "\n" +
            "\n" +
            "--检测职工代码对应设置(YY_ZGBMK)\n" +
            "if not exists(select 1 from YY_ZGBMK (nolock) where jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在职工代码,请检查数据！','select * from YY_ZGBMK'\n" +
            "end\n" +
            "\n" +
            "--检测职工代码对应设置(YY_ZGBMK)逻辑错误\n" +
            "if exists(select 1 from YY_ZGBMK (nolock) where ks_id not in (select id from YY_KSBMK where jlzt=0))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在职工对应无效科室,请检查数据！','select ks_id,* from YY_ZGBMK (nolock) where ks_id not in (select id from YY_KSBMK where jlzt=0)'\n" +
            "end\n" +
            "\n" +
            "--检测职工代码对应设置(YY_ZGBMK)逻辑错误\n" +
            "if exists(select 1 from YY_ZGBMK a(nolock),YY_KSBMK b(nolock) where a.ks_id=b.id and a.ks_mc<>b.name)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在职工对应无效科室名称,请检查数据！','select a.ks_id,b.id,a.ks_mc,b.name,* from YY_ZGBMK a(nolock),YY_KSBMK b(nolock) where a.ks_id=b.id and a.ks_mc<>b.name'\n" +
            "end\n" +
            "\n" +
            "--检测职工代码对应设置(YY_ZGBMK)逻辑错误\n" +
            "if exists(select 1 from YY_ZGBMK (nolock) where bq_id not in (select id from ZY_BQDMK where jlzt=0) and isnull(bq_id,'')<>'')\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在职工对应无效病区,请检查数据！','select bq_id,* from YY_ZGBMK (nolock) where bq_id not in (select id from ZY_BQDMK where jlzt=0) and isnull(bq_id,'')<>'''\n" +
            "end\n" +
            "\n" +
            "--检测职工代码对应设置(YY_ZGBMK)逻辑错误\n" +
            "if exists(select 1 from YY_ZGBMK a(nolock),ZY_BQDMK b(nolock) where a.bq_id=b.id and a.bq_mc<>b.name)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在职工对应无效病区名称,请检查数据！','select a.bq_id,b.id,a.bq_mc,b.name,* from YY_ZGBMK a(nolock),ZY_BQDMK b(nolock) where a.bq_id=b.id and a.bq_mc<>b.name'\n" +
            "end\n" +
            "\n" +
            "--检测职工代码对应设置(YY_ZGBMK)逻辑错误\n" +
            "if exists(select 1 from YY_ZGBMK a(nolock) where jlzt=0 and zglb='1' and (isnull(ghf,'')='' or isnull(zlf,'')=''))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在专家医生无对应挂号诊疗费,请检查数据！','select ghf,zlf,* from YY_ZGBMK a(nolock) where jlzt=0 and zglb=‘1’ and (isnull(ghf,'')='' or isnull(zlf,'')='')'\n" +
            "end\n" +
            "\n" +
            "--检测职工存在，权限未分配的医生\n" +
            "if exists(select 1 from YY_ZGBMK a,czryk b  where a.zglb in( 0,1,3) and (b.gwdm=''or gwdm is null ) and a.id=b.id)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','HIS系统存在未分配权限的医生，请检查数据！',\n" +
            "\t'select a.* from YY_ZGBMK a,czryk b  where a.zglb in(0,1,3) and (b.gwdm=''or gwdm is null ) and a.id=b.id'\n" +
            "end\n" +
            "\n" +
            "--检测职工存在，权限未分配的护士\n" +
            "if exists(select 1 from YY_ZGBMK a,czryk b  where a.zglb=2 and (b.gwdm=''or gwdm is null ) and a.id=b.id)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','HIS系统存在未分配权限的护士，请检查数据！',\n" +
            "\t'select a.* from YY_ZGBMK a,czryk b  where a.zglb=2 and (b.gwdm=''or gwdm is null ) and a.id=b.id'\n" +
            "end\n" +
            "\n" +
            "--检测操作员代码对应设置(czryk)\n" +
            "if not exists(select 1 from czryk (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在操作员代码,请检查数据！','select * from czryk'\n" +
            "end\n" +
            "\n" +
            "--检测操作员代码对应设置(czryk)逻辑错误\n" +
            "if exists(select 1 from czryk (nolock) where isdl=1 and zgbm not in (select id from YY_ZGBMK where jlzt=0))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在操作员对应无效职工,请检查数据！','select zgbm,* from czryk (nolock) where isdl=1 and zgbm not in (select id from YY_ZGBMK where jlzt=0)'\n" +
            "end\n" +
            "\n" +
            "--检测操作员代码对应设置(czryk)逻辑错误\n" +
            "if exists(select 1 from czryk (nolock) where isdl=1 and ks_id not in (select id from YY_KSBMK where jlzt=0))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在操作员对应无效科室,请检查数据！','select ks_id,* from czryk (nolock) where isdl=1 and ks_id not in (select id from YY_KSBMK where jlzt=0)'\n" +
            "end\n" +
            "--\n" +
            "--检测操作员代码对应设置(czryk)逻辑错误\n" +
            "if exists(select 1 from czryk a(nolock),YY_KSBMK b(nolock) where a.ks_id=b.id and a.ks_mc<>b.name)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在操作员对应无效科室名称,请检查数据！','select a.ks_id,b.id,a.ks_mc,b.name,* from czryk a(nolock),YY_KSBMK b(nolock) where a.ks_id=b.id and a.ks_mc<>b.name'\n" +
            "end\n" +
            "\n" +
            "--检测操作员代码对应设置(czryk)逻辑错误\n" +
            "if exists(select 1 from czryk (nolock) where bq_id not in (select id from ZY_BQDMK where jlzt=0) and isnull(bq_id,'')<>'')\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在操作员对应无效病区,请检查数据！','select bq_id,* from czryk (nolock) where bq_id not in (select id from ZY_BQDMK where jlzt=0) and isnull(bq_id,'')<>'''\n" +
            "end\n" +
            "\n" +
            "--检测操作员代码对应设置(czryk)逻辑错误\n" +
            "if exists(select 1 from czryk a(nolock),ZY_BQDMK b(nolock) where a.bq_id=b.id and a.bq_mc<>b.name)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在操作员对应无效病区名称,请检查数据！','select a.bq_id,b.id,a.bq_mc,b.name,* from czryk a(nolock),ZY_BQDMK b(nolock) where a.bq_id=b.id and a.bq_mc<>b.name'\n" +
            "end\n" +
            "\n" +
            "--检测工作岗位设置(gzgw)\n" +
            "if not exists(select 1 from gzgw (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在工作岗位,请检查数据！','select * from gzgw'\n" +
            "end\n" +
            "\n" +
            "--检测工作岗位权限(gzgwqx)\n" +
            "if not exists(select 1 from gzgwqx (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在工作岗位权限,请检查数据！','select * from gzgwqx'\n" +
            "end\n" +
            "\n" +
            "--02科室人事部分\n" +
            "--检测药品分类大类(YK_YPFLDLK)\n" +
            "if not exists(select 1 from YK_YPFLDLK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品分类大类,请检查数据！','select * from YK_YPFLDLK'\n" +
            "end\n" +
            "\n" +
            "--检测药品剂型库(YK_YPJXK)\n" +
            "if not exists(select 1 from YK_YPJXK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品剂型库,请检查数据！','select * from YK_YPJXK'\n" +
            "end\n" +
            "\n" +
            "--检测药品分类库(YK_YPFLK)\n" +
            "if not exists(select 1 from YK_YPFLK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品分类库,请检查数据！','select * from YK_YPFLK'\n" +
            "end\n" +
            "\n" +
            "--检测药品分类库(YK_YPFLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPFLK (nolock) where fl_id not in (select id from YK_YPFLDLK) and fl_id<>'')\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品分类库对应无效大类,请检查数据！','select fl_id,* from YK_YPFLK (nolock) where fl_id not in (select id from YK_YPFLDLK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品来源库(YK_YPLYK)\n" +
            "if not exists(select 1 from YK_YPLYK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品来源库,请检查数据！','select * from YK_YPLYK'\n" +
            "end\n" +
            "\n" +
            "--检测药品帐目设置(YK_ZMLBK)\n" +
            "if not exists(select 1 from YK_ZMLBK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品帐目设置,请检查数据！','select * from YK_ZMLBK'\n" +
            "end\n" +
            "\n" +
            "--检测药库药品操作代码设置(YK_YPCZDMK)\n" +
            "if not exists(select 1 from YK_YPCZDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药库药品操作代码,请检查数据！','select * from YK_YPCZDMK'\n" +
            "end\n" +
            "\n" +
            "--检测药房药品操作代码设置(YF_YPCZDMK)\n" +
            "if not exists(select 1 from YF_YPCZDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药房药品操作代码,请检查数据！','select * from YF_YPCZDMK'\n" +
            "end\n" +
            "\n" +
            "\n" +
            "--检测药库药品入库方式设置(YK_YPRKFS)\n" +
            "if not exists(select 1 from YK_YPRKFS (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药库药品入库方式,请检查数据！','select * from YK_YPRKFS'\n" +
            "end\n" +
            "\n" +
            "--检测药库药品出库方式设置(YK_YPCKFS)\n" +
            "if not exists(select 1 from YK_YPCKFS (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药库药品出库方式,请检查数据！','select * from YK_YPCKFS'\n" +
            "end\n" +
            "\n" +
            "--检测药品特殊标志(YK_TSBZK)\n" +
            "if not exists(select 1 from YK_TSBZK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品特殊标志,请检查数据！','select * from YK_TSBZK'\n" +
            "end\n" +
            "\n" +
            "--检测药品临床目录库(YK_YPLCMLK)\n" +
            "if not exists(select 1 from YK_YPLCMLK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品临床目录库,请检查数据！','select * from YK_YPLCMLK'\n" +
            "end\n" +
            "\n" +
            "--检测药品临床目录库(YK_YPLCMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPLCMLK (nolock) where ypjx not in (select id from YK_YPJXK) and jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品剂型对应无效剂型,请检查数据！','select ypjx,* from YK_YPLCMLK (nolock) where ypjx not in (select id from YK_YPJXK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品临床目录库(YK_YPLCMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPLCMLK (nolock) where ggdw not in (select name from YY_UNIT) and jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品单位对应无效单位,请检查数据！','select ggdw,* from YK_YPLCMLK (nolock) where ggdw not in (select name from YY_UNIT)'\n" +
            "end\n" +
            "\n" +
            "--检测药品规格目录库(YK_YPGGMLK)\n" +
            "if not exists(select 1 from YK_YPGGMLK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品规格目录库,请检查数据！','select * from YK_YPGGMLK'\n" +
            "end\n" +
            "\n" +
            "--检测药品规格目录库(YK_YPGGMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPGGMLK (nolock) where lc_idm not in (select idm from YK_YPLCMLK) and tybz=0 )\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品规格对应无效临床名称,请检查数据！','select lc_idm,* from YK_YPGGMLK (nolock) where lc_idm not in (select idm from YK_YPLCMLK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品规格目录库(YK_YPGGMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPGGMLK (nolock) where yplh not in (select id from YY_SFDXMK)and tybz=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品类号无效数据,请检查数据！','select yplh,* from YK_YPGGMLK (nolock) where yplh not in (select id from YY_SFDXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品规格目录库(YK_YPGGMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPGGMLK (nolock) where jxdm not in (select id from YK_YPJXK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品剂型对应无效剂型,请检查数据！','select jxdm,* from YK_YPGGMLK (nolock) where jxdm not in (select id from YK_YPJXK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品规格目录库(YK_YPGGMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPGGMLK (nolock) where ggdw not in (select name from YY_UNIT))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品规格单位对应无效单位,请检查数据！','select ggdw,* from YK_YPGGMLK (nolock) where ggdw not in (select id from YY_UNIT)'\n" +
            "end\n" +
            "\n" +
            "--检测药品规格目录库(YK_YPGGMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPGGMLK (nolock) where zxdw not in (select name from YY_UNIT))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品最小单位对应无效单位,请检查数据！','select zxdw,* from YK_YPGGMLK (nolock) where zxdw not in (select id from YY_UNIT)'\n" +
            "end\n" +
            "\n" +
            "--检测药品规格目录库(YK_YPGGMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPGGMLK (nolock) where fldm not in (select id from YK_YPFLK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品分类对应无效分类,请检查数据！','select fldm,* from YK_YPGGMLK (nolock) where fldm not in (select id from YK_YPFLK)'\n" +
            "end\n" +
            "\n" +
            "--检测厂家代码(YK_CJDMK)\n" +
            "if not exists(select 1 from YK_CJDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在厂家代码,请检查数据！','select * from YK_CJDMK'\n" +
            "end\n" +
            "\n" +
            "--检测供货单位(YK_GHDWDMK)\n" +
            "if not exists(select 1 from YK_GHDWDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在供货单位,请检查数据！','select * from YK_GHDWDMK'\n" +
            "end\n" +
            "\n" +
            "--检测药库代码库(YK_YKDMK)\n" +
            "if not exists(select 1 from YK_YKDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药库代码,请检查数据！','select * from YK_YKDMK'\n" +
            "end\n" +
            "\n" +
            "--检测药库代码库(YK_YKDMK)逻辑错误\n" +
            "if exists(select 1 from YK_YKDMK (nolock) where id not in (select id from YY_KSBMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药库代码不是本院科室,请检查数据！','select id,* from YK_YKDMK (nolock) where id not in (select id from YY_KSBMK)'\n" +
            "end\n" +
            "\n" +
            "--检测药房代码库(YF_YFDMK)\n" +
            "if not exists(select 1 from YF_YFDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药房代码库,请检查数据！','select * from YF_YFDMK'\n" +
            "end\n" +
            "\n" +
            "--检测药房代码库(YF_YFDMK)逻辑错误\n" +
            "if exists(select 1 from YF_YFDMK (nolock) where id not in (select id from YY_KSBMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药房代码不是本院科室,请检查数据！','select id,* from YF_YFDMK (nolock) where id not in (select id from YY_KSBMK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品操作单位设置(YK_YPDWDMK)\n" +
            "if not exists(select 1 from YK_YPDWDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药库药品操作单位设置,请检查数据！','select * from YK_YPDWDMK'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)\n" +
            "if not exists(select 1 from YK_YPCDMLK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品产地目录库,请检查数据！','select * from YK_YPCDMLK'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where gg_idm not in (select idm from YK_YPGGMLK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品对应无效规格名称,请检查数据！','select gg_idm,* from YK_YPCDMLK (nolock) where gg_idm not in (select idm from YK_YPGGMLK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where lc_idm not in (select idm from YK_YPLCMLK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品对应无效临床名称,请检查数据！','select lc_idm,* from YK_YPCDMLK (nolock) where lc_idm not in (select idm from YK_YPLCMLK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where yplh not in (select id from YY_SFDXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品类号无效数据,请检查数据！','select yplh,* from YK_YPCDMLK (nolock) where yplh not in (select id from YY_SFDXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where jxdm not in (select id from YK_YPJXK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品剂型对应无效剂型,请检查数据！','select jxdm,* from YK_YPCDMLK (nolock) where jxdm not in (select id from YK_YPJXK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where ggdw not in (select name from YY_UNIT))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品规格单位对应无效单位,请检查数据！','select ggdw,* from YK_YPCDMLK (nolock) where ggdw not in (select name from YY_UNIT)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where zxdw not in (select name from YY_UNIT))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品最小单位对应无效单位,请检查数据！','select zxdw,* from YK_YPCDMLK (nolock) where zxdw not in (select name from YY_UNIT)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where fldm not in (select id from YK_YPFLK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品分类对应无效分类,请检查数据！','select fldm,* from YK_YPCDMLK (nolock) where fldm not in (select id from YK_YPFLK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where cjdm not in (select id from YK_CJDMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品产地对应无效产地,请检查数据！','select cjdm,* from YK_YPCDMLK (nolock) where cjdm not in (select id from YK_CJDMK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where ykdw not in (select name  from YY_UNIT))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品药库单位对应无效单位,请检查数据！','select ykdw,* from YK_YPCDMLK (nolock) where ykdw not in (select name from YY_UNIT))'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where mzdw not in (select name from YY_UNIT))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品门诊单位对应无效单位,请检查数据！','select mzdw,* from YK_YPCDMLK (nolock) where mzdw not in (select name from YY_UNIT)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where zydw not in (select name from YY_UNIT))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品住院单位对应无效单位,请检查数据！','select zydw,* from YK_YPCDMLK (nolock) where zydw not in (select name from YY_UNIT)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where zmlb not in (select id from YK_ZMLBK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药品账目类别对应无效账目类别,请检查数据！','select zmlb,* from YK_YPCDMLK (nolock) where zmlb not in (select id from YK_ZMLBK)'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where ykdw=mzdw and ykxs<>mzxs)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药库单位与门诊单位一致但系数不一致,请检查数据！','select ykdw,mzdw,ykxs,mzxs,* from YK_YPCDMLK (nolock) where ykdw=mzdw and ykxs<>mzxs'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where ykdw=zydw and ykxs<>zyxs)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在药库单位与住院单位一致但系数不一致,请检查数据！','select ykdw,zydw,ykxs,zyxs,* from YK_YPCDMLK (nolock) where ykdw=zydw and ykxs<>zyxs'\n" +
            "end\n" +
            "\n" +
            "--检测药品产地目录库(YK_YPCDMLK)逻辑错误\n" +
            "if exists(select 1 from YK_YPCDMLK (nolock) where mzdw=zydw and mzxs<>zyxs)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在门诊单位与住院单位一致但系数不一致,请检查数据！','select mzdw,zydw,mzxs,zyxs,* from YK_YPCDMLK (nolock) where mzdw=zydw and mzxs<>zyxs'\n" +
            "end\n" +
            "\n" +
            "---////住院相关////\n" +
            "--检测入院方式(ZYB_RYFSK)\n" +
            "if not exists(select 1 from ZYB_RYFSK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在入库方式,请检查数据！','select * from ZYB_RYFSK'\n" +
            "end\n" +
            "\n" +
            "--检测出院方式(ZYB_CYFSK)\n" +
            "if not exists(select 1 from ZYB_CYFSK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在出库方式,请检查数据！','select * from ZYB_CYFSK'\n" +
            "end\n" +
            "\n" +
            "--检测支付方式(YY_ZFFSK)\n" +
            "if not exists(select 1 from YY_ZFFSK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在支付方式,请检查数据！','select * from YY_ZFFSK'\n" +
            "end\n" +
            "\n" +
            "--检测药品用法(ZY_YPYFK)\n" +
            "if not exists(select 1 from ZY_YPYFK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在药品用法,请检查数据！','select * from ZY_YPYFK'\n" +
            "end\n" +
            "\n" +
            "--检测医嘱单据分类(BQ_YZDJFLK)\n" +
            "if not exists(select 1 from BQ_YZDJFLK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在医嘱单据分类,请检查数据！','select * from BQ_YZDJFLK'\n" +
            "end\n" +
            "\n" +
            "--检测医嘱单据库(BQ_YZDJK)\n" +
            "if not exists(select 1 from BQ_YZDJK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在医嘱单据库,请检查数据！','select * from BQ_YZDJK'\n" +
            "end\n" +
            "\n" +
            "--检测医嘱频次(ZY_YZPCK)\n" +
            "if not exists(select 1 from ZY_YZPCK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在医嘱频次,请检查数据！','select * from ZY_YZPCK'\n" +
            "end\n" +
            "\n" +
            "--检测住院固定执行项目设置(ZY_GDXMK)\n" +
            "if not exists(select 1 from ZY_GDXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在住院固定执行项目设置,请检查数据！','select * from ZY_GDXMK'\n" +
            "end\n" +
            "\n" +
            "--检测病床代码库(ZY_BCDMK)\n" +
            "if not exists(select 1 from ZY_BCDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在病床代码库,请检查数据！','select * from ZY_BCDMK'\n" +
            "end\n" +
            "\n" +
            "--检测病床代码库(ZY_BCDMK)逻辑错误\n" +
            "if exists(select 1 from ZY_BCDMK (nolock) where bqdm not in (select id from ZY_BQDMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在病床所在病区对应无效病区,请检查数据！','select bqdm,* from ZY_BCDMK (nolock) where bqdm not in (select id from ZY_BQDMK)'\n" +
            "end\n" +
            "\n" +
            "--检测病床代码库(ZY_BCDMK)逻辑错误\n" +
            "if exists(select 1 from ZY_BCDMK (nolock) where ksdm not in (select id from YY_KSBMK) and  jlzt =0 )\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在病床所在科室对应无效科室,请检查数据！','select ksdm,* from ZY_BCDMK (nolock) where ksdm not in (select id from YY_KSBMK)'\n" +
            "end\n" +
            "\n" +
            "--检测病床代码库(ZY_BCDMK)逻辑错误\n" +
            "if exists(select 1 from ZY_BCDMK (nolock) where cwfdm not in (select id from YY_SFXXMK)and  jlzt=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在病床床位费对应无效项目代码,请检查数据！','select cwfdm,* from ZY_BCDMK (nolock) where cwfdm not in (select id from YY_SFXXMK)'\n" +
            "end\n" +
            "\n" +
            "----////手术相关////\n" +
            "--if @bz in (1,2)\n" +
            "--begin\n" +
            "----检测手术等级代码(SS_SSDJDMK)\n" +
            "--if not exists(select 1 from SS_SSDJDMK (nolock))\n" +
            "--begin\n" +
            "--\tinsert into temp_result\n" +
            "--\tselect 'F','系统不存在手术等级代码,请检查数据！','select * from SS_SSDJDMK'\n" +
            "--end\n" +
            "\n" +
            "--检测手术切口等级(SS_QKDJK)\n" +
            "if not exists(select 1 from SS_QKDJK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在手术切口等级,请检查数据！','select * from SS_QKDJK'\n" +
            "end\n" +
            "\n" +
            "--检测手术体位情况(SS_SSTWQKK)\n" +
            "if not exists(select 1 from SS_SSTWQKK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在手术体位情况,请检查数据！','select * from SS_SSTWQKK'\n" +
            "end\n" +
            "\n" +
            "--检测手术室代码库(SS_SSDMK)\n" +
            "if not exists(select 1 from SS_SSDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在手术室代码库,请检查数据！','select * from SS_SSDMK'\n" +
            "end\n" +
            "\n" +
            "--检测手术室代码库(SS_SSDMK)逻辑错误\n" +
            "if exists(select 1 from SS_SSDMK (nolock) where id not in (select id from YY_KSBMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在手术科室对应无效科室,请检查数据！','select id,* from SS_SSDMK (nolock) where id not in (select id from YY_KSBMK)'\n" +
            "end\n" +
            "\n" +
            "--检测手术房间(SS_SSFJK)\n" +
            "if not exists(select 1 from SS_SSFJK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在手术房间,请检查数据！','select * from SS_SSFJK'\n" +
            "end\n" +
            "\n" +
            "\n" +
            "--检测麻醉室代码库(SS_MZDMK)\n" +
            "if not exists(select 1 from SS_MZDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在麻醉室代码库,请检查数据！','select * from SS_MZDMK'\n" +
            "end\n" +
            "\n" +
            "--检测手术麻醉对应设置(SS_SSMZDYK)\n" +
            "if not exists(select 1 from SS_SSMZDYK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在手术麻醉对应设置,请检查数据！','select * from SS_SSMZDYK'\n" +
            "end\n" +
            "\n" +
            "--////财务相关////\n" +
            "--检测住院发票项目(YY_ZYFPXMK)\n" +
            "if not exists(select 1 from YY_ZYFPXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在住院发票项目,请检查数据！','select * from YY_ZYFPXMK'\n" +
            "end\n" +
            "\n" +
            "--检测门诊发票项目(YY_MZFPXMK)\n" +
            "if not exists(select 1 from YY_MZFPXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在门诊发票项目,请检查数据！','select * from YY_MZFPXMK'\n" +
            "end\n" +
            "\n" +
            "--检测住院会计项目(YY_ZYKJXMK)\n" +
            "if not exists(select 1 from YY_ZYFPXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在住院会计项目,请检查数据！','select * from YY_ZYFPXMK'\n" +
            "end\n" +
            "\n" +
            "--检测门诊会计项目(YY_MZKJXMK)\n" +
            "if not exists(select 1 from YY_MZFPXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在门诊会计项目,请检查数据！','select * from YY_MZFPXMK'\n" +
            "end\n" +
            "\n" +
            "--检测核算项目(YY_HSXMK)\n" +
            "if not exists(select 1 from YY_HSXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在核算项目,请检查数据！','select * from YY_HSXMK'\n" +
            "end\n" +
            "\n" +
            "--检测收费大项目(YY_SFDXMK)\n" +
            "if not exists(select 1 from YY_SFDXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在收费大项目,请检查数据！','select * from YY_SFDXMK'\n" +
            "end\n" +
            "\n" +
            "--检测收费大项目(YY_SFDXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFDXMK (nolock) where mzfp_id not in (select id from YY_MZFPXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在收费大项目无对应门诊发票项目,请检查数据！','select mzfp_id,* from YY_SFDXMK (nolock) where mzfp_id not in (select id from YY_MZFPXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测收费大项目(YY_SFDXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFDXMK (nolock) where zyfp_id not in (select id from YY_ZYFPXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在收费大项目无对应住院发票项目,请检查数据！','select zyfp_id,* from YY_SFDXMK (nolock) where zyfp_id not in (select id from YY_ZYFPXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测收费大项目(YY_SFDXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFDXMK (nolock) where mzkj_id not in (select id from YY_MZKJXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在收费大项目无对应门诊会计项目,请检查数据！','select mzkj_id,* from YY_SFDXMK (nolock) where mzkj_id not in (select id from YY_MZKJXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测收费大项目(YY_SFDXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFDXMK (nolock) where zykj_id not in (select id from YY_ZYKJXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在收费大项目无对应住院会计项目,请检查数据！','select zykj_id,* from YY_SFDXMK (nolock) where zykj_id not in (select id from YY_ZYKJXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测收费大项目(YY_SFDXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFDXMK (nolock) where hsxm_id not in (select id from YY_HSXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在收费大项目无对应核算项目,请检查数据！','select hsxm_id,* from YY_SFDXMK (nolock) where hsxm_id not in (select id from YY_HSXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测收费小项目库(YY_SFXXMK)\n" +
            "if not exists(select 1 from YY_SFXXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在收费小项目库,请检查数据！','select * from YY_SFXXMK'\n" +
            "end\n" +
            "\n" +
            "--检测收费小项目(YY_SFXXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFXXMK (nolock) where dxmdm not in (select id from YY_SFDXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在收费小项目无对应大项目,请检查数据！','select dxmdm,* from YY_SFXXMK (nolock) where dxmdm not in (select id from YY_SFDXMK)'\n" +
            "end\n" +
            "\n" +
            "--检测收费小项目(YY_SFXXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFXXMK (nolock) where yjbz=1 and yjqrbz<>1)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在医技项目无确认标志,请检查数据！','select * from YY_SFXXMK (nolock) where yjbz=1 and yjqrbz<>1'\n" +
            "end\n" +
            "\n" +
            "--检测收费小项目(YY_SFXXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFXXMK (nolock) where yjbz=1 and yjqrbz=1 and isnull(zxks_id,'')='')\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在医技项目无执行科室,请检查数据！','select zxks_id,* from YY_SFXXMK (nolock) where yjbz=1 and yjqrbz=1 and isnull(zxks_id,'')='''\n" +
            "end\n" +
            "\n" +
            "--检测收费小项目(YY_SFXXMK)逻辑错误\n" +
            "if exists(select 1 from YY_SFXXMK (nolock) where isnull(zxks_id,'')<>'' and zxks_id not in (select id from YY_KSBMK where jlzt=0))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在执行科室对应无效科室,请检查数据！','select zxks_id,* from YY_SFXXMK (nolock) where isnull(zxks_id,'')<>'' and zxks_id not in (select id from YY_KSBMK where jlzt=0)'\n" +
            "end\n" +
            "\n" +
            "---////医保相关////\n" +
            "--检测医保类型库(YY_YBFLK)\n" +
            "if not exists(select 1 from YY_YBFLK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在医保类型库,请检查数据！','select * from YY_YBFLK'\n" +
            "end\n" +
            "\n" +
            "--检测磁卡费别对应设置(YY_CKFBDYK)\n" +
            "if not exists(select 1 from YY_CKFBDYK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在磁卡费别对应设置,请检查数据！','select * from YY_CKFBDYK'\n" +
            "end\n" +
            "\n" +
            "--检测收费代码设置(SF_YFDYK)\n" +
            "if not exists(select 1 from SF_YFDYK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在收费代码设置,请检查数据！','select * from SF_YFDYK'\n" +
            "end\n" +
            "\n" +
            "--检测收费窗口代码设置(SF_SFCKDMK)\n" +
            "if not exists(select 1 from SF_SFCKDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在收费窗口代码设置,请检查数据！','select * from SF_SFCKDMK'\n" +
            "end\n" +
            "\n" +
            "--检测配药窗口设置(YF_PYCKDMK)\n" +
            "if not exists(select 1 from YF_PYCKDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在配药窗口设置,请检查数据！','select * from YF_PYCKDMK'\n" +
            "end\n" +
            "\n" +
            "--检测发药窗口代码设置(YF_FYCKDMK)\n" +
            "if not exists(select 1 from YF_FYCKDMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在发药窗口代码设置,请检查数据！','select * from YF_FYCKDMK'\n" +
            "end\n" +
            "\n" +
            "--检测收发配窗口设置(SF_SFPDYK)\n" +
            "if not exists(select 1 from SF_SFPDYK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在收发配窗口设置,请检查数据！','select * from SF_SFPDYK'\n" +
            "end\n" +
            "\n" +
            "\n" +
            "--检测临床收费项目设置(YY_LCSFXMK )\n" +
            "if not exists(select 1 from YY_LCSFXMK (nolock))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统不存在临床收费项目设置,请检查数据！','select * from YY_LCSFXMK '\n" +
            "end\n" +
            "----检测临床项目中无对应的收费小项目\n" +
            "if exists(select 1 from YY_LCSFXMDYK (nolock) where xmdm not in (select id from YY_SFXXMK))\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在临床项目对应无效项目代码,请检查数据！','select xmdm,* from YY_LCSFXMDYK (nolock) where xmdm not in (select id from YY_SFXXMK)'\n" +
            "end\n" +
            "\n" +
            "----校验药品系数逻辑（MZ、ZY>ykxs)\n" +
            "if exists(select 1 from  YK_YPCDMLK where mzxs>ykxs or zyxs>ykxs and tybz=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在mzxs、zyxs>ykxs 的药品,请检查数据！','select * from  YK_YPCDMLK where mzxs>ykxs or zyxs>ykxs and tybz=0'\n" +
            "end\n" +
            "----校验药品系数逻辑（mzxs=zyxs时，单位应相同)\n" +
            "if exists(select 1 from  YK_YPCDMLK where  (mzxs=zyxs and mzdw<>zydw)  and tybz=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在mzxs=zyxs时，单位不相同的药品,请检查数据！','select * from  YK_YPCDMLK where  (mzxs=zyxs and mzdw<>zydw)  and tybz=0'\n" +
            "end\n" +
            "----校验药品系数逻辑（进价>零售价)\n" +
            "if exists(select 1 from  YK_YPCDMLK where  mrjj>ylsj and tybz=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在进价>零售价的药品,请检查数据！','select * from  YK_YPCDMLK where  mrjj>ylsj  and tybz=0)'\n" +
            "end\n" +
            "\n" +
            "----4校验药品逻辑错误(系数不同，单位相同）\n" +
            "if exists(select 1 from  YK_YPCDMLK where  mzxs<>ykxs and zyxs<>ykxs  and (mzdw=ykdw or zydw=ykdw )  and tybz=0 )\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','系统存在系数不同，单位相同的药品,请检查数据！','select * from  YK_YPCDMLK where  mzxs<>ykxs and zyxs<>ykxs  and (mzdw=ykdw or zydw=ykdw )  and tybz=0'\n" +
            "end\n" +
            "\n" +
            "----5校验药品逻辑错误(累计取药时，住院单位和系数同药库单位和系数）\n" +
            "if exists(select 1 from  YK_YPCDMLK where ljlybz=2 and zyxs<>ykxs  and tybz=0)\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','存在累计取药的药品，其住院单位和系数应同药库单位和系数,请检查数据！','select * from  YK_YPCDMLK where ljlybz=2 and zyxs<>ykxs  and tybz=0'\n" +
            "end\n" +
            "\n" +
            "----5.1校验住院累计取药参数的逻辑错误(累计取药时，config6039应>10）\n" +
            "declare @ljlybz int\n" +
            "select @ljlybz=count(1) from YK_YPCDMLK where ljlybz=2 and zyxs<>ykxs  and tybz=0\n" +
            "if  @ljlybz>0 and exists(select 1 from YY_CONFIG where id='6039' and config='3')\n" +
            "begin\n" +
            "\tinsert into temp_result\n" +
            "\tselect 'F','存在累计取药的药品，但可调参数6039设置不对,建议设置为99！','select * from YY_CONFIG where id=‘6039’'\n" +
            "end\n" +
            "\n" +
            "\n" +
            "if not exists(select * from temp_result)\n" +
            "begin \n" +
            "\tselect 'T','系统数据完整,达到上线要求！'\n" +
            "\treturn\n" +
            "end \n" +
            "\n" +
            "select * from temp_result\n" +
            "\n" +
            "\n" +
            "\n";
    private static String RUN_PROC_SQL = "exec usp_yy_jcsj_his";

    private static String GET_PROC_RESULT_SQL = "select * from temp_result";
}
