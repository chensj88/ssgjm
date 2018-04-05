
package cn.com.winning.ssgj.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getNoticeResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getNoticeResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NoticeResult" type="{http://ws.livebos.apex.com/}noticeResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNoticeResponse", propOrder = {
    "noticeResult"
})
public class GetNoticeResponse {

    @XmlElement(name = "NoticeResult")
    protected NoticeResult noticeResult;

    /**
     * 获取noticeResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NoticeResult }
     *     
     */
    public NoticeResult getNoticeResult() {
        return noticeResult;
    }

    /**
     * 设置noticeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NoticeResult }
     *     
     */
    public void setNoticeResult(NoticeResult value) {
        this.noticeResult = value;
    }

}
