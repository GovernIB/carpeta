/**
 * @author anadal
 * @create date 2022-08-29 09:08:12
 * @modify date 2022-08-29 09:08:12
 */
import React from "react";
import ReactDOM from "react-dom";
import ElsMeusExpedients from "./TaulaPaginadaTester";
import i18next from "./i18n";


console.log("Current language: " + i18next.language);

if (document.getElementById("root")) {
  ReactDOM.render(
    <React.StrictMode>
      <ElsMeusExpedients />
    </React.StrictMode>,
    document.getElementById("root")
  );
} else {
  console.warn("No he trobat div amb id root !!!");
}



