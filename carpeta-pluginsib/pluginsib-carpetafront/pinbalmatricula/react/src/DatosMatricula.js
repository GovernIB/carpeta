import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import DatePicker from 'react-datepicker';

/**
 *  @author jpernia
 */

class DatosMatricula extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
            documentAutenticat: sessionStorage.getItem('usuariDNI'),
            documentTitular: null,
            dataNaixementTitular: null,
            nomTitular: null,
            primerLlinatgeTitular: null,
            segonLlinatgeTitular: null,
            tipusDocumentTitular: '1',
            radioSelectedOption: 'option1',
            mostrarInfo: false,
            error: false
        };

        this.handleChangeDataNaixement = this.handleChangeDataNaixement.bind(this);
        this.handleChangeDocument = this.handleChangeDocument.bind(this);
        this.handleChangeTipusDocument = this.handleChangeTipusDocument.bind(this);
        this.handleChangeNom = this.handleChangeNom.bind(this);
        this.handleChangePrimerLlinatge = this.handleChangePrimerLlinatge.bind(this);
        this.handleChangeSegonLlinatge = this.handleChangeSegonLlinatge.bind(this);
        this.handleChangeRadio = this.handleChangeRadio.bind(this);
        this.mostrarDocument = this.mostrarDocument.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        const getLocale = locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`)
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

        this.fechaNacimientoTitular;
        this.documentacionTitular;
        this.documentacionAutenticado;
        this.tipoDocumentacionTitular;
        this.nombreTitular;
        this.primerApellidoTitular;
        this.segundoApellidoTitular;
        this.radioOpcion;
    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    handleChangeDataNaixement(e) {
        $('#fechaNacimiento').removeClass('borderError');
        this.setState({
            ...this.state,
            dataNaixementTitular: e
        });
    }

    handleChangeTipusDocument(e) {
        this.setState({
            ...this.state,
            tipusDocumentTitular: e.target.value
        });
    }

    handleChangeDocument(e) {
        $('#dni').removeClass('borderError');
        this.setState({
            ...this.state,
            documentTitular: e.target.value
        });
    }

    handleChangeNom(e) {
        $('#nomTitular').removeClass('borderError');
        this.setState({
            ...this.state,
            nomTitular: e.target.value
        });
    }

    handleChangePrimerLlinatge(e) {
        $('#primerLliTitular').removeClass('borderError');
        this.setState({
            ...this.state,
            primerLlinatgeTitular: e.target.value
        });
    }

    handleChangeSegonLlinatge(e) {
        this.setState({
            ...this.state,
            segonLlinatgeTitular: e.target.value
        });
    }

    handleChangeRadio(e) {
        $('#errorMsg').html("");
        $('#errorContainer').addClass('ocult');

        this.setState({
            ...this.state,
            mostrarInfo: false,
            documentTitular: null,
            dataNaixementTitular: null,
            nomTitular: null,
            primerLlinatgeTitular: null,
            segonLlinatgeTitular: null,
            tipusDocumentTitular: '1',
            radioSelectedOption: e.target.value
        });
    }

    mostrarDocument(){
        $.dateFormat = function(dateObject) {
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

            return day + "/" + month + "/" + year;
        };

        const { t } = this.props;
        return this.state.radioSelectedOption === 'option1' ? this.state.documentAutenticat
            : this.state.radioSelectedOption === 'option2' ? this.state.documentTitular
                : " " + this.state.nomTitular + " " + this.state.primerLlinatgeTitular + " " + this.state.segonLlinatgeTitular + " " + $.dateFormat(this.state.dataNaixementTitular);
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
                dataNaixementTitular: this.state.dataNaixementTitular,
                documentTitular: this.state.documentTitular,
                documentAutenticat: this.state.documentAutenticat,
                tipusDocumentTitular: this.state.tipusDocumentTitular,
                nomTitular: this.state.nomTitular,
                primerLlinatgeTitular: this.state.primerLlinatgeTitular,
                segonLlinatgeTitular: this.state.segonLlinatgeTitular,
                radioSelectedOption: this.state.radioSelectedOption
            };

            this.fechaNacimientoTitular = this.state.dataNaixementTitular;
            this.documentacionTitular = this.state.documentTitular;
            this.documentacionAutenticado = this.state.documentAutenticat;
            this.tipoDocumentacionTitular = this.state.tipusDocumentTitular;
            this.nombreTitular = this.state.nomTitular;
            this.primerApellidoTitular = this.state.primerLlinatgeTitular;
            this.segundoApellidoTitular = this.state.segonLlinatgeTitular;
            this.radioOpcion = this.state.radioSelectedOption;

            await axios.get(url2, {params: params}).then((res) => {

                this.setState({
                    ...this.state,
                    isLoaded: true,
                    mostrarInfo: true,
                    error: false
                });

                if (res.data != null) {
                    this.setState({
                        ...this.state,
                        data: res.data
                    });
                } else{
                    this.setState({
                        ...this.state,
                        data: null
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

    validaFormulari(){

        const { t } = this.props;

        if(this.state.radioSelectedOption === 'option2'){
            if(this.state.documentTitular === null) {
                $('#errorMsg').html(t('pinbalMatriculaErrorDocument'));
                $('#errorContainer').removeClass('ocult');
                $('#dni').addClass('borderError');
                return false;
            }
        }else if(this.state.radioSelectedOption === 'option3'){
            if(this.state.nomTitular === null) {
                $('#errorMsg').html(t('pinbalMatriculaErrorNom'));
                $('#errorContainer').removeClass('ocult');
                $('#nomTitular').addClass('borderError');
                return false;
            }
            if(this.state.primerLlinatgeTitular === null) {
                $('#errorMsg').html(t('pinbalMatriculaErrorPrimerLLi'));
                $('#errorContainer').removeClass('ocult');
                $('#primerLliTitular').addClass('borderError');
                return false;
            }
            if(this.state.dataNaixementTitular === null) {
                $('#errorMsg').html(t('pinbalMatriculaErrorData'));
                $('#errorContainer').removeClass('ocult');
                $('#fechaNacimiento').addClass('borderError');
                return false;
            }
        }

        $('#errorMsg').html("");
        $('#errorContainer').addClass('ocult');

        $('#dni').removeClass('borderError');
        $('#nomTitular').removeClass('borderError');
        $('#primerLliTitular').removeClass('borderError');
        $('#fechaNacimiento').removeClass('borderError');

        return true;
    }


    componentDidUpdate() {

        $("#radio1").attr("tabindex","501");
        $("#radio2").attr("tabindex","502");
        $("#radio3").attr("tabindex","503");

        $("#dni").attr("tabindex","505");

        $("#nomTitular").attr("tabindex","506");
        $("#primerLliTitular").attr("tabindex","507");
        $("#segonLliTitular").attr("tabindex","508");
        $("#fechaNacimiento").attr("tabindex","509");

        const { t } = this.props;

        let content;
        let alerta;
        let data = this.state.data;


        if (data !== null) {

            if (data.codRespuesta === '0') {
                alerta = <div className="alert alert-success" role="alert">
                    {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigoInicio')} {this.mostrarDocument()} {t('pinbalMatriculaCodigo0')}
                </div>;

                let nom = '';
                let llinatge1 = '';
                let llinatge2 = '';
                let document = '';
                let dataNaixe = '';
                let cursVigent = '';
                let cursFutur = '';

                if (data.alumno.nombre !== undefined) {
                    nom = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaNom')}</dt><dd className="col-sm-7">{data.alumno.nombre}</dd></div>
                }
                if (data.alumno.apellido1 !== undefined) {
                    llinatge1 = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaApellido1')}</dt><dd className="col-sm-7">{data.alumno.apellido1}</dd></div>
                }
                if (data.alumno.apellido2 !== undefined) {
                    llinatge2 = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaApellido2')}</dt><dd className="col-sm-7">{data.alumno.apellido2}</dd></div>
                }
                if (data.alumno.idTitular.documentacion !== undefined) {
                    document = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaDocument')}</dt><dd className="col-sm-7">{data.alumno.idTitular.tipoDocumentacion} {data.alumno.idTitular.documentacion}</dd></div>
                }
                if (data.alumno.fechaNacimiento !== undefined) {
                    dataNaixe = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaNaixement')}</dt><dd className="col-sm-7">{data.alumno.fechaNacimiento}</dd></div>
                }
                if (data.cursoMatriculaVigente !== undefined) {
                    cursVigent = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaVigent')}</dt><dd className="col-sm-7">{data.cursoMatriculaVigente}</dd></div>
                }
                if (data.cursoMatriculaFutura !== undefined) {
                    cursFutur = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaFutura')}</dt><dd className="col-sm-7">{data.cursoMatriculaFutura}</dd></div>
                }

                content = <div className="contenedorInfoPersonal mt-2">
                    <dl className="row">
                        {nom}
                        {llinatge1}
                        {llinatge2}
                        {document}
                        {dataNaixe}
                        {cursVigent}
                        {cursFutur}
                    </dl>
                </div>;

            } else if (data.codRespuesta === '') {
                alerta = <div className="alert alert-danger" role="alert">
                    {t('pinbalMatriculaError')} : {data.error}
                </div>;
            } else if (data.codRespuesta === '1') {
                alerta = <div className="alert alert-danger" role="alert">
                    {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigoInicio')} {this.mostrarDocument()} {t('pinbalMatriculaCodigo1')}
                </div>;
            } else if (data.codRespuesta === '2') {
                alerta = <div className="alert alert-warning" role="alert">
                    {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigo2')} {this.mostrarDocument()} {t('pinbalMatriculaCodigoFin')}
                </div>;
            } else if (data.codRespuesta === '3') {
                alerta = <div className="alert alert-warning" role="alert">
                    {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigo3')} {this.mostrarDocument()} {t('pinbalMatriculaCodigoFin')}
                </div>;
            }
        }

    }


    render() {

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let formulari;
        let content;
        let alerta;

        formulari = <>
            <Form id="dadesMatricula" style={{ marginBottom: '20px'}}>
                <Row style={{ width: 'fit-content'}}>
                    <Col className="col-xs-12 col-mb-6">
                        <Form.Select id="radioOpcio"
                                     name="radioOpcio" className="form-control form-control-sm focusIn"
                                     value={this.state.radioSelectedOption}
                                     tabindex="503"
                                     onChange={(e) => {
                                         this.handleChangeRadio(e);
                                     }}>
                            <option value="option1"
                                    selected={this.state.radioSelectedOption === 'option1'}>{t('pinbalMatriculaDadesPropies')}</option>
                            <option value="option2"
                                    selected={this.state.radioSelectedOption === 'option2'}>{t('pinbalMatriculaFillDocument')}</option>
                            <option value="option3"
                                    selected={this.state.radioSelectedOption === 'option3'}>{t('pinbalMatriculaFillData')}</option>
                        </Form.Select>
                    </Col>
                </Row>
                <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }}>

                    {this.state.radioSelectedOption === 'option2' &&
                    <Row className="pt-2">
                        <Col className="col-xs-12 mb-4">
                            <Form.Group>
                                <Form.Label id="tipoDocum">{t('pinbalMatriculaTipusDocument')}</Form.Label>
                                <Form.Select id="tipusDocument"
                                             name="tipusDocument" className="form-control form-control-sm focusIn"
                                             value={this.state.tipusDocument}
                                             tabindex="504"
                                             aria-labelledby="tipoDocum"
                                             onChange={(e) => {
                                                 this.handleChangeTipusDocument(e);
                                             }}>
                                    <option value="1"
                                            selected={this.state.tipusDocument === '1'}>{t('pinbalMatriculaNIF')}</option>
                                    <option value="2"
                                            selected={this.state.tipusDocument === '2'}>{t('pinbalMatriculaNIE')}</option>
                                    <option value="3"
                                            selected={this.state.tipusDocument === '3'}>{t('pinbalMatriculaPassaport')}</option>
                                    <option value="4"
                                            selected={this.state.tipusDocument === '4'}>{t('pinbalMatriculaComunitari')}</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
                        <Col className="col-xs-12 mb-4">
                            <Form.Group>
                                <Form.Label>{t('pinbalMatriculaDNI')}</Form.Label>
                                <Form.Control
                                    type="text" id="dni" name="dni"
                                    maxlength="12"
                                    tabIndex="505"
                                    value={this.state.documentTitular}
                                    onChange={(e) => {
                                        this.handleChangeDocument(e);
                                    }}
                                    className="form-control form-control-sm focusIn"
                                />
                            </Form.Group>
                        </Col>
                    </Row>
                    }
                    {this.state.radioSelectedOption === 'option3' &&
                    <>
                        <Row className="pt-2">
                            <Col md={3} xs={12} className="ajustaForm">
                                <Form.Group>
                                    <Form.Label style={{ float: 'left', paddingTop: '0.5em'}}>{t('pinbalMatriculaNomTitular')}</Form.Label>
                                    <Form.Control
                                        type="text" id="nomTitular"
                                        tabIndex="506"
                                        value={this.state.nomTitular}
                                        onChange={(e) => {
                                            this.handleChangeNom(e);
                                        }}
                                        className="form-control form-control-sm focusIn"
                                    />
                                </Form.Group>
                            </Col>
                            <Col md={3} xs={12} className="ajustaForm">
                                <Form.Group>
                                    <Form.Label style={{ float: 'left', paddingTop: '0.5em'}}>{t('pinbalMatriculaPrimerLliTitular')}</Form.Label>
                                    <Form.Control
                                        type="text" id="primerLliTitular"
                                        tabIndex="507"
                                        value={this.state.primerLlinatgeTitular}
                                        onChange={(e) => {
                                            this.handleChangePrimerLlinatge(e);
                                        }}
                                        className="form-control form-control-sm focusIn"
                                    />
                                </Form.Group>
                            </Col>
                            <Col md={3} xs={12} className="ajustaForm">
                                <Form.Group>
                                    <Form.Label style={{ float: 'left', paddingTop: '0.5em'}}>{t('pinbalMatriculaSegonLliTitular')}</Form.Label>
                                    <Form.Control
                                        type="text" id="segonLliTitular"
                                        tabIndex="508"
                                        value={this.state.segonLlinatgeTitular}
                                        onChange={(e) => {
                                            this.handleChangeSegonLlinatge(e);
                                        }}
                                        className="form-control form-control-sm focusIn"
                                    />
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row className="pt-2">
                            <Col md={2} xs={12} className="ajustaForm70">
                                <Form.Group>
                                    <Form.Label style={{ float: 'left', paddingTop: '0.5em'}}>{t('pinbalMatriculaNaixement')}</Form.Label>
                                    <DatePicker
                                        selected={this.state.dataNaixementTitular}
                                        onChange={(e) => this.handleChangeDataNaixement(e)}
                                        name="fechaNacimiento"
                                        id="fechaNacimiento"
                                        dateFormat="dd/MM/yyyy"
                                        className="form-control form-control-sm estilCalendar focusIn"
                                        locale={this.locale}
                                        showYearDropdown={true}
                                        preventOpenOnFocus={true}
                                        popperPlacement="top"
                                    />
                                </Form.Group>
                            </Col>
                        </Row>
                    </>
                    }
                    <Row>
                        <div id="errorContainer" className="pb-2 pt-2 ml-3 mr-0 ocult" style={{width: '100%'}}>
                            <div className="alert alert-danger" role="alert" id="errorMsg"/>
                        </div>
                    </Row>
                    <Row>
                        <Button type="submit" className="btn btn-primary carpeta-btn ml-3 mt-2" onClick={(e) => {this.handleSubmit(e)}} tabindex="510">{t('pinbalMatriculaCercaBoto')}</Button>
                    </Row>
                </Container>
            </Form>
        </>

        if (!isLoaded) {
             alerta='';
             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
            const data = this.state.data;

            if (this.state.error) {
                alerta='';
                content = <div className="alert alert-danger" role="alert">{data.error}</div>;
            } else {

                if (data !== null && this.state.mostrarInfo) {

                    if (data.codRespuesta === '0') {
                        alerta = <div className="alert alert-success" role="alert">
                            {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigoInicio')} {this.mostrarDocument()} {t('pinbalMatriculaCodigo0')}
                        </div>;

                        let nom = '';
                        let llinatge1 = '';
                        let llinatge2 = '';
                        let document = '';
                        let dataNaixe = '';
                        let cursVigent = '';
                        let cursFutur = '';

                        if (data.alumno.nombre !== undefined) {
                            nom = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaNom')}</dt><dd className="col-sm-7">{data.alumno.nombre}</dd></div>
                        }
                        if (data.alumno.apellido1 !== undefined) {
                            llinatge1 = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaApellido1')}</dt><dd className="col-sm-7">{data.alumno.apellido1}</dd></div>
                        }
                        if (data.alumno.apellido2 !== undefined) {
                            llinatge2 = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaApellido2')}</dt><dd className="col-sm-7">{data.alumno.apellido2}</dd></div>
                        }
                        if (data.alumno.idTitular.documentacion !== undefined) {
                            document = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaDocument')}</dt><dd className="col-sm-7">{data.alumno.idTitular.tipoDocumentacion} {data.alumno.idTitular.documentacion}</dd></div>
                        }
                        if (data.alumno.fechaNacimiento !== undefined) {
                            dataNaixe = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaNaixement')}</dt><dd className="col-sm-7">{data.alumno.fechaNacimiento}</dd></div>
                        }
                        if (data.cursoMatriculaVigente !== undefined) {
                            cursVigent = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaVigent')}</dt><dd className="col-sm-7">{data.cursoMatriculaVigente}</dd></div>
                        }
                        if (data.cursoMatriculaFutura !== undefined) {
                            cursFutur = <div className="pt-2"><dt className="col-sm-3">{t('pinbalMatriculaFutura')}</dt><dd className="col-sm-7">{data.cursoMatriculaFutura}</dd></div>
                        }

                        content = <div className="contenedorInfoPersonal mt-2">
                            <dl className="row">
                                {nom}
                                {llinatge1}
                                {llinatge2}
                                {document}
                                {dataNaixe}
                                {cursVigent}
                                {cursFutur}
                            </dl>
                        </div>;

                    } else if (data.codRespuesta === '') {
                        alerta = <div className="alert alert-danger" role="alert">
                            {t('pinbalMatriculaError')} : {data.error}
                        </div>;
                    } else if (data.codRespuesta === '1') {
                        alerta = <div className="alert alert-danger" role="alert">
                            {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigoInicio')} {this.mostrarDocument()} {t('pinbalMatriculaCodigo1')}
                        </div>;
                    } else if (data.codRespuesta === '2') {
                        alerta = <div className="alert alert-warning" role="alert">
                            {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigo2')} {this.mostrarDocument()} {t('pinbalMatriculaCodigoFin')}
                        </div>;
                    } else if (data.codRespuesta === '3') {
                        alerta = <div className="alert alert-warning" role="alert">
                            {t('pinbalMatriculaFecha')} {data.fechaProceso} : {t('pinbalMatriculaCodigo3')} {this.mostrarDocument()} {t('pinbalMatriculaCodigoFin')}
                        </div>;
                    }
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
                                {formulari}
                                {alerta}
                                {content}
                            <div className="col-md-12 border-0 float-left p-0" id="botoTornarMatricula" style={{ marginTop: '20px' }}>
                                <button type="button" data-toggle="modal" onClick={() => {
                                    window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                                }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarMatricula">{t('pinbalMatriculaTornar')}</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(DatosMatricula);