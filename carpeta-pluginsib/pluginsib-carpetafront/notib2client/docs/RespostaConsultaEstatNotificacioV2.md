

# RespostaConsultaEstatNotificacioV2


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**error** | **Boolean** | Indica si s&#39;ha produït algún error en l&#39;enviament |  [optional] |
|**errorData** | **Long** | Data en que s&#39;ha produït l&#39;error |  [optional] |
|**errorDescripcio** | **String** | Identificador de la notificació a Notib |  [optional] |
|**identificador** | **String** | Identificador de la notificació a Notib |  [optional] |
|**estat** | [**EstatEnum**](#EstatEnum) | Enumerat amb la informació de l&#39;estat de l&#39;enviament |  [optional] |
|**tipus** | **String** | Indica si l’enviament és una comunicació o una notificació |  [optional] |
|**emisorDir3** | **String** | Codi Dir3 de l’organisme emisor (Entitat de Notib) |  [optional] |
|**procediment** | [**Procediment**](Procediment.md) |  |  [optional] |
|**organGestorDir3** | **String** | Codi DIR3 de l’òrgan gestor que realitza la notificació/comunicació |  [optional] |
|**concepte** | **String** | Concepte de la notificació |  [optional] |
|**numExpedient** | **String** | Identificador de l&#39;expedient al qual pertany la notificació |  [optional] |
|**dataCreada** | **Long** | Data en que s&#39;ha donat d&#39;alta la notificació |  [optional] |
|**dataEnviada** | **Long** | Data en que s&#39;ha enviat la notificació a Notifica (o SIR) |  [optional] |
|**dataFinalitzada** | **Long** | Data en que s&#39;ha finalitzat la notificació |  [optional] |
|**dataProcessada** | **Long** | Data en que s&#39;ha marcat la notificació com a processada |  [optional] |



## Enum: EstatEnum

| Name | Value |
|---- | -----|
| PENDENT | &quot;PENDENT&quot; |
| ENVIADA | &quot;ENVIADA&quot; |
| REGISTRADA | &quot;REGISTRADA&quot; |
| FINALITZADA | &quot;FINALITZADA&quot; |
| PROCESSADA | &quot;PROCESSADA&quot; |
| ENVIADA_AMB_ERRORS | &quot;ENVIADA_AMB_ERRORS&quot; |
| FINALITZADA_AMB_ERRORS | &quot;FINALITZADA_AMB_ERRORS&quot; |



