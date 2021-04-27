import i18n from "i18next";
import {initReactI18next} from "react-i18next";

import translationEN from './translations/translation_en.json';
import translationES from './translations/translation_es.json';
import translationCA from './translations/translation_ca.json';

// the translations
const resources = {
  en: {
    translation: translationEN
  },
  es: {
    translation: translationES
  },
  ca: {
    translation: translationCA
  }
};

const langActual = sessionStorage.getItem("langActual");
  
  i18n
  // .use(detector) // web lang detect
  .use(initReactI18next)
  .init({
    resources,
    lng: langActual,
    // fallbackLng: "ca", // use ca if detected lng is not available

    keySeparator: false, // we do not use keys in form messages.welcome

    interpolation: {
      escapeValue: false // react already safes from xss
    }
  });

export default i18n;