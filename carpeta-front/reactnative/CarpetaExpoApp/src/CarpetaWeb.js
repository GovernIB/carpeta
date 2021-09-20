/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 18-08-2021 08:51:02
 * @modify date 18-08-2021 08:51:02
 * @desc [description]
 */

import React, {Component} from 'react';
import {StyleSheet, ScrollView, Text, View} from 'react-native';
import * as Linking from "expo-linking";

import Section from './Section';
import VistaWebComponent from './components/VistaWebComponent';
import Persistencia from './Persistencia';

class CarpetaWeb extends Component {
  constructor(props) {
    super(props);
    this.navigate.bind(this);

    this.calledWhenDataLoaded = this.calledWhenDataLoaded.bind(this);
    this.callWhenNavigationStateChange = this.callWhenNavigationStateChange.bind(this);

    this.storage = new Persistencia();
    // Carrega els valors de forma asincrona i quan ha acabat crida a la funcio passada per paràmetre
    this.storage.load(this.calledWhenDataLoaded);

    this.state = {loadedData: false, urlcarpeta: '', codientitat: ''};

    console.log('Carpeta: Surt de Constructor');
  }

  calledWhenDataLoaded(urlcarpeta, codientitat) {
    console.log(
      'Carpeta: ENTRA A calledWhenDataLoaded =>  urlcarpeta: ' +
        urlcarpeta +
        ' |  codientitat ' +
        codientitat,
    );

    if (urlcarpeta === '' || codientitat === '') {
      this.props.history.push('/config/');
    } else {
      this.setState({loadedData: true, urlcarpeta: urlcarpeta, codientitat: codientitat});
    }
  }

  callWhenNavigationStateChange(event) {
    var url = event.url;
    //console.log('Check URL => ]' + url + '[');

    if (url.includes('/public/doLogin?')) {
      //console.log('');
      //console.log(' alreadyopen => ' + this.alreadyopen);
      //console.log('');

      if (this.alreadyopen) {
        this.alreadyopen = false;
      } else {
        this.alreadyopen = true;

        var pos = url.lastIndexOf('=');

        var loginCode = url.substring(pos + 1, url.length);

        console.log('Carpeta: LoginCode => ' + loginCode);

        var url = this.state.urlcarpeta;
        var urlbase = this.getUrlBase(url);

        var theUrlBrowser =
          url + '/public/preLoginApp/' + loginCode + '?urlbase=' + encodeURIComponent(urlbase);

        console.log(new Date().toUTCString() + '  Obring URL => ]' + theUrlBrowser + '[');
        Linking.openURL(theUrlBrowser);
      }
      return false;
    }
    return true;
  }

  componentDidMount() {
    // B. If we are on Android, we immediately call the navigate method passing in the url.
    // If we are on iOS, We add an event listener to call handleOpenUrl when an incoming link is detected.
    console.log('Carpeta => componentDidMount()');

    Linking.addEventListener('url', this.handleOpenURL);
  }

  componentWillUnmount() {
    // C. We delete the Linking listener on componentWillUnmount
    Linking.removeEventListener('url', this.handleOpenURL);
  }

  handleOpenURL = event => {
    // D. When handleOpenURL is called, we pass the event url to the navigate method.
    console.log('Carpeta => handleOpenURL(' + event.url + ')');
    this.navigate(event.url);
  };

  /* Si des d'un navegador atacam a carpetaapp://carpeta/loquesigui s'executarà aquest mètode */
  navigate = url => {
    // E. We first parse the url to get the id and route name. We then check to see if the route
    // name is equal to "show", and if so we navigate to the People component, passing the id as a prop.

    console.log('Carpeta => navigate(' + url + ')');

    if (!url) {
      return;
    }

    const route = url.replace(/.*?:\/\//g, '');
    console.log('route => ' + route);

    if (url.includes('/show/')) {
      var param = route.split('/').join('_');

      var pos = param.lastIndexOf('_');

      var loginCode = param.substring(pos + 1, param.length);

      console.log('loginCode => ' + loginCode);

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
          url={reactNativeHomepage}
          debug={false}
          callWhenNavigationStateChange={this.callWhenNavigationStateChange}
        />
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  borderdotted: {
    /*
    borderWidth: 1,
    borderColor: 'red',
    borderStyle: 'dotted',    
    borderRadius: 1,
    */
  },
  link: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
});

export default CarpetaWeb;
