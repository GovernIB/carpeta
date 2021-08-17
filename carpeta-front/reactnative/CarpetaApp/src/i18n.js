// I18N
//import * as RNLocalize from 'react-native-localize';
//import i18n from 'i18n-js';
//import memoize from 'lodash.memoize';

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

const langActual = 'ca'; // ?????????

export const setLocale = locale => {
  i18n.locale = locale;
};

i18n.use(initReactI18next).init({
  resources,
  lng: langActual,
  // fallbackLng: "ca", // use ca if detected lng is not available

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
