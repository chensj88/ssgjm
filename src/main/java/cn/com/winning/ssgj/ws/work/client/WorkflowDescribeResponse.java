
package cn.com.winning.ssgj.ws.work.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for workflowDescribeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflowDescribeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workflowDescribes" type="{http://ws.livebos.apex.com/}workflowProfile" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowDescribeResponse", propOrder = {
    "workflowDescribes"
})
public class WorkflowDescribeResponse {

    @XmlElement(nillable = true)
    protected List<WorkflowProfile> workflowDescribes;

    /**
     * Gets the value of the workflowDescribes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the workflowDescribes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorkflowDescribes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WorkflowProfile }
     * 
     * 
     */
    public List<WorkflowProfile> getWorkflowDescribes() {
        if (workflowDescribes == null) {
            workflowDescribes = new ArrayList<WorkflowProfile>();
        }
        return this.workflowDescribes;
    }

}
