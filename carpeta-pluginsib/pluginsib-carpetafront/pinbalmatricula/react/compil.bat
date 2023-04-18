
set STARTTIME=%TIME%

call npm run build --profile

xcopy  /Y .\dist\reactjs_main_pinbalmatricula.js ..\src\main\resources\webpage_pinbalmatricula\reactjs_main_pinbalmatricula.js
@echo off
call ..\..\elapsedtime.bat %STARTTIME% %TIME%
