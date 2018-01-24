package cn.com.winning.ssgj.base.id;


/**
 * ID创建接口。
 *
 * @param <T> 对象
 * @author Administrator
 */
public interface IdCreator<T> {

    public abstract T create();

    public abstract T create(int i);

}
