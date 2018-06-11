package cn.com.winning.ssgj.domain;

import org.apache.ibatis.type.Alias;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

@Alias("mobileSiteQuestion")
public class MobileSiteQuestion extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private String timeMmdd; //时间

    private String num; //数量

    private List<T> listQuery; //集合


    public String getTimeMmdd() {
        return timeMmdd;
    }

    public void setTimeMmdd(String timeMmdd) {
        this.timeMmdd = timeMmdd;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<T> getListQuery() {
        return listQuery;
    }

    public void setListQuery(List<T> listQuery) {
        this.listQuery = listQuery;
    }

    public MobileSiteQuestion(List<T> listQuery) {
        this.listQuery = listQuery;
    }
}
