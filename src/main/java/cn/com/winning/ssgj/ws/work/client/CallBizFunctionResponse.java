
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for callBizFunctionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="callBizFunctionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BizFunctionResult" type="{http://ws.livebos.apex.com/}bizFunctionResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callBizFunctionResponse", propOrder = {
    "bizFunctionResult"
})
public class CallBizFunctionResponse {

    @XmlElement(name = "BizFunctionResult")
    protected BizFunctionResult bizFunctionResult;

    /**
     * Gets the value of the bizFunctionResult property.
     * 
     * @return
     *     possible object is
     *     {@link BizFunctionResult }
     *     
     */
    public BizFunctionResult getBizFunctionResult() {
        return bizFunctionResult;
    }

    /**
     * Sets the value of the bizFunctionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BizFunctionResult }
     *     
     */
    public void setBizFunctionResult(BizFunctionResult value) {
        this.bizFunctionResult = value;
    }

}
