
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for validateSessionIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="validateSessionIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LBEResult" type="{http://ws.livebos.apex.com/}lbeResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validateSessionIdResponse", propOrder = {
    "lbeResult"
})
public class ValidateSessionIdResponse {

    @XmlElement(name = "LBEResult")
    protected LbeResult lbeResult;

    /**
     * Gets the value of the lbeResult property.
     * 
     * @return
     *     possible object is
     *     {@link LbeResult }
     *     
     */
    public LbeResult getLBEResult() {
        return lbeResult;
    }

    /**
     * Sets the value of the lbeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LbeResult }
     *     
     */
    public void setLBEResult(LbeResult value) {
        this.lbeResult = value;
    }

}
