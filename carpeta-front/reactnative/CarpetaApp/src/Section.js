/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 18-08-2021 08:17:18
 * @modify date 18-08-2021 08:17:18
 * @desc [description]
 * @format
 * @flow strict-local
 */
import * as constants from './Constants.js';
import React, {Component} from 'react';
import {Text, View, StyleSheet} from 'react-native';

class Section extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <View style={styles.sectionContainer}>
        <Text style={styles.sectionTitle}>{this.props.title}</Text>
        <Text style={styles.sectionDescription}>{this.props.children}</Text>
      </View>
    );
  }
}

// FIXME: falten PropTypes !!!!

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
    flex: 1,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: constants.Color_BLACK,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
});

export default Section;
