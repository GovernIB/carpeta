/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 19-08-2021 12:39:30
 * @modify date 19-08-2021 12:39:30
 * @desc [description]
 * @format
 * @flow strict-local
 */
import { sessionStorage } from "./SessionStorageClass";

import React, { Component } from "react";
import { Text } from "react-native";

import * as Linking from "expo-linking";

import { withTranslation } from "react-i18next";

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = { loadingExpoPushToken: 0 };
    this.checkLoadingExpoPushToken.bind(this);
  }

  componentDidMount() {
    this.checkLoadingExpoPushToken();
  }

  checkLoadingExpoPushToken() {
    console.log(" Entra a checkLoadingExpoPushToken ...");
    if (!sessionStorage.getItem("expoPushToken")) {
      console.log("expoPushToken no definit ...");
      setTimeout(() => {
        this.checkLoadingExpoPushToken();
      }, 1000);
    } else {
      console.log("expoPushToken definit ...");
    }
    var newValue = this.state.loadingExpoPushToken + 1;
    this.setState({ loadingExpoPushToken: newValue });
  }

  render() {
    var expoPushToken = sessionStorage.getItem("expoPushToken");
    if (!expoPushToken) {
      expoPushToken = "Loading ... ";
    }

    return (
      <Text>
        PAGINA DE PRESENTACIÓ - HOME {"\n\n"}Link:{" "}
        {Linking.makeUrl("carpeta/show/LOGINCODE")}
        {"\n\n"}
        Expo Push Token: {expoPushToken}
      </Text>
    );
  }
}

// I18N
export default withTranslation()(Home);
