
package cn.com.winning.ssgj.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for noticeResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="noticeResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult">
 *       &lt;sequence>
 *         &lt;element name="workflowCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "noticeResult", propOrder = {
    "workflowCount"
})
public class NoticeResult
    extends LbeResult
{

    protected int workflowCount;

    /**
     * Gets the value of the workflowCount property.
     * 
     */
    public int getWorkflowCount() {
        return workflowCount;
    }

    /**
     * Sets the value of the workflowCount property.
     * 
     */
    public void setWorkflowCount(int value) {
        this.workflowCount = value;
    }

}
