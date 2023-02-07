
import React from "react";
import { StyleSheet, ScrollView, Text } from "react-native";
import * as Linking from "expo-linking";
import VistaWebComponent from "./components/VistaWebComponent";
import Persistencia from "./Persistencia";
import { sessionStorageRN } from "./SessionStorageClass";
import {LinkingOpenURL} from './WebBrowserUtils';
import withRouter from './withRouter';

/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 19-08-2021 09:09:44
 * @modify date 19-08-2021 09:09:44
 * @desc
 */
class LoginIBCallBackBrowser extends React.Component {
  constructor(props) {
    super(props);
    this.navigateCarpetaWeb.bind(this);
    this.calledWhenDataLoaded = this.calledWhenDataLoaded.bind(this);
    this.callWhenNavigationStateChange =
      this.callWhenNavigationStateChange.bind(this);

    this.storage = new Persistencia();
    // Carrega els valors de forma asincrona i quan ha acabat crida a la funcio passada per paràmetre
    this.storage.load(this.calledWhenDataLoaded);

    this.state = { loadedData: false, urlcarpeta: "" };
  }

  componentDidMount() {
    console.log("LoginIBCallBackBrowser => componentDidMount()");

    const { loginIBCallBackBrowserRef } = this.props;
    loginIBCallBackBrowserRef(this);
  }

  componentWillUnmount() {
    const { loginIBCallBackBrowserRef } = this.props;
    loginIBCallBackBrowserRef(undefined);
  }

  
  loadRootPage() {
    this.navigateCarpetaWeb(this.state.urlcarpeta, true);
  }


  navigateCarpetaWeb(url, ispublic) {
    // Crida a vistaWebComponent per a que canvii la pàgina web
    // Canvia url del WebView
    console.log(
      "LoginIBCallBackBrowser::navigateCarpetaWeb(" + url + "," + ispublic + ")"
    );

    if (this.vistaWebComponent) {
      this.vistaWebComponent.navigateCarpetaWeb(url, ispublic);
    } else {
      console.error(
        "LoginIBCallBackBrowser::navigateCarpetaWeb => this.vistaWebComponent és null"
      );
    }
  }

  calledWhenDataLoaded(urlcarpeta, codientitat) {
    console.log("LoginIBCallBackBrowser::calledWhenDataLoaded  => entra]" + urlcarpeta + "[");

    this.setState({ loadedData: true, urlcarpeta: urlcarpeta });
  }

  // METODE REPETIT A CarpetaWeb.js
  callWhenNavigationStateChange(event) {
    var url = event.url;
    console.log("");
    console.log("LoginIBCallBackBrowser::Check URL => ]" + url + "[");

    if (url.includes("/public/doLogin?")) {
      if (this.alreadyopen) {
        this.alreadyopen = false;
      } else {
        this.alreadyopen = true;

        var pos = url.lastIndexOf("=");

        var loginCode = url.substring(pos + 1, url.length);

        console.log("LoginIBCallBackBrowser: LoginCode => ]" + loginCode + "[");
        var urlCarpeta = this.state.urlcarpeta;
        console.log("LoginIBCallBackBrowser: urlCarpeta => ]" + urlCarpeta + "[");
        var urlbase = this.getUrlBase(url);

        console.log("LoginIBCallBackBrowser: urlbase => ]" + urlbase + "[");

        var theUrlBrowser =
          urlCarpeta +
          "/public/preLoginApp/" +
          loginCode +
          "?urlbase=" +
          encodeURIComponent(urlbase);

        console.log(
          new Date().toUTCString() + "  Obring URL => ]" + theUrlBrowser + "["
        );
        LinkingOpenURL(theUrlBrowser, this.loadRootPage);
      }
      return false;
    }
    if (url.startsWith(this.state.urlcarpeta)) {
      console.log("LoginIBCallBackBrowser::URL interna : " + url);
      return true;
    } else {
      if (!this.lastExternal || this.lastExternal != url) {
        console.log("CarpetaWeb:: stopLoading ... ");
        event.target.stopLoading();
        console.log("LoginIBCallBackBrowser::Obrint URL externa : " + url);
        
        Linking.canOpenURL(url).then((supported) => {
          if (supported) {
            return Linking.openURL(url).catch(() => null);
          }
        });
        this.lastExternal = url;
      } else {
        event.target.stopLoading();
        this.lastExternal = null;
        console.log(
          "LoginIBCallBackBrowser::NO Obrim URL externa: REPETIDA !!!! "
        );
      }

      return false;
    }
  }

  render() {
    console.log("LoginIBCallBackBrowser::  ENTRA A RENDER ... ");

    let codilogin = this.props.params.codilogin;

    if (!this.state.loadedData) {
      return <Text>Loading data ...</Text>;
    }
    console.log(
      "LOGINIB CALLBACK => Render ... CodiLogin =]" + codilogin + "["
    );
    console.log(
      "LOGINIB CALLBACK => Render ... this.state.urlcarpeta =]" + this.state.urlcarpeta + "["
    );

    var loginUrl =
      this.state.urlcarpeta +
      "/public/homePageAppPostWebLogin/" +
      codilogin;

    var expoPushToken = sessionStorageRN.getItem("expoPushToken");
    if (!expoPushToken) {
      console.error("ExpoPushToken no definit !!! No s'envia al servidor :-(");
    } else {
      console.log("Enviant ExpoPushToken al Servidor: " + expoPushToken);
      loginUrl =
        loginUrl + "?expopushtoken=" + encodeURIComponent(expoPushToken);
    }

    console.log("LOGINIB CALLBACK => Obrint URL " + loginUrl);

    return (
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={styles.borderdotted}
      >
        <VistaWebComponent
          url={loginUrl}
          debug={true}
          vistaWebComponentRef={(ref) => (this.vistaWebComponent = ref)}
          callWhenNavigationStateChange={this.callWhenNavigationStateChange}
        />
      </ScrollView>
    );
    //}
  }
}

const styles = StyleSheet.create({
  // borderdotted: {
  //   borderWidth: 3,
  //   borderColor: 'red',
  //   borderStyle: 'dotted',
  //   borderRadius: 1,
  //   //flex: 1,
  //   //position: 'relative',
  //   //height: 500,
  // },

  fixToText: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
});

export default withRouter(LoginIBCallBackBrowser);
