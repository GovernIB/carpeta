import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';

/**
 *  @author anadal
 */
class DadesPersonals extends Component {


    constructor(props) {
        super(props);
        // console.log('  CONSTRUCTOR DADES PERSONALS !!!!!');
    }


    render() {

        console.log('  RENDER DADES PERSONALS!!!!!');

        const { t } = this.props;
        
        const dades = this.props.dades;

        const userData = dades.userData;

        var tipus = '';
        var representant;
        if (userData.representative) {

            representant = 
                <div>
                    <p className="titol h4">{t('representant')}</p>
                    <div className="col-md-12 border-0 float-left p-0">
                        <dl className="row">
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('dadespersonalsNom')}</dt>
                                <dd className="col-sm-7">{userData.representative.name}</dd>
                            </div>
                            
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('dadespersonalsLlinatge1')}</dt>
                                <dd className="col-sm-7">{userData.representative.surname1}</dd>
                            </div>
                            
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('dadespersonalsLlinatge2')}</dt>
                                <dd className="col-sm-7">{userData.representative.surname2}</dd>
                            </div>

                            <div class="mt-3">
                                <dt className="col-sm-3">{t('dadespersonalsDni')}</dt>
                                <dd className="col-sm-7">{userData.representative.administrationID}</dd>
                            </div>
                        </dl>
                    </div>
                </div>;
            
            tipus=<p className="titol h4">{t('representat')}</p>;

        } else {
            representant = "";
        }


        var llinatge1;
        var llinatge2;
        console.log("DadesPersonals:: business => " + userData.business)
        if (userData.business) {
            llinatge1 = "";
            llinatge2 = "";
            tipus= <p className="titol h4">{t('empresa')}</p>;
        } else {
            llinatge1 = <div class="mt-3">
                <dt className="col-sm-3">{t('dadespersonalsLlinatge1')}</dt>
                <dd className="col-sm-7">{userData.surname1}</dd>
            </div>;
            
            llinatge2 = <div class="mt-3">
                <dt className="col-sm-3">{t('dadespersonalsLlinatge2')}</dt>
                <dd className="col-sm-7">{userData.surname2}</dd>
            </div>;
        }



        return (
            <div className="infoNoMenu">
                {tipus}
                <div className="col-md-12 border-0 float-left p-0">
                    <dl className="row">
                        <div>
                            <dt className="col-sm-3">{t('dadespersonalsNom')}</dt>
                            <dd className="col-sm-7">{userData.name}</dd>
                        </div>
                        {llinatge1}
                        {llinatge2}
                        
                        <div class="mt-3">
                            <dt className="col-sm-3">{t('dadespersonalsDni')}</dt>
                            <dd className="col-sm-7">{userData.administrationID}</dd>
                        </div>

                        <div class="mt-3">
                            <dt className="col-sm-3">{t('dadespersonalsMetode')}</dt>
                            <dd className="col-sm-7">{userData.authenticationMethod}</dd>
                        </div>

                    </dl>
                </div>
                {representant}
            </div>
        );

    }
}

export default withTranslation()(DadesPersonals);