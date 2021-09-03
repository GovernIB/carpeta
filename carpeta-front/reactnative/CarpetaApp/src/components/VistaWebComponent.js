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
import {Url_Base} from '../Constants';

// FIXME: Falta els propptypes per this.props.url
const dim = Dimensions.get('window');

class VistaWebComponent extends Component {
  alreadyopen = false;

  constructor(props) {
    super(props);
  }

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
          onNavigationStateChange={event => {
            //this.webview.stopLoading();
            var url = event.url;
            console.log('Check URL => ]' + url + '[');

            if (url.includes('/public/doLogin?')) {
              console.log('');
              console.log(' alreadyopen => ' + this.alreadyopen);
              console.log('');

              if (this.alreadyopen) {
                this.alreadyopen = false;
              } else {
                this.alreadyopen = true;

                var pos = url.lastIndexOf('=');

                var loginCode = url.substring(pos + 1, url.length);

                console.log('LoginCode => ' + loginCode);

                var theUrlBrowser =
                  Url_Base +
                  '/carpetafront/public/preLoginApp/' +
                  loginCode +
                  '?urlbase=' +
                  encodeURIComponent(Url_Base);

                console.log(new Date().toUTCString() + '  Obring URL => ]' + theUrlBrowser + '[');
                Linking.openURL(theUrlBrowser);
              }
              return false;
            }
            return true;
          }}
          /*
          useWebKit={true}
          scalesPageToFit={true}
          allowFileAccess={true}
          injectedJavaScriptBeforeContentLoaded={`
          window.onerror = function(message, sourcefile, lineno, colno, error) {
            window.alert("Message: " + message + " - Source: " + sourcefile + " Line: " + lineno + ":" + colno);
            return false;
          };
          true;
        }
          startInLoadingState={true}
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
