#!/bin/bash

echo off
cat help.txt

env mvn -DskipTests $@ install 

if [ $? == 0 ]; then
  if [ "CARPETA_DEPLOY_DIR" == "" ];  then

    echo  =================================================================
    echo    Definex la variable d\'entorn CARPETA_DEPLOY_DIR apuntant al
    echo    directori de deploy del JBOSS  i automaticament s\'hi copiara
    echo    l\'ear generat.
    echo  =================================================================
  
  else
  
    echo on
    echo --------- COPIANT EAR ---------
    cp ./ear/target/carpeta.ear $CARPETA_DEPLOY_DIR

  fi
fi


