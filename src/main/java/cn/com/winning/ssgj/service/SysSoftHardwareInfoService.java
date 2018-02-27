package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.SysSoftHardwareInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysSoftHardwareInfoService {

    Integer createSysSoftHardwareInfo(SysSoftHardwareInfo t);

    int modifySysSoftHardwareInfo(SysSoftHardwareInfo t);

    int removeSysSoftHardwareInfo(SysSoftHardwareInfo t);

    SysSoftHardwareInfo getSysSoftHardwareInfo(SysSoftHardwareInfo t);

    List<SysSoftHardwareInfo> getSysSoftHardwareInfoList(SysSoftHardwareInfo t);

    Integer getSysSoftHardwareInfoCount(SysSoftHardwareInfo t);

    List<SysSoftHardwareInfo> getSysSoftHardwareInfoPaginatedList(SysSoftHardwareInfo t);


    Integer getSysSoftHardwareInfoCountForSelectiveKey(SysSoftHardwareInfo t);

    List<SysSoftHardwareInfo> getSysSoftHardwareInfoPaginatedListForSelectiveKey(SysSoftHardwareInfo t);

    /**
     * 使用ID字符串查询符合要求的ID
     * @param data
     * @return
     */
    List<SysSoftHardwareInfo> getSysSoftHardwareInfoListByIds(SysSoftHardwareInfo data);

    List<SysSoftHardwareInfo> getSysSoftHardwareInfoListForNames(SysSoftHardwareInfo info);
}