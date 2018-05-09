
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doWorkActionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doWorkActionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WorkActionResult" type="{http://ws.livebos.apex.com/}workActionResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doWorkActionResponse", propOrder = {
    "workActionResult"
})
public class DoWorkActionResponse {

    @XmlElement(name = "WorkActionResult")
    protected WorkActionResult workActionResult;

    /**
     * Gets the value of the workActionResult property.
     * 
     * @return
     *     possible object is
     *     {@link WorkActionResult }
     *     
     */
    public WorkActionResult getWorkActionResult() {
        return workActionResult;
    }

    /**
     * Sets the value of the workActionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkActionResult }
     *     
     */
    public void setWorkActionResult(WorkActionResult value) {
        this.workActionResult = value;
    }

}
