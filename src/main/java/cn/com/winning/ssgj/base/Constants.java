package cn.com.winning.ssgj.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Constants implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 是否成功
     */
    public static final String SUCCESS = "success";

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
     * 状态码  0 不使用/失效
     */
    public static final Integer STATUS_UNUSE = 0;
    /**
     * 状态码  1 使用/生效
     */
    public static final Integer STATUS_USE = 1;

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
        public static final  String FLOW_TYPE_BIG = "0";
        /**
         * 流程小类
         */
        public static final  String FLOW_TYPE_SMALL = "1";

        /**
         * 一级流程ID
         */
        public static final int FLOW_FALSE_VALUE = -1;


    }

    public class  DataInfo {
        /**
         * 国标数据
         */
        public static final  String DATA_TYPE_GUOBIAO = "0"  ;
        /**
         * 行标数据
         */
        public static final  String DATA_TYPE_HANGBIAO = "1"  ;
        /**
         * 共享数据
         */
        public static final  String DATA_TYPE_GONGXIANG = "2"  ;
        /**
         * 易用数据
         */
        public static final  String DATA_TYPE_YIYONG = "3"  ;


    }
}
