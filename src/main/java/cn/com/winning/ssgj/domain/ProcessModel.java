package cn.com.winning.ssgj.domain;

/**
 * 项目流程数据接口类型
 *
 * @author ChenKuai
 * @create 2018-02-26 上午 11:13
 **/
public class ProcessModel {

    /**
     * @val class 状态
     */
    private String state;

    /**
     * @val 文本
     */
    private String text;

    public ProcessModel() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
