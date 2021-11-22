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

/**
 *  @author jpernia
 */

class Sistra extends Component {

    constructor(props) {
        super(props);

        let startDateObj = new Date();
        const endDateObj = new Date();
        startDateObj.setMonth(endDateObj.getMonth()-6);

        this.state = {
            isLoaded: false,
            data: null,
            dataInici: startDateObj,
            dataFi: endDateObj,
            estat: 'A',
            pagination_active: 1,
            pagination_total_items: 10,
            total_items: 0,
            error: false
        };

        this.handleChangeDataInici = this.handleChangeDataInici.bind(this);
        this.handleChangeDataFi = this.handleChangeDataFi.bind(this);
        this.handleChangeEstat = this.handleChangeEstat.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        const getLocale = locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`)
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

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

    handlePagination(event){
        let newPageNumber =  event.target.text;

        this.setState({
            ...this.state,
            pagination_active: newPageNumber,
            error: false
        });

        const params = {
            dataInici: this.state.dataInici,
            dataFi: this.state.dataFi,
            estat: this.state.estat,
            pageNumber: newPageNumber-1
        };

        const url2 = this.props.pathtoservei;

        axios.get(url2, {params: params}).then( (response) => {

            if (response.data != null){
                this.setState({
                    ...this.state,
                    data: response.data.tramits,
                    pagination_active: newPageNumber,
                    total_items: response.data.totalRegistres,
                    pagination_total_items: response.data.registresPagina
                });
            }


        }).catch( error => {
            console.log('Error axios', error);
            this.setState({
                ...this.state,
                error: true
            });
        } );

    }


    async handleSubmit(e) {

        const { t } = this.props;
        let validatFormulari = this.validaFormulari();

        if(validatFormulari) {

            this.setState({
                ...this.state,
                isLoaded: false
            });

            e.preventDefault();

            const url2 = this.props.pathtoservei;

            const params = {
                dataInici: this.state.dataInici,
                dataFi: this.state.dataFi,
                estat: this.state.estat,
                pageNumber: this.state.pagination_active - 1
            };

            await axios.get(url2, {params: params}).then((response) => {

                this.setState({
                    ...this.state,
                    error: false
                });

                if (response.data != null) {
                    this.setState({
                        ...this.state,
                        isLoaded: true,
                        data: response.data.tramits,
                        pagination_active: 1,
                        total_items: response.data.totalRegistres,
                        pagination_total_items: response.data.registresPagina
                    });

                }

            }).catch(error => {
                console.log('Error axios', error);
                this.setState({
                    ...this.state,
                    error: true
                });
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

    goParentTo(url) {
        window.open(url, '_blank');
        $('#myModal').modal('hide');
    }


    componentDidUpdate() {

        const { t } = this.props;

        let taulaTramits;
        var tamanyTaula = { width: '99%'};
        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};

        if(this.state.total_items !== 0 && this.state.data !== null) {
            let paginationNumbers = [];
            for (let number = 1; number <= Math.ceil(this.state.total_items/10); number++) {
                paginationNumbers.push(<Pagination.Item key={number} active={number === this.state.pagination_active} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
                // console.log("Pagination active debug:", number, this.state.pagination_active , number === this.state.pagination_active );
            }

            taulaTramits = <>
                <Table responsive striped bordered hover style={tamanyTaula}>
                    <thead className="table-success">
                    <tr>
                        <th>{t('sistraTramit')}</th>
                        <th style={tamanyData}>{t('sistraData')}</th>
                        <th>{t('sistraEstat')}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.data.map(({descripcionTramite, fechaInicio, pendiente, numero, url, versionSistra, mostraModal, registrado}) => {
                        return <tr className="clickableRow" data-numero={numero} data-url={url} onClick={()=>this.obrirTramit(url)} data-version={versionSistra} style={cursorPointer} data-mostramodal={$.siNo(mostraModal)} data-pending={$.siNo(pendiente)} tabIndex="511">
                            <td>{descripcionTramite}</td>
                            <td data-order={$.dateOrder(fechaInicio)}>{$.dateFormat(fechaInicio)}</td>
                            <td>{$.estatTramit(pendiente, mostraModal, registrado)}</td>
                        </tr>
                    })}
                    </tbody>
                </Table>
                <Pagination>
                    {paginationNumbers}
                </Pagination>
            </>
        } else{
            if(this.state.total_items === 0 && this.state.data !== null) {
                taulaTramits = <div className="pt-3 alert alert-secondary" style={{ float: 'left', width: '95%'}} role="alert">{t('sistraBuid')}</div>
            }
        }

        $('.clickableRow')
            .click(function(event){
                console.log("CLICK");
                event.preventDefault();
                carregarUrl($(this));
            })
            .keypress(function(event){
                console.log("KEYPRESS");
                event.preventDefault();
                carregarUrl($(this));
            });

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

        function carregarUrl(obj) {
            if (obj.data('numero') !== '') {
                window.location.replace(obj.data('url'));
            }else if (obj.data('pending') === 'S' && obj.data('mostramodal') === 'S'){
                openModalConfirm(obj.data('url'));
            }else{
                window.open(obj.data('url'), '_blank');
            }
        }

        function openModalConfirm(url) {

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
                    + '<button class="btn btn-success" type="button" onclick="this.goParentTo(\''+url+'\')" tabindex="511">'+ t('sistraModalBotoContinuar') +'</button>'
                    + '<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true" tabindex="512">'+ t('sistraModalBotoCancelar') +'</button>'

                    + '</div>'

                    + '</div>'
                    + '</div>'
                    + '</div>');

            $('#myModal').modal('show');
        }

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

        $.estatTramit = function(pendiente, mostraModal, registrado) {
            if (pendiente) {
                if (mostraModal) {
                    return t('sistraNoFinalizatPresencial');
                }else{
                    return t('sistraNoFinalitzat');
                }
            }else{
                if (registrado) {
                    return t('sistraRegistrat');
                }else{
                    return t('sistraFinalitzat');
                }
            }
        }

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};
        var tamanyTaula = { width: '99%'};

        let content;
        let taulaTramits;

        if (!isLoaded) {
             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
            content =
            <>
                <Form id="fechaBusqueda" style={{ marginBottom: '20px'}}>
                    <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }}>
                        <Row>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>{t('sistraDataInici')}</Form.Label>

                                    <DatePicker
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
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>{t('sistraDataFi')}</Form.Label>
                                    <DatePicker
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
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label id="estado">{t('sistraEstat')}</Form.Label>
                                    <Form.Select id="tramiteFinalizado"
                                                 name="tramiteFinalizado" className="form-control form-control-sm focusIn"
                                                 value={this.state.estat}
                                                 tabindex="503"
                                                 aria-labelledby="estado"
                                                 onChange={(e) => {this.handleChangeEstat(e); }}>
                                        <option value="A" selected="selected">{t('sistraTots')}</option>
                                        <option value="S">{t('sistraFinalitzat')}</option>
                                        <option value="N">{t('sistraNoFinalitzat')}</option>
                                        <option value="R">{t('sistraRegistrat')}</option>
                                    </Form.Select>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                                <div className="alert alert-danger" role="alert" id="errorMsg"/>
                            </div>
                        </Row>
                        <Row>
                            <Button type="submit" className="btn btn-primary carpeta-btn ml-3 mt-2" onClick={(e) => {this.handleSubmit(e)}} tabindex="504">{t('sistraCercaBoto')}</Button>
                        </Row>
                    </Container>
                </Form>
            </>

            const Header = [t('sistraTramit'), t('sistraData'), t('sistraEstat')];

            if(this.state.total_items !== 0 && this.state.data !== null) {
                let paginationNumbers = [];
                for (let number = 1; number <= Math.ceil(this.state.total_items/10); number++) {
                    paginationNumbers.push(<Pagination.Item key={number} active={number.toString() === this.state.pagination_active.toString()} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
                }

                taulaTramits = <>
                    <Table responsive striped bordered hover style={tamanyTaula}>
                        <thead className="table-success">
                        <tr>
                            <th>{t('sistraTramit')}</th>
                            <th style={tamanyData}>{t('sistraData')}</th>
                            <th>{t('sistraEstat')}</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.data.map(({descripcionTramite, fechaInicio, pendiente, numero, url, versionSistra, mostraModal, registrado}) => {
                            // return <tr className="clickableRow" data-numero={numero} data-url={url} onClick={()=>this.obrirTramit(url)} data-version={versionSistra} style={cursorPointer} data-mostramodal={$.siNo(mostraModal)} data-pending={$.siNo(pendiente)} tabIndex="511">
                            return <tr className="clickableRow" data-numero={numero} data-url={url} data-version={versionSistra} style={cursorPointer} data-mostramodal={$.siNo(mostraModal)} data-pending={$.siNo(pendiente)} tabIndex="511">
                                <td>{descripcionTramite}</td>
                                <td data-order={$.dateOrder(fechaInicio)}>{$.dateFormat(fechaInicio)}</td>
                                <td>{$.estatTramit(pendiente, mostraModal, registrado)}</td>
                            </tr>
                        })}
                        </tbody>
                    </Table>
                    <Pagination>
                        {paginationNumbers}
                    </Pagination>
                </>

            } else{
                if(this.state.total_items === 0 && this.state.data !== null) {
                    taulaTramits = <div className="pt-3 alert alert-secondary" style={{ float: 'left', width: '95%'}} role="alert">{t('sistraBuid')}</div>
                }
            }

        }

        
        return (
            <div className="infoNoMenu">
                <h2 className="titol h2">{this.props.titles[i18n.language]}</h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                    <div className="infoNoMenu">
                        <div className="col-md-12 border-0 float-left p-0">
                            {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}
                            {content}
                        </div>
                    </div>
                </div>
                {taulaTramits}
                <div className="col-md-12 border-0 float-left p-0" id="botoTornarSistra" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarSistra">{t('sistraTornar')}</button>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(Sistra);