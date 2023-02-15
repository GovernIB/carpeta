/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */
import React from "react";
import PaginationCarpetaProps from "./PaginationCarpetaProps";
import RenderTableProps from "./RenderTableProps";
interface RenderPaginationTableProps extends RenderTableProps {
    paginationInfo: PaginationCarpetaProps;
    i18n: any;
}
declare class RenderPaginationTable extends React.Component<RenderPaginationTableProps> {
    constructor(props: RenderPaginationTableProps);
    render(): JSX.Element;
}
export default RenderPaginationTable;
