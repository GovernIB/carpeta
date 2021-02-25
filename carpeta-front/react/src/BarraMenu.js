import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";

class BarraMenu extends Component {

	constructor(){
		super();
		this.state = {
			enllasosMenuBar: null
		}
	}


	componentDidMount() {

		var auth = sessionStorage.getItem('autenticat');

		if (auth ==='1') {
			var baseURL = sessionStorage.getItem('contextPath');
			var url = baseURL + `/webui/menubarlinks`;

			axios.get(url).then(res => {
				const enllasosMenuBar = res.data;
				this.setState({ enllasosMenuBar });
			});
		}
	}

	componentWillReceiveProps(lng) {
		var auth = sessionStorage.getItem('autenticat');
		if (auth ==='1') {
			var baseURL = sessionStorage.getItem('contextPath');
			var url = baseURL + `/webui/menubarlinks`;
			axios.get(url).then(res => {
				const enllasosMenuBar = res.data;
				this.setState({ enllasosMenuBar });
			});
		}
	}

	render() {

        const { t } = this.props;
		var auth = sessionStorage.getItem('autenticat');

		let enllasosBarraMenu;

		if(auth === '0' || !this.state.enllasosMenuBar ){
			enllasosBarraMenu = "";
		} else{
			enllasosBarraMenu = this.state.enllasosMenuBar.map((s, i) => (
				<li className="itemBar" key={i}>
					<a href={s.url} className="imc-bt-menubar">
						<img src={s.urllogo} title="" alt="" className="logoMenuBar"/>
						<span>{s.label}</span>
					</a>
				</li>
			))
		}

		return (
			<div id = "barraMenu">
				<header className="imc-titol">

					<nav className="imc--contingut">

						{/*<a href="{this.context.router.goBack()}" className="imc-torna"*/}
						{/*   title={t('menuTorna')}><span>{t('menuTorna')}</span></a>*/}
						<h1>
							<span>{t('menuTitol')}</span>
						</h1>

						<ul>
							{enllasosBarraMenu}
							<li>
								<button type="button" className="imc-bt-menu" id="imc-bt-menu" title={t('menuMenu')}>
									{/*<span>{t('menuMenu')}</span>*/}
								</button>
							</li>
						</ul>
					</nav>

				</header>
			</div>
		)
	}
}

export default withTranslation()(BarraMenu);
