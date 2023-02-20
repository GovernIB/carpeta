import React from "react";

import {RowType} from "./RowType"

class RowTypeUtils {
  static getIcon(rowType: RowType, color: boolean = true): JSX.Element {
    let colorStyle;

    if (color == true) {
      colorStyle = { color: "#32814B" };
    } else {
      colorStyle = {};
    }

    switch (rowType) {
      case RowType.INTERNAL_LINK:
        return <i className="fas fa-external-link-square-alt fa-lg" style={colorStyle}></i>;
      case RowType.EXTERNAL_LINK:
        return <i className="fas fa-external-link-alt fa-lg" style={colorStyle}></i>;
      case RowType.DOWNLOAD_FILE:
        return <i className="fas fa-file-download fa-lg" style={colorStyle}></i>;
      case RowType.OPEN_DIALOG:
        return <i className="far fa-window-maximize fa-lg" style={colorStyle}></i>;
      case RowType.SHOW_ADDITIONAL_INFO:
        return <i className="fas fa-caret-square-down fa-lg" style={colorStyle}></i>;
      case RowType.OTHER_INFO:
        return <i className="fas fa-info-circle fa-lg" style={colorStyle}></i>;
      case RowType.NONE:
      default:
        return <></>;
    }
  }

  static getLabel(rowType: RowType, i18n: any): string {
    switch (rowType) {
      case RowType.INTERNAL_LINK:
        return i18n.t("rowtype.internal_link");
      case RowType.EXTERNAL_LINK:
        return i18n.t("rowtype.external_link");
      case RowType.DOWNLOAD_FILE:
        return i18n.t("rowtype.download_file");
      case RowType.OPEN_DIALOG:
        return i18n.t("rowtype.open_dialog");
      case RowType.SHOW_ADDITIONAL_INFO:
        return i18n.t("rowtype.show_additional_info");
      case RowType.OTHER_INFO:
        return i18n.t("rowtype.other_info");
      case RowType.NONE:
      default:
        return "";
    }
  }
}

export { RowType, RowTypeUtils };
