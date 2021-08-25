/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 19-08-2021 09:09:44
 * @modify date 19-08-2021 09:09:44
 * @desc
 */
import React from 'react';
import {StyleSheet, ScrollView} from 'react-native';
import VistaWebComponent from './components/VistaWebComponent';
import {Url_Base} from './Constants';

class LoginIBCallBackBrowser extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  obrirWeb() {
    var loginUrl = Url_Base + '/carpetafront/public/lrn/' + this.props.codilogin;

    console.log('LoginIBCallBackBrowser => Obrint WebView a ' + loginUrl);

    this.setState({url: loginUrl});
  }

  render() {
    console.log('LOGINIB CALLBACK => Render ... CodiLogin =]' + this.props.codilogin + '[');

    var loginUrl = Url_Base + '/carpetafront/public/lrn/' + this.props.codilogin;

    console.log('LOGINIB CALLBACK => Obrint URL ' + loginUrl);

    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={styles.borderdotted}>
        <VistaWebComponent url={loginUrl} debug={true} />
      </ScrollView>
    );
    //}
  }
}

const styles = StyleSheet.create({
  borderdotted: {
    borderWidth: 3,
    borderColor: 'red',
    borderStyle: 'dotted',
    borderRadius: 1,
    //flex: 1,
    //position: 'relative',
    //height: 500,
  },

  fixToText: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
});

export default LoginIBCallBackBrowser;
