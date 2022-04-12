
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ConsultaAvanzadaService", targetNamespace = "https://consultaApoderamientos.minhap.es/", wsdlLocation = "classpath:wsdl/reaCXFWSv2.wsdl")
public class ConsultaAvanzadaService
    extends Service
{

    private final static URL CONSULTAAVANZADASERVICE_WSDL_LOCATION;
    private final static WebServiceException CONSULTAAVANZADASERVICE_EXCEPTION;
    private final static QName CONSULTAAVANZADASERVICE_QNAME = new QName("https://consultaApoderamientos.minhap.es/", "ConsultaAvanzadaService");

    static {
        CONSULTAAVANZADASERVICE_WSDL_LOCATION = org.fundaciobit.pluginsib.carpetafront.apodera.api.ConsultaAvanzadaService.class.getResource("classpath:wsdl/reaCXFWSv2.wsdl");
        WebServiceException e = null;
        if (CONSULTAAVANZADASERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'classpath:wsdl/reaCXFWSv2.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        CONSULTAAVANZADASERVICE_EXCEPTION = e;
    }

    public ConsultaAvanzadaService() {
        super(__getWsdlLocation(), CONSULTAAVANZADASERVICE_QNAME);
    }

    public ConsultaAvanzadaService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CONSULTAAVANZADASERVICE_QNAME, features);
    }

    public ConsultaAvanzadaService(URL wsdlLocation) {
        super(wsdlLocation, CONSULTAAVANZADASERVICE_QNAME);
    }

    public ConsultaAvanzadaService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CONSULTAAVANZADASERVICE_QNAME, features);
    }

    public ConsultaAvanzadaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsultaAvanzadaService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ConsultaAvanzadaPortType
     */
    @WebEndpoint(name = "ConsultaAvanzadaPort")
    public ConsultaAvanzadaPortType getConsultaAvanzadaPort() {
        return super.getPort(new QName("https://consultaApoderamientos.minhap.es/", "ConsultaAvanzadaPort"), ConsultaAvanzadaPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConsultaAvanzadaPortType
     */
    @WebEndpoint(name = "ConsultaAvanzadaPort")
    public ConsultaAvanzadaPortType getConsultaAvanzadaPort(WebServiceFeature... features) {
        return super.getPort(new QName("https://consultaApoderamientos.minhap.es/", "ConsultaAvanzadaPort"), ConsultaAvanzadaPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CONSULTAAVANZADASERVICE_EXCEPTION!= null) {
            throw CONSULTAAVANZADASERVICE_EXCEPTION;
        }
        return CONSULTAAVANZADASERVICE_WSDL_LOCATION;
    }

}
