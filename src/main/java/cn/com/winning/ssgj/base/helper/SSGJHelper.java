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
    @Autowired
    @Qualifier("productIdService")
    private StepSequenceFactory productIdService;
    @Autowired
    @Qualifier("productCodeService")
    private StepSequenceFactory productCodeService;
    @Autowired
    @Qualifier("flowIdService")
    private StepSequenceFactory flowIdService;
    @Autowired
    @Qualifier("onlineFileIdService")
    private StepSequenceFactory onlineFileIdService;

    @Autowired
    @Qualifier("videoIdService")
    private StepSequenceFactory videoIdService;

    @Autowired
    @Qualifier("flowCodeService")
    private StepSequenceFactory flowCodeService;

    @Autowired
    @Qualifier("dataIdService")
    private StepSequenceFactory dataIdService;

    @Autowired
    @Qualifier("dataCodeService")
    private StepSequenceFactory dataCodeService;

    @Autowired
    @Qualifier("thirdInterfaceIdService")
    private StepSequenceFactory thirdInterfaceIdService;

    @Autowired
    @Qualifier("thirdInterfaceCodeService")
    private StepSequenceFactory thirdInterfaceCodeService;

    @Autowired
    @Qualifier("shIdService")
    private StepSequenceFactory shIdService;

    @Autowired
    @Qualifier("shCodeService")
    private StepSequenceFactory shCodeService;
    @Autowired
    @Qualifier("sysReportInfoCodeService")
    private StepSequenceFactory sysReportInfoCodeService;
    @Autowired
    @Qualifier("sysReportInfoIdService")
    private StepSequenceFactory sysReportInfoIdService;

    @Autowired
    @Qualifier("sysParamsIdService")
    private StepSequenceFactory sysParamsIdService;

    @Autowired
    @Qualifier("sysFlowQuestionCodeService")
    private StepSequenceFactory sysFlowQuestionCodeService;
    @Autowired
    @Qualifier("sysFlowQuestionIdService")
    private StepSequenceFactory sysFlowQuestionIdService;

    @Autowired
    @Qualifier("sysFlowAnswerCodeService")
    private StepSequenceFactory sysFlowAnswerCodeService;
    @Autowired
    @Qualifier("sysFlowAnswerIdService")
    private StepSequenceFactory sysFlowAnswerIdService;

    @Autowired
    @Qualifier("sysRoleUserIdService")
    private StepSequenceFactory sysRoleUserIdService;

    @Autowired
    @Qualifier("sysModIdService")
    private StepSequenceFactory sysModIdService;

    @Autowired
    @Qualifier("sysRoleModIdService")
    private StepSequenceFactory sysRoleModIdService;
    @Autowired
    @Qualifier("sysTrainVideoRepoIdService")
    private StepSequenceFactory sysTrainVideoRepoIdService;

    @Autowired
    @Qualifier(value = "dataCheckScriptIdService")
    private StepSequenceFactory  dataCheckScriptIdService;

    @Autowired
    @Qualifier(value = "siteQuestionIdService")
    private StepSequenceFactory  siteQuestionIdService;

    @Autowired
    @Qualifier(value = "floorQuestionIdService")
    private StepSequenceFactory  floorQuestionIdService;
    @Autowired
    @Qualifier(value = "etOnlineInfoIdService")
    private StepSequenceFactory  etOnlineInfoIdService;
    @Autowired
    @Qualifier(value = "etUserInfoIdService")
    private StepSequenceFactory  etUserInfoIdService;
    
    public long createEtOnlineInfoIdService(){
        return (long) etOnlineInfoIdService.create();
    }

    public long createEtUserInfoIdService(){
        return (long) etUserInfoIdService.create();
    }
    
    public long createDataCheckScriptIdService(){
        return (long) dataCheckScriptIdService.create();
    }

    public long createVideoIdService(){
        return (long) videoIdService.create();
    }
    public long createSysTrainVideoRepoId(){
        return (long) sysTrainVideoRepoIdService.create();
    }
    public long createSysRoleModId(){
        return (long) sysRoleModIdService.create();
    }
    public long createSysModId(){
        return (long) sysModIdService.create();
    }
    public long createRoleUserId() {
        return (long) sysRoleUserIdService.create();
    }
    public long createOnlineFileIdService(){ return (long) onlineFileIdService.create(); }
    public long createSiteQuestionIdService(){ return (long) siteQuestionIdService.create(); }
    public long createFloorQuestionIdService(){ return (long) floorQuestionIdService.create(); }



    /**
     * 获取报表类信息表Id
     *
     * @return userId
     */
    public long createReportInfoId() {
        return (long) sysReportInfoIdService.create();
    }
    /**
     * 获取报表类信息表Code
     *
     * @return userId
     */
    public String createReportInfoCode() {
        return  sysReportInfoCodeService.create().toString();
    }
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
     * 产品ID
     * @return
     */
    public long createPuductId(){
    	return (long) productIdService.create();
    }
    /**
     * 产品CODE
     * @return
     */
    public String createPuductCode(){
    	return  productCodeService.create().toString();
    }

    /**
     * 业务流程编号
     * 流程大类
     * @return
     */
    public String createFlowCode(){
        return flowCodeService.create().toString();
    }

    /**
     * 业务流程ID
     * @return
     */
    public long createFlowId(){
        return (long) flowIdService.create();
    }

    /**
     * 基础数据编码
     * @return
     */
    public String createDataCode(){
        return dataCodeService.create().toString();
    }

    /**
     * 基础数据ID
     * @return
     */
    public long createDataId(){
        return (long) dataIdService.create();
    }

    /**
     * 第三方接口编码
     * @return
     */
    public String createThirdInterfaceDataCode(){
        return thirdInterfaceCodeService.create().toString();
    }

    /**
     * 第三方接口ID
     * @return
     */
    public long createThirdInterfaceId(){
        return (long) thirdInterfaceIdService.create();
    }

    /**
     * 软硬件信息编码
     * @return
     */
    public String createSoftwareHardwareCode(){
        return shCodeService.create().toString();
    }

    /**
     * 软硬件信息ID
     * @return
     */
    public long createSoftwareHardwareId(){
        return (long) shIdService.create();
    }

    /**
     * 参数表主键
     * @return
     */
    public long createSysParamsId(){ return (long) sysParamsIdService.create();}

    /**
     * 流程问题编码
     * @return
     */
    public String createSysFlowQuestionCode(){
        return sysFlowQuestionCodeService.create().toString();
    }

    /**
     * 流程问题ID
     * @return
     */
    public Long createSysFlowQuestionId(){
        return (Long) sysFlowQuestionIdService.create();
    }

    /**
     * 流程问题答案编码
     * @return
     */
    public String createSysFlowAnswerCode(){
        return sysFlowAnswerCodeService.create().toString();
    }



    /**
     * 流程问题答案ID
     * @return
     */
    public Long createSysFlowAnswerId(){
        return (Long) sysFlowAnswerIdService.create();
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
