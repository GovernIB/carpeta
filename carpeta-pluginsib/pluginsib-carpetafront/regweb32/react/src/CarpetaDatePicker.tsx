import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";

//import $ from 'jquery';

/**
 *  @author anadal
 */

interface CarpetaDatePickerProps extends WithTranslation {
  name: string;
  defaultValue: Date;
  onChangeDate?(newDate: Date, oldDate: Date): void;
}

class CarpetaDatePicker extends React.Component<CarpetaDatePickerProps> {
  private inputName: string;

  constructor(props: CarpetaDatePickerProps) {
    super(props);

    console.log("CarpetaDatePicker::CONSTRUCTOR => Inici ");

    this.onChangeDateCarpetaDatePicker = this.onChangeDateCarpetaDatePicker.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);

    this.props.i18n.on("languageChanged", this.canviatIdioma);

    this.inputName = this.props.name + "Input";
  }

  canviatIdioma(lang: string) {
    console.log("CarpetaDatePicker::canviatIdioma(" + lang + ") ...");
    //const theName = this.props.name;

    const theName = this.props.name;
    // XYZ ZZZ
    //@ts-ignore

    $("#" + theName).datetimepicker({
      format: "DD/MM/YYYY",
      locale: lang,
    });

    this.forceUpdate();
  }

  componentDidMount() {
    console.log("CarpetaDatePicker::componentDidMount() => Inici ... XXXXXXXXXXXX " + this.props.i18n.language);

    const theName = this.props.name;
    // XYZ ZZZ
    //@ts-ignore

    $("#" + theName).datetimepicker({
      format: "DD/MM/YYYY",
      locale: this.props.i18n.language,
    });

    // ts-ignore

    $("#" + theName).on("change.datetimepicker", this.onChangeDateCarpetaDatePicker);
  }

  onChangeDateCarpetaDatePicker(e: any) {
    console.log("change event fired   ************************************************** " + new Date().toString());
    console.log("e.date: => " + new Date(e.date));
    console.log("e.oldDate: => " + new Date(e.oldDate));

    /*
        let de = document.getElementById({this.inputName});
        if (de != null) {
          //@ts-ignore
          console.log("              Nova Data: " + de.value);
        }
*/

    if (this.props.onChangeDate) {
      this.props.onChangeDate(new Date(e.date), new Date(e.oldDate));
    }
  }

  render() {
    console.log("CarpetaDatePicker::render() " + this.props.i18n.language);
    const t = this.props.i18n.t;

    const lang = this.props.i18n.language;

    return (
      <div className="form-group">
        <div className="input-group date" id={this.props.name} data-target-input="nearest">
          <input
            data-toggle="datetimepicker"
            className="form-control datetimepicker-input"
            data-target={"#" + this.props.name}
            style={{ padding: "0px 0px 0px 10px" }}
            id={this.inputName}
            readOnly={false}
            value={this.props.defaultValue.toLocaleString(lang, {
              year: "numeric",
              month: "2-digit",
              day: "2-digit",
            })}
          />
          <div className="input-group-append" data-target={"#" + this.props.name} data-toggle="datetimepicker">
            <div className="input-group-text">
              <i className="fas fa-calendar"></i>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withTranslation()(CarpetaDatePicker);
