# LlistatPaginatAcces

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**name** | **String** | Nom descriptiu del que s&#x27;està retornant | 
**page** | **Integer** | Número pàgina. Comença per 1. | 
**pageSize** | **Integer** | Mida de pàgina | 
**totalPages** | **Integer** | Número total de pàgines | 
**totalCount** | **Integer** | Número total d&#x27;elements | 
**itemsReturned** | **Integer** | Numero d&#x27;elements retornats | 
**next** | **String** | Si hi ha més elements, llavors retorna la URL a la següent pàgina de dades. |  [optional]
**dateDownload** | **String** | Data i hora en que s&#x27;han retornat les dades. Format ISO-8601 | 
**data** | [**List&lt;Acces&gt;**](Acces.md) | Elements retornats. Pot retornar un null o una llista buida si no hi ha elements. |  [optional]
