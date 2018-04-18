package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtOnlineFile;
import cn.com.winning.ssgj.domain.support.UrlContent;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtOnlineFileService {

    Integer createEtOnlineFile(EtOnlineFile t);

    int modifyEtOnlineFile(EtOnlineFile t);

    int removeEtOnlineFile(EtOnlineFile t);

    EtOnlineFile getEtOnlineFile(EtOnlineFile t);

    List<EtOnlineFile> getEtOnlineFileList(EtOnlineFile t);

    Integer getEtOnlineFileCount(EtOnlineFile t);

    List<EtOnlineFile> getEtOnlineFilePaginatedList(EtOnlineFile t);

    List<UrlContent> getUrlContentFromEtOnlineFileList(EtOnlineFile t);
}