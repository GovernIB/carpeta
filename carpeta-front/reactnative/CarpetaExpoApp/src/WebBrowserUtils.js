/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 03-10-2022 08:32:49
 * @modify date 03-10-2022 08:32:49
 * @desc [description]
 */

 import * as WebBrowser from 'expo-web-browser';


 export const LinkingOpenURL = async (url) => {

      let result = await WebBrowser.openBrowserAsync(url);
      console.log("WebBrowser.openBrowserAsync() => " + result);

  }



