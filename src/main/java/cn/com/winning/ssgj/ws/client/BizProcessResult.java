
package cn.com.winning.ssgj.ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bizProcessResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bizProcessResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult">
 *       &lt;sequence>
 *         &lt;element name="outputVariables" type="{http://ws.livebos.apex.com/}lbParameter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bizProcessResult", propOrder = {
    "outputVariables"
})
public class BizProcessResult
    extends LbeResult
{

    @XmlElement(nillable = true)
    protected List<LbParameter> outputVariables;

    /**
     * Gets the value of the outputVariables property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outputVariables property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutputVariables().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LbParameter }
     * 
     * 
     */
    public List<LbParameter> getOutputVariables() {
        if (outputVariables == null) {
            outputVariables = new ArrayList<LbParameter>();
        }
        return this.outputVariables;
    }

}
