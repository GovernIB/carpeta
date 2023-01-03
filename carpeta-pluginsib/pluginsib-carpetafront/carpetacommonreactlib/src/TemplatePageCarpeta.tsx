/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */

import React from "react";
import initTranslation from "./InitTranslation";

interface TemplatePageCarpetaProps {
  titles: any;
  subtitles: any;
  children: JSX.Element;
  i18n: any;
}

class TemplatePageCarpeta extends React.Component<TemplatePageCarpetaProps> {
  constructor(props: TemplatePageCarpetaProps) {
    super(props);
    console.log("  CONSTRUCTOR TemplatePageCarpeta !!!!!");

    initTranslation(this.props.i18n);
  }

  render() {
    console.log("  RENDER TemplatePageCarpeta !!!!!");

    let i18n = this.props.i18n;

    let language = i18n.language;

    return (
      <>
        <div className="titolPaginaApp visioMobil">{this.props.titles[language]}</div>
        <div className="infoNoMenu">
          <h2 className="titol h2 ocultarMobil">{this.props.titles[language]}</h2>
          <div className="col-md-12 border-0 float-left p-0">
            <p className="lh15 ocultarMobil">{this.props.subtitles[language]} </p>

            <div
              className="tab-pane fade show active"
              id="elsmeusexpedients"
              role="tabpanel"
              aria-labelledby="home-tab"
            >
              {this.props.children}
              <div className="col-md-12 border-0 float-left p-0" id="botoTornarDadesP" style={{ marginTop: "20px" }}>
                <button
                  type="button"
                  data-toggle="modal"
                  onClick={() => {
                    window.location.href = sessionStorage.getItem("pagTornar") as string;
                    sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath") as string);
                  }}
                  className="botoSuport botoTornauApp"
                  tabIndex={520}
                  aria-labelledby="botoTornarDadesP"
                >
                  {i18n.t("tornar")}
                </button>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}

export default TemplatePageCarpeta;
