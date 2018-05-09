
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getWorkAvailableActionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getWorkAvailableActionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AvailableWorkActionResponse" type="{http://ws.livebos.apex.com/}availableWorkActionResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWorkAvailableActionResponse", propOrder = {
    "availableWorkActionResponse"
})
public class GetWorkAvailableActionResponse {

    @XmlElement(name = "AvailableWorkActionResponse")
    protected AvailableWorkActionResponse availableWorkActionResponse;

    /**
     * Gets the value of the availableWorkActionResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AvailableWorkActionResponse }
     *     
     */
    public AvailableWorkActionResponse getAvailableWorkActionResponse() {
        return availableWorkActionResponse;
    }

    /**
     * Sets the value of the availableWorkActionResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailableWorkActionResponse }
     *     
     */
    public void setAvailableWorkActionResponse(AvailableWorkActionResponse value) {
        this.availableWorkActionResponse = value;
    }

}
