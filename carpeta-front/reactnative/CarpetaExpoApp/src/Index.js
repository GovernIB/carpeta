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

import {
  Button,
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  Linking,
  Alert,
} from "react-native";
import { openLink, tryDeepLinking } from "./UtilsWeb";
import { Link, Route, Switch, withRouter } from "./components/Routing";

import Home from "./Home";
import CarpetaWeb from "./CarpetaWeb";
import LoginIBCallBackBrowser from "./LoginIBCallBackBrowser";
import Plataforma from "./Plataforma";
import PersistenciaControl from "./PersistenciaControl";
import ExemplesDeComponents from "./ExemplesDeComponents";
import { Redirect } from "react-router-dom";

class Index extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isHidden: false,
    };

    this.ocultarVista.bind(this);
    this.navigateCarpetaWeb.bind(this);
  }

  componentDidMount() {
    const { indexRef } = this.props;
    indexRef(this);
  }

  componentWillUnmount() {
    //const { indexRef } = this.props;
    //indexRef(undefined);
  }

  navigateCarpetaWeb(url, ispublic) {
    // Crida a CarpetaWeb per a que canvii la pàgina web
    console.log(
      new Date().toLocaleString() + " Index::navigateCarpetaWeb(" + url + "," + ispublic+ ")"
    );

    console.log(
      new Date().toLocaleString() +
        " Index::navigateCarpetaWeb => this.carpetaWeb = " +
        this.carpetaWeb
    );


    // CARPETA FRONT PUBLIC
    if (this.carpetaWeb) {
      //
      // S'hauria de veure si és una URL publica o securitzada ...      
      

      
      if (ispublic) {
          // Si es un mòdul public de Carpeta Front, llavors s'ha de carregar
          this.carpetaWeb.navigateCarpetaWeb(url, ispublic);
      } else {
        // Si és un mòdul privat llavors s'ha de mostrar un avis indicant que abans s'ha d'autenticar
        console.log("Index::navigateCarpetaWeb => Mostrant un Alert per la pantalla !!!!")
        setTimeout( () => { 
           Alert.alert('Requereix Autenticar-se',
            'Per accedir als detalls de la Notificació requereix accedir/loguejar-se a Carpeta.'); }, 11);

          
      }    


    } else {
      console.log(
        new Date().toLocaleString() +
          " Index::navigateCarpetaWeb => this.carpetaWeb = ES NULL !!!!!"
      );
    }

    

      // CARPETA FRONT AUTENTICAT
      if (this.loginIBCallBackBrowser) {
        this.loginIBCallBackBrowser.navigateCarpetaWeb(url, ispublic);
      } else {
        console.log(
          new Date().toLocaleString() +
            " Index::navigateCarpetaWeb => this.loginIBCallBackBrowser = ES NULL !!!!!"
        );
      }
    

  }

  ocultarVista() {
    console.log("Entered 1111111111 !!!!!!!!!!!!!!!!/" + this.state.isHidden);
    //this.setState({ isHidden: true });
    this.props.history.push("/config/");

    console.log("Entered 22222222 !!!!!!!!!!!!!!!!/" + this.state.isHidden);
  }

  render() {
    // console.log('RENDERRRR   '  + this.state.isHidden);
    // let url = "https://www.uib.es";
    // const [statusBarStyle] = 'dark-content';
    //
    // const onOpenLink = async () => {
    //   await this.openLink(url,statusBarStyle);
    // };

    return (
      <View style={styles.container}>
        {/* MENU SUPERIOR */}
        <View style={styles.nav} hide={this.state.isHidden}>
          {/*<TouchableOpacity title='clic' onPress={()=> openLink()}>*/}
          {/*  <View style={styles.navItem}>*/}
          {/*    <Text>Google</Text>*/}
          {/*  </View>*/}
          {/*</TouchableOpacity>*/}
          {/*<Link to="/components" style={styles.navItem}>*/}
          {/*  <Text>Components</Text>*/}
          {/*</Link>*/}
          {/*<Link to="/carpeta" style={styles.navItem}>*/}
          {/*  <Text>Carpeta</Text>*/}
          {/*</Link>
          <Link to="/config" style={styles.navItem}>
            <Text>⁝</Text>
          </Link>
          */}

          <TouchableOpacity title="X" onPress={() => this.ocultarVista()}>
            <View style={styles.button}>
              <Text style={styles.buttonText}>X</Text>
            </View>
          </TouchableOpacity>
          {/*    <Text>XX</Text> <Link to=""  style={styles.navItem}  >
          </Link>*/}
        </View>

        {/* Contingut de la Pagina */}
        <Switch
          onValueChange={(value) => this.setState({ isHidden: value })}
          value={this.state.isHidden}
        >
          <Route
            exact
            path="/"
            // render={(props) => {
            //   return <Home />;
            // }}
//          >
//            <Redirect to="/carpeta" />
//          </Route>
            render={(props) => {
                return <CarpetaWeb {...props} />;
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
              return (
                <CarpetaWeb
                  carpetaWebRef={(ref) => (this.carpetaWeb = ref)}
                  {...props}
                />
              );
            }}
          />
          <Route
            path="/loginibcallbackbrowser/:codilogin"
            render={(props) => {
              return (
                <LoginIBCallBackBrowser
                  {...props}
                  codilogin={props.match.params.codilogin}
                  loginIBCallBackBrowserRef={(ref) => (this.loginIBCallBackBrowser = ref)}
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
