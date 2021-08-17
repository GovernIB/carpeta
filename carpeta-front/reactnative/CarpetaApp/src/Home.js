import * as constants from './Constants.js';
import React, {Component} from 'react';
import {UseColorSchemeHook} from './UseColorShemeHook.js';
import Section from './Section.js';
import VistaWeb from './VistaWeb.js';
import {
  Linking,
  Platform,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';

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

class Home extends Component {
  constructor(props) {
    super(props);
  }

  openLink() {
    Linking.openURL('https://egghead.io');
  }

  render() {
    const {t} = this.props;
    return (
      <UseColorSchemeHook>
        {isDarkMode => (
          <SafeAreaView
            //  FIXME:
            // eslint-disable-next-line react-native/no-inline-styles
            style={{
              flex: 1,
              backgroundColor: isDarkMode ? constants.Color_BLACK : constants.Color_WHITE,
            }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'}>
              <Text>{t('menuTitol')}</Text>{' '}
            </StatusBar>
            <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
              <View
                style={{
                  backgroundColor: isDarkMode ? constants.Color_BLACK : constants.Color_WHITE,
                }}>
                <Section title="Traduccions" isDarkMode={isDarkMode}>
                  <View>
                    <TraductorExemple />
                    <TraductorControl />
                  </View>
                </Section>
                <Section title="Plataforma" isDarkMode={isDarkMode}>
                  <Plataforma />
                </Section>
                <Section title="Open External Browser" isDarkMode={isDarkMode}>
                  <TouchableOpacity onPress={this.openLink}>
                    <Text style={styles.link}>
                      Link to https://egghead.io/{' == '}
                      {'<a href="https://egghead.io" target="_blank"> </a>'}
                    </Text>
                  </TouchableOpacity>
                </Section>
                <Section title="Inline Web View: " isDarkMode={isDarkMode}>
                  <View style={styles.borderdotted}>
                    <VistaWeb url="http://otae.fundaciobit.org" />
                  </View>
                </Section>
              </View>
            </ScrollView>
          </SafeAreaView>
        )}
      </UseColorSchemeHook>
    );
  }
}

const styles = StyleSheet.create({
  link: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },

  borderdotted: {
    borderWidth: 3,
    borderColor: 'red',
    borderStyle: 'dotted',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
  },
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
export default withTranslation()(Home);
