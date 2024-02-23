# Datat

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**estat** | [**EstatEnum**](#EstatEnum) | Estat de la notificació |  [optional]
**data** | [**Date**](Date.md) | Data en que s&#x27;ha creat el datat |  [optional]
**origen** | **String** | Origen del datat |  [optional]
**receptorNif** | **String** | Nif del receptor de la notificació |  [optional]
**receptorNom** | **String** | Nom del receptor de la notificació |  [optional]
**errorDescripcio** | **String** | Descripció de l&#x27;error, en cas que es datat tingui algun error |  [optional]
**numSeguiment** | **String** | Número de seguiment, en cas d&#x27;enviament postal  * Camp actualemnt no utilitzat |  [optional]

<a name="EstatEnum"></a>
## Enum: EstatEnum
Name | Value
---- | -----
NOTIB_PENDENT | &quot;NOTIB_PENDENT&quot;
NOTIB_ENVIADA | &quot;NOTIB_ENVIADA&quot;
ABSENT | &quot;ABSENT&quot;
ADRESA_INCORRECTA | &quot;ADRESA_INCORRECTA&quot;
DESCONEGUT | &quot;DESCONEGUT&quot;
ENVIADA_CI | &quot;ENVIADA_CI&quot;
ENVIADA_DEH | &quot;ENVIADA_DEH&quot;
ENVIAMENT_PROGRAMAT | &quot;ENVIAMENT_PROGRAMAT&quot;
ENTREGADA_OP | &quot;ENTREGADA_OP&quot;
ERROR_ENTREGA | &quot;ERROR_ENTREGA&quot;
EXPIRADA | &quot;EXPIRADA&quot;
EXTRAVIADA | &quot;EXTRAVIADA&quot;
MORT | &quot;MORT&quot;
LLEGIDA | &quot;LLEGIDA&quot;
NOTIFICADA | &quot;NOTIFICADA&quot;
PENDENT | &quot;PENDENT&quot;
PENDENT_ENVIAMENT | &quot;PENDENT_ENVIAMENT&quot;
PENDENT_SEU | &quot;PENDENT_SEU&quot;
PENDENT_CIE | &quot;PENDENT_CIE&quot;
PENDENT_DEH | &quot;PENDENT_DEH&quot;
REBUTJADA | &quot;REBUTJADA&quot;
SENSE_INFORMACIO | &quot;SENSE_INFORMACIO&quot;
FINALITZADA | &quot;FINALITZADA&quot;
ENVIADA | &quot;ENVIADA&quot;
REGISTRADA | &quot;REGISTRADA&quot;
PROCESSADA | &quot;PROCESSADA&quot;
ANULADA | &quot;ANULADA&quot;
ENVIAT_SIR | &quot;ENVIAT_SIR&quot;
ENVIADA_AMB_ERRORS | &quot;ENVIADA_AMB_ERRORS&quot;
FINALITZADA_AMB_ERRORS | &quot;FINALITZADA_AMB_ERRORS&quot;
