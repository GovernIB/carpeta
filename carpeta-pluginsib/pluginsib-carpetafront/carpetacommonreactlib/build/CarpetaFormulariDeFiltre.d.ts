/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */
import React from "react";
interface CarpetaFormulariDeFiltreProps {
    children: JSX.Element;
    handleSubmitSearcher(event: any): boolean;
    i18n: any;
}
declare class CarpetaFormulariDeFiltre extends React.Component<CarpetaFormulariDeFiltreProps> {
    constructor(props: CarpetaFormulariDeFiltreProps);
    private handleSubmitSearcher;
    setError(): void;
    render(): JSX.Element;
}
export default CarpetaFormulariDeFiltre;
