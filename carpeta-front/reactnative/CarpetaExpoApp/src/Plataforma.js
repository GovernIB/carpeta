/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 09:57:24
 * @modify date 17-08-2021 09:57:24
 * @desc Comandes per Plataforma
 */
import React, { Component } from "react";

// I18N
import { withTranslation } from "react-i18next";

import { Text, StyleSheet, Platform, ScrollView } from "react-native";
import HolaCaracola from "./components/HolaCaracola";
import Section from "./Section";

class Plataforma extends Component {
  render() {
    /*
      <ScrollView contentInsetAdjustmentBehavior="automatic" style={{}}>
      
      </ScrollView>
        */
    return (
      <Section title="Plataforma">
        <Text style={styles.plataforma}>
          Platform.OS: {Platform.OS}
          {"\n"}
          Platform.Version: {Platform.Version}
        </Text>
        <Text>
          {"\n\n"}Exemple de Càrrega de classes segons Plataforma =>{"\n"}
          <HolaCaracola />
        </Text>
      </Section>
    );
  }
}

const styles = StyleSheet.create({
  plataforma: {
    flex: 1,
    ...Platform.select({
      ios: { backgroundColor: "blue" },
      android: { backgroundColor: "green" },
      web: { backgroundColor: "red" },
      // other platforms, web for example
      default: { backgroundColor: "yellow" },
    }),
  },
});

// I18N
export default withTranslation()(Plataforma);
