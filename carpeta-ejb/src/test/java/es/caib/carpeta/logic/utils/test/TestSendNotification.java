package es.caib.carpeta.logic.utils.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.logic.utils.SendNotificationToMobile;
import es.caib.carpeta.logic.utils.I18NLogicUtils;
import es.caib.carpeta.logic.utils.SendNotificationResult;

/**
 * 
 * @author anadal
 *
 */
public class TestSendNotification {

    
    public static final Logger log = Logger.getLogger(TestSendNotification.class);
    
    public static void main(String[] args) {
        

        org.apache.log4j.BasicConfigurator.configure();

        try {
            
            log.error(" Start ..." );
            
            Properties prop = new Properties();
            
            final InputStreamReader in = new InputStreamReader(
                    new FileInputStream(new File("mobileid.properties") ), StandardCharsets.UTF_8);

            prop.load(in);

            String mobileid = prop.getProperty("mobileid");

            String title = prop.getProperty("titol"); //"Això és el títol";
            String message = prop.getProperty("missatge");
            String code = prop.getProperty("code");

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", code);
            SendNotificationResult result = SendNotificationToMobile.sendMessageToMobile(mobileid, title, message,
                    data);

            System.out.println(result);

        } catch (I18NException i18ne) {
            
            // Init Messages
            new I18NLogicUtils();
            

            System.err.println(I18NLogicUtils.getMessage(i18ne, new Locale("ca")));

            i18ne.printStackTrace(System.err);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
