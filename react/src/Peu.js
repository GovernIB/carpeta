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

		var suport = <button type="button" onClick={this.toggle} className="botoSuport">{t('suportAqui')}</button>

		var tancar =  <button type="button" onClick={this.toggle} className="botoSuport">{t('suportTanca')}</button>;

		var dades = this.state.dadesSuport.toString();


		var dadesSuport = dades.substring(1, dades.length - 2);
		var arrayDeCadenas = dadesSuport.split(', ');

		const listWebs = arrayDeCadenas.filter(dada => dada.substr(dada.indexOf('=')+1).startsWith('http')).map((dada) =>
			<li className="pb-2">{t('suportConsulta')} <a href={dada.substr(dada.indexOf('=')+1)}>{dada.substr(0,dada.indexOf('='))}</a></li>
		);

		const listTelefons = arrayDeCadenas.filter(dada => !isNaN(parseInt(dada.substr(dada.indexOf('=')+1)))).map((dada) =>
			<li className="pb-2">{t('suportPer')} {dada.substr(0,dada.indexOf('='))} {t('suportCrida')} <p className="text-verd"> {dada.substr(dada.indexOf('=')+1)}</p></li>
		);

		const listMails = arrayDeCadenas.filter(dada => dada.substr(dada.indexOf('=')+1).includes('@')).map((dada) =>
			<li className="pb-2">{t('suportMail')} <a href={"mailto:'"+dada.substr(dada.indexOf('=')+1)+"'"}>{dada.substr(dada.indexOf('=')+1)}</a></li>
		);

		return (
			<footer className="imc-peu">
				<div className="imc--contingut">

					<DadesEntitat/>

					<div className="imc-peu-opcions">

						<div className="pb-3 pl-5"><strong>{t('suportAjuda')} </strong> {suport}</div>

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
							{listWebs}
							{listTelefons}
							{listMails}
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
