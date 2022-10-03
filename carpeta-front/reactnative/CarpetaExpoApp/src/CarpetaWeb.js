

import React, {Component} from 'react';
import {StyleSheet, ScrollView, Text, View, Platform } from 'react-native';
import * as Linking from "expo-linking";

import Section from './Section';
import VistaWebComponent from './components/VistaWebComponent';
import Persistencia from './Persistencia';
import {LinkingOpenURL} from './WebBrowserUtils';
import * as WebBrowser from 'expo-web-browser';

/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 18-08-2021 08:51:02
 * @modify date 18-08-2021 08:51:02
 * @desc [description]
 */
class CarpetaWeb extends Component {
  constructor(props) {
    super(props);

    this.navigate.bind(this);
    this.navigateCarpetaWeb.bind(this);
    this.calledWhenDataLoaded = this.calledWhenDataLoaded.bind(this);
    this.callWhenNavigationStateChange = this.callWhenNavigationStateChange.bind(this);
    

    this.storage = new Persistencia();
    // Carrega els valors de forma asincrona i quan ha acabat crida a la funcio passada per paràmetre
    this.storage.load(this.calledWhenDataLoaded);
    this.state = {loadedData: false, urlcarpeta: '', codientitat: ''};

    this.vistaWebComponentRef = React.createRef();

    console.log('CarpetaWeb: Surt de Constructor');
  }

  calledWhenDataLoaded(urlcarpeta, codientitat) {
    console.log(
      'CarpetaWeb: ENTRA A calledWhenDataLoaded =>  urlcarpeta: ' +
        urlcarpeta +
        ' |  codientitat ' +
        codientitat,
    );

    if (urlcarpeta === '' || codientitat === '') {
      // això va a configuració
      //this.props.history.push('/config/');

      // posam valors a pinyo fix
      this.setState({loadedData: true, urlcarpeta:'https://www.caib.es/carpetafront', codientitat: 'caib'});

    } else {
      this.setState({loadedData: true, urlcarpeta: urlcarpeta, codientitat: codientitat});
    }
  }

  // METODE REPETIT A LoginIBCallBackBrowser.js
  callWhenNavigationStateChange(event) {
    var url = event.url;
    console.log('CarpetaWeb::Check URL => ]' + url + '[');

    

    if (url.includes('/public/doLogin?')) {

      if (this.alreadyopen) {
        this.alreadyopen = false;
      } else {
        this.alreadyopen = true;

        var pos = url.lastIndexOf('=');

        var loginCode = url.substring(pos + 1, url.length);

        console.log('CarpetaWeb::LoginCode => ' + loginCode);
        var urlCarpeta = this.state.urlcarpeta;
        var urlbase = this.getUrlBase(url);

        var theUrlBrowser =
          urlCarpeta + '/public/preLoginApp/' + loginCode + '?urlbase=' + encodeURIComponent(urlbase);

        console.log(new Date().toUTCString() + '  Obring URL => ]' + theUrlBrowser + '[');
        LinkingOpenURL(theUrlBrowser);
      }
      return false;
    }

    if(url.startsWith(this.state.urlcarpeta)) {
      console.log("CarpetaWeb::URL interna : " + url);
      return true;
    } else {

      console.log("CarpetaWeb:: stopLoading ... ");
      event.target.stopLoading();
      console.log("CarpetaWeb::Obrint URL externa : " + url);
      
      Linking.canOpenURL(url).then((supported) => {
        if (supported) {
          return Linking.openURL(url).catch(() => null);
        }
      });

      return false;
    }
   
  }

  componentDidMount() {
    // B. If we are on Android, we immediately call the navigate method passing in the url.
    // If we are on iOS, We add an event listener to call handleOpenUrl when an incoming link is detected.
    console.log('CarpetaWeb => componentDidMount()');

    this.eventListener = Linking.addEventListener('url', this.handleOpenURL);

    const { carpetaWebRef } = this.props;

    if(carpetaWebRef) {
      carpetaWebRef(this);
    }
  }

  componentWillUnmount() {
    // C. We delete the Linking listener on componentWillUnmount
    //DEPRECATED: Linking.removeEventListener('url', this.handleOpenURL);
    this.eventListener.remove();

    const { carpetaWebRef } = this.props;
    if(carpetaWebRef) {
      carpetaWebRef(undefined);
    }
  }

  handleOpenURL = event => {
    // D. When handleOpenURL is called, we pass the event url to the navigate method.
    console.log('CarpetaWeb => handleOpenURL(' + event.url + ')');
    this.navigate(event.url);
  };

  navigateCarpetaWeb(url, ispublic) {
    // Crida a vistaWebComponent per a que canvii la pàgina web
    // Canvia url del WebView
    console.log('CarpetaWeb => navigateCarpetaWeb(' + url + ','+  ispublic+ ')');

    if (this.vistaWebComponent) {
      this.vistaWebComponent.navigateCarpetaWeb(url, ispublic);
    } else {
      console.error("CarpetaWeb::navigateCarpetaWebthis: this.vistaWebComponent és null");
    }
  }


  /* Si des d'un navegador atacam a carpetaapp://carpeta/loquesigui s'executarà aquest mètode */
  navigate = url => {
    // E. We first parse the url to get the id and route name. We then check to see if the route
    // name is equal to "show", and if so we navigate to the People component, passing the id as a prop.

    console.log('CarpetaWeb => navigate(' + url + ')');

    if (!url) {
      return;
    }

    const route = url.replace(/.*?:\/\//g, '');
    console.log('route => ' + route);

    if (url.includes('/show/')) {
      var param = route.split('/').join('_');

      var pos = param.lastIndexOf('_');

      var loginCode = param.substring(pos + 1, param.length);
        
      
        
      if (Platform.OS === 'ios') {
          console.log("Tancant WebBrowser per IOS ...");
          WebBrowser.dismissBrowser();
      }

      console.log('CarpetaWeb::navigate => loginCode: ' + loginCode);

      this.props.history.push('/loginibcallbackbrowser/' + loginCode);
    }
  };

  getUrlBase(url) {
    var pos = url.lastIndexOf('/');
    var pos2 = url.indexOf('//');
    var ubase;
    if (pos === pos2 + 1) {
      ubase = url;
    } else {
      ubase = url.substring(0, pos);
    }
    return ubase;
  }

  render() {
    if (!this.state.loadedData) {
      return <Text>Carregant dades. Esperi per favor ...</Text>;
    }

    var u = this.state.urlcarpeta;
    var c = this.state.codientitat;

    var ubase = this.getUrlBase(u);


    var deeplinknativeapp = Linking.makeUrl("carpeta/show/LOGINCODE");

    var reactNativeHomepage = u + '/public/rnhp/' + c + '?urlbase=' + encodeURIComponent(ubase)
         + '&deeplinknativeapp=' + encodeURIComponent(deeplinknativeapp);

    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <VistaWebComponent
          vistaWebComponentRef={ ref => this.vistaWebComponent = ref}
          url={reactNativeHomepage}
          debug={false}
          callWhenNavigationStateChange={this.callWhenNavigationStateChange}
        />
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  /*borderdotted: {
      borderWidth: 1,
   borderColor: 'red',
   borderStyle: 'dotted',
   borderRadius: 1,

 },*/
  link: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
});

export default CarpetaWeb;
