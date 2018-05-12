package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.support.UrlContent;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtOnlineFileDao;
import cn.com.winning.ssgj.domain.EtOnlineFile;
import cn.com.winning.ssgj.service.EtOnlineFileService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtOnlineFileServiceImpl implements EtOnlineFileService {

    @Resource
    private EtOnlineFileDao etOnlineFileDao;



    public Integer createEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.insertEntity(t);
    }


    public EtOnlineFile getEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.selectEntity(t);
    }


    public Integer getEtOnlineFileCount(EtOnlineFile t) {
        return (Integer) this.etOnlineFileDao.selectEntityCount(t);
    }


    public List<EtOnlineFile> getEtOnlineFileList(EtOnlineFile t) {
        return this.etOnlineFileDao.selectEntityList(t);
    }


    public int modifyEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.updateEntity(t);
    }


    public int removeEtOnlineFile(EtOnlineFile t) {
        return this.etOnlineFileDao.deleteEntity(t);
    }


    public List<EtOnlineFile> getEtOnlineFilePaginatedList(EtOnlineFile t) {
        return this.etOnlineFileDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<UrlContent> getUrlContentFromEtOnlineFileList(EtOnlineFile t) {
        List<EtOnlineFile> onlineFileList = this.etOnlineFileDao.selectEntityList(t);
        List<UrlContent> contentList = new ArrayList<>();
        for (EtOnlineFile file : onlineFileList) {
            if(!"".equals(file.getImgPath())){
                UrlContent content = new UrlContent();
                content.setId(file.getId());
                content.setSourceId(file.getId());
                String imgPath = file.getImgPath();
                content.setName(imgPath.substring(imgPath.lastIndexOf("/")+1));
                content.setUrl(Constants.FTP_SHARE_FLODER + imgPath);
                content.setOperDate(file.getMap().get("createDate").toString());
                content.setUserName(file.getMap().get("userName").toString());
                contentList.add(content);
            }
        }
        return contentList;
    }

}
