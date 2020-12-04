import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import DadesEntitat from './DadesEntitat';
import EnllasosXarxes from "./EnllasosXarxes";
import EnllasosPeuCentral from './EnllasosPeuCentral';

class Peu extends Component {
	
	render() {

		var autenticat = this.props.autenticat;
		const {t} = this.props;

		var suport;

		if(autenticat === '0'){
			suport = <a href="javascript:newSuport('contingut', '0');">{t('suportAqui')}</a>;
		} else if(autenticat === '1'){
			suport = <a href="javascript:newSuport('contingut', '1');">{t('suportAqui')}</a>;
		}

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
			</footer>
		);
	}
}
export default withTranslation()(Peu);
