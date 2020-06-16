package es.caib.carpeta.back.form;

import org.fundaciobit.genapp.common.web.form.BaseForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class CarpetaBaseForm extends BaseForm {

  public CarpetaBaseForm() {
  }
  
  public CarpetaBaseForm(boolean nou) {
    super(nou);
  }
  
  public CarpetaBaseForm(CarpetaBaseForm __toClone) {
    super(__toClone);
  }
  
}
