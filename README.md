# Listas digitales (digitalLists)

## Tabla de contenido

1. [Descripción](#descripción)
2. [Diagrama](#diagrama)
3. [Tecnología](#tecnología)
4. [Despliegue](#despliegue)
5. [Endpoints](#endpoints)
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

## Despliegue

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

## Endpoints

Se recomienda probarse en local o en despliegue definitico máquina virtual. Si se despliega en heroku no generará código QR por cuestión de configuración.Caso contrario, probado en local sí generará el código.

A continuación enuncio los endpoints disponibles en el componente:

### Razones (reason)

- Listar todos las razones:

`url/digitalLists/reason/`

_Verbo Get._

- Listar razón por id:

`url/digitalLists/reason/<id>`

_Verbo Get._

- Crear razón:

`url/digitalLists/reason/`

_Verbo Post._

_Se debe pasar el objeto 'reason' con sus respectivos campos (name, description(opcional), eventDate)._

- Actualizar razón por id:

`url/digitalLists/reason/<id>`

_Verbo Put._

_Se debe pasar el objeto reason con sus respectivos campos actualizados (name, description(opcional), eventDate)._

- Eliminar razón por id:

`url/digitalLists/reason/<id>`

_Verbo Delete._

### Usuarios (user)

- Listar todos los usuarios:

`url/digitalLists/user/`

_Verbo Get._

- Listar usuario por id:

`url/digitalLists/user/<id>`

_Verbo Get._

- Crear usuario:

`url/digitalLists/user/`

_Verbo Post._

_Se debe pasar el objeto 'user' con sus respectivos campos (fullName, documentType, documentNumber, email, reason:{id})._

- Actualizar usuario por id:

`url/digitalLists/user/<id>`

_Verbo Put._

_Se debe pasar el objeto 'user' con sus respectivos campos actualizados (fullName, documentType, documentNumber, email, reason:{id})._

- Eliminar usuario por id:

`url/digitalLists/user/<id>`

_Verbo Delete._

### Registros (record)

- Listar todos los registros:

`url/digitalLists/record/`

_Verbo Get._

- Listar registro por id:

`url/digitalLists/record/<id>`

_Verbo Get._

- Crear registro:

`url/digitalLists/record/`

_Verbo Post._

_Se debe pasar el objeto 'record' con el campo (user:{id})._

- Actualizar registro por id:

`url/digitalLists/record/<id>`

_Verbo Put._

_Se debe pasar el objeto 'record' con sus respectivos campos actualizados (user:{id})._

- Eliminar usuario por id:

`url/digitalLists/record/<id>`

_Verbo Delete._

## Autor

[Sergio Manrique](https://www.linkedin.com/in/seraleman/)

