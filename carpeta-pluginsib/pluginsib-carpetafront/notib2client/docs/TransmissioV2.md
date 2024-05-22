

# TransmissioV2

Llistat de les notificacions retornades

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** | Identificador de l&#39;enviament |  [optional] |
|**emisor** | **String** | Codi dir3 de l&#39;entitat emisora |  [optional] |
|**organGestor** | [**GenericInfo**](GenericInfo.md) |  |  [optional] |
|**procediment** | [**GenericInfo**](GenericInfo.md) |  |  [optional] |
|**numExpedient** | **String** | Número de l’expedient al que està associada la notificació |  [optional] |
|**concepte** | **String** | Concepte de la notificació |  [optional] |
|**descripcio** | **String** | Descripció de la notificació |  [optional] |
|**dataEnviament** | **Long** | Data d&#39;enviament de la notificació |  [optional] |
|**estat** | [**GenericInfo**](GenericInfo.md) |  |  [optional] |
|**dataEstat** | **Long** | Data en que s&#39;ha realitzat l&#39;enviament |  [optional] |
|**document** | [**DocumentConsultaV2**](DocumentConsultaV2.md) |  |  [optional] |
|**titular** | [**PersonaConsultaV2**](PersonaConsultaV2.md) |  |  [optional] |
|**destinataris** | [**List&lt;PersonaConsultaV2&gt;**](PersonaConsultaV2.md) | Persones representans, destinatàries de l&#39;enviament |  [optional] |
|**error** | **Boolean** | Informa si s&#39;ha produït algun error en la notificació |  [optional] |
|**errorData** | **Long** | Data de l&#39;error |  [optional] |
|**errorDescripcio** | **String** | Descripció de l&#39;error |  [optional] |
|**justificant** | **String** | Url per a descarregar el justificant de registre |  [optional] |
|**certificacio** | **String** | Url per a descarregar la Certificació generada al realitzar la compareixença de la notificació |  [optional] |



