//import * as constants from './Constants.js';
import React, {Component} from 'react';
import {StyleSheet, Dimensions, Platform} from 'react-native';
import WebView from 'react-native-webview';

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
    if (Platform.OS === 'web') {
      return <iframe src={this.props.url} style={styles.iframe} />;
    }

    return (
      /*<Text>AQI HA D?ANAR PAGINA WEB </Text>*/

      <WebView
        originWhitelist={['*']}
        automaticallyAdjustContentInsets={false}
        scrollEnabled={true}
        // source={{html: '<h1>Hello world</h1>'}}
        source={{uri: this.props.url}}
        style={styles.classWebView}
      />
    );
  }
}

const styles = StyleSheet.create({
  classWebView: {
    width: dim.width - 40,
    height: dim.height - 40,
  },
  iframe: {},
});

export default VistaWeb;
