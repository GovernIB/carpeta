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
import "./i18n";

import { StyleSheet, Text, View, TouchableOpacity, Alert } from "react-native";

import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context";
import { Routes, Route, NativeRouter, Link } from "react-router-native";

import CarpetaWeb from "./CarpetaWeb";
import LoginIBCallBackBrowser from "./LoginIBCallBackBrowser";
import PersistenciaControl from "./PersistenciaControl";
import withRouter from "./withRouter";

class Index extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isHidden: false,
    };

    this.mostrarPaginaDeConfiguracio = this.mostrarPaginaDeConfiguracio.bind(this);
    this.navigateCarpetaWeb = this.navigateCarpetaWeb.bind(this);

    //codiLogin = this.props.match.params.codilogin;
  }

  componentDidMount() {
    const { indexRef } = this.props;
    indexRef(this);
  }

  componentWillUnmount() {
    //const { indexRef } = this.props;
    //indexRef(undefined);
  }

  navigateCarpetaWeb(url: string, ispublic: boolean) {
    // Crida a CarpetaWeb per a que canvii la pàgina web
    console.log(new Date().toLocaleString() + " Index::navigateCarpetaWeb(" + url + "," + ispublic + ")");

    console.log(new Date().toLocaleString() + " Index::navigateCarpetaWeb => this.carpetaWeb = " + this.carpetaWeb);

    // CARPETA FRONT PUBLIC
    if (this.carpetaWeb) {
      //
      // S'hauria de veure si és una URL publica o securitzada ...

      if (ispublic) {
        // Si es un mòdul public de Carpeta Front, llavors s'ha de carregar
        this.carpetaWeb.navigateCarpetaWeb(url, ispublic);
      } else {
        // Si és un mòdul privat llavors s'ha de mostrar un avis indicant que abans s'ha d'autenticar
        console.log("Index::navigateCarpetaWeb => Mostrant un Alert per la pantalla !!!!");
        setTimeout(() => {
          Alert.alert(
            "Requereix Autenticar-se",
            "Per accedir als detalls de la Notificació requereix accedir/loguejar-se a Carpeta."
          );
        }, 11);
      }
    } else {
      console.log(new Date().toLocaleString() + " Index::navigateCarpetaWeb => this.carpetaWeb = ES NULL !!!!!");
    }

    // CARPETA FRONT AUTENTICAT
    if (this.loginIBCallBackBrowser) {
      this.loginIBCallBackBrowser.navigateCarpetaWeb(url, ispublic);
    } else {
      console.log(
        new Date().toLocaleString() + " Index::navigateCarpetaWeb => this.loginIBCallBackBrowser = ES NULL !!!!!"
      );
    }
  }

  mostrarPaginaDeConfiguracio() {
    console.log("Entered 1111111111 !!!!!!!!!!!!!!!!/" + this.state.isHidden);
    this.setState({ isHidden: true });

    //{ (this.state.isHidden == true) && <Navigate to="/config" replace={true} /> }

    //this.props.history.push("/config/");
    this.props.navigate("/config");

    console.log("Entered 22222222 !!!!!!!!!!!!!!!!/" + this.state.isHidden);
  }

  render() {
    return (
      <View style={styles.container}>
        {/* MENU SUPERIOR */}
        <View style={styles.nav} hide={this.state.isHidden}>
          <TouchableOpacity title="X" onPress={() => this.mostrarPaginaDeConfiguracio()}>
            <View style={styles.button}>
              <Text style={styles.buttonText}>X</Text>
            </View>
          </TouchableOpacity>
        </View>

        {/* Contingut de la Pagina */}

        <Routes>
          <Route path="/" element={<CarpetaWeb />} />

          <Route path="/config" element={<PersistenciaControl />} />

          <Route path="/carpeta" element={<CarpetaWeb carpetaWebRef={(ref) => (this.carpetaWeb = ref)} />} />
          <Route
            path="/loginibcallbackbrowser/:codilogin"
            element={
              <LoginIBCallBackBrowser loginIBCallBackBrowserRef={(ref) => (this.loginIBCallBackBrowser = ref)} />
            }
          />
        </Routes>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {},

  nav: {
    flexDirection: "row",
    // justifyContent: "space-around",
    borderStyle: "solid",
    borderColor: "#32814B",
    borderWidth: 0,
    alignSelf: "flex-start",
    position: "absolute",
    zIndex: 10,
    backgroundColor: "#32814B",
    opacity: 0.0,
  },
  navItem: {
    flex: 1,
    fontWeight: "bold",
    alignItems: "center",
  },

  button: {
    borderRadius: 8,
    paddingVertical: 14,
    paddingHorizontal: 9,
    backgroundColor: "#32814B",
    opacity: 0.0,
  },
  buttonText: {
    color: "#32814B",
    fontSize: 8,
    textAlign: "center",
  },
});

export default withTranslation()(withRouter(Index));
