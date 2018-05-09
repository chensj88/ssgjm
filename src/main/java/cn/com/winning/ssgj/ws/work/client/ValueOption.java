
package cn.com.winning.ssgj.ws.work.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for valueOption.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="valueOption">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VALUE"/>
 *     &lt;enumeration value="DISPLAY"/>
 *     &lt;enumeration value="BOTH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "valueOption")
@XmlEnum
public enum ValueOption {

    VALUE,
    DISPLAY,
    BOTH;

    public String value() {
        return name();
    }

    public static ValueOption fromValue(String v) {
        return valueOf(v);
    }

}
