import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import DadesEntitat from './DadesEntitat';
import EnllasosXarxes from "./EnllasosXarxes";
import EnllasosPeuCentral from './EnllasosPeuCentral';
import {Link} from "react-router-dom";
import axios from "axios";
import i18n from 'i18next';

class Peu extends Component {

	constructor(){
		super();
		this.state = {
			dadesSuport: [],
			modal: false,
			error: false
		};

		this.toggle = this.toggle.bind(this);
		this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN PEU A ]" + lng+ "[");
        this.componentDidMount();
    }

	handleClick() {
		$("#ajudaModal").modal("hide");
	}

	toggle() {
		this.setState(prevState => ({
			modal: !prevState.modal
		}));
	}


	componentDidMount() {
		var baseURL = sessionStorage.getItem('contextPath');
		var url =  baseURL + `/webui/suport`;
		axios.get(url)
			.then(res => {
				this.setState({ dadesSuport: res.data });
			})
			.catch(error => {
				console.log(JSON.stringify(error));
				if (error.response) {
					console.log("error.response.data: " + error.response.data);
					console.log("error.response.status: " + error.response.status);
					console.log("error.response.headers: " + error.response.headers);
				}
				this.setState({
					error: JSON.stringify(error)
				});
			});
	}

	
	render() {
		

		var autenticat = sessionStorage.getItem('autenticat');
		const {t} = this.props;

		const dadesSuport = this.state.dadesSuport;
		let content;

		if (this.state.error) {
			content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
		} else {

			var suportWeb = dadesSuport.filter(s => s.tipus === '1').map((s, i) =>
				<li className="pb-2 liAjuda" key={i} id="linkSuportWeb">{t('suportConsulta')} <a href={s.valor} aria-labelledby="linkSuportWeb">{t('suportWeb')}</a></li>
			);
			var suportFAQ = dadesSuport.filter(s => s.tipus === '4').map((s, i) =>
				// <li className="pb-2 liAjuda" key={i} id="linkSuportFaq">{t('suportConsultaFAQ')} <a href={s.valor} aria-labelledby="linkSuportFaq">{t('suportFAQ')}</a></li>
				<li className="pb-2 liAjuda" key={i} id="linkSuportFaq">{t('suportConsultaFAQ')} <Link to={{pathname: `/faq`, nomPagina: 'menuPreguntesFrecuents' }} aria-labelledby="linkSuportFaq" onClick={this.handleClick.bind(this)}>{t('suportFAQ')}</Link></li>
			);
			var suportConsulta = dadesSuport.filter(s => s.tipus === '5').map((s, i) =>
				<li className="pb-2 liAjuda" key={i} id="linkSuportConsulta">{t('suportConsultaTecnica')} <a href={s.valor} aria-labelledby="linkSuportConsulta">{t('suportConsultaTecnicaEnllas')}</a></li>
			);
			var suportTelefon = dadesSuport.filter(s => s.tipus === '2').map((s, i) =>
				<li className="pb-2 liAjuda" key={i} id="linkSuportTlf">{t('suportCrida')} <a href={"tel:+34" + s.valor} aria-labelledby="linkSuportTlf">{s.valor}</a></li>
			);
			var suportMail = dadesSuport.filter(s => s.tipus === '3').map((s, i) =>
				<li className="pb-2 liAjuda" key={i} id="linkSuportMail">{t('suportMail')} <a
					href={"mailto:'" + s.valor + "'"} aria-labelledby="linkSuportMail">{s.valor}</a></li>
			);
			var suportAutenticacio = dadesSuport.filter(s => s.tipus === '6').map((s, i) =>
				<li className="pb-2 liAjuda" key={i} id="linkSuportAut">{t('suportAutenticacio')} <a href={"tel:+34" + s.valor} aria-labelledby="linkSuportAut">{s.valor}</a>
				</li>
			);

			content = '';
		}

		return (
			<footer className="imc-peu">
				<div className="imc--contingut">

					<DadesEntitat/>

					<div className="imc-peu-opcions">

						<div className="pb-3 col2peu" id="botoSuportModal">
							{/*<button type="button" id="dialogAjuda" data-toggle="modal" data-target="#ajudaModal" className="botoSuport" tabIndex="611" aria-labelledby="botoSuportModal">{t('suportAqui')}</button>*/}
							<button type="button" id="dialogAjuda" data-toggle="modal" data-target="#ajudaModal" className="botoSuport font13App" tabIndex="611" aria-labelledby="botoSuportModal">{t('suportAqui')}</button>
						</div>

						<EnllasosPeuCentral autenticat={autenticat} />
					</div>

					<div className="imc-peu-xarxes">
						<EnllasosXarxes/>
					</div>
				</div>

				<div className="modal fade" id="ajudaModal" tabIndex="-1" aria-hidden="true">
					<div className="modal-dialog ajudaModalApp">
						<div className="modal-content">
							<div className="modal-header">
								<p className="card-title titol h2 textModalAjudaApp">{t('suportTitol')}</p>
								<button type="button" className="close textModalAjudaApp" data-dismiss="modal" aria-label={t('tancar')} aria-describedby="tancarBotoSuport">
									<span aria-hidden="true">&times;</span>
									<span className="noVisible" id="tancarBotoSuport">{t('tancarAjuda')}</span>
								</button>
							</div>
							<div className="modal-body">
								<ul className="pl-3 ajuda textModalAjudaApp">
									{content}
									{suportWeb}
									{suportFAQ}
									{suportConsulta}
									{suportTelefon}
									{suportMail}
									{suportAutenticacio}
								</ul>
							</div>
							<div className="modal-footer" id="tancarSuportModal">
								<button type="button" className="botoSuport" data-dismiss="modal" aria-labelledby="tancarSuportModal">{t('suportTanca')}</button>
							</div>
						</div>
					</div>
				</div>

			</footer>
		);
	}
}
export default withTranslation()(Peu);
