package es.caib.carpeta.model;

public class CarpetaDaoManager {
  
  private static ICarpetaDaoManagers instance = null;
  
  public static void setDaoManagers(ICarpetaDaoManagers managers) {
    instance = managers;
  }
  
  public static ICarpetaDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode CarpetaDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
