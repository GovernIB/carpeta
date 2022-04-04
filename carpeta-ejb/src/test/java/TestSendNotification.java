import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import es.caib.carpeta.logic.utils.SendExpoNotification;
import es.caib.carpeta.logic.utils.SendExpoNotification.ResultSendNotification;

/**
 * 
 * @author anadal
 *
 */
public class TestSendNotification {
    
    
    public static void main(String[] args) {
        
        
        try {
            Properties prop = new Properties();
            
            prop.load(new FileInputStream(new File("mobileid.properties")));
            
            String mobileid = prop.getProperty("mobileid");
            
            String title = prop.getProperty("titol");
            String message = prop.getProperty("missatge");
            String code = prop.getProperty("code");
            
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", code);
            ResultSendNotification result = SendExpoNotification.sendMessageToMobile(mobileid, title, message, data);

            System.out.println(result);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
