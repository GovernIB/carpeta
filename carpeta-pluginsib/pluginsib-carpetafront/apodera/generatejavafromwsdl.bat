REM wsimport -Xnocompile -XautoNameResolution -p org.fundaciobit.pluginsib.carpetafront.apodera.api -d .\src\main\java  .\apodera_services_reaCXFWSv2.wsdl


mvn clean install -P generatejavafromwsdl 
