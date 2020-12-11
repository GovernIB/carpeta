import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";

class BarraMenu extends Component {

	constructor(){
		super();
		this.state = {
			enllasosMenuBar: []
		}
	}

	componentWillMount() {
		var url = window.location.href + `webui/menubarlinks`;
		axios.get(url).then(res => {
			const enllasosMenuBar = res.data;
			this.setState({ enllasosMenuBar });
		})
	}

	componentWillReceiveProps(lng) {
		var url = window.location.href + `webui/menubarlinks`;
		axios.get(url).then(res => {
			const enllasosMenuBar = res.data;
			this.setState({ enllasosMenuBar });
		})
	}

	render() {

        const { t } = this.props;

		let enllasosBarraMenu;

		if(!this.state.enllasosMenuBar.length){
			enllasosBarraMenu = "";
		} else{
			enllasosBarraMenu = this.state.enllasosMenuBar.map((s, i) => (
				<li className="itemBar">
					<a href={s.url} className="imc-bt-menubar">
						<img src={s.urllogo} title={s.label} alt={s.label} className="logoMenuBar"/>
						<span>{s.label}</span>
					</a>
				</li>
			))
		}

		return (
			<header className="imc-titol">

				<nav className="imc--contingut">

					{/*<a href="{this.context.router.goBack()}" className="imc-torna"*/}
					{/*   title={t('menuTorna')}><span>{t('menuTorna')}</span></a>*/}
					<h1>
						<span className="pl-3 fs">{t('menuTitol')}</span>
					</h1>

					<ul>
						{enllasosBarraMenu}
						<li>
							<button type="button" className="imc-bt-menu" id="imc-bt-menu" title={t('menuMenu')}>
								<span>{t('menuMenu')}</span>
							</button>
						</li>
					</ul>
				</nav>

			</header>
		)
	}
}

export default withTranslation()(BarraMenu);
