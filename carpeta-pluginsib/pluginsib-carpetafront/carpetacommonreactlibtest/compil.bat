set STARTTIME=%TIME%

call npm run build

@echo off
call ..\elapsedtime.bat %STARTTIME% %TIME%
