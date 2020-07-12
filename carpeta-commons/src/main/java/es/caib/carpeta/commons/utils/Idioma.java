package es.caib.carpeta.commons.utils;

import java.util.stream.Stream;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 02/07/2020
 */

/*public enum Idioma {
   CA,
   ES,
   EN;


   public static Stream<Idioma> stream() {
      return Stream.of(Idioma.values());
   }
}*/



public enum Idioma
{
   CATALAN("ca","Català", "Catalán", "Catalan"), CASTELLANO("es", "Castellà","Castellano", "Spanish"),
   INGLES("en","Anglès", "Inglés", "English");

   private String nombreCorto;
   private String nombreCatalan;
   private String nombreCastellano;
   private String nombreIngles;

   Idioma(String nombreCorto, String nombreCatalan, String nombreCastellano, String nombreIngles) {
      this.nombreCorto = nombreCorto;
      this.nombreCatalan = nombreCatalan;
      this.nombreCastellano = nombreCastellano;
      this.nombreIngles = nombreIngles;
   }


   public String getNombreCorto() {
      return nombreCorto;
   }

   public void setNombreCorto(String nombreCorto) {
      this.nombreCorto = nombreCorto;
   }

   public String getNombreCatalan() {
      return nombreCatalan;
   }

   public void setNombreCatalan(String nombreCatalan) {
      this.nombreCatalan = nombreCatalan;
   }

   public String getNombreCastellano() {
      return nombreCastellano;
   }

   public void setNombreCastellano(String nombreCastellano) {
      this.nombreCastellano = nombreCastellano;
   }

   public String getNombreIngles() {
      return nombreIngles;
   }

   public void setNombreIngles(String nombreIngles) {
      this.nombreIngles = nombreIngles;
   }

   public static Stream<Idioma> stream() {
      return Stream.of(Idioma.values());
   }

   public String getNombreCortoPorDefecto(){
      return CATALAN.nombreCorto;
}
}


