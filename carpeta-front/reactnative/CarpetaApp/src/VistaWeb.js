//import * as constants from './Constants.js';
import React, {Component} from 'react';
import {StyleSheet, Dimensions, Platform, View, Text} from 'react-native';
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
    var text = 'WIDTH: ' + dim.width;

    if (Platform.OS === 'web') {
      return (
        <View style={styles.iframeContainer}>
          <Text>{text}</Text>
          <iframe src={this.props.url} height={dim.height - 40} styles={styles.iframe} />
        </View>
      );
    }

    return (
      <View style={styles.classWebView}>
        <Text>{text}</Text>
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
  iframeContainer: {
    width: dim.width - 80,
  },
  iframe: {
    width: dim.width - 140,
    //height: 500,
  },
});

export default VistaWeb;
