package es.caib.carpeta.pluginsib.carpetafront.api;

import java.util.List;
import java.util.Properties;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;

public abstract class AbstractPinbalCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

	public AbstractPinbalCarpetaFrontPlugin() {
		super();	
	}

	public AbstractPinbalCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
		super(propertyKeyBase, properties);
	}

	public AbstractPinbalCarpetaFrontPlugin(String propertyKeyBase) {
		super(propertyKeyBase);
	}


	public void omplirDadesSolicitutComunes(Solicitud solicitud, ScspFuncionario funcionario, ScspTitular titular) {
		
		try {
			
			String propertyBase = getPropertyBase();
			
			final String codProcedimiento = getPropertyRequired(propertyBase + "codiprocediment"); // "CODSVDR_GBA_20121107";
			final String finalidad = getPropertyRequired(propertyBase + "finalitat"); // "Baremacions per el proces d'escolaritzacio";
			final String identificadorSolicitante = getPropertyRequired(propertyBase + "identificadorsolicitant"); // "S0711001H";
			final String unidadTramitadora = getPropertyRequired(propertyBase + "unitattramitadora"); // "Servei d'escolarització";
			
			if (isDevelopment()) {
		        log.info(" ========================= CARREGANT INFO ==============================");		        
		        log.info("codProcedimiento =>" + codProcedimiento);
		        log.info("finalidad =>" + finalidad);
		        log.info("identificadorSolicitante =>" + identificadorSolicitante);
		        log.info("unidadTramitadora =>" + unidadTramitadora);
		        log.info(" =======================================================================");
	        }
			
			solicitud.setIdentificadorSolicitante(identificadorSolicitante);
	        solicitud.setUnidadTramitadora(unidadTramitadora);
	        solicitud.setCodigoProcedimiento(codProcedimiento);
	        solicitud.setFinalidad(finalidad);
	        solicitud.setConsentimiento(ScspSolicitante.ScspConsentimiento.Si);
	        solicitud.setFuncionario(funcionario);
	        solicitud.setTitular(titular);
			
		} catch (Exception e) {
			log.error("AbstractPinbalCarpetaFrontPlugin: error omplirDadesSolicitutComunes => " + e.getMessage());
		} 
	}
	
	
	// CONNEXIÓ 
	public ScspRespuesta getConnexio(List<Solicitud> solicituds) throws Exception { 
		
		ScspRespuesta resposta;
		
		String propertyBase = getPropertyBase();
		final String baseUrl = getProperty(propertyBase + "baseurl"); 
		final String userName = getProperty(propertyBase + "username");
		final String password = getProperty(propertyBase + "password");
		
		final String codigoCertificado = getPropertyRequired(propertyBase + "codicertificat"); 
		
		ClientGeneric clientRest = new ClientGeneric(baseUrl,userName,password);
		resposta = clientRest.peticionSincrona(codigoCertificado, solicituds);
	     
		return resposta;
	}
	
	// JUSTIFICANT
	/*
	public ScspJustificante getJustificant(String idPeticio) throws Exception {
			
		if(idPeticio == null || "".equals(idPeticio)) {
			log.info("GetJustificant - PeticióID NULL ");
			return null;
		}
		
		log.info("GetJustificant - PeticióID: " + idPeticio);
		
		String propertyBase = getPropertyBase();
		final String baseUrl = getProperty(propertyBase + "baseurl"); 
		final String userName = getProperty(propertyBase + "username");
		final String password = getProperty(propertyBase + "password");
		
		ClientGeneric clientRest = new ClientGeneric(baseUrl,userName,password);
		ScspJustificante justificant = clientRest.getJustificante(idPeticio);
		
		return justificant;
	}
	*/
	
    public boolean isDevelopment() {
        return "true".equals(getProperty(getPropertyBase() + "development"));
    }
	
	
}
