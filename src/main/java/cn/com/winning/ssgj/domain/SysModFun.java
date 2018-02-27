package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:46
 */

@Alias("sysModFun")
public class SysModFun extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long modId;

    private Long funId;

    public SysModFun() {

    }

    public Long getModId() {
        return modId;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }

    public Long getFunId() {
        return funId;
    }

    public void setFunId(Long funId) {
        this.funId = funId;
    }
}