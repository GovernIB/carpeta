/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 15:18:13
 * @modify date 17-08-2021 15:18:13
 * @desc [description]
 */

import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';

import translationEN from './translations/translation_en.json';
import translationES from './translations/translation_es.json';
import translationCA from './translations/translation_ca.json';

// the translations
const resources = {
  en: {
    translation: translationEN,
  },
  es: {
    translation: translationES,
  },
  ca: {
    translation: translationCA,
  },
};

const langActual = 'ca'; // FIXME: S'haurà d'obtenir no se d'on !!!!

export const setLocale = locale => {
  i18n.locale = locale;
};

i18n.use(initReactI18next).init({
  resources,
  compatibilityJSON: 'v3',
  lng: langActual,

  react: {
    useSuspense: false,
  },
  wait: false,

  keySeparator: false, // we do not use keys in form messages.welcome

  interpolation: {
    escapeValue: false, // react already safes from xss
  },
});

export default i18n;
