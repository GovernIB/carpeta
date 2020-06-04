package es.caib.carpeta.persistence;

import es.caib.carpeta.commons.utils.Constants;

import java.util.Map;
import java.util.Set;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 25/05/2020
 */

public abstract class Traducible<T extends TraduccionBase> {

   public abstract Map<String, T> getTraducciones();




   // Metodos públicos.

   public  void setTraducciones(Map<String,T> traducciones){
      getTraducciones().putAll(traducciones);
   };

   /**
    * Conjunto de idiomas para los que hay traduccion.
    * @return codigos de idioma para los que hay traduccion.
    */
   public Set<String> getLangs() {
      return getTraducciones().keySet();
   }


   /**
    * Obtiene la traduccion por defecto.
    * @return La traduccion en el idioma per defecto.
    */
   public  T getTraduccion() {
      return getTraducciones().get(Constants.DEFAULT_LANGUAGE);
   }

   /**
    * Obtiene la traduccion en un idioma determinado o <code>null</code>.
    * @param idioma Idioma de la traduccion.
    * @return Traduccion en el idioma indicado o <code>null</code> si no existe.
    */
   public T getTraduccion(String idioma) {
      return getTraducciones().get(idioma);
   }


   /**
    * Fija una traduccion en un idioma determinado, o la borra si es <code>null</code>.
    * @param idioma Idioma de la traduccion,
    * @param traduccion La traduccion a fijar.
    */
   public void setTraduccion(String idioma, T traduccion) {
      if (traduccion == null) {
         getTraducciones().remove(idioma);
      } else {
         getTraducciones().put(idioma, traduccion);
      }
   }

}
