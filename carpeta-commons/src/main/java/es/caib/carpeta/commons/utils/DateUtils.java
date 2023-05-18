package es.caib.carpeta.commons.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date sumarRestarDiasFecha(Date fecha, int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }
    
    
    
    public static Date darreraHoraDelDia(Date date) {
        
        Calendar cal = Calendar.getInstance();
        
        cal.setTimeInMillis(date.getTime());
        
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        
        return cal.getTime();
    }
}
