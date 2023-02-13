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
          href="javascript:console.log();"
          onClick={() => {
            this.props.onClick(this.props.value);
          }}
        >
          <a>{this.props.children}</a>
        </a>
      </li>
    );
  }
}

export { PaginationItemCarpeta };
