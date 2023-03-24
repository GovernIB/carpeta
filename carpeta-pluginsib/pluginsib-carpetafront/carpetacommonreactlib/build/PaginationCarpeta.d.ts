/**
 * @author [anadal]
 * @create date 2022-12-28 11:36:00
 * @modify date 2022-12-28 11:36:00
 * @desc [description]
 */
import React from "react";
import { PaginationCarpetaProps, PaginationInfo } from "./PaginationCarpetaProps";
interface PaginationCarpetaState {
    paginationInfo: PaginationInfo | null;
}
declare class PaginationCarpeta extends React.Component<PaginationCarpetaProps, PaginationCarpetaState> {
    private static readonly ROTATE_RIGHT_STYLE;
    private static readonly ROTATE_LEFT_STYLE;
    constructor(props: PaginationCarpetaProps);
    updatePaginationInfo(paginationInfo: PaginationInfo | null): void;
    render(): JSX.Element;
}
export default PaginationCarpeta;
