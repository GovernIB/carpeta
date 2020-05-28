package es.caib.carpeta.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 21/05/2020
 */
@Embeddable
public class TraduccionAviso extends TraduccionBase {

   @NotNull
   @Column(name = "TEXTO_AVISO", nullable = false)
   private String textoAviso;

   public String getTextoAviso() {
      return textoAviso;
   }

   public void setTextoAviso(String textoAviso) {
      this.textoAviso = textoAviso;
   }
}
