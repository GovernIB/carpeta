import React from 'react';
import { withTranslation } from 'react-i18next';


function BarraMenu ({ t }) {

    return (
		<header className="imc-titol">

			<nav className="imc--contingut">

				<a href="{this.context.router.goBack()}" className="imc-torna" title={ t('menuTorna') }><span>{ t('menuTorna') }</span></a>
				<h1>
					<span>{ t('menuTitol') }</span>
				</h1>

				<ul>
					<li>
						<a href="http://www.caib.es/govern/organigrama/directori.do?lang=ca" className="imc-bt-directori" title={ t('menuDirectori') }>
							<span>{ t('menuDirectori') }</span>
						</a>
					</li>
					<li>
						<button type="button" className="imc-bt-menu" id="imc-bt-menu" title={ t('menuMenu') }>
							<span>{ t('menuMenu') }</span>
						</button>
					</li>
				</ul>
			</nav>

		</header>
    )
}

export default withTranslation()(BarraMenu);
