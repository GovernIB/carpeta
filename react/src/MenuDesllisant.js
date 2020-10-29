import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';

class MenuDesllisant extends Component {

	infoHtml(missatge, pluginID) {
		alert(missatge);
		var contingut = newPluginHtml('contingut', '1', pluginID);
		window.location.href(contingut);
	}

	infoReact(missatge, pluginID) {
		alert(missatge);
		var contingut = newPluginReact('contingut', '1', pluginID);
		window.location.href(contingut);
	}

	error(missatge) {
		alert(missatge);
	}

	render() {

		const {t} = this.props;
		var autenticat = this.props.autenticat;
		var urlBase = window.location.href;

		var boto_ca;
		if (i18n.language === 'ca') {
			boto_ca = <strong>{t('menuIdioma_ca')}</strong>;
		} else {
			boto_ca =
				<button onClick={() => i18n.changeLanguage('ca')} className="boton-menu">{t('menuIdioma_ca')}</button>;
		}

		var boto_es;
		if (i18n.language === 'es') {
			boto_es = <strong>{t('menuIdioma_es')}</strong>;
		} else {
			boto_es =
				<button onClick={() => i18n.changeLanguage('es')} className="boton-menu">{t('menuIdioma_es')}</button>;
		}


		const plugins = JSON.parse(sessionStorage.getItem('plugins'));

		var accessibilitat;
		var plugHtml;
		var plugHtmlInfo;
		var plugHtmlWarning;
		var plugHtmlError;
		var plugReact;
		var plugReactInfo;
		var plugReactWarning;
		var plugReactError;
		var sortir;
		if (autenticat === '1') {
			accessibilitat = <li><a href="javascript:newAccessibilitat('contingut', '1');"
									className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat"
									title={t('menuAccessibilitat')}><span>{t('menuAccessibilitat')}</span></a></li>;
			sortir = <li><a href="sortir" className="imc-marc-ico imc--sortir" id="imc-marc-sortir"
							title={t('menuSortir')}><span>{t('menuSortir')}</span></a></li>;

			if (i18n.language === 'ca') {
				plugHtml = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 0).map(s => (
					<li><a href={"javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"}
						   title={s.nomCa}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></a></li>
				));
				plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map(s => (
					<li><button title={s.nomCa} className="botoMenu alert1menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></button></li>
				));
				plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map(s => (
					<li><button title={s.nomCa} className="botoMenu alert2menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></button></li>
				));
				plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map(s => (
					<li><button title={s.nomCa} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></button></li>
				));

				plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map(s => (
					<li><a href={"javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"}
						   title={s.nomCa}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></a></li>
				));
				plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map(s => (
					<li><button title={s.nomCa} className="botoMenu alert1menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></button></li>
				));
				plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map(s => (
					<li><button title={s.nomCa} className="botoMenu alert2menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></button></li>
				));
				plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map(s => (
					<li><button title={s.nomCa} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomCa}</span></button></li>
				));
			}

			if (i18n.language === 'es') {
				plugHtml = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 0).map(s => (
					<li><a href={"javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"}
						   title={s.nomEs}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></a></li>
				));
				plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map(s => (
					<li><button title={s.nomEs} className="botoMenu alert1menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></button></li>
				));
				plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map(s => (
					<li><button title={s.nomEs} className="botoMenu alert2menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></button></li>
				));
				plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map(s => (
					<li><button title={s.nomEs} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></button></li>
				));

				plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map(s => (
					<li><a href={"javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"}
						   title={s.nomEs}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></a></li>
				));
				plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map(s => (
					<li><button title={s.nomEs} className="botoMenu alert1menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></button></li>
				));
				plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map(s => (
					<li><button title={s.nomEs} className="botoMenu alert2menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></button></li>
				));
				plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map(s => (
					<li><button title={s.nomEs} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
						src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
						className="imc-icona"></img><span>{s.nomEs}</span></button></li>
				));
			}

		}
		if (autenticat === '0') {
			accessibilitat = <li><a href="javascript:newAccessibilitat('contingut', '0');"
									className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat"
									title={t('menuAccessibilitat')}><span>{t('menuAccessibilitat')}</span></a></li>;
			plugHtml = '';
			plugReact = '';
			plugHtmlInfo = '';
			plugHtmlWarning = '';
			plugHtmlError = '';
			plugReactInfo = '';
			plugReactWarning = '';
			plugReactError = '';
			sortir = '';
		}


		return (
			<div className="imc-marc" id="imc-marc" tabIndex="-1" aria-hidden="true">
				<div className="imc--fons"></div>

				<div className="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
					<div className="imc-cercador" id="imc-cercador">
					</div>
					<ul>
						<li>
							<a href="http://www.caib.es/govern/cercadorAv.do?lang=ca"
							   className="imc-marc-ico imc--avanzada" title={t('menuRecerca')}>
								<span>{t('menuRecerca')}</span>
							</a>
						</li>
						<li className="imc-marc-ico imc--idioma">
							{boto_ca} \ {boto_es}
						</li>
						{accessibilitat}
						{plugHtml}
						{plugHtmlInfo}
						{plugHtmlWarning}
						{plugHtmlError}
						{plugReact}
						{plugReactInfo}
						{plugReactWarning}
						{plugReactError}
						{sortir}
					</ul>
				</div>
			</div>
		);
	}
}

export default withTranslation()(MenuDesllisant);
