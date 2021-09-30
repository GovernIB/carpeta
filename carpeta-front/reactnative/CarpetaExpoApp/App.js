/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 20-09-2021 13:54:52
 * @modify date 20-09-2021 13:54:52
 * @desc Punt d'entrada a l'APP i registre de Notificacions
 */

import Constants from "expo-constants";
import React, { Component } from "react";
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context";
import { withTranslation } from "react-i18next";

import { Router } from "./src/components/Routing";
import Index from "./src/Index";
import * as Notifications from "expo-notifications";


Notifications.setNotificationHandler({
  handleNotification: async () => ({
    shouldShowAlert: true,
    shouldPlaySound: false,
    shouldSetBadge: false,
  }),
});

//metodo que genera y registra el token unico
async function registerForPushNotificationsAsync() {
  let token;

  const { status: existingStatus } = await Notifications.getPermissionsAsync();
  let finalStatus = existingStatus;
  if (existingStatus !== "granted") {
    const { status } = await Notifications.requestPermissionsAsync();
    finalStatus = status;
  }

  if (finalStatus !== "granted") {
    // FIXME:
    alert("Failed to get push token for push notification: " + finalStatus);
    return;
  }

  /* if (!Constants.isDevice && Platform.OS === "ios") {
    // FIXME:
    alert("Must use physical device for Push Notifications in IOS");
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
    console.log(token);
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
  }

  componentDidMount() {
    registerForPushNotificationsAsync();

    Notifications.addNotificationReceivedListener(this._handleNotification);

    Notifications.addNotificationResponseReceivedListener(
      this._handleNotificationResponse
    );
  }

  /** Entra aqui quan el dispositiu rep la notificació */
  _handleNotification = (notification) => {
    console.log(" =============================================== ");

    console.log(
      new Date().toLocaleString() +
        " notification.request.content.title => " +
        notification.request.content.title
    );
    console.log(
      new Date().toLocaleString() +
        " notification.request.content.body => " +
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

  /** Entra aqui quan l'usuari pitja la notificació */
  _handleNotificationResponse = (response) => {
    console.log("RESPONSE: " + response);

    console.log(
      new Date().toLocaleString() +
        " response.notification.request.content.title => " +
        response.notification.request.content.title
    );
    console.log(
      new Date().toLocaleString() +
        " response.notification.request.content.body => " +
        response.notification.request.content.body
    );
  };

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
