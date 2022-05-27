import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';

/**
 *  @author jagarcia
 */

class ReprendreTramit extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
            clau: null,
            error: false
        };

        this.handleChangeClau = this.handleChangeClau.bind(this);
        this.handleSubmitClau = this.handleSubmitClau.bind(this);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

    }

    canviatIdioma(lng) {
      //  console.log(" CANVIAT IDIOMA EN ReprendreTramit A ]" + lng+ "[");
      this.componentDidMount();
    }
	
    handleChangeClau(e) {
        this.setState({
            ...this.state, 
            clau: e.target.value
        });
    }

    async handleSubmitClau(val) {

        const url2 = this.props.pathtoservei;
        await axios.get(url2, {params:{clau: val}}).then( (response) => {

            this.setState({
                ...this.state,
                error: false
            });

            const url = response.data.url;
            if (url.length > 0 )
                window.open(url, "_blank");
            else{
                this.setState({
                    ...this.state,
                    error: true
                });
            }

        }).catch( error => {
            console.log(error);
            this.setState({
                ...this.state,
                error: true
            });
        } );
    }

    componentDidMount() {
        this.setState({
            ...this.state, 
            isLoaded: true
        }); 

        const { t } = this.props;
        
        let content;

            content = <>
                <div className="form-group">
                    <label for="clau">{t('reprendreClau')}</label>
                    <input type="text" className="form-control col-md-6" id="clau" name="clau" value={this.state.clau} onChange={(e)=>{this.handleChangeClau(e);}} />
                    <input type="button" className="btn btn-primary carpeta-btn mt-2" onClick={() => {this.handleSubmitClau(this.state.clau)}} value={t('reprendreContinuar')} />
                </div>
            </>
        
    }

    componentDidUpdate(){

        // console.log("ComponentDidUpdate ReprendreTramit: " , i18n.language);

    }

    render() {

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;

        if (!isLoaded) {
           
             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {

            content = <>
                <div className="form-group">
                    <label for="clau">{i18n.t('reprendreClau')}</label>
                    <input type="text" className="form-control columnReprendre" id="clau" name="clau" value={this.state.clau} onChange={(e)=>{this.handleChangeClau(e);}} />
                    <input type="button" className="btn btn-primary carpeta-btn mt-2" onClick={() => {this.handleSubmitClau(this.state.clau)}} value={i18n.t('reprendreContinuar')} />
                </div>
            </>
            
        }

        
        return (<>
                    <div className="titolPaginaApp visioMobil">
                        {this.props.titles[i18n.language]}
                    </div>
                    <div className="infoNoMenu">
                        <h2 className="titol h2 ocultarMobil">{this.props.titles[i18n.language]}</h2>
                        <div className="col-md-12 border-0 float-left p-0">
                            <p className="lh15 ocultarMobil">{this.props.subtitles[i18n.language]} </p>
                            <div className="infoNoMenu">
                                <div className="col-md-12 border-0 float-left p-0">
                                    {this.state.error && <div className="alert alert-danger hide" role="alert">{i18n.t('reprendreNoClau')}</div>}
                                    {content}
                                </div>
                            </div>
                        </div>
                        <div className="col-md-12 border-0 float-left p-0" id="botoTornarDiscapacidad" style={{ marginTop: '20px' }}>
                            <button type="button" data-toggle="modal" onClick={() => {
                                window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                            }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarDiscapacidad">{i18n.t('reprendreTornar')}</button>
                        </div>
                    </div>
                </>
            );
            
    }
}

export default withTranslation()(ReprendreTramit);