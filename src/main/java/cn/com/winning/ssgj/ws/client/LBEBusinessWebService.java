package cn.com.winning.ssgj.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2018-02-05T12:18:59.947+08:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "LBEBusinessWebService", 
                  wsdlLocation = "http://weberp.winning.com.cn:9080/service/LBEBusiness?wsdl",
                  targetNamespace = "http://ws.livebos.apex.com/") 
public class LBEBusinessWebService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.livebos.apex.com/", "LBEBusinessWebService");
    public final static QName LBEBusinessServiceImplPort = new QName("http://ws.livebos.apex.com/", "LBEBusinessServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://weberp.winning.com.cn:9080/service/LBEBusiness?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(LBEBusinessWebService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://weberp.winning.com.cn:9080/service/LBEBusiness?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public LBEBusinessWebService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public LBEBusinessWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LBEBusinessWebService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public LBEBusinessWebService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public LBEBusinessWebService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public LBEBusinessWebService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns LBEBusinessService
     */
    @WebEndpoint(name = "LBEBusinessServiceImplPort")
    public LBEBusinessService getLBEBusinessServiceImplPort() {
        return super.getPort(LBEBusinessServiceImplPort, LBEBusinessService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LBEBusinessService
     */
    @WebEndpoint(name = "LBEBusinessServiceImplPort")
    public LBEBusinessService getLBEBusinessServiceImplPort(WebServiceFeature... features) {
        return super.getPort(LBEBusinessServiceImplPort, LBEBusinessService.class, features);
    }

}
