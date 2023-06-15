import React, { Component } from 'react';
import { withTranslation, WithTranslation } from "react-i18next";
import axios from "axios";
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


class Certificats extends React.Component<CertificatsProps> {
    constructor(props: CertificatsProps) {
        super(props);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on("languageChanged", this.canviatIdioma);
    }

    componentDidMount() {
    }

    canviatIdioma(_lng: string) {
        this.forceUpdate();
    }



    render() {

        const buttons = [];
        for (let i = 0; i < this.props.pluginsToLoad.length; i++) {

            let p: PluginInfo = this.props.pluginsToLoad[i];
            buttons.push(<CertificatBoto subtitle={p.subtitle} title={p.title} pluginNumber={p.pluginNumber} pathToServeiTe={this.props.pathtoserveiTe + "/" + p.pluginNumber} key={i} />);
        }

        return (
            <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
                <div>
                    {buttons}
                </div>
            </TemplatePageCarpeta>

        );

    }
}

export default withTranslation()(Certificats);