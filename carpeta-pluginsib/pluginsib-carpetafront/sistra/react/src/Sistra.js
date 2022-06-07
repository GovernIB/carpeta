import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';
import DatePicker from 'react-datepicker';
import Table from 'react-bootstrap/Table';
import Pagination  from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import DetallRegistre from './DetallRegistre';
import ReactDOM from "react-dom";

/**
 *  @author jpernia
 */

class Sistra extends Component {

    constructor(props) {
        super(props);

        let startDateObj = new Date();
        const endDateObj = new Date();
        startDateObj.setMonth(endDateObj.getMonth()-1);

        this.state = {
            isLoaded: false,
            data: null,
            dataInici: startDateObj,
            dataFi: endDateObj,
            estat: 'A',
            pagination_active: 1,
            pagination_total_items: 5,
            total_items: 0,
            numeroRegistro: null,
            error: null,
            filter_regPorPagina: 5,
            cercaRegistres: 5,
            formVisible: false
        };

        this.handleChangeDataInici = this.handleChangeDataInici.bind(this);
        this.handleChangeDataFi = this.handleChangeDataFi.bind(this);
        this.handleChangeEstat = this.handleChangeEstat.bind(this);
        this.handleRegPorPaginaFilterParam = this.handleRegPorPaginaFilterParam.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.mostrarForm = this.mostrarForm.bind(this);

        const getLocale = locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`)
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

        this.fechaInicio;
        this.fechaFin;
        this.estado;

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
        // console.log(" CANVIAT IDIOMA EN IniciPublic A ]" + lng+ "[");
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
    handleChangeEstat(e) {
        this.setState({
            ...this.state,
            estat: e.target.value
        });

    }
    handleRegPorPaginaFilterParam(e){

        this.setState({
            ...this.state,
            filter_regPorPagina: e.target.value,
            isLoaded: false,
            error: null,
            cercaRegistres: e.target.value
        });

        const params = {
            dataInici: this.state.dataInici,
            dataFi: this.state.dataFi,
            estat: this.state.estat,
            registrosPorPagina: e.target.value,
            pageNumber: 0
        };

        const url3 = this.props.pathtoservei;

        axios.get(url3, {params: params}).then( (response) => {

            if (response.data != null){
                this.setState({
                    ...this.state,
                    data: response.data.tramits,
                    pagination_active: 1,
                    total_items: response.data.totalRegistres,
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
        let newPageNumber =  event.target.text;
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
        });

        const params = {
            dataInici: this.state.dataInici,
            dataFi: this.state.dataFi,
            estat: this.state.estat,
            registrosPorPagina: this.state.filter_regPorPagina,
            pageNumber: pagActiva-1
        };

        const url3 = this.props.pathtoservei;

        axios.get(url3, {params: params}).then( (response) => {

            if (response.data != null){
                this.setState({
                    ...this.state,
                    data: response.data.tramits,
                    pagination_active: pagActiva,
                    total_items: response.data.totalRegistres,
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

    }


    handleSubmit(e) {

        const { t } = this.props;
        let validatFormulari = this.validaFormulari();

        if(validatFormulari) {

            this.setState({
                ...this.state,
                isLoaded: false,
                cercaRegistres: this.state.filter_regPorPagina,
                data: null
            });

            e.preventDefault();

            const url2 = this.props.pathtoservei;

            const params = {
                dataInici: this.state.dataInici,
                dataFi: this.state.dataFi,
                estat: this.state.estat,
                registrosPorPagina: this.state.filter_regPorPagina,
                pageNumber: 0
            };

            this.fechaInicio = this.state.dataInici;
            this.fechaFin = this.state.dataFi;
            this.estado = this.state.estat;

            axios.get(url2, {params: params}).then((response) => {

                this.setState({
                    ...this.state,
                    isLoaded: true,
                    pagination_active: 1,
                    error: null
                });

                if (response.data != null) {
                    this.setState({
                        ...this.state,
                        data: response.data.tramits,
                        total_items: response.data.totalRegistres,
                        pagination_total_items: response.data.registresPagina
                    });
                } else{
                    this.setState({
                        ...this.state,
                        data: null,
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
        const getLocale = locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`)
        this.locale = getLocale(this.props.language);

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
                estat: this.state.estat,
                registrosPorPagina: this.state.filter_regPorPagina,
                pageNumber: 0
            };

            this.fechaInicio = this.state.dataInici;
            this.fechaFin = this.state.dataFi;
            this.estado = this.state.estat;

            axios.get(url, {params: params}).then((response) => {

                this.setState({
                    ...this.state,
                    isLoaded: true,
                    pagination_active: 1,
                    error: null
                });

                if (response.data != null) {
                    this.setState({
                        ...this.state,
                        data: response.data.tramits,
                        total_items: response.data.totalRegistres,
                        pagination_total_items: response.data.registresPagina
                    });
                } else{
                    this.setState({
                        ...this.state,
                        data: null,
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

    obrirTramit(url) {
        var win = window.open(url, '_blank');
        if (win) {
            win.focus();
        } else {
            alert('Permeteu finestres emergents per a aquest lloc web');
        }
    }

    validaFormulari(){

        const { t } = this.props;

        if(!this.validaFecha(this.state.dataInici) || !this.validaFecha(this.state.dataFi)){
            return false;
        }

        if(Date.parse(this.state.dataInici) > Date.parse(this.state.dataFi)){
            $('#errorMsg').html(t('sistraDataIniciError'));
            $('#errorContainer').removeClass('ocult');
            // $('#fechaInicio').css('border','1px solid red !important');
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

        const { t } = this.props;

        let taulaTramits;
        var tamanyTaula = { width: '99%'};
        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};

        if(this.state.data && typeof (this.state.total_items) !== undefined && typeof (this.state.data) !== undefined && this.state.total_items !== 0) {
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

            taulaTramits = <>
                <Table responsive striped bordered hover style={tamanyTaula}>
                    <thead className="table-success">
                    <tr>
                        <th>{t('sistraTramit')}</th>
                        <th style={{whiteSpace: 'nowrap'}}>{t('sistraData')}</th>
                        <th style={{whiteSpace: 'nowrap'}}>{t('sistraEstat')}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.data.map(({descripcionTramite, fechaInicio, pendiente, numero, url, versionSistra, mostraModal, tipo}) => {
                        return <tr className="clickableRow" data-numero={numero} data-url={url} onClick={()=>this.obrirTramit(url)} data-version={versionSistra} style={cursorPointer} data-mostramodal={$.siNo(mostraModal)} data-pending={$.siNo(pendiente)} tabIndex="511">
                            <td>{descripcionTramite}</td>
                            <td data-order={$.dateOrder(fechaInicio)} style={{whiteSpace: 'nowrap'}}>{$.dateFormat(fechaInicio)}</td>
                            <td style={{whiteSpace: 'nowrap'}}>{$.estatTramit(pendiente, mostraModal, tipo, numero)}</td>
                        </tr>
                    })}
                    </tbody>
                </Table>
                <Pagination>
                    {paginationNumbers}
                </Pagination>
            </>

        } else if(this.state.total_items === 0 && this.state.data !== null) {
            taulaTramits = <div className="pt-3 alert alert-secondary margeBuid" style={{ float: 'left', width: '95%'}} role="alert">{t('sistraBuid')}</div>

        }

        $('#veureError').click(function(){
            $('#ocultaError').removeClass('ocult');
            $('#veureError').addClass('ocult');
            $('#divOcult').css('display','block');
        });

        $('#ocultaError').click(function(){
            $('#ocultaError').addClass('ocult');
            $('#veureError').removeClass('ocult');
            $('#divOcult').css('display','none');
        });

        $("tr").each(function(i) {
            $(this).removeAttr("role");
        });

        let detallRegistreContainer;
        if ( this.state.numeroRegistro != null ){
            detallRegistreContainer = <DetallRegistre pathtoservei={props.detallpathtoservei} numero={this.state.numeroRegistro} />;
            ReactDOM.render(detallRegistreContainer, document.getElementById('sistracontainer'));
        }

    }

    handleItemClick(numeroRegistro) {
        this.setState({
            ...this.state,
            isLoaded: false,
            numeroRegistro: numeroRegistro
        });
    }

    openModalConfirm(url) {
        const { t } = this.props;
        $('body')
            .append(
                '<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
                + '<div class="modal-dialog" role="document">'
                + '<div class="modal-content">'

                + '<div class="modal-header">'
                + '<h3 id="myModalLabel">'+ t('sistraModalTitol') +'</h3>'
                + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-weight: normal;font-size: 20px;" tabindex="590">x</button>'
                + '</div>'

                + '<div class="modal-body">'
                + '<p>'+ t('sistraModalTexte') +'</p>'
                + '</div>'

                + '<div class="modal-footer">'
                + '<button class="btn btn-success" type="button" onclick="window.open(\''+url+'\', \'_blank\');$(\'#myModal\').modal(\'hide\');" tabindex="511">'+ t('sistraModalBotoContinuar') +'</button>'
                + '<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true" tabindex="512">'+ t('sistraModalBotoCancelar') +'</button>'

                + '</div>'

                + '</div>'
                + '</div>'
                + '</div>');

        $('#myModal').modal('show');
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
            return day + "-" + month + "-" + year + " " + hour + ":" + minute;
        };

        $.siNo = function(variable) {
            if (variable) {
                return 'S';
            }else{
                return 'N';
            }
        }

        $.estatTramit = function(pendiente, mostraModal, tipo, numero) {
            if (pendiente) {
                if (mostraModal) {
                    return t('sistraNoFinalizatPresencial'); //Pendent compareixença
                }else{
                    return t('sistraNoFinalitzat');  // Inacabat
                }
            }else{
                if (tipo === 'REGISTRO' || numero !== '') {
                    return t('sistraFinalitzat'); // Registrat
                }else{
                    return t('sistraFinalitzat'); // Finalitzat
                }
            }
        }

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

        $.nomEstat = function(estat) {
            if (estat === 'A') {
                return t('sistraTots');
            }else if (estat === 'S'){
                return t('sistraFinalitzat');
            }else if (estat === 'N'){
                return t('sistraNoFinalitzat');
            }else if (estat === 'P'){
                return t('sistraNoFinalizatPresencial');
            }else if (estat === 'R'){
                return t('sistraRegistrat');
            }
        }

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};
        var tamanyTaula = { width: '99%'};

        let content;
        let formulari;

        let taulaTramits;
        let detallRegistreContainer;
        let selectRegistres;
        let criteris;

        let cardTramits = [];

        formulari = <>
            <Form id="fechaBusqueda" style={{ marginBottom: '20px'}} className="ocultarMobil">
                <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }} className="ampleTotalApp">
                    <Row>
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('sistraDataInici')}</Form.Label>

                                <DatePicker
                                    portalId="root-portal"
                                    selected={this.state.dataInici}
                                    onChange={ (startDate) => this.handleChangeDataInici(startDate) }
                                    selectsStart
                                    // startDate={this.state.dataInici}
                                    // endDate={this.state.dataFi}
                                    name="dataInici"
                                    id="dataInici"
                                    dateFormat="dd/MM/yyyy"
                                    className="form-control form-control-sm estilCalendar focusIn"
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
                                <Form.Label>{t('sistraDataFi')}</Form.Label>
                                <DatePicker
                                    portalId="root-portal"
                                    selected={this.state.dataFi}
                                    onChange={ (endDate) => this.handleChangeDataFi(endDate) }
                                    selectsEnd
                                    // startDate={this.state.dataInici}
                                    // endDate={this.state.dataFi}
                                    minDate={this.state.dataInici}
                                    name="dataFi"
                                    id="dataFi"
                                    dateFormat="dd/MM/yyyy"
                                    className="form-control form-control-sm estilCalendar focusIn"
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
                                <Form.Label id="estado">{t('sistraEstat')}</Form.Label>
                                <Form.Select id="tramiteFinalizado"
                                             name="tramiteFinalizado" className="form-control form-control-sm focusIn"
                                             value={this.state.estat}
                                             tabindex="503"
                                             aria-labelledby="estado"
                                             onChange={(e) => {this.handleChangeEstat(e); }}>
                                    <option value="A" className="form-control form-control-sm selectMobil" selected={this.state.estat === 'A'}>{t('sistraTots')}</option>
                                    <option value="S" className="form-control form-control-sm selectMobil" selected={this.state.estat === 'S'}>{t('sistraFinalitzat')}</option>
                                    <option value="N" className="form-control form-control-sm selectMobil" selected={this.state.estat === 'N'}>{t('sistraNoFinalitzat')}</option>
                                    <option value="P" className="form-control form-control-sm selectMobil" selected={this.state.estat === 'P'}>{t('sistraNoFinalizatPresencial')}</option>
                                    {/*<option value="R" className="form-control form-control-sm selectMobil" selected={this.state.estat === 'R'}>{t('sistraRegistrat')}</option>*/}
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
                                    <option value="5" className="form-control form-control-sm selectMobil">5</option>
                                    <option value="10" className="form-control form-control-sm selectMobil">10</option>
                                    <option value="25" className="form-control form-control-sm selectMobil">25</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                            <div className="alert alert-danger" role="alert" id="errorMsg"/>
                        </div>
                    </Row>
                    <Row className="col-md-3 pl-3 row botoFormApp" style={{zIndex: '4'}}>
                        <Button type="submit" className="btn btn-primary carpeta-btn mt-2" onClick={(e) => {this.handleSubmit(e)}} tabindex="504">{t('sistraCercaBoto')}</Button>
                    </Row>
                </Container>
            </Form>
            </>

        if (!isLoaded) {
            taulaTramits = '';

             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;

            selectRegistres = '';
            criteris = '';

        } else {

                criteris = <div id="idCriteris">
                    <div style={{float: 'left', marginTop: '9px;', paddingBottom: '0.7em'}} className="col-md-10 pr-3 visioMobil">
                        <span className="oi oi-calendar iconaFormApp" title={t('sistraDates')} style={{verticalAlign: 'sub'}}/>
                        <span className="pl-3">{$.dateFormatCerca(this.state.dataInici) +
                        t('carpeta_criterio_5') +
                        $.dateFormatCerca(this.state.dataFi)}</span>
                    </div>
                    <div style={{float: 'rigth', marginTop: '9px;', paddingBottom: '0.7em', textAlign: 'end'}} onClick={() => {this.mostrarForm();}} className="col-md-1 visioMobil">
                        <span className="oi oi-magnifying-glass iconaFormApp" title={t('sistraCerca')}/>
                    </div>
                </div>

                if(this.state.data && typeof (this.state.total_items) !== undefined && typeof (this.state.data) !== undefined && this.state.total_items !== 0) {
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
                                                {t('sistraMostra')}
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
                                                {t('sistraRegistres')}
                                            </p>
                                        </div>
                                    </div>;

                    taulaTramits = <>
                        <Table responsive striped bordered hover style={tamanyTaula} className="ocultarMobil">
                            <thead className="table-success">
                                <tr>
                                    <th>{t('sistraTramit')}</th>
                                    <th style={{whiteSpace: 'nowrap'}}>{t('sistraData')}</th>
                                    <th style={{whiteSpace: 'nowrap'}}>{t('sistraEstat')}</th>
                                </tr>
                            </thead>
                            <tbody>
                            {this.state.data.map(({   descripcionTramite,
                                                      fechaInicio,
                                                      pendiente,
                                                      numero,
                                                      url,
                                                      versionSistra,
                                                      mostraModal,
                                                      tipo
                                                  },i) => {

                                if (numero !== '')
                                    return <>
                                                <tr className="clickableRow" data-numero={numero} data-url={url}
                                                           data-version={versionSistra} style={cursorPointer}
                                                           data-mostramodal={$.siNo(mostraModal)}
                                                           data-pending={$.siNo(pendiente)} tabIndex="511"
                                                           onClick={(e) => this.handleItemClick(numero)}>
                                                    <td>{descripcionTramite}</td>
                                                    <td data-order={$.dateOrder(fechaInicio)}
                                                        style={{whiteSpace: 'nowrap'}}>{$.dateFormat(fechaInicio)}</td>
                                                    <td style={{whiteSpace: 'nowrap'}}>{$.estatTramit(pendiente, mostraModal, tipo, numero)}</td>
                                                </tr>
                                            </>

                                if (numero === '' && pendiente && mostraModal)
                                    return <>
                                            <tr className="clickableRow" data-numero={numero} data-url={url}
                                                   data-version={versionSistra} style={cursorPointer}
                                                   data-mostramodal={$.siNo(mostraModal)}
                                                   data-pending={$.siNo(pendiente)} tabIndex="511"
                                                   onClick={(e) => this.openModalConfirm(url)}>
                                                <td>{descripcionTramite}</td>
                                                <td data-order={$.dateOrder(fechaInicio)}
                                                    style={{whiteSpace: 'nowrap'}}>{$.dateFormat(fechaInicio)}</td>
                                                <td style={{whiteSpace: 'nowrap'}}>{$.estatTramit(pendiente, mostraModal, tipo, numero)}</td>
                                            </tr>
                                    </>

                                if (numero === '' && (!pendiente || !mostraModal))
                                    return <>
                                            <tr className="clickableRow" data-numero={numero} data-url={url}
                                                       data-version={versionSistra} style={cursorPointer}
                                                       data-mostramodal={$.siNo(mostraModal)}
                                                       data-pending={$.siNo(pendiente)} tabIndex="511"
                                                       onClick={(e) => window.open(url, '_blank')}>
                                                <td>{descripcionTramite}</td>
                                                <td data-order={$.dateOrder(fechaInicio)}
                                                    style={{whiteSpace: 'nowrap'}}>{$.dateFormat(fechaInicio)}</td>
                                                <td style={{whiteSpace: 'nowrap'}}>{$.estatTramit(pendiente, mostraModal, tipo, numero)}</td>
                                            </tr>
                                    </>
                            })}
                            </tbody>
                        </Table>
                        <div style={{float: 'left', marginTop: '9px;', width: '60%'}} className="ocultarMobil">
                            {t('carpeta_paginacion_1') +
                            ((parseInt(this.state.pagination_active, 10) - 1) * parseInt(this.state.cercaRegistres, 10) + 1) +
                            t('carpeta_paginacion_2') +
                            (((parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.total_items, 10))
                                    ? parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)
                                    : this.state.total_items
                            ) +
                            t('carpeta_paginacion_3') +
                            this.state.total_items +
                            t('carpeta_paginacion_4')}
                        </div>
                        <Pagination style={{float: 'right', paddingRight: '0.7em'}} className="ocultarMobil">
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

                    this.state.data.map(({   descripcionTramite,
                                             fechaInicio,
                                             pendiente,
                                             numero,
                                             url,
                                             versionSistra,
                                             mostraModal,
                                             tipo
                                         },i) => {

                        if (numero !== '')
                            cardTramits.push(
                                <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil" key={i} tabIndex={502+i} data-numero={numero} data-url={url}
                                     data-version={versionSistra} style={cursorPointer} data-mostramodal={$.siNo(mostraModal)}
                                     data-pending={$.siNo(pendiente)} onClick={(e) => this.handleItemClick(numero)}>
                                    <div className="col-sm-1 float-left">
                                        <span className="oi oi-pencil iconaFormApp" title={t('sistraDates')} style={{verticalAlign: 'sub'}}/>
                                    </div>
                                    <div className="col-sm-10 float-right">
                                        <p className="card-text pl-1 mt-0 font-weight-bold" style={{color: 'rgb(102, 102, 102)'}}>{descripcionTramite}</p>
                                        <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{$.dateFormat(fechaInicio)}</p>
                                        <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">{$.estatTramit(pendiente, mostraModal, tipo, numero)}</h3>
                                    </div>
                                </div>
                            )

                        if (numero === '' && pendiente && mostraModal)
                            cardTramits.push(
                                <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"  key={i} tabIndex={502+i} data-numero={numero} data-url={url}
                                     data-version={versionSistra} style={cursorPointer} data-mostramodal={$.siNo(mostraModal)}
                                     data-pending={$.siNo(pendiente)} onClick={(e) => this.openModalConfirm(url)}>
                                    <div className="col-sm-1 float-left">
                                        <span className="oi oi-circle-check iconaFormApp" title={t('sistraDates')} style={{verticalAlign: 'sub'}}/>
                                    </div>
                                    <div className="col-sm-10 float-right">
                                        <p className="card-text pl-1 mt-0 font-weight-bold" style={{color: 'rgb(102, 102, 102)'}}>{descripcionTramite}</p>
                                        <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{$.dateFormat(fechaInicio)}</p>
                                        <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">{$.estatTramit(pendiente, mostraModal, tipo, numero)}</h3>
                                    </div>
                                </div>
                            )

                        if (numero === '' && (!pendiente || !mostraModal))
                            cardTramits.push(
                                <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"  key={i} tabIndex={502+i} data-numero={numero} data-url={url}
                                     data-version={versionSistra} style={cursorPointer} data-mostramodal={$.siNo(mostraModal)}
                                     data-pending={$.siNo(pendiente)} onClick={(e) => window.open(url, '_blank')}>
                                    <div className="col-sm-1 float-left">
                                        <span className="oi oi-pencil iconaFormApp" title={t('sistraDates')} style={{verticalAlign: 'sub'}}/>
                                    </div>
                                    <div className="col-sm-10 float-right">
                                        <p className="card-text pl-1 mt-0 font-weight-bold" style={{color: 'rgb(102, 102, 102)'}}>{descripcionTramite}</p>
                                        <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{$.dateFormat(fechaInicio)}</p>
                                        <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">{$.estatTramit(pendiente, mostraModal, tipo, numero)}</h3>
                                    </div>
                                </div>
                            )
                    })

                    cardTramits.push(<div className="visioMobil">
                        <div style={{float: 'left', marginTop: '9px;', width: '100%'}} className="visioMobil pb-4">
                            {t('carpeta_paginacion_1_App') +
                            ((parseInt(this.state.pagination_active, 10) - 1) * parseInt(this.state.cercaRegistres, 10) + 1) +
                            t('carpeta_paginacion_2') +
                            (((parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.total_items, 10))
                                    ? parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)
                                    : this.state.total_items
                            ) +
                            t('carpeta_paginacion_3_App') +
                            this.state.total_items +
                            t('carpeta_paginacion_4')}
                        </div>
                        <Pagination style={{float: 'left', paddingRight: '0.7em', width: '100%'}} size="lg">
                            {paginationNumbers}
                        </Pagination>
                        </div>
                    )


                } else if (this.state.total_items === 0 && this.state.data !== null) {
                    taulaTramits = <>
                        <div className="pt-3 alert alert-secondary" style={{float: 'left', width: '95%'}}
                             role="alert">{t('sistraBuid')}</div>
                    </>
                }

        }

        
        return (<>
                <div className="titolPaginaApp visioMobil">
                    {this.props.titles[i18n.language]}
                </div>
                <div className="infoNoMenu">
                    {this.state.numeroRegistro == null && <>
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
                            {taulaTramits}
                            {cardTramits}
                        </div>
                        <div className="col-md-12 border-0 float-left p-0" id="botoTornarSistra" style={{ marginTop: '20px' }}>
                            <button type="button" data-toggle="modal" onClick={() => {
                                window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                            }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarSistra">{t('sistraTornar')}</button>
                        </div>
                    </>}
                    {this.state.numeroRegistro != null && detallRegistreContainer}
                </div>
            </>
            );
            
    }
}

export default withTranslation()(Sistra);