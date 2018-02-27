package cn.com.winning.ssgj.domain.expand;

/**
 * @author chenshijie
 * @title Flot报表数据实体类
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.domain.expand
 * @date 2018-02-24 9:58
 */
public class FlotDataInfo {

    /**
     * 显示文本
     */
    private String label;

    /**
     * 数据量
     */
    private int data;

    /**
     * 显示颜色
     */
    private String color;

    public FlotDataInfo() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "FlotDataInfo{" +
                "label='" + label + '\'' +
                ", data=" + data +
                ", color='" + color + '\'' +
                '}';
    }
}

