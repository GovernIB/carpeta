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
import {ScrollView, StyleSheet, Text, View} from 'react-native';

import {withTranslation} from 'react-i18next';
import TraductorControl from './TraductorControl.js';
import TraductorExemple from './TraductorExemple.js';
import Plataforma from './Plataforma.js';
import VistaWebIncrustada from './VistaWebIncrustada.js';


class ExemplesDeComponents extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    /* <UseColorSchemeHook> {isDarkMode => (*/
    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
        <View style={styles.borderdottedgreen}>
          <TraductorExemple />
          <TraductorControl />
          {/*
              <View>
                <Text>ISDARKMODE = ]{isDarkMode}[</Text>
              </View>
               */}
        </View>
        <View style={styles.borderdottedred}>
            <Plataforma/>
        </View>

        <View style={styles.borderdottedblue}>
            <VistaWebIncrustada/>
        </View>        


        
      </ScrollView>
    );
    //   )} </UseColorSchemeHook>
  }
}

const styles = StyleSheet.create({
  borderdottedgreen: {
    borderWidth: 3,
    borderColor: 'green',
    borderStyle: 'dotted',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
    backgroundColor: constants.Color_WHITE,
  },
  borderdottedred: {
    borderWidth: 3,
    borderColor: 'red',
    borderStyle: 'dotted',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
    backgroundColor: constants.Color_WHITE,
    marginTop: 35,
  },
  borderdottedblue: {
    borderWidth: 3,
    borderColor: 'blue',
    borderStyle: 'dotted',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
    backgroundColor: constants.Color_WHITE,
    marginTop: 35,
  },
});

// I18N
export default withTranslation()(ExemplesDeComponents);
