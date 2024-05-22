

# NotificacioV2

Objecte amb les dades necessàries per a donar d'alta una notificació

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**emisorDir3Codi** | **String** | Codi Dir3 de l’organisme emisor (Entitat de Notib) |  |
|**organGestor** | **String** | Codi DIR3 de l’òrgan gestor que realitza la notificació/comunicació.  * Obligatori en el cas de procediments comuns.  * En el cas de notificacions de procediments no comuns, no s&#39;utilitzarà aquest camp, sinó que s’utilitzarà l’òrgan gestor al que pertany el procediment. |  [optional] |
|**enviamentTipus** | [**EnviamentTipusEnum**](#EnviamentTipusEnum) | Enumerat que indica si l’enviament és una comunicació o una notificació |  |
|**concepte** | **String** | Concepte de l’enviament.   * Si ha d’anar a CIE només s’agafaran els 50 primers caràcters |  |
|**descripcio** | **String** | Descripció detallada de l’enviament |  [optional] |
|**enviamentDataProgramada** | **Date** | Data en la que l’enviament es posarà a disposició per a la compareixença.  * En cas de no informar-se es posarà en disposició per a la compareixença de forma inmediata.  El format de la data serà del tipus yyyy-MM-dd |  [optional] |
|**retard** | **Integer** | Dies que l’enviament estarà a disposició de compareixença en carpeta abans d’entregar-lo per altres mitjans |  [optional] |
|**caducitat** | **Date** | Data d’expiració de l’enviament.  * Aquest camp és excloent amb el de caducitatDiesNaturals. Només s&#39;ha d&#39;indicar un dels dos. En cas d&#39;informar els dos, s&#39;ignorarà el camp caducitatDiesNaturals.  * Aquest camp és obligatori per notificacions i opcional per a comunicacions.  El format de la data serà del tipus yyyy-MM-dd |  [optional] |
|**caducitatDiesNaturals** | **Integer** | Dies naturals abans que expiri l&#39;enviament  * Aquest camp és excloent amb el de caducitat. Només s&#39;ha d&#39;indicar un dels dos. En cas d&#39;informar els dos, aquest s&#39;ignorarà. |  [optional] |
|**usuariCodi** | **String** | Codi de l’usuari que està realitzant la notificació. Aquest usuari s&#39;utilitzarà per a realitzar l&#39;assentament registral de sortida |  |
|**procedimentCodi** | **String** | Identificador del procediment SIA al que pertany la notificació. Obligatori en el cas de notificacions |  [optional] |
|**grupCodi** | **String** | Codi del grup al que s’assigna la notificació.  * Aquest camp només té sentit quan es vol donar d’alta una notificació que s’ha configurat amb grups a Notifica (veure manual d’usuari) |  [optional] |
|**numExpedient** | **String** | Identificador de l&#39;expedient al qual pertany la notificació |  [optional] |
|**enviaments** | [**List&lt;Enviament&gt;**](Enviament.md) | Llista d&#39;enviaments continguts en la Notificació/Comunicació |  |
|**idioma** | [**IdiomaEnum**](#IdiomaEnum) | Enumerat que indica l’idioma de la notificació |  [optional] |
|**document** | [**DocumentV2**](DocumentV2.md) |  |  |
|**document2** | [**DocumentV2**](DocumentV2.md) |  |  [optional] |
|**document3** | [**DocumentV2**](DocumentV2.md) |  |  [optional] |
|**document4** | [**DocumentV2**](DocumentV2.md) |  |  [optional] |
|**document5** | [**DocumentV2**](DocumentV2.md) |  |  [optional] |



## Enum: EnviamentTipusEnum

| Name | Value |
|---- | -----|
| NOTIFICACIO | &quot;NOTIFICACIO&quot; |
| COMUNICACIO | &quot;COMUNICACIO&quot; |
| SIR | &quot;SIR&quot; |



## Enum: IdiomaEnum

| Name | Value |
|---- | -----|
| CA | &quot;CA&quot; |
| ES | &quot;ES&quot; |



