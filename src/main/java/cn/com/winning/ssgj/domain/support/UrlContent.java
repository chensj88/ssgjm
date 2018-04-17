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
    /**
     * 记录的ID
     */
    private Long id;
    /**
     * 对应记录ID
     */
    private Long recordId;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件路径
     */
    private String url;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作时间
     */
    private String operDate;


    public UrlContent() {
    }

    public UrlContent(Long id,Long recordId,String name, String url,String userName,String operDate) {
        this.id = id;
        this.recordId = recordId;
        this.name = name;
        this.url = url;
        this.userName = userName;
        this.operDate = operDate;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperDate() {
        return operDate;
    }

    public void setOperDate(String operDate) {
        this.operDate = operDate;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
}
