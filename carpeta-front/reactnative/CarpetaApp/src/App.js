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

/**
 * @author anadal(u80067)
 * @format
 * @flow strict-local
 */

class App extends Component {
  constructor(props) {
    super(props);
  }

  openLink() {
    Linking.openURL('https://egghead.io');
  }

  render() {
    return (
      <UseColorSchemeHook>
        {isDarkMode => (
          <SafeAreaView
            style={{
              backgroundColor: isDarkMode ? constants.Color_BLACK : constants.Color_WHITE,
            }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
              <View
                style={{
                  backgroundColor: isDarkMode ? constants.Color_BLACK : constants.Color_WHITE,
                }}>
                <Section title="Step One" isDarkMode={isDarkMode}>
                  Edit <Text style={styles.highlight}>App.js</Text> to change this screen and then
                  come back to see your edits.
                </Section>
                <Section title="See Your Changes & Platform" isDarkMode={isDarkMode}>
                  Hello World _See_Your_Changes_ !!! {'\n'}
                  Platform: {Platform.OS} v{Platform.Version}
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
                    <VistaWeb url="https://google.es" />
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
});

export default App;
