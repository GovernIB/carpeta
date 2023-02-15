set STARTTIMEALL=%TIME%

FOR %%G IN ("carpetacommonreactlib","carpetacommonreactlibtest","regwebdetallcomponent\reactlib","apodera\react","dadeslogin\react","expedients\react","notib\react","notificacionssistra\react","pinbalayudasubvenciones\react","pinbalcontrataciones\react","pinbalconvivencia\react","pinbaldiscapacidad\react","pinbalfamilia\react","pinbalhistorico\react","pinbalmatricula\react","pinbalpolicia\react","regweb32\react","reprendretramitsistra\react","sistra\react") DO (
@echo off
echo -
echo =================================================
echo == Compilant  %%G ==
echo =================================================
echo -

@echo on
cd %%G

if exist node_modules (
REM No fer res
) ELSE (
  call npm install
)

call compil.bat
if %ERRORLEVEL% NEQ 0 goto :eof

if %%G=="carpetacommonreactlib" (
   REM No fer res
) ELSE (
  if %%G=="carpetacommonreactlibtest" (
    REM no fer res
  ) ELSE (
    cd ..
  )
)
cd ..

)


@echo off
call elapsedtime.bat %STARTTIMEALL% %TIME%






