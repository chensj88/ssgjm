
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for workflowProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflowProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attribute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="describe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="i18NCategory" type="{http://ws.livebos.apex.com/}i18NString" minOccurs="0"/>
 *         &lt;element name="i18NDescribe" type="{http://ws.livebos.apex.com/}i18NString" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the attribute property.
     * 
     */
    public int getAttribute() {
        return attribute;
    }

    /**
     * Sets the value of the attribute property.
     * 
     */
    public void setAttribute(int value) {
        this.attribute = value;
    }

    /**
     * Gets the value of the category property.
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
     * Sets the value of the category property.
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
     * Gets the value of the describe property.
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
     * Sets the value of the describe property.
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
     * Gets the value of the i18NCategory property.
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
     * Sets the value of the i18NCategory property.
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
     * Gets the value of the i18NDescribe property.
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
     * Sets the value of the i18NDescribe property.
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
     * Gets the value of the name property.
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
     * Sets the value of the name property.
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
     * Gets the value of the type property.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

}
