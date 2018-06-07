package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.StringUtil;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDictInfoDao;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.service.SysDictInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysDictInfoServiceImpl implements SysDictInfoService {

    @Resource
    private SysDictInfoDao sysDictInfoDao;



    public Integer createSysDictInfo(SysDictInfo t) {
        generateDictSortValue(t);
        return this.sysDictInfoDao.insertEntity(t);
    }



    public SysDictInfo getSysDictInfo(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntity(t);
    }


    public Integer getSysDictInfoCount(SysDictInfo t) {
        return (Integer) this.sysDictInfoDao.selectEntityCount(t);
    }


    public List<SysDictInfo> getSysDictInfoList(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityList(t);
    }


    public int modifySysDictInfo(SysDictInfo t) {
        return this.sysDictInfoDao.updateEntity(t);
    }


    public int removeSysDictInfo(SysDictInfo t) {
        return this.sysDictInfoDao.deleteEntity(t);
    }


    public List<SysDictInfo> getSysDictInfoPaginatedList(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityPaginatedList(t);
    }

    @Override

    public List<SysDictInfo> getSysDictInfoPageForAnd(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityListBySelectiveKeyForAnd(t);
    }

    @Override

    public Integer getSysDictInfoCountForAnd(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityCountBySelectiveKeyForAnd(t);
    }

    @Override

    public List<SysDictInfo> getSysDictInfoPageForOr(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityListBySelectiveKeyForOr(t);
    }

    @Override

    public Integer getSysDictInfoCountForOr(SysDictInfo t) {
        return this.sysDictInfoDao.selectEntityCountBySelectiveKeyForOr(t);
    }

    @Override

    public boolean existDictValue(SysDictInfo dictInfo) {
        Integer count = (Integer) this.sysDictInfoDao.selectEntityCount(dictInfo);
        if (count.intValue() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<SysDictInfo> getSysDictInfoListByType(SysDictInfo dict) {
        return this.sysDictInfoDao.selectSysDictInfoListByType(dict);
    }

    /**
     * 根据传入的valueList{dictValue}和客户号下已经生成的产品的ID来获取需要生成的产品
     * @param valueList dictValue 码值的list
     * @param serialNo 客户号
     * @return  List<SysDictInfo>
     */
    @Override
    public List<SysDictInfo> getSysDictInfoListByValue(List<String> valueList, String serialNo) {
        SysDictInfo dict = new SysDictInfo();
        dict.setDictCode(Constants.DictInfo.PRODUCT_NAME);
        dict.getMap().put("pks",valueList);
        dict.getMap().put("serialNo",serialNo);
        return this.sysDictInfoDao.selectSysDictInfoListByValue(dict);
    }

    @Override
    public List<SysDictInfo> getSysDictInfoListBySelectKey(SysDictInfo info) {
        return this.sysDictInfoDao.selectSysDictInfoListBySelectKey(info);
    }

    /**
     * 生成排序值
     *
     * @param t
     */
    private void generateDictSortValue(SysDictInfo t) {
        SysDictInfo oldDict = new SysDictInfo();
        oldDict.setDictCode(t.getDictCode());
        List<SysDictInfo> dictInfoList = this.getSysDictInfoList(oldDict);
        List<Integer> orderList = new ArrayList<Integer>();
        for (SysDictInfo info : dictInfoList) {
            if (!StringUtil.isNull(info.getDictSort())) {
                orderList.add(Integer.parseInt(info.getDictSort()));
            }
        }
        int i = 1;
        boolean flag = true;
        while (flag) {
            if (orderList.contains(i)) {
                i++;
            } else {
                flag = false;
            }
        }
        t.setDictSort(i + "");
    }
}
