/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 20-09-2021 13:54:52
 * @modify date 20-09-2021 13:54:52
 * @desc Punt d'entrada a l'APP i registre de Notificacions
 */

import { Linking, Platform } from "react-native";
import Constants from "expo-constants";
import React, { Component } from "react";
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context";
import { withTranslation } from "react-i18next";
import { Router } from "./src/components/Routing";
import { sessionStorageRN } from "./src/SessionStorageClass";
import Index from "./src/Index";
import * as Notifications from "expo-notifications";
import * as ScreenOrientation from "expo-screen-orientation";
import Persistencia from './src/Persistencia';




Notifications.setNotificationHandler({
  handleNotification: async () => ({
    shouldShowAlert: true,
    shouldPlaySound: false,
    shouldSetBadge: false,
  }),
});

//metodo que genera y registra el token unico
async function registerForPushNotificationsAsync() {
  if (Platform.OS === "web") {
    sessionStorageRN.setItem(
      "expoPushToken",
      "Entorn Web no suporta Notificacions"
    );
    return;
  }

  let token;

  const { status: existingStatus } = await Notifications.getPermissionsAsync();
  let finalStatus = existingStatus;
  if (existingStatus !== "granted") {
    const { status } = await Notifications.requestPermissionsAsync();
    finalStatus = status;
  }

  if (finalStatus !== "granted") {
    // FIXME:
    var msg = "Failed to get push token for push notification: " + finalStatus;
    // Apple Store ho dóna com un error o sigui que ho deixam com console.warn !!!!!!
    //alert(msg);
    console.warn(msg);
    sessionStorageRN.setItem("expoPushToken", msg);
    return;
  }

  /* if (!Constants.isDevice && Platform.OS === "ios") {
    // FIXME:
    alert("Must use physical device for Push Notifications in IOS !!!!");
    return;
  }  else  */ {
    var pushToken;
    try {
      pushToken = await Notifications.getExpoPushTokenAsync();
    } catch (e) {
      alert("Error fent Notifications.getExpoPushTokenAsync(): " + e);
      return;
    }
    token = pushToken.data;
    console.log("ExponentPushToken: ]" + token + "[");
    sessionStorageRN.setItem("expoPushToken", token);
    if (Platform.OS === "android") {
      Notifications.setNotificationChannelAsync("default", {
        name: "default",
        importance: Notifications.AndroidImportance.MAX,
        vibrationPattern: [0, 250, 250, 250],
        lightColor: "#FF231F7C",
      });
    }

    // FIXME:
    //alert(token); //mostramos el token
    return token;
  }
}

class App extends Component {
  constructor(props) {
    super(props);

    this.calledWhenDataLoaded = this.calledWhenDataLoaded.bind(this);
    this._handleNotificationResponse = this._handleNotificationResponse.bind(this);
    

    this.storage = new Persistencia();
    // Carrega els valors de forma asincrona i quan ha acabat crida a la funcio passada per paràmetre
    this.storage.load(this.calledWhenDataLoaded);
    this.state = { loadedData: false, urlcarpeta: "" };
  }



  calledWhenDataLoaded(urlcarpeta, codientitat) {
    console.log("ENTRA A DORENDER ");

    this.setState({ loadedData: true, urlcarpeta: urlcarpeta });
  }

  componentDidMount() {
    if (Platform.OS != "web") {
      ScreenOrientation.unlockAsync();
    }

    registerForPushNotificationsAsync();

    Notifications.addNotificationReceivedListener(this._handleNotification);

    Notifications.addNotificationResponseReceivedListener(
      this._handleNotificationResponse
    );
  }

  /** Entra aqui quan el dispositiu rep una notificació PUSH */
  _handleNotification = (notification) => {
    console.log(" =============================================== ");

    console.log(
      new Date().toLocaleString() +
        "REBUDA notification.request.content.title => " +
        notification.request.content.title
    );

    console.log(
      new Date().toLocaleString() +
        "REBUDA notification.request.content.body => " +
        notification.request.content.body
    );

    // JSON.stringify(.notification.request.content.data)
    /*
    title?: string;
    subtitle?: string;
    body?: string;
    data?: { [key: string]: unknown };
    badge?: number;
    sound?
    */

    let localnotificationId = notification.notificationId;
    // Això serveix per indicar d'alguna forma que s'ha processat aquesta notificació.
    setTimeout(function () {
      Notifications.dismissNotificationAsync(localnotificationId);
    }, 5000);
  };

  /** Entra aqui quan l'usuari pitja sobre la notificació PUSH */
  _handleNotificationResponse = (response) => {
    console.log("RESPONSE: " + response);

    console.log(
      new Date().toLocaleString() +
        "CLICK response.notification.request.content.title => " +
        response.notification.request.content.title
    );
    console.log(
      new Date().toLocaleString() +
        "CLICK  response.notification.request.content.body => " +
        response.notification.request.content.body
    );
    console.log(
      new Date().toLocaleString() +
        "CLICK  response.notification.request.content.data => " +
        JSON.stringify(response.notification.request.content.data)
    );

    if (response.notification.request.content.data.action) {

      var action =  response.notification.request.content.data.action;
      console.log(
        new Date().toLocaleString() +
          "CLICK  response.notification.request.content.data.action = " +
          action
      );
      

      if (action == "NONE") {
        //  Do nothing
      } else  if (action == "SHOWPLUGIN") {

         var url = response.notification.request.content.data.url;
         console.log(
          new Date().toLocaleString() +
            "CLICK  response.notification.request.content.data.url = " +
            url
        );

         if (url) {

          console.log(
            new Date().toLocaleString() +
              "CLICK  this.state.urlcarpeta = " +
              this.state.urlcarpeta
          );


          var fullUrl = this.state.urlcarpeta + url;
          console.log(
            new Date().toLocaleString() +
              "CLICK OK this.index=" + this.index
          );

          //Linking.openURL(fullUrl);
          var ispublic = response.notification.request.content.data.ispublic;

          var fullUrl = this.state.urlcarpeta + url;
          console.log(
            new Date().toLocaleString() +
              "CLICK OK this.index.navigateCarpetaWeb(" +
              fullUrl + "," + ispublic + ")"
          );

          
          this.index.navigateCarpetaWeb(fullUrl, ispublic);
         }

      }
      
    } else {
      console.log(
        new Date().toLocaleString() +
          "CLICK  response.notification.request.content.data.action = NO DEFINIT "
      );
    }

   
  };

  render() {
    return (
      <SafeAreaProvider>
        <Router>
          <SafeAreaView>
            <Index indexRef={ref => { this.index = ref;}}/>
          </SafeAreaView>
        </Router>
      </SafeAreaProvider>
    );
  }
}

export default App;
