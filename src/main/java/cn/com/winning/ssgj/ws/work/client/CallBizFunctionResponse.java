
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>callBizFunctionResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="callBizFunctionResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BizFunctionResult" type="{http://ws.livebos.apex.com/}bizFunctionResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callBizFunctionResponse", propOrder = {
    "bizFunctionResult"
})
public class CallBizFunctionResponse {

    @XmlElement(name = "BizFunctionResult")
    protected BizFunctionResult bizFunctionResult;

    /**
     * 获取bizFunctionResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BizFunctionResult }
     *     
     */
    public BizFunctionResult getBizFunctionResult() {
        return bizFunctionResult;
    }

    /**
     * 设置bizFunctionResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BizFunctionResult }
     *     
     */
    public void setBizFunctionResult(BizFunctionResult value) {
        this.bizFunctionResult = value;
    }

}
