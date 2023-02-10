/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 13-01-2023 11:24:29
 * @modify date 13-01-2023 11:24:29
 * @desc [description]
 */

class ConfigurationProvider {
  constructor() {}


  static isRevisionPeriod() {

    // Mes => gener:0, Febrer:1, Març: 2, ...
    let finishdaterevision = new Date(2023, 1, 7);
    let today = new Date();

    console.log("Today: " + today);
    console.log("Today: " + today.getTime());
    console.log("finishdaterevision: " + finishdaterevision);
    console.log("finishdaterevision: " + finishdaterevision.getTime());

    console.log("calcul: " + (today.getTime() <= finishdaterevision.getTime()));

    if(today.getTime() <= finishdaterevision.getTime()) {
        console.log("Return true");
        return true;
    } else {
        console.log("Return false");
        return false;
    }
  }

  static getCarpetaServer() {
    if (this.isRevisionPeriod()) {
      return "https://se.caib.es/carpetafront";
    } else {
      return "https://www.caib.es/carpetafront";
    }
  }

  static getCarpetaEntity() {
    return "caib";
  }
}


export default ConfigurationProvider;
