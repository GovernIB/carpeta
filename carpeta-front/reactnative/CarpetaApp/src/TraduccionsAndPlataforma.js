/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 15:21:17
 * @modify date 17-08-2021 15:21:17
 * @desc [description]
 * @format
 * @flow strict-local
 */

import * as constants from './Constants.js';
import React, {Component} from 'react';
import Section from './Section.js';
import {ScrollView, StyleSheet, View} from 'react-native';

import {withTranslation} from 'react-i18next';
import TraductorControl from './TraductorControl.js';
import TraductorExemple from './TraductorExemple.js';
import Plataforma from './Plataforma.js';

class TraduccionsAndPlataforma extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
        <View style={styles.borderdotted}>
          <Section title="Traduccions">
            <View>
              <TraductorExemple />
              <TraductorControl />
            </View>
          </Section>
          <Section title="Plataforma">
            <Plataforma />
          </Section>
        </View>
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  borderdotted: {
    borderWidth: 3,
    borderColor: 'green',
    borderStyle: 'dotted',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
    backgroundColor: constants.Color_WHITE,
  },
});

// I18N
export default withTranslation()(TraduccionsAndPlataforma);
