/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */
import React from "react";
import { RenderTableProps, RenderTableData } from "./RenderTableProps";
interface RenderTableState extends RenderTableData {
    isLoaded: boolean;
}
declare class RenderTable extends React.Component<RenderTableProps, RenderTableState> {
    constructor(props: RenderTableProps);
    componentDidMount(): void;
    updateTableData(dades: RenderTableData | null): void;
    render(): JSX.Element;
}
export default RenderTable;
