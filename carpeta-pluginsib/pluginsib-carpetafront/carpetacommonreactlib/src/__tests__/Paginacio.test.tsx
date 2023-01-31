import React from "react";

// , fireEvent, waitForElement
import { render, screen } from "@testing-library/react";

import PaginationCarpetaProps from "../PaginationCarpetaProps";
import PaginationCarpeta from "../PaginationCarpeta";

function onClickPaginacio(pagina: number) {
  console.log("S'ha clicat item " + pagina);
}

function i18nAddResourceBundle(lang: string, space: string, translations: any, value1: boolean, value2: boolean) {
  if (!lang) {
    console.log(space + translations + value1 + value2);
  }
}

describe("PaginationTest", () => {
  test("Ha de mostrarpaginacio ...", async () => {
    const paginacioProps: PaginationCarpetaProps = {
      paginaActual: 2,
      elementsPerPagina: 4,
      totalPagines: 5,
      registresRetornats: 6,
      totalRegistres: 25,
      onClickPagination: onClickPaginacio,
    };

    const i18nmock: any = {
      t: (str: string) => str,
      i18n: {
        changeLanguage: () => new Promise(() => {}),
      },
      addResourceBundle: i18nAddResourceBundle,
    };

    render(<PaginationCarpeta i18n={i18nmock} paginationInfo={paginacioProps} />);

    //expect(screen.getByRole("button")).toHaveTextContent(label);

    var url = screen.logTestingPlaygroundURL();

    console.log("[[[" + url + "]]]");
  });
});
