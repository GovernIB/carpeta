import React from "react";
import * as reactdetect from "react-device-detect";

/**
 *  @author anadal
 */

interface CarpetaDatePickerProps  {
  basename: string;
  defaultValue: Date;
  onChangeDate?(newDate: Date, oldDate: Date): boolean;
  i18n: any;
}

class CarpetaDatePicker extends React.Component<CarpetaDatePickerProps> {
  private datePickerName: string;

  constructor(props: CarpetaDatePickerProps) {
    super(props);

    console.log("CarpetaDatePicker::CONSTRUCTOR => Inici ");

    this.onChangeDateCarpetaDatePicker = this.onChangeDateCarpetaDatePicker.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);

    this.props.i18n.on("languageChanged", this.canviatIdioma);

    let base: number = Math.floor(Math.random() * 10000000);

    this.datePickerName = base + this.props.basename + "DatePicker";
  }

  private canviatIdioma(lang: string) {
    console.log("CarpetaDatePicker::canviatIdioma(" + lang + ") ...");

    //@ts-ignore
    $("#" + this.datePickerName).datetimepicker("locale", lang);

    this.forceUpdate();
  }

  componentDidMount() {
    //console.log("CarpetaDatePicker::componentDidMount() => Inici ... XXXXXXXXXXXX " + this.props.i18n.language);

    const theName = this.datePickerName;

    
    //@ts-ignore
    //$(".bootstrap-datetimepicker-widget").css("transform","scale(1.5,1.5)");
    


    //@ts-ignore
    $("#" + theName).datetimepicker({
      format: "DD/MM/YYYY",
      locale: this.props.i18n.language,
    });

    //@ts-ignore
    $("#" + theName).on("change.datetimepicker", this.onChangeDateCarpetaDatePicker);


  }

  private onChangeDateCarpetaDatePicker(e: any) {
    //console.log("change event fired   ************************************************** " + new Date().toString());
    console.log("e.date: => " + new Date(e.date));
    //console.log("e.oldDate: => " + new Date(e.oldDate));

    if (this.props.onChangeDate) {
      if (!this.props.onChangeDate(new Date(e.date), new Date(e.oldDate))) {
        /*
        let camp:any = document.getElementById(this.datePickerName + "Input");
        if (camp) {
          const lang = this.props.i18n.language;
          camp.value = e.oldDate.toLocaleString(lang, {
            year: "numeric",
            month: "2-digit",
            day: "2-digit",
          })
        }
        */
      }
    }
  }

  render() {
    console.log("CarpetaDatePicker::render() " + this.props.i18n.language);

    const lang = this.props.i18n.language;

    return (
      <div className="form-group">
        { reactdetect.isMobileOnly && <style dangerouslySetInnerHTML={{__html: `.bootstrap-datetimepicker-widget { transform:scale(2.5,2.5); transform-origin:top left; }`}} /> }
        <div className="input-group date" id={this.datePickerName} data-target-input="nearest">
          <input
            data-toggle="datetimepicker"
            className="form-control datetimepicker-input"
            data-target={"#" + this.datePickerName}
            style={{ padding: "0px 0px 0px 10px" }}
            id={this.datePickerName + "Input"}
            readOnly={false}
            value={this.props.defaultValue.toLocaleString(lang, {
              year: "numeric",
              month: "2-digit",
              day: "2-digit",
            })}
          />
          <div className="input-group-append" data-target={"#" + this.datePickerName} data-toggle="datetimepicker">
            <div className="input-group-text">
              <i className="fas fa-calendar"></i>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CarpetaDatePicker;
