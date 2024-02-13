# RespostaAltaV2

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**error** | **Boolean** | Indica si s&#x27;ha produït algún error en l&#x27;enviament |  [optional]
**errorData** | [**DateTime**](DateTime.md) | Data en que s&#x27;ha produït l&#x27;error |  [optional]
**errorDescripcio** | **String** | Identificador de la notificació a Notib |  [optional]
**identificador** | **String** | Identificador únic que se li ha assignat a la notificació a Notib |  [optional]
**estat** | [**EstatEnum**](#EstatEnum) | Estat de la notificació |  [optional]
**referencies** | [**List&lt;EnviamentReferenciaV2&gt;**](EnviamentReferenciaV2.md) | Referències (identificadors) úniques que s&#x27;han assignat a de cada un dels enviaments de la notificació |  [optional]
**dataCreacio** | **String** | Data en que s&#x27;ha donat d&#x27;alta la notificació |  [optional]

<a name="EstatEnum"></a>
## Enum: EstatEnum
Name | Value
---- | -----
PENDENT | &quot;PENDENT&quot;
ENVIADA | &quot;ENVIADA&quot;
REGISTRADA | &quot;REGISTRADA&quot;
FINALITZADA | &quot;FINALITZADA&quot;
PROCESSADA | &quot;PROCESSADA&quot;
ENVIADA_AMB_ERRORS | &quot;ENVIADA_AMB_ERRORS&quot;
FINALITZADA_AMB_ERRORS | &quot;FINALITZADA_AMB_ERRORS&quot;
