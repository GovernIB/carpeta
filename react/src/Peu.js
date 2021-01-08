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

	componentWillMount() {
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

		var suport = <button type="button" onClick={this.toggle} className="botoSuport">{t('suportAqui')}</button>

		var tancar =  <button type="button" onClick={this.toggle} className="botoSuport">{t('suportTanca')}</button>;

		var suportWeb = dadesSuport.filter(s => s.tipus === '1').map(s =>
			<li className="pb-2">{t('suportConsulta')} <a href={s.valor}>{t('suportWeb')}</a></li>
		);
		var suportTelefon = dadesSuport.filter(s => s.tipus === '2').map(s =>
			<li className="pb-2">{t('suportCrida')} <p className="text-verd"> {s.valor}</p></li>
		);
		var suportMail = dadesSuport.filter(s => s.tipus === '3').map(s =>
			<li className="pb-2">{t('suportMail')} <a href={"mailto:'"+s.valor+"'"}>{s.valor}</a></li>
		);
		var suportFAQ = dadesSuport.filter(s => s.tipus === '4').map(s =>
			<li className="pb-2">{t('suportConsulta')} <a href={s.valor}>{t('suportFAQ')}</a></li>
		);
		var suportConsulta = dadesSuport.filter(s => s.tipus === '5').map(s =>
			<li className="pb-2">{t('suportConsultaTecnica')} <p className="text-verd"> {s.valor}</p></li>
		);
		var suportAutenticacio = dadesSuport.filter(s => s.tipus === '6').map(s =>
			<li className="pb-2">{t('suportAutenticacio')} <p className="text-verd"> {s.valor}</p></li>
		);


		return (
			<footer className="imc-peu">
				<div className="imc--contingut">

					<DadesEntitat/>

					<div className="imc-peu-opcions">

						<div className="pb-3 col2peu"><strong>{t('suportAjuda')} </strong> {suport}</div>

						<EnllasosPeuCentral autenticat={autenticat} />
					</div>

					<div className="imc-peu-xarxes">
						<EnllasosXarxes/>
					</div>
				</div>


				<Modal isOpen={this.state.modal} fade={false} toggle={this.toggle}>
					<ModalHeader toggle={this.toggle}>
						<p className="card-title titol h2">{t('suportTitol')}</p>
					</ModalHeader>
					<ModalBody>
						<ul className="pl-3">
							{suportWeb}
							{suportTelefon}
							{suportMail}
							{suportFAQ}
							{suportConsulta}
							{suportAutenticacio}
						</ul>
					</ModalBody>
					<ModalFooter>
						{tancar}
					</ModalFooter>
				</Modal>
			</footer>
		);
	}
}
export default withTranslation()(Peu);
