/**
 * @author anadal
 * @create date 2023-01-03 11:22:46
 * @modify date 2023-01-03 11:22:46
 * @desc [description]
 */

import translationEN from "./translations/translation_en.json";
import translationES from "./translations/translation_es.json";
import translationCA from "./translations/translation_ca.json";


function initTranslation(i18n:any) {


    i18n.addResourceBundle(
        "ca",
        "translation",
        translationCA,
        true,
        false
      );
      
      i18n.addResourceBundle(
        "es",
        "translation",
        translationES,
        true,
        false
      );
      
      i18n.addResourceBundle(
        "en",
        "translation",
        translationEN,
        true,
        false
      );
  
}


export default initTranslation;