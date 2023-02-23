
set STARTTIME=%TIME%

call npm run build --profile

xcopy  /Y .\dist\reactjs_main.js ..\src\main\resources\webpage_pinbalmatricula
@echo off
call ..\..\elapsedtime.bat %STARTTIME% %TIME%
