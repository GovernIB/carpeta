/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 09:24:27
 * @modify date 17-08-2021 09:24:27
 * @desc Exemple de Control de Persistència
 */
import React, { Component } from "react";
import { Alert, Button, StyleSheet, Text, TextInput, View, Platform } from "react-native";
import Persistencia from "./Persistencia";
import Constants from "expo-constants";
import { sessionStorageRN } from "./SessionStorageClass";
import ConfigurationProvider from "./ConfigurationProvider";


class PersistenciaControl extends Component {
  constructor(props) {
    console.log("Entra a Constructor");

    super(props);
    this.guardarDades = this.guardarDades.bind(this);
    this.tornarACarpeta = this.tornarACarpeta.bind(this);
    this.calledWhenDataLoaded = this.calledWhenDataLoaded.bind(this);

    this.storage = new Persistencia();

    // Carrega els valors de forma asincrona i quan ha acabat crida a la funcio passada per paràmetre
    this.storage.load(this.calledWhenDataLoaded);

    this.state = { loadedData: false, urlcarpeta: "", codientitat: "" };

    console.log("Surt de Constructor");
  }

  calledWhenDataLoaded(urlcarpeta, codientitat) {
    console.log("ENTRA A DORENDER ");

    if (urlcarpeta === "") {
      urlcarpeta =   ConfigurationProvider.getCarpetaServer();
    }

    if (codientitat === "") {
      codientitat = ConfigurationProvider.getCarpetaEntity();
    }
    this.setState({
      loadedData: true,
      urlcarpeta: urlcarpeta,
      codientitat: codientitat,
    });
  }

  tornarACarpeta() {
    this.props.history.push("/carpeta");
  }

  guardarDades() {
    console.log("GUARDANT " + this.state.urlcarpeta);

    this.storage.save(this.state.urlcarpeta, this.state.codientitat);

    console.log("GUARDAT " + this.state.urlcarpeta);

    Alert.alert("Guardades les dades");
  }

  render() {
    console.log("ENTRA EN RENDER  ");

    if (!this.state.loadedData) {
      return <Text>Loading data ...</Text>;
    }

    let expoPushToken = sessionStorageRN.getItem("expoPushToken");
    if (!expoPushToken) {
      expoPushToken = "== NO DEFINIT ==";
    }

    return (
      <View>
        <Text style={styles.title}>Carpeta APP</Text>
        <Text style={styles.label}>
          Version App: {Constants.manifest.version}{"\n"}
          Expo Push Token: {expoPushToken}{"\n"}
          Platform OS: {Platform.OS}{"\n"}          
          Platform Version: {Platform.Version}{"\n"}
          Revision: {ConfigurationProvider.isRevisionPeriod()?"Yes":"No"}
        </Text>
        <View style={styles.fixToText}>
          <Text style={styles.label}>URL Servidor:</Text>
        </View>
        <View style={styles.fixToText}>
          <TextInput
            onChangeText={(text) => this.setState({ urlcarpeta: text })}
            style={styles.inputtext}
            value={this.state.urlcarpeta}
          />
        </View>
        <View style={styles.fixToText}>
          <Text style={styles.label}>Codi Entitat:</Text>
        </View>
        <View style={styles.fixToText}>
          <TextInput
            onChangeText={(text) => this.setState({ codientitat: text })}
            style={styles.inputtext}
            value={this.state.codientitat}
          />
        </View>
        <View style={styles.fixToBoto}>
          <Button title="Guardar" onPress={this.guardarDades} />
          <Button title="Tornar a Carpeta" onPress={this.tornarACarpeta} />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  fixToText: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  fixToBoto: {
    flexDirection: "row",
    justifyContent: "space-between",
    margin: 20,
  },
  label: {
    padding: 0,
    margin: 10,
  },
  title: {
    padding: 0,
    margin: 10,
    fontSize: 24,
    justifyContent: "center",
    alignItems: "center",
  },
  inputtext: {
    flex: 1,
    color: "#FF0000",
    borderWidth: 1,
    borderColor: "red",
    borderStyle: "solid",
    padding: 0,
    margin: 10,
  },
});

export default PersistenciaControl;
