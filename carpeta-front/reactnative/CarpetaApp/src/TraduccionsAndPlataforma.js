//import * as constants from './Constants.js';
import React, {Component} from 'react';
//import {UseColorSchemeHook} from './UseColorShemeHook.js';
import Section from './Section.js';
import {ScrollView, StyleSheet, View} from 'react-native';

import {withTranslation} from 'react-i18next';
import TraductorControl from './TraductorControl.js';
import TraductorExemple from './TraductorExemple.js';
import Plataforma from './Plataforma.js';

/**
 * @author anadal(u80067)
 * @format
 * @flow strict-local
 *
 */

class TraduccionsAndPlataforma extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    var isDarkMode = false;
    /*
    var mystyles = StyleSheet.create({
      safe: {
        flex: 1,
        backgroundColor: isDarkMode ? constants.Color_BLACK : constants.Color_WHITE,
      },
    });

    }
      <UseColorSchemeHook>
        {isDarkMode => (
          <SafeAreaView
            //  FIXME:
            // eslint-disable-next-line react-native/no-inline-styles
            style={mystyles.safe}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'}>
              <Text>{t('menuTitol')}</Text>{' '}
            </StatusBar><ScrollView contentInsetAdjustmentBehavior="automatic" style={mystyles.safe}></ScrollView>
        */
    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
        <View style={styles.borderdotted}>
          <Section title="Traduccions" isDarkMode={isDarkMode}>
            <View>
              <TraductorExemple />
              <TraductorControl />
            </View>
          </Section>
          <Section title="Plataforma" isDarkMode={isDarkMode}>
            <Plataforma />
          </Section>
        </View>
      </ScrollView>
    );
  }
}
/*}
          </SafeAreaView>
        )}
              </UseColorSchemeHook> */
//  FIXME:

const styles = StyleSheet.create({
  borderdotted: {
    borderWidth: 3,
    borderColor: 'green',
    borderStyle: 'dotted',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
    //    backgroundColor: isDarkMode ? constants.Color_BLACK : constants.Color_WHITE,
  },
});

// I18N
export default withTranslation()(TraduccionsAndPlataforma);
