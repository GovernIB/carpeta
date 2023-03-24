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
  onClick?(value:number):void;
  active?: boolean;
  title?:string;
}

class PaginationItemCarpeta extends React.Component<InternalPaginationItemCarpetaProps> {
  constructor(props: InternalPaginationItemCarpetaProps) {
    super(props);
  }

  render() {
    return (
      <li key={"item_" + this.props.value} className={`page-item ${this.props.active ? "active" : ""}`}>
        <a
          className="page-link"
          role="button"
          title={this.props.title?this.props.title:""}
          href={undefined}
          onClick={() => {
            if (this.props.onClick) { this.props.onClick(this.props.value); }
          }}
        >
          {this.props.children}
        </a>
      </li>
    );
  }
}

export { PaginationItemCarpeta };
