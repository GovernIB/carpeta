/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */
import { Component } from "react";
import { RenderInternalTableProps } from "./RenderTableProps";
declare class RenderTableDesktop extends Component<RenderInternalTableProps> {
    constructor(props: RenderInternalTableProps);
    componentDidMount(): void;
    onClickTableRow(i: number): void;
    mostrarMesInfo(row: string): void;
    render(): JSX.Element;
}
export default RenderTableDesktop;
