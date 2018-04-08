package cn.com.winning.ssgj.service;

import cn.com.winning.ssgj.domain.EtProjectImplPath;

import java.util.List;

public interface  EtProjectImplPathService {

    public Integer createEtProjectImplPath(EtProjectImplPath t);

    public EtProjectImplPath getEtProjectImplPath(EtProjectImplPath t) ;

    public Integer getEtProjectImplPathCount(EtProjectImplPath t);

    public List<EtProjectImplPath> getEtProjectImplPathList(EtProjectImplPath t);

    public int modifyEtProjectImplPath(EtProjectImplPath t);

    public int removeEtProjectImplPath(EtProjectImplPath t);

    public List<EtProjectImplPath> getEtProjectImplPathPaginatedList(EtProjectImplPath t) ;


    public void generateEtProjectImplPathInfo(String path);
}