/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */
import { Component } from "react";
type RenderTableProps = {
    dades: any[];
    columnsNom: any[];
    columnsTitols: any[];
};
declare class RenderTableDesktop extends Component<RenderTableProps> {
    constructor(props: RenderTableProps);
    render(): JSX.Element;
}
export default RenderTableDesktop;
