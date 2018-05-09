
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryWorkflowDescribeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryWorkflowDescribeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WorkflowDescirbe" type="{http://ws.livebos.apex.com/}workflowDescribeResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryWorkflowDescribeResponse", propOrder = {
    "workflowDescirbe"
})
public class QueryWorkflowDescribeResponse {

    @XmlElement(name = "WorkflowDescirbe")
    protected WorkflowDescribeResponse workflowDescirbe;

    /**
     * Gets the value of the workflowDescirbe property.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowDescribeResponse }
     *     
     */
    public WorkflowDescribeResponse getWorkflowDescirbe() {
        return workflowDescirbe;
    }

    /**
     * Sets the value of the workflowDescirbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowDescribeResponse }
     *     
     */
    public void setWorkflowDescirbe(WorkflowDescribeResponse value) {
        this.workflowDescirbe = value;
    }

}
