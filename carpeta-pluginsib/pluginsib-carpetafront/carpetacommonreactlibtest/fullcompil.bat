set STARTTIME=%TIME%

cd ..\carpetacommonreactlib

call npm run build

cd ..\carpetacommonreactlibtest

call npm run build

@echo off
call ..\elapsedtime.bat %STARTTIME% %TIME%

start index.html
