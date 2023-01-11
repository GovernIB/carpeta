/**
 * @author anadal
 * @create date 2023-01-11 07:56:19
 * @modify date 2023-01-11 07:56:19
 * @desc [description]
 */
import React from "react";
interface InternalPaginationItemCarpetaProps {
    children: JSX.Element | string;
    value: number;
    onClick: Function;
    active?: boolean;
}
declare class PaginationItemCarpeta extends React.Component<InternalPaginationItemCarpetaProps> {
    constructor(props: InternalPaginationItemCarpetaProps);
    render(): JSX.Element;
}
export { PaginationItemCarpeta };
