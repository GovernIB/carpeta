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

class LoginIBCallBackBrowser extends React.Component {
  constructor(props) {
    super(props);
    this.calledWhenDataLoaded = this.calledWhenDataLoaded.bind(this);

    this.storage = new Persistencia();
    // Carrega els valors de forma asincrona i quan ha acabat crida a la funcio passada per paràmetre
    this.storage.load(this.calledWhenDataLoaded);

    this.state = {loadedData: false, urlcarpeta: ''};
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
