
set STARTTIME=%TIME%

call npm run bundleup

xcopy  /Y .\dist\reactjs_main.js ..\src\main\resources\webpage

call ..\..\elapsedtime.bat %STARTTIME% %TIME%
