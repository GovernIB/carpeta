/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 10:51:32
 * @modify date 17-08-2021 10:51:32
 * @desc [description]
 */
import React, {Component} from 'react';
import {Dimensions, StyleSheet, Text, View} from 'react-native';
import WebView from 'react-native-webview';

// FIXME: Falta els propptypes per this.props.url
const dim = Dimensions.get('window');

class VistaWebComponent extends Component {
  render() {
    return (
      <View style={styles.classWebView}>
        {this.props.debug && <Text>AMB REACT NATIVE WEBVIEW</Text>}
        <WebView
          originWhitelist={['*']}
          automaticallyAdjustContentInsets={true}
          scrollEnabled={true}
          // source={{html: '<h1>Hello world</h1>'}}
          source={{uri: this.props.url}}
          style={styles.classWebView}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  classWebView: {
    width: dim.width - 40,
    height: dim.height - 40,
  },
});

export default VistaWebComponent;
