CONFIGURACIÓ DEL SUBSISTEMA KEYCLOAK DINS JBOSS

Ficar el contingut del fitxer keycloak-subsystem.xml dins el fitxer standalone.xml (o standalone-full.xml) del JBoss,
per activar la seguretat amb keycloak pels mòduls Back i l'API REST. Si keycloak està instal·lat correctament el tag
<subsystem> amb el namespace de keycloak ja hi hauria de ser.

El tag <auth-server-url> fa referència a la URL del servidor keycloak. Els tags <resource> fan referència als "client"
definit dins keycloak.

Veure el manual de configuració dels nous estàndards per veure com definir aquests valors.