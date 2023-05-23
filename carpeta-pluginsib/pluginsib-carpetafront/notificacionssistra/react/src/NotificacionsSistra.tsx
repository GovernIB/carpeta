import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import i18n from "./i18n";
import axios from "axios";
import { RenderTable, RenderTableData, RenderTableReturn, RowType, TemplatePageCarpeta } from "carpetacommonreactlib";

/**
 *  @author anadal
 *  @author jpernia
 */

interface NotificacionsSistraProps extends WithTranslation {
    titles: any;
    subtitles: any;
    pathtoservei: string;
    comunicacionesUrl: string;
}

class NotificacionsSistra extends React.Component<NotificacionsSistraProps> {
    private columnsNom: string[];


    constructor(props: NotificacionsSistraProps) {
        super(props);

        this.columnsNom = ["descripcion", "fecha"];


        this.carregaDadesNotificacionsSistra = this.carregaDadesNotificacionsSistra.bind(this);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on("languageChanged", this.canviatIdioma);
    }

    canviatIdioma(_lng: string) {
        this.forceUpdate();
    }

    componentDidMount() { }

    carregaDadesNotificacionsSistra(returnData: RenderTableReturn) {
        console.log("ENTRANT A carregaDadesNotificacionsSistra")
        const params = {};
        const url2 = this.props.pathtoservei;

        axios
            .get(url2, { params: params })
            .then((response) => {
                    if (!response.data) {
                    console.log(
                        "NotificacionsSistra::loadPaginatedData() => Error buit notificacions? => " + response.data.comunicacions
                    );

                    let errorPantalla = "Notificacions és null .";

                    let data: RenderTableData = {
                        tableData: null,
                        error: errorPantalla,
                    };
                    returnData.returnDataFunction(data);
                } else {
                    console.log("S'han carregat dades. Notificacions = "+ response.data);
                    let data: RenderTableData = {
                        tableData: response.data.comunicacions,
                        error: null,
                    };
                    returnData.returnDataFunction(data);
                }
            })
            .catch((error) => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                let errorPantalla;
                if (JSON.stringify(error).toString().includes("Request failed with status code 500")) {
                    errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", "");
                    errorPantalla = errorPantalla.replace("</body></html>", "");
                } else {
                    errorPantalla = JSON.stringify(error);
                }
                let data: RenderTableData = {
                    tableData: null,
                    error: errorPantalla,
                };
                returnData.returnDataFunction(data);
            });
    }

    onClickRow(_i: number, obj: any) {
        window.open(obj.url, "_blank");
    }

    render() {
        const i18n = this.props.i18n;

        let columnsTitols: string[];
        columnsTitols = [
            i18n.t("notificacionsSistraComunicacionDescripcion"),
            i18n.t("notificacionsSistraComunicacionFecha"),
        ];


        return (
            <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
                <div className="float-left" style={{ width: "97%", position: "relative" }}>
                    <RenderTable
                        loadData={this.carregaDadesNotificacionsSistra}
                        columnNames={this.columnsNom}
                        columnTitles={columnsTitols}
                        rowType={RowType.EXTERNAL_LINK}
                        i18n={i18n}
                        onClickRow={this.onClickRow}
                    />
                </div>
            </TemplatePageCarpeta>
        );

    }
}

export default withTranslation()(NotificacionsSistra);
