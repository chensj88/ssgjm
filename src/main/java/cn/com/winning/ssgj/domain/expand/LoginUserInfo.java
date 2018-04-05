package cn.com.winning.ssgj.domain.expand;

import cn.com.winning.ssgj.domain.SysUserInfo;
/**  
 * @Title: LoginUserInfo.java
 * @Package cn.com.winning.ssgj.domain.expand
 * @Description: 用户信息扩展类，添加项目和合同ID
 * @author chenshijie
 * @date 2018/1/19 15:20
 */
public class LoginUserInfo extends SysUserInfo {

    private Long cId;

    private Long pmId;

    public Long getcId() {
        return cId;
    }
    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Long getPmId() {
        return pmId;
    }

    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

}
