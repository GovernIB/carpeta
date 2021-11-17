import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from './i18n';
import axios from "axios";
import Table from 'react-bootstrap/Table';
import Pagination  from 'react-bootstrap/Pagination';

/**
 *  @author jpernia
 */

class NotificacionsSistra extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            dataComunicacions: null,
            pagination_active: 1,
            pagination_total_items: 10,
            total_items: 0,
            error: false
        };

    }

    componentDidMount() {

        const url = this.props.pathtoservei;

        const params = {
            pageNumber: 0
        };

        axios.get(url, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    total_items: response.data.totalRegistres,
                    isLoaded: true
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

    handlePagination(event){

        let newPageNumber =  event.target.text;

        this.setState({
            ...this.state,
            pagination_active: newPageNumber,
            error: false
        });

        const params = {
            pageNumber: newPageNumber-1
        };
        const url2 = this.props.pathtoservei;

        axios.get(url2, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    total_items: response.data.totalRegistres,
                    pagination_total_items: response.data.registresPagina,
                    pagination_active: newPageNumber,
                    isLoaded: true
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

    componentDidUpdate() {
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

        $("tr").each(function(i) {
            $(this).removeAttr("role");
        });

        function carregarUrl(obj) {
            window.open(obj.data('url'), '_blank');
        }
    }


    render() {

        $.dateOrder = function(dateObject) {
            var day = dateObject.orig_day;
            var month = dateObject.orig_month;
            var year = dateObject.orig_year;
            var hour = dateObject.orig_hour;
            var minute = dateObject.orig_minute;
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
            var day = dateObject.orig_day;
            var month = dateObject.orig_month;
            var year = dateObject.orig_year;
            var hour = dateObject.orig_hour;
            var minute = dateObject.orig_minute;
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

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};
        var tamanyTaula = { width: '99%'};

        let content;
        let taulaNotificacionsSistra;


        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                <div className="loader"/>
            </div>;
        } else {
            content = "";

            if(this.state.dataComunicacions !== null) {
                let paginationNumbers = [];
                for (let number = 1; number <= Math.ceil(this.state.total_items/10); number++) {
                    paginationNumbers.push(<Pagination.Item key={number} active={number === this.state.pagination_active} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
                }

                taulaNotificacionsSistra = <>
                    <Table responsive striped bordered hover style={tamanyTaula}>
                        <thead className="table-success">
                        <tr>
                            <th>{t('notificacionsSistraComunicacionDescripcion')}</th>
                            <th style={tamanyData}>{t('notificacionsSistraComunicacionFecha')}</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.dataComunicacions.map(({descripcion, fecha, url}) => {
                            return <tr className="clickableRow" data-target="_blank" data-url={url} style={cursorPointer} tabIndex="511">
                                <td>{descripcion}</td>
                                <td data-order={$.dateOrder(fecha)}>{$.dateFormat(fecha)}</td>
                            </tr>
                        })}
                        </tbody>
                    </Table>
                    <Pagination>
                        {paginationNumbers}
                    </Pagination>
                </>

            } else{
                    taulaNotificacionsSistra = <div className="pt-3 alert alert-secondary" style={{ float: 'left', width: '95%'}} role="alert">{t('notificacionsSistraBuid')}</div>
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
                            <div className="card-body imc--llista--capses pl-0">
                                {this.state.isLoaded && taulaNotificacionsSistra }
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-md-12 border-0 float-left p-0" id="botoTornarNotificacionsSistra" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport" tabIndex="520" aria-labelledby="notificacionsSistraTornar">{t('notificacionsSistraTornar')}</button>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(NotificacionsSistra);