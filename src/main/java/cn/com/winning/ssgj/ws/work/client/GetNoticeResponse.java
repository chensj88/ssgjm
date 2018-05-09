
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNoticeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNoticeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NoticeResult" type="{http://ws.livebos.apex.com/}noticeResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the noticeResult property.
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
     * Sets the value of the noticeResult property.
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
