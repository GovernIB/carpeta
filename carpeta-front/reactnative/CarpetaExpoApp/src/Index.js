/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 14:54:50
 * @modify date 17-08-2021 14:54:50
 * @desc [description]
 */

import React, { Component } from "react";

// I18N
import { withTranslation } from "react-i18next";

import { StyleSheet, Text, View } from "react-native";

import { Link, Route, Switch, withRouter } from "./components/Routing";

import Home from "./Home";
import CarpetaWeb from "./CarpetaWeb";
import LoginIBCallBackBrowser from "./LoginIBCallBackBrowser";
import Plataforma from "./Plataforma";
import PersistenciaControl from "./PersistenciaControl";
import ExemplesDeComponents from "./ExemplesDeComponents";

class Index extends Component {
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
          <Link to="/components" style={styles.navItem}>
            <Text>Components</Text>
          </Link>
          <Link to="/carpeta" style={styles.navItem}>
            <Text>Carpeta</Text>
          </Link>
          <Link to="/config" style={styles.navItem}>
            <Text>Config.</Text>
          </Link>
        </View>

        {/* Contingut de la Pagina */}
        <Switch>
          <Route
            exact
            path="/"
            render={(props) => {
              return <Home />;
            }}
          />
          <Route
            path="/config"
            render={(props) => {
              return <PersistenciaControl {...props} />;
            }}
          />

          <Route path="/components" component={ExemplesDeComponents} />

          <Route
            path="/carpeta"
            render={(props) => {
              return <CarpetaWeb {...props} />;
            }}
          />
          <Route
            path="/loginibcallbackbrowser/:codilogin"
            render={(props) => {
              return (
                <LoginIBCallBackBrowser
                  {...props}
                  codilogin={props.match.params.codilogin}
                />
              );
            }}
          />
        </Switch>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
  },

  nav: {
    flexDirection: "row",
    justifyContent: "space-around",
    borderStyle: "solid",
    borderColor: "black",
    borderWidth: 1,
    paddingTop: 5,
    paddingBottom: 5,
  },
  navItem: {
    flex: 1,
    fontWeight: "bold",
    alignItems: "center",
    padding: 2,
    margin: 2,
    borderStyle: "solid",
    borderColor: "red",
    borderWidth: 2,
    borderRadius: 10,
  },
});

export default withTranslation()(withRouter(Index));
