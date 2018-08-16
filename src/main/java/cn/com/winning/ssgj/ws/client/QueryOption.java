
package cn.com.winning.ssgj.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryOption complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryOption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="batchSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="queryCount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueOption" type="{http://ws.livebos.apex.com/}valueOption" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryOption", propOrder = {
    "batchNo",
    "batchSize",
    "orderBy",
    "queryCount",
    "queryId",
    "valueOption"
})
public class QueryOption {

    protected int batchNo;
    protected int batchSize;
    protected String orderBy;
    protected boolean queryCount;
    protected String queryId;
    protected ValueOption valueOption;

    /**
     * Gets the value of the batchNo property.
     * 
     */
    public int getBatchNo() {
        return batchNo;
    }

    /**
     * Sets the value of the batchNo property.
     * 
     */
    public void setBatchNo(int value) {
        this.batchNo = value;
    }

    /**
     * Gets the value of the batchSize property.
     * 
     */
    public int getBatchSize() {
        return batchSize;
    }

    /**
     * Sets the value of the batchSize property.
     * 
     */
    public void setBatchSize(int value) {
        this.batchSize = value;
    }

    /**
     * Gets the value of the orderBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the value of the orderBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBy(String value) {
        this.orderBy = value;
    }

    /**
     * Gets the value of the queryCount property.
     * 
     */
    public boolean isQueryCount() {
        return queryCount;
    }

    /**
     * Sets the value of the queryCount property.
     * 
     */
    public void setQueryCount(boolean value) {
        this.queryCount = value;
    }

    /**
     * Gets the value of the queryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * Sets the value of the queryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryId(String value) {
        this.queryId = value;
    }

    /**
     * Gets the value of the valueOption property.
     * 
     * @return
     *     possible object is
     *     {@link ValueOption }
     *     
     */
    public ValueOption getValueOption() {
        return valueOption;
    }

    /**
     * Sets the value of the valueOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueOption }
     *     
     */
    public void setValueOption(ValueOption value) {
        this.valueOption = value;
    }

}
