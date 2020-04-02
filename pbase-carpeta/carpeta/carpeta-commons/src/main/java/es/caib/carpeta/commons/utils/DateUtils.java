package es.caib.carpeta.core.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date sumarRestarDiasFecha(Date fecha, int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }
}
