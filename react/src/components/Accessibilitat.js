import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import waiImg from '../assets/images/wcag2AA.png';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('accessibilitat.titol') }</p>

          <div className="col-md-12 border-0 float-left p-0">
            <p className="lh15">{ t('accessibilitat.declaracio1') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitat.decret') }</a> { t('accessibilitat.declaracio2') }</p>
            <p className="lh15">{ t('accessibilitat.declaracio3') } <a href="https://www.caib.es/carpeta/" target="_blank" rel="noopener noreferrer">https://www.caib.es/carpeta/</a> { t('accessibilitat.declaracio4') }</p>

            <p className="titol h3">{ t('accessibilitat.compliment') }</p>
            <p className="lh15">{ t('accessibilitat.compliment1') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitat.decret') }</a>{ t('accessibilitat.compliment2') }</p>

            <p className="titol h3">{ t('accessibilitat.noAccessible') }</p>
            <p className="lh15">{ t('accessibilitat.noAccessible1') }</p>
            <p className="lh15">{ t('accessibilitat.noAccessible2') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitat.decret') }</a>.</p>
            <div className="mb-3">
              <ul className="lh15 pl-5 pt-1">
                <li>{ t('accessibilitat.noAccessible3') }</li>
                <li>{ t('accessibilitat.noAccessible4') }</li>
                <li>{ t('accessibilitat.noAccessible5') }</li>
              </ul>
            </div>

            <p className="titol h3">{ t('accessibilitat.preparacio') }</p>
            <p className="lh15">{ t('accessibilitat.preparacio1') }</p>
            <p className="lh15">{ t('accessibilitat.preparacio2') }</p>

            <p className="titol h3">{ t('accessibilitat.observacions') }</p>
            <p className="lh15">{ t('accessibilitat.observacions1') } <a href="https://www.caib.es/sistrafront/sistrafront/inicio?language=ca&modelo=PD0018ENCW&version=1" target="_blank" rel="noopener noreferrer">{ t('accessibilitat.observacions5') }</a>.</p>
            <p className="lh15">{ t('accessibilitat.observacions2') } <a href="http://dgtic.caib.es/" target="_blank" rel="noopener noreferrer">{ t('accessibilitat.observacions3') }</a> { t('accessibilitat.observacions4') }</p>

            <p className="titol h3">{ t('accessibilitat.procediment') }</p>
            <p className="lh15">{ t('accessibilitat.procediment1') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitat.procediment2') }</a>.</p>

            <p className="titol h3">{ t('accessibilitat.opcional') }</p>
            <p className="lh15">{ t('accessibilitat.opcional1') }</p>
            <p className="lh15">{ t('accessibilitat.opcional2') }</p>
            <p className="lh15">{ t('accessibilitat.opcional3') }</p>
            <p className="lh15"><a className="mr-auto" href="https://www.w3.org/WAI/WCAG1AA-Conformance" target="_blank" rel="noopener noreferrer"><img src={waiImg} alt={ t('accessibilitat.wai') } title={ t('accessibilitat.wai') }/></a></p>
            <p className="lh15">{ t('accessibilitat.opcional4') }</p>

          </div>

        </div>
      </div>
    );
  }
}
const Contingut = withTranslation()(LegacyComponentClass)

export default function Accessibilitat() {
  return (
    <Suspense fallback="loading">
      <Contingut />
    </Suspense>
  );
}
