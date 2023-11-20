import React, { Component } from 'react';
import { withTranslation, WithTranslation } from "react-i18next";
import i18n from './i18n';

import {
    TemplatePageCarpeta,
} from "carpetacommonreactlib";
import CertificatBoto from './CertificatBoto';

/**
 *  @author fbosch
 */

interface CertificatsProps extends WithTranslation {
    pathtoserveiTe: string;
    titles: any;
    subtitles: any;
    pluginsToLoad: PluginInfo[];
}

interface CertificatsState {
    noDisponiblesVisible: boolean;
}


class Certificats extends React.Component<CertificatsProps, CertificatsState> {

    refButtons: React.RefObject<CertificatBoto>[];

    constructor(props: CertificatsProps) {
        super(props);
        this.refButtons = [];
        this.state = {
            noDisponiblesVisible: true
        }

        this.canviatIdioma = this.canviatIdioma.bind(this);
        this.onChangeNoDisponiblesVisibles = this.onChangeNoDisponiblesVisibles.bind(this);

        i18n.on("languageChanged", this.canviatIdioma);
    }

    onChangeNoDisponiblesVisibles() {
        let nouValor : boolean;

        nouValor = !this.state.noDisponiblesVisible;

        for (let i = 0; i < this.refButtons.length; i++) {
            //@ts-ignore
            this.refButtons[i].current.mostrar(nouValor);
        }

        this.setState({
            noDisponiblesVisible: nouValor
        });
    }


    componentDidMount() {

    }

    canviatIdioma(_lng: string) {
        this.forceUpdate();
    }

    render() {
        const t = this.props.i18n.t;
        const buttons = [];
        let botoProps : CertificatBotoProperties;
        let botoState : CertificatBotoState;

        var textBotoAmagarNoDisponibles;
        if(this.state.noDisponiblesVisible){
            textBotoAmagarNoDisponibles = t("amagarNoDisponibles")
        }else{
            textBotoAmagarNoDisponibles = t("mostrarNoDisponibles")
        }

        let referenceButtonList = [];

        for (let i = 0; i < this.props.pluginsToLoad.length; i++) {

            let myRef : React.RefObject<CertificatBoto> = React.createRef();

            let p: PluginInfo = this.props.pluginsToLoad[i];
            let certificatButton = <CertificatBoto ref={myRef} i18n={this.props.i18n} noDisponiblesVisible={this.state.noDisponiblesVisible} subtitle={p.subtitle} title={p.title} pluginNumber={p.pluginNumber} pathToServeiTe={this.props.pathtoserveiTe + "/" + p.pluginNumber} key={i} />;

            buttons.push(certificatButton);

            referenceButtonList.push(myRef);
        }

        this.refButtons = referenceButtonList;

        let formulari = (
            <>
                <div className="col-md-3 pl-3 row botoFormApp" style={{ zIndex: "4", marginTop: "40px" }}>
                    <button
                        type="submit"
                        className="btn btn-secondary carpeta-btn mt-2"
                        onClick={() => {
                            this.onChangeNoDisponiblesVisibles();
                        }}
                        tabIndex={505}
                    >
                        {textBotoAmagarNoDisponibles}
                    </button>
                </div>

                <div className="row">
                    <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                        <div className="alert alert-danger" role="alert" id="errorMsg">
                            {t("errorIniciMajorFinal")}
                        </div>
                    </div>
                </div>
            </>
        )




        return (
            <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
                <>

                <div className="col-md-12 border-0 pl-0 pr-0">
                    <div className="card-body imc--llista--capses">
                        <div className="row mb-0">
                            {buttons}
                        </div>
                    </div>
                </div>

                <div tabIndex={520}
                    id="botoAmagarNoDisponibles"
                >
                    {formulari}
                </div>
                </>
            </TemplatePageCarpeta>

        );

    }
}

export default withTranslation()(Certificats);