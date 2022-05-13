/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 19-08-2021 09:09:44
 * @modify date 19-08-2021 09:09:44
 * @desc
 */
import React from 'react';
import {StyleSheet, ScrollView, Text} from 'react-native';
import VistaWebComponent from './components/VistaWebComponent';
import Persistencia from './Persistencia';
import { sessionStorageRN } from "./SessionStorageClass";

class LoginIBCallBackBrowser extends React.Component {
  constructor(props) {
    super(props);
    this.navigateCarpetaWeb.bind(this);
    this.calledWhenDataLoaded = this.calledWhenDataLoaded.bind(this);

    this.storage = new Persistencia();
    // Carrega els valors de forma asincrona i quan ha acabat crida a la funcio passada per paràmetre
    this.storage.load(this.calledWhenDataLoaded);

    this.state = {loadedData: false, urlcarpeta: ''};
  }


  componentDidMount() {
    console.log('LoginIBCallBackBrowser => componentDidMount()');

    const { loginIBCallBackBrowserRef } = this.props;
    loginIBCallBackBrowserRef(this);
  }

  componentWillUnmount() {

    const { loginIBCallBackBrowserRef } = this.props;
    loginIBCallBackBrowserRef(undefined);
  }

  navigateCarpetaWeb(url, ispublic) {
    // Crida a vistaWebComponent per a que canvii la pàgina web
    // Canvia url del WebView
    console.log('LoginIBCallBackBrowser::navigateCarpetaWeb(' + url + ',' + ispublic+ ')');

    if (this.vistaWebComponent) {
      this.vistaWebComponent.navigateCarpetaWeb(url, ispublic);
    } else {
      console.error("LoginIBCallBackBrowser::navigateCarpetaWeb => this.vistaWebComponent és null");
    }
  }


  calledWhenDataLoaded(urlcarpeta, codientitat) {
    console.log('ENTRA A DORENDER ');

    this.setState({loadedData: true, urlcarpeta: urlcarpeta});
  }

  render() {
    if (!this.state.loadedData) {
      return <Text>Loading data ...</Text>;
    }
    console.log('LOGINIB CALLBACK => Render ... CodiLogin =]' + this.props.codilogin + '[');

    var loginUrl =
      this.state.urlcarpeta + '/public/homePageAppPostWebLogin/' + this.props.codilogin;

    var expoPushToken = sessionStorageRN.getItem("expoPushToken");
    if (!expoPushToken) {
      console.error("ExpoPushToken no definit !!! No s'envia al servidor :-(")
    } else {
      console.log("Enviant ExpoPushToken al Servidor: " + expoPushToken);
      loginUrl = loginUrl + "?expopushtoken=" + encodeURIComponent(expoPushToken);
    }

    console.log('LOGINIB CALLBACK => Obrint URL ' + loginUrl);

    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={styles.borderdotted}>
        <VistaWebComponent url={loginUrl} debug={true} vistaWebComponentRef={ ref => this.vistaWebComponent = ref} />
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
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
});

export default LoginIBCallBackBrowser;
