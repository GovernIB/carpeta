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
      valorDisplay: 'block'
    };
    this.teCertificat = this.teCertificat.bind(this);
    this.pitjarBoto = this.pitjarBoto.bind(this);
    this.canviatIdioma = this.canviatIdioma.bind(this);
    this.mostrar = this.mostrar.bind(this);
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

      if(this.state.urlDescarrega != null){
        return true;
      }
  }

  pitjarBoto() {
    if (this.state.urlDescarrega) {
      window.open(this.state.urlDescarrega, "_blank");
    }
  }

  mostrar(mostrar: boolean){
    
    if(!this.state.urlDescarrega){
      this.setState({
        valorDisplay: mostrar? 'block' : 'none'
      });
    }
    
  }

  render() {

    const t = this.props.i18n.t;


    const styleDesc =  { fontSize: '84%', color: '#666', textAlign: 'center' as 'center' } ;
    const isLoaded = this.state.isLoaded;

    let icon;
    let downloadButton;
    let buttonMessage;
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

      if (this.state.error) {
        if (this.props.noDisponiblesVisible) {
          //Carregat i amb error
          
          downloadButton =(
            <>
              <br />
              <div className="alert alert-warning" role="alert">
                {t("errorCertificat")}
              </div>
            </>
          );
          icon = null;
          

        } else {
          certificateButton = null;
        }
      } else {
        //Carregat sense error
        if (this.state.urlDescarrega) {
          console.log();
          downloadButton = (
            <>
              <br />
              <button className="alert alert-success" role="alert" onClick={this.pitjarBoto}>
                {t("descarregarCertificat")}
              </button>
            </>
          );
          //Te certificat
          icon = <i className="fas fa-certificate" style={{ color: "#328141"}}></i>;

        } else {
          if (this.props.noDisponiblesVisible) {
            //No te certificat
            icon = null;

            downloadButton = (
              <>
                <br />
                <div className="alert alert-warning" role="alert">
                  {t("noCertificat")}
                </div>
              </>
            );


          } else {
            certificateButton = null;
          }
        }
      }
      certificateButton = 
      <div key={"cert"+i} className="col-md-12 border-0 pl-0 pr-0">
              <p className={`card cardAppVerd col-md-12 align-items-lg-center capsaPlugin pt-3 alert`}
                      tabIndex={502+i} aria-labelledby={"nomPseudo"+i}>
                  <h3 className="apartat2 titolPlugin titol h3 alignCenter visioMobil titolPluginApp" id={"nomPseudo"+i}>{this.props.title}</h3>
                  <hr className="visioMobil" color="grey" style={{width: '40%', height: '4px', marginLeft: 'auto', marginRight: 'auto'}}/>
                  <span className="pl-1 alignCenter" style={{position: 'absolute', top: '4px', marginBottom: '4px'}}>{icon}</span>
                  <h3 className="apartat2 titolPlugin h3 alignCenter ocultarMobil" style={{verticalAlign: 'middle', fontWeight: 'bold',lineHeight: 1.1, marginTop: '8px'}} id={"nomPseudo"+i}>{this.props.title}</h3>
                  <span className="card-text alignCenter padPluginApp" style={styleDesc}>{this.props.subtitle}</span>
                  <span style={{position: 'absolute', bottom: '4px'}}>{downloadButton}</span>
              </p>
              </div>
    }

    if(isVisible){
      return <>
      <div
              className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0"
              id="botoCert"
              style={{display: this.state.valorDisplay}}
              >
      {certificateButton}
      </div></>
    }else{
      return null;
    }
  }
}

export default CertificatBoto;

