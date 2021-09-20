/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 19-08-2021 12:39:30
 * @modify date 19-08-2021 12:39:30
 * @desc [description]
 * @format
 * @flow strict-local
 */

import React, { Component } from "react";
import { Text } from "react-native";

import * as Linking from "expo-linking";

import { withTranslation } from "react-i18next";

class Home extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Text>
        PAGINA DE PRESENTACIÓ - HOME {"\n\n"}Link:{" "}
        {Linking.makeUrl("carpeta/show/LOGINCODE")}
      </Text>
    );
  }
}

// I18N
export default withTranslation()(Home);
