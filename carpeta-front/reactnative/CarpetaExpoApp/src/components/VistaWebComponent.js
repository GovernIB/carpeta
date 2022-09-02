/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 10:51:32
 * @modify date 12-05-2022 11:02:24
 * @desc [description]
 */
import React, { Component } from "react";
import { Dimensions, StyleSheet, Text, View } from "react-native";
import WebView from "react-native-webview";

// FIXME: Falta els propptypes per this.props.url
const dim = Dimensions.get("window");

class VistaWebComponent extends Component {
  alreadyopen = false;

  constructor(props) {
    super(props);
    this.navigateCarpetaWeb.bind(this);
    this.state = { url: this.props.url };
  }

  componentDidMount() {
    const { vistaWebComponentRef } = this.props;
    vistaWebComponentRef(this);
  }

  componentWillUnmount() {
    //const { vistaWebComponentRef } = this.props;
    //vistaWebComponentRef(undefined);
  }

  navigateCarpetaWeb(urlParam, ispublic) {
    console.log("VistaWebComponent::navigateCarpetaWeb(" + urlParam + "," + ispublic+ ")");
    this.setState({ url: urlParam });
  }



  render() {
    var url = this.state.url;
    //if (this.props.debug)
    {
      console.log("VistaWebComponent::render()  NATIVE WEBVIEW X(" + url + ")");
    }

    return (
      <View id="vistaid" style={styles.classView}>
        <WebView
          originWhitelist={["*"]}
          automaticallyAdjustContentInsets={true}
          scrollEnabled={true}
          ignoreSslError={true}
          javaScriptEnabled={true}
          ref={(ref) => {
            this.webview = ref;
          }}
          onNavigationStateChange={(event) => {
            console.log("VistaWebComponent:: onNavigationStateChange(" + event.url+ ")");
            if (this.props.callWhenNavigationStateChange) {
              event.target = this.webview;
              return this.props.callWhenNavigationStateChange(event);
            }
            console.log("VistaWebComponent:: NO DEFINIT this.props.callWhenNavigationStateChange: " + event.url);
            return true;
          }}
          source={{ uri: url }}
          style={styles.classWebView}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  classView: {
    width: dim.width,
    height: "100%", // dim.height - 40,
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
    borderColor: "green",
    borderStyle: "dotted",
    flex: 1,
  },
});

export default VistaWebComponent;
