set STARTTIME=%TIME%

call npm run build

xcopy  /Y .\dist\notib_reactjs_main.js ..\src\main\resources\webpage\notib_reactjs_main.js

@echo off
call ..\..\elapsedtime.bat %STARTTIME% %TIME%
