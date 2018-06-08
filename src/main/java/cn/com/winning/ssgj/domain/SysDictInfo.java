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

@Alias("sysDictInfo")
public class SysDictInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private String dictCode;

    private String dictValue;

    private String dictLabel;

    private String dictSort;
    /**
     * 字典值 int 转换失败几位 null
     */
    private Integer dictV;
    /**
     * 字典说明
     */
    private String dictDesc;
    /**
     * 拼音码
     */
    private String pyCode;
    /**
     * 产品分类，适用于产品信息
     */
    private String productType;

    public SysDictInfo() {

    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictSort() {
        return dictSort;
    }

    public void setDictSort(String dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public Integer getDictV() {
        try {
            return Integer.parseInt(dictValue);
        }catch (NumberFormatException e){
            return null;
        }

    }

    public void setDictV(Integer dictV) {
        this.dictV = dictV;
    }

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}