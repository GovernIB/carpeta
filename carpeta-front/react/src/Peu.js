import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import DadesEntitat from './DadesEntitat';
import EnllasosXarxes from "./EnllasosXarxes";
import EnllasosPeuCentral from './EnllasosPeuCentral';
import {Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import axios from "axios";

class Peu extends Component {

	constructor(){
		super();
		this.state = {
			dadesSuport: [],
			modal: false
		};

		this.toggle = this.toggle.bind(this);
	}

	toggle() {
		this.setState(prevState => ({
			modal: !prevState.modal
		}));
	}

	componentDidMount() {
		var url = window.location.href + `webui/suport`;
		axios.get(url).then(res => {
			const dadesSuport = res.data;
			this.setState({ dadesSuport: dadesSuport });
		})
	}

	componentWillReceiveProps(lng) {
		var url = window.location.href + `webui/suport`;
		axios.get(url).then(res => {
			const dadesSuport = res.data;
			this.setState({ dadesSuport: dadesSuport });
		})
	}
	
	render() {

		var autenticat = this.props.autenticat;
		const {t} = this.props;

		const dadesSuport = this.state.dadesSuport;

		var suportWeb = dadesSuport.filter(s => s.tipus === '1').map((s, i) =>
			<li className="pb-2 liAjuda" key={i}>{t('suportConsulta')} <a href={s.valor}>{t('suportWeb')}</a></li>
		);
		var suportTelefon = dadesSuport.filter(s => s.tipus === '2').map((s, i) =>
			<li className="pb-2 liAjuda" key={i}>{t('suportCrida')} <p className="text-verd"> {s.valor}</p></li>
		);
		var suportMail = dadesSuport.filter(s => s.tipus === '3').map((s, i) =>
			<li className="pb-2 liAjuda" key={i}>{t('suportMail')} <a href={"mailto:'"+s.valor+"'"}>{s.valor}</a></li>
		);
		var suportFAQ = dadesSuport.filter(s => s.tipus === '4').map((s, i) =>
			<li className="pb-2 liAjuda" key={i}>{t('suportConsulta')} <a href={s.valor}>{t('suportFAQ')}</a></li>
		);
		var suportConsulta = dadesSuport.filter(s => s.tipus === '5').map((s, i) =>
			<li className="pb-2 liAjuda" key={i}>{t('suportConsultaTecnica')} <p className="text-verd"> {s.valor}</p></li>
		);
		var suportAutenticacio = dadesSuport.filter(s => s.tipus === '6').map((s, i) =>
			<li className="pb-2 liAjuda" key={i}>{t('suportAutenticacio')} <p className="text-verd"> {s.valor}</p></li>
		);


		return (
			<footer className="imc-peu">
				<div className="imc--contingut">

					<DadesEntitat/>

					<div className="imc-peu-opcions">

						<div className="pb-3 col2peu">
							<button type="button" id="dialogAjuda" data-toggle="modal" data-target="#ajudaModal" className="botoSuport">{t('suportAqui')}</button>
						</div>

						<EnllasosPeuCentral autenticat={autenticat} />
					</div>

					<div className="imc-peu-xarxes">
						<EnllasosXarxes/>
					</div>
				</div>

				<div className="modal fade" id="ajudaModal" tabIndex="-1" aria-labelledby="ajudaLabel" aria-hidden="true">
					<div className="modal-dialog">
						<div className="modal-content">
							<div className="modal-header">
								<p className="card-title titol h2">{t('suportTitol')}</p>
								<button type="button" className="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div className="modal-body">
								<ul className="pl-3 ajuda">
									{suportWeb}
									{suportTelefon}
									{suportMail}
									{suportFAQ}
									{suportConsulta}
									{suportAutenticacio}
								</ul>
							</div>
							<div className="modal-footer">
								<button type="button" className="botoSuport" data-dismiss="modal" aria-label="Close">{t('suportTanca')}</button>
							</div>
						</div>
					</div>
				</div>

			</footer>
		);
	}
}
export default withTranslation()(Peu);
