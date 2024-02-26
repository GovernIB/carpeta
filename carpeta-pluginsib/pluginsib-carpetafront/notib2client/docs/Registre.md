# Registre

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**numero** | **Integer** | Número de registre |  [optional]
**data** | **Long** | Data del registre |  [optional]
**numeroFormatat** | **String** | Número de registre amb format |  [optional]
**estat** | [**EstatEnum**](#EstatEnum) | Estat del registre |  [optional]
**oficina** | **String** | Oficina on s&#x27;ha realitzat el registre |  [optional]
**llibre** | **String** | Llibre on s&#x27;ha anotat el registre |  [optional]

<a name="EstatEnum"></a>
## Enum: EstatEnum
Name | Value
---- | -----
VALID | &quot;VALID&quot;
RESERVA | &quot;RESERVA&quot;
PENDENT | &quot;PENDENT&quot;
OFICI_EXTERN | &quot;OFICI_EXTERN&quot;
OFICI_INTERN | &quot;OFICI_INTERN&quot;
OFICI_ACCEPTAT | &quot;OFICI_ACCEPTAT&quot;
DISTRIBUIT | &quot;DISTRIBUIT&quot;
ANULAT | &quot;ANULAT&quot;
RECTIFICAT | &quot;RECTIFICAT&quot;
REBUTJAT | &quot;REBUTJAT&quot;
REENVIAT | &quot;REENVIAT&quot;
DISTRIBUINT | &quot;DISTRIBUINT&quot;
OFICI_SIR | &quot;OFICI_SIR&quot;
