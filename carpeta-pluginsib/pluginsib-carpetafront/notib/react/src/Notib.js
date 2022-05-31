import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from './i18n';
import axios from "axios";
import Table from 'react-bootstrap/Table';
import Pagination  from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

/**
 *  @author jpernia
 */

class Notib extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            dataComunicacions: null,
            urldetallbase: null,
            urldetallbase2: null,
            urldetallbase3: null,
            filter_regPorPagina: 5,
            filter_type: '0',
            filter_status: '0',
            pagination_active: 1,
            pagination_total_items: 5,
            total_items: 0,
            error: null,
            cercaRegistres: 5,
            missatgeBuid: i18n.t('notibBuid'),
            formVisible: false
        };

        this.handleRegPorPaginaFilterParam = this.handleRegPorPaginaFilterParam.bind(this);
        this.handleTypeFilterParam = this.handleTypeFilterParam.bind(this);
        this.handleStatusFilterParam = this.handleStatusFilterParam.bind(this);
        this.mostrarForm = this.mostrarForm.bind(this);

        this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);

        const getLocale = Locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

    }

    mostrarForm(){
        if(document.getElementById("fechaBusqueda").classList.contains("ocultarMobil")) {
            document.getElementById("fechaBusqueda").classList.remove("ocultarMobil");
        }
        document.getElementById("idCriteris").style.display = "none";

        this.setState({
            ...this.state,
            formVisible: true
        });
    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }


    handleRegPorPaginaFilterParam(e){

        this.setState({
            filter_regPorPagina: e.target.value,
            isLoaded: false,
            error: null,
            cercaRegistres: e.target.value
        });

        e.preventDefault();

        let url3;
        if(this.state.filter_type === '0') {
            if(this.state.filter_status === '0') {
                url3 = this.props.pathtoservei;
            } else if(this.state.filter_status === '1') {
                url3 = this.props.pathtoserveiPendientesUrl;
            } else if(this.state.filter_status === '2') {
                url3 = this.props.pathtoserveiLeidasUrl;
            }
        }else if(this.state.filter_type === '1') {
            if(this.state.filter_status === '0') {
                url3 = this.props.notificacionesTodasUrl;
            } else if(this.state.filter_status === '1') {
                url3 = this.props.notificacionesPendientesUrl;
            } else if(this.state.filter_status === '2') {
                url3 = this.props.notificacionesLeidasUrl;
            }
        }else if(this.state.filter_type === '2'){
            if(this.state.filter_status === '0') {
                url3 = this.props.comunicacionesTodasUrl;
            } else if(this.state.filter_status === '1') {
                url3 = this.props.comunicacionesPendientesUrl;
            } else if(this.state.filter_status === '2') {
                url3 = this.props.comunicacionesLeidasUrl;
            }
        }

        const params = {
            registrosPorPagina: e.target.value,
            pageNumber: 0,
            tipo: this.state.filter_type,
            estado: this.state.filter_status
        };

        axios.get(url3, {params: params}).then( (response) => {

            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    urldetallbase: response.data.urldetallbase,
                    urldetallbase2: response.data.urldetallbase2,
                    urldetallbase3: response.data.urldetallbase3,
                    total_items: response.data.totalRegistres,
                    pagination_active: 1,
                    pagination_total_items: response.data.registresPagina,
                    isLoaded: true,
                    error: null
                });
            }

        }).catch(error => {
            console.log(JSON.stringify(error));
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                this.setState({
                    error: "error500plugin",
                    isLoaded: true
                });
            } else{
                this.setState({
                    error: JSON.stringify(error),
                    isLoaded: true
                });
            }

        });


    };

    componentDidMount() {

        const url = this.props.pathtoservei;

        const params = {
            pageNumber: 0,
            registrosPorPagina: this.state.filter_regPorPagina,
            tipo: this.state.filter_type,
            estado: this.state.filter_status
        };

        axios.get(url, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    urldetallbase: response.data.urldetallbase,
                    urldetallbase2: response.data.urldetallbase2,
                    urldetallbase3: response.data.urldetallbase3,
                    total_items: response.data.totalRegistres,
                    isLoaded: true,
                    error: null
                });
            }
        }).catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                    this.setState({
                        error: "error500plugin",
                        isLoaded: true
                    });
                } else{
                    this.setState({
                        error: JSON.stringify(error),
                        isLoaded: true
                    });
                }

            });

    }

    handlePagination(event){

        let newPageNumber =  event.target.text;

        this.setState({
            ...this.state,
            pagination_active: newPageNumber,
            error: null,
            isLoaded: false
        });

        const params = {
            pageNumber: newPageNumber-1,
            registrosPorPagina: this.state.filter_regPorPagina,
            tipo: this.state.filter_type,
            estado: this.state.filter_status
        };
        const url2 = this.props.pathtoservei;

        axios.get(url2, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    urldetallbase: response.data.urldetallbase,
                    urldetallbase2: response.data.urldetallbase2,
                    urldetallbase3: response.data.urldetallbase3,
                    total_items: response.data.totalRegistres,
                    pagination_total_items: response.data.registresPagina,
                    pagination_active: newPageNumber,
                    isLoaded: true,
                    error: null
                });
            }
        }).catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                    this.setState({
                        error: "error500plugin",
                        isLoaded: true
                    });
                } else{
                    this.setState({
                        error: JSON.stringify(error),
                        isLoaded: true
                    });
                }

            });

    }

    handleTypeFilterParam(e){

        this.setState({
            ...this.state,
            filter_type: e.target.value,
            filter_status: '0'
        });

    };

    handleStatusFilterParam(e){
        this.setState({
            ...this.state,
            filter_status: e.target.value
        });
    };

    handleSubmitSearcher(e) {

        const {t} = this.props;

        e.preventDefault();

        this.setState({
            ...this.state,
            isLoaded: false,
            error: null,
            cercaRegistres: this.state.filter_regPorPagina,
            dataComunicacions: null
        });


        if(this.state.filter_type === '0'){
            this.setState({ ...this.state, missatgeBuid: t('notibBuid') });
        } else if(this.state.filter_type === '1'){
            this.setState({ ...this.state, missatgeBuid: t('notibNotificacionsBuid') });
        } else if (this.state.filter_type === '2'){
            this.setState({ ...this.state, missatgeBuid: t('notibComunicacionsBuid') });
        }

        let url2;
        if(this.state.filter_type === '0') {
            if(this.state.filter_status === '0') {
                url2 = this.props.pathtoservei;
            } else if(this.state.filter_status === '1') {
                url2 = this.props.pathtoserveiPendientesUrl;
            } else if(this.state.filter_status === '2') {
                url2 = this.props.pathtoserveiLeidasUrl;
            }
        }else if(this.state.filter_type === '1') {
            if(this.state.filter_status === '0') {
                url2 = this.props.notificacionesTodasUrl;
            } else if(this.state.filter_status === '1') {
                url2 = this.props.notificacionesPendientesUrl;
            } else if(this.state.filter_status === '2') {
                url2 = this.props.notificacionesLeidasUrl;
            }
        }else if(this.state.filter_type === '2'){
            if(this.state.filter_status === '0') {
                url2 = this.props.comunicacionesTodasUrl;
            } else if(this.state.filter_status === '1') {
                url2 = this.props.comunicacionesPendientesUrl;
            } else if(this.state.filter_status === '2') {
                url2 = this.props.comunicacionesLeidasUrl;
            }
        }

        const params = {
            registrosPorPagina: this.state.filter_regPorPagina,
            pageNumber: 0,
            tipo: this.state.filter_type,
            estado: this.state.filter_status
        };

        axios.get(url2, {params: params}).then( (response) => {

            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    urldetallbase: response.data.urldetallbase,
                    urldetallbase2: response.data.urldetallbase2,
                    urldetallbase3: response.data.urldetallbase3,
                    total_items: response.data.totalRegistres,
                    pagination_active: 1,
                    pagination_total_items: response.data.registresPagina,
                    isLoaded: true,
                    error: null
                });
            }

        }).catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                    this.setState({
                        error: "error500plugin",
                        isLoaded: true
                    });
                } else{
                    this.setState({
                        error: JSON.stringify(error),
                        isLoaded: true
                    });
                }

            });

    }

    componentDidUpdate() {

        $("tr").each(function(i) {
            $(this).removeAttr("role");
        });

    }


    render() {

        $.dateOrder = function(dateObject) {
            var d = new Date(dateObject);
            var day = d.getDate();
            var month = d.getMonth() + 1;
            var year = d.getFullYear();
            var hour = d.getHours();
            var minute = d.getMinutes();
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minute < 10) {
                minute = "0" + minute;
            }
            return year.toString() + month.toString() + day.toString() + hour.toString() + minute.toString();
        };

        $.dateFormat = function(dateObject) {
            var d = new Date(dateObject);
            var day = d.getDate();
            var month = d.getMonth() + 1;
            var year = d.getFullYear();
            var hour = d.getHours();
            var minute = d.getMinutes();
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minute < 10) {
                minute = "0" + minute;
            }
            return day + "/" + month + "/" + year + " " + hour + ":" + minute;
        };

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};
        var tamanyTaula = { width: '99%'};

        let content;
        let taulaNotib;
        let selectRegistres;
        let criteris;

        let cardNotificacions = [];

        let formulari = <>
            <Form id="fechaBusqueda" style={{marginBottom: '20px'}} className="ocultarMobil">
                <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }} className="ampleTotalApp">
                    <Row>
                        <div className="col-xs-12 mb-3 col campFormApp campFormNotibApp">
                            <Form.Group>
                                <Form.Label style={{float:'left'}}>{t('notibTipus')}</Form.Label>
                                <Form.Select id="tipo"
                                             name="tipo" className="form-control form-control-sm focusIn"
                                             value={this.state.filter_type}
                                             tabindex="504"
                                             aria-labelledby="tipo"
                                             onChange={(e) => {this.handleTypeFilterParam(e); }}>
                                    <option value="0" className="form-control form-control-sm selectMobil" selected="selected">{t('notibTotes')}</option>
                                    <option value="1" className="form-control form-control-sm selectMobil">{t('notibNotificacions')}</option>
                                    <option value="2" className="form-control form-control-sm selectMobil">{t('notibComunicacions')}</option>
                                </Form.Select>
                            </Form.Group>
                        </div>
                        <div className="col-xs-12 mb-3 col campFormApp campFormNotibApp">
                            <Form.Group>
                                <Form.Label style={{float:'left'}}>{t('notibComunicacionEstat')}</Form.Label>
                                <Form.Select id="estat"
                                             name="estat" className="form-control form-control-sm focusIn"
                                             value={this.state.filter_status}
                                             tabindex="505"
                                             aria-labelledby="estat"
                                             onChange={(e) => {this.handleStatusFilterParam(e); }}>
                                    <option value="0" className="form-control form-control-sm selectMobil" selected="selected">{t('notibTotes')}</option>
                                    <option value="1" className="form-control form-control-sm selectMobil">{t('notibPendents')}</option>
                                    <option value="2" className="form-control form-control-sm selectMobil">{t('notibLlegides')}</option>
                                </Form.Select>
                            </Form.Group>
                        </div>
                    </Row>
                    <Row style={{ width: 'fit-content', display: 'none'}}>
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('registro_regPorPagina')}</Form.Label>
                                <Form.Select id="regPorPagina"
                                             name="regPorPagina" className="form-control form-control-sm focusIn"
                                             value={this.state.filter_regPorPagina}
                                             tabindex="505"
                                             aria-labelledby="regPorPagina"
                                             style={{color: '#666', borderRadius: '0.2rem'}}
                                             onChange={(e) => {this.handleRegPorPaginaFilterParam(e); }}>
                                    <option value="5" className="form-control form-control-sm selectMobil" selected={this.state.filter_regPorPagina.toString() === '5'}>5</option>
                                    <option value="10" className="form-control form-control-sm selectMobil" selected={this.state.filter_regPorPagina.toString() === '10'}>10</option>
                                    <option value="25" className="form-control form-control-sm selectMobil" selected={this.state.filter_regPorPagina.toString() === '25'}>25</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                            <div className="alert alert-danger" role="alert" id="errorMsg"/>
                        </div>
                    </Row>
                    <Row className="col-md-3 row botoFormApp" style={{zIndex: '4'}}>
                        <Button type="submit" id="botoSubmit" className="btn btn-primary carpeta-btn mt-2" onClick={(e) => {this.handleSubmitSearcher(e)}} tabindex="505">{t('carpeta_buscar')}</Button>
                    </Row>
                </Container>
            </Form>
        </>

        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                <div className="loader"/>
            </div>;

            selectRegistres = "";
            taulaNotib = "";
            criteris = '';

        } else {

            criteris = <div id="idCriteris">
                <div style={{float: 'left', marginTop: '9px;', paddingBottom: '0.7em'}} className="col-md-10 pr-3 visioMobil">
                    <span className="oi oi-eye iconaFormApp" title={t('sistraDates')} style={{verticalAlign: 'sub'}}/>
                    <span className="pl-3">{t('notibTotesCriteri')}</span>
                </div>
                <div style={{float: 'rigth', marginTop: '9px;', paddingBottom: '0.7em', textAlign: 'end'}} onClick={() => {this.mostrarForm();}} className="col-md-1 visioMobil">
                    <span className="oi oi-magnifying-glass iconaFormApp" title={t('notibCerca')}/>
                </div>
            </div>

            content = "";

            const Header = [t('notibComunicacionFecha'), t('notibComunicacionConcepte'), t('notibComunicacionEstat')];

            if(this.state.dataComunicacions && typeof (this.state.total_items) !== undefined && typeof (this.state.dataComunicacions) !== undefined && this.state.total_items !== 0) {
                let paginationNumbers = [];
                for (let number = 1; number <= Math.ceil(this.state.total_items/this.state.cercaRegistres); number++) {
                    paginationNumbers.push(<Pagination.Item key={number}
                                                            active={number.toString() === this.state.pagination_active.toString()}
                                                            activeLabel=""
                                                            onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
                }

                selectRegistres = <div className="col-md-12 border-0 p-0">
                    <div className="col-sd-1 pb-2 margRegSelect">
                        <p className="lh15 mb-1 mRegSelApp">
                            {t('notibMostra')}
                            <Form.Select id="rPP"
                                         name="rPP" className="focusIn"
                                         value={this.state.filter_regPorPagina}
                                         tabindex="510"
                                         aria-labelledby="rPP"
                                         style={{color: '#666', borderRadius: '0.2rem'}}
                                         onChange={(e) => {this.handleRegPorPaginaFilterParam(e); }}>
                                <option value="5" className="form-control form-control-sm selectMobil">5</option>
                                <option value="10" className="form-control form-control-sm selectMobil">10</option>
                                <option value="25" className="form-control form-control-sm selectMobil">25</option>
                            </Form.Select>
                            {t('notibRegistres')}
                        </p>
                    </div>
                </div>;

                taulaNotib = <>
                    <Table id="tableId" responsive striped bordered hover style={tamanyTaula} className="ocultarMobil">
                        <thead className="table-success">
                        <tr>
                            <th style={tamanyData}>{t('notibComunicacionFecha')}</th>
                            <th>{t('notibComunicacionOrgano')}</th>
                            <th>{t('notibComunicacionConcepte')}</th>
                            <th style={tamanyData}>{t('notibComunicacionDataEstat')}</th>
                            <th>{t('notibTipus')}</th>
                            <th>{t('notibComunicacionEstat')}</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.dataComunicacions.map(({transmissio, tipus}) => {
                            return <tr className="" data-target="_blank" onClick={() =>
                                window.open(tipus === 'notificacio' ? (transmissio.estat === 'FINALIZADA' || transmissio.estat === 'FINALITZADA' || transmissio.estat === 'PROCESADA' || transmissio.estat === 'PROCESSADA' ? this.state.urldetallbase2 : this.state.urldetallbase) : this.state.urldetallbase3, '_blank')}
                                       style={cursorPointer} tabIndex="511">
                                <td data-order={$.dateOrder(transmissio.dataEnviament)}>{$.dateFormat(transmissio.dataEnviament)}</td>
                                <td>{transmissio.organGestor}</td>
                                <td>{transmissio.concepte}</td>
                                <td>{$.dateFormat(transmissio.dataEstat)}</td>
                                <td>{tipus === 'notificacio' ? i18n.t('notibNotificacio') : i18n.t('notibComunicacio')}</td>
                                <td>{transmissio.estat}</td>
                            </tr>
                        })}
                        </tbody>
                    </Table>
                    <div style={{float:'left', marginTop: '9px;',width: '60%'}} className="ocultarMobil">
                        {t('carpeta_paginacion_1') +
                        ((parseInt(this.state.pagination_active,10)-1)*parseInt(this.state.cercaRegistres, 10)+1) +
                        t('carpeta_paginacion_2') +
                        ( ((parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.total_items,10))
                                ? parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres, 10)
                                : this.state.total_items
                        ) +
                        t('carpeta_paginacion_3') +
                        this.state.total_items +
                        t('carpeta_paginacion_4')}
                    </div>
                    <Pagination style={{float:'right',paddingRight: '0.7em'}} className="ocultarMobil">
                        {paginationNumbers}
                    </Pagination>
                </>

                this.state.dataComunicacions.map(({transmissio, tipus},i) => {

                    cardNotificacions.push(
                        <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                             key={i} tabIndex={502+i} onClick={(e) =>
                            window.open(tipus === 'notificacio' ? (transmissio.estat === 'FINALIZADA' || transmissio.estat === 'FINALITZADA' || transmissio.estat === 'PROCESADA' || transmissio.estat === 'PROCESSADA' ? this.state.urldetallbase2 : this.state.urldetallbase) : this.state.urldetallbase3, '_blank')}>
                            <div className="col-sm-1 float-left">
                                <span className="oi oi-envelope-closed iconaFormApp" title={t('notibComunicacio')} style={{verticalAlign: 'sub'}}/>
                            </div>
                            <div className="col-sm-10 float-right">
                                <p className="card-text pl-1 mt-0 font-weight-bold" style={{color: 'rgb(102, 102, 102)'}}>{transmissio.concepte}</p>
                                <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{$.dateFormat(transmissio.dataEnviament)}</p>
                                <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{transmissio.organGestor}</p>
                                <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{tipus === 'notificacio' ? i18n.t('notibNotificacio') : i18n.t('notibComunicacio')}</p>
                                <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}><b>{t('notibComunicacionDataEstat')}: </b>{$.dateFormat(transmissio.dataEstat)}</p>
                                <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">{transmissio.estat}</h3>
                            </div>
                        </div>
                    )

                })

                cardNotificacions.push(<div className="visioMobil">
                        <div style={{float: 'left', marginTop: '9px;', width: '60%'}} className="visioMobil">
                            {t('carpeta_paginacion_1_App') +
                            ((parseInt(this.state.pagination_active, 10) - 1) * parseInt(this.state.cercaRegistres, 10) + 1) +
                            t('carpeta_paginacion_2') +
                            (((parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.pagination_total_items, 10))
                                    ? parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)
                                    : this.state.pagination_total_items
                            ) +
                            t('carpeta_paginacion_3_App') +
                            this.state.pagination_total_items +
                            t('carpeta_paginacion_4')}
                        </div>
                        <Pagination style={{float: 'right', paddingRight: '0.7em'}}>
                            {paginationNumbers}
                        </Pagination>
                    </div>
                )


            } else if(this.state.total_items === 0 && this.state.dataComunicacions !== null) {

                taulaNotib = <div className="pt-3 alert alert-secondary" style={{float: 'left', width: '95%'}}
                                  role="alert">{this.state.missatgeBuid}</div>

            }

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
                                {!this.state.formVisible && criteris}
                                {formulari}
                                {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}
                                {content}
                            </div>
                        </div>
                    </div>
                    <div className="float-left" style={{width: '97%', position: 'relative'}}>
                        {selectRegistres}
                        {this.state.isLoaded && taulaNotib }
                        {cardNotificacions}
                    </div>
                    <div className="col-md-12 border-0 float-left p-0" id="botoTornarNotib" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                        }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarNotib">{t('notibTornar')}</button>
                    </div>
                </div>
            </>
            );
            
    }
}

export default withTranslation()(Notib);