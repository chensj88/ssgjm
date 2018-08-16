
package cn.com.winning.ssgj.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getWorkOwnerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getWorkOwnerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WorkOwnerResponse" type="{http://ws.livebos.apex.com/}workOwnerResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWorkOwnerResponse", propOrder = {
    "workOwnerResponse"
})
public class GetWorkOwnerResponse {

    @XmlElement(name = "WorkOwnerResponse")
    protected WorkOwnerResponse workOwnerResponse;

    /**
     * Gets the value of the workOwnerResponse property.
     * 
     * @return
     *     possible object is
     *     {@link WorkOwnerResponse }
     *     
     */
    public WorkOwnerResponse getWorkOwnerResponse() {
        return workOwnerResponse;
    }

    /**
     * Sets the value of the workOwnerResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkOwnerResponse }
     *     
     */
    public void setWorkOwnerResponse(WorkOwnerResponse value) {
        this.workOwnerResponse = value;
    }

}
