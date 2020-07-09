import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

import Backend from 'i18next-http-backend';
import LanguageDetector from 'i18next-browser-languagedetector';

import translationCa from "./locales/ca/translation.json";
import translationEs from "./locales/es/translation.json";

// the translations
const resources = {
  ca: {
    translation: translationCa
  },
  es: {
    translation: translationEs
  }
};


i18n
  // load translation using http -> see /public/locales (i.e. https://github.com/i18next/react-i18next/tree/master/example/react/public/locales)
  // learn more: https://github.com/i18next/i18next-http-backend
  .use(Backend)
  // detect user language
  // learn more: https://github.com/i18next/i18next-browser-languageDetector
  .use(LanguageDetector)
  // pass the i18n instance to react-i18next.
  .use(initReactI18next)
  // init i18next
  // for all options read: https://www.i18next.com/overview/configuration-options
  .init({

    resources: resources,

    fallbackLng: 'ca',
    debug: true,
    interpolation: {
      escapeValue: false, // not needed for react as it escapes by default
    },
    //load: 'http://localhost:8080/carpetafront/locales/{{lng}}/{{ns}}.json',
    backend: {
      // for all available options read the backend's repository readme file
      // loadPath: './locales/{{lng}}/{{ns}}.json',
      //loadPath: './carpetafront/locales/{{lng}}/{{ns}}.json',
      //loadPath: 'http://localhost:8080/carpetafront/locales/{{lng}}/{{ns}}.json',
      //allowMultiLoading: true
    }
  });

export default i18n;
