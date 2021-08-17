/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 10:51:45
 * @modify date 17-08-2021 10:51:45
 * @desc [description]
 */
import React, {Component} from 'react';
import {Dimensions, StyleSheet, Text, View} from 'react-native';

// FIXME: Falta els propptypes per this.props.url

const dim = Dimensions.get('window');

class VistaWebComponent extends Component {
  render() {
    return (
      <View style={stylesWeb.iframeContainer}>
        {this.props.debug && <Text>AMB IFRAME</Text>}
        <iframe src={this.props.url} height={dim.height - 40} styles={stylesWeb.iframe} />
      </View>
    );
  }
}

const stylesWeb = StyleSheet.create({
  iframeContainer: {
    width: dim.width - 80,
  },
  iframe: {
    width: dim.width - 140,
    //height: 500,
  },
});

export default VistaWebComponent;
