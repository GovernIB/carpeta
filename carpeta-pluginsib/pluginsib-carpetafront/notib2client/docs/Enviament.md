

# Enviament

Llista d'enviaments continguts en la Notificació/Comunicació

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**titular** | [**Persona**](Persona.md) |  |  |
|**destinataris** | [**List&lt;Persona&gt;**](Persona.md) | Conjunt de destinataris als que s&#39;enviarà la notificació.  * Depenent de l&#39;entitat es permet informar únicament un destinatari per enviament a múltiples destinataris (Ex. la CAIB únicamnet admet un destinatari.  * En cas d&#39;informar múltiples destinataris, només que un d&#39;ells accepti la notificació, aquesta es dóna per notificada. |  [optional] |
|**entregaPostalActiva** | **Boolean** | Indica si s&#39;ha de realitzar entrega postal (Consultar prèviament si està disponible l&#39;entrega postal a l&#39;òrgan gestor emissor) |  [optional] |
|**entregaPostal** | [**EntregaPostal**](EntregaPostal.md) |  |  [optional] |
|**entregaDehActiva** | **Boolean** | Indica si s&#39;ha de realitzar l&#39;enviament a la DEH __Actualment en desús amb l&#39;entrada en funcionament de la DEHú__ |  [optional] |
|**entregaDeh** | [**EntregaDeh**](EntregaDeh.md) |  |  [optional] |
|**serveiTipus** | [**ServeiTipusEnum**](#ServeiTipusEnum) | Enumerat que indica la urgència que té l’enviament. |  [optional] |



## Enum: ServeiTipusEnum

| Name | Value |
|---- | -----|
| NORMAL | &quot;NORMAL&quot; |
| URGENT | &quot;URGENT&quot; |



