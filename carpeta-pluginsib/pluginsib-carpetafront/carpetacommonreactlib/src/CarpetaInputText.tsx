/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */

import React from "react";

interface CarpetaInputTextProps {
  id: string;
  placeHolder: string;
  defaultValue?: string;
  onChangedText?(newText: string): void;
}

class CarpetaInputText extends React.Component<CarpetaInputTextProps> {
  constructor(props: CarpetaInputTextProps) {
    super(props);

    console.log("CarpetaInputText::CONSTRUCTOR => Inici ");

    this.onKeyDownInput = this.onKeyDownInput.bind(this);
    this.onKeyUpInput = this.onKeyUpInput.bind(this);
  }

  private onKeyDownInput(event: any) {
    if (event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
    return true;
  }

  private onKeyUpInput(event: any) {
    if (event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
    if (this.props.onChangedText) {
      this.props.onChangedText(event.target.value);
    }
    return true;
  }

  render() {
    return (
      <input
        placeholder={this.props.placeHolder}
        maxLength={25}
        tabIndex={501}
        type="text"
        id={this.props.id}
        readOnly={false}
        onKeyDown={this.onKeyDownInput}
        onKeyUp={this.onKeyUpInput}
        className="form-control form-control-sm focusIn font1App"
        defaultValue={this.props.defaultValue == undefined ? "" : this.props.defaultValue}
      />
    );
  }
}

export default CarpetaInputText;
