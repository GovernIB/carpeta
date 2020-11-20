import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";

class MapaWeb extends Component {

	constructor(){
		super();
		this.state = {
			plugins: []
		}
	}

	componentWillMount() {
		var url = window.location.href + `pluginfront/veureplugins`;
		axios.get(url).then(res => {
			const plugins = res.data;
			this.setState({ plugins });
		})
	}

	componentWillReceiveProps(lng) {
		var url = window.location.href + `pluginfront/veureplugins`;
		axios.get(url).then(res => {
			const plugins = res.data;
			this.setState({ plugins });
		})
	}

	render() {

		const {t} = this.props;

		var autenticat = sessionStorage.getItem('autenticat');
		const plugins = this.state.plugins;

		var plug;

		var informacio;
		var dades;
		var logoClau;

		if (autenticat === '1') {
			logoClau = '';
			informacio = <a href="javascript:newInici('contingut', '1');">{t('mapaWebInformacio')}</a>;

			plug = plugins.map(s => (<p className="lh15 upper"><a
				href={"javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"}>{s.nom}</a></p>));

			dades = <a href="javascript:newDadesPersonals('contingut', '1');">{t('mapaWebDades')}</a>;
		}
		if (autenticat === '0') {
			logoClau = <span class="oi oi-lock-locked colorClave" title={t('mapaWebClave')}></span>;
			informacio = <a href="javascript:newInici('contingut', '0');">{t('mapaWebInformacio')}</a>;

			plug = plugins.map(s => (
				<p className="lh15 upper"><a href="javascript: var loc = new URL(window.location.href);  window.location.href=('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host) )">{s.nom}</a> {logoClau}</p>));

			dades = <a href="javascript: var loc = new URL(window.location.href);  window.location.href=('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host) )">{t('mapaWebDades')}</a>;
		}

		return (
			<div className="container-contenido">

				<div className="infoNoMenu">
					<p className="titol h2">{t('mapaWebTitol')}</p>

					<div className="col-md-12 border-0 pl-0 pr-0">

						<p className="lh15">{t('mapaWebDescripcio')}</p>

						<div className="card">
							<ul className="list-group list-group-flush">
								<li className="list-group-item">
									<p className="lh15 upper">{informacio}</p>
								</li>
								<li className="list-group-item">
									<p className="titol h5 upper">{t('mapaWebPlugins')}</p>
									{plug}
								</li>
								<li className="list-group-item">
									<p className="lh15 upper">{dades} {logoClau}</p>
								</li>
							</ul>
						</div>

					</div>

				</div>

			</div>
		);
	}
}

export default withTranslation()(MapaWeb);