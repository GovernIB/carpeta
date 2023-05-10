import React from 'react';
import {withTranslation, WithTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';

/**
 *  @author jagarcia
 */

interface ReprendreTramitProps extends WithTranslation{
    pathtoservei: string;
    titles: any;
    subtitles: any;
}

interface ReprendreTramitState {
    isLoaded: boolean;
    error: boolean;
}

class ReprendreTramit extends React.Component<ReprendreTramitProps, ReprendreTramitState> {

    private clau: string|undefined;


    constructor(props: ReprendreTramitProps) {
        super(props);

        this.state = {
            isLoaded: true,
            error: false
        }
        this.clau = undefined;
        

        this.handleChangeClau = this.handleChangeClau.bind(this);
        this.handleSubmitClau = this.handleSubmitClau.bind(this);

        this.handleSubmitClauSync = this.handleSubmitClauSync.bind(this);
        

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng:string) {
      //  console.log(" CANVIAT IDIOMA EN ReprendreTramit A ]" + lng+ "[");
        this.forceUpdate();
    }
	
    handleChangeClau(e: any) {
        
        this.clau = e.target.value;
    }

    handleSubmitClauSync(){
        
        this.handleSubmitClau(this.clau != undefined ? this.clau : "");
        this.setState({
            ...this.state,
            isLoaded: false
        });
    }

    async handleSubmitClau(val: string) {

        const url2 = this.props.pathtoservei;
        await axios.get(url2, {params:{clau: val}}).then( (response) => {

            const url = response.data.url;
            if (url.length > 0 ){
                window.open(url, "_blank");
                this.setState({
                    isLoaded: true,
                    error: false
                });
            }else{
                this.setState({
                    isLoaded: true,
                    error: true
                });
            }

        }).catch( error => {
            console.log(error);
            this.setState({
                isLoaded: true,
                error: true
            });
        } );

    }

    componentDidMount() {
        
    }

    componentDidUpdate(){
        // console.log("ComponentDidUpdate ReprendreTramit: " , i18n.language);
    }

    render() {

        let isLoaded = this.state.isLoaded;

        const t = this.props.i18n.t;
        let content;

        let reprendreContinuar : string = t('reprendreContinuar');

        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
            content = <>
                <div className="form-group">
                    <label htmlFor="clau">{i18n.t('reprendreClau')}</label>
                    <input type="text" className="form-control columnReprendre" id="clau" name="clau" value={this.clau} onChange={(e)=>{this.handleChangeClau(e);}} />
                    <input type="button" className="btn btn-primary carpeta-btn mt-2" onClick={() => {this.handleSubmitClauSync()}} value={reprendreContinuar} />
                </div>
            </>
            
        }

        let pagTornar: string|null = sessionStorage.getItem("pagTornar");
        let contextPath: string|null = sessionStorage.getItem("contextPath");
        
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
                                window.location.href = pagTornar != null ? pagTornar : "" ; sessionStorage.setItem("pagTornar", contextPath != null ? contextPath : "")
                            }} className="botoSuport botoTornauApp" tabIndex={520} aria-labelledby="botoTornarDiscapacidad">{i18n.t('reprendreTornar')}</button>
                        </div>
                    </div>
                </>
            );
            
    }
}

export default withTranslation()(ReprendreTramit);