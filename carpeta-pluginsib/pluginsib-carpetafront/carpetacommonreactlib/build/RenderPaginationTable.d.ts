/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */
import React from "react";
import { RenderPaginationTableData, RenderPaginationTableProps } from "./PaginationCarpetaProps";
declare class RenderPaginationTable extends React.Component<RenderPaginationTableProps> {
    private page;
    private elementsByPage;
    static DEFAULT_SELECT_ELEMENTS_BY_PAGE: number[];
    private childRenderTable;
    private childRenderPagination;
    constructor(props: RenderPaginationTableProps);
    componentDidMount(): void;
    componentDidUpdate(): void;
    returnDataFunction(data: RenderPaginationTableData): void;
    loadDataAsync(page: number, elementsByPage: number): void;
    onClickPagination(page: number): void;
    onClickSelectElementsByPage(elementsByPage: number): void;
    render(): JSX.Element;
}
export default RenderPaginationTable;
