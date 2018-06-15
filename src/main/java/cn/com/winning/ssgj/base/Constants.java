package cn.com.winning.ssgj.base;

import cn.com.winning.ssgj.base.util.FtpPropertiesLoader;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Constants implements Serializable {

    private static final long serialVersionUID = -1L;

    public static int FTP_PORT = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();
    public static String FTP_SERVER = FtpPropertiesLoader.getProperty("ftp.server");
    public static String FTP_SHARE_SERVER = FtpPropertiesLoader.getProperty("ftp.share");
    public static String FTP_SHARE_FLODER = "http://" + FTP_SHARE_SERVER + ":8081/shareFolder";
    /**
     * FTP上传文件前缀  PC端
     */
    public static String UPLOAD_PC_PREFIX = "/upload/";
    /**
     * FTP上传文件前缀 手机端
     */
    public static String UPLOAD_MOBILE_PREFIX = "/mupload/";

    /**
     * 是否成功
     */
    public static final String SUCCESS = "success";
    public static final String FAILD = "faild";

    /**
     * 定义用户类型
     */
    public static Map<String, String> USER_TYPE_MAP = new HashMap<String, String>();

    static {
        USER_TYPE_MAP.put("0", "医院/客户");
        USER_TYPE_MAP.put("1", "公司员工");
    }

    /**
     * 性别
     */
    public static final String USER_SEX = "sex";

    /**
     * 系统编码
     */
    public static final String SYS_ENCODING = "UTF-8";

    /**
     * 用户信息
     */
    public static final String USER_INFO = "userInfo";
    /**
     * 状态码  0 不使用/失效 否
     */
    public static final Integer STATUS_UNUSE = 0;
    /**
     * 状态码  1 使用/生效 是
     */
    public static final Integer STATUS_USE = 1;
    /**
     * 审批状态码  开始/新增
     */
    public static final Integer APPROVAL_STATUS_START = 0;
    /**
     * 审批状态码  待审核/提交
     */
    public static final Integer APPROVAL_STATUS_SUMMIT = 1;
    /**
     * 审批状态码  审核不通过
     */
    public static final Integer APPROVAL_STATUS_REJECT = 2;

    /**
     * 审批状态码  审核（完结）
     */
    public static final Integer APPROVAL_STATUS_END = 9;
    /**
     * PMIS状态码  2|作废
     */
    public static final Integer PMIS_STATUS_UNUSE = 2;
    /**
     * PMIS状态码  1|生效;
     */
    public static final Integer PMIS_STATUS_USE = 1;
    /**
     * 视频类型 用户自定义
     */
    public static final String VIDEO_TYPE_OF_CUSTOMER = "99";

    /**
     * 系统配置
     */
    public static final String SYS_CONFIG = "sysConfig";

    /**
     * 存放图片路径
     */
    public static final String IMAGE_PATH = "files/winwing/image/";

    /**
     * 存放附件路径
     */
    public static final String FILE_PATH = "files/winwing/file/";

    /**
     * 文件服务器路径
     */
    public static final String FILE_SERVER_CONTEXT = "fctx";
    /**
     * 流程调研
     */
    public static final String REPORT_TYPE_BUSINESS_PROCESS = "1";
    /**
     * 报表单据
     */
    public static final String REPORT_TYPE_REPORT_AND_BILL = "2";
    /**
     * 上线评估和上线联调
     */
    public static final String REPORT_TYPE_ONLINE_FILE = "3";
    /**
     * 上线切换 图片
     */
    public static final String REPORT_TYPE_ONLINE_SWITCH_IMG_FILE = "4";
    /**
     * 上线切换报告
     */
    public static final String REPORT_TYPE_ONLINE_SWITCH_FILE = "5";

    public class PMIS {
        /**
         * 计划执行状态  暂停/搁置
         */
        public static final int JHZXZT_STOP = -1;
        /**
         * 计划执行状态 运行
         */
        public static final int JHZXZT_RUNING = 0;
        /**
         * 计划执行状态 完成
         */
        public static final int JHZXZT_FINISH = 1;
        /**
         * 计划执行状态 移交
         */
        public static final int JHZXZT_TRANSFER = 2;
        /**
         * 服务类型 实施项目
         */
        public static final int FWLX_SSXM = 0;
        /**
         * 服务类型 服务合同项目
         */
        public static final int FWLX_FWHTXM = 1;
        /**
         * 服务类型 免费维护项目
         */
        public static final int FWLX_MFWHXM = 2;
        /**
         * 合同产品类别
         */
        public static final int CPLB_0 = 0;

        /**
         * 合同产品类别 标准产品
         */
        public static final int CPLB_1 = 1;
        /**
         * 合同产品类别 非标产品
         */
        public static final int CPLB_2 = 2;
        /**
         * 合同产品类别 标准BD产品
         */
        public static final int CPLB_3 = 3;
        /**
         * 合同产品类别 非BD外购
         */
        public static final int CPLB_4 = 4;
        /**
         * 合同产品类别 服务清单
         */
        public static final int CPLB_5 = 5;
        /**
         * 合同产品类别 常规硬件清单
         */
        public static final int CPLB_6 = 6;
        /**
         * 合同产品类别 软件清单
         */
        public static final int CPLB_7 = 7;
        /**
         * 合同产品类别 指定转包硬件清单
         */
        public static final int CPLB_8 = 8;
        /**
         * 合同产品类别 接口/自助机清单
         */
        public static final int CPLB_9 = 9;
        /**
         * 产品类型 软件
         */
        public static final int CPLX_1 = 1;
        /**
         * 产品类型 硬件
         */
        public static final int CPLX_2 = 2;
        /**
         * 产品类型 服务
         */
        public static final int CPLX_3 = 3;
        /**
         * 产品类型 期间服务
         */
        public static final int CPLX_4 = 4;
        /**
         * 产品类型 集成服务
         */
        public static final int CPLX_5 = 5;

    }

    public class User {
        /**
         * 用户状态  正常
         */
        public static final String USER_STATUS_NORMAL = "1";
        /**
         * 用户状态  锁定
         */
        public static final String USER_STATUS_LOCKED = "2";
        /**
         * 用户性别  男
         */
        public static final String USER_SEX_MALE = "0";
        /**
         * 用户性别  女
         */
        public static final String USER_SEX_FEMALE = "1";
        /**
         * 用户类型  公司
         */
        public static final String USER_TYPE_COMPANY = "1";
        /**
         * 用户类型  医院
         */
        public static final String USER_TYPE_HOSPITAL = "0";
        /**
         * 用户类型  管理员
         */
        public static final String USER_TYPE_ADMIN = "2";
        /**
         * 用户类型  公司
         */
        public static final String USER_TYPE_COMPANY_LABEL = "公司";
        /**
         * 用户类型  医院
         */
        public static final String USER_TYPE_HOSPITAL_LABEL = "医院";
        /**
         * 用户类型  管理员
         */
        public static final String USER_TYPE_ADMIN_LABEL = "管理员";
    }

    public class Role {
        /**
         * 角色类型  管理员
         */
        public static final String ROLE_TYPE_ADMIN = "0";
        /**
         * 角色类型 公司
         */
        public static final String ROLE_TYPE_COMPANY = "1";
        /**
         * 角色类型 医院
         */
        public static final String ROLE_TYPE_HOSPITAL = "2";
    }

    public class Flow {
        /**
         * 流程大类
         */
        public static final String FLOW_TYPE_BIG = "0";
        /**
         * 流程小类
         */
        public static final String FLOW_TYPE_SMALL = "1";

        /**
         * 一级流程ID
         */
        public static final int FLOW_FALSE_VALUE = -1;


    }

    public class DataInfo {
        /**
         * 国标数据
         */
        public static final String DATA_TYPE_GUOBIAO = "0";
        /**
         * 行标数据
         */
        public static final String DATA_TYPE_HANGBIAO = "1";
        /**
         * 共享数据
         */
        public static final String DATA_TYPE_GONGXIANG = "2";
        /**
         * 易用数据
         */
        public static final String DATA_TYPE_YIYONG = "3";


    }

    public class PmisWSConstants {
        /**
         * PMIS WS URL
         */
        public static final String WS_URL = "http://weberp.winning.com.cn:9080/service/LBEBusiness?wsdl";
        /**
         * PMIS 工作底稿 WS URL
         */
        public static final String WS_TEST_URL = "http://203.110.176.178:9083/service/LBEBusiness?wsdl";
        /**
         * PMIS WS 用户
         */
        public static final String WS_USER = "sszhb";
        /**
         * PMIS WS 密码
         */
        public static final String WS_PASSWORD = "abc123";
        /**
         * PMIS WS 密码加密方式 plain 不加密
         */
        public static final String WS_ALGORITHM = "plain";
        /**
         * PMIS WS 服务节点名称 数据导入
         */
        public static final String WS_SERVICE_OBJECT_NAME = "WS_DS_CXJCXX";
        /**
         * PMIS WS 服务节点名称 实施底稿登记
         */
        public static final String WORK_WS_SERVICE_OBJECT_NAME = "PM_GZDG_WSXZ";
        /**
         * 查询底稿状态
         */
        public static final String QUERY_WORK_WS_SERVICE_OBJECT_NAME = "WS_DS_CXDGZT";
        /**
         * PMIS WS 服务类型  1 用户
         */
        public static final String WS_SERVICE_QUERY_USER = "1";
        /**
         * PMIS WS 服务类型 客户信息
         */
        public static final String WS_SERVICE_QUERY_CUSTOM = "2";
        /**
         * PMIS WS 服务类型 项目信息
         */
        public static final String WS_SERVICE_QUERY_PROJECT = "3";
        /**
         * PMIS WS 服务类型 项目人员信息
         */
        public static final String WS_SERVICE_QUERY_PUSER = "4";
        /**
         * PMIS WS 服务类型 合同信息
         */
        public static final String WS_SERVICE_QUERY_CONTRACT = "5";
        /**
         * PMIS WS 服务类型  产品信息
         */
        public static final String WS_SERVICE_QUERY_PRODUCT = "6";
        /**
         * PMIS WS 服务类型 合同产品清单
         */
        public static final String WS_SERVICE_QUERY_CONTRACT_PRODUCT = "7";
        /**
         * PMIS WS 服务类型 组织机构
         */
        public static final String WS_SERVICE_QUERY_ORG = "8";

        /**
         * PMIS WS 服务类型 用户登录验证
         */
        public static final String WS_SERVICE_OBJECT_NAME_USER_LOGIN = "PUB_DS_YHXY";
        /**
         * PMIS WS 返回结果码, <=0失败
         */
        public static final int RESULT_CODE_FAIL = 0;

        /**
         * PMIS WS 返回结果码,>0 成功
         */
        public static final int RESULT_CODE_SUCCESS = 1;

        /**
         * PMIS WS 查询类别名称
         */
        public static final String QUERY_TYPE_NAME = "Ptype";
        /**
         * 底稿状态查询
         */
        public static final String QUERY_TYPE_NAME_FOR_REPORT = "code";

        /**
         * PMIS WS 第一次查询的数量
         */
        public static final int QUERY_FIRST_BATCH_SIZE = 1;

        /**
         * PMIS WS 每次查询的数量
         */
        public static final int QUERY_BATCH_SIZE = 1000;
        /**
         * 项目/客户经理
         */
        public static final String USER_ROLE_TYPE_PM = "0";
        /**
         * 项目/客户经理
         */
        public static final String USER_ROLE_TYPE_PM_NAME = "项目/客户经理";
        /**
         * 实施服务人员
         */
        public static final String USER_ROLE_TYPE_SS = "1";
        /**
         * 实施服务人员
         */
        public static final String USER_ROLE_TYPE_SS_NAME = "实施服务人员";
        /**
         * 支持人员
         */
        public static final String USER_ROLE_TYPE_ZC = "2";
        /**
         * 支持人员
         */
        public static final String USER_ROLE_TYPE_ZC_NAME = "支持人员";

    }

    public class Vue {
        public static final String VUE_LOGIN_URL = "/ssgjm/vue/login.do";
        public static final String VUE_LOGOUT_URL = "/ssgjm/vue/logout.do";
        public static final String VUE_TOKEN_NAME = "token";
        public static final String COMMON_EXPORT_TAG = "export";
        public static final String COMMON_DOWNLOAD_TAG = "download";
        public static final String COMMON_UPLOAD_TAG = "upload";
    }

    /**
     * 单据类型
     * 0单据;1 发票;2 报表;
     */
    public class ReportType {
        /**
         * 单据
         */
        public static final int RP_DJ = 0;
        /**
         * 发票
         */
        public static final int RP_FP = 1;
        /**
         * 报表
         */
        public static final int RP_BB = 2;
    }

    public class DictInfo {
        /**
         * 地区信息
         */
        public static final String AREA_CODE = "areaCode";
        /**
         * 审核状态码
         */
        public static final String AUDI_TYPE = "audiType";
        /**
         * 数据类型
         */
        public static final String DATA_TYPE = "dataType";
        /**
         * 难度级别
         */
        public static final String DIFFCULT_LEVEL = "diffcultLevel";
        /**
         * 上传文件类型
         */
        public static final String FILE_TYPE = "FileType";
        /**
         * 流程分类
         */
        public static final String FLOW_TYPE = "flowType";
        /**
         * 医院等级
         */
        public static final String HOSPITAL_LEVEL = "hospitalLevel";
        /**
         * 医院类型
         */
        public static final String HOSPITAL_TYPE = "hospitalType";
        /**
         * 底稿状态
         */
        public static final String MANUSCRIPT_STATUS = "manuscriptStatus";
        /**
         * 底稿类型
         */
        public static final String MANUSCRIPT_TYPE = "manuscriptType";
        /**
         * 状态为否
         */
        public static final String NOT_INSCOPE = "NotInScope";
        /**
         * 解决方式
         */
        public static final String OPE_RTYPE = "operType";
        /**
         * 报表分类
         */
        public static final String PAPER_TYPE = "paperType";
        /**
         * 岗位信息
         */
        public static final String POSITION_NAME = "positionName";
        /**
         * 优先级
         */
        public static final String PRIORITY_TYPE = "priorityType";
        /**
         * 产品字典
         */
        public static final String PRODUCT_NAME = "productName";
        /**
         * PMIS问题类型
         */
        public static final String QUESTION_TYPE = "questionType";
        /**
         * 原因分类
         */
        public static final String REASON_TYPE = "reasonType";
        /**
         * 性别
         */
        public static final String SEX = "sex";
        /**
         * 模拟运行效果
         */
        public static final String SIMULATE_RESULT = "simulateResult";
        /**
         * 用户状态
         */
        public static final String USER_STATUS = "userStatus";
        /**
         * 用户类型
         */
        public static final String USER_TYPE = "userType";
        /**
         * 客户视频类型
         */
        public static final String VIDEO_CTYPE = "videoCType";
        /**
         * 视频类型
         */
        public static final String VIDEO_TYPE = "videoType";

    }

    /**
     * 新建（待分配,实施工程师退回）
     */
    public static final int CREATE_UNALLOCATED = 1;
    /**
     * 已分配（未接受）
     */
    public static final int ALLOCATED_UNACCEPTED = 2;
    /**
     * 接受（未处理）
     */
    public static final int ACCEPTED_UNTREATED = 3;
    /**
     * 已处理（完成）
     */
    public static final int TREATED_COMPLETE = 4;
    /**
     * 院方确认完成（结束）
     */
    public static final int CONFIRM_END = 5;
    /**
     * 院方打回
     */
    public static final int REFUSE = 6;

    /**
     * 工程师打回打回
     */
    public static final int ENGINEER_REFUSE = 7;

}
