

# RespostaConsultaEstatEnviamentV2


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**error** | **Boolean** | Indica si s&#39;ha produït algún error en l&#39;enviament |  [optional] |
|**errorData** | **Long** | Data en que s&#39;ha produït l&#39;error |  [optional] |
|**errorDescripcio** | **String** | Identificador de la notificació a Notib |  [optional] |
|**identificador** | **String** | Identificador de la notificació a Notib |  [optional] |
|**referencia** | **String** | Identificador de l&#39;enviament a Notib |  [optional] |
|**notificaIndentificador** | **String** | Identificador de l&#39;enviament a Notific@ |  [optional] |
|**estat** | [**EstatEnum**](#EstatEnum) | Enumerat amb la informació de l&#39;estat de l&#39;enviament |  [optional] |
|**estatData** | **Long** | Data en que s&#39;ha assignat l&#39;estat actual de l&#39;enviament |  [optional] |
|**estatDescripcio** | **String** | Descripció de l&#39;estat de l&#39;enviament |  [optional] |
|**enviamentSir** | **Boolean** | Indica si l&#39;enviament s&#39;ha realitzat a través de SIR |  [optional] |
|**dehObligat** | **Boolean** | Indica si el destinatari està obligat a rebre les notificacions al DEH |  [optional] |
|**dehNif** | **String** | Nif a utilitzar per l&#39;enviamnt a DEH |  [optional] |
|**entragaPostalActiva** | **Boolean** | Indica si l&#39;enviament està configurat per enviament postal |  [optional] |
|**adressaPostal** | **String** | Adreça on es realitzarà l&#39;enviament postal |  [optional] |
|**interessat** | [**Persona**](Persona.md) |  |  [optional] |
|**representants** | [**List&lt;Persona&gt;**](Persona.md) | Representants a qui enviar l&#39;enviament |  [optional] |
|**registre** | [**Registre**](Registre.md) |  |  [optional] |
|**sir** | [**Sir**](Sir.md) |  |  [optional] |
|**datat** | [**Datat**](Datat.md) |  |  [optional] |
|**certificacio** | [**Certificacio**](Certificacio.md) |  |  [optional] |



## Enum: EstatEnum

| Name | Value |
|---- | -----|
| NOTIB_PENDENT | &quot;NOTIB_PENDENT&quot; |
| NOTIB_ENVIADA | &quot;NOTIB_ENVIADA&quot; |
| ABSENT | &quot;ABSENT&quot; |
| ADRESA_INCORRECTA | &quot;ADRESA_INCORRECTA&quot; |
| DESCONEGUT | &quot;DESCONEGUT&quot; |
| ENVIADA_CI | &quot;ENVIADA_CI&quot; |
| ENVIADA_DEH | &quot;ENVIADA_DEH&quot; |
| ENVIAMENT_PROGRAMAT | &quot;ENVIAMENT_PROGRAMAT&quot; |
| ENTREGADA_OP | &quot;ENTREGADA_OP&quot; |
| ERROR_ENTREGA | &quot;ERROR_ENTREGA&quot; |
| EXPIRADA | &quot;EXPIRADA&quot; |
| EXTRAVIADA | &quot;EXTRAVIADA&quot; |
| MORT | &quot;MORT&quot; |
| LLEGIDA | &quot;LLEGIDA&quot; |
| NOTIFICADA | &quot;NOTIFICADA&quot; |
| PENDENT | &quot;PENDENT&quot; |
| PENDENT_ENVIAMENT | &quot;PENDENT_ENVIAMENT&quot; |
| PENDENT_SEU | &quot;PENDENT_SEU&quot; |
| PENDENT_CIE | &quot;PENDENT_CIE&quot; |
| PENDENT_DEH | &quot;PENDENT_DEH&quot; |
| REBUTJADA | &quot;REBUTJADA&quot; |
| SENSE_INFORMACIO | &quot;SENSE_INFORMACIO&quot; |
| FINALITZADA | &quot;FINALITZADA&quot; |
| ENVIADA | &quot;ENVIADA&quot; |
| REGISTRADA | &quot;REGISTRADA&quot; |
| PROCESSADA | &quot;PROCESSADA&quot; |
| ANULADA | &quot;ANULADA&quot; |
| ENVIAT_SIR | &quot;ENVIAT_SIR&quot; |
| ENVIADA_AMB_ERRORS | &quot;ENVIADA_AMB_ERRORS&quot; |
| FINALITZADA_AMB_ERRORS | &quot;FINALITZADA_AMB_ERRORS&quot; |



