package es.caib.carpeta.front.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

@WebListener
public class HttpSessionChecker implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {
        System.out.printf("Session ID is created at soon", event.getSession().getId(), new Date());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.printf("Session ID is destroyed at soon", event.getSession().getId(), new Date());
    }

}