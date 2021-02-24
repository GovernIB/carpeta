import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import ExpirarSessio from "./ExpirarSessio";
import Breadcrumb from "./Breadcrumb";
import { Link } from "react-router-dom";
import * as breadcrumbPaths from "./utils/breadcrumbPaths";
import * as breadcrumbPathsAut from "./utils/breadcrumbPathsAut";

class MapaWeb extends Component {

	constructor(){
		super();
		this.state = {
			plugins: []
		}
	}

	componentDidMount() {
		var baseURL = sessionStorage.getItem('contextPath');
		var url = baseURL + `/pluginfront/veureplugins`;

		axios.get(url).then(res => {
			const plugins = res.data;
			this.setState({ plugins });
		})
	}

	componentWillReceiveProps(lng) {
		var baseURL = sessionStorage.getItem('contextPath');
		var url = baseURL + `/pluginfront/veureplugins`;
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


			plug = plugins.map(s => (<p className="lh15 upper">
			     {/*
			     <a
				href={"javascript:('contingut', '1', '" + s.pluginID + "');"}>
				{s.nom}</a>
				*/}
				<Link to="/pluginhtml/{s.pluginID}" >
				{s.nom}
				</Link>
				</p>));

			dades = <a href="javascript:newDadesPersonals('contingut', '1');">{t('mapaWebDades')}</a>;
		}
		if (autenticat === '0') {
			logoClau = <span className="oi oi-lock-locked colorClave" title={t('mapaWebClave')}/>;
			informacio = <a href="javascript:newInici('contingut', '0');">{t('mapaWebInformacio')}</a>;


			plug = <p className="lh15 upper">XYZ ZZZ FALTA ENLLAÇ A LOGIN</p>; /* XYZ ZZZ Aqui enllaç a LOGIN !!!!!! */
			/*
			plugins.map(s => (
				<p className="lh15 upper"><a href="javascript: var loc = new URL(window.location.href);  window.location.href=('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host) )">{s.nom}</a> {logoClau}</p>));
*/

			dades = <a href="javascript: var loc = new URL(window.location.href);  window.location.href=('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host) )">{t('mapaWebDades')}</a>;
		}

		clearTimeout(sessionStorage.getItem('idTimeOut'));

		var motlla = <Breadcrumb items={breadcrumbPaths.MapaWeb} autenticat={autenticat}/>

		return (
			<div className="container-contenido">
				{motlla}
				<ExpirarSessio />

				<div className="infoNoMenu">
					<h2><p className="titol h2">{t('mapaWebTitol')}</p></h2>

					<div className="col-md-12 border-0 pl-0 pr-0">

						<p className="lh15">{t('mapaWebDescripcio')}</p>

						<div className="card">
							<ul className="list-group list-group-flush">
								<li className="list-group-item">
									<p className="lh15 upper">{informacio}</p>
								</li>
								<li className="list-group-item">
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