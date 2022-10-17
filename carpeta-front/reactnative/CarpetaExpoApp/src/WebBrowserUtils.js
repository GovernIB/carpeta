/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 03-10-2022 08:32:49
 * @modify date 03-10-2022 08:32:49
 * @desc [description]
 */

import * as WebBrowser from "expo-web-browser";
import * as Linking from "expo-linking";

export const LinkingOpenURL = async (url, loadRootPage) => {
  let _handleRedirect = (event) => {
    console.log("  \n\n EVENT WEBBROWSER: " + event.url + "\n\n");
  };

  let eventToListen = Linking.addEventListener("url", _handleRedirect);

  global.isPostLoginCarpeta = false;

  let result;

  if (Platform.OS === "ios") {
    result = await WebBrowser.openBrowserAsync(url);

    if (result.type == "cancel") {
      loadRootPage();
    }
  } else {
    let options = {}; //showTitle: false };

    // redirectURL => "carpetaapp://carpeta/userclosewebbrowser";
    let redirectUrl = Linking.createURL("/userclosewebbrowser");
    console.log(
      "\n\nWebBrowser.openBrowserAsync() => Redirect:  " + redirectUrl + "\n\n"
    );

    result = await WebBrowser.openAuthSessionAsync(url, redirectUrl, options);

    setTimeout(
      function (loadRootPage2) {
        console.log(
          "TIMEOUT  => " +
            global.isPostLoginCarpeta +
            " # loadRootPage => " +
            loadRootPage2
        );

        if (global.isPostLoginCarpeta == false) {
          console.log("TIMEOUT  => Cridant a CARPETA ROOT");
          loadRootPage2();
        }
      },
      2500,
      loadRootPage
    );
  }

  console.log("WebBrowser.openAuthSessionAsync() => " + result);
  console.log("WebBrowser.openAuthSessionAsync() => TYPE " + result.type);
  console.log("WebBrowser.openAuthSessionAsync() => URL " + result.url);

  console.log(new Date().toLocaleString() + " LinkingOpenURL::Sortim ..");

  // Linking.removeEventListener("url", _handleRedirect);

  eventToListen.remove();
};
