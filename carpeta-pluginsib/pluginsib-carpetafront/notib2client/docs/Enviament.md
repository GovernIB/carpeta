# Enviament

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**titular** | [**Persona**](Persona.md) |  | 
**destinataris** | [**List&lt;Persona&gt;**](Persona.md) | Conjunt de destinataris als que s&#x27;enviarà la notificació.  * Depenent de l&#x27;entitat es permet informar únicament un destinatari per enviament a múltiples destinataris (Ex. la CAIB únicamnet admet un destinatari.  * En cas d&#x27;informar múltiples destinataris, només que un d&#x27;ells accepti la notificació, aquesta es dóna per notificada. |  [optional]
**entregaPostalActiva** | **Boolean** | Indica si s&#x27;ha de realitzar entrega postal (Consultar prèviament si està disponible l&#x27;entrega postal a l&#x27;òrgan gestor emissor) |  [optional]
**entregaPostal** | [**EntregaPostal**](EntregaPostal.md) |  |  [optional]
**entregaDehActiva** | **Boolean** | Indica si s&#x27;ha de realitzar l&#x27;enviament a la DEH __Actualment en desús amb l&#x27;entrada en funcionament de la DEHú__ |  [optional]
**entregaDeh** | [**EntregaDeh**](EntregaDeh.md) |  |  [optional]
**serveiTipus** | [**ServeiTipusEnum**](#ServeiTipusEnum) | Enumerat que indica la urgència que té l’enviament. |  [optional]

<a name="ServeiTipusEnum"></a>
## Enum: ServeiTipusEnum
Name | Value
---- | -----
NORMAL | &quot;NORMAL&quot;
URGENT | &quot;URGENT&quot;
