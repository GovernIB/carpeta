package com.niamedtech.expo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.niamedtech.expo.exposerversdk.ExpoPushNotificationClient;
import com.niamedtech.expo.exposerversdk.request.PushNotification;
import com.niamedtech.expo.exposerversdk.response.TicketResponse.Ticket;
import com.niamedtech.expo.exposerversdk.util.PushNotificationUtil;

/**
 * 
 */
public class TestCarpeta {

    public static final Logger log = LoggerFactory.getLogger(TestCarpeta.class);
    
    public static Charset iso88591charset = Charset.forName("ISO-8859-1");
    
    
    public static void main(String[] args) {

        try {

            log.info(" Start ...");

            Properties prop = new Properties();

            final InputStreamReader in = new InputStreamReader(new FileInputStream(new File("mobileid.properties")), StandardCharsets.UTF_8);

            prop.load(in);

            String mobileid = prop.getProperty("mobileid");
            
            

            
                    //; // "Això és el títol";
            String title = toISO88591(prop.getProperty("titol"));
            String message = toISO88591(prop.getProperty("missatge"));
            String code = prop.getProperty("code");

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", code);
            if (!PushNotificationUtil.isExponentPushToken(mobileid)) {
                throw new Exception("Token:" + mobileid + " is not a valid token.");
            }

            PushNotification notification = new PushNotification();
            notification.setTo(Arrays.asList(mobileid));
            notification.setTitle(title);
            notification.setBody(message);
            notification.setData(data);


            List<PushNotification> all = new ArrayList<PushNotification>();
            all.add(notification);

            CloseableHttpClient httpClient = HttpClients.createDefault();

            ExpoPushNotificationClient client = new ExpoPushNotificationClient.Builder().setHttpClient(httpClient)
                    .build();

            List<Ticket> result = client.sendPushNotifications(all);

            for (Ticket ticket : result) {
                log.info(ticket.getId() + " - " + ticket.getStatus() + " - " + ticket.getMessage());
            }

        } catch (Exception e) {
            log.error("Error enviant nofificacio", e);
        }

        
    }


    protected static String toISO88591(String title) {
        CharBuffer inputBuffer = iso88591charset.decode(ByteBuffer.wrap(title.getBytes()));
                 
        title = inputBuffer.toString();
        return title;
    }

}
