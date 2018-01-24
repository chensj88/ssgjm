package cn.com.winning.ssgj.base.helper;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.com.winning.ssgj.domain.support.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.com.winning.ssgj.base.id.StepSequenceFactory;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 实施工具服务类
 * <p>主要用于获取ID和编码</p>
 *
 */
@Component
public class SSGJHelper {

    @Autowired
    @Qualifier("userIdService")
    private StepSequenceFactory userIdService;
    @Autowired
    @Qualifier("roleIdService")
    private StepSequenceFactory roleIdService;
    @Autowired
    @Qualifier("roleCodeService")
    private StepSequenceFactory roleCodeService;
    @Autowired
    @Qualifier("funcIdService")
    private StepSequenceFactory funcIdService;
    @Autowired
    @Qualifier("funcCodeService")
    private StepSequenceFactory funcCodeService;
    @Autowired
    @Qualifier("meunIdService")
    private StepSequenceFactory meunIdService;
    @Autowired
    @Qualifier("menuCodeService")
    private StepSequenceFactory menuCodeService;
    @Autowired
    @Qualifier("serialIdService")
    private StepSequenceFactory serialIdService;
    @Autowired
    @Qualifier("serialCodeService")
    private StepSequenceFactory serialCodeService;
    @Autowired
    @Qualifier("logIdService")
    private StepSequenceFactory logIdService;

    /**
     * 获取用户ID信息
     *
     * @return userId
     */
    public long createUserId() {
        return (long) userIdService.create();
    }

    /**
     * 获取角色ID信息
     *
     * @return roleId
     */
    public long createRoleId() {
        return (long) roleIdService.create();
    }

    /**
     * 获取角色代码
     * @return roleCode
     */
    public String createRoleCode(){
        return  roleCodeService.create().toString();
    }

    /**
     * 获取功能ID信息
     *
     * @return funcId
     */
    public long createFuncId() {
        return (long) funcIdService.create();
    }

    /**
     * 获取管功能代码
     * @return FuncCode
     */
    public String createFuncCode() {
        return funcCodeService.create().toString();
    }

    /**
     * 获取菜单ID信息
     *
     * @return MenuId
     */
    public long createMenuId() {
        return (long) meunIdService.create();
    }

    /**
     * 获取管菜单代码
     * @return MenuCode
     */
    public String createMenuCode() {
        return menuCodeService.create().toString();
    }

    /**
     * 获取单据号
     * @return serialId
     */
    public long createSerialId(){
        return (long) serialIdService.create();
    }
    /**
     * 获取单据号
     * @return serialCode
     */
    public String createSerialCode(){
        return serialCodeService.create().toString();
    }

    /**
     * 获取日志ID
     * @return
     */
    public long createLogId(){
        return (long) logIdService.create();
    }
    /**
     * 密码加密
     * @param password 明文密码
     * @param length   密码长度
     * @return MD5 加密后的密码
     * @throws NoSuchAlgorithmException     没有上述加密的算法
     * @throws UnsupportedEncodingException 不支持上述解码的编码集
     * @deprecated
     */
    public static String encodePassword(String password, int length) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = null;
        String md5 = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes("UTF-8"));
            md5 = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            throw e;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e;
        }
        return fillMD5(md5, length);

    }

    private static String fillMD5(String md5, int length) {
        return md5.length() == length ? md5 : fillMD5("0" + md5, length);
    }

    /**
     * 将文字转为汉语拼音
     * @deprecated
     */
    public static String toLoginName(String chineseLanguage) {
        char[] cl_chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }


    /**
     * 创建分页需要的类
     * @param pageNo bootstrap使用的页数
     * @param rows   每页的数据量
     * @return
     */
    public static Row createPageRowInfo(int pageNo,int rows){
        int startNum = (pageNo -1) * rows;
        return  new Row(startNum,rows);
    }

    public static void main(String[] args) {
        try {
            System.out.println(encodePassword("123456", 32));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
