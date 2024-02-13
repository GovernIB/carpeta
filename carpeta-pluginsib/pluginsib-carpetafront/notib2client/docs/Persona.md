# Persona

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**incapacitat** | **Boolean** | Indica si el titular es incapacitat per llegir/rebutjar la notificació. En aquest cas, seria obligatori informar un destinatari de l&#x27;enviament. |  [optional]
**interessatTipus** | [**InteressatTipusEnum**](#InteressatTipusEnum) | Enumerat que indica el tipus de l’interessat que realitza l’enviament.  * En cas d&#x27;indicar un interessat tipus FISICA_SENSE_NIF serà obligatori informar el camp documentTipus | 
**nom** | **String** | Nom del titular.  * Obligatori en cas de persones físiques  * Mida màxima de 255 caràcters quan es tracta d&#x27;una administració  * Mida màxima de 30 caràcters per la resta de tipus d&#x27;interessats  * En el cas de persones jurídiques es posarà el nom en el camp raoSocial. En cas de no informar-se el camp raoSocial s&#x27;agafarà el valor indicat en aquest camp |  [optional]
**llinatge1** | **String** | Primer llinatge de l’interessat  * Obligatori en cas de persones físiques |  [optional]
**llinatge2** | **String** | Segon llinatge de l’interessat |  [optional]
**documentTipus** | [**DocumentTipusEnum**](#DocumentTipusEnum) | Enumerat que indica el tipus de document aportat per l’interessat  * Aquest camp únicament es té en compte quan el tipus d’interessat és FISICA_SENSE_NIF |  [optional]
**nif** | **String** | Número del document de l&#x27;interessat  * Obligatori excepte per interessats tipus FISICA_SENSE_NIF |  [optional]
**telefon** | **String** | Telèfon de l’interessat  * Actualment no s’utilitza |  [optional]
**email** | **String** | Correu electrònic de l’interessat.   * Altament recomanat el seu ús. |  [optional]
**raoSocial** | **String** | Raó social de l’interessat  * S’utilitza únicament en cas de persones jurídiques, i si no s’informa aquest camp, s’utilitzarà el valor del camp nom. És obligatori que un dels dos camps estigui informat |  [optional]
**dir3Codi** | **String** | Codi DIR3 de la administració a la que pertany la persona.  * Obligatori si s’ha d’enviar a una administració. | 

<a name="InteressatTipusEnum"></a>
## Enum: InteressatTipusEnum
Name | Value
---- | -----
ADMINISTRACIO | &quot;ADMINISTRACIO&quot;
FISICA | &quot;FISICA&quot;
JURIDICA | &quot;JURIDICA&quot;
FISICA_SENSE_NIF | &quot;FISICA_SENSE_NIF&quot;

<a name="DocumentTipusEnum"></a>
## Enum: DocumentTipusEnum
Name | Value
---- | -----
PASSAPORT | &quot;PASSAPORT&quot;
ESTRANGER | &quot;ESTRANGER&quot;
ALTRE | &quot;ALTRE&quot;
