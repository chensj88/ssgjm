package cn.com.winning.ssgj.domain.support;

import java.io.Serializable;

/**
 * @author chenshijie
 * @title 上传文件的信息内容
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.domain.support
 * @date 2018-04-10 16:12
 */
public class UrlContent implements Serializable {

    private Long id;
    private String name;
    private String url;

    public UrlContent() {
    }

    public UrlContent(Long id,String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
