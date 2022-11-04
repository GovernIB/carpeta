divendres en Simó em va enviar les instruccions per crear tràmits a DEV. Ho reenvío al grup.
Per iniciar tràmits no seria necessari tenir cap rol. Te recoman que sí tenguis Cl@ve, perquè a partir d'ara només se podrà accedir a Carpeta, i fer tràmits, a través de Cl@ve.

De SISTRA1 tens aquesta URL: https://dev.caib.es/sistrafront/tramites/ , on podràs trobar els següents tràmits per provar:

- [TS0010REGT] - Trámite test registro telemático (versión 1): el trámite más sencillo y rápido para generar solicitudes finalizadas con registro electrónico.
- [TS0009ENVI] - Trámite test de envío (versión 1): el trámite más sencillo y rápido para generar solicitudes finalizadas y "enviadas" a zonaper, sin registro.
- [TS0005TEST] - Trámite test plataforma (versión1): es un trámite más complejo y largo, que permite probar bastantes funcionalidades de la plataforma. Acaba también en un registro electrónico.
- [TS0011PAGO] - Trámite test de pago (versión 1): es un trámite sencillo que permite probar la integración con la pasarela de pagos de la ATIB. Como admite el pago presencial, se puede utilizar para probar el preregistro.
- [TS0012FRMA] - Trámite test de firma de formularios y solicitud (versión1): es un trámite sencillo que nos permite probar la firma electrónica avanzada. También acaba en un registro electrónico.
- [TS0017REPR] - Trámite test registro telemático representante representado (versión 1): es un trámite sencillo que permite probar modalidades de representación. También acaba en un registro electrónico.

SISTRA2, en canvi, funciona diferent. Te pas la URL per realitzar un tràmit similar al TS0010REGT però per SISTRA2.

https://dev.caib.es/sistramitfront/asistente/iniciarTramite.html?tramite=TEST-REGISTRE&version=2&idioma=ca&servicioCatalogo=false&idTramiteCatalogo=3850523

Quan l'inicïis te permetrà escollir, dins Cl@ve, com te vols autenticar: via DNIe/Certificat Digital o a través de Cl@ve PIN. És important destacar que la manera d'autenticar-se hauria de permetre reprendre, o no, un tràmit. Per exemple: si inicies el tràmit anterior amb DNIe/Certificat, t'autenticaràs amb un nivell de QAA=3 (nivell d'autenticació ALT); en canvi, si ho fas per Cl@ve PIN, entraràs amb un QAA=2 (nivell autenticació MITJÀ). Això és important perquè, a CARPETA, només es podran reprendre tràmits on el nivell de QAA sigui igual o superior al del tràmit. Ex: Si el tràmit QAA=3 i jo entr dins CARPETA amb QAA=2, no m'hauria de permetre poder reprendre'l (aquesta comprovació la fa SISTRA2). Tot això que te cont no és aplicable a SISTRA1 perquè no està preparat per això.


