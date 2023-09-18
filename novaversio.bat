
@echo off 

IF "%~1" == "" GOTO SENSEPARAMS

@echo on
cmd /C mvn -DgroupId=es.caib.carpeta -DartifactId=* versions:set -DnewVersion=%*

@echo off
SETLOCAL EnableDelayedExpansion
for /F "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do rem"') do (
  set "DEL=%%a"
)

REM @echo. 
REM @echo. 
REM call :ColorText 4F "--------------------------- IMPORTANT ---------------------------------"
REM @echo. 
REM call :ColorText 4F "[  Has d'actualitzar la propietat carpeta.version del pom.xml arrel  ]"
REM @echo. 
REM call :ColorText 4F "-----------------------------------------------------------------------"

@echo.
@echo.
@echo off

GOTO EXIT

:ColorText
@echo off
<nul set /p ".=%DEL%" > "%~2"
findstr /v /a:%1 /R "^$" "%~2" nul
del "%~2" > nul 2>&1
goto :eof


:SENSEPARAMS

@echo.
@echo.
echo Falta versio
@echo.
@echo.


:EXIT
