/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */

import React from "react";

interface CarpetaFormulariDeFiltreProps {
  children: JSX.Element;
  handleSubmitSearcher(event: any): boolean;
  i18n:any;
}

class CarpetaFormulariDeFiltre extends React.Component<CarpetaFormulariDeFiltreProps> {
  constructor(props: CarpetaFormulariDeFiltreProps) {
    super(props);

    console.log("CarpetaFormulariDeFiltre::CONSTRUCTOR => Inici ");

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
  }

  private handleSubmitSearcher(e: any): boolean {
    if (this.props.handleSubmitSearcher) {
      return this.props.handleSubmitSearcher(e);
    }
    return true;
  }

  public setError() {
    {
      /* XYZ ZZZ canviar-ho a id="errorMsg" */
    }
  }

  render() {
    return (
      <form id="formulariDeFiltre" style={{ marginBottom: "20px" }}>
        <div style={{ width: "95%", paddingLeft: "0px", margin: "0px" }} className="ampleTotalApp container">
          <div className="row">{this.props.children}</div>
          <div className="col-md-3 pl-3 row botoFormApp" style={{ zIndex: "4" }}>
            <button
              type="submit"
              className="btn btn-primary carpeta-btn mt-2"
              onClick={(e) => {
                this.handleSubmitSearcher(e);
              }}
              tabIndex={505}
            >
              {this.props.i18n.t("carpeta_buscar")}
            </button>
          </div>
        </div>
      </form>
    );
  }
}

export default CarpetaFormulariDeFiltre;
