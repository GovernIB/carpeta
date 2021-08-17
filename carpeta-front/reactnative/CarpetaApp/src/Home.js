//import * as constants from './Constants.js';
import React, {Component} from 'react';
//import {UseColorSchemeHook} from './UseColorShemeHook.js';
import {Text} from 'react-native';

import {withTranslation} from 'react-i18next';

/**
 * @author anadal(u80067)
 * @format
 * @flow strict-local
 *
 */

class Home extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    //const {t} = this.props;
    //var isDarkMode = false;
    return <Text>HOLA DE DE HOME</Text>;
  }
}

// I18N
export default withTranslation()(Home);
