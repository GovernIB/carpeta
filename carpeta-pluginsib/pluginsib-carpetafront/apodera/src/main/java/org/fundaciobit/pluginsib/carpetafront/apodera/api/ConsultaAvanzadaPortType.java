
package org.fundaciobit.pluginsib.carpetafront.apodera.api;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConsultaAvanzadaPortType", targetNamespace = "https://consultaApoderamientos.minhap.es/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConsultaAvanzadaPortType {


    /**
     * 
     * @param peticion
     * @return
     *     returns es.caib.apodera.client.api.RespuestaConsulta
     * @throws SoapFault
     */
    @WebMethod(action = "consultaAvanzadaApoderamientos")
    @WebResult(name = "RespuestaConsulta", targetNamespace = "https://consultaApoderamientos.minhap.es/respuestaConsulta", partName = "respuesta")
    public RespuestaConsulta consultaAvanzadaApoderamientos(
        @WebParam(name = "PeticionConsulta", targetNamespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", partName = "peticion")
        PeticionConsulta peticion)
        throws SoapFault
    ;

}
