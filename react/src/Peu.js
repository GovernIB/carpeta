import {Component} from 'react';
import {withTranslation} from 'react-i18next';
import DadesEntitat from './DadesEntitat';
import EnllasosXarxes from "./EnllasosXarxes";

class Peu extends Component {
	
	render() {

		var urlBase = window.location.href;

		var autenticat = this.props.autenticat;
		const {t} = this.props;

		var mapa;
		if (autenticat === '1') {
			mapa = <li><a href="javascript:newMapaWeb('contingut', '1');">{t('peuMapa')}</a></li>;
		}
		if (autenticat === '0') {
			mapa = <li><a href="javascript:newMapaWeb('contingut', '0');">{t('peuMapa')}</a></li>;
		}

		var urlAvis = <li><a href={"http://www.caib.es/govern/external/infoLegal.do?lang=ca"}>{t('peuAvis')}</a></li>;

		var urlRss = <li>
			<a href={"http://www.caib.es/govern/rss.do?lang=ca"} className="imc-en-rss">
				<span className="mr-1">{t('peuRss')}</span>
				<svg viewBox="0 0 430.117 430.118" xmlSpace="preserve">
					<g>
						<path
							d="M97.493,332.473c10.419,10.408,16.755,24.525,16.794,40.244c-0.04,15.687-6.375,29.809-16.755,40.17l-0.04,0.019c-10.398,10.352-24.603,16.681-40.398,16.681c-15.775,0-29.944-6.348-40.34-16.699C6.384,402.526,0,388.422,0,372.717c0-15.719,6.384-29.869,16.754-40.253v0.009c10.401-10.36,24.57-16.735,40.34-16.735C72.89,315.738,87.081,322.131,97.493,332.473zM97.493,332.464v0.009c0.019,0,0.019,0,0.019,0L97.493,332.464z M16.754,412.906c0,0,0,0,0-0.019c-0.019,0-0.019,0-0.019,0L16.754,412.906z M0.046,146.259v82.129c53.618,0.033,104.328,21.096,142.278,59.104c37.943,37.888,58.917,88.675,59.003,142.477h0.028v0.149h82.467c-0.065-78.233-31.866-149.099-83.279-200.549C149.122,178.126,78.285,146.308,0.046,146.259z M0.196,0v82.089c191.661,0.14,347.464,156.184,347.594,348.028h82.327c-0.056-118.571-48.248-225.994-126.132-303.932C226.073,48.274,118.721,0.051,0.196,0z"></path>
					</g>
				</svg>
			</a>
		</li>;


		return (
			<footer className="imc-peu">
				<div className="imc--contingut">

					<DadesEntitat/>

					<div className="imc-peu-opcions">
						<ul>
							{mapa}
							{urlAvis}
							{urlRss}
						</ul>
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
