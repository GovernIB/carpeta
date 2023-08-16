import React, { Component } from "react";
import axios from "axios";
import i18n from "./i18n";

/**
 *  @author fbosch
 */

type CertificatBotoState = {
  isLoaded: boolean;
  urlDescarrega: string | null;
  isVisible: boolean;
  error: string | null;
};

class CertificatBoto extends React.Component<
  CertificatBotoProperties,
  CertificatBotoState
> {
  constructor(props: CertificatBotoProperties) {
    super(props);

    this.state = {
      isLoaded: false,
      urlDescarrega: null,
      isVisible: true,
      error: null,
    };
    this.teCertificat = this.teCertificat.bind(this);
    this.pitjarBoto = this.pitjarBoto.bind(this);
    this.canviatIdioma = this.canviatIdioma.bind(this);
    i18n.on("languageChanged", this.canviatIdioma);
  }

  componentDidMount() {
    this.teCertificat();
  }

  canviatIdioma(_lng: string) {
    this.forceUpdate();
  }

  async teCertificat() {
    const url2 = this.props.pathToServeiTe;
    axios
      .get(url2)
      .then((res) => {
        console.log("XYZ Entra a TeCertificat() Res.Data= " + res.data);
        this.setState({
          ...this.state,
          isLoaded: true,
          urlDescarrega: res.data,
          error: null,
        });
      })
      .catch((error) => {
        console.log(JSON.stringify(error));
        if (error.response) {
          console.log("error.response.data: " + error.response.data);
          console.log("error.response.status: " + error.response.status);
          console.log("error.response.headers: " + error.response.headers);
          this.setState({
            ...this.state,
            isLoaded: true,
            urlDescarrega: null,
            error: error.response.data,
          });
        } else {
          this.setState({
            ...this.state,
            isLoaded: true,
            urlDescarrega: null,
            error: JSON.stringify(error),
          });
        }
      });
  }

  pitjarBoto() {
    if (this.state.urlDescarrega) {
      window.open(this.state.urlDescarrega, "_blank");
    }
  }

  render() {
    const isLoaded = this.state.isLoaded;

    let icon;
    let errorMessage;
    let certificateButton;
    let isVisible: boolean = true;
    console.log("Render 1");

    if (!isLoaded) {
      //Carregant
      console.log("Render 2");
      icon = (<div id="carregant" className="loader centrat" style={{ scale: 0.5, height: "20px", width: "20px" }}></div>);
      certificateButton =

      <p data-toggle="modal" 
            style={{ color: "grey", marginTop: "20px" }} 
            aria-labelledby="botoCert"
            >{this.props.title + " "}  {icon}
            </p>
        /*<button
          type="button"
          data-toggle="modal"
          onClick={this.pitjarBoto}
          className=" btn btn-warning"
          tabIndex={520}
          aria-labelledby="botoCert"
        >
          {this.props.title + " "}  {icon}
        </button>*/

    } else {
      //Carregat
      console.log("Render 3");


      if (this.state.error) {
        if (this.props.noDisponiblesVisible) {
          //Carregat i amb error
          errorMessage = (
            <>
              <br />
              <div className="alert alert-danger" role="alert">
                {this.state.error}
              </div>
            </>
          );
          icon = <i className="far fa-time"></i>;
          certificateButton =
          <div className="alert alert-danger">
            <a data-toggle="modal" 
            onClick={this.pitjarBoto} 
            href="javascript:void(0);" 
            style={{ color: "red", marginTop: "20px" }} 
            aria-labelledby="botoCert"
            >{this.props.title + " "}  {errorMessage}
            </a>
          </div>
          /*<button
            type="button"
            data-toggle="modal"
            onClick={this.pitjarBoto}
            className=" btn btn-danger"
            tabIndex={520}
            aria-labelledby="botoCert"
          >
            {this.props.title + " "}  {errorMessage}
          </button>*/
        } else {
          certificateButton = null;
        }
      } else {
        //Carregat sense error
        console.log("Render 4");
        if (this.state.urlDescarrega) {
          //Te certificat
          icon = <i className="fas fa-file-download"></i>;
          certificateButton =
          <div className="alert alert-success">
            <a 
            data-toggle="modal" 
            onClick={this.pitjarBoto}
            href="javascript:void(0);"
            style={{ color: "green", marginTop: "20px" }}
            aria-labelledby="botoCert">{this.props.title + " "}  {icon}</a>
            </div>

          /*<button
            type="button"
            data-toggle="modal"
            onClick={this.pitjarBoto}
            className=" btn btn-success"
            tabIndex={520}
            aria-labelledby="botoCert"
          >
            {this.props.title + " "}  {icon}
          </button>*/

        } else {
          if (this.props.noDisponiblesVisible) {
            //No te certificat
            icon = <i className="far fa-time"></i>;
            certificateButton =
            <div className="alert alert-warning">
              <a data-toggle="modal" 
              style={{ color: "grey", marginTop: "20px"}} 
              aria-labelledby="botoCert"
              >{this.props.title + ": No te certificat "} {icon}
              </a>
              </div>


            /*<button
              type="button"
              data-toggle="modal"
              onClick={this.pitjarBoto}
              className=" btn btn-warning"
              tabIndex={520}
              aria-labelledby="botoCert"
            >
              {this.props.title + ": No te certificat "} {icon}
            </button>*/

          } else {
            certificateButton = null;
          }
        }
      }



    }

    console.log("Render Final");

    return (
      <>
        {isVisible && (
          <div
            className="col-md-12 border-0 float-left p-0"
            id="botoCert"
          >
            {certificateButton}
          </div>
        )}
      </>
    );
  }
}

export default CertificatBoto;
