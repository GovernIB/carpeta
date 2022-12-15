BEGIN;

-- Plugin de "Els Meus Expedients"
UPDATE car_plugin SET propietats = concat(car_plugin.propietats, '

#Filtres plugin Expedient
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.metadada=eni:metadada_sample
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.operacio=CONTE
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.valor1=valor1_sample
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.valor2=valor2_sample

#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.2.metadada=...')
WHERE classe = 'org.fundaciobit.pluginsib.carpetafront.expedients.ExpedientsCarpetaFrontPlugin';