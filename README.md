# Listas digitales (digitalLists)

## Tabla de contenido

1. [Descripción](#descripción)
2. [Diagrama](#diagrama)
3. [Tecnología](#tecnología)
4. [Pruebas y despliegue](#pruebas-y-despliegue)  
&nbsp;4.1 [Base de datos](#base-de-datos)  
&nbsp;4.2 [Módulo](#módulo)  
&nbsp;4.3 [Endpoints](#endpoints)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.1 [Razones](#razones-reason)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.2 [Usuarios](#usuarios-user)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.3 [Registros](#registros-record)
6. [Autor](#autor)

## Descripción

Módulo backend encargado de adminitrar los registros de las visitas al HUB de innovación SENA. En este se pueden gestionar las entidades:

- Razones de visitas.
- La creación de usuarios y el envío automático de QR para que este sea leído en recepción y se persista la información de la visita.
- La persistencia de los registros.

## Diagrama

```
├── src
│   └── main
│       └── java
│           ├── ...
│               └── digital_lists_be
│                   ├── components
│                       ├── reason
│                           ├── helpers
│                               ├── IReasonService.java
│                               └── ReasonServiceImpl.java
│                           ├── IReasonDao.java
│                           ├── Reason.java
│                           └── ReasonRestController.java
│                       ├── record
│                           ├── helpers
│                               ├── IRecordService.java
│                               └── RecordServiceImpl.java
│                           ├── IRecordDao.java
│                           ├── Record.java
│                           └── RecordRestController.java
│                       ├── user
│                           ├── helpers
│                               ├── IUserService.java
│                               └── UserService.java
│                           ├── IUserDao.java
│                           ├── User.java
│                           └── UserRestController.java
│                   ├── helpers
│                       └── email
│                           └── Email.java
│                       ├── localDateTime
│                           ├── ILocalDateTime.java
│                           └── LocalDateTimeImp.java
│                       ├── qr
│                           └── QRCodeGenerator.java
│                       └── response
│                           ├── IResponse.java
│                           └── ResponseImpl.java
|                   └── DigitalListsBeApplication.java
│           └── resources
|               ├── qrCodes
|                   └── QRCode.png
|               └── application.properties
├── Dockerfile
├── pom.xml
├── ...
└── README.md
```

## Tecnología

- Java
- SpringBoot
- MySQL

## Pruebas y despliegue

### Base de Datos

La base de datos temporal se encuentra desplegada en la nube en Heroku, informo credenciales por si se desea comprobar persistencia:

- Nombre del hosto / IP `us-cdbr-east-05.cleardb.net`
- Usuario `b6df61649f1f12`
- Contraseña `8bbc9001`
- Base de datos `heroku_ef5d732d7056839`
- Puerto `3306` o preferencia

### Módulo

Una copia del módulo está desplegada en Heroku, se puede acceder a él en la siguiente URL [https://hub-digital-lists-backend.herokuapp.com/](https://hub-digital-lists-backend.herokuapp.com/). Para su uso es necesario disponer de un software como Thunder Client o crear un componente frontend que lo consuma.

Este componente desplegado solo hace el trabajo de persistir la información en la base de datos al leer el código QR ya que era necesario que existiera en la web.

### Endpoints

Se recomienda probarse en local o en despliegue definitico máquina virtual. Si se despliega en heroku no generará código QR por cuestión de configuración. Caso contrario, probado en local sí generará el código.

A continuación enuncio los endpoints disponibles en el componente (recuerde cambiar `<url_despliegue>` por la url de despliegue o de prueba `localhost:8080`):

### **Razones (reason)**

> Listar todas las razones:
>> Verbo Get  
`<url_despliegue>/digitalLists/reason/`  

> Listar razón por id:
>> Verbo Get  
`<url_despliegue>/digitalLists/reason/4`

> Crear razón:
>> Verbo Post  
`<url_despliegue>/digitalLists/reason/`  
_Se debe pasar el objeto 'reason'._
```
{
    "name": "Taller 4.0",
    "eventDate": "2021-06-10 13:00:00",
    "description": "Lanzamiento ruta RIIES Hospital General"
}
```
> Actualizar razón por id:
>> Verbo Put  
`<url_despliegue>/digitalLists/reason/4`  
_Se debe pasar el objeto 'reason' actualizado._
```
{
  "eventDate": "2021-12-05 14:00:00",
  "name": "Taller CSS",
  "description": "Lanzamiento ruta RIIES Hospital General"
}
```

> Eliminar razón por id:
>> Verbo Delete  
`<url_despliegue>/digitalLists/reason/4`


### **Usuarios (user)**

> Listar todos los usuarios:
>> Verbo Get  
`<url_despliegue>/digitalLists/user/`  

> Listar usuario por id:
>> Verbo Get  
`<url_despliegue>/digitalLists/user/4`

> Listar usuario por número de documento:
>> Verbo Get  
`<url_despliegue>/digitalLists/user/1032658923`

> Crear usuario:
>> Verbo Post  
`<url_despliegue>/digitalLists/user/`  
_Se debe pasar el objeto 'user'._
```
{
  "documentType": "Cédula de ciudadanía",
  "email": "petropresidente@gmail.com",
  "documentNumber": "1000000002",
  "fullName": "Gustavo Petro",
  "reason": {
    "id": 4
  }
}
```

> Actualizar usuario por id:
>>Verbo Put  
`<url_despliegue>/digitalLists/user/4`  
_Se debe pasar el objeto 'user' actualizado._
```
{
  "fullName": "Daniel Correa prueba",
  "documentType": "Cédula de ciudadanía",
  "documentNumber": "10326589",
  "email": "daniel@gmail.com",
  "happening": "Taller de HTML"
}
```

> Eliminar usuario por id:
Verbo Delete  
`<url_despliegue>/digitalLists/user/4`  


### **Registros (record)**

> Listar todos los registros:
>> Verbo Get  
`<url_despliegue>/digitalLists/record/`

> Listar registro por id:
>> Verbo Get  
`<url_despliegue>/digitalLists/record/4`  

> Crear registro:
>> Verbo Post  
`<url_despliegue>/digitalLists/record/`  
_Se debe pasar el objeto 'record'._
```
{
    "user":{
        "id": 4
    }
}
```

> Actualizar registro por id:
>> Verbo Put  
`<url_despliegue>/digitalLists/record/4`  
_Se debe pasar el objeto 'record' actualizado._
```
{
  "user": {
    "id": 14
  }
}
```

> Eliminar usuario por id:
>> Verbo Delete  
`<url_despliegue>/digitalLists/record/4`


## Autor
**[Sergio Manrique](https://www.linkedin.com/in/seraleman/)**

