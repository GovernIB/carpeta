import React, {Component} from 'react';
import {View, Text, Dimensions, StyleSheet} from 'react-native';
import VistaWebComponent from './components/VistaWebComponent';

/**
 * @author anadal(u80067)
 * @format
 * @flow strict-local
 */
const dim = Dimensions.get('window');

class VistaWeb extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    var text = 'WIDTH: ' + dim.width;
    var url = 'http://otae.fundaciobit.org';

    return (
      <View style={styles.borderdotted}>
        <Text>{text}</Text>

        <VistaWebComponent url={url} debug={true} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  borderdotted: {
    borderWidth: 3,
    borderColor: 'red',
    borderStyle: 'dotted',
    borderRadius: 1,
    flex: 1,
    position: 'relative',
  },
});

export default VistaWeb;
