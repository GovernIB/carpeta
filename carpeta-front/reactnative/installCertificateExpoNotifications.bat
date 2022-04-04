
REM Instal·la el certificat del servidor que en que es troba l'API d'Expo per enviar notificacions als Mòbils 

keytool -importcert -noprompt -file expopushnotifications.cer  -alias expopushnotifications  -keystore %JAVA_HOME%\lib\security\cacerts -storepass changeit
