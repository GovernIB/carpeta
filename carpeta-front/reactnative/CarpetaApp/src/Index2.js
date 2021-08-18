/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 14:54:50
 * @modify date 17-08-2021 14:54:50
 * @desc [description]
 */

import React, {Component} from 'react';

// I18N
import {withTranslation} from 'react-i18next';

import {StyleSheet, Text, View} from 'react-native';
import {Link, Route, Switch, withRouter} from './components/Routing';

import Home from './Home';
import TraduccionsAndPlataforma from './TraduccionsAndPlataforma.js';
import UtilsWeb from './UtilsWeb.js';

// FIXME: Canviar nom per Index

class Index2 extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <View style={styles.container}>
        {/* MENU SUPERIOR */}
        <View style={styles.nav}>
          <Link to="/" style={styles.navItem}>
            <Text>Home</Text>
          </Link>
          <Link to="/utilsweb" style={styles.navItem}>
            <Text>Utilitats Web</Text>
          </Link>
          <Link to="/traduccionsplataforma" style={styles.navItem}>
            <Text>Traduccions i plataforma</Text>
          </Link>
        </View>

        {/* Contingut de la Pagina */}
        <Switch>
          <Route
            exact
            path="/"
            render={props => {
              return <Home />;
            }}
          />
          <Route
            path="/traduccionsplataforma"
            render={props => {
              return <TraduccionsAndPlataforma />;
            }}
          />
          <Route
            path="/utilsweb"
            render={props => {
              return <UtilsWeb />;
            }}
          />
        </Switch>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    marginTop: 25,
    padding: 10,
  },

  nav: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    borderStyle: 'solid',
    borderColor: 'black',
    borderWidth: 1,
  },
  navItem: {
    flex: 1,
    fontWeight: 'bold',
    alignItems: 'center',
    padding: 5,
    margin: 5,
    borderStyle: 'solid',
    borderColor: 'red',
    borderWidth: 2,
    borderRadius: 10,
  },
});

export default withTranslation()(withRouter(Index2));
