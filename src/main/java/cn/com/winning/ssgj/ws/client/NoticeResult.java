
package cn.com.winning.ssgj.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>noticeResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="noticeResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="workflowCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * 获取workflowCount属性的值。
     * 
     */
    public int getWorkflowCount() {
        return workflowCount;
    }

    /**
     * 设置workflowCount属性的值。
     * 
     */
    public void setWorkflowCount(int value) {
        this.workflowCount = value;
    }

}
