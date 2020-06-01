import React, { Component, Suspense } from 'react';
import {menuLateral} from '../assets/js/helper.js';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
				<header className="imc-titol">

					<nav className="imc--contingut">

						<a href="{this.context.router.goBack()}" className="imc-torna" title="Torna"><span>{ t('menu.torna') }</span></a>
						<h1>
							<span>{ t('menu.titol') }</span>
						</h1>

						<ul>
							<li>
								<a href="http://www.caib.es/govern/organigrama/directori.do?lang=ca" className="imc-bt-directori" title={ t('menu.directori') }>
									<span>{ t('menu.directori') }</span>
								</a>
							</li>
							<li>
								<button type="button" className="imc-bt-menu" id="imc-bt-menu" title={ t('menu.menu') } onClick={menuLateral}>
									<span>{ t('menu.menu') }</span>
								</button>
							</li>
						</ul>
					</nav>

				</header>
    );
  }
}
const BarraM = withTranslation()(LegacyComponentClass)

export default function BarraMenu() {
  return (
    <Suspense fallback="loading">
      <BarraM />
    </Suspense>
  );
}
