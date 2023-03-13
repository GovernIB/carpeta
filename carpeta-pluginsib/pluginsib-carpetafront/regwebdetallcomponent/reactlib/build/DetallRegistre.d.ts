import React from "react";
/**
 *  @author jagarcia
    @author anadal
 */
interface DetallRegistreProps {
    pathtoservei: string;
    numero: string;
    t: any;
    tornarDeDetallRegistreFunc: Function;
    axios: any;
}
type DetallRegistreState = {
    isLoaded: boolean;
    data: any;
    error: string;
};
declare class DetallRegistre extends React.Component<DetallRegistreProps, DetallRegistreState> {
    constructor(props: DetallRegistreProps);
    componentDidMount(): void;
    descarregarAnnex(annexId: any): void;
    generarJustificant(url: any): Promise<void>;
    t(cadena: string): string;
    downloadDoc(datafile: any, dataName: any): void;
    mostraTooltip(): void;
    dateFormat(dateObject: any): string;
    render(): JSX.Element;
}
export default DetallRegistre;
