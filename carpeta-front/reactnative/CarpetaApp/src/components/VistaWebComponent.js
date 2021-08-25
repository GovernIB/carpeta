/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 10:51:32
 * @modify date 17-08-2021 10:51:32
 * @desc [description]
 */
import React, {Component} from 'react';
import {Dimensions, Linking, StyleSheet, Text, View} from 'react-native';
import WebView from 'react-native-webview';

// FIXME: Falta els propptypes per this.props.url
const dim = Dimensions.get('window');

class VistaWebComponent extends Component {
  render() {
    var debugEntry = '';

    if (this.props.debug) {
      debugEntry = <Text>AMB REACT NATIVE WEBVIEW X({this.props.url})</Text>;
    } else {
      debugEntry = '';
    }

    return (
      <View id="vistaid" style={styles.classView}>
        {debugEntry}
        <WebView
          originWhitelist={['*']}
          automaticallyAdjustContentInsets={true}
          scrollEnabled={true}
          ignoreSslError={true}
          javaScriptEnabled={true}
          ref={ref => {
            this.webview = ref;
          }}
          /*
          onNavigationStateChange={event => {
            //this.webview.stopLoading()
            var url = event.url;
            if (url.includes('/rnhp/')) {
              console.log('Obring URl => ]' + url + '[');
              Linking.openURL(url);
            }
          }}
          /*
          injectedJavaScriptBeforeContentLoaded={`
          window.onerror = function(message, sourcefile, lineno, colno, error) {
            window.alert("Message: " + message + " - Source: " + sourcefile + " Line: " + lineno + ":" + colno);
            return false;
          };
          true;
        `}
          useWebKit={true}
          startInLoadingState={true}
          scalesPageToFit={true}
          allowFileAccess={true}
          domStorageEnabled={true}
          allowUniversalAccessFromFileURLs={true}
          allowFileAccessFromFileURLs={true}onError={syntheticEvent => {
            const {nativeEvent} = syntheticEvent;
            console.warn('WebView error: ', nativeEvent);
          }}
          */
          // source={{html: '<h1>Hello world</h1>'}}
          source={{uri: this.props.url}}
          style={styles.classWebView}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  classView: {
    width: dim.width - 40,
    height: '100%', // dim.height - 40,
    borderWidth: 3,
    borderColor: 'blue',
    borderStyle: 'dotted',
    flex: 1,
  },
  classWebView: {
    width: dim.width - 40,
    height: dim.height - 40,
    borderWidth: 3,
    borderColor: 'green',
    borderStyle: 'dotted',
    flex: 1,
  },
});

export default VistaWebComponent;
