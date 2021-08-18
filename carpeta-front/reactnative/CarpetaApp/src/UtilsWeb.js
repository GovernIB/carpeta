import * as constants from './Constants.js';
import React, {Component} from 'react';
import Section from './Section.js';
import VistaWeb from './VistaWeb.js';
import {Linking, ScrollView, StyleSheet, Text, TouchableOpacity, View} from 'react-native';

import {withTranslation} from 'react-i18next';

/**
 * @author anadal(u80067)
 * @format
 * @flow strict-local
 *
 */

class UtilsWeb extends Component {
  constructor(props) {
    super(props);
  }

  openLink() {
    Linking.openURL('https://egghead.io');
  }

  render() {
    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
        <View
          style={{
            backgroundColor: constants.Color_WHITE,
          }}>
          <Section title="Open External Browser">
            <TouchableOpacity onPress={this.openLink}>
              <Text style={styles.link}>
                Link to https://egghead.io/{' == '}
                {'<a href="https://egghead.io" target="_blank"> </a>'}
              </Text>
            </TouchableOpacity>
          </Section>
          <Section title="Inline Web View: ">
            <VistaWeb />
          </Section>
        </View>
      </ScrollView>
    );
  }
}
/*

          </SafeAreaView>
        )}
      </UseColorSchemeHook>*/

const styles = StyleSheet.create({
  link: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

// I18N
export default withTranslation()(UtilsWeb);
