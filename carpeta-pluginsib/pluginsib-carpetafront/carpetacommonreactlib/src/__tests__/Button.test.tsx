import React from "react";

// , fireEvent, waitForElement
import { render, screen } from "@testing-library/react";

import { Button } from "../Button";

describe("ButtonTest", () => {
  test("Ha de mostrar un boto amb XXXX ...", async () => {
    //const result  =
    let label = "XX Hola Caracola";
    render(<Button>{label}</Button>);

    //expect(screen.getByRole('button')).toBeDisabled()

    //console.log(result);

    //expect(screen.getByText("XXXX")).toBeInTheDocument();

    expect(screen.getByRole("button")).toHaveTextContent(label);

    var url = screen.logTestingPlaygroundURL();

    console.log("[[[" + url + "]]]");
  });
});
