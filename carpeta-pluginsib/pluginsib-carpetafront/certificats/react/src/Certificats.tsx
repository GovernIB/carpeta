import React, { Component } from 'react';
import { withTranslation, WithTranslation } from "react-i18next";
import axios from "axios";
import i18n from './i18n';

import {
    PaginationInfo,
    RenderPaginationTable,
    RenderPaginationTableData,
    ReturnPaginationData,
    TemplatePageCarpeta,
    CarpetaDatePicker,
    CarpetaInputText,
    CarpetaFormulariDeFiltre,
    CarpetaFormulariDeFiltreItem,
    RowType
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
    constructor(props: CertificatsProps) {
        super(props);

        this.state = {
            noDisponiblesVisible: true
        }

        this.canviatIdioma = this.canviatIdioma.bind(this);
        this.onChangeNoDisponiblesVisibles = this.onChangeNoDisponiblesVisibles.bind(this);

        i18n.on("languageChanged", this.canviatIdioma);
    }

    onChangeNoDisponiblesVisibles() {
        if (this.state.noDisponiblesVisible) {
            this.setState({
                noDisponiblesVisible: false
            });
        } else {
            this.setState({
                noDisponiblesVisible: true
            });
        }
        console.log("XYZ ZZZ Entrat a onChangeNoDisponiblesVisibles");
        this.forceUpdate();
    }


    componentDidMount() {

    }

    canviatIdioma(_lng: string) {
        this.forceUpdate();
    }

    render() {
        const t = this.props.i18n.t;
        const buttons = [];

        var textBotoAmagarNoDisponibles;
        if(this.state.noDisponiblesVisible){
            textBotoAmagarNoDisponibles = this.props.i18n.t("amagarNoDisponibles")
        }else{
            textBotoAmagarNoDisponibles = this.props.i18n.t("mostrarNoDisponibles")
        }

        for (let i = 0; i < this.props.pluginsToLoad.length; i++) {

            let p: PluginInfo = this.props.pluginsToLoad[i];
            buttons.push(<CertificatBoto noDisponiblesVisible={this.state.noDisponiblesVisible} subtitle={p.subtitle} title={p.title} pluginNumber={p.pluginNumber} pathToServeiTe={this.props.pathtoserveiTe + "/" + p.pluginNumber} key={i} />);
        }

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
                    <div>
                        {buttons}
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