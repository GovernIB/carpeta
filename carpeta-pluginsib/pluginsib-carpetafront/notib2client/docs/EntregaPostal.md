# EntregaPostal

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**tipus** | [**TipusEnum**](#TipusEnum) | Enumerat que indica el tipus de entrega postal. | 
**viaTipus** | [**ViaTipusEnum**](#ViaTipusEnum) | Enumerat que indica el tipus de via per a entregues postals  * Obligatori quan tipoDomicili &#x3D; NACIONAL |  [optional]
**viaNom** | **String** | Nom de la via per entregues postals  * Obligatori quan tipoDomicili &#x3D; NACIONAL o ESTRANGER |  [optional]
**numeroCasa** | **String** | Número de la casa per entregues postals  * Obligatori quan tipoDomicili &#x3D; NACIONAL excepte si s’indica el punt quilomètric |  [optional]
**numeroQualificador** | **String** | Informació addicional sobre la numeració de l’adreça |  [optional]
**puntKm** | **String** | Punt quilomètric per entregues postals  * Obligatori quan tipoDomicili &#x3D; NACIONAL excepte si s’indica numeroCasa |  [optional]
**apartatCorreus** | **String** | Apartat de correus per entregues postals  * Obligatori quan tipoDomicili &#x3D; APARTAT_CORREUS |  [optional]
**portal** | **String** | Portal de la casa per entregues postals |  [optional]
**escala** | **String** | Escala de la casa per entregues postals |  [optional]
**planta** | **String** | Planta de la casa per entregues postals |  [optional]
**porta** | **String** | Porta de la casa per entregues postals |  [optional]
**bloc** | **String** | Bloc de la casa per entregues postals |  [optional]
**complement** | **String** | Informació extra sobre la casa per entregues postals |  [optional]
**codiPostal** | **String** | Codi postal de la casa per entregues postals.  * Per enviaments internacionals a països sense codi posatal es pot posar 00000. | 
**poblacio** | **String** | Població on s’entrega l’enviament  * Obligatori quan tipoDomicili &#x3D; NACIONAL, ESTRANGER o APARTAT_POSTAL |  [optional]
**municipiCodi** | **String** | Codi INE de 6 caràcters del municipi on s’entrega l’enviament (inclou codi de control)  * Obligatori quan tipoDomicili &#x3D; NACIONAL o APARTAT_POSTAL |  [optional]
**provincia** | **String** | Codi INE de 2 dígits de la província on s’entrega l’enviament  * Obligatori quan tipoDomicili &#x3D; NACIONAL o APARTAT_POSTAL |  [optional]
**paisCodi** | **String** | Codi ISO 3166 de 2 caràcters del país on s’entrega l’enviament * Obligatori quan tipoDomicili &#x3D; ESTRANGER |  [optional]
**linea1** | **String** | Línia 1 de l’adreça d’entrega de l’enviament sense normalitzar  * Obligatori quan tipoDomicili &#x3D; SENSE_NORMALITZAR |  [optional]
**linea2** | **String** | Línia 2 de l’adreça d’entrega de l’enviament sense normalitzar  * Obligatori quan tipoDomicili &#x3D; SENSE_NORMALITZAR |  [optional]
**cie** | **String** | Línia 2 de l’adreça d’entrega de l’enviament sense normalitzar |  [optional]
**formatSobre** | **String** | Cadena indicant el format del sobre  |  [optional]
**formatFulla** | **String** | Cadena indicant el format de la fulla |  [optional]

<a name="TipusEnum"></a>
## Enum: TipusEnum
Name | Value
---- | -----
NACIONAL | &quot;NACIONAL&quot;
ESTRANGER | &quot;ESTRANGER&quot;
APARTAT_CORREUS | &quot;APARTAT_CORREUS&quot;
SENSE_NORMALITZAR | &quot;SENSE_NORMALITZAR&quot;

<a name="ViaTipusEnum"></a>
## Enum: ViaTipusEnum
Name | Value
---- | -----
ALAMEDA | &quot;ALAMEDA&quot;
CALLE | &quot;CALLE&quot;
CAMINO | &quot;CAMINO&quot;
CARRER | &quot;CARRER&quot;
CARRETERA | &quot;CARRETERA&quot;
GLORIETA | &quot;GLORIETA&quot;
KALEA | &quot;KALEA&quot;
PASAJE | &quot;PASAJE&quot;
PASEO | &quot;PASEO&quot;
PLA_A | &quot;PLAÇA&quot;
PLAZA | &quot;PLAZA&quot;
RAMBLA | &quot;RAMBLA&quot;
RONDA | &quot;RONDA&quot;
RUA | &quot;RUA&quot;
SECTOR | &quot;SECTOR&quot;
TRAVESIA | &quot;TRAVESIA&quot;
URBANIZACION | &quot;URBANIZACION&quot;
AVENIDA | &quot;AVENIDA&quot;
AVINGUDA | &quot;AVINGUDA&quot;
BARRIO | &quot;BARRIO&quot;
CALLEJA | &quot;CALLEJA&quot;
CAMI | &quot;CAMI&quot;
CAMPO | &quot;CAMPO&quot;
CARRERA | &quot;CARRERA&quot;
CUESTA | &quot;CUESTA&quot;
EDIFICIO | &quot;EDIFICIO&quot;
ENPARANTZA | &quot;ENPARANTZA&quot;
ESTRADA | &quot;ESTRADA&quot;
JARDINES | &quot;JARDINES&quot;
JARDINS | &quot;JARDINS&quot;
PARQUE | &quot;PARQUE&quot;
PASSEIG | &quot;PASSEIG&quot;
PRAZA | &quot;PRAZA&quot;
PLAZUELA | &quot;PLAZUELA&quot;
PLACETA | &quot;PLACETA&quot;
POBLADO | &quot;POBLADO&quot;
VIA | &quot;VIA&quot;
TRAVESSERA | &quot;TRAVESSERA&quot;
PASSATGE | &quot;PASSATGE&quot;
BULEVAR | &quot;BULEVAR&quot;
POLIGONO | &quot;POLIGONO&quot;
OTROS | &quot;OTROS&quot;
