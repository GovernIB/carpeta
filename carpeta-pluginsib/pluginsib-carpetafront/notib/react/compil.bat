set STARTTIME=%TIME%

call npm run build --profile

xcopy  /Y .\dist\notib_reactjs_main.js ..\src\main\resources\webpage

@echo off
call ..\..\elapsedtime.bat %STARTTIME% %TIME%
