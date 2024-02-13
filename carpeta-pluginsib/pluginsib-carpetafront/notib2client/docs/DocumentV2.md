# DocumentV2

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**arxiuNom** | **String** | Nom de l’arxiu | 
**contingutBase64** | **String** | Contingut del document en Base64.  * Obligatori si no està informat l’enllaç extern, l’uuid o el csv. * Mida màxima 10Mb |  [optional]
**uuid** | **String** | Codi uuid que es pot utilitzar per tal d’obtenir el document imprimible del sistema d’arxiu  * Obligatori si no està informat l’enllaç extern, el contingut en Base64 o el csv. |  [optional]
**csv** | **String** | Codi csv que es pot utilitzar per tal d’obtenir el document imprimible del sistema d’arxiu  * Obligatori si no està informat l’enllaç extern, el contingut en Base64 o l&#x27;uuid. |  [optional]
**url** | **String** | Enllaç extern on es troba el document de l’enviament.  * Obligatori si no està informat l’uuid, el contingut en Base64 o el csv. * Sistema actualment NO soportat. |  [optional]
**normalitzat** | **Boolean** | Indica si el document està normalitzat per a la impressió al CIE.   * Només aplica per enviaments postals. |  [optional]
**origen** | [**OrigenEnum**](#OrigenEnum) | Enumerat que indica l’origen del document.  * No s’utilitza en el cas de documents passats com a csv o uuid |  [optional]
**tipoDocumental** | [**TipoDocumentalEnum**](#TipoDocumentalEnum) | Enumerat que indica el tipus de document.  * No s’utilitza en el cas de documents passats com a csv o uuid |  [optional]
**validesa** | [**ValidesaEnum**](#ValidesaEnum) | Enumerat que indica la validesa del document * No s’utilitza en el cas de documents passats com a csv o uuid |  [optional]
**modoFirma** | **Boolean** | Indica, en cas de document pdf, si aquest està firmat electrònicament.  * No s’utilitza en el cas de documents passats com a csv o uuid. |  [optional]

<a name="OrigenEnum"></a>
## Enum: OrigenEnum
Name | Value
---- | -----
CIUTADA | &quot;CIUTADA&quot;
ADMINISTRACIO | &quot;ADMINISTRACIO&quot;

<a name="TipoDocumentalEnum"></a>
## Enum: TipoDocumentalEnum
Name | Value
---- | -----
RESSOLUCIO | &quot;RESSOLUCIO&quot;
ACORD | &quot;ACORD&quot;
CONTRACTE | &quot;CONTRACTE&quot;
CONVENI | &quot;CONVENI&quot;
DECLARACIO | &quot;DECLARACIO&quot;
COMUNICACIO | &quot;COMUNICACIO&quot;
NOTIFICACIO | &quot;NOTIFICACIO&quot;
PUBLICACIO | &quot;PUBLICACIO&quot;
JUSTIFICANT_RECEPCIO | &quot;JUSTIFICANT_RECEPCIO&quot;
ACTA | &quot;ACTA&quot;
CERTIFICAT | &quot;CERTIFICAT&quot;
DILIGENCIA | &quot;DILIGENCIA&quot;
INFORME | &quot;INFORME&quot;
SOLICITUD | &quot;SOLICITUD&quot;
DENUNCIA | &quot;DENUNCIA&quot;
ALEGACIO | &quot;ALEGACIO&quot;
RECURS | &quot;RECURS&quot;
COMUNICACIO_CIUTADA | &quot;COMUNICACIO_CIUTADA&quot;
FACTURA | &quot;FACTURA&quot;
ALTRES_INCAUTATS | &quot;ALTRES_INCAUTATS&quot;
ALTRES | &quot;ALTRES&quot;
LLEI | &quot;LLEI&quot;
MOCIO | &quot;MOCIO&quot;
INSTRUCCIO | &quot;INSTRUCCIO&quot;
CONVOCATORIA | &quot;CONVOCATORIA&quot;
ORDRE_DIA | &quot;ORDRE_DIA&quot;
INFORME_PONENCIA | &quot;INFORME_PONENCIA&quot;
DICTAMEN_COMISSIO | &quot;DICTAMEN_COMISSIO&quot;
INICIATIVA_LEGISLATIVA | &quot;INICIATIVA_LEGISLATIVA&quot;
PREGUNTA | &quot;PREGUNTA&quot;
INTERPELACIO | &quot;INTERPELACIO&quot;
RESPOSTA | &quot;RESPOSTA&quot;
PROPOSICIO_NO_LLEI | &quot;PROPOSICIO_NO_LLEI&quot;
ESQUEMA | &quot;ESQUEMA&quot;
PROPOSTA_RESOLUCIO | &quot;PROPOSTA_RESOLUCIO&quot;
COMPAREIXENSA | &quot;COMPAREIXENSA&quot;
SOLICITUD_INFORMACIO | &quot;SOLICITUD_INFORMACIO&quot;
ESCRIT | &quot;ESCRIT&quot;
INICIATIVA_LEGISLATIVA2 | &quot;INICIATIVA_LEGISLATIVA2&quot;
PETICIO | &quot;PETICIO&quot;

<a name="ValidesaEnum"></a>
## Enum: ValidesaEnum
Name | Value
---- | -----
COPIA | &quot;COPIA&quot;
COPIA_AUTENTICA | &quot;COPIA_AUTENTICA&quot;
ORIGINAL | &quot;ORIGINAL&quot;
