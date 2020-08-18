import React from 'react';
import { withTranslation } from 'react-i18next';


function Accessibilitat ({ t }) {

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('accessibilitatTitol') }</p>

          <div className="col-md-12 border-0 float-left p-0">
            <p className="lh15">{ t('accessibilitatDeclaracio1') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitatDecret') }</a> { t('accessibilitatDeclaracio2') }</p>
            <p className="lh15">{ t('accessibilitatDeclaracio3') } <a href="https://www.caib.es/carpeta/" target="_blank" rel="noopener noreferrer">https://www.caib.es/carpeta/</a> { t('accessibilitatDeclaracio4') }</p>

            <p className="titol h3">{ t('accessibilitatCompliment') }</p>
            <p className="lh15">{ t('accessibilitatCompliment1') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitatDecret') }</a>{ t('accessibilitatCompliment2') }</p>

            <p className="titol h3">{ t('accessibilitatNoAccessible') }</p>
            <p className="lh15">{ t('accessibilitatNoAccessible1') }</p>
            <p className="lh15">{ t('accessibilitatNoAccessible2') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitatDecret') }</a>.</p>
            <div className="mb-3">
              <ul className="lh15 pl-5 pt-1">
                <li>{ t('accessibilitatNoAccessible3') }</li>
                <li>{ t('accessibilitatNoAccessible4') }</li>
                <li>{ t('accessibilitatNoAccessible5') }</li>
              </ul>
            </div>

            <p className="titol h3">{ t('accessibilitatPreparacio') }</p>
            <p className="lh15">{ t('accessibilitatPreparacio1') }</p>
            <p className="lh15">{ t('accessibilitatPreparacio2') }</p>

            <p className="titol h3">{ t('accessibilitatObservacions') }</p>
            <p className="lh15">{ t('accessibilitatObservacions1') } <a href="https://www.caib.es/sistrafront/sistrafront/inicio?language=ca&modelo=PD0018ENCW&version=1" target="_blank" rel="noopener noreferrer">{ t('accessibilitatObservacions5') }</a>.</p>
            <p className="lh15">{ t('accessibilitatObservacions2') } <a href="http://dgtic.caib.es/" target="_blank" rel="noopener noreferrer">{ t('accessibilitatObservacions3') }</a> { t('accessibilitatObservacions4') }</p>

            <p className="titol h3">{ t('accessibilitatProcediment') }</p>
            <p className="lh15">{ t('accessibilitatProcediment1') } <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">{ t('accessibilitatDecret') }</a> { t('accessibilitatProcediment2') }</p>

            <p className="titol h3">{ t('accessibilitatOpcional') }</p>
            <p className="lh15">{ t('accessibilitatOpcional1') }</p>
            <p className="lh15">{ t('accessibilitatOpcional2') }</p>
            <p className="lh15">{ t('accessibilitatOpcional3') }</p>
            <p className="lh15"><a className="mr-auto" href="https://www.w3.org/WAI/WCAG1AA-Conformance" target="_blank" rel="noopener noreferrer"><div className="imc--logowai"></div></a></p>
            <p className="lh15">{ t('accessibilitatOpcional4') }</p>

          </div>

        </div>
      </div>
    );
}

export default withTranslation()(Accessibilitat);
