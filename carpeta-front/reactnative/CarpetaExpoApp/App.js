/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 20-09-2021 13:54:52
 * @modify date 20-09-2021 13:54:52
 * @desc Punt d'entrada a l'APP
 */

import React, { Component } from "react";
import { SafeAreaProvider ,SafeAreaView} from "react-native-safe-area-context";
import { withTranslation } from "react-i18next";

import { Router } from "./src/components/Routing";
import Index from "./src/Index";


class App extends Component {
  

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <SafeAreaProvider>
       
        <Router>
          <SafeAreaView>
            <Index />
          </SafeAreaView>
        </Router>
      </SafeAreaProvider>
    );
  }
}

export default App;
