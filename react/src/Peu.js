import {Component} from 'react';
import {withTranslation} from 'react-i18next';
import DadesEntitat from './DadesEntitat';
import EnllasosXarxes from "./EnllasosXarxes";
import EnllasosPeuCentral from './EnllasosPeuCentral';

class Peu extends Component {
	
	render() {

		var urlBase = window.location.href;

		var autenticat = this.props.autenticat;
		const {t} = this.props;

		
		


		return (
			<footer className="imc-peu">
				<div className="imc--contingut">

					<DadesEntitat/>

					<div className="imc-peu-opcions">
						<EnllasosPeuCentral autenticat={autenticat} />
					</div>

					<div className="imc-peu-xarxes">
						<EnllasosXarxes/>
					</div>
				</div>
			</footer>
		);
	}
}
export default withTranslation()(Peu);
