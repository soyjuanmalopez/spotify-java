# SINERGIAS FRONTED-BACKEND

## Formato atributos básicos

-   **Booleano:**  true/false
-   **Fechas:**  2020-10-05T12:41:59+00:00
-   **Días de la semana**  : Se establece como formato estándar una lista de 7 elementos indicando si ese día está activo o no mediante booleanos. Ej: { "operatingDays": [true, false, true, false, true, true, false] }
-   **Enums**  String en mayus "EXAMPLE"
-   **Listas vacías:**  Se informarán las listas vacías
-   **Monedas:**  ISO 4217
-   **Ciudades:**  ISO 3166 - Código alfa-2
-   **Limitadores de números:**  1500.87

## Respuestas de las peticiones
```js 
{
	code: 200, 
	data: ..., 
	message: "OK", 
	status: "Success"
 }
```
## Headers

### Language

```js 
{ 'Content-Language' : es-ES }
```
### Authorization 
```js 
{ 'Authorization': Bearer <token> }
```
## ## Error responses

|CODE                |Message                  |Description                         |
|----------------|-------------------------------|-----------------------------|
|1XX|Informational            |-            |
|2XX|Success            |-            |
|3XX|Redirection|-            |
|4XX|Client Error|-            |
|400|Bad Request|-            |
|401|Unauthorized|-            |
|403|Forbidden            |-            |
|404|File Not Found|-            |
|405|Method Not Allowed|-            |
|408|Request Timeout|-            |
|5XX|Server Error|-            |
|501|Not Implemented|-            |
|502|Bad Gateway|-            |
|503|Service Unavailable|-            |
|504|Gateway Timeout|-            |
|505|HTTP Version Not Supported|-            |

##  Query params

Keys query params con CamelCase [https://api-url.com/xxx?testParam=0&sizeParam=7](https://api-url.com/xxx?testParam=0&sizeParam=7)


## Paginación

### Estructura petición

[https://api-url.com/xxx?page=0&size=9](https://api-url.com/xxx?page=0&size=9)


### Respuesta

```
data: {
    content: [...],
    page: {
        pageNumber: 0,
        pageSize: 9,
        totalPages: 6
    }
}
```

## Paginación dinámica

Las colecciones dinámicas pueden variar en cada momento y en estos escenarios no es factible devolver posiciones en la colección. En estos casos se utiliza la paginación dinámica ya que mejora el rendimiento al no tener en cuenta el total de registros ni el número de páginas.

La páginación dinámica utiliza el concepto de offset. El offset indicará el primer o último registro de la página actual para que la query busque los X registros anterior o posteriores al offset.

Para la páginación dinámica se requiere lógica por parte de FRONT para enviar los parámetros correctamente. La respuesta de back solo devuelve la información.


### Estructura petición

[https://api-url.com/xxx?size=10&order=desc&offset=idValue](https://api-url.com/xxx?size=10&order=desc&offset=idValue)

-   **size:**  número de registros a devolver
-   **order:**  con valores desc o asc para indicar si queremos los registros de la página anterior o posterior.
-   **offset:**  indica, por ejemplo, el id del primer o último registro dependiendo de la siguiente página que se quiere mostrar.
-   **sort:**  opcional, para indicar por que campo se está ordenando la información.

### Respuesta

```
data: {
    content: [...]
}
```


## Nulls

-   Los campos con valores null se informaran tanto desde Back como desde Front para garantizar la estructura de la información y facilitar el procesamiento por parte de Front.
-   Ejemplos:

```
{ 
    "foo": "asasdasd",
    "bar": "asdasda"
}

{ 
    "foo": "asasdasd",
    "bar": null
}

{ 
    "foo": "asasdasd",
}
```
## Doc contratos backend/frontend

-   Se documentará el contrato en confluence y se adjuntará a la historia de Jira correspondiente. La documentación automática se seguirá generando con Swagger.