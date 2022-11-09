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
import DatePicker from 'react-datepicker';

/**
 *  @author jpernia
 */

class Notib extends Component {

    constructor(props) {
        super(props);

        let startDateObj = new Date();
        const endDateObj = new Date();
        startDateObj.setMonth(endDateObj.getMonth()-1);

        this.state = {
            isLoaded: false,
            dataComunicacions: null,
            dataInici: startDateObj,
            dataFi: endDateObj,
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

        this.handleChangeDataInici = this.handleChangeDataInici.bind(this);
        this.handleChangeDataFi = this.handleChangeDataFi.bind(this);
        this.handleRegPorPaginaFilterParam = this.handleRegPorPaginaFilterParam.bind(this);
        this.handleTypeFilterParam = this.handleTypeFilterParam.bind(this);
        this.handleStatusFilterParam = this.handleStatusFilterParam.bind(this);
        this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
        this.mostrarForm = this.mostrarForm.bind(this);
        this.mostrarMesInfo = this.mostrarMesInfo.bind(this);

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

    mostrarMesInfo(row) {
        if(document.getElementById(row).style.display === "none" ) {
            document.getElementById(row).style.display = "table-row";
        } else if( document.getElementById(row).style.display === "table-row" ) {
            document.getElementById(row).style.display = "none";
        }
    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    handleChangeDataInici(e) {
        this.setState({
            ...this.state,
            dataInici: e
        });
    }
    handleChangeDataFi(e) {
        this.setState({
            ...this.state,
            dataFi: e
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

    handleRegPorPaginaFilterParam(e){
        this.setState({
            filter_regPorPagina: e.target.value,
            isLoaded: false,
            error: null,
            cercaRegistres: e.target.value
        });

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
            dataInici: this.state.dataInici,
            dataFi: this.state.dataFi,
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
                var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                errorPantalla = errorPantalla.replace("</body></html>", '');
                this.setState({
                    error: errorPantalla,
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


    handlePagination(event, accio, isNumber, pag){

        let newPageNumber = event.target.text;
        let pagActiva;

        if(!isNumber) {
            if (accio === 0) {
                pagActiva = newPageNumber;
            }
            if (accio === 1) {
                pagActiva = this.state.pagination_active - 1;
            }
            if (accio === 2) {
                pagActiva = this.state.pagination_active + 1;
            }
        }else{
            pagActiva = pag;
        }

        this.setState({
            ...this.state,
            pagination_active: pagActiva,
            error: null,
            isLoaded: false
            // filter_type: this.state.filter_type,
            // filter_status: this.state.filter_status
        });

        const params = {
            dataInici: this.state.dataInici,
            dataFi: this.state.dataFi,
            pageNumber: pagActiva-1,
            registrosPorPagina: this.state.filter_regPorPagina,
            tipo: this.state.filter_type,
            estado: this.state.filter_status
        };

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
                    pagination_active: pagActiva,
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
                    var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                    errorPantalla = errorPantalla.replace("</body></html>", '');
                    this.setState({
                        error: errorPantalla,
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

    handleSubmitSearcher(e) {

        const {t} = this.props;

        let validatFormulari = this.validaFormulari();

        if(validatFormulari) {

            this.setState({
                ...this.state,
                isLoaded: false,
                cercaRegistres: this.state.filter_regPorPagina,
                dataComunicacions: null
            });

            e.preventDefault();

            if(this.state.filter_type === '0'){
                this.setState({ ...this.state, missatgeBuid: t('notibBuid'), isLoaded: false });
            } else if(this.state.filter_type === '1'){
                this.setState({ ...this.state, missatgeBuid: t('notibNotificacionsBuid'), isLoaded: false });
            } else if (this.state.filter_type === '2'){
                this.setState({ ...this.state, missatgeBuid: t('notibComunicacionsBuid'), isLoaded: false });
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
                dataInici: this.state.dataInici,
                dataFi: this.state.dataFi,
                registrosPorPagina: this.state.filter_regPorPagina,
                pageNumber: 0,
                tipo: this.state.filter_type,
                estado: this.state.filter_status
            };


            axios.get(url2, {params: params}).then( (response) => {

                this.setState({
                    ...this.state,
                    isLoaded: true,
                    pagination_active: 1,
                    error: null
                });

                if (response.data != null){
                    this.setState({
                        ...this.state,
                        dataComunicacions: response.data.comunicacions,
                        urldetallbase: response.data.urldetallbase,
                        urldetallbase2: response.data.urldetallbase2,
                        urldetallbase3: response.data.urldetallbase3,
                        total_items: response.data.totalRegistres,
                        pagination_total_items: response.data.registresPagina
                    });
                } else{
                    this.setState({
                        ...this.state,
                        dataComunicacions: null,
                        total_items: 0,
                        pagination_total_items: 10
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
                        var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                        errorPantalla = errorPantalla.replace("</body></html>", '');
                        this.setState({
                            error: errorPantalla,
                            isLoaded: true
                        });
                    } else{
                        this.setState({
                            error: JSON.stringify(error),
                            isLoaded: true
                        });
                    }

                });

        } else{
            e.preventDefault();
        }

    }

    componentDidMount() {

        this.setState({
            ...this.state,
            isLoaded: true
        });

        const { t } = this.props;
        let validatFormulari = this.validaFormulari();

        if(validatFormulari) {

            this.setState({
                ...this.state,
                isLoaded: false
            });

            const url = this.props.pathtoservei;

            const params = {
                dataInici: this.state.dataInici,
                dataFi: this.state.dataFi,
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
                    var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                    errorPantalla = errorPantalla.replace("</body></html>", '');
                    this.setState({
                        error: errorPantalla,
                        isLoaded: true
                    });
                } else{
                    this.setState({
                        error: JSON.stringify(error),
                        isLoaded: true
                    });
                }

            });

        } else{
            e.preventDefault();
        }

    }

    validaFormulari(){

        const { t } = this.props;

        if(!this.validaFecha(this.state.dataInici) || !this.validaFecha(this.state.dataFi)){
            return false;
        }

        if(Date.parse(this.state.dataInici) > Date.parse(this.state.dataFi)){
            $('#errorMsg').html(t('notibDataIniciError'));
            $('#errorContainer').removeClass('ocult');
            return false;
        }

        return true;
    }

    validaFecha(date) {
        if (isNaN(Date.parse(date))){
            $('#errorMsg').html("Error de data");
            $('#errorContainer').removeClass("ocult");
            return false;
        }else{
            $('#errorContainer').addClass("ocult");
            return true;
        }
    }

    componentDidUpdate() {
        $("#dataInici").attr("tabindex","501");
        $("#dataFi").attr("tabindex","502");

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

        $.dateFormatCerca = function(dateObject) {
            var d = new Date(dateObject);
            var day = d.getDate();
            var month = d.getMonth() + 1;
            var year = d.getFullYear();
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
            return day + "-" + month + "-" + year;
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

        let mostraCarregar = false;

        let cardNotificacions = [];

        let formulari = <>
            <Form id="fechaBusqueda" style={{marginBottom: '20px'}} className="ocultarMobil">
                <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }} className="ampleTotalApp">
                    <Row>
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('notibDataInici')}</Form.Label>
                                <DatePicker
                                    portalId="root-portal"
                                    selected={this.state.dataInici}
                                    onChange={ (startDate) => this.handleChangeDataInici(startDate) }
                                    selectsStart
                                    name="dataInici"
                                    id="dataInici"
                                    dateFormat="dd/MM/yyyy"
                                    className="form-control form-control-sm estilCalendar focusIn font1App"
                                    locale={this.locale}
                                    showYearDropdown={true}
                                    preventOpenOnFocus={true}
                                    popperPlacement="bottom"
                                    popperModifiers={{
                                        flip: {
                                            behavior: ["bottom"] // don't allow it to flip to be above
                                        },
                                        preventOverflow: {
                                            enabled: false // tell it not to try to stay within the view (this prevents the popper from covering the element you clicked)
                                        },
                                        hide: {
                                            enabled: false // turn off since needs preventOverflow to be enabled
                                        }
                                    }}
                                />
                            </Form.Group>
                        </Col>
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('notibDataFi')}</Form.Label>
                                <DatePicker
                                    portalId="root-portal"
                                    selected={this.state.dataFi}
                                    onChange={ (endDate) => this.handleChangeDataFi(endDate) }
                                    selectsEnd
                                    minDate={this.state.dataInici}
                                    name="dataFi"
                                    id="dataFi"
                                    dateFormat="dd/MM/yyyy"
                                    className="form-control form-control-sm estilCalendar focusIn font1App"
                                    locale={this.locale}
                                    showYearDropdown={true}
                                    preventOpenOnFocus={true}
                                    popperPlacement="bottom"
                                    popperModifiers={{
                                        flip: {
                                            behavior: ["bottom"] // don't allow it to flip to be above
                                        },
                                        preventOverflow: {
                                            enabled: false // tell it not to try to stay within the view (this prevents the popper from covering the element you clicked)
                                        },
                                        hide: {
                                            enabled: false // turn off since needs preventOverflow to be enabled
                                        }
                                    }}
                                />
                            </Form.Group>
                        </Col>


                        {/*<div className="col-xs-12 mb-3 col campFormApp campFormNotibApp">*/}
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label style={{float:'left'}}>{t('notibTipus')}</Form.Label>
                                <Form.Select id="tipo"
                                             name="tipo" className="form-control form-control-sm focusIn font1App"
                                             value={this.state.filter_type}
                                             tabindex="504"
                                             aria-labelledby="tipo"
                                             onChange={(e) => {this.handleTypeFilterParam(e); }}>
                                    <option value="0" className="form-control form-control-sm selectMobil" selected="selected">{t('notibTotes')}</option>
                                    <option value="1" className="form-control form-control-sm selectMobil">{t('notibNotificacions')}</option>
                                    <option value="2" className="form-control form-control-sm selectMobil">{t('notibComunicacions')}</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
                        {/*<div className="col-xs-12 mb-3 col campFormApp campFormNotibApp">*/}
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label style={{float:'left'}}>{t('notibComunicacionEstat')}</Form.Label>
                                <Form.Select id="estat"
                                             name="estat" className="form-control form-control-sm focusIn font1App"
                                             value={this.state.filter_status}
                                             tabindex="505"
                                             aria-labelledby="estat"
                                             onChange={(e) => {this.handleStatusFilterParam(e); }}>
                                    <option value="0" className="form-control form-control-sm selectMobil" selected="selected">{t('notibTotes')}</option>
                                    <option value="1" className="form-control form-control-sm selectMobil">{t('notibPendents')}</option>
                                    <option value="2" className="form-control form-control-sm selectMobil">{t('notibLlegides')}</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
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
                    <span className="oi oi-calendar iconaFormApp" title={t('notibDates')} style={{verticalAlign: 'sub'}}/>
                    <span className="pl-3">{$.dateFormatCerca(this.state.dataInici) +
                    t('carpeta_criterio_5') +
                    $.dateFormatCerca(this.state.dataFi)}</span>
                </div>
                <div style={{float: 'rigth', marginTop: '9px;', paddingBottom: '0.7em', textAlign: 'end'}} onClick={() => {this.mostrarForm();}} className="col-md-1 visioMobil">
                    <span className="oi oi-magnifying-glass iconaFormApp" title={t('notibCerca')}/>
                </div>
            </div>

            content = "";

            const Header = [t('notibComunicacionFecha'), t('notibComunicacionConcepte'), t('notibComunicacionEstat')];

            if(this.state.dataComunicacions && typeof (this.state.total_items) !== undefined && typeof (this.state.dataComunicacions) !== undefined && this.state.total_items !== 0) {
                let paginationNumbers = [];

                let showMax = 5;
                let endPage;
                let startPage;
                let pageNumbers = Math.ceil(this.state.total_items/this.state.cercaRegistres);
                if (pageNumbers <= showMax) {
                    startPage = 1;
                    endPage = pageNumbers;
                }else {
                    if(this.state.pagination_active < 4){
                        startPage = 1;
                    } else{
                        startPage = this.state.pagination_active - 2;
                    }
                    if (this.state.pagination_active + 2 < pageNumbers) {
                        endPage = this.state.pagination_active + 2;
                    }else {
                        endPage = pageNumbers;
                    }
                }

                for (let number = startPage; number <= endPage; number++) {
                    if (number === startPage && startPage > 1) {
                        paginationNumbers.push(<Pagination.Ellipsis key={number} className="muted" />);
                    }
                    paginationNumbers.push(<Pagination.Item key={number}
                                                            active={number.toString() === this.state.pagination_active.toString()}
                                                            activeLabel=""
                                                            onClick={(event) => this.handlePagination(event, 0, false, 0)}>{number}</Pagination.Item>,);
                    if (number === endPage && endPage < pageNumbers) {
                        paginationNumbers.push(<Pagination.Ellipsis key={number} className="muted" />);
                    }
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
                            <th className="colFixe">{t('notibComunicacionFecha')}</th>
                            <th className="colFixe">{t('notibTipus')}</th>
                            <th>{t('notibComunicacionConcepte')}</th>
                            <th className="colFixe">{t('notibComunicacionEstat')}</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.dataComunicacions.map(({transmissio, tipus},i) => {
                            // let nomTitular;
                            // if(transmissio.titular.nom){
                            //     nomTitular = transmissio.titular.nom + " " + transmissio.titular.llinatge1;
                            // }else{
                            //     nomTitular = transmissio.titular.raoSocial;
                            // }
                            return <>
                                    <tr className="clickable-row" style={cursorPointer} tabIndex={511 + i*2 - 1} onClick={() => this.mostrarMesInfo('row' + i)} onKeyPress={() => this.mostrarMesInfo('row' + i)}>
                                        <td data-order={$.dateOrder(transmissio.dataEnviament)}>{$.dateFormat(transmissio.dataEnviament)}</td>
                                        <td>{tipus === 'notificacio' ? i18n.t('notibNotificacio') : i18n.t('notibComunicacio')}</td>
                                        <td>{transmissio.concepte}</td>
                                        <td>{transmissio.estat.codi}</td>
                                    </tr>
                                    <tr style={{display: 'none'}} id={'row' + i}>
                                        <td colSpan={4}>
                                            <div style={{float: 'left', width: '70%'}}>
                                                {transmissio.emisor &&<p><b>{t('notibComunicacionEmissor')}</b>: {transmissio.emisor}</p>}
                                                {transmissio.organGestor.nom &&<p><b>{t('notibComunicacionOrgano')}</b>: {transmissio.organGestor.nom}</p>}
                                                {transmissio.procediment.codi && <p><b>{t('notibProcediment')}</b>: {transmissio.procediment.codi}</p>}
                                                {transmissio.descripcio && <p><b>{t('notibDescripcioNotificacio')}</b>: {transmissio.descripcio}</p>}
                                            </div>
                                            <div style={{float: 'right', width: 'auto'}} id="accedirNotib">
                                                {transmissio.dataEstat && <p><b>{t('notibDarreraModificacio')}</b>: {$.dateFormat(transmissio.dataEstat)}</p>}
                                                {transmissio.subestat && <p><b>{t('notibSubestat')}</b>: {transmissio.subestat}</p>}
                                                <p className="pt-2">
                                                    <button className="btn btn-primary carpeta-btn botoAccedirCarpeta"
                                                            title={t('notibAccedirComunicacio')}
                                                            tabIndex={511 + i*2}
                                                            aria-labelledby="accedirNotib"
                                                            onClick={() =>
                                                                window.open(tipus === 'notificacio' ? (transmissio.estat.codi === 'FINALIZADA' || transmissio.estat.codi === 'FINALITZADA' || transmissio.estat.codi === 'PROCESADA' || transmissio.estat.codi === 'PROCESSADA' ? this.state.urldetallbase2 : this.state.urldetallbase) : this.state.urldetallbase3, '_blank')}>
                                                            <span className="oi oi-external-link" title=""
                                                                  aria-hidden="true"/> {t('notibBotoDehu')}
                                                    </button>
                                                </p>
                                            </div>
                                        </td>
                                    </tr>
                                    </>
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
                        {pageNumbers > showMax && <Pagination.First onClick={(event) => this.handlePagination(event, 0, true, 1)}
                                         disabled={this.state.pagination_active === 1} />}
                        {pageNumbers > showMax && <Pagination.Prev onClick={(event) => this.handlePagination(event, 1, false, 0)}
                                                                disabled={this.state.pagination_active === 1}/>}
                        {paginationNumbers}
                        {pageNumbers > showMax && <Pagination.Next onClick={(event) => this.handlePagination(event, 2, false, 0)}
                                                                disabled={this.state.pagination_active === pageNumbers}/>}
                        {pageNumbers > showMax && <Pagination.Last onClick={(event) => this.handlePagination(event, 0, true, pageNumbers)}
                                         disabled={this.state.pagination_active === pageNumbers}/>}
                    </Pagination>

                </>

                this.state.dataComunicacions.map(({transmissio, tipus},i) => {
                    // let nomTitular;
                    // if(transmissio.titular.nom){
                    //     nomTitular = transmissio.titular.nom + " " + transmissio.titular.llinatge1;
                    // }else{
                    //     nomTitular = transmissio.titular.raoSocial;
                    // }

                    cardNotificacions.push(
                        <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                             key={i} tabIndex={502+i} onClick={(e) =>
                            window.open(tipus === 'notificacio' ? (transmissio.estat.codi === 'FINALIZADA' || transmissio.estat.codi === 'FINALITZADA' || transmissio.estat.codi === 'PROCESADA' || transmissio.estat.codi === 'PROCESSADA' ? this.state.urldetallbase2 : this.state.urldetallbase) : this.state.urldetallbase3, '_blank')}>
                            <div className="col-sm-1 float-left">
                                <span className="oi oi-envelope-closed iconaFormApp" title={t('notibComunicacio')} style={{verticalAlign: 'sub'}}/>
                            </div>
                            <div className="col-sm-10 float-right">
                                <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{$.dateFormat(transmissio.dataEnviament)}</p>
                                <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{tipus === 'notificacio' ? i18n.t('notibNotificacio') : i18n.t('notibComunicacio')}</p>
                                <p className="card-text pl-1 mt-0 font-weight-bold" style={{color: 'rgb(102, 102, 102)'}}>{transmissio.concepte}</p>
                                {transmissio.emisor && <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}><b>{t('notibComunicacionEmissor')}: </b>{transmissio.emisor}</p>}
                                {transmissio.organGestor.nom && <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{transmissio.organGestor.nom}</p>}
                                {transmissio.procediment.codi && <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}><b>{t('notibProcediment')}: </b>{transmissio.procediment.codi}</p>}
                                {transmissio.descripcio && <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}><b>{t('notibDescripcioNotificacio')}: </b>{transmissio.descripcio}</p>}
                                {transmissio.dataEstat && <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}><b>{t('notibDarreraModificacio')}: </b>{$.dateFormat(transmissio.dataEstat)}</p>}
                                {transmissio.subestat && <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}><b>{t('notibSubestat')}: </b>{transmissio.subestat}</p>}
                                <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">{transmissio.estat.codi}</h3>
                            </div>
                        </div>
                    )

                })

                cardNotificacions.push(<div className="visioMobil">
                        <div style={{float: 'left', marginTop: '9px;', width: '100%'}} className="visioMobil pb-4">
                            {t('carpeta_paginacion_1_App') +
                            ((parseInt(this.state.pagination_active, 10) - 1) * parseInt(this.state.cercaRegistres, 10) + 1) +
                            t('carpeta_paginacion_2') +
                            ( ((parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.total_items,10))
                                    ? parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres, 10)
                                    : this.state.total_items
                            ) +
                            t('carpeta_paginacion_3_App') +
                            this.state.total_items +
                            t('carpeta_paginacion_4')}
                        </div>
                        <Pagination style={{float: 'left', width: '100%'}} size="lg">
                            {pageNumbers > showMax && <Pagination.First onClick={(event) => this.handlePagination(event, 0, true, 1)}
                                              disabled={this.state.pagination_active === 1}/>}
                            {pageNumbers > showMax && <Pagination.Prev onClick={(event) => this.handlePagination(event, 1, false, 0)}
                                             disabled={this.state.pagination_active === 1}/>}
                            {paginationNumbers}
                            {pageNumbers > showMax && <Pagination.Next onClick={(event) => this.handlePagination(event, 2, false, 0)}
                                             disabled={this.state.pagination_active === pageNumbers}/>}
                            {pageNumbers > showMax && <Pagination.Last onClick={(event) => this.handlePagination(event, 0, true, pageNumbers)}
                                             disabled={this.state.pagination_active === pageNumbers}/>}
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
                        {taulaNotib}
                        {cardNotificacions}
                    </div>
                    <div className="col-md-12 border-0 float-left p-0" id="botoTornarNotib" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                        }} className="botoSuport botoTornauApp" tabIndex="520" aria-labelledby="botoTornarNotib">{t('notibTornar')}</button>
                    </div>
                </div>
            </>
            );
            
    }
}

export default withTranslation()(Notib);