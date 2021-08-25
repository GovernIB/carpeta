/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 18-08-2021 08:51:02
 * @modify date 18-08-2021 08:51:02
 * @desc [description]
 */

import React, {Component} from 'react';
import {Text, StyleSheet, ScrollView, Linking} from 'react-native';

import {TouchableOpacity} from 'react-native';
import Section from './Section';
import {Url_Base, Codi_Entitat} from './Constants';
import VistaWebComponent from './components/VistaWebComponent';

class LoginIB extends Component {
  constructor(props) {
    super(props);
    this.navigate.bind(this);
  }

  componentDidMount() {
    // B. If we are on Android, we immediately call the navigate method passing in the url.
    // If we are on iOS, We add an event listener to call handleOpenUrl when an incoming link is detected.
    console.log('LoginIB => componentDidMount()');

    Linking.addEventListener('url', this.handleOpenURL);
  }

  componentWillUnmount() {
    // C. We delete the Linking listener on componentWillUnmount
    Linking.removeEventListener('url', this.handleOpenURL);
  }

  handleOpenURL = event => {
    // D. When handleOpenURL is called, we pass the event url to the navigate method.
    console.log('LoginIB => handleOpenURL(' + event.url + ')');
    this.navigate(event.url);
  };

  /* Si des d'un navegador atacam a carpetaapp://carpeta/loquesigui s'executarà "navigate = url" */
  navigate = url => {
    // E. We first parse the url to get the id and route name. We then check to see if the route
    // name is equal to "show", and if so we navigate to the People component, passing the id as a prop.

    console.log('LoginIB => navigate(' + url + ')');

    if (!url) {
      return;
    }

    const route = url.replace(/.*?:\/\//g, '');
    console.log('route => ' + route);

    // const id = route.match(/\/([^/]+)\/?$/)[1];
    // const routeName = route.split('/')[0];

    if (url.includes('/show/')) {
      var param = route.split('/').join('_');

      var pos = param.lastIndexOf('_');

      var loginCode = param.substring(pos + 1, param.length);

      console.log('loginCode => ' + loginCode);

      this.props.history.push('/loginibcallbackbrowser/' + loginCode);
    }
  };

  openLink() {
    // FIXME: Canviar per algo de Carpeta FRONT !!!
    Linking.openURL(
      Url_Base + '/carpetafront/public/ea/caib?urlbase=' + encodeURIComponent(Url_Base),
    );
  }

  render() {
    var reactNativeHomepage =
      Url_Base +
      '/carpetafront/public/rnhp/' +
      Codi_Entitat +
      '?urlbase=' +
      encodeURIComponent(Url_Base);

    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
        <Section title="LoginIB: Open External Browser And Return to App">
          <TouchableOpacity onPress={this.openLink} style={styles.borderdotted}>
            <Text style={styles.link}>DO LOGIN</Text>
          </TouchableOpacity>

          <VistaWebComponent url={reactNativeHomepage} debug={true} />
        </Section>
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  borderdotted: {
    borderWidth: 3,
    borderColor: 'red',
    borderStyle: 'dotted',
    borderRadius: 1,
  },
  link: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
});

export default LoginIB;
