import React, { Component } from "react";
import axios from "axios";

/**
 *  @author fbosch
 */

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
    this.props.i18n.on("languageChanged", this.canviatIdioma);
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

    const t = this.props.i18n.t;


    const styleDesc =  { fontSize: '84%', color: '#666', textAlign: 'center' as 'center' } ;
    const isLoaded = this.state.isLoaded;

    let icon;
    let errorMessage;
    let warnMessage
    let botoDescarrega;
    let certificateButton;
    let isVisible: boolean = true;
    console.log("Render 1");


    let i = this.props.pluginNumber;

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
        
    } else {
      //Carregat
      console.log("Render 3");


      if (this.state.error) {
        if (this.props.noDisponiblesVisible) {
          //Carregat i amb error
          errorMessage = (
            <>
              <br />
              <div className="alert alert-warning" role="alert">
                S'ha produit un error obtenint el certificat.
              </div>
            </>
          );
          icon = <i className="far fa-time"></i>;
          certificateButton =
          
          <div
              className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0"
              id="botoCert"
              >
          <div key={"cert"+i} className="col-md-12 border-0 pl-0 pr-0">
          <div className={`card cardAppVerd col-md-12 align-items-lg-center capsaPlugin pt-3 alert`}
                  tabIndex={502+i} aria-labelledby={"nomPseudo"+i}>
              <h3 className="apartat2 titolPlugin titol h3 alignCenter visioMobil titolPluginApp" id={"nomPseudo"+i}>{this.props.title}</h3>
              <hr className="visioMobil" color="red" style={{width: '40%', height: '4px', marginLeft: 'auto', marginRight: 'auto'}}/>
              <span className="card-title titol pl-1 h3 alignCenter">{icon}</span>
              <h3 className="apartat2 titolPlugin titol h3 alignCenter ocultarMobil" id={"nomPseudo"+i}>{this.props.title}</h3>
              <span className="card-text alignCenter padPluginApp"
                  style={styleDesc}>{this.props.subtitle}
                  
                  {errorMessage}</span>
          </div>
          </div>
          </div>

        } else {
          certificateButton = null;
        }
      } else {
        //Carregat sense error
        console.log("Render 4");
        if (this.state.urlDescarrega) {

          botoDescarrega = (
            <>
              <br />
              <button className="alert alert-success" role="alert" onClick={this.pitjarBoto}>
                {t("descarregarCertificat")}
              </button>
            </>
          );
          //Te certificat
          icon = <i className="fas fa-file-download"></i>;
          
          certificateButton =
          /*<div className="alert alert-success">
            <a 
            data-toggle="modal" 
            onClick={this.pitjarBoto}
            href="javascript:void(0);"
            style={{ color: "green", marginTop: "20px" }}
            aria-labelledby="botoCert">{this.props.title + " "}  {icon}</a>
            </div>
            */

            <div
              className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0"
              id="botoCert"
              >
            <div key={"cert"+i} className="col-md-12 border-0 pl-0 pr-0">
            <div className={`card cardAppVerd col-md-12 align-items-lg-center capsaPlugin pt-3 alert`}
                    tabIndex={502+i} aria-labelledby={"nomPseudo"+i}>
                <h3 className="apartat2 titolPlugin titol h3 alignCenter visioMobil titolPluginApp" id={"nomPseudo"+i}>{this.props.title}</h3>
                <hr className="visioMobil" color="#c30045" style={{width: '40%', height: '4px', marginLeft: 'auto', marginRight: 'auto'}}/>
                <span className="card-title titol pl-1 h3 alignCenter">{icon}</span>
                <h3 className="apartat2 titolPlugin titol h3 alignCenter ocultarMobil" id={"nomPseudo"+i}>{this.props.title}</h3>
                <span className="card-text alignCenter padPluginApp"
                    style={styleDesc}>{this.props.subtitle}
                    
                    {botoDescarrega}</span>
                    
            </div>
            </div>
            </div>

        } else {
          if (this.props.noDisponiblesVisible) {
            //No te certificat
            icon = <i className="far fa-time"></i>;

            warnMessage = (
              <>
                <br />
                <div className="alert alert-warning" role="alert">
                  No s'ha pogut obtenir el certificat
                </div>
              </>
            );


            certificateButton =
            <div
              className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0"
              id="botoCert"
              >
              <div key={"cert"+i} className="col-md-12 border-0 pl-0 pr-0">
              <button className={`card cardAppVerd col-md-12 align-items-lg-center capsaPlugin pt-3 alert`}
                      tabIndex={502+i} aria-labelledby={"nomPseudo"+i}>
                  <h3 className="apartat2 titolPlugin titol h3 alignCenter visioMobil titolPluginApp" id={"nomPseudo"+i}>{this.props.title}</h3>
                  <hr className="visioMobil" color="grey" style={{width: '40%', height: '4px', marginLeft: 'auto', marginRight: 'auto'}}/>
                  <span className="card-title titol pl-1 h3 alignCenter">{icon}</span>
                  <h3 className="apartat2 titolPlugin titol h3 alignCenter ocultarMobil" id={"nomPseudo"+i}>{this.props.title}</h3>
                  <span className="card-text alignCenter padPluginApp"
                      style={styleDesc}>{this.props.subtitle}:
                      
                      {warnMessage}</span>
              </button>
              </div>
              </div>

          } else {
            certificateButton = null;
          }
        }
      }



    }

    if(isVisible){
      return <>{certificateButton}</>
    }else{
      return null;
    }
  }
}

export default CertificatBoto;

