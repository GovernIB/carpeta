import React, { Component } from "react";
import axios from "axios";
import i18n from "./i18n";

/**
 *  @author fbosch
 */

type CertificatState = {
  isLoaded: boolean;
  urlDescarrega: string | null;
  error: string | null;
};

class Certificats extends React.Component<
  CertificatBotoProperties,
  CertificatState
> {
  constructor(props: CertificatBotoProperties) {
    super(props);

    this.state = {
      isLoaded: false,
      urlDescarrega: null,
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
    let isVisible: boolean = true;
    console.log("Render 1");


    

    if (!isLoaded) {
      console.log("Render 2");
      icon =(<div  id="carregant" className="loader centrat" style={{ scale: 0.5, height: "20px", width: "20px" }}></div>);
      /*icon = (
        <div
          id="carregant"
          className="loader-container centrat "
          style={{ height: "30px", width: "30px" }}
        >
          <div className="loader" style={{ height: "100%", width: "100%" }}></div>
        </div>
      );*/
      
    } else {
      console.log("Render 3");
      if (this.state.error) {
        icon = (
          <>
            <br />
            <div className="alert alert-danger" role="alert">
              {this.state.error}
            </div>
          </>
        );
      } else {
        console.log("Render 4");
        if (this.state.urlDescarrega) {
          let urlDescarrega = this.state.urlDescarrega;
          icon = <i className="fas fa-file-download"></i>;
        } else {
          console.log("No te certificats per descarregar");
          isVisible = false;
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
            style={{ marginTop: "20px" }}
          >
            <button
              type="button"
              data-toggle="modal"
              onClick={this.pitjarBoto}
              className=" btn btn-warning"
              tabIndex={520}
              aria-labelledby="botoCert"
            >
              {this.props.title + " "}  {icon}
            </button>
          </div>
        )}
      </>
    );
  }
}

export default Certificats;
