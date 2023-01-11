/**
 * @author [anadal]
 * @create date 2022-12-28 11:36:00
 * @modify date 2022-12-28 11:36:00
 * @desc [description]
 */
import React from "react";
import PaginationCarpetaProps from "./PaginationCarpetaProps";
interface InternalPaginationCarpetaProps {
    paginationInfo: PaginationCarpetaProps;
    i18n: any;
}
declare class PaginationCarpeta extends React.Component<InternalPaginationCarpetaProps> {
    constructor(props: InternalPaginationCarpetaProps);
    render(): JSX.Element;
}
export default PaginationCarpeta;
