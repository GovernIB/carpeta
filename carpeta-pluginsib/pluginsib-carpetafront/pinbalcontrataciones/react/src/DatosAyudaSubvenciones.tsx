import React from 'react';
import {WithTranslation, withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

/**
 *  @author jagarcia
 */

interface DatosAyudaSubvencionesProps extends WithTranslation{
    pathtoservei: string;
    titles: any;
    subtitles: any;
}

interface DatosAyudaSubvencionesState{
    isLoaded: boolean;
    data: any;
}

class DatosAyudaSubvenciones extends React.Component<DatosAyudaSubvencionesProps, DatosAyudaSubvencionesState> {
    
    
    constructor(props: DatosAyudaSubvencionesProps) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null
        };

    }

    componentDidMount() {

        const url2 = this.props.pathtoservei;
        axios.get(url2).then(res => {

            // console.log(" AXIOS OK OK OK OK OK", res.data);

            this.setState({
                ...this.state, 
                isLoaded: true,
                data: res.data
            });
        }).catch(error => {

            console.log(JSON.stringify(error));
            const restdata = { "error": JSON.stringify(error) };
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            this.setState({
                ...this.state, 
                isLoaded: true,
                data: restdata
            });
        });
        
    }

    onChangeMostrarDetallError() {
        const { t } = this.props;
        const detallError = document.getElementById("errorDetail");
        const detallBoto = document.getElementById("detallBoto");
        if (detallError?.style.display == 'block') {
            detallError?.setAttribute("style", "color: red; display: none");
            if(detallBoto){
                detallBoto.textContent = t("mostrarDetallError");
            }
            
        } else {
            detallError?.setAttribute("style", "color: red; display: block");
            if(detallBoto){
                detallBoto.textContent = t("amagarDetallError");
            }
        }


    }

    render() {
        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;
        let contentApp;

        if (!isLoaded) {
        
            content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
        
            const data = this.state.data;
            if (data.error) {  
                

                content = <><div className="alert alert-warning" role="alert">{t("errorConsultaPinbal")}
                    <br />
                    <button id = "detallBoto" type="submit"
                        className="btn btn-warning carpeta-btn mt-2"
                        onClick={() => {
                            this.onChangeMostrarDetallError();
                        }}
                        tabIndex={505}>{t("mostrarDetallError")}</button>
                    <br /><br />
                    <div style={{ color: "red", display: "none" }} id="errorDetail" >
                        {data.error}
                    </div>
                </div>
                </>;
            } else {
				
                let alerta;

                if ( data.codigo == '0'){
                    alerta = <div className="alert alert-success" role="alert">
                        {t('pinbalContratacionesFecha')} {data.fecha} : {t('pinbalContratacionesCodigo'+data.codigo)}
                    </div>;
                } else if( data.codigo == '1'){
                    alerta = <div className="alert alert-danger" role="alert">
                        {t('pinbalContratacionesFecha')} {data.fecha} : {t('pinbalContratacionesCodigo'+data.codigo)}
                    </div>;
                } else {
                    alerta = <div className="alert alert-warning" role="alert">
                        {t('pinbalContratacionesFecha')} {data.fecha} : {t('pinbalContratacionesCodigo'+data.codigo)}
                    </div>;
                }

				content = <div className="ocultarMobil">
                    {alerta}
                    <div className="contenedorInfoPersonal mt-2">
                        <dl className="row">
                            {data.dni && <div>
                                <dt className="col-sm-3">{t('pinbalContratacionesDni')}</dt>
                                <dd className="col-sm-7">{data.dni}</dd>
                            </div>}
                            {data.nombre && <div className="mt-3">
                                <dt className="col-sm-3">{t('pinbalContratacionesNom')}</dt>
                                <dd className="col-sm-7">{data.nombre}</dd>
                            </div>}
                            {data.apellido1 && <div className="mt-3">
                                <dt className="col-sm-3">{t('pinbalContratacionesApellido1')}</dt>
                                <dd className="col-sm-7">{data.apellido1}</dd>
                            </div>}
                            {data.apellido2 && <div className="mt-3">
                                <dt className="col-sm-3">{t('pinbalContratacionesApellido2')}</dt>
                                <dd className="col-sm-7">{data.apellido2}</dd>
                            </div>}
                        </dl>
                    </div>
				</div>;

                let contentAppTitle: string = t('pinbalContratacionesConsulta');

                contentApp = <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto" tabIndex={510}>
                    <div className="col-sm-1 float-left">
                        <span className="oi oi-bell iconaFormApp" title={contentAppTitle} style={{verticalAlign: 'sub'}}/>
                    </div>
                    <div className="col-sm-10 float-right">
                        {alerta}
                        {data.dni && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalContratacionesDni')}:</b> {data.dni}</p>}
                        {data.nombre && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalContratacionesNom')}:</b> {data.nombre}</p>}
                        {data.apellido1 && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalContratacionesApellido1')}:</b> {data.apellido1}</p>}
                        {data.apellido2 && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalContratacionesApellido2')}:</b> {data.apellido2}</p>}
                    </div>
                </div>;

            }
        }

        let paginaTornar: string|null = sessionStorage.getItem("pagTornar");
        let contextPath: string|null = sessionStorage.getItem("contextPath");

        return (<>
                <div className="titolPaginaApp visioMobil">
                    {this.props.titles[i18n.language]}
                </div>
                <div className="infoNoMenu">
                    <h2 className="titol h2 ocultarMobil">{this.props.titles[i18n.language]}</h2>
                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15 ocultarMobil pt-3">{this.props.subtitles[i18n.language]} </p>
                        <div className="infoNoMenu">
                            <div className="col-md-12 border-0 float-left p-0">
                                {content}
                                {contentApp}
                            </div>
                        </div>
                    </div>
                    <div className="col-md-12 border-0 float-left p-0" id="botoTornarContrataciones" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = paginaTornar != null ? paginaTornar : ""; sessionStorage.setItem("pagTornar", contextPath != null ? contextPath : "")
                        }} className="botoSuport botoTornauApp" tabIndex={520} aria-labelledby="botoTornarContrataciones">{t('pinbalContratacionesTornar')}</button>
                    </div>
                </div>
            </>
            );
            
    }
}

export default withTranslation()(DatosAyudaSubvenciones);