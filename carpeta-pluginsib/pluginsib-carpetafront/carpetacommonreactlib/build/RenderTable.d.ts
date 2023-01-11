/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */
import React from "react";
type RenderTableProps = {
    dades: any[];
    columnsNom: any[];
    columnsTitols: any[];
    onClickRow?: Function;
    mobileIcon?: string;
};
declare class RenderTable extends React.Component<RenderTableProps> {
    constructor(props: RenderTableProps);
    render(): JSX.Element;
}
export default RenderTable;
