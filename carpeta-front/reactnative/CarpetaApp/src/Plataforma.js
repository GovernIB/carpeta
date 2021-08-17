/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 09:57:24
 * @modify date 17-08-2021 09:57:24
 * @desc Comandes per Plataforma
 */
import React, {Component} from 'react';

// I18N
import {withTranslation} from 'react-i18next';

import {Text, StyleSheet, Platform, View} from 'react-native';
import HolaCaracola from './components/HolaCaracola';

class Plataforma extends Component {
  render() {
    return (
      <View>
        <Text style={styles.plataforma}>
          Platform.OS: {Platform.OS}
          {'\n'}
          Platform.Version: {Platform.Version}
        </Text>
        <HolaCaracola />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  plataforma: {
    flex: 1,
    ...Platform.select({
      ios: {backgroundColor: 'blue'},
      android: {backgroundColor: 'green'},
      web: {backgroundColor: 'red'},
      // other platforms, web for example
      default: {backgroundColor: 'yellow'},
    }),
  },
});

// I18N
export default withTranslation()(Plataforma);
