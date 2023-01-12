
set STARTTIME=%TIME%

call npm run build

xcopy  /Y .\dist\reactjs_main.js ..\src\main\resources\webpage

@echo off
call ..\..\elapsedtime.bat %STARTTIME% %TIME%
