
package cn.com.winning.ssgj.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateResult" type="{http://ws.livebos.apex.com/}createResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createResponse", propOrder = {
    "createResult"
})
public class CreateResponse {

    @XmlElement(name = "CreateResult")
    protected CreateResult createResult;

    /**
     * Gets the value of the createResult property.
     * 
     * @return
     *     possible object is
     *     {@link CreateResult }
     *     
     */
    public CreateResult getCreateResult() {
        return createResult;
    }

    /**
     * Sets the value of the createResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateResult }
     *     
     */
    public void setCreateResult(CreateResult value) {
        this.createResult = value;
    }

}
