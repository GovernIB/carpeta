

# Persona

Representants a qui enviar l'enviament

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**incapacitat** | **Boolean** | Indica si el titular es incapacitat per llegir/rebutjar la notificació. En aquest cas, seria obligatori informar un destinatari de l&#39;enviament. |  [optional] |
|**interessatTipus** | [**InteressatTipusEnum**](#InteressatTipusEnum) | Enumerat que indica el tipus de l’interessat que realitza l’enviament.  * En cas d&#39;indicar un interessat tipus FISICA_SENSE_NIF serà obligatori informar el camp documentTipus |  |
|**nom** | **String** | Nom del titular.  * Obligatori en cas de persones físiques  * Mida màxima de 255 caràcters quan es tracta d&#39;una administració  * Mida màxima de 30 caràcters per la resta de tipus d&#39;interessats  * En el cas de persones jurídiques es posarà el nom en el camp raoSocial. En cas de no informar-se el camp raoSocial s&#39;agafarà el valor indicat en aquest camp |  [optional] |
|**llinatge1** | **String** | Primer llinatge de l’interessat  * Obligatori en cas de persones físiques |  [optional] |
|**llinatge2** | **String** | Segon llinatge de l’interessat |  [optional] |
|**documentTipus** | [**DocumentTipusEnum**](#DocumentTipusEnum) | Enumerat que indica el tipus de document aportat per l’interessat  * Aquest camp únicament es té en compte quan el tipus d’interessat és FISICA_SENSE_NIF |  [optional] |
|**nif** | **String** | Número del document de l&#39;interessat  * Obligatori excepte per interessats tipus FISICA_SENSE_NIF |  [optional] |
|**telefon** | **String** | Telèfon de l’interessat  * Actualment no s’utilitza |  [optional] |
|**email** | **String** | Correu electrònic de l’interessat.   * Altament recomanat el seu ús. |  [optional] |
|**raoSocial** | **String** | Raó social de l’interessat  * S’utilitza únicament en cas de persones jurídiques, i si no s’informa aquest camp, s’utilitzarà el valor del camp nom. És obligatori que un dels dos camps estigui informat |  [optional] |
|**dir3Codi** | **String** | Codi DIR3 de la administració a la que pertany la persona.  * Obligatori si s’ha d’enviar a una administració. |  |



## Enum: InteressatTipusEnum

| Name | Value |
|---- | -----|
| ADMINISTRACIO | &quot;ADMINISTRACIO&quot; |
| FISICA | &quot;FISICA&quot; |
| JURIDICA | &quot;JURIDICA&quot; |
| FISICA_SENSE_NIF | &quot;FISICA_SENSE_NIF&quot; |



## Enum: DocumentTipusEnum

| Name | Value |
|---- | -----|
| PASSAPORT | &quot;PASSAPORT&quot; |
| ESTRANGER | &quot;ESTRANGER&quot; |
| ALTRE | &quot;ALTRE&quot; |



