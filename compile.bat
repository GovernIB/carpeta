@echo off

echo optional parameters -Dcaib -Psqlgen

cmd /C mvn -DskipTests %* clean install

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