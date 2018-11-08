
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>workflowProfile complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="workflowProfile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="attribute" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="describe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="i18NCategory" type="{http://ws.livebos.apex.com/}i18NString" minOccurs="0"/&gt;
 *         &lt;element name="i18NDescribe" type="{http://ws.livebos.apex.com/}i18NString" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowProfile", propOrder = {
    "attribute",
    "category",
    "describe",
    "i18NCategory",
    "i18NDescribe",
    "name",
    "type"
})
public class WorkflowProfile {

    protected int attribute;
    protected String category;
    protected String describe;
    protected I18NString i18NCategory;
    protected I18NString i18NDescribe;
    protected String name;
    protected int type;

    /**
     * 获取attribute属性的值。
     * 
     */
    public int getAttribute() {
        return attribute;
    }

    /**
     * 设置attribute属性的值。
     * 
     */
    public void setAttribute(int value) {
        this.attribute = value;
    }

    /**
     * 获取category属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置category属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * 获取describe属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置describe属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescribe(String value) {
        this.describe = value;
    }

    /**
     * 获取i18NCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link I18NString }
     *     
     */
    public I18NString getI18NCategory() {
        return i18NCategory;
    }

    /**
     * 设置i18NCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link I18NString }
     *     
     */
    public void setI18NCategory(I18NString value) {
        this.i18NCategory = value;
    }

    /**
     * 获取i18NDescribe属性的值。
     * 
     * @return
     *     possible object is
     *     {@link I18NString }
     *     
     */
    public I18NString getI18NDescribe() {
        return i18NDescribe;
    }

    /**
     * 设置i18NDescribe属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link I18NString }
     *     
     */
    public void setI18NDescribe(I18NString value) {
        this.i18NDescribe = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取type属性的值。
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

}
