
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryWorkflowDescribeResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryWorkflowDescribeResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WorkflowDescirbe" type="{http://ws.livebos.apex.com/}workflowDescribeResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * 获取workflowDescirbe属性的值。
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
     * 设置workflowDescirbe属性的值。
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
