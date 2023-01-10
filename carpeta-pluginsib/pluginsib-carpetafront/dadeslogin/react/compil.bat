REM call npm run build --profile

call npm run bundleup

xcopy  /Y .\dist\reactjs_main.js ..\src\main\resources\webpage_dadeslogin
