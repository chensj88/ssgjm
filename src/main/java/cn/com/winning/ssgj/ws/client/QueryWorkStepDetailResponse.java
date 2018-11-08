
package cn.com.winning.ssgj.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryWorkStepDetailResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryWorkStepDetailResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WorkStepDetail" type="{http://ws.livebos.apex.com/}queryResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryWorkStepDetailResponse", propOrder = {
    "workStepDetail"
})
public class QueryWorkStepDetailResponse {

    @XmlElement(name = "WorkStepDetail")
    protected QueryResult workStepDetail;

    /**
     * 获取workStepDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link QueryResult }
     *     
     */
    public QueryResult getWorkStepDetail() {
        return workStepDetail;
    }

    /**
     * 设置workStepDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link QueryResult }
     *     
     */
    public void setWorkStepDetail(QueryResult value) {
        this.workStepDetail = value;
    }

}
