package es.caib.carpeta.logic;



import javax.ejb.Local;
/*
import es.caib.carpeta.ejb.AnnexLocal;
import es.caib.carpeta.jpa.AnnexJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
*/
/**
 * 
 * @author anadal
 *
 */
@Local
public interface SampleLogicaLocal /* extends AnnexLocal */ {
	
	public static final String JNDI_NAME = "java:app/carpeta-logic/SampleLogicaEJB!es.caib.carpeta.logic.SampleLogicaLocal";

/*
  public Set<Long> deleteFull(AnnexJPA annex) throws I18NException;
  
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException;
  */
}

