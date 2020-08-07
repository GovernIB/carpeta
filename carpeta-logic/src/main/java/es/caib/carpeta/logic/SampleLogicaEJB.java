package es.caib.carpeta.logic;


/*
import es.caib.carpeta.ejb.AnnexEJB;
import es.caib.carpeta.ejb.FitxerLocal;
import es.caib.carpeta.jpa.AnnexJPA;
import es.caib.carpeta.model.entity.AnnexFirmat;
import es.caib.carpeta.model.fields.AnnexFields;
import es.caib.carpeta.model.fields.AnnexFirmatFields;

import org.fundaciobit.genapp.common.i18n.I18NException;

*/

import javax.ejb.Stateless;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "SampleLogicaEJB")
public class SampleLogicaEJB implements SampleLogicaLocal {


}



 
 /**
@Stateless(name = "AnnexLogicaEJB")
public class AnnexLogicaEJB extends AnnexEJB implements AnnexLogicaLocal,
    AnnexFields {


  @EJB(mappedName = FitxerLocal.JNDI_NAME)
  private FitxerLocal fitxerEjb;

  @EJB(mappedName = es.caib.carpeta.ejb.AnnexFirmatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.AnnexFirmatLocal annexFirmatEjb;
  
  
  @Override
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException {
    // TODO Validar !!!
    
    return (AnnexJPA)create(annex);
  }
  


  @Override
  public Set<Long> deleteFull(AnnexJPA annex) throws I18NException {
    
    Set<Long> files = new HashSet<Long>();
    
    if (annex == null) {
      return files;
    }
    
    // Annex
    delete(annex);
    
    return files;
  }

}
*/
