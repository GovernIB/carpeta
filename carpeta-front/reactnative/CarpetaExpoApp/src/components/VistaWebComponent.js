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
  alreadyopen = false;

  constructor(props) {
    super(props);
  }

  render() {
    if (this.props.debug) {
      console.log('AMB REACT NATIVE WEBVIEW X(' + this.props.url + ')');
    }

    return (
      <View id="vistaid" style={styles.classView}>
        <WebView
          originWhitelist={['*']}
          automaticallyAdjustContentInsets={true}
          scrollEnabled={true}
          ignoreSslError={true}
          javaScriptEnabled={true}
          ref={ref => {
            this.webview = ref;
          }}
          onNavigationStateChange={event => {
            if (this.props.callWhenNavigationStateChange) {
              return this.props.callWhenNavigationStateChange(event);
            }
            return true;
          }}
          source={{uri: this.props.url}}
          style={styles.classWebView}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  classView: {
    width: dim.width,
    height: '100%', // dim.height - 40,
    /*
    borderWidth: 3,
    borderColor: 'blue',
    borderStyle: 'dotted',
    */
    flex: 1,
  },
  classWebView: {
    width: dim.width,
    height: dim.height - 40,
    borderWidth: 3,
    borderColor: 'green',
    borderStyle: 'dotted',
    flex: 1,
  },
});

export default VistaWebComponent;
