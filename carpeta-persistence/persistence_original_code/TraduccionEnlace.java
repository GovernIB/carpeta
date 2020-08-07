package es.caib.carpeta.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 20/05/2020
 */
@Embeddable
public class TraduccionEnlace extends TraduccionBase {

   @NotNull
   @Column(name = "URL", nullable = false)
   private String url;

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
}
