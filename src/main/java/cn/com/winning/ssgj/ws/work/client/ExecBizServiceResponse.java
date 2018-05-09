
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for execBizServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="execBizServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BizServiceResult" type="{http://ws.livebos.apex.com/}bizProcessResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "execBizServiceResponse", propOrder = {
    "bizServiceResult"
})
public class ExecBizServiceResponse {

    @XmlElement(name = "BizServiceResult")
    protected BizProcessResult bizServiceResult;

    /**
     * Gets the value of the bizServiceResult property.
     * 
     * @return
     *     possible object is
     *     {@link BizProcessResult }
     *     
     */
    public BizProcessResult getBizServiceResult() {
        return bizServiceResult;
    }

    /**
     * Sets the value of the bizServiceResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BizProcessResult }
     *     
     */
    public void setBizServiceResult(BizProcessResult value) {
        this.bizServiceResult = value;
    }

}
