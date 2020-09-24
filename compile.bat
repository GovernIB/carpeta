@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-11

echo optional parameters -Dcaib -Psqlgen

cmd /C mvn -DskipTests %* clean install

echo ELIMINANDO TEMPORALES JBOSS
rd /q /s C:\Users\mgonzalez\Documents\servidores\EAP-7.2.0\standalone\deployments\tmp
rd /q /s C:\Users\mgonzalez\Documents\servidores\EAP-7.2.0\standalone\log

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED CARPETA_DEPLOY_DIR (
      for /f "tokens=* delims=" %%x in (versio.txt) do set CARPETA_VERSIO=%%x
	  @echo on
	  echo --------- COPIANT EAR %CARPETA_VERSIO% ---------

	  xcopy /Y carpeta-ear\target\carpeta.ear %CARPETA_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn CARPETA_DEPLOY_DIR apuntant al
	  echo    directori de deploy del JBOSS  i automaticament s'hi copiara
	  echo    l'ear generat.
	  echo  =================================================================
	) 

)