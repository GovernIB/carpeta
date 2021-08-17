import React, {Component} from 'react';

// I18N
//import * as RNLocalize from 'react-native-localize';
//import i18n from 'i18n-js';
//import memoize from 'lodash.memoize';
// import {setI18nConfig, t} from './i18n.js';

import i18n from './i18n';
import {withTranslation} from 'react-i18next';
import Home from './Home.js';

class App extends Component {
  constructor(props) {
    super(props);
    // I18N
    //i18n.init();

    console.log('LOCALE => ' + i18n.language);
  }

  render() {
    return <Home />;
  }
}

export default withTranslation()(App);
