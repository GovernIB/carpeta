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

import {Button, StyleSheet, Text, View} from "react-native";

import { Link, Route, Switch, withRouter } from "./components/Routing";

import Home from "./Home";
import CarpetaWeb from "./CarpetaWeb";
import LoginIBCallBackBrowser from "./LoginIBCallBackBrowser";
import Plataforma from "./Plataforma";
import PersistenciaControl from "./PersistenciaControl";
import ExemplesDeComponents from "./ExemplesDeComponents";
import {Redirect} from "react-router-dom";

class Index extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isHidden: false,
    };
    this.ocultarVista.bind(this);
  }


  ocultarVista() {
     console.log('Entered 1111111111 !!!!!!!!!!!!!!!!/' + this.state.isHidden);
    //this.setState({ isHidden: true });
    this.props.history.push('/config/');

    console.log('Entered 22222222 !!!!!!!!!!!!!!!!/'  + this.state.isHidden);

  }

  render() {

    console.log('RENDERRRR   '  + this.state.isHidden);
    return (

      <View style={styles.container}>
        {/* MENU SUPERIOR */}
        <View style={styles.nav} hide={this.state.isHidden}>
          {/*<Link to="/" style={styles.navItem}>*/}
          {/*  <Text>Home</Text>*/}
          {/*</Link>*/}
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

              <Button title='⁝' onPress={()=>this.ocultarVista()} color="#32814B"/>
            {/*    <Text>XX</Text> <Link to=""  style={styles.navItem}  >
          </Link>*/ }


        </View>

        {/* Contingut de la Pagina */}
        <Switch onValueChange={value => this.setState({ isHidden: value })}  value={this.state.isHidden}>
          <Route
            exact
            path="/"
            // render={(props) => {
            //   return <Home />;
            // }}
            >
            <Redirect to="/carpeta" />
          </Route>


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
    // justifyContent: "space-around",
    // borderStyle: "solid",
    // borderColor: "black",
    // borderWidth: 0,
    // paddingTop: 1,
    // paddingBottom: 1,
    // float: "right"
    // justifyContent: "flex-end",
    alignSelf: 'flex-end',
    position: "absolute",
    zIndex: 10,
    backgroundColor: "#32814B",
  },
  navItem: {
    flex: 1,
    fontWeight: "bold",
    alignItems: "center",
    // padding: 1,
    // margin: 1,
    // borderStyle: "solid",
    // borderColor: "black",
    // borderWidth: 0,
    // borderRadius: 3,
  },
});

export default withTranslation()(withRouter(Index));
