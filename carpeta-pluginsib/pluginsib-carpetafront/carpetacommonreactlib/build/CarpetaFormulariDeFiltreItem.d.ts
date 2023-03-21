/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */
import React from "react";
interface CarpetaFormulariDeFiltreItemProps {
    children: JSX.Element;
    label: string;
}
declare class CarpetaFormulariDeFiltreItem extends React.Component<CarpetaFormulariDeFiltreItemProps> {
    constructor(props: CarpetaFormulariDeFiltreItemProps);
    render(): JSX.Element;
}
export default CarpetaFormulariDeFiltreItem;
