/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 18-08-2021 08:51:35
 * @modify date 18-08-2021 08:51:35
 * @desc [description]
 * @format
 * @flow strict-local
 */

import * as constants from './Constants.js';
import React, {Component} from 'react';
import {View, Text, Dimensions, StyleSheet, ScrollView, Button} from 'react-native';
import VistaWebComponent from './components/VistaWebComponent';
import Section from './Section';

const dim = Dimensions.get('window');

class VistaWebIncrustada extends Component {
  constructor(props) {
    super(props);
    this.state = {url: 'http://otae.fundaciobit.org'};
    this.otae.bind(this);
    this.carpeta.bind(this);
  }

  otae() {
    this.setState({url: 'http://otae.fundaciobit.org'});
  }

  carpeta() {
    this.setState({url: 'http://carpeta.fundaciobit.org/carpetafront'});
  }

  // contentInsetAdjustmentBehavior="automatic"
  render() {
    var text = 'WIDTH: ' + dim.width;
    var url = this.state.url;
    return (
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        // FIXME:
        // eslint-disable-next-line react-native/no-inline-styles
        style={{paddingRight: 0, paddingLeft: 0, marginTop: 0}}>
        <Section
          title="Inline Web View: "
          // eslint-disable-next-line react-native/no-inline-styles
          style={{paddingRight: 0, paddingLeft: 0, marginTop: 0}}>
          <View style={styles.borderdotted}>
            <Text>
              {text}
              {'\n'}
            </Text>
            {/* Dos botons: un per otae i un per carpeta.fundaciobit.org */}
            <View style={styles.fixToText}>
              <Button onPress={() => this.otae()} title=" OTAE " />
              <Button onPress={() => this.carpeta()} title="CARPETA" />
            </View>

            <VistaWebComponent url={url} debug={true} />
          </View>
        </Section>
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  borderdotted: {
    borderWidth: 2,
    borderColor: 'blue',
    borderStyle: 'solid',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
    backgroundColor: constants.Color_WHITE,
  },
  fixToText: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
});

export default VistaWebIncrustada;
