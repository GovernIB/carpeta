set STARTTIME=%TIME%

call npm run build

xcopy  /Y .\dist\reactjs_main.js ..\src\main\resources\webpage_expedients\expedients_reactjs_main.js

@echo off
call ..\..\elapsedtime.bat %STARTTIME% %TIME%
